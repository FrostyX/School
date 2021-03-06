package zp4jv;

public class Response {
	
	private StatusCode status;
	private String content;
	private String contentType;

	public Response(StatusCode status, String content) {
		this.status = status;
		this.content = content;
		this.contentType = "text/html";
		
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("HTTP/1.1 {0} {1}\n", status.getCode(), status.getTitle()));
		sb.append("Server: MarkdownWebServer\n");
		sb.append("Connection: close\n");
		sb.append("Content-Type: " + contentType + "\n");
		sb.append("\n");
		sb.append(content);
		sb.append("\n");
		return sb.toString();
	}
}
