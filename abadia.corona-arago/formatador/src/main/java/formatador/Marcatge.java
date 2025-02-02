package formatador;

/**
 * 
 * 
 * 
 * 
 * 
 */
public abstract class Marcatge extends Codi {
	
	public static final String ATRIBUT_VERSIÓ = "version";
	public static final String ATRIBUT_XML_LANG = "xml:version";
	
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

	public Mode llegirMode() {
		return mode;
	}
	public void escriureMode(Mode mode) {
		this.mode = mode;
	}

	public Marcatge(String entrada) {
		super(entrada);
	}
	
	public void obrir(String marcatge) {
		escriure(MENOR_QUE);
		escriure(marcatge);
		escriure(MAJOR_QUE);
	}
	public void tancar(String marcatge) {
		escriure(MENOR_QUE);
		escriure(BARRA);
		escriure(marcatge);
		escriure(MAJOR_QUE);
	}
	public void escriuTancamentAbans(String marcatge) {
		escriureAbans(obtéTancament(marcatge));
	}
	
	public static String obtéObertura(String marcatge) {
		return MENOR_QUE + marcatge + MAJOR_QUE; 
	}
	public static String obtéTancament(String marcatge) {
		return MENOR_QUE + BARRA + marcatge + MAJOR_QUE; 
	}	
}