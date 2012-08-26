package riskyfoot.coupedumonde;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import riskyfoot.Riskyfoot;
import riskyfoot.coupedumonde.dao.RiskyfootDAO;
import riskyfoot.coupedumonde.model.MancheClassement;

@SuppressWarnings("serial")
public class MancheClassementServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		Riskyfoot rf = new Riskyfoot();
		rf.setBaseURL("www.riskyfoot.fr"); 
//		rf.setBaseURL("localhost");
		
		RiskyfootDAO cdm = new RiskyfootDAO(rf);

		String saison = req.getParameter("saison")!=null?req.getParameter("saison"):""; // Courante
		String concours = req.getParameter("concours")!=null?req.getParameter("concours"):"l1";
		String manche = req.getParameter("manche")!=null?req.getParameter("manche"):"1";
		
		List<MancheClassement> lmc = cdm.mancheClassement(saison, concours, manche);
		
//		resp.setContentType("text/plain"); 
//
//		for(MancheClassement mc : lmc) {
//			int pts = (int) (Double.valueOf(mc.getClassementManchePlayer().getPoints()) / 8.00);
//			resp.getWriter().println(mc.getClassement()+"- "+mc.getPseudo()+" : "+pts+" buts");
//		}
		
		req.setAttribute("lmc", lmc);
		
		RequestDispatcher dispatcher = this.getServletContext().getNamedDispatcher("mancheClassement.jsp");
    	try {
    		dispatcher.forward(req, resp);
    	} catch (ServletException e) {
    		    			e.printStackTrace();
    		resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
    				"Can not redirect to JSP page cause : "+e.getMessage());
    	}
    	return;
	}
}
