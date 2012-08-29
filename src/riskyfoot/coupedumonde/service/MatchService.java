package riskyfoot.coupedumonde.service;

import java.util.List;

import riskyfoot.coupedumonde.dao.RiskyfootDAO;
import riskyfoot.coupedumonde.model.MancheClassement;
import riskyfoot.coupedumonde.model.Match;
import riskyfoot.coupedumonde.model.MancheRiskyfoot;

public class MatchService {

	private RiskyfootDAO dao; // Classement des manches
	
	public MatchService(RiskyfootDAO dao) {
		this.dao = dao;
	}
	
	public Match match(List<MancheRiskyfoot> manches, String participant1, String participant2) {

		Match m = new Match();
		m.setParticipant1(participant1);
		m.setParticipant2(participant2);
		
		double pointsParticipant1=0.0, pointsParticipant2=0.0;
		
		for(MancheRiskyfoot manche : manches) {
			List<MancheClassement> lmc = dao.mancheClassement(manche.getSaison(), manche.getConcours(), manche.getNumero());
			
			for(MancheClassement mc : lmc) {
				if(mc.getPseudo().equals(participant1)) {
					pointsParticipant1 = pointsParticipant1 + Double.valueOf(mc.getClassementManchePlayer().getPoints());
				} else if(mc.getPseudo().equals(participant2)) {
					pointsParticipant2 = pointsParticipant2 + Double.valueOf(mc.getClassementManchePlayer().getPoints());
				}
			}
		}
		
		int butsParticipant1 = (int) (pointsParticipant1 / 8.00);
		int butsParticipant2 = (int) (pointsParticipant2 / 8.00);
		
		m.setButs1(butsParticipant1);
		m.setButs2(butsParticipant2);
		
		return m;
	}
}
