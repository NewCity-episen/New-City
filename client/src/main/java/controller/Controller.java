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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
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

import javax.imageio.ImageIO;
import javax.sql.rowset.WebRowSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;


import javax.swing.UIManager;




import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

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
		filterLoad();


		loadAdvancedFiltre();
		loadReturnButton();
		loadConfigurateWindows(); 
		loadReturn ();
		//filterButtonLoad();
		loadConfigurate();

		loadMappingButtons();
		loadAdvancedFiltre();
		loadReturnButton();
		loadConfigurateWindows(); 
		//filterButtonLoad();
		

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
				 LoanPanel.getFloorBox().addItem("étage "+i); 
			 }
			
			 LoanPanel.getBuildingBox().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
	                for(int i=0;i<LoanPanel.getBuildingBox().getItemCount();i++) {
	                	if((LoanPanel.getBuildingBox().getSelectedIndex()==i)) {
	                		LoanPanel.getFloorBox().removeAllItems();
	                		for(int j=1;j<=Integer.valueOf(((Building)LoanPanel.getBuildingBox().getSelectedItem()).getNb_of_floor());j++) {
	                			LoanPanel.getFloorBox().addItem("étage "+j);
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
								workSpace.getWorkSpaceButton().setText(workSpace.getSpace_name());
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
	
	public void filterLoad() {
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
				  LoanPanel.getBuildingBoxFilter().addItem(building);
			  }
			 for(int i=1;i<=allBuildings.get(0).getNb_of_floor();i++) {
				 LoanPanel.getFloorBoxFilter().addItem("étage "+i); 
			 }

			 LoanPanel.getBuildingBoxFilter().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
		                for(int i=0;i<LoanPanel.getBuildingBoxFilter().getItemCount();i++) {
		                	if((LoanPanel.getBuildingBoxFilter().getSelectedIndex()==i)) {
		                		LoanPanel.getFloorBoxFilter().removeAllItems();
		                		for(int j=1;j<=Integer.valueOf(((Building)LoanPanel.getBuildingBoxFilter().getSelectedItem()).getNb_of_floor());j++) {
		                			LoanPanel.getFloorBoxFilter().addItem("étage "+j);
		                		}
		                	}
		                }
					} 
				 });
			 
			LoanPanel.getFilterButton().addActionListener(event -> filterButtonLoad());

			 } catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
	}
	
	private void filterButtonLoad() {
		
		try {
			Response response= sendRequestToServer("select-offers.json",null);
			System.out.println("result : " + response.getResponseData());
			ArrayList<Map> offerList = (ArrayList<Map>)response.getResponseData();
			System.out.println("First id :" + offerList.get(0).get("space_id"));
			new LocationOfferPanel(offerList);
		} catch (InterruptedException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//LoanPanel.getJPanel().dispose();
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
					if(!FrameReservedWorkSpace.isOpened()) {
						
					FrameReservedWorkSpace frame=new FrameReservedWorkSpace();
					MappingWorkSpaceButtonLoad(frame.getButtonMapping(),workSpace,frame);
					}
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
				MappingPanel mappingPanel=new MappingPanel();
				if(!View.getappFrame().isEnabled()) {	
				View.getappFrame().setEnabled(true);
				frame.dispose();
				}
				mdl.setMp(mappingPanel);
				loadEquipments();
				loadWorkSpaces();
				loadEquipmentsToInstall(workSpace.getId_work_space());
				mappingPanel.setWorkSpace(workSpace);
				if(!workSpace.isInitialized()) {
				loadMapSpots(mappingPanel);
				}
				else {
					try {
						logger.info("HAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAY");
						updateSpotMap(mappingPanel,1);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				mappingPanel.showChoiceOfMappingPanel();
			}
		});
		
	}
	public void updateSpotMap(MappingPanel MappingPanel,int x) throws IOException {
		int id_work_space=MappingPanel.getWorkSpace().getId_work_space();
		Response response;
		try {
			response = sendRequestToServer("select-Spot.json","{\"id_work_space\": \""+id_work_space+"\"}");
			String responseBody=response.getResponseBody().substring(response.getResponseBody().indexOf("["),
	                response.getResponseBody().indexOf("]")+1);
			ObjectMapper mapper=new ObjectMapper();
			ArrayList<Spot> allSpots=mapper.readValue(responseBody,
					 new TypeReference<ArrayList<Spot>>(){});
			MappingPanel.getWorkSpace().setSpots(allSpots);
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MappingPanel.getCancelButton().setEnabled(false);
	
		if(MappingPanel.getWorkSpace().getSpace_type().equals("open space")) {
			MappingPanel.setSpotsMap(new SpotsMapBackground(RequestsFileLocation+"\\openspace.jpg"));
			
		}
		else if(MappingPanel.getWorkSpace().getSpace_type().equals("bureau")) {
			MappingPanel.setSpotsMap(new SpotsMapBackground(RequestsFileLocation+"\\bureau.jpg"));
		}
		else if(MappingPanel.getWorkSpace().getSpace_type().equals("salle de conference")) {
			MappingPanel.setSpotsMap(new SpotsMapBackground(RequestsFileLocation+"\\salle_de_conférence.jpg"));
		}
		else if(MappingPanel.getWorkSpace().getSpace_type().equals("salle de reunion")) {
			MappingPanel.setSpotsMap(new SpotsMapBackground(RequestsFileLocation+"\\salle_de_réunion.jpg"));
		}
		if(x==1) {
		MappingPanel.getSpotsMap().setBounds(10, 30, 888, 508);
		MappingPanel.getSpotsMap().setLayout(null);
		}
		MappingPanel.getMappingSpotsPanel().add(MappingPanel.getSpotsMap());
		
		
		for(Spot spot: MappingPanel.getWorkSpace().getSpots()) {
			for(Equipment equipment:MappingPanel.getWorkSpace().getEquipmentsToInstall()) {
				if(equipment.getId_equipment()==spot.getId_equipment()) {
					spot.setState(equipment.isState());
				}
			}
			if(x==1) {
				JLabel label=new JLabel();
				spot.setLabelSpot(label);
				MappingPanel.getSpotsMap().add(label);
		
				spot.getLabelSpot().addMouseListener(new MouseAdapter() {
					public void mouseEntered(MouseEvent e) {
						
						spot.getLabelSpot().setIcon(new ImageIcon(RequestsFileLocation+"\\pin-white.png"));
					}
					public void mouseExited(MouseEvent e) {
						if(!spot.getColor().equals("orange")) {
						spot.getLabelSpot().setIcon(new ImageIcon(RequestsFileLocation+"\\pin-"+spot.getColor()+".png"));
						}else {
							spot.getLabelSpot().setIcon(new ImageIcon(RequestsFileLocation+"\\pin-"+spot.getColor()+".gif"));
						}
					}
					public void mouseClicked(MouseEvent e) {
						spot.getPopUpMenu().show(MappingPanel.getSpotsMap(), spot.getPosition_x(), spot.getPosition_y()+40);
					}
				});
			}
			spot.getLabelSpot().setToolTipText("<html><div>id: "+spot.getId_spot()+"</div> installé:"+spot.getEquipmentInstalled()+"</html>");
			spot.getLabelSpot().setBounds(spot.getPosition_x(),spot.getPosition_y(),32, 41);
			spot.getPlaceBtnItem().setEnabled(false);
			if(!spot.isTaken()) {
				spot.getLabelSpot().setIcon(new ImageIcon(RequestsFileLocation+"\\pin-blue.png"));
				spot.setColor("blue");
				spot.getRemoveBtnItem().setEnabled(false);
				}
				else if((spot.isTaken())&&(spot.isState())) {
					spot.getLabelSpot().setIcon(new ImageIcon(RequestsFileLocation+"\\pin-green.png"));
					spot.setColor("green");
					spot.getRemoveBtnItem().setEnabled(true);
				}
				else if((spot.isTaken())&&((!spot.isState()))){
					logger.info("result:{}",spot.isState());
					spot.getLabelSpot().setIcon(new ImageIcon(RequestsFileLocation+"\\pin-red.png"));
					spot.setColor("red");
					spot.getRemoveBtnItem().setEnabled(true);
				}
			spot.getLabelSpot().repaint();
			spot.getLabelSpot().revalidate();
			
			MappingPanel.getSpotsMap().revalidate();
			MappingPanel.getSpotsMap().validate();
			MappingPanel.getSpotsMap().repaint();
			MappingPanel.getSpotsMap().revalidate();
    	}
	}
	public void loadMapSpots(MappingPanel MappingPanel){
		int id_work_space=MappingPanel.getWorkSpace().getId_work_space();
		Response response;
		try {
			MappingPanel.getWorkSpace().setInitialized(true);
			response = sendRequestToServer("select-Spot.json","{\"id_work_space\": \""+id_work_space+"\"}");
			String responseBody=response.getResponseBody().substring(response.getResponseBody().indexOf("["),
	                response.getResponseBody().indexOf("]")+1);
			ObjectMapper mapper=new ObjectMapper();
			
			ArrayList<Spot> allSpots=mapper.readValue(responseBody,
					 new TypeReference<ArrayList<Spot>>(){});
			if(MappingPanel.getWorkSpace().getSpace_type().equals("open space")) {
				MappingPanel.setSpotsMap(new SpotsMapBackground(RequestsFileLocation+"\\openspace.jpg"));	
			}
			else if(MappingPanel.getWorkSpace().getSpace_type().equals("bureau")) {
				MappingPanel.setSpotsMap(new SpotsMapBackground(RequestsFileLocation+"\\bureau.jpg"));
			}
			else if(MappingPanel.getWorkSpace().getSpace_type().equals("salle de conference")) {
				MappingPanel.setSpotsMap(new SpotsMapBackground(RequestsFileLocation+"\\salle_de_conférence.jpg"));
			}
			else if(MappingPanel.getWorkSpace().getSpace_type().equals("salle de reunion")) {
				MappingPanel.setSpotsMap(new SpotsMapBackground(RequestsFileLocation+"\\salle_de_réunion.jpg"));
			}
			MappingPanel.getSpotsMap().setBounds(10, 30, 888, 508);
			MappingPanel.getSpotsMap().setLayout(null);
			MappingPanel.getMappingSpotsPanel().add(MappingPanel.getSpotsMap());
			MappingPanel.getSpotsMap().revalidate();
			MappingPanel.getWorkSpace().setSpots(allSpots);	
			MappingPanel.getSpotsMap().invalidate();
			MappingPanel.getSpotsMap().revalidate();
			MappingPanel.getSpotsMap().repaint();
			for(Spot spot: allSpots) {
				for(Equipment equipment:MappingPanel.getWorkSpace().getEquipmentsToInstall()) {
					if(equipment.getId_equipment()==spot.getId_equipment()) {
						spot.setState(equipment.isState());
						spot.setEquipmentInstalled(equipment);
					}
				}
				
				MappingPanel.getSpotsMap().revalidate();
				MappingPanel.getSpotsMap().repaint();
				JLabel labelSpot=new JLabel();
				spot.setLabelSpot(labelSpot);
				spot.getPlaceBtnItem().setEnabled(false);
				spot.getPlaceBtnItem().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
					  try {
						  int id_equipment=((Equipment)MappingPanel.getEquipmentsToInstallBox().getSelectedItem()).getId_equipment();
						Response response=sendRequestToServer("update-Spot.json","{\"id_equipment\": \""+id_equipment+"\", \"id_spot\": \""
						  		+spot.getId_spot()+ "\", \"taken\": \""+true+"\"}");
						response=sendRequestToServer("update-MaterialNeeds.json","{\"id_equipment\": \""+id_equipment
						+"\", \"installed\": \""+true+ "\", \"state\": \""+true+"\"}");
						spot.setColor("green");	
						spot.setTaken(true);
						spot.setState(true);
						spot.setEquipmentInstalled((Equipment)MappingPanel.getEquipmentsToInstallBox().getSelectedItem());
						loadEquipmentsToInstall(MappingPanel.getWorkSpace().getId_work_space());
						updateSpotMap(MappingPanel,-1);
						
						if(MappingPanel.getCurrentp()==2) {
							MappingPanel.getMapEquipmentsBtn().doClick();
						}
						else if(MappingPanel.getCurrentp()==3) {
							MappingPanel.getMapSensorsBtn().doClick();
						}
						
					} catch (InterruptedException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					   }
					}	
				});
				spot.getRemoveBtnItem().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						 try {
							  int id_equipment=spot.getId_equipment();
							Response response=sendRequestToServer("update-Spot.json","{\"id_equipment\": \""+null+"\", \"id_spot\": \""
							  		+spot.getId_spot()+ "\", \"taken\": \""+false+"\"}");
							response=sendRequestToServer("update-MaterialNeeds.json","{\"id_equipment\": \""+id_equipment
							+"\", \"installed\": \""+false+ "\", \"state\": \""+true+"\"}");
							spot.setColor("blue");	
							spot.setTaken(false);
							spot.setState(true);
							spot.setEquipmentInstalled(null);
							loadEquipmentsToInstall(MappingPanel.getWorkSpace().getId_work_space());
							
							updateSpotMap(MappingPanel,-1);
							if(MappingPanel.getCurrentp()==2) {
								MappingPanel.getMapEquipmentsBtn().doClick();
							}
							else if(MappingPanel.getCurrentp()==3) {
								MappingPanel.getMapSensorsBtn().doClick();
							}
							
						} catch (InterruptedException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						   }
						}	
					});
				spot.getPopUpMenu().add(spot.getPlaceBtnItem());
				spot.getPopUpMenu().add(spot.getRemoveBtnItem());
				spot.getLabelSpot().setComponentPopupMenu(spot.getPopUpMenu());
				spot.getLabelSpot().setBounds(spot.getPosition_x(),spot.getPosition_y(),32, 41);
				 
				try{
					if(!spot.isTaken()) {
						BufferedImage	myPicture = ImageIO.read(new File(RequestsFileLocation+"\\pin-blue.png"));
				spot.getLabelSpot().setIcon(new ImageIcon(myPicture));
				spot.setColor("blue");
				spot.getRemoveBtnItem().setEnabled(false);
				}
				else if((spot.isTaken())&&(spot.isState())) {
					BufferedImage	myPicture = ImageIO.read(new File(RequestsFileLocation+"\\pin-green.png"));
					spot.getLabelSpot().setIcon(new ImageIcon(myPicture));
					spot.setColor("green");
					spot.getRemoveBtnItem().setEnabled(true);
				}
				else if((spot.isTaken())&&((!spot.isState()))){
					BufferedImage	myPicture = ImageIO.read(new File(RequestsFileLocation+"\\pin-red.png"));
					spot.getLabelSpot().setIcon(new ImageIcon(myPicture));
					spot.setColor("red");
					spot.getRemoveBtnItem().setEnabled(true);
				}
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				MappingPanel.getSpotsMap().add(spot.getLabelSpot());
				MappingPanel.getSpotsMap().revalidate();
				MappingPanel.getSpotsMap().validate();
				spot.getLabelSpot().setToolTipText("<html><div>id: "+spot.getId_spot()+"</div> installé:"+spot.getEquipmentInstalled()+"</html>");
				spot.getLabelSpot().addMouseListener(new MouseAdapter() {
					public void mouseEntered(MouseEvent e) {
						
						spot.getLabelSpot().setIcon(new ImageIcon(RequestsFileLocation+"\\pin-white.png"));
					}
					public void mouseExited(MouseEvent e) {
						if(!spot.getColor().equals("orange")) {
						spot.getLabelSpot().setIcon(new ImageIcon(RequestsFileLocation+"\\pin-"+spot.getColor()+".png"));
						}else {
							spot.getLabelSpot().setIcon(new ImageIcon(RequestsFileLocation+"\\pin-"+spot.getColor()+".gif"));
						}
					}
					public void mouseClicked(MouseEvent e) {
						spot.getPopUpMenu().show(MappingPanel.getSpotsMap(), spot.getPosition_x(), spot.getPosition_y()+40);
					}
				});
				MappingPanel.getSpotsMap().revalidate();
				
			}
			
			
			
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void loadMappingButtons() {
		MappingPanel.getMapEquipmentsBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MappingPanel.getEquipmentsToInstallBox().revalidate();
				MappingPanel.getEquipmentsToInstallBox().removeAllItems();
				for(Equipment equipment: MappingPanel.getWorkSpace().getEquipmentsToInstall()) {
					if(equipment.getEquipment_type().equals("connected object")&&(!equipment.isInstalled())) {
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

				for(Equipment equipment: MappingPanel.getWorkSpace().getEquipmentsToInstall()) {
					if((equipment.getEquipment_type().equals("sensor")||(equipment.getEquipment_type().equals("sensorWindows")))&&(!equipment.isInstalled())) {
						MappingPanel.getEquipmentsToInstallBox().addItem(equipment);
						
					}
				}
				MappingPanel.showMappingPanel(2);
			}
			
		});
		 
		MappingPanel.getOkEquipmentButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				Equipment equipmentChoosed=(Equipment)MappingPanel.getEquipmentsToInstallBox().getSelectedItem();
				if(equipmentChoosed!=null) {
				ArrayList<Spot> allSpots=MappingPanel.getWorkSpace().getSpots();
				
				for(Spot spot: allSpots) {
					if(spot.getSpot_type().equals(equipmentChoosed.getEquipment_spot_type())) {
						spot.getLabelSpot().invalidate();
						spot.getLabelSpot().revalidate();
						spot.getLabelSpot().setIcon(new ImageIcon(RequestsFileLocation+"\\pin-orange.gif"));
						spot.setColor("orange");
						spot.getPopUpMenu().revalidate();
						spot.getPlaceBtnItem().setEnabled(true);
						MappingPanel.getCancelButton().setEnabled(true);
 	
					}
				}
				}
			}
		});
		MappingPanel.getCancelButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					updateSpotMap(mdl.getMp(),-1);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		MappingPanel.getReturnButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MappingPanel.getCancelButton().doClick();
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
						equipment.setInstalled(Boolean.parseBoolean(equipmentToInstallMap.get("installed")));
						equipment.setState(Boolean.parseBoolean(equipmentToInstallMap.get("state")));

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
	public void loadAdvancedFiltre() {
		
		ActionListener advancedFiltreListenner=new ActionListener () { 		
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					
                    AdvancedFiltrePanel advancedFiltrePanel =new AdvancedFiltrePanel();
					int floorNumber=LoanPanel.getFloorBox().getSelectedIndex()+1;
					Building building=(Building) LoanPanel.getBuildingBox().getSelectedItem();
					AdvancedFiltrePanel.setFloorNum(floorNumber);
					AdvancedFiltrePanel.setBuilding(building);
					FunctionalitiesBarAndPanel.getMyFunctionalities().show(FunctionalitiesBarAndPanel.getFunctionalitiesPanel(),"Filter");
					AdvancedFiltrePanel.show();
					
					
					
				}			
		};
		LoanPanel.getBtnAdvancedFilter().addActionListener(advancedFiltreListenner);
		}
		
	public void loadReturnButton() {
		
ActionListener returnButtonActionListener =new ActionListener() {
            
			@Override
			public void actionPerformed(ActionEvent e) {
				FunctionalitiesBarAndPanel.getMyFunctionalities().show(FunctionalitiesBarAndPanel.getFunctionalitiesPanel(),"Accueil");	
			}
			
		};
		
		AdvancedFiltrePanel.getBtnReturn().addActionListener(returnButtonActionListener);
	}
		
	public void loadConfigurateWindows() {
		ActionListener configurerActionListener =new ActionListener() {	
			public void actionPerformed(ActionEvent e) {

			if(AdvancedFiltrePanel.getBtnFilterWindow().isSelected()) {
				
				try {
					
				    ConfigurateWindowsPanel configurateWindowsPanel = new ConfigurateWindowsPanel ();
					int floorNumber=LoanPanel.getFloorBox().getSelectedIndex()+1;
					Building building=(Building) LoanPanel.getBuildingBox().getSelectedItem();	
					
					 
				    //***load all mapped and taken workspaces of  the floor number floorNumber of the Building building 
					//Response workspaceResponse= sendRequestToServer("select-WorkSpaces.json","{\"id_building\": \""+building.getId_building()+"\", \"space_floor\": \""+floorNumber+ "\", \"taken\": \""+true+"\", \"configurable\": \""+false+"\"}");
					Response workspaceResponse= sendRequestToServer("select-WorkSpaces.json",null);
					String workspaceResponseBody=workspaceResponse.getResponseBody().substring(workspaceResponse.getResponseBody().indexOf("["),
							workspaceResponse.getResponseBody().indexOf("]")+1);
					ObjectMapper takenmapper=new ObjectMapper();
					ArrayList<WorkSpace> takenWorkSpaces=takenmapper.readValue(workspaceResponseBody,
							 new TypeReference<ArrayList<WorkSpace>>(){});					
					takenWorkSpaces.removeIf(w -> w.getNumber_of_windows()==0);
					
					
				    //***load all smartwindows of the Building building and the floor numbre floorNumber
					Response winResponse= sendRequestToServer("select-wind-to-configure.json",null);					
					String winResponseBody=winResponse.getResponseBody().substring(winResponse.getResponseBody().indexOf("["),
							winResponse.getResponseBody().indexOf("]")+1);
					ObjectMapper allWinMapper=new ObjectMapper();
					ArrayList<SmartWindow> allSmartWin=allWinMapper.readValue(winResponseBody,new TypeReference<ArrayList<SmartWindow>>(){});
					
					ArrayList<SmartWindow> winToCfgArrayList = new ArrayList<SmartWindow>();
					for (SmartWindow sw:allSmartWin) {
						if (takenWorkSpaces.stream().anyMatch(ws -> ws.getId_work_space()==sw.getId_work_space() )) {
							
							 winToCfgArrayList.add(sw);
						}
					}
					for (SmartWindow sw:winToCfgArrayList)  {ConfigurateWindowsPanel.getWinToCnfgmodel().addElement(sw);}	
					FunctionalitiesBarAndPanel.getMyFunctionalities().show(FunctionalitiesBarAndPanel.getFunctionalitiesPanel(),"Configurate");	
				}catch (InterruptedException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				   }
			}}}; 
			AdvancedFiltrePanel.getBtnOk().addActionListener(configurerActionListener);
	}
	public void loadReturn ()
	{
		ActionListener returnActionListener  =new ActionListener() {	
	
		
		public void actionPerformed(ActionEvent e) {
		
		FunctionalitiesBarAndPanel.getMyFunctionalities().show(FunctionalitiesBarAndPanel.getFunctionalitiesPanel(),"Filter");	
	}
		};
		
	    ConfigurateWindowsPanel.getRetourBtn().addActionListener(returnActionListener);
	
	}
	public void loadConfigurate() {
		
		ActionListener confActionListener  =new ActionListener() {	
			public void actionPerformed(ActionEvent e) {	
				 new FrameToConfigurate();
		
		}
	};
	ConfigurateWindowsPanel.getConfigureBtn().addActionListener(confActionListener);
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
