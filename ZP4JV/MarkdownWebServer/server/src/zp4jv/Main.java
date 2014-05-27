package zp4jv;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) {
		
		try {

			Config config = new Config(Config.DEFAULT_PATH);
			if(args.length > 0) {
				config.setPath(args[0]);
			}

			System.out.println("Starting MarkdownWebServer");

			for(VHostConfig vhost : config.getVHosts()) {
				System.out.println("Starting VHost: " + vhost.getName());
				(new VHostThread(vhost)).start();
			}

		}
		catch(RuntimeException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("[ERROR] Config not found");
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
}
