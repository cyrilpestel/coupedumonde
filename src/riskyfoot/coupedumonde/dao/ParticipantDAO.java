package riskyfoot.coupedumonde.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import org.datanucleus.store.query.QueryResult;

import riskyfoot.coupedumonde.model.Participant;

public class ParticipantDAO {

	private PersistenceManager pm;
	
	public ParticipantDAO(PersistenceManager pm) {
		this.pm = pm;
	}
	
	
	public void add(Participant p) {
		pm.makePersistent(p);
	}
	
	public List<Participant> list() {
		String query = "SELECT FROM " + Participant.class.getName()+" ORDER BY date ASC";
//		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		List<Participant> lp = new ArrayList<Participant>();
//		try {
			List<Participant> result = (List<Participant>) pm.newQuery(query).execute();
			
			for(Participant p : result) {
				lp.add(p);
			}
//		} finally {
//			pm.close();
//		}
		return lp;
	}
	
	public Participant get(String pseudo) {
		String query = "SELECT FROM " + Participant.class.getName() +" WHERE pseudo == "+pseudo;
//		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Participant p = null;
//		try {
			
			QueryResult o =  (QueryResult) pm.newQuery(query).execute();
			
			System.out.println("o "+o.isEmpty()+" "+o.getClass());
//		} finally {
//			pm.close();
//		}

		return p;
	}
	
	public boolean exists(String pseudo) {
		String query = "SELECT FROM " + Participant.class.getName() +" WHERE pseudo == '"+pseudo+"'";
//		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		boolean exists = false;
		QueryResult result = null;
//		try {
			
			result =  (QueryResult) pm.newQuery(query).execute();
			
//			System.out.println("result "+result);
			
			if(result!=null && !result.isEmpty()) {
//				System.out.println("result pas vide = "+result.size());
				exists = true;
			}
//		} finally {
//			pm.close();
//		}

		return exists;
	}

	public PersistenceManager getPm() {
		return pm;
	}

	public void setPm(PersistenceManager pm) {
		this.pm = pm;
	}
}
