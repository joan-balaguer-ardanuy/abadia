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
	public void escriu(String clau, String valor) {
		sortida.append(TABULACIÓ);
		sortida.append(CLAU_OBERTURA);
		sortida.append(clau);
		sortida.append(CLAU_TANCAMENT);
		sortida.append(FINAL_DE_LÍNIA);
		sortida.append(TABULACIÓ);
		sortida.append(VALOR_OBERTURA);
		sortida.append(valor);
		sortida.append(VALOR_TANCAMENT);
		sortida.append(FINAL_DE_LÍNIA);
		sortida.append(ANYELL_OBERTURA);
		sortida.append(FINAL_DE_LÍNIA);
		
		tancament.insert(0, ANYELL_TANCAMENT + FINAL_DE_LÍNIA);
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