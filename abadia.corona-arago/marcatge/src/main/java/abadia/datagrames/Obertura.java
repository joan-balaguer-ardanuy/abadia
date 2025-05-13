package abadia.datagrames;

import abadia.Etiqueta;

public class Obertura extends Etiqueta {
	
	private static final long serialVersionUID = 2192568594436122818L;
	
	@Override
	public String obertura() {
		return Character.toString(MENOR);
	}
	@Override
	public String tancament() {
		return Character.toString(MAJOR);
	}
	
	public Obertura(String marca) {
		super(marca);
	}
	public Obertura(String domini, String marca) {
		super(domini, marca);
	}
	public Obertura(String marca, Atributs atributs) {
		super(marca, atributs);
	}
	public Obertura(Atributs atributs, String espai, String marca) {
		super(marca, espai, atributs);
	}
}