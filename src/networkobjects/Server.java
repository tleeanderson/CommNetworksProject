package networkobjects;

public class Server {
	
	private String ipAddress;

	public Server(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
}
