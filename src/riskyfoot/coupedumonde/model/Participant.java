package riskyfoot.coupedumonde.model;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;


@PersistenceCapable
public class Participant {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

	@Persistent
	private Date date;
	@Persistent
	private String pseudo;
	@Persistent
	private Integer classementRiskyfoot;
	@Persistent
	private String equipe;
	
	public Key getKey() {
		return key;
	}
	public void setKey(Key key) {
		this.key = key;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getEquipe() {
		return equipe;
	}
	public void setEquipe(String equipe) {
		this.equipe = equipe;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getClassementRiskyfoot() {
		return classementRiskyfoot;
	}
	public void setClassementRiskyfoot(Integer classementRiskyfoot) {
		this.classementRiskyfoot = classementRiskyfoot;
	}
}
