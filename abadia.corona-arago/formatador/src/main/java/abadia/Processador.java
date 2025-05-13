package abadia;

import abadia.datagrames.Obertura;
import abadia.datagrames.Pròleg;
import abadia.datagrames.Tancament;

/**
 * <h1>Document Extensible de Marcatge (DEM 1.0).</h1>
 * <h2><i>Primer prototip per a codificació en<br/>
 * Octet del Format Transformat Unificat.</i></h2>
 */
public abstract class Processador extends Codi {
	
	/**
	 * Determina de quina manera es presenta el marcatge de <tt>Marcatge.class.this</tt> codi.<br/>
	 * <ul>
	 * <li><b>Mode</b>.<tt>INDENTAT</tt>: Quan s'inserta marcatge es procedeix
	 * a una inserció d'un <b>Unicode</b>.<tt>FINAL_DE_LÍNIA</tt> al final de la
	 * sortida i seguidament s'hi afegeix una <b>Unicode</b>.<tt>TABULACIÓ</tt>.</li>
	 * <li><b>Mode</b>.<tt>BRUT</tt>: Quan s'estableix estiqueta no es
	 * proedeix a cap tractament especial.</li>
	 * </ul>
	 */
	public enum Mode {
		
		/**
		 * <b>Mode</b>.<tt>INDENTAT</tt>
		 */
		INDENTAT,

		/**
		 * <b>Mode</b>.<tt>BRUT</tt>
		 */
		BRUT
	}
	
	/**
	 * El mode d'escriptura de marcatges.
	 */
	private Mode mode;
	
	private String[] noms;

	public String[] llegirNoms() {
		return noms;
	}
	public Mode llegirMode() {
		return mode;
	}
	public void escriureMode(Mode mode) {
		this.mode = mode;
	}

	public Processador(String entrada, String... noms) {
		super(entrada);
		// assignem els espais de noms de l'abadia
		this.noms = noms;
		// Inicialitzem els constructors de cadenes
		// llest per insertar-hi noves claus amb valors.
		prepararDocument();
	}

	/**
	 * Text inicial de sortida:<br/>
	 * &nbsp;&nbsp;&lt;?xml version=\"1.0\" encoding=\"UTF-8\"?&gt;<br/>
	 * &nbsp;&nbsp;&lt;abadia xml:lang=\"ca\" xmlns=\"http://abadia-monsterrat.cat/abadia\"&gt;<br/>
	 * <br/>
	 * Text inicial de tancament:<br/>
	 * &lt;/abadia&gt;
	 */
	public void escriure(String clau, String valor) {
		cap().append(Datagrama.TABULACIÓ);
		escriurePropietat(CLAU, clau);
		cap().append(Datagrama.FINAL_DE_LÍNIA);
		cap().append(Datagrama.TABULACIÓ);
		escriurePropietat(VALOR, valor);
		cap().append(Datagrama.FINAL_DE_LÍNIA);
		Obertura anyellObertura = new Obertura(ANYELL);
		anyellObertura.escriure(cap());
		cap().append(Datagrama.FINAL_DE_LÍNIA);
		Tancament anyellTancament = new Tancament(ANYELL);
		escriureCuaAbans(anyellTancament.llegir() + Datagrama.FINAL_DE_LÍNIA);
	}

	public void escriurePropietat(String nom, String valor) {
		Obertura oberturaClau = new Obertura(nom);
		oberturaClau.escriure(cap());
		cap().append(valor);
		Tancament tancamentClau = new Tancament(nom);
		tancamentClau.escriure(cap());
	}
	public void escriurePropietat(String domini, String nom, String valor) {
		Obertura oberturaClau = new Obertura(domini, nom);
		oberturaClau.escriure(cap());
		cap().append(valor);
		Tancament tancamentClau = new Tancament(domini, nom);
		tancamentClau.escriure(cap());
	}
	/**
	 * StringBuilder sortida:<br/>
	 * &lt;?xml version=\"1.0\" encoding=\"UTF-8\"?&gt;<br/>
	 * &lt;abadia xml:lang=\"cat\" xmlns=\"http://abadia-monsterrat.cat/abadia\"&gt;<br/>
	 * <br/>
	 * StringBuilder tancament:<br/>
	 * &lt;/abadia&gt;
	 */
	public void prepararDocument() {
		// Preparem inici de document
		Pròleg.estàndard().escriure(cap());
		escriureCap(Datagrama.FINAL_DE_LÍNIA);
		// Preparem inici del document de l'abadia
		Obertura obertura = new Obertura(ABADIA);
		obertura.establirAtribut(new Atribut(Marcatge.LLENGUA, "cat"));
		obertura.establirAtribut(new Atribut(Marcatge.DOMINI, "http://abadia-montserrat.cat/anyell"));
		obertura.escriure(cap());
		escriureCap(Datagrama.FINAL_DE_LÍNIA);
		// Preparem fi del document de l'abadia
		Tancament tancament = new Tancament(ABADIA);
		tancament.escriure(cua());
	}
	
	public static final String ABADIA = "abadia";
	public static final String ANYELL = "anyell";
	public static final String CLAU = "clau";
	public static final String VALOR = "valor";
	public static final String NOM = "nom";
}