package catalunya.formatador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Font: [https://bdlex.iec.cat/]<br/>
 * <br/>
 * <tt>
 * <big>Tractament de informació proporcionada per BDLEX:</big><br/>
 * <big>=================================================</big>
 * </tt><br/>
 * <h1>Exemple de línia de text a tractar:</h1>
 * <ul>
 * <li>Primer datagrama,<br/>
 * &nbsp;&nbsp;<i><b>inci:[</b></i><tt>"DIEC1&nbsp;&nbsp;&nbsp;&nbsp;[a] a¹ [pl. as] f. Nom de la lletra a A. |<br/>
 * &nbsp;&nbsp;no saber ni la a No saber ni els rudiments d'una cosa.'\n'"</tt><i><b>]:fi</b></i></li>
 * <li>En primer lloc, reemplaçarem la tabulació existent entre "DIEC1" i obtindríem:<br/>
 * &nbsp;&nbsp;<b><i>inici:[</i></b><tt>"DIEC1 [a] a¹ [pl. as] f. Nom de la lletra a A. |<br/>
 * &nbsp;&nbsp;no saber ni la a No saber ni els rudiments d'una cosa.'\n'"</tt><b><i>]:fi</i></b></li>
 * </ul>
 * <p>S'enumera a continuació què n'obtenim d'interpretar aquesta línia segons
 * què ens dirà, en cada moment del mateix procediment, què hem d'executar a cada
 * moment, segons el procés actual de lectura en el qual ens trobem.:</p>
 * <ul>
 * <li>""DIEC1":  principalment identificarà esdeveniment de nova entrada de diccionari.</li>
 * <li>"[": indica que comença la lectura de la clau &lt;K&gt; on ens trobem processant.</li>
 * <li> * "]": a l'aparèixer aquest caràcter per primera vegada, vol dir que la clau del
 * diccionari ja ha sigut llegida per complet.</li>
 * <li>"]": a l'aparèixer aquest caràcter per primera vegada, vol dir que la clau del
 * diccionari ja ha sigut llegida per complet.</li>
 * <li>" ": un espai de separació</li>
 * <li>La clau és tota la resta: fins a la següent aparició de "DIEC1	", és a dir:<br/>
 * "a¹ [pl. as] f. Nom de la lletra a A. | no saber ni la a No saber ni els rudiments d'una cosa."</li>
 * </ul>
 * <br/>
 * <h1>Format per línia:</h1>
 * <tt>
 * [inici de iteració]:<br/>
 * &gt; CODI_FONT &gt;<br/>
 * &nbsp;&nbsp;&gt; aparició de caràcter d'obertura '[' &gt;<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&gt; CLAU &gt;<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&gt; aparició de caràcter ']' &gt;<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&gt; un espai blanc ' '&gt; <br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&gt; VALOR (fins darrera aparició de<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'\n' des del final de la mateixa línia)&gt;<br/>
 * :[fi de iteració]<br/>
 * </tt>
 */
public class Bdlex {

	/**
	 * INSTITUT D\'ESTUDIS CATALANS. <i>Diccionari de la llengua catalana</i>.
	 * Barcelona: Edicions 62: Enciclopèdia Catalana: Publicacions de l\'Abadia de Montserrat;
	 * Palma de Mallorca: Editorial Moll; València: Edicions 3 i 4, 1995
	 */
	public static final String DIEC1 = "DIEC1";
	
	/**
	 * FERRER PASTOR, Francesc. <i>Diccionari general</i>. València: Impremta Fermar, 1985
	 */
	public static final String DGFP = "DGFP";
	
	/**
	 * ALCOVER, Antoni M. i Francesc de B. MOLL. <i>Diccionari Català-Valencià-Balear</i>,
	 * Ciutat de Mallorca, 1926-1968. 10 v.
	 */
	public static final String DCVB = "DCVB";
	
	/**
	 * FABRA, Pompeu. <i>Diccionari general de la llengua catalana</i>.
	 * Barcelona: Llibreria Catalònia, 1932
	 */
	public static final String DGLC = "DGLC";
	
	/**
	 * VALLÈS, Emili. <i>Pal·las: diccionari català-castellà-francès:
	 * amb vocabularis castellà-català-francès-català</i>. Barcelona: Horta, [1927]
	 */
	public static final String DPCV = "DPCV";
	
	/**
	 * MARTÍ I GADEA, Joaquim. <i>Novísimo diccionario general valenciano-castellano</i>.
	 * València. 1891
	 */
	public static final String DGMG = "DGMG";
	
	/**
	 * TARONGÍ I CORTÈS, Josep]. <i>Diccionari mallorquí-castellá</i> per un mallorquí,
	 * aficionat á sa seva llengua. Palma de Mallorca: Imprenta d\'en Bartomeu Rotger, 1878. [Inacabat]
	 */
	public static final String DMCT = "DMCT";
	
	/**
	 * AMENGUAL, Joan Josep. <i>Nuevo diccionario mallorquin-castellano-latin</i>.
	 * Palma: Imprenta y Librería de Juan Colomar, 1858. 2 v.
	 */
	public static final String NDMA = "NDMA";
	
	/**
	 * ESCRIG, José. <i>Diccionario valenciano-castellano</i>.
	 * Valencia: Imprenta de J. Ferrer de Orga, 1851
	 */
	public static final String DVCE = "DVCE";
	
	/**
	 * FIGUERA, Pere Antoni. <i>Diccionari mallorqui-castella</i>.
	 * Palma: Imprenta y Llibreria de Esteva Trias, 1840
	 */
	public static final String DMCF = "DMCF";
	
	/**
	 * LABÈRNIA, Pere. <i>Diccionari de la llengua catalana: ab la correspondencia castellana y llatina</i>.
	 * Barcelona: Estampa dels hereus de la V. Pla, 1839 1840. 2 v.
	 */
	public static final String DLCL = "DLCL";
	
	/**
	 * ESTEVE, Joaquim; BELLVITGES, Josep; JUGLÀ I FONT, Antoni. <i>Diccionario catalan-castellano-latino</i>.
	 * Barcelona: en la oficina de Tecla Pla viuda, 1803 1805. 2 v.
	 */
	public static final String DEBJ = "DEBJ";
	
	/**
	 * FEBRER I CARDONA, Antoni. &quot;Diccionári menorquí españól francês y llatí&quot;.
	 * [manuscrit, principis segle XIX]
	 */
	public static final String DMFC = "DMFC";

	public static final class Entrada {
		public static final String CLAU_OBERTURA = "[";
		public static final String CLAU_TANCAMENT = "]";
	}
	public static final class Sortida {
		/**
		 * &lt;?xml version=\"1.0\" encoding=\"UTF-8\"?&gt;
		 */
		public static final String CAPÇALERA = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		/**
		 * &lt;abadia xml:lang=\"ca\" xmlns=\"http://abadia-monsterrat.cat/abadia\"&gt;
		 */
		public static final String ABADIA_OBERTURA = "<abadia xml:lang=\"ca\" xmlns=\"http://abadia-montserrat.cat/abadia\">";
		/**
		 * &lt;/abadia&gt;
		 */
		public static final String ABADIA_TANCAMENT = "</abadia>";
		/**
		 * &lt;anyell&gt;
		 */
		public static final String ANYELL_OBERTURA = "<anyell>";
		/**
		 * &lt;/anyell&gt;
		 */
		public static final String ANYELL_TANCAMENT = "</anyell>";
		/**
		 * &lt;nom&gt;
		 */
		public static final String NOM_OBERTURA = "<nom>";
		/**
		 * &lt;/nom&gt;
		 */
		public static final String NOM_TANCAMENT = "</nom>";
		/**
		 * &lt;clau&gt;
		 */
		public static final String CLAU_OBERTURA = "<clau>";
		/**
		 * &lt;/anyell&gt;
		 */
		public static final String CLAU_TANCAMENT = "</clau>";
		/**
		 * &lt;valor&gt;
		 */
		public static final String VALOR_OBERTURA = "<valor>";
		/**
		 * &lt;/valor&gt;
		 */
		public static final String VALOR_TANCAMENT = "</valor>";
	}
	public static final class Caràcter {
		public static final char TABULACIÓ = '	';
		public static final char EOL = '\n';
	}
	public static abstract class Font {
		
		/**
		 * La font 
		 */
		private String font;
		
		/**
		 * El codi d'entrada
		 */
		private String entrada;

		/**
		 * El codi de sortida
		 */
		private StringBuilder sortida;
		
		/**
		 * Els tancaments XML que s'aniran afegint incrementalment
		 * mentre vagi definint noves claus trobades.
		 */
		private StringBuilder tancament;
		
		/**
		 * Obté el Bdlex.DICCIONARI: DIEC1, DGFP....
		 * @return el Bdlex.DICCIONARI establert
		 */
		public String llegeixFont() {
			return font;
		}
		/**
		 * Estableix el Bdlex.DICCIONARI: DIEC1, DGFP....
		 */
		public void escriuFont(String font) {
			this.font = font;
		}
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
		/**
		 * Retorna l'actual vector de caràcters en el qual s'hi troba escrvint.
		 * @return 	
		 */
		public StringBuilder llegeixSortida() {
			return sortida;
		}
		/**
		 * Estableix l'actual vector de caràcters en el qual s'hi troba escrvint.
		 * @return
		 */
		public void escriuSortida(StringBuilder sortida) {
			this.sortida = sortida;
		}
				
		/**
		 * Constructor de nova font de text pla per tractar.
		 * @param entrada la cadena de caràcters inicialment introduïda.
		 */
		public Font(String font, String entrada) {
			this.font = font;
			// Pre-formatar diccionari abans d'establir en la font.
			// Eliminació de totes les tabulacions.
			this.entrada = entrada.replaceAll(String.valueOf(Caràcter.TABULACIÓ), "");
			this.sortida = new StringBuilder();
			this.tancament = new StringBuilder();
			// Inicialitzem els constructors de cadenes llest per insertar-hi noves claus amb valors
			preparaSortida();
		}
		
		/**
		 * Retorna vector de caràcters composat.
		 * @return el nou vector de caràcters composat
		 */
		public String extreuDocument() {
			sortida.append(tancament.toString());
			return sortida.toString();
		}
		
		/**
		 * Retorna conjunt de java.lang.String que concatenats serien equivalents al <tt>text</tt>
		 * @param text {@link String} text amb el qual dividir el text d'entrada
		 * @return java.lang.Array de java.lang.String
		 */
		public String[] retallaEntrades(String text) {
			return entrada.split(text);
		}
		
		/**
		 * StringBuilder sortida:<br/>
		 * &lt;?xml version=\"1.0\" encoding=\"UTF-8\"?&gt;<br/>
		 * &lt;abadia xml:lang=\"ca\" xmlns=\"http://abadia-monsterrat.cat/abadia\"&gt;<br/>
		 * <br/>
		 * StringBuilder tancament:<br/>
		 * &lt;/abadia&gt;
		 */
		public void preparaSortida() {
			sortida.append(Sortida.CAPÇALERA);
			sortida.append(Caràcter.EOL);
			sortida.append(Sortida.ABADIA_OBERTURA);
			sortida.append(Caràcter.EOL);
			
			tancament.append(Sortida.ABADIA_TANCAMENT);
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
			sortida.append(Caràcter.TABULACIÓ);
			sortida.append(Sortida.CLAU_OBERTURA);
			sortida.append(clau);
			sortida.append(Sortida.CLAU_TANCAMENT);
			sortida.append(Caràcter.EOL);
			sortida.append(Caràcter.TABULACIÓ);
			sortida.append(Sortida.VALOR_OBERTURA);
			sortida.append(valor);
			sortida.append(Sortida.VALOR_TANCAMENT);
			sortida.append(Caràcter.EOL);
			sortida.append(Sortida.ANYELL_OBERTURA);
			sortida.append(Caràcter.EOL);
			
			tancament.insert(0, Sortida.ANYELL_TANCAMENT + Caràcter.EOL);
		}
	}
	
	public static abstract class Formatador
		extends Font
		implements java.util.Map<String, String> {

		/**
		 * La taula base
		 */
		java.util.Map<String,String> diccionari;
		
		public Formatador(String font, String entrada) {
			super(font, entrada);
			diccionari = new LinkedHashMap<String, String>();
		}
		
		/**
		 * 
		 */
		public void llegeix() { 
			String[] entrades = retallaEntrades(llegeixFont());
			for(String entrada : entrades) {
				if(entrada.startsWith(Entrada.CLAU_OBERTURA)) {
					String clau = entrada.substring(1, entrada.indexOf(Entrada.CLAU_TANCAMENT));
					String valor = entrada.substring(entrada.indexOf(Entrada.CLAU_TANCAMENT)+2, entrada.lastIndexOf(Caràcter.EOL));
					this.put(clau, valor);
				}
			}
		}
		/**
		 * 
		 */
		public void escriu() {
			for(Map.Entry<String, String> entry : diccionari.entrySet()) {
				escriu(entry.getKey(), entry.getValue());
			}
		}

		@Override
		public int size() {
			return diccionari.size();
		}
		@Override
		public boolean isEmpty() {
			return diccionari.isEmpty();
		}
		@Override
		public boolean containsKey(Object key) {
			return diccionari.containsKey(key);
		}
		@Override
		public boolean containsValue(Object value) {
			return diccionari.containsValue(value);
		}
		@Override
		public String get(Object key) {
			return diccionari.get(key);
		}
		@Override
		public String put(String key, String value) {
			return diccionari.put(key, value);
		}
		@Override
		public String remove(Object key) {
			return diccionari.remove(key);
		}
		@Override
		public void putAll(Map<? extends String, ? extends String> m) {
			diccionari.putAll(m);
		}
		@Override
		public void clear() {
			diccionari.clear();
		}
		@Override
		public Set<String> keySet() {
			return diccionari.keySet();
		}
		@Override
		public Collection<String> values() {
			return diccionari.values();
		}
		@Override
		public Set<Entry<String, String>> entrySet() {
			return diccionari.entrySet();
		}
	}

	public static void main(String[] args) {
		try {
			// Extracció de DIEC1
//			String entrada = Bdlex.llegeix("/home/joan/git/abadia/catalunya.abadia/llenguatge/català/registre/diec1.txt");
//			DIEC1 diec1 = new DIEC1(entrada);
//			diec1.escriu();
//			Bdlex.escriu("/home/joan/git/abadia/catalunya.abadia/llenguatge/català/diec1.xml", diec1.extreuDocument());
			// Extracció de DIEC1
			String entrada = Bdlex.llegeix("/home/joan/git/abadia/catalunya.abadia/llenguatge/català/registre/dgfp.txt");
			DGFP dgfp = new DGFP(entrada);
			dgfp.escriu();
			Bdlex.escriu("/home/joan/git/abadia/catalunya.abadia/llenguatge/català/català.dgfp.xml", dgfp.extreuDocument());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public static String llegeix(String ruta) throws Throwable {
		String text = null;
		BufferedReader br = new BufferedReader(new FileReader(ruta));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    text = sb.toString();
		} finally {
		    br.close();
		}
		return text;
	}
	
	public static void escriu(String ruta, String text) {
	    BufferedWriter writer;
	    try {
			writer = new BufferedWriter(new FileWriter(ruta));
		    writer.write(text);
		    writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DIEC1').
	 */
	public static final class DIEC1 extends Formatador {
		/**
		 * Constructor de classe Formatador DIEC1.
		 * @param entrada el text pla
		 */
		public DIEC1(String entrada) {
			super(Bdlex.DIEC1, entrada);
			llegeix();
		}
	}
	/**
	 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DGFP').
	 */
	public static final class DGFP extends Formatador {
		/**
		 * Constructor de classe Formatador DGFP
		 * @param entrada el text pla
		 */
		public DGFP(String entrada) {
			super(Bdlex.DGFP, entrada);
			llegeix();
		}
	}
	/**
	 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DCVB').
	 */
	public static final class DCVB extends Formatador {
		/**
		 * Constructor de classe Formatador DCVB
		 * @param entrada el text pla
		 */
		public DCVB(String entrada) {
			super(Bdlex.DCVB, entrada);
			llegeix();
		}
	}
	/**
	 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DGLC').
	 */
	public static final class DGLC extends Formatador {
		/**
		 * Constructor de classe Formatador DGLC
		 * @param entrada el text pla
		 */
		public DGLC(String entrada) {
			super(Bdlex.DGLC, entrada);
			llegeix();
		}
	}
	/**
	 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DPCV').
	 */
	public static final class DPCV extends Formatador {
		/**
		 * Constructor de classe Formatador DPCV
		 * @param entrada el text pla
		 */
		public DPCV(String entrada) {
			super(Bdlex.DPCV, entrada);
			llegeix();
		}
	}
	/**
	 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DGMG').
	 */
	public static final class DGMG extends Formatador {
		
		/**
		 * Constructor de classe Formatador DGMG
		 * @param entrada el text pla
		 */
		public DGMG(String entrada) {
			super(Bdlex.DGMG, entrada);
			llegeix();
		}
	}
	/**
	 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('NDMA').
	 */
	public static final class NDMA extends Formatador {
		
		/**
		 * Constructor de classe Formatador NDMA
		 * @param entrada el text pla
		 */
		public NDMA(String entrada) {
			super(Bdlex.NDMA, entrada);
			llegeix();
		}
	}
	/**
	 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DVCE').
	 */
	public static final class DVCE extends Formatador {
		
		/**
		 * Constructor de classe Formatador DVCE
		 * @param entrada el text pla
		 */
		public DVCE(String entrada) {
			super(Bdlex.DVCE, entrada);
			llegeix();
		}
	}
	/**
	 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DMCF').
	 */
	public static final class DMCF extends Formatador {
		
		/**
		 * Constructor de classe Formatador DMCF
		 * @param entrada el text pla
		 */
		public DMCF(String entrada) {
			super(Bdlex.DMCF, entrada);
			llegeix();
		}
	}
	/**
	 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DLCL').
	 */
	public static final class DLCL extends Formatador {
		
		/**
		 * Constructor de classe Formatador DLCL
		 * @param entrada el text pla
		 */
		public DLCL(String entrada) {
			super(Bdlex.DLCL, entrada);
			llegeix();
		}
	}
	/**
	 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DEBJ').
	 */
	public static final class DEBJ extends Formatador {
		
		/**
		 * Constructor de classe Formatador DEBJ
		 * @param entrada el text pla
		 */
		public DEBJ(String entrada) {
			super(Bdlex.DEBJ, entrada);
			llegeix();
		}
	}
	/**
	 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DEBJ').
	 */
	public static final class DMFC extends Formatador {
		
		/**
		 * Constructor de classe Formatador DMFC
		 * @param entrada el text pla
		 */
		public DMFC(String entrada) {
			super(Bdlex.DMFC, entrada);
			llegeix();
		}
	}
}