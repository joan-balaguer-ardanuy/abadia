package abadia;

/**
 * 
 */
public abstract class Marcatge extends Datagrama {
	
	private static final long serialVersionUID = -666L;
	private String domini;
	private String marca;

	public String domini() {
		return domini;
	}
	public String marca() {
		return marca;
	}
	public String marca(String value) {
		String antic = this.marca;
		this.marca = value;
		return antic;
	}
	
	public Marcatge() {
		super();
		this.domini = null;
	}
	public Marcatge(String marca) {
		super();
		this.domini = null;
		this.marca = marca;
	}
	public Marcatge(String domini, String marca) {
		super();
		this.domini = domini;
		this.marca = marca;
	}
	
	/**
	 * {@inheritDoc} 
	 * Hola, hola, hola!
	 */
	public final void escriure(StringBuilder font) {
		super.escriure(font);
		marcarInici();
		if(domini() != null) {
			escriure(domini());
			escriure(DOS_PUNTS);
		}
		escriure(marca());
		marcarFinal();
	}
	
	@Override
	public String llegir() {
		return domini() != null ?
				domini() + DOS_PUNTS + marca() : marca();  
	}
	
	protected abstract void marcarInici();
	protected abstract void marcarFinal();
	
	/**
	 * Octet del Format Transformat Unificat. 
	 */
	public static final String UTF8 = "UTF-8";
	
	/**
	 * Versió actual del Document Extensible de Marcatge. 
	 */
	public static final String DEM = "1.0";
	
	/**
	 * Marca bàsica.
	 */
	public static final String MARCA = "xml";
	
	/**
	 * Atribut de codificació.
	 */
	public static final String CODIFICACIÓ = "encoding";
	
	/**
	 * Atribut de versió emprada en l'escriptura del document.
	 */
	public static final String VERSIÓ = "version";
	
	/**
	 * Atribut de identificació de llengua usada en
	 * la transcripció del document.
	 */
	public static final String LLENGUA = "lang";

	/**
	 * Atribut de identificació de llengua usada en
	 * la transcripció del document.
	 */
	public static final String DOMINI = "xmlns";
}