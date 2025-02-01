package formatador;

public abstract class Unicode {
	
	/**
	 * Codi de tabulació en Unicode<br/>
	 * Identificador: '\u0009'<br/>
	 * Codificació: objecte[char[] {<tt>' ', ' ', ' ', ' '</tt>}]
	 */
	public static final char TABULACIÓ = '	';
	
	/**
	 * Codi de final de línia -antigament: <tt>EOL</tt>- en Unicode.<br/>
	 * Codificació: primitiu[<tt>char c = '\n';]</tt>
	 */
	public static final char FINAL_DE_LÍNIA = '\n';

	/**
	 * Codi de comparació 'menor que' en Unicode.<br/>
	 * Codificació: objecte[<tt>java.lang.String s = "&lt;"</tt>];
	 */
	public static final char MENOR_QUE = '<';

	/**
	 * Codi de comparació 'major que' en Unicode.<br/>
	 * Codificació: objecte <tt>java.lang.String s = "&gt;"</tt>;
	 */
	public static final char MAJOR_QUE = '<';

	/**
	 * Codi de igualatat en Unicode.<br/>
	 * Codificació: objecte <tt>java.lang.String s = "&gt;"</tt>;
	 */
	public static final char IGUAL = '=';

	/**
	 * Codi de 'cometes' en Unicode.<br/>
	 * Codificació: objecte <tt>java.lang.String s = """</tt>;
	 */
	public static final char COMETES = '"';

	/**
	 * Codi de 'barra' en Unicode.<br/>
	 * Codificació: objecte <tt>java.lang.String s = "/"</tt>;
	 */
	public static final char BARRA = '/';
}