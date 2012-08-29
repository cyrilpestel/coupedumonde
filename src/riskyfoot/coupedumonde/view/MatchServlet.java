package riskyfoot.coupedumonde.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import riskyfoot.Riskyfoot;
import riskyfoot.coupedumonde.dao.RiskyfootDAO;
import riskyfoot.coupedumonde.model.MancheRiskyfoot;
import riskyfoot.coupedumonde.model.Match;
import riskyfoot.coupedumonde.service.MatchService;

public class MatchServlet extends HttpServlet {
	
	private MatchService matchService;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		Riskyfoot rf = new Riskyfoot();
		rf.setBaseURL("www.riskyfoot.fr"); 
//		rf.setBaseURL("localhost");
		
		RiskyfootDAO dao = new RiskyfootDAO(rf);
		
		matchService = new MatchService(dao);

	}
	
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
		
		String mancheLigue1 = req.getParameter("manche1")!=null?req.getParameter("manche1"):"1";
		String mancheEurope = req.getParameter("manche2")!=null?req.getParameter("manche2"):"1";
		
		String participant1 = req.getParameter("participant1");
		String participant2 = req.getParameter("participant2");
		
		List<MancheRiskyfoot> manches = new ArrayList<MancheRiskyfoot>();
		MancheRiskyfoot mLigue1 = new MancheRiskyfoot("","l1",mancheLigue1);
		MancheRiskyfoot mEurope = new MancheRiskyfoot("","aeu", mancheEurope);
		
		manches.add(mLigue1);
		manches.add(mEurope);
		
		Match m = matchService.match(manches, participant1, participant2);
		
		resp.getWriter().println(m.getParticipant1()+" "+m.getButs1()+" - "+m.getButs2()+" "+m.getParticipant2());
		resp.getWriter().close();
	}
}
