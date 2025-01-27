package catalunya.formatador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Bdlex {

	public static void main(String[] args) {
		try {
			String diec = Bdlex.llegeix("/home/joan/git/abadia/catalunya.abadia/llenguatge/català/diec1.arrel.txt");
			System.out.println(diec);
			Bdlex.escriu("/home/joan/git/abadia/catalunya.abadia/llenguatge/català/diec1.arrel.prova.txt", diec);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public static String llegeix(String ruta) throws Throwable {
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
	
	public static void escriu(String ruta, String text) {
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