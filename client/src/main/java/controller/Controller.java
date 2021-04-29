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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

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
		filterLoad();
		
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
				 LoanPanel.getFloorBoxFilter().addItem("Étage "+i); 
			 }

			 LoanPanel.getBuildingBoxFilter().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
		                for(int i=0;i<LoanPanel.getBuildingBoxFilter().getItemCount();i++) {
		                	if((LoanPanel.getBuildingBoxFilter().getSelectedIndex()==i)) {
		                		LoanPanel.getFloorBoxFilter().removeAllItems();
		                		for(int j=1;j<=Integer.valueOf(((Building)LoanPanel.getBuildingBoxFilter().getSelectedItem()).getNb_of_floor());j++) {
		                			LoanPanel.getFloorBoxFilter().addItem("Étage "+j);
		                		}
		                	}
		                }
					} 
				 });
			 //
			/* LoanPanel.getFilterButton().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// write here the location search algorithm
		               
					} 
				 });*/

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
				MappingPanel mappingPanel=new MappingPanel();
				loadEquipments();
				loadWorkSpaces();
				loadEquipmentsToInstall(workSpace.getId_work_space());
				MappingPanel.setWorkSpace(workSpace);
				if(!MappingPanel.isInitialized()) {
				loadMapSpots();
				}else {
					updateSpotMap();
				}
				FunctionalitiesBarAndPanel.getMyFunctionalities().show(FunctionalitiesBarAndPanel.getFunctionalitiesPanel(), "Mapping");
				MappingPanel.showChoiceOfMappingPanel();
			}
		});
		
	}
	public void updateSpotMap() {
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
		for(Spot spot: MappingPanel.getWorkSpace().getSpots()) {
			for(Equipment equipment:MappingPanel.getWorkSpace().getEquipmentsToInstall()) {
				if(equipment.getId_equipment()==spot.getId_equipment()) {
					spot.setState(equipment.isState());
				}
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
			MappingPanel.getSpotsMap().revalidate();
			MappingPanel.getSpotsMap().validate();
			MappingPanel.getSpotsMap().repaint();
			
	}
	}
	public void loadMapSpots(){
		int id_work_space=MappingPanel.getWorkSpace().getId_work_space();
		Response response;
		try {
			MappingPanel.setInitialized(true);
			response = sendRequestToServer("select-Spot.json","{\"id_work_space\": \""+id_work_space+"\"}");
			String responseBody=response.getResponseBody().substring(response.getResponseBody().indexOf("["),
	                response.getResponseBody().indexOf("]")+1);
			ObjectMapper mapper=new ObjectMapper();
			ArrayList<Spot> allSpots=mapper.readValue(responseBody,
					 new TypeReference<ArrayList<Spot>>(){});
			MappingPanel.getWorkSpace().setSpots(allSpots);
			MappingPanel.getSpotsMap().removeAll();
			MappingPanel.getSpotsMap().revalidate();
			MappingPanel.getSpotsMap().validate();
			MappingPanel.getSpotsMap().repaint();
			MappingPanel.getJPanel().revalidate();
			MappingPanel.getJPanel().validate();
			MappingPanel.getJPanel().repaint();
			MappingPanel.getSpotsMap().setLayout(null);
			for(Spot spot: allSpots) {
				for(Equipment equipment:MappingPanel.getWorkSpace().getEquipmentsToInstall()) {
					if(equipment.getId_equipment()==spot.getId_equipment()) {
						spot.setState(equipment.isState());
						spot.setEquipmentInstalled(equipment);
					}
				}
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
						updateSpotMap();
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
							updateSpotMap();
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
			JLabel spotsMapBackground=new JLabel();
			spotsMapBackground.setBounds(0, 0, 888, 508);
			if(MappingPanel.getWorkSpace().getSpace_type().equals("open space")) {
				spotsMapBackground.setIcon(new ImageIcon(RequestsFileLocation+"\\openspace.jpg"));
				
			}
			else if(MappingPanel.getWorkSpace().getSpace_type().equals("bureau")) {
				spotsMapBackground.setIcon(new ImageIcon(RequestsFileLocation+"\\bureau.jpg"));
			}
			else if(MappingPanel.getWorkSpace().getSpace_type().equals("salle de conference")) {
				spotsMapBackground.setIcon(new ImageIcon(RequestsFileLocation+"\\salle_de_conférence.jpg"));
			}
			else if(MappingPanel.getWorkSpace().getSpace_type().equals("salle de reunion")) {
				spotsMapBackground.setIcon(new ImageIcon(RequestsFileLocation+"\\salle_de_réunion.jpg"));
			}
			MappingPanel.getSpotsMap().add(spotsMapBackground);
			MappingPanel.getSpotsMap().revalidate();
			
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
				MappingPanel.getMappingSpotsPanel().validate();
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
				
				ArrayList<Spot> allSpots=MappingPanel.getWorkSpace().getSpots();
	
				for(Spot spot: allSpots) {
					if(spot.getSpot_type().equals(equipmentChoosed.getEquipment_spot_type())) {
						spot.getLabelSpot().setIcon(new ImageIcon(RequestsFileLocation+"\\pin-orange.gif"));
						spot.setColor("orange");
						spot.getPopUpMenu().revalidate();
						spot.getPlaceBtnItem().setEnabled(true);
						MappingPanel.getCancelButton().setEnabled(true);
 	
					}
				}
			}
		});
		MappingPanel.getCancelButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateSpotMap();
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
