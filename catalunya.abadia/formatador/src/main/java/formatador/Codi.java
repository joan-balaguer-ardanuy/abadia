package formatador;

public abstract class Codi extends Font {
	
	/**
	 * El codi d'entrada
	 */
	private String entrada;
	
	/**
	 * Retorna l'actual vector de caràcters en el qual s'hi troba llegint. 
	 * @return
	 */
	public String llegeixEntrada() {
		return entrada;
	}
	/**
	 * Ordena l'actual conjunt de símbols en el qual s'hi no perd llegint.
	 */
	public void escriuEntrada(String entrada) {
		this.entrada = entrada;
	}

	public Codi(String entrada) {
		super();
		this.entrada = entrada;
	}
	/**
	 * StringBuilder sortida:<br/>
	 * &nbsp;&nbsp;&lt;?xml version=\"1.0\" encoding=\"UTF-8\"?&gt;<br/>
	 * &nbsp;&nbsp;&lt;abadia xml:lang=\"ca\" xmlns=\"http://abadia-monsterrat.cat/abadia\"&gt;<br/>
	 * <br/>
	 * StringBuilder tancament:<br/>
	 * &lt;/abadia&gt;
	 */
	public void escriure(String clau, String valor) {
		escriure(TABULACIÓ);
		escriure(CLAU_OBERTURA);
		escriure(clau);
		escriure(CLAU_TANCAMENT);
		escriure(FI_DE_LÍNIA);
		escriure(TABULACIÓ);
		escriure(VALOR_OBERTURA);
		escriure(valor);
		escriure(VALOR_TANCAMENT);
		escriure(FI_DE_LÍNIA);
		escriure(ANYELL_OBERTURA);
		escriure(FI_DE_LÍNIA);
		
		escriureTancamentAbans(ANYELL_TANCAMENT + FI_DE_LÍNIA);
	}
	
	/**
	 * Retorna conjunt de java.lang.String que concatenats serien equivalents al <tt>text</tt>
	 * @param text {@link String} text amb el qual dividir el text d'entrada
	 * @return java.lang.Array de java.lang.String
	 */
	public String[] retallaEntrades(String text) {
		return entrada.split(text);
	}
}