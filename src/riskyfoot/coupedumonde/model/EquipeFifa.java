package riskyfoot.coupedumonde.model;

public class EquipeFifa {

	private String classement;
	private String equipe;
	
	public EquipeFifa(String classement, String equipe) {
		this.classement = classement;
		this.equipe = equipe;
	}
	
	public String getClassement() {
		return classement;
	}
	public void setClassement(String classement) {
		this.classement = classement;
	}
	public String getEquipe() {
		return equipe;
	}
	public void setEquipe(String equipe) {
		this.equipe = equipe;
	}
}
