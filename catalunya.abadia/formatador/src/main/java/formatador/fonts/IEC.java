package formatador.fonts;

import java.util.Map;
import formatador.Entrada;
import formatador.Formatador;
import formatador.Text;

/**
 * 
 */
public class IEC {
	
	/**
	 * Font del text pla: [https://bdlex.iec.cat/]<br/>
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
	 * <li>El valor és tota la resta: fins a la següent aparició de "DIEC1	", és a dir:<br/>
	 * "a¹ [pl. as] f. Nom de la lletra a A. | no saber ni la a No saber ni els rudiments d'una cosa.<tt>'\n'</tt>"</li>
	 * </ul>
	 * <h1>Procés per línia:</h1>
	 * <tt>[inici de iteració]:<br/>
	 * &gt; CODI_FONT &gt;<br/>
	 * &nbsp;&nbsp;&gt; aparició de caràcter d'obertura '[' &gt;<br/>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&gt; CLAU &gt;<br/>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&gt; aparició de caràcter ']' &gt;<br/>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&gt; un espai blanc ' '&gt; <br/>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&gt; VALOR (fins darrera aparició de<br/>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'\n' des del final de la mateixa línia)&gt;<br/>
	 * :[fi de iteració]</tt><br/>
	 */
	public static abstract class BDLEX
		extends Formatador {

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
		
		/**
		 * La font 
		 */
		private String font;
		
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
		
		public BDLEX(String font, String entrada) {
			super(entrada);
			this.font = font;
			// Pre-formatar diccionari abans d'establir en la font.
			// Eliminació de totes les tabulacions.
			this.escriuEntrada(llegeixEntrada().replaceAll(String.valueOf(TABULACIÓ), ""));
			// Inicia lectura.
			llegeix();
		}
		
		/**
		 * 
		 */
		public void llegeix() { 
			String[] entrades = retallaEntrades(llegeixFont());
			for(String entrada : entrades) {
				if(entrada.startsWith(Entrada.CLAU_OBERTURA)) {
					String clau = entrada.substring(1, entrada.indexOf(Entrada.CLAU_TANCAMENT));
					String valor = entrada.substring(entrada.indexOf(Entrada.CLAU_TANCAMENT)+2, entrada.lastIndexOf(FI_DE_LÍNIA));
					this.put(clau, valor);
				}
			}
		}
		/**
		 * 
		 */
		public void escriu() {
			for(Map.Entry<String, String> entry : entrySet()) {
				escriu(entry.getKey(), entry.getValue());
			}
		}

		/**
		 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DIEC1').
		 */
		public static final class DIEC1 extends BDLEX {
			
			/**
			 * Constructor de classe Formatador DIEC1.
			 * @param entrada el text pla
			 */
			public DIEC1(String entrada) {
				super(DIEC1, entrada);
			}
		}
		/**
		 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DGFP').
		 */
		public static final class DGFP extends BDLEX {
			
			/**
			 * Constructor de classe Formatador DGFP
			 * @param entrada el text pla
			 */
			public DGFP(String entrada) {
				super(DGFP, entrada);
			}
		}
		/**
		 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DCVB').
		 */
		public static final class DCVB extends BDLEX {
			
			/**
			 * Constructor de classe Formatador DCVB
			 * @param entrada el text pla
			 */
			public DCVB(String entrada) {
				super(DCVB, entrada);
			}
		}
		/**
		 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DGLC').
		 */
		public static final class DGLC extends BDLEX {
			
			/**
			 * Constructor de classe Formatador DGLC
			 * @param entrada el text pla
			 */
			public DGLC(String entrada) {
				super(DGLC, entrada);
			}
		}
		/**
		 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DPCV').
		 */
		public static final class DPCV extends BDLEX {
			
			/**
			 * Constructor de classe Formatador DPCV
			 * @param entrada el text pla
			 */
			public DPCV(String entrada) {
				super(DPCV, entrada);
			}
		}
		/**
		 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DGMG').
		 */
		public static final class DGMG extends BDLEX {
			
			/**
			 * Constructor de classe Formatador DGMG
			 * @param entrada el text pla
			 */
			public DGMG(String entrada) {
				super(DGMG, entrada);
			}
		}
		/**
		 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('NDMA').
		 */
		public static final class NDMA extends BDLEX {
			
			/**
			 * Constructor de classe Formatador NDMA
			 * @param entrada el text pla
			 */
			public NDMA(String entrada) {
				super(NDMA, entrada);
			}
		}
		/**
		 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DVCE').
		 */
		public static final class DVCE extends BDLEX {
			
			/**
			 * Constructor de classe Formatador DVCE
			 * @param entrada el text pla
			 */
			public DVCE(String entrada) {
				super(DVCE, entrada);
			}
		}
		/**
		 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DMCF').
		 */
		public static final class DMCF extends BDLEX {
			
			/**
			 * Constructor de classe Formatador DMCF
			 * @param entrada el text pla
			 */
			public DMCF(String entrada) {
				super(DMCF, entrada);
			}
		}
		/**
		 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DLCL').
		 */
		public static final class DLCL extends BDLEX {
			
			/**
			 * Constructor de classe Formatador DLCL
			 * @param entrada el text pla
			 */
			public DLCL(String entrada) {
				super(DLCL, entrada);
			}
		}
		/**
		 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DEBJ').
		 */
		public static final class DEBJ extends BDLEX {
			
			/**
			 * Constructor de classe Formatador DEBJ
			 * @param entrada el text pla
			 */
			public DEBJ(String entrada) {
				super(DEBJ, entrada);
			}
		}
		/**
		 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DEBJ').
		 */
		public static final class DMFC extends BDLEX {
			
			/**
			 * Constructor de classe Formatador DMFC
			 * @param entrada el text pla
			 */
			public DMFC(String entrada) {
				super(DMFC, entrada);
			}
		}
	}

	public static void main(String[] args) {
		try {
			// Extracció de DIEC1
//			String entrada = Bdlex.llegeix("/home/joan/git/abadia/catalunya.abadia/llenguatge/català/registre/diec1.txt");
//			DIEC1 diec1 = new DIEC1(entrada);
//			diec1.escriu();
//			Bdlex.escriu("/home/joan/git/abadia/catalunya.abadia/llenguatge/català/diec1.xml", diec1.extreuDocument());
			// Extracció de DGFP
			String entrada = Text.llegeix("/home/joan/git/abadia/catalunya.abadia/llenguatge/català/registre/dgfp.txt");
			BDLEX dgfp = new BDLEX.DGFP(entrada);
			dgfp.escriu();
			Text.escriu("/home/joan/git/abadia/catalunya.abadia/llenguatge/català/català.dgfp.xml", dgfp.extreuDocument());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}