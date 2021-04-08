package ClientConfig;

public class ClientCoreConfig {
	private String serverIP;
	private int destinationPort;
	

	
	public ClientCoreConfig() {

	}
	
	public int getDestinationPort() {
		return destinationPort;
	}
	public void setDestinationPort(int DestinationPort) {
		this.destinationPort=DestinationPort;
	}
	public String getServerIP() {
		return serverIP;
	}
    public void setServerIP(String ServerIP) {
    	this.serverIP=ServerIP;
    }
	public String toString() {
		return "ClientCoreConfig{"+"ServerIP="+serverIP+",DestinationPort="+destinationPort+"}";
	}
}
