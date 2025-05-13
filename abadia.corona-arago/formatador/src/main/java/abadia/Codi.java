package abadia;

public abstract class Codi extends Font {
	
	/**
	 * El codi d'entrada
	 */
	private String codi;
	
	/**
	 * Retorna l'actual vector de caràcters en el qual s'hi troba llegint. 
	 * @return
	 */
	public String llegirEntrada() {
		return codi;
	}
	/**
	 * Ordena l'actual conjunt de símbols en el qual s'hi no perd llegint.
	 */
	public void escriureEntrada(String entrada) {
		this.codi = entrada;
	}

	public Codi(String entrada) {
		super();
		this.codi = entrada;
	}
	
	/**
	 * Retorna conjunt de java.lang.String que concatenats serien equivalents al <tt>text</tt>
	 * @param text {@link String} text amb el qual dividir el text d'entrada
	 * @return java.lang.Array de java.lang.String
	 */
	public String[] retallarEntrades(String text) {
		return codi.split(text);
	}
}