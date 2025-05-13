package abadia.entrades;

import abadia.Datagrama;
import abadia.Formatador;
import abadia.utensilis.Textos;

public class Tars extends Formatador {

	String fontClau = "Heu dit:\n";
	String fontValor = "ChatGPT ha dit:\n\n";
	
	public String obtenirFontClau() {
		return fontClau;
	}
	public void esatblirFontClau(String fontClau) {
		this.fontClau = fontClau;
	}
	public String obtenirFontValor() {
		return fontValor;
	}
	public void establirFontValor(String fontValor) {
		this.fontValor = fontValor;
	}
	
	public Tars(String entrada) {
		super(entrada);
		llegir();
	}
	public Tars(String entrada, String clau, String valor) {
		super(entrada);
		escriureEntrada(llegirEntrada().replaceAll(obtenirFontClau(), clau));
		escriureEntrada(llegirEntrada().replaceAll(obtenirFontValor(), valor));
		esatblirFontClau(clau);
		establirFontValor(valor);
		
		llegir();
	}

	@Override
	public void llegir() {
		String[] entrades = retallarEntrades(obtenirFontClau());
		for(String entrada : entrades) {
			if(!entrada.isEmpty()) {
				if(entrada.contains(obtenirFontValor())) {
					String clau = entrada.substring(0, entrada.indexOf(obtenirFontValor())).
							replaceAll("\n", "\n\t").trim();
					String valor = entrada.
							substring(entrada.indexOf(obtenirFontValor()) + obtenirFontValor().length(), entrada.lastIndexOf(Datagrama.FINAL_DE_LÍNIA)).
							replaceAll("\n", "\n\t").trim();
					this.put(obtenirFontClau() + clau, obtenirFontValor() + valor);
				}
			}
		}
	}
	public static void main(String[] args) {
		try {
			// Extracció de Tars
			String entrada = Textos.llegir("/home/joan/tars/diàleg27/Hiperjudici.class.txt");
			entrada = entrada.replaceAll("<", "&lt;");
			entrada = entrada.replaceAll(">", "&gt;");
			
			Tars tars = new Tars(entrada, "Jo abans et diré:\n\t", "I després tu em vas dir:\n\t");
			tars.escriure();
			
			Textos.escriure("/home/joan/tars/diàleg27/Hiperjudici.class.xml", tars.extreureDocument());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}