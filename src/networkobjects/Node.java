package networkobjects;

public class Node {

	private Object data;
	private String ipAddress;
	private int nodeNumber;
	private String displayName = "node";
	
	public Node(int nodeNumber) {
		this.nodeNumber = nodeNumber;
		displayName += nodeNumber;
	}
	
	public Node(String ipAddress, Object data, int nodeNumber) {
		this.ipAddress = ipAddress;
		this.data = data;
		this.nodeNumber = nodeNumber;
	}
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public String getIpAdress() {
		return ipAddress;
	}

	public void setIpAdress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getNodeNumber() {
		return nodeNumber;
	}

	public void setNodeNumber(int nodeNumber) {
		this.nodeNumber = nodeNumber;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
