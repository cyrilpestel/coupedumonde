package riskyfoot.coupedumonde.model;

import lombok.Data;

@Data
public class MancheRiskyfoot {

	private String saison;
	private String concours;
	private String numero;
	
	public MancheRiskyfoot(String s, String c, String n) {
		this.saison = s;
		this.concours = c;
		this.numero = n;
	}
}
