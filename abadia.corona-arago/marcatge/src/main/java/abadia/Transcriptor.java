package abadia;

import java.io.Serializable;

public interface Transcriptor extends Serializable {
	String llegir();
	void escriure(StringBuilder sortida);
	
	void escriure(char sortida);
	void escriure(String sortida);
	void escriureAbans(char sortida);
	void escriureAbans(String sortida);
}