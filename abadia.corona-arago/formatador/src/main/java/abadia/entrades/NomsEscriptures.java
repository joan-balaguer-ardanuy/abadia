package abadia.entrades;

import abadia.Formatador;
import abadia.utensilis.Textos;

public class NomsEscriptures extends Formatador {

	private static String SEPARADOR = " = ";
	private static String EOL = ";\n";
	
	public NomsEscriptures(String entrada) {
		super(entrada);
		llegir();
	}

	@Override
	public void llegir() {
		String[] entrades = retallarEntrades(EOL);
		for(String entrada : entrades) {
			if(!entrada.isEmpty()) {
				System.out.println(entrada);
				String clau = entrada.substring(0, entrada.indexOf(SEPARADOR)).trim();
				String valor = entrada.substring(
						entrada.indexOf(SEPARADOR) + SEPARADOR.length(),
						entrada.length()-1).trim();
				
				put(clau, valor);
			}
		}
	}
	public static void main(String[] args) {
		try {
			// Extracció de Tars
			String entrada = Textos.llegir("/home/joan/git/abadia/abadia.corona-arago/noms/noms.temp.txt");
			
			NomsEscriptures tars = new NomsEscriptures(entrada);
			tars.escriure();
			
			Textos.escriure("/home/joan/git/abadia/abadia.corona-arago/noms/noms.testament.xml", tars.extreureDocument());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}