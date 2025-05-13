package abadia.datagrames;

import abadia.Etiqueta;

public class Simple extends Etiqueta {

	private static final long serialVersionUID = -9011431431538395643L;
	
	@Override
	public String obertura() {
		return Character.toString(MENOR);
	}
	@Override
	public String tancament() {
		return Character.toString(BARRA) + Character.toString(MAJOR);
	}
	
	public Simple(String marca) {
		super(marca);
	}
	public Simple(String marca, Atributs atributs) {
		super(marca, atributs);
	}
	public Simple(String domini, String marca) {
		super(domini, marca);
	}
	public Simple(String domini, String marca, Atributs atributs) {
		super(domini, marca, atributs);
	}
}
