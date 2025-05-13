package abadia.datagrames;

import abadia.Marcatge;
import abadia.Transcriptor;

public class Referència
	extends Marcatge implements Transcriptor {
	
	private static final long serialVersionUID = -4238469823394409651L;
	
	public Referència(String codi) {
		super(codi);
	}

	@Override
	protected void marcarInici() {
		escriure(AMPERSAND);
	}
	@Override
	protected void marcarFinal() {
		escriure(COMETES);
	}
	
	public static final Referència REF_MENOR = new Referència("lt");
	public static final Referència REF_MAJOR = new Referència("gt");
	public static final Referència REF_AMPERSAND = new Referència("amp");
	public static final Referència REF_ESPAI = new Referència("nbsp");
}