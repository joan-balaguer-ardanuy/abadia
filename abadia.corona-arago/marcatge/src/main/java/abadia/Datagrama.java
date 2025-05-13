package abadia;

public abstract class Datagrama
	implements Transcriptor {

	private static final long serialVersionUID = 5412861129441302630L;

	private StringBuilder font;
	
	public StringBuilder font() {
		return font;
	}
	public StringBuilder font(StringBuilder sortida) {
		StringBuilder antic = this.font;
		this.font = sortida;
		return antic;
	}
	
	protected Datagrama() {
		this.font = null;
	}

	@Override
	public abstract String llegir();
	
	@Override
	public void escriure(StringBuilder sortida) {
		this.font = sortida;
	}
	
	@Override
	public void escriure(char entrada) {
		font().append(entrada);
	}
	@Override
	public void escriure(String entrada) {
		font().append(entrada);
	}
	@Override
	public void escriureAbans(char entrada) {
		font().insert(0, entrada);
	}
	@Override
	public void escriureAbans(String entrada) {
		font().insert(0, entrada);
	}
	
	@Override
	public final String toString() {
		return llegir();
	}

	/**
	 * Codi de tabulació en Unicode<br/>
	 * Identificador: '\u0009'<br/>
	 * Codificació: objecte[char[] {<tt>'	'</tt>}]
	 */
	public static final char TABULACIÓ = '\u0009';
	
	/**
	 * Codi de final de línia -antigament: <tt>EOL</tt>- en Unicode.<br/>
	 * Codificació: primitiu[<tt>char c = '\n';</tt>]
	 */
	public static final char FINAL_DE_LÍNIA = '\n';

	/**
	 * Codi de comparació 'menor que' en Unicode.<br/>
	 * Codificació: objecte[<tt>java.lang.String s = "&lt;"</tt>];
	 */
	public static final char MENOR = '<';

	/**
	 * Codi de comparació 'major que' en Unicode.<br/>
	 * Codificació: objecte[<tt>java.lang.String s = "&gt;"</tt>];
	 */
	public static final char MAJOR = '>';

	/**
	 * Codi de igualatat en Unicode.<br/>
	 * Codificació: objecte[<tt>java.lang.String s = "&gt;"</tt>];
	 */
	public static final char IGUAL = '=';

	/**
	 * Codi de 'cometes' en Unicode.<br/>
	 * Codificació: objecte[<tt>java.lang.String s = """</tt>];
	 */
	public static final char COMETES = '"';
	
	/**
	 * Codi de 'cometa' en Unicode.<br/>
	 * Codificació: objecte[<tt>java.lang.String s = "'"</tt>];
	 */
	public static final char COMETA = '\'';

	/**
	 * Codi de 'barra' en Unicode.<br/>
	 * Codificació: objecte[<tt>java.lang.String s = "/"</tt>];
	 */
	public static final char BARRA = '/';

	/**
	 * Codi de 'dos punts' en Unicode.<br/>
	 * Codificació: objecte[<tt>java.lang.String s = ":"</tt>];
	 */
	public static final char DOS_PUNTS = ':';
	
	/**
	 * Codi de 'punt i coma' en Unicode.<br/>
	 * Codificació: objecte[<tt>java.lang.String s = ";"</tt>];
	 */
	public static final char PUNT_I_COMA = ':';

	/**
	 * Codi de 'espai' en Unicode.<br/>
	 * Codificació: objecte[<tt>java.lang.String s = " "</tt>];
	 */
	public static final char ESPAI = ' ';
	
	/**
	 * Codi de 'interrogant' en Unicode.<br/>
	 * Codificació: objecte[<tt>java.lang.String s = "?"</tt>];
	 */
	public static final char INTERROGANT = '?';
	
	/**
	 * Codi de 'exclamació' en Unicode.<br/>
	 * Codificació: objecte[<tt>java.lang.String s = "!"</tt>];
	 */
	public static final char EXCLAMACIÓ = '!';
	
	/**
	 * Codi de 'ampersand' en Unicode.<br/>
	 * Codificació: objecte[<tt>java.lang.String s = "&"</tt>];
	 */
	public static final char AMPERSAND = '&';
	
	/**
	 * Codi de 'guió' en Unicode.<br/>
	 * Codificació: objecte[<tt>java.lang.String s = "-"</tt>];
	 */
	public static final char GUIÓ = '-';
	
	/**
	 * Codi de 'apòstrof' 1 en Unicode.<br/>
	 * Codificació: objecte[<tt>java.lang.String s = "'"</tt>];
	 */
	public static final char APÒSTROF = '\'';
	
	/**
	 * Codi de 'cometa_dreta' en Unicode.<br/>
	 * Codificació: objecte[<tt>java.lang.String s = "’"</tt>];
	 */
	public static final char COMETA_DRETA = '’';
}