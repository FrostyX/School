package zp4jv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class ShareClient {
	
	public static final String host = "127.0.0.1";
	public static final int port = 4242;
	public static final String prompt = "you@" + host + " -> ";

	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Socket socket = new Socket(host, port);

		InputStream inpStream = socket.getInputStream();
		OutputStream outStream = socket.getOutputStream();
		
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(outStream));
		BufferedReader rd = new BufferedReader(new InputStreamReader(inpStream));
		
		Scanner cin = new Scanner(System.in);
		
		while(true) {
			
			// Přečtení příkazu z konzole
			System.out.print(prompt);
			String line = cin.nextLine();

			// Zaslání příkazu na server
			wr.write(line + "\n");
			wr.flush();
			
			// Vypsání odpovědi serveru
			String output = null;
			while ((output = rd.readLine()) != null) {
				
				System.out.println(output);
				if (!rd.ready())
					break;
			}

			if(line.equals("quit")) {
				break;
			}
		}

		cin.close();
		inpStream.close();
		outStream.close();
		socket.close();
		
		System.out.println("Bye :-)");
	}
}
