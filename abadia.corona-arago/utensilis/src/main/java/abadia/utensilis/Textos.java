package abadia.utensilis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Textos {
	
	public static String llegir(String ruta) throws Throwable {
		String text = null;
		BufferedReader br = new BufferedReader(new FileReader(ruta));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    text = sb.toString();
		} finally {
		    br.close();
		}
		return text;
	}
	public static void escriure(String ruta, String text) {
	    BufferedWriter writer;
	    try {
			writer = new BufferedWriter(new FileWriter(ruta));
		    writer.write(text);
		    writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}