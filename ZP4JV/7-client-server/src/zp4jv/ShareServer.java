package zp4jv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ShareServer {

	public static final int PORT = 4242;
	public static String directory = "./data/";

	private static BufferedWriter wr;
	private static BufferedReader rd;
	private static boolean stopServer = false;
	private static boolean stopClient = false;
	
	public static void main(String[] args) throws IOException {
		
		ServerSocket srvSocket = new ServerSocket(PORT);
		
		while (!stopServer) {
			System.out.println("Waiting for a client");
			Socket clientSocket = srvSocket.accept();
			
			wr = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			rd = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			while (!stopClient) {
				String line = rd.readLine();
				
				if(line == null)
					continue;
				
				respond(parseCmd(line));
				wr.flush();
			}

			stopClient = false;
			wr.close();
			rd.close();
			clientSocket.close();
		}

		System.out.println("Shutting down");
		srvSocket.close();
	}
	
	private static void respond(String[] cmd) throws IOException {
		if(cmd == null)
			return;

		switch(cmd[0]) {
			case "quit": {
				stopClient = true; 
				break;
			}

			case "stop": { 
				stopClient = true; 
				stopServer = true; 
				break; 
			}

			case "dir": {
				StringBuilder sb = new StringBuilder();
				
				File dir = new File(directory);
				for (File file : dir.listFiles()) {
					if (file.isFile()) {
						sb.append(file.getName() + "\n");
					}
				}
				
				wr.write(sb.toString());
				break;
			}

			case "get": {
				try {
					wr.write(fileContent(directory + cmd[1]));
				}
				catch(Exception e) {
					wr.write("Missing argument for `get`\n");
				}
				break;
			}
			
			default: {
				wr.write(cmd[0] + ": Command not found\n");
			}
		}
		
	}
	
	private static String[] parseCmd(String cmd) {
		return cmd.split(" ");
	}
	
	private static String fileContent(String path) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new FileReader(path));

		String line = null;
		while ((line = br.readLine()) != null) {
			sb.append(line + "\n");
		}

		br.close();
		return sb.toString();
	}
}
