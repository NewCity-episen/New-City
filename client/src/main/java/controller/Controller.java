package controller;
import view.*;
import model.*;
import shared.code.Request;
import shared.code.Response;
import shared.code.StudentConfig;

import java.awt.Color;
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

import javax.swing.JButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

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
		loadButtons();
		RequestsFileLocation= System.getenv(ConfigEnVar);
		
	}

	public void loadButtons() {
		quitButtonLoad();
		loanButtonLoad();
		mappingButtonLoad();
		configurateWindowsButtonLoad();
		okButtonLoad();
	}
	public void quitButtonLoad() {
		ActionListener quitButtonListener=new ActionListener() {
       
			@Override
			public void actionPerformed(ActionEvent e) {
				vw.getMyCardPanels().show(vw.getLayeredPanel(), "Pane1");
			}
			
		};
		mouseCursorOnButton(FunctionalitiesBarAndPanel.getQuitButton());
		FunctionalitiesBarAndPanel.getQuitButton().setFocusPainted(false);
		FunctionalitiesBarAndPanel.getQuitButton().addActionListener(quitButtonListener);
	}
	
	public void loanButtonLoad() {
		ActionListener loanButtonListener=new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FunctionalitiesBarAndPanel.getMyFunctionalities().show(FunctionalitiesBarAndPanel.getFunctionalitiesPanel(),"Réservation");	
			}
			
		};
		focusButtons(FunctionalitiesBarAndPanel.getLoanButton());
		mouseCursorOnButton(FunctionalitiesBarAndPanel.getLoanButton());
		FunctionalitiesBarAndPanel.getLoanButton().setFocusPainted(false);
		FunctionalitiesBarAndPanel.getLoanButton().addActionListener(loanButtonListener);
	}
	public void mappingButtonLoad() {
		ActionListener mappingButtonListener=new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FunctionalitiesBarAndPanel.getMyFunctionalities().show(FunctionalitiesBarAndPanel.getFunctionalitiesPanel(),"Mapping");	
			}
			
		};
		focusButtons(FunctionalitiesBarAndPanel.getMappingButton());
		mouseCursorOnButton(FunctionalitiesBarAndPanel.getMappingButton());
		FunctionalitiesBarAndPanel.getMappingButton().setFocusPainted(false);
		FunctionalitiesBarAndPanel.getMappingButton().addActionListener(mappingButtonListener);
	}
	public void configurateWindowsButtonLoad() {
		ActionListener mappingButtonListener=new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FunctionalitiesBarAndPanel.getMyFunctionalities().show(FunctionalitiesBarAndPanel.getFunctionalitiesPanel(),"Configurer fenêtres");	
			}		
		};
		FunctionalitiesBarAndPanel.getConfigurateWindowsButton().setFocusPainted(false);
		focusButtons(FunctionalitiesBarAndPanel.getConfigurateWindowsButton());
		mouseCursorOnButton(FunctionalitiesBarAndPanel.getConfigurateWindowsButton());
		FunctionalitiesBarAndPanel.getConfigurateWindowsButton().addActionListener(mappingButtonListener);
	}

	public void okButtonLoad() {
		ActionListener okButtonListener=new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FunctionalitiesBarAndPanel.setInformationLabel(mdl.getCompanyName(), mdl.getContact());
				vw.getMyCardPanels().show(vw.getLayeredPanel(), "functions");
			}
			
		};
		
		Panel1.getOkButton().addActionListener(okButtonListener);
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
				if(!button.isFocusOwner()) {
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
	
/******************Server Part: won't be used for now*********************************************************/
	public Socket connectToServer()  {
		try {
			
			clientconfig= new ClientConfig();
			InetAddress ip=InetAddress.getByName(clientconfig.getConfig().getServerIP());
			logger.info("Trying to connect to IP:{}",ip.getHostAddress());
			//InetAddress ip=InetAddress.getByName("localhost");
			return new Socket(ip , clientconfig.getConfig().getDestinationPort());//Connect to the server
		} catch (UnknownHostException e) {
			logger.info("Unknown host:");
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("No I/O");
		}
		return null;
		
	}
	public void sendRequestToServer(String query) throws InterruptedException, JsonParseException, JsonMappingException, IOException {
		
		Socket socketClient=connectToServer();
		if(socketClient==null) {
			logger.info("Client not connected to server.");
		}
		else {
			try {
                final ObjectMapper jsonMapper= new ObjectMapper();
                final ObjectMapper yamlMapper= new ObjectMapper(new YAMLFactory());
                Request rq = null;
        
                if(query.equals("select")) {
                	rq=jsonMapper.readValue(new File(RequestsFileLocation+"/select-request.json"), Request.class);
                }
                else if(query.equals("insert")) {
                	rq=jsonMapper.readValue(new File(RequestsFileLocation+"/insert-request.json"), Request.class);
                	StudentConfig students=yamlMapper.readValue(new File(RequestsFileLocation+"/students-to-be-inserted.yaml"), StudentConfig.class);
                	rq.setRequestContent(jsonMapper.writeValueAsString(students));
                	
                }
                else if(query.equals("delete")) {
                	rq=jsonMapper.readValue(new File(RequestsFileLocation+"/delete-request.json"), Request.class);
                	StudentConfig students=yamlMapper.readValue(new File(RequestsFileLocation+"/students-to-be-deleted.yaml"), StudentConfig.class);
                    rq.setRequestContent(jsonMapper.writeValueAsString(students));

             
                }
                else if(query.equals("update")) {
                	rq=jsonMapper.readValue(new File(RequestsFileLocation+"/update-request.json"), Request.class);
                	StudentConfig students=yamlMapper.readValue(new File(RequestsFileLocation+"/students-to-be-updated.yaml"), StudentConfig.class);
                	rq.setRequestContent(jsonMapper.writeValueAsString(students));
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
                out.close();
				inputStream.close();
				socketClient.close();
			} catch (IOException e) {
				logger.info("No I/O");
				e.printStackTrace();
			}
		}
	}

}
