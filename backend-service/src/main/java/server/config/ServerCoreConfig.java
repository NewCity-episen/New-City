package server.config;

public class ServerCoreConfig {
	
	private int listenPort;
	private int soTimeOut;
	
	public ServerCoreConfig() {
		
	}
	
	public int getListenPort() {
		return listenPort;
	}
	public void setListenPort(int listenPort) {
		this.listenPort=listenPort;
	}
	public int getSoTimeOut() {
		return soTimeOut;
	}
	public void setSoTimeOut(int soTimeOut) {
		this.soTimeOut=soTimeOut;
	}
	public String toString() {
		return "ServerCoreConfig{"+"listenPort="+listenPort+", soTimeOut="+soTimeOut+"}";
	}
}
