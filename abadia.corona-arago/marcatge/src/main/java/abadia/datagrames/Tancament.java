package abadia.datagrames;

import abadia.Etiqueta;

public class Tancament extends Etiqueta {

	private static final long serialVersionUID = -2575234164149942878L;
	
	@Override
	public String obertura() {
		return new String(new char[] { MENOR, BARRA } );
	}
	@Override
	public String tancament() {
		return Character.toString(MAJOR);
	}
	
	public Tancament(String marca) {
		super(marca);
	}
	public Tancament(String domini, String marca) {
		super(domini, marca);
	}
}