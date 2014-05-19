package zp4jv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	private static BufferedWriter wr;
	private static BufferedReader rd;

	public static void main(String[] args) {
		
		try {
			System.out.println("Starting MarkdownWebServer");
			ServerSocket srvSocket = new ServerSocket(Config.PORT);
			
			boolean stopServer = false;
			while (!stopServer) {
				// Open I/O
				Socket clientSocket = srvSocket.accept();
				wr = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
				rd = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	
				// Respond
				respond(parseRequest(rd.readLine()));
				
				// Close I/O
				wr.flush();
				wr.close();
				rd.close();
				clientSocket.close();
			}
	
			System.out.println("Shutting down");
			srvSocket.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (MarkdownException e) {
			e.printStackTrace();
		}
	}
	
	public static void respond(String[] request) throws IOException, MarkdownException {
		// @TODO Pokud je adresář, vypsat obsah, jinak vv
		Markdown m = Markdown.fromFile(Config.DOCUMENT_ROOT + request[1]);
		Response r = new Response(StatusCode.OK, m.toHtml());
		wr.write(r.toString());
	}

	public static String[] parseRequest(String request) throws IOException {
		return request.split(" ");
	}
}
