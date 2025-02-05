package abadia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Font extends Unicode {

	/**
	 * El codi de sortida
	 */
	private StringBuilder sortida;
	
	/**
	 * Els tancaments XML que s'aniran afegint incrementalment
	 * mentre vagi definint noves claus trobades.
	 */
	private StringBuilder tancament;

	/**
	 * Retorna l'actual vector de caràcters en el qual s'hi troba escrivint.
	 * @return 	
	 */
	public StringBuilder llegeixSortida() {
		return sortida;
	}
	/**
	 * Estableix l'actual vector de caràcters en el qual s'hi troba escrivint.
	 * @return
	 */
	public void escriuSortida(StringBuilder sortida) {
		this.sortida = sortida;
	}
	/**
	 * Retorna l'actual vector de caràcters en el qual s'hi troba escrivint.
	 * @return 	
	 */
	public StringBuilder llegeixTancament() {
		return tancament;
	}
	/**
	 * Estableix l'actual vector de caràcters en el qual s'hi troba escrivint.
	 * @return
	 */
	public void escriuTancament(StringBuilder tancament) {
		this.tancament = tancament;
	}

	public Font() {
		super();
		// Preparem els dos vectors de caràcters que
		// formaran el document final: [sortida.concatena(tancament)]
		this.sortida = new StringBuilder();
		this.tancament = new StringBuilder();
		// Inicialitzem els constructors de cadenes
		// llest per insertar-hi noves claus amb valors.
		preparaSortida();
	}

	/**
	 * StringBuilder sortida:<br/>
	 * &nbsp;&nbsp;&lt;?xml version=\"1.0\" encoding=\"UTF-8\"?&gt;<br/>
	 * &nbsp;&nbsp;&lt;abadia xml:lang=\"ca\" xmlns=\"http://abadia-monsterrat.cat/abadia\"&gt;<br/>
	 * <br/>
	 * StringBuilder tancament:<br/>
	 * &lt;/abadia&gt;
	 */
	public void escriuAnyell(String clau, String valor) {
		sortida.append(TABULACIÓ);
		sortida.append(CLAU_OBERTURA);
		sortida.append(clau);
		sortida.append(CLAU_TANCAMENT);
		sortida.append(FI_DE_LÍNIA);
		
		sortida.append(TABULACIÓ);
		
		sortida.append(VALOR_OBERTURA);
		sortida.append(valor);
		sortida.append(VALOR_TANCAMENT);
		sortida.append(FI_DE_LÍNIA);
		
		sortida.append(ANYELL_OBERTURA);
		sortida.append(FI_DE_LÍNIA);
		
		tancament.insert(0, ANYELL_TANCAMENT + FI_DE_LÍNIA);
	}
	
	public void escriure(String marcatge) {
		sortida.append(marcatge);
	}
	public void escriure(char caràcter) {
		sortida.append(caràcter);
	}
	public void escriureAbans(String marcatge) {
		sortida.insert(0, marcatge);
	}
	public void escriureAbans(char caràcter) {
		sortida.insert(0, caràcter);
	}
	public void escriureTancament(String marcatge) {
		tancament.append(marcatge);
	}
	public void escriureTancamentAbans(String marcatge) {
		tancament.insert(0, marcatge);
	}
	/**
	 * Retorna vector de caràcters composat.
	 * @return el nou vector de caràcters composat
	 */
	public String extreuDocument() {
		sortida.append(tancament.toString());
		return sortida.toString();
	}
	/**
	 * StringBuilder sortida:<br/>
	 * &lt;?xml version=\"1.0\" encoding=\"UTF-8\"?&gt;<br/>
	 * &lt;abadia xml:lang=\"ca\" xmlns=\"http://abadia-monsterrat.cat/abadia\"&gt;<br/>
	 * <br/>
	 * StringBuilder tancament:<br/>
	 * &lt;/abadia&gt;
	 */
	public void preparaSortida() {
		// Preparem inici de document
		sortida.append(CAPÇALERA);
		sortida.append(ABADIA_OBERTURA);
		// Preparem fi de document
		tancament.append(ABADIA_TANCAMENT);
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
	
	public static final String ABADIA = "abadia";
	public static final String ANYELL = "anyell";
	public static final String CLAU = "clau";
	public static final String VALOR = "valor";
	
	/**
	 * &lt;?xml version=\"1.0\" encoding=\"UTF-8\"?&gt;
	 */
	public static final String CAPÇALERA = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
	/**
	 * &lt;abadia xml:lang=\"ca\" xmlns=\"http://abadia-monsterrat.cat/abadia\"&gt;
	 */
	public static final String ABADIA_OBERTURA = "<abadia xml:lang=\"ca\" xmlns=\"http://abadia-montserrat.cat/anyell\">\n";
	/**
	 * &lt;/abadia&gt;
	 */
	public static final String ABADIA_TANCAMENT = "</abadia>";
	
	/**
	 * &lt;anyell&gt;
	 */
	public static final String ANYELL_OBERTURA = "<anyell>\n";
	/**
	 * &lt;/anyell&gt;
	 */
	public static final String ANYELL_TANCAMENT = "</anyell>\n";
	/**
	 * &lt;nom&gt;
	 */
	public static final String NOM_OBERTURA = "<nom>";
	/**
	 * &lt;/nom&gt;
	 */
	public static final String NOM_TANCAMENT = "</nom>\n";
	/**
	 * &lt;clau&gt;
	 */
	public static final String CLAU_OBERTURA = "<clau>";
	/**
	 * &lt;/anyell&gt;
	 */
	public static final String CLAU_TANCAMENT = "</clau>";
	/**
	 * &lt;valor&gt;
	 */
	public static final String VALOR_OBERTURA = "<valor>";
	/**
	 * &lt;/valor&gt;
	 */
	public static final String VALOR_TANCAMENT = "</valor>";
}