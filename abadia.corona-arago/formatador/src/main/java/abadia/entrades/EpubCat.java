package abadia.entrades;

import abadia.Datagrama;
import abadia.Formatador;
import abadia.utensilis.Textos;

import java.util.LinkedHashMap;
import java.util.Map;

public class EpubCat extends Formatador {
	
	Map<String,Integer> estructura;
	
	public static void main(String[] args) {
		try {
			Map<String, Integer> estructura = ElSenyorDelsAnells();
			String entrada = Textos.llegir("/home/joan/...");
			EpubCat epub = new EpubCat(entrada, estructura);
			Textos.escriure("/home/joan/...", epub.extreureDocument());
		} catch (Throwable e) {
			e.printStackTrace();
		}
//		// Ken Follet - Un món sense fi
//		estructura.put(SECCIÓ_INTRODUCCIÓ, 1);
//		estructura.put(SECCIÓ_AUTOR, 1);
//		estructura.put(SECCIÓ_PORTADA, 1);
//		estructura.put(SECCIÓ_CONTRAPORTADA, 1);
//		estructura.put(SECCIÓ_PRÒLEG, 1);
//		estructura.put(SECCIÓ_VOLUM, 1);
//		estructura.put(SECCIÓ_CAPÍTOLS, 91);
//		estructura.put(SECCIÓ_EPÍLEG, 1);
//		estructura.put(SECCIÓ_NOTES, 1);
	}

	public EpubCat(String entrada, Map<String,Integer> estructura) {
		super(entrada);
		// Reemplacem apòstrof '’' per '''
		escriureEntrada(llegirEntrada().replaceAll(
				String.valueOf(Datagrama.COMETA_DRETA),
				String.valueOf(Datagrama.APÒSTROF)));
		// Establim estructura del llibre
		this.estructura = estructura;
		// Inicia lectura.
		llegir();
	}

	@Override
	public void llegir() {
		String[] entrades = retallarEntrades(SEPARADOR_1);
		int i = 0;
		// Iniciem identificació i muntatge de seccions
		for(Map.Entry<String,Integer> secció : estructura.entrySet()) {
			for(int j = i + 1; j <= i + secció.getValue(); j++) {
				
				int clau = 0;
				String valor = entrades[j];
				
				switch (secció.getKey()) {
				case SECCIÓ_CAPÍTOLS:
				case SECCIÓ_PROEMI:
				case SECCIÓ_PRÒLEG:
					put(String.valueOf(clau), valor);
					break;
				default:
					put(secció.getKey(), valor);
					break;
				}
				i++;
			}
		}
	}
	public static Map<String, Integer> ElSenyorDelsAnells() {
		Map<String, Integer> estructura = new LinkedHashMap<String, Integer>();
		// J R R Tolkien - El Senyor dels Anells
		estructura.put(SECCIÓ_INTRODUCCIÓ, 1);
		estructura.put(SECCIÓ_AUTOR, 1);
		estructura.put(SECCIÓ_PORTADA, 1);
		estructura.put(SECCIÓ_EDITORIAL, 1);
		
		// LA GERMANDAT DE L’ANELL
		estructura.put(SECCIÓ_VOLUM, 1);
		// PROEMI
		estructura.put(SECCIÓ_PROEMI, 2);
		// PRÒLEG
		estructura.put(SECCIÓ_PRÒLEG, 5);
		
		// LLIBRE PRIMER
		estructura.put(SECCIÓ_LLIBRE, 1);
		// "Capítol "
		estructura.put(SECCIÓ_CAPÍTOLS, 12);
		
		// LLIBRE SEGON
		estructura.put(SECCIÓ_LLIBRE, 1);
		// "Capítol "
		estructura.put(SECCIÓ_CAPÍTOLS, 10);
		
		// LES DUES TORRES
		estructura.put(SECCIÓ_VOLUM, 1);
		
		// LLIBRE TERCER
		estructura.put(SECCIÓ_LLIBRE, 1);
		// "Capítol "
		estructura.put(SECCIÓ_CAPÍTOLS, 11);
		
		// LLIBRE QUART
		estructura.put(SECCIÓ_LLIBRE, 1);
		// "Capítol "
		estructura.put(SECCIÓ_CAPÍTOLS, 10);
		
		// EL RETORN DEL REI
		estructura.put(SECCIÓ_VOLUM, 1);
		
		// LLIBRE CINQUÈ
		estructura.put(SECCIÓ_LLIBRE, 1);
		// "Capítol "
		estructura.put(SECCIÓ_CAPÍTOLS, 10);
		
		// LLIBRE SISÈ
		estructura.put(SECCIÓ_LLIBRE, 1);
		// "Capítol "
		estructura.put(SECCIÓ_CAPÍTOLS, 9);
		
		// A partir de "JOHN RONALD REULEN TOLKIEN... "
		estructura.put(SECCIÓ_EPÍLEG, 1);
		// Notes
		estructura.put(SECCIÓ_NOTES, 1);
		
		return estructura;
	}
	
	public static final String SEPARADOR_1 = "\n\n\n\n\n\n";
	
	public static final String SEPARADOR_2 = "\n\n\n";
	
	public static final String SEPARADOR_3 = "\n\n";
	
	public static final String SECCIÓ_INTRODUCCIÓ = "Introducció";

	public static final String SECCIÓ_AUTOR = "Autor";
	
	public static final String SECCIÓ_PORTADA = "Portada";
	
	public static final String SECCIÓ_EDITORIAL = "Contraportada";
	
	public static final String SECCIÓ_DEDICATÒRIA = "Dedicatòria";
	
	public static final String SECCIÓ_PROEMI = "Proemi";
	
	public static final String SECCIÓ_PRÒLEG = "Pròleg";
	
	public static final String SECCIÓ_CAPÍTOLS = "Capítol";

	public static final String SECCIÓ_VOLUM = "Volum";

	public static final String SECCIÓ_LLIBRE = "Llibre";
	
	public static final String SECCIÓ_EPÍLEG = "Epíleg";
	
	public static final String SECCIÓ_NOTES = "Notes";
}