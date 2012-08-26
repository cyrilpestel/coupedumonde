package riskyfoot.coupedumonde.service;

import java.util.Date;
import java.util.List;

import javax.jdo.Transaction;

import riskyfoot.coupedumonde.model.*;
import riskyfoot.coupedumonde.dao.ParticipantDAO;

public class ParticipantService {

	private ParticipantDAO dao;
	
	public void add(String pseudo) {
		Participant p = new Participant();
		p.setDate(new Date());
		p.setPseudo(pseudo);
		
		if(p!=null && p.getPseudo()!=null && p.getPseudo().trim().length()>0)
			dao.add(p);
	}
	
	public void addAll(String[] pseudoList) {
		for(String pseudo : pseudoList) {
			add(pseudo);
		}
	}
	
	public List<Participant> list() {

		List<Participant> lp = dao.list();
		
		return lp;
	}

	public ParticipantDAO getDao() {
		return dao;
	}

	public void setDao(ParticipantDAO dao) {
		this.dao = dao;
	}
}
