package zp4jv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class VHostThread extends Thread {
	
	private VHostConfig config;
	
	public VHostThread(VHostConfig config) {
		this.config = config;
	}

	public void run() {
		try {
			ServerSocket srvSocket = new ServerSocket(config.getPort());
			
			boolean stopServer = false;
			while (!stopServer) {
				// Open I/O
				Socket clientSocket = srvSocket.accept();
				BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
				BufferedReader rd = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	
				// Respond
				String line = rd.readLine();
				if(line == null) continue;
				respond(parseRequest(line), wr);
				
				// Close I/O
				wr.flush();
				wr.close();
				rd.close();
				clientSocket.close();
			}
	
			System.out.println("Shutting down");
			srvSocket.close();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
    }

	private void respond(String[] request, BufferedWriter wr) throws IOException, MarkdownException {
		String path = config.getDocumentRoot() + request[1];
		File file = new File(path);
		if(!file.exists()) {
			Response r = new Response(StatusCode.NotFound, "Page not found");
			wr.write(r.toString());
		}
		else if(file.isDirectory()) {
			StringBuilder sb = new StringBuilder();
			for (File f : file.listFiles()) {
				String a = String.format("<a href=\"%1$s\" title=\"%2$s\">%2$s</a><br />", request[1] + f.getName(), f.getName());
				sb.append(a);
			}
			Response r = new Response(StatusCode.OK, sb.toString());
			wr.write(r.toString());
		}
		else {
			Markdown m = Markdown.fromFile(path);
			Response r = new Response(StatusCode.OK, m.toHtml());
			wr.write(r.toString());
		}
	}

	private String[] parseRequest(String request) throws IOException {
		String[] parsed = request.split(" ");
		int l = parsed[1].length();
		if(parsed[1].charAt(l - 1) != '/') {
			parsed[1] += '/';
		}
		return parsed;
	}
}
