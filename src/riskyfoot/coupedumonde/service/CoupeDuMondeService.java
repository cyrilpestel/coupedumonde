package riskyfoot.coupedumonde.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;

import riskyfoot.coupedumonde.dao.RiskyfootDAO;
import riskyfoot.coupedumonde.model.*;

public class CoupeDuMondeService {

	private RiskyfootDAO dao;
	
	public CoupeDuMondeService(RiskyfootDAO dao) {
		this.dao = dao;
	}
	
	/**
	 * Classement des participants selon leur classement riskyfoot 2011/2012 et l'indice fifa.
	 * @param lp liste des participants
	 * @return liste des participants
	 */
	public List<Participant> classementParticipantFifa(List<Participant> lp) {

		// 1. Récupère le classement Riskyfoot de chaque participant
		List<ClassementRiskyfoot> lcr = dao.classementRiskyfoot("2012");
		
		if(lcr!=null) {
			for(Participant p : lp) {
				Integer cr = findClassementRiskyfootParticipant(p, lcr);
				if(cr!=null)
					p.setClassementRiskyfoot(cr);
			}
		} else {
			return null; //TODO throw exception
		}
		
		// Les participants ont leur classement riskyfoot ou null dans le cas ou le classement n'a pas été trouvé
		
		// 2. Tri en fonction du classement riskyfoot
		lp = triClassementRiskyfootParticipant(lp);
		
		// 3. Association Equipe Fifa
		if(lp.size()<=fifaEquipes.length) {
			int ip = 0;
			for(Participant p : lp) {
				p.setEquipe(fifaEquipes[ip++]);
			}
		} else {
			return null; // throw exception
		}
		
		return lp;
	}
	
	/**
	 * Recherche le classement Riskyfoot du participant.
	 * @param p le participant
	 * @param lcr le classement riskyfoot
	 * @return le classement
	 */
	protected Integer findClassementRiskyfootParticipant(Participant p, List<ClassementRiskyfoot> lcr) {
		for(ClassementRiskyfoot cr : lcr) {
			if(cr.getPseudo().toLowerCase().equals(p.getPseudo().trim().toLowerCase()))
				return Integer.valueOf(cr.getClassement());
		}
		return null;
	}
	
	/**
	 * Trie du classement des participants selon leur classement Riskyfoot 2011/2012.
	 * @param lp liste des participants
	 * @return liste des participants trié
	 */
	protected List<Participant> triClassementRiskyfootParticipant(List<Participant> lp) {
		
		Collections.sort(lp, new Comparator<Participant>() {
			public int compare(Participant p1, Participant p2) {
				if(p1.getClassementRiskyfoot()==null || p2.getClassementRiskyfoot()==null)
					return 1;
				return p1.getClassementRiskyfoot() - p2.getClassementRiskyfoot();
			}
		});
		
		return lp;
	}
	
	/**
	 * Liste des groupes de participants.
	 * @param lp
	 * @param nbGroupe
	 * @return
	 */
	public List<List<Participant>> repartition(List<Participant> lp, int nbGroupe) {
		List<List<Participant>> lg = new ArrayList<List<Participant>>(nbGroupe);
		
		// Index du groupe
		int ig = 0;
		for(Participant p : lp) {
			if(ig==nbGroupe)
				ig = 0;
			
			// Liste des participants du groupe 'ig'
			List<Participant> lpg = null; 
			if(lg.size()<=ig) {
				lpg = new ArrayList<Participant>();
				lg.add(lpg);
			} else {
				lpg = lg.get(ig);
			}
			
			lpg.add(p);
			
			ig++;
		}
		
		return lg;
	}

	/**
	 * Liste des equipes Fifa de la zone euro.
	 */
	public static final String[] fifaEquipes = new String[] { 
		"Espagne", "Allemagne", "Angleterre", "Portugal", "Italie", "Pays-Bas", "Croatie"
		, "Danemark", "Russie", "Grèce", "France", "Suède", "République tchèque", "Suisse"
		, "Norvège", "République d'Irlande", "Hongrie", "Turquie", "Bosnie-Herzégovine"
		, "Slovénie", "Serbie", "Pays de Galles", "Slovaquie", "Ukraine", "Ecosse", "Estonie"
		, "Monténégro", "Roumanie", "Belgique", "Pologne", "Arménie", "Autriche", "Lettonie"
		, "Finlande"
	};
	
	//// SETTERS & GETTERS \\\\
	
	public RiskyfootDAO getDao() {
		return dao;
	}

	public void setDao(RiskyfootDAO dao) {
		this.dao = dao;
	}
}
