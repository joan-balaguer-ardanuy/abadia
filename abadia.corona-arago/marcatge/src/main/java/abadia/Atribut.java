package abadia;

public class Atribut extends Marcatge {

	private static final long serialVersionUID = 7286719804317032809L;
	String valor;
	
	public String valor() {
		return this.valor;
	}
	public String valor(String valor) {
		String antic = this.valor;
		this.valor = valor;   
		return antic;
	}
	
	public Atribut(String marca) {
		super(marca);
	}
	public Atribut(String marca, String valor) {
		super(marca);
		this.valor = valor;
	}
	public Atribut(String domini, String marca, String valor) {
		super(domini, marca);
		this.valor = valor;
	}
	
	@Override
	public String llegir() {
		StringBuilder sortida = new StringBuilder(super.llegir());
		sortida.append(IGUAL);
		sortida.append(COMETES);
		sortida.append(valor());
		sortida.append(COMETES);
		return sortida.toString();
	}
	@Override
	protected void marcarInici() {
		// no-res
	}
	@Override
	protected void marcarFinal() {
		escriure(IGUAL);
		escriure(COMETES);
		escriure(valor());
		escriure(COMETES);
	}
}