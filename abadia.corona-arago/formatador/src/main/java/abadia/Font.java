package abadia;

public abstract class Font {

	/**
	 * El codi de sortida
	 */
	private StringBuilder cap;
	
	/**
	 * Els tancaments XML que s'aniran afegint incrementalment
	 * mentre vagi definint noves claus trobades.
	 */
	private StringBuilder cua;

	/**
	 * Retorna l'actual vector de caràcters en el qual s'hi troba escrivint.
	 * @return 	
	 */
	public StringBuilder cap() {
		return cap;
	}
	/**
	 * Estableix l'actual vector de caràcters en el qual s'hi troba escrivint.
	 * @return
	 */
	public void cap(StringBuilder sortida) {
		this.cap = sortida;
	}
	/**
	 * Retorna l'actual vector de caràcters en el qual s'hi troba escrivint.
	 * @return 	
	 */
	public StringBuilder cua() {
		return cua;
	}
	/**
	 * Estableix l'actual vector de caràcters en el qual s'hi troba escrivint.
	 * @return
	 */
	public void cua(StringBuilder tancament) {
		this.cua = tancament;
	}

	public Font() {
		super();
		// Preparem els dos vectors de caràcters que
		// formaran el document final: [sortida.concatena(tancament)]
		this.cap = new StringBuilder();
		this.cua = new StringBuilder();
	}
	
	public void escriureCap(String codi) {
		cap.append(codi);
	}
	public void escriureCap(char caràcter) {
		cap.append(caràcter);
	}
	public void escriureCapAbans(String codi) {
		cap.insert(0, codi);
	}
	public void escriureCapAbans(char caràcter) {
		cap.insert(0, caràcter);
	}
	public void escriureCua(String codi) {
		cua.append(codi);
	}
	public void escriureCuaAbans(String codi) {
		cua.insert(0, codi);
	}
	
	/**
	 * Retorna vector de caràcters composat.
	 * @return el nou vector de caràcters composat
	 */
	public String extreureDocument() {
		cap.append(cua.toString());
		return cap.toString();
	}
}