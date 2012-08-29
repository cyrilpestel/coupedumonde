package riskyfoot.coupedumonde.view;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import riskyfoot.coupedumonde.dao.PMF;
import riskyfoot.coupedumonde.dao.ParticipantDAO;
import riskyfoot.coupedumonde.model.Participant;
import riskyfoot.coupedumonde.service.CoupeDuMondeService;
import riskyfoot.coupedumonde.service.ParticipantService;

@SuppressWarnings("serial")
public class ParticipantServlet extends HttpServlet {
	
	private PersistenceManager pmf;
	private ParticipantService participantService;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		pmf = PMF.get().getPersistenceManager();
		participantService = new ParticipantService();
		
		ParticipantDAO dao = new ParticipantDAO(pmf);
		
		participantService.setDao(dao);
	}
	
	public void destroy() {
		pmf.close();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		List<Participant> lp = participantService.list();
		
		req.setAttribute("lp", lp);
		
		RequestDispatcher dispatcher = this.getServletContext().getNamedDispatcher("participant.jsp");
    	try {
    		dispatcher.forward(req, resp);
    	} catch (ServletException e) {
    		    			e.printStackTrace();
    		resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
    				"Can not redirect to JSP page cause : "+e.getMessage());
    	}
    	return;
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String pseudo = req.getParameter("pseudo");
		
		String pseudoList = req.getParameter("pseudoList");
		
		if(pseudoList!=null) {
			String[] pseudos = pseudoList.split("\n");
			
			participantService.addAll(pseudos);
			
		} else if(pseudo!=null && !pseudo.trim().equals("")) {
			
			participantService.add(pseudo);

		}
		
		resp.sendRedirect("/participant");
	}
}
