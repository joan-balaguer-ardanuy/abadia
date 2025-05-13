package abadia.entrades;

import abadia.Datagrama;
import abadia.Formatador;
import abadia.utensilis.Textos;

/**
 * Font del text pla: [https://bdlex.iec.cat/] - 2025 dC<br/>
 * <br/>
 * <tt><big>Tractament de informació proporcionada per BDLEX:</big><br/>
 * <big>=================================================</big><br/>
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
public class Bdlex extends Formatador {
	
	/**
	 * La font 
	 */
	private String font;
	
	/**
	 * Obté el Bdlex.DICCIONARI: DIEC1, DGFP....
	 * @return el Bdlex.DICCIONARI establert
	 */
	public String llegirFont() {
		return font;
	}
	/**
	 * Estableix el Bdlex.DICCIONARI: DIEC1, DGFP....
	 */
	public void escriureFont(String font) {
		this.font = "\n" + font;
	}
	
	@Override
	public String toString() {
		return extreureDocument();
	}
	
	public Bdlex(String font, String entrada) {
		super(entrada);
		this.font = font;
		// Pre-formatar diccionari abans d'establir en la font.
		// Eliminació de totes les tabulacions.
		escriureEntrada(llegirEntrada().replaceAll(String.valueOf(Datagrama.TABULACIÓ), ""));
		
		// Eliminació de totes les aparicions de "\nSUPL	".
		escriureEntrada(llegirEntrada().replaceAll("\nSUPL	", ""));
		
		escriureEntrada(llegirEntrada().replaceAll("<", "&lt;"));
		escriureEntrada(llegirEntrada().replaceAll(">", "&gt;"));
		
		// Inicia lectura.
		llegir();
	}
	
	public boolean llegirEntrada(String començament, int accepció, String entrada) {
		if(entrada.startsWith(començament)) {
			String clau = entrada.substring(començament.length(), entrada.indexOf(']')) + " (" + accepció + ")";
			String valor = entrada.substring(entrada.indexOf(']')+2, entrada.lastIndexOf(Datagrama.FINAL_DE_LÍNIA));
			this.put(clau, valor);
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 */
	public void llegir() {
		String[] entrades = retallarEntrades(llegirFont());
		for(String entrada : entrades) {
			if(!entrada.isEmpty()) {
				if(entrada.startsWith("[")) {
					String clau = entrada.substring(1, entrada.indexOf(']'));
					String valor = entrada.substring(entrada.indexOf(']')+2, entrada.lastIndexOf(Datagrama.FINAL_DE_LÍNIA));
					this.put(clau, valor);
				} else {
					for(int i = 1; i <= 10; i++) {
						if(llegirEntrada(i + ". [", i, entrada))
							break;
					}
				}
			}
		}
	}

	/**
	 * INSTITUT D\'ESTUDIS CATALANS. <i>Diccionari de la llengua catalana</i>.
	 * Barcelona: Edicions 62: Enciclopèdia Catalana: Publicacions de l\'Abadia de Montserrat;
	 * Palma de Mallorca: Editorial Moll; València: Edicions 3 i 4, 1995
	 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DIEC1').
	 */
	public static final String DIEC1 = "DIEC1";
	
	/**
	 * FERRER PASTOR, Francesc. <i>Diccionari general</i>. València: Impremta Fermar, 1985
	 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DGFP').
	 */
	public static final String DGFP = "DGFP";
	
	/**
	 * ALCOVER, Antoni M. i Francesc de B. MOLL. <i>Diccionari Català-Valencià-Balear</i>,
	 * Ciutat de Mallorca, 1926-1968. 10 v.
	 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DCVB').
	 */
	public static final String DCVB = "DCVB";
	
	/**
	 * FABRA, Pompeu. <i>Diccionari general de la llengua catalana</i>.
	 * Barcelona: Llibreria Catalònia, 1932
	 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DGLC').
	 */
	public static final String DGLC = "DGLC";
	
	/**
	 * VALLÈS, Emili. <i>Pal·las: diccionari català-castellà-francès:
	 * amb vocabularis castellà-català-francès-català</i>. Barcelona: Horta, [1927]
	 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DPCV').
	 */
	public static final String DPCV = "DPCV";
	/**
	 * MARTÍ I GADEA, Joaquim. <i>Novísimo diccionario general valenciano-castellano</i>.
	 * València. 1891
	 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DGMG').
	 */
	public static final String DGMG = "DGMG";
	
	/**
	 * AMENGUAL, Joan Josep. <i>Nuevo diccionario mallorquin-castellano-latin</i>.
	 * Palma: Imprenta y Librería de Juan Colomar, 1858. 2 v.
	 * 
	 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('NDMA').
	 */
	public static final String NDMA = "NDMA";
		
	/**
	 * ESCRIG, José. <i>Diccionario valenciano-castellano</i>.
	 * Valencia: Imprenta de J. Ferrer de Orga, 1851
	 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DVCE').
	 */
	public static final String DVCE = "DVCE";
	
	/**
	 * FIGUERA, Pere Antoni. <i>Diccionari mallorqui-castella</i>.
	 * Palma: Imprenta y Llibreria de Esteva Trias, 1840
	 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DMCF').
	 */
	public static final String DMCF = "DMCF";
	
	/**
	 * LABÈRNIA, Pere. <i>Diccionari de la llengua catalana: ab la correspondencia castellana y llatina</i>.
	 * Barcelona: Estampa dels hereus de la V. Pla, 1839 1840. 2 v.
	 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DLCL').
	 */
	public static final String DLCL = "DLCL";
	
	/**
	 * ESTEVE, Joaquim; BELLVITGES, Josep; JUGLÀ I FONT, Antoni. <i>Diccionario catalan-castellano-latino</i>.
	 * Barcelona: en la oficina de Tecla Pla viuda, 1803 1805. 2 v.
	 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DEBJ').
	 */
	public static final String DEBJ = "DEBJ"; 
	
	/**
	 * FEBRER I CARDONA, Antoni. &quot;Diccionári menorquí españól francês y llatí&quot;.
	 * [manuscrit, principis segle XIX]
	 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DMFC').
	 */
	public static final String DMFC = "DMFC";
	
	/**
	 * TARONGÍ I CORTÈS, Josep]. <i>Diccionari mallorquí-castellá</i> per un mallorquí,
	 * aficionat á sa seva llengua. Palma de Mallorca: Imprenta d\'en Bartomeu Rotger, 1878. [Inacabat]
	 * Concreció per a resultats obtinguts per distinció de font en BDLEX ('DMCT').
	 */
	public static final String DMCT = "DMCT";
	
	public static void main(String[] args) {
		try {
			// Extracció de DIEC1
			String entrada = Textos.llegir("/home/joan/git/abadia/abadia.corona-arago/diccionaris/català/registre/diec1.txt");
			Bdlex dic = new Bdlex(DIEC1, "\n" + entrada);
			dic.escriure();
			Textos.escriure("/home/joan/git/abadia/abadia.corona-arago/diccionaris/català/diec1.xml", dic.extreureDocument());
			
			entrada = Textos.llegir("/home/joan/git/abadia/abadia.corona-arago/diccionaris/català/registre/dgfp.txt");
			dic = new Bdlex(DGFP, "\n" + entrada);
			dic.escriure();
			Textos.escriure("/home/joan/git/abadia/abadia.corona-arago/diccionaris/català/dgfp.xml", dic.extreureDocument());
			
			entrada = Textos.llegir("/home/joan/git/abadia/abadia.corona-arago/diccionaris/català/registre/dcvb.txt");
			dic = new Bdlex(DCVB, "\n" + entrada);
			dic.escriure();
			Textos.escriure("/home/joan/git/abadia/abadia.corona-arago/diccionaris/català/dcvb.xml", dic.extreureDocument());
			
			entrada = Textos.llegir("/home/joan/git/abadia/abadia.corona-arago/diccionaris/català/registre/dglc.txt");
			dic = new Bdlex(DGLC, "\n" + entrada);
			dic.escriure();
			Textos.escriure("/home/joan/git/abadia/abadia.corona-arago/diccionaris/català/dglc.xml", dic.extreureDocument());

			entrada = Textos.llegir("/home/joan/git/abadia/abadia.corona-arago/diccionaris/català/registre/dpcv.txt");
			dic = new Bdlex(DPCV, "\n" + entrada);
			dic.escriure();
			Textos.escriure("/home/joan/git/abadia/abadia.corona-arago/diccionaris/català/dpcv.xml", dic.extreureDocument());

			entrada = Textos.llegir("/home/joan/git/abadia/abadia.corona-arago/diccionaris/català/registre/dgmg.txt");
			dic = new Bdlex(DGMG, "\n" + entrada);
			dic.escriure();
			Textos.escriure("/home/joan/git/abadia/abadia.corona-arago/diccionaris/català/dgmg.xml", dic.extreureDocument());

			entrada = Textos.llegir("/home/joan/git/abadia/abadia.corona-arago/diccionaris/català/registre/ndma.txt");
			dic = new Bdlex(NDMA, "\n" + entrada);
			dic.escriure();
			Textos.escriure("/home/joan/git/abadia/abadia.corona-arago/diccionaris/català/ndma.xml", dic.extreureDocument());

			entrada = Textos.llegir("/home/joan/git/abadia/abadia.corona-arago/diccionaris/català/registre/dvce.txt");
			dic = new Bdlex(DVCE, "\n" + entrada);
			dic.escriure();
			Textos.escriure("/home/joan/git/abadia/abadia.corona-arago/diccionaris/català/dvce.xml", dic.extreureDocument());

			entrada = Textos.llegir("/home/joan/git/abadia/abadia.corona-arago/diccionaris/català/registre/dmcf.txt");
			dic = new Bdlex(DMCF, "\n" + entrada);
			dic.escriure();
			Textos.escriure("/home/joan/git/abadia/abadia.corona-arago/diccionaris/català/dmfc.xml", dic.extreureDocument());

			entrada = Textos.llegir("/home/joan/git/abadia/abadia.corona-arago/diccionaris/català/registre/dlcl.txt");
			dic = new Bdlex(DLCL, "\n" + entrada);
			dic.escriure();
			Textos.escriure("/home/joan/git/abadia/abadia.corona-arago/diccionaris/català/dlcl.xml", dic.extreureDocument());

			entrada = Textos.llegir("/home/joan/git/abadia/abadia.corona-arago/diccionaris/català/registre/debj.txt");
			dic = new Bdlex(DEBJ, "\n" + entrada);
			dic.escriure();
			Textos.escriure("/home/joan/git/abadia/abadia.corona-arago/diccionaris/català/debj.xml", dic.extreureDocument());

			entrada = Textos.llegir("/home/joan/git/abadia/abadia.corona-arago/diccionaris/català/registre/dmfc.txt");
			dic = new Bdlex(DMFC, "\n" + entrada);
			dic.escriure();
			Textos.escriure("/home/joan/git/abadia/abadia.corona-arago/diccionaris/català/dmfc.xml", dic.extreureDocument());

			entrada = Textos.llegir("/home/joan/git/abadia/abadia.corona-arago/diccionaris/català/registre/dmct.txt");
			dic = new Bdlex(DMCT, "\n" + entrada);
			dic.escriure();
			Textos.escriure("/home/joan/git/abadia/abadia.corona-arago/diccionaris/català/dmct.xml", dic.extreureDocument());
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}