package controller;
import view.*;
import model.*;
import shared.code.Request;
import shared.code.Response;

import java.awt.Color;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import java.util.HashMap;

import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ClientConfig.ClientConfig;





public class Controller {
	private static final String ConfigEnVar="REQUESTS_LOCATION";
	private final String RequestsFileLocation;
	private final static Logger logger=LoggerFactory.getLogger(Controller.class.getName());
	private Model mdl;
	private View vw;
	public static ClientConfig clientconfig;
	
	public Controller(Model mdl,View vw) throws JsonParseException, JsonMappingException, IOException {
		this.mdl=mdl;
		this.vw=vw;
		RequestsFileLocation= System.getenv(ConfigEnVar);
		loadData();
		loadButtons();
		
		
	}

	public void loadButtons() {
		firstPageButtonLoad();
		refreshButtonLoad();
		quitButtonLoad();
		okButtonLoad();
		loadMappingButtons();
		
	}
	public void loadData() {
		loadCompaniesBox();
		buildingAndFloorBoxLoad();
		
	}
	public void buildingAndFloorBoxLoad() {
		Response response;
		try {
			response = sendRequestToServer("select-Buildings.json",null);
			String responseBody=response.getResponseBody().substring(response.getResponseBody().indexOf("["),
					                                                        response.getResponseBody().indexOf("]")+1);
			ObjectMapper mapper=new ObjectMapper();
			ArrayList<Building> allBuildings=mdl.getAllBuildings();
			 allBuildings = mapper.readValue(responseBody,
                    new TypeReference<ArrayList<Building>>(){});
			 for(Building building: allBuildings) {
				  LoanPanel.getBuildingBox().addItem(building);
			  }
			 for(int i=1;i<=allBuildings.get(0).getNb_of_floor();i++) {
				 LoanPanel.getFloorBox().addItem("Étage "+i); 
			 }
			 LoanPanel.getBuildingBox().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
	                for(int i=0;i<LoanPanel.getBuildingBox().getItemCount();i++) {
	                	if((LoanPanel.getBuildingBox().getSelectedIndex()==i)) {
	                		LoanPanel.getFloorBox().removeAllItems();
	                		for(int j=1;j<=Integer.valueOf(((Building)LoanPanel.getBuildingBox().getSelectedItem()).getNb_of_floor());j++) {
	                			LoanPanel.getFloorBox().addItem("Étage "+j);
	                		}
	                	}
	                }
				} 
			 });
			 LoanPanel.getBtnOkFloorBuilding().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						LoanPanel.getPanelMap().removeAll();
						LoanPanel.getPanelMap().setLayout(new FlowLayout(FlowLayout.CENTER,15,15));
						LoanPanel.getPanelMap().revalidate();
						LoanPanel.getPanelMap().repaint();
						int floorNumber=LoanPanel.getFloorBox().getSelectedIndex()+1;
						int buildingNumber=LoanPanel.getBuildingBox().getSelectedIndex()+1;
						loadWorkSpaces();
						loadBuildingMap();
						workSpaceButtonLoad();
						for(int i=0;i<mdl.getAllWorkSpaces().size();i++) {
							WorkSpace workSpace=mdl.getAllWorkSpaces().get(i);
							if((workSpace.getId_building()==buildingNumber)&&(workSpace.getSpace_floor()==floorNumber)) {
								if((workSpace.isTaken())&&(workSpace.getId_entreprise()==mdl.getSelectedCompany().getId_entreprise())) {
									workSpace.getWorkSpaceButton().setBackground(new Color(143, 188, 143));	
								}
								else if(workSpace.isTaken()) {
									workSpace.getWorkSpaceButton().setBackground(new Color(165, 42, 42));
									workSpace.getWorkSpaceButton().setEnabled(false);
								}
								else {
									workSpace.getWorkSpaceButton().setBackground(new Color(169, 169, 169));	
								}
								workSpace.getWorkSpaceButton().setText("Espace "+workSpace.getId_work_space());
								LoanPanel.getPanelMap().add(workSpace.getWorkSpaceButton());
								LoanPanel.getPanelMap().validate();
							}
						}	
					}
				});
			 } catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
	}
	public void loadBuildingMap() {
		int row=0;int column=0;
		for(Building building: mdl.getAllBuildings()) {
			building.setMapBuilding(new HashMap<Integer,WorkSpace [][]>());
			for(int i=1;i<=building.getNb_of_floor();i++) {
				row=0;column=0;
				WorkSpace[][] workSpaceArray=new WorkSpace[10][7]; 
					for(WorkSpace workSpace: mdl.getAllWorkSpaces()) {
						if((workSpace.getId_building()==building.getId_building())&&(workSpace.getSpace_floor()==i)) {
							workSpace.setPosition_X(row);
							workSpace.setPosition_Y(column);
							workSpaceArray[row][column]=workSpace;
							if(column==6) {
								column=0;
								row++;
							}
							else {
								column++;
							}
						}	
					}
				building.getMapBuilding().put(i,workSpaceArray);
			}
     	}
		
	}
	public void loadWorkSpaces() {
		try {
			Response response= sendRequestToServer("select-WorkSpaces.json",null);
			String responseBody=response.getResponseBody().substring(response.getResponseBody().indexOf("["),
                    response.getResponseBody().indexOf("]")+1);
			ObjectMapper mapper=new ObjectMapper();
			ArrayList<WorkSpace> allWorkSpaces=mapper.readValue(responseBody,
					 new TypeReference<ArrayList<WorkSpace>>(){});
			mdl.setAllWorkSpaces(allWorkSpaces);
			
		} catch (InterruptedException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void loadCompaniesBox() {
		loadCompanies();
		for(Company company: mdl.getAllCompanies()) {
			Home.getCompanyNameList().addItem(company);
			
		}
		
		}
	public void loadCompanies() {
		Response response;
		try {
			response = sendRequestToServer("select-Companies.json",null);
			String responseBody=response.getResponseBody().substring(response.getResponseBody().indexOf("["),
	                response.getResponseBody().indexOf("]")+1);
			ObjectMapper mapper=new ObjectMapper();
			ArrayList<Company> allCompanies=mapper.readValue(responseBody,
					 new TypeReference<ArrayList<Company>>(){});
			mdl.setAllCompanies(allCompanies);
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void loadEquipments() {
		try {
			Response response= sendRequestToServer("select-Equipments.json",null);
			String responseBody=response.getResponseBody().substring(response.getResponseBody().indexOf("["),
	                response.getResponseBody().indexOf("]")+1);
			ObjectMapper mapper=new ObjectMapper();
			ArrayList<Equipment> allEquipments=mapper.readValue(responseBody,
					 new TypeReference<ArrayList<Equipment>>(){});
			mdl.setAllEquipments(allEquipments);
			
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public void workSpaceButtonLoad() {
		for(WorkSpace workSpace: mdl.getAllWorkSpaces()) {
			if(workSpace.isTaken()) {
			workSpace.getWorkSpaceButton().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					FrameReservedWorkSpace frame=new FrameReservedWorkSpace();
					MappingWorkSpaceButtonLoad(frame.getButtonMapping(),workSpace,frame);
				  }
				 
			   });
			}
			else if (!(workSpace.isTaken())) {
				
				//mohamed's part
				
				
				
			}
			

		}
	}
	public void MappingWorkSpaceButtonLoad(JButton button,WorkSpace workSpace,JFrame frame) {
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!View.getappFrame().isEnabled()) {
				View.getappFrame().setEnabled(true);
				frame.dispose();
				}
				loadEquipments();
				loadWorkSpaces();
				loadEquipmentsToInstall(workSpace.getId_work_space());
				MappingPanel.setWorkSpace(workSpace);
				FunctionalitiesBarAndPanel.getMyFunctionalities().show(FunctionalitiesBarAndPanel.getFunctionalitiesPanel(),"Mapping");
				MappingPanel.showChoiceOfMappingPanel();
			}
		});
		
	}
	public void loadMappingButtons() {
		MappingPanel.getMapEquipmentsBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				MappingPanel.getEquipmentsToInstallBox().removeAllItems();
				for(Equipment equipment: MappingPanel.getWorkSpace().getEquipmentsToInstall()) {
					if(!(equipment.getEquipment_type().equals("Capteur")||(equipment.getEquipment_type().equals("Capteur de configuration")))) {
						MappingPanel.getEquipmentsToInstallBox().addItem(equipment);
					}
				}
				MappingPanel.showMappingPanel(1);
			}
			
		});
		MappingPanel.getMapSensorsBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MappingPanel.getEquipmentsToInstallBox().removeAllItems();
				MappingPanel.getMappingSpotsPanel().validate();
				for(Equipment equipment: MappingPanel.getWorkSpace().getEquipmentsToInstall()) {
					if(equipment.getEquipment_type().equals("Capteur")||(equipment.getEquipment_type().equals("Capteur de configuration"))) {
						MappingPanel.getEquipmentsToInstallBox().addItem(equipment);
					}
				}
				MappingPanel.showMappingPanel(2);
			}
			
		});
		MappingPanel.getReturnButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MappingPanel.showChoiceOfMappingPanel();
			}
			
		});
	}
	public void loadEquipmentsToInstall(int id_work_space) {
		try {
			Response response= sendRequestToServer("select-MaterialNeeds.json","{\"id_work_space\": \""+id_work_space+"\"}");
			String responseBody=response.getResponseBody().substring(response.getResponseBody().indexOf("["),
	                response.getResponseBody().indexOf("]")+1);
			ObjectMapper mapper=new ObjectMapper();
			ArrayList<Map<String,String>> equipmentsToInstallMAP=mapper.readValue(responseBody,
					 new TypeReference<ArrayList<Map<String,String>>>(){});
			ArrayList<Equipment> equipmentsToInstall=new ArrayList<Equipment>();
			for(Map<String,String> equipmentToInstallMap: equipmentsToInstallMAP) {
				int id_equipment =Integer.valueOf(equipmentToInstallMap.get("id_equipment"));
				for(Equipment equipment: mdl.getAllEquipments()) {
					if(equipment.getId_equipment()==id_equipment) { 
						equipment.setInstalled(Boolean.getBoolean(equipmentToInstallMap.get("installed")));
						equipment.setState(Boolean.getBoolean(equipmentToInstallMap.get("state")));
						equipmentsToInstall.add(equipment);
						break;
					}
				}	
			}
			for(WorkSpace workSpace: mdl.getAllWorkSpaces()) {
				if((workSpace.getId_work_space()==id_work_space)) {
					workSpace.setEquipmentsToInstall(equipmentsToInstall);
					break;
				}
			}
			
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void firstPageButtonLoad() {
		ActionListener firstPageButtonListener=new ActionListener() {
            
			@Override
			public void actionPerformed(ActionEvent e) {
				FunctionalitiesBarAndPanel.getMyFunctionalities().show(FunctionalitiesBarAndPanel.getFunctionalitiesPanel(),"Accueil");	
			}
			
		};
		mouseCursorOnButton(FunctionalitiesBarAndPanel.getFirstPageButton());
		focusButtons(FunctionalitiesBarAndPanel.getFirstPageButton());
		FunctionalitiesBarAndPanel.getFirstPageButton().addActionListener(firstPageButtonListener);
	}
	public void refreshButtonLoad() {
		ActionListener refreshButtonListener=new ActionListener() {
       
			@Override
			public void actionPerformed(ActionEvent e) {
				FunctionalitiesBarAndPanel.getMyFunctionalities().show(FunctionalitiesBarAndPanel.getFunctionalitiesPanel(),"Réservation");	
			}
			
		};
		mouseCursorOnButton(FunctionalitiesBarAndPanel.getRefreshButton());
		focusButtons(FunctionalitiesBarAndPanel.getRefreshButton());

		FunctionalitiesBarAndPanel.getRefreshButton().addActionListener(refreshButtonListener);
	}
	public void quitButtonLoad() {
		ActionListener quitButtonListener=new ActionListener() {
       
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		};
		mouseCursorOnButton(FunctionalitiesBarAndPanel.getQuitButton());
		FunctionalitiesBarAndPanel.getQuitButton().addActionListener(quitButtonListener);
	}

	public void okButtonLoad() {
		ActionListener okButtonListener=new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mdl.setSelectedCompany((Company)Home.getCompanyNameList().getSelectedItem());
				FunctionalitiesBarAndPanel.setInformationLabel(mdl.getSelectedCompany());
				vw.getMyCardPanels().show(vw.getLayeredPanel(), "functions");
				LoanPanel.getBtnOkFloorBuilding().doClick();
			}
			
		};
		
		Home.getOkButton().addActionListener(okButtonListener);
	}
	public void focusButtons(JButton button) {
		FocusListener focusListener=new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				button.setBackground(new Color(176, 196, 222));
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				button.setBackground(new Color(100, 149, 237));
			}
			
		};
		button.addFocusListener(focusListener);
	}
	public void mouseCursorOnButton(JButton button) {
		MouseListener mouseListener=new MouseListener() {

			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				button.setBackground(new Color(176, 196, 222));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if(!button.isFocusOwner()) {// if it's a button from the toolBar
				button.setBackground(new Color(100, 149, 237));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		button.addMouseListener(mouseListener);
	}
	
/******************Server Part*********************************************************/
	public Socket connectToServer()  {
		try {
			
			clientconfig= new ClientConfig();
			//InetAddress ip=InetAddress.getByName(clientconfig.getConfig().getServerIP());
			//logger.info("Trying to connect to IP:{}",ip.getHostAddress());
			InetAddress ip=InetAddress.getByName("localhost");
			return new Socket(ip , clientconfig.getConfig().getDestinationPort());//Connect to the server
		} catch (UnknownHostException e) {
			logger.info("Unknown host:");
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("No I/O");
		}
		return null;
		
	}
	public Response sendRequestToServer(String jsonNameFile,String jsonValues) throws InterruptedException, JsonParseException, JsonMappingException, IOException {
		
		Socket socketClient=connectToServer();
		if(socketClient==null) {
			logger.info("Client not connected to server.");
		}
		else {
			try {
                final ObjectMapper jsonMapper= new ObjectMapper();
        
                Request rq = null;
                	rq=jsonMapper.readValue(new File(RequestsFileLocation+"/"+jsonNameFile), Request.class);
                if(!(jsonValues==null)) {
                	rq.setRequestContent(jsonValues); //If there are values that you would like to add into the RequestBody of your JSON file(the object "jsonValues" must be written in json format)
                }
                
                logger.info("Request from client : {}",jsonMapper.writeValueAsString(rq));
                DataOutputStream out= new DataOutputStream(socketClient.getOutputStream());
                out.write(jsonMapper.writeValueAsBytes(rq),0,jsonMapper.writeValueAsBytes(rq).length);//Sending the json file converted as bytes to the server
                InputStream inputStream=socketClient.getInputStream();
                while(inputStream.available()==0) {
					Thread.sleep(0); /* The thread sleeps until there is data in the inputStream
											sent by the server*/						
				}
                byte [] inputData=new byte[inputStream.available()];
                inputStream.read(inputData);
                logger.debug("Data received {} bytes from server, content={}",inputData.length,new String(inputData));
                Response response=jsonMapper.readValue(new String(inputData), Response.class);
                out.close();
				inputStream.close();
				socketClient.close();
                return response;
                
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
