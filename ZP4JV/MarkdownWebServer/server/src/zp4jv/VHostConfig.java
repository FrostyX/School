package zp4jv;

public class VHostConfig {
	private int port;
	private String name;
	private String documentRoot;

	public VHostConfig(int port, String name, String documentRoot) {
		this.port = port;
		this.name = name;
		this.documentRoot = documentRoot;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDocumentRoot() {
		return documentRoot;
	}
	
	public void setDocumentRoot(String documentRoot) {
		this.documentRoot = documentRoot;
	}
}
