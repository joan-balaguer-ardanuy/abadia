package abadia;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class Etiqueta extends Marcatge {
	
	private static final long serialVersionUID = 4829806847577701304L;

	public enum Tipus {
		ETIQUETA,
		ATRIBUT,
		OBERTURA,
		TANCAMENT,
		SIMPLE,
		INSTRUCCIÓ,
		CDATA,
		COMENTARI
	}
	
	public abstract String obertura();
	public abstract String tancament();
	
	Atributs atributs;
	Tipus tipus;
	
	public Tipus tipus() {	
		return tipus;
	}
	public void tipus(Tipus tipus) {
		this.tipus = tipus;
	}
	
	public Etiqueta(String marca) {
		super(marca);
	}
	public Etiqueta(String marca, Atributs atributs) {
		super(marca);
		this.atributs = atributs;
	}
	public Etiqueta(String domini, String marca) {
		super(domini, marca);
	}
	public Etiqueta(String domini, String marca, Atributs atributs) {
		super(domini, marca);
		this.atributs = atributs;
	}
	
	@Override
	public String llegir() {
		return obertura() + super.llegir() + tancament();
	}
	
	@Override
	protected final void marcarInici() {
		escriure(obertura());
	}
	@Override
	protected final void marcarFinal() {
		if(atributs != null) {
			escriure(ESPAI);
			atributs.escriure(font());
		}
		escriure(tancament());
	}

	public void establirAtribut(Atribut propietat) {
		if(atributs == null) {
			atributs = new Atributs(propietat);
		} else atributs.add(propietat);
	}
	
	public boolean verificarFragment(String fragment) {
		char[] caràcters = fragment.toCharArray();
		for(char c : caràcters) {
			if(verificarCaràcter(c) == false)
				return false;
		}
		return true;
	}
	public boolean verificarCaràcter(char c) {
		switch (c) {
		case MENOR:
		case MAJOR:
		case BARRA:
		case INTERROGANT:
		case ESPAI:
		case DOS_PUNTS:
		case IGUAL:
			return false;
		default:
			return true;
		}
	}
	
	public static class Atributs
		extends Datagrama
			implements Set<Atribut>, Transcriptor {
		
		private static final long serialVersionUID = 7712542629346538397L;
		Set<Atribut> conjunt;
		
		public Atributs(Atribut element) {
			conjunt = new LinkedHashSet<Atribut>();
			conjunt.add(element);
		}
		
		@Override
		public String llegir() {
			StringBuilder codi = new StringBuilder();
			Iterator<Atribut> iterador = iterator();
			if(iterador.hasNext()) {
				codi.append(iterador.next().toString());
				while (iterador.hasNext()) {
					codi.append(ESPAI);
					codi.append(iterador.next().toString());
				}
			}
			return codi.toString();
		}
		
		@Override
		public void escriure(StringBuilder sortida) {
			Iterator<Atribut> iterador = iterator();
			if(iterador.hasNext()) {
				iterador.next().escriure(sortida);
				while (iterador.hasNext()) {
					sortida.append(ESPAI);
					iterador.next().escriure(sortida);
				}
			}
		}
		
		@Override
		public int size() {
			return conjunt.size();
		}
		@Override
		public boolean isEmpty() {
			return conjunt.isEmpty();
		}
		@Override
		public boolean contains(Object o) {
			return conjunt.contains(o);
		}
		@Override
		public Iterator<Atribut> iterator() {
			return conjunt.iterator();
		}
		@Override
		public Object[] toArray() {
			return conjunt.toArray();
		}
		@Override
		public <T> T[] toArray(T[] a) {
			return conjunt.toArray(a);
		}
		@Override
		public boolean add(Atribut e) {
			return conjunt.add(e);
		}
		@Override
		public boolean remove(Object o) {
			return conjunt.remove(o);
		}
		@SuppressWarnings("unlikely-arg-type")
		@Override
		public boolean containsAll(Collection<?> c) {
			return conjunt.contains(c);
		}
		@Override
		public boolean addAll(Collection<? extends Atribut> c) {
			return conjunt.addAll(c);
		}
		@Override
		public boolean removeAll(Collection<?> c) {
			return conjunt.removeAll(c);
		}
		@Override
		public boolean retainAll(Collection<?> c) {
			return conjunt.retainAll(c);
		}
		@Override
		public void clear() {
			conjunt.clear();	
		}
	}
}
