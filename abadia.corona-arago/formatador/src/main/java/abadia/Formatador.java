package abadia;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public abstract class Formatador
	extends Processador
	implements java.util.Map<String, String> {

	/**
	 * La taula base
	 */
	java.util.Map<String,String> diccionari;

	public Formatador(String entrada) {
		super(entrada);
		diccionari = new LinkedHashMap<String, String>();
	}
	
	public abstract void llegir();
	
	/**
	 * 
	 */
	public void escriure() {
		for(Map.Entry<String, String> entry : entrySet()) {
			escriure(entry.getKey(), entry.getValue());
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