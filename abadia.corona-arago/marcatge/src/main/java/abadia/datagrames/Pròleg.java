package abadia.datagrames;

import abadia.Atribut;
import abadia.Etiqueta;

/**
 * <h1>Capçalera de Document</h1>
 * <h2><i>La capçalera en tot Document Extensible de Marcatge (DEM 1.0).</i></h2>
 */
public class Pròleg extends Etiqueta {

	private static final long serialVersionUID = -3526311887709426878L;

	/**
	 * Sortida:<br/>
	 * &lt;?xml version=\"1.0\" encoding=\"UTF-8\"?&gt;
	 */
	public static final Pròleg estàndard() {
		Atributs propietats = new Atributs(versió);
		propietats.add(codificació);
		return new Pròleg(propietats);
	}

	/**
	 * Sortida:<br/>
	 * &lt;?
	 */	
	@Override
	public String obertura() {
		return new String(new char[] { MENOR, INTERROGANT } );
	}

	/**
	 * Sortida:<br/>
	 * ?&gt;
	 */
	@Override
	public String tancament() {
		return new String(new char[] { INTERROGANT, MAJOR } );
	}
	
	public Pròleg() {
		super(MARCA);
	}
	public Pròleg(Atributs característiques) {
		super(MARCA, característiques);
	}
	
	/**
	 * Sortida:<br/>
	 * version=\"1.0\"
	 */	
	public static final Atribut versió = new Atribut(VERSIÓ, DEM);

	/**
	 * Sortida:<br/>
	 * encoding=\"UTF-8\"
	 */
	public static final Atribut codificació = new Atribut(CODIFICACIÓ, UTF8);
}