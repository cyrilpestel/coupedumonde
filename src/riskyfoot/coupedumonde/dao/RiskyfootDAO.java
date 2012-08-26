package riskyfoot.coupedumonde.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import riskyfoot.Riskyfoot;
import riskyfoot.coupedumonde.model.*;

public class RiskyfootDAO {

	private Riskyfoot riskyfoot;
	
	public RiskyfootDAO(Riskyfoot rf) {
		riskyfoot = rf;
	}
	
	public List<MancheClassement> mancheClassement(String saison, String concours, String manche) {
		String url = "http://"+riskyfoot.getBaseURL()+"/json/mancheClassement.php?saison="+saison+"&concours="+concours+"&manche="+manche;

		ObjectMapper mapper = new ObjectMapper();
		try {
		
			return mapper.readValue(new URL(url), new TypeReference<List<MancheClassement>>() {});

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<ClassementRiskyfoot> classementRiskyfoot(String saison) {
		
		String url = "http://"+riskyfoot.getBaseURL()+"/json/classement.php?saison="+saison;

		ObjectMapper mapper = new ObjectMapper();
		try {
		
			return mapper.readValue(new URL(url), new TypeReference<List<ClassementRiskyfoot>>() {});

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
//		Hashtable<Integer, Participant> lpr = new Hashtable<Integer, Participant>();
//		int ip = 1;
//		for(Participant p : lp) {
//			int ipr = ip * ir;
//			
//			lpr.put(ipr, p);
//			
//			ip++;
//		}
		
		return null;
	}
	


	public static void main(String[] args) {
		
		Riskyfoot rf = new Riskyfoot();
		rf.setBaseURL("www.riskyfoot.fr");
		
		RiskyfootDAO cdm = new RiskyfootDAO(rf);

		String saison = "2012"; // Courante
		String concours = "l1";
		String manche = "1";
//		cdm.classementManche(saison, concours, manche);
//		List<MancheClassement> lmc = cdm.mancheClassement(saison, concours, manche);
//		
//		
//		for(MancheClassement mc : lmc) {
//			System.out.println(mc.getClassement()+"- "+mc.getPseudo());
//		}
		

		List<ClassementRiskyfoot> cr = cdm.classementRiskyfoot(saison);
		

		for(ClassementRiskyfoot p : cr) {
			System.out.println(p.getClassement()+"- "+p.getPseudo());
		}
	}
}
