package riskyfoot.coupedumonde.view;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import riskyfoot.Riskyfoot;
import riskyfoot.coupedumonde.dao.PMF;
import riskyfoot.coupedumonde.dao.ParticipantDAO;
import riskyfoot.coupedumonde.dao.RiskyfootDAO;
import riskyfoot.coupedumonde.model.Participant;
import riskyfoot.coupedumonde.service.CoupeDuMondeService;
import riskyfoot.coupedumonde.service.ParticipantService;

public class ClassementParticipantFifaServlet extends HttpServlet {

	private PersistenceManager pmf;
	private ParticipantService participantService;
	private CoupeDuMondeService cdmService;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		pmf = PMF.get().getPersistenceManager();
		participantService = new ParticipantService();
		
		ParticipantDAO dao = new ParticipantDAO(pmf);
		
		participantService.setDao(dao);

		Riskyfoot rf = new Riskyfoot();
		rf.setBaseURL("www.riskyfoot.fr"); 
//		rf.setBaseURL("localhost");
		
		RiskyfootDAO cdm = new RiskyfootDAO(rf);

		cdmService = new CoupeDuMondeService(cdm);
	}

	public void destroy() {
		pmf.close();
	}
	

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		List<Participant> lp = participantService.list();

		List<Participant> cpf = cdmService.classementParticipantFifa(lp);

		req.setAttribute("cpf", cpf);
		
		if(cpf!=null) {
			List<List<Participant>> groupes = cdmService.repartition(cpf, 4);
			
			req.setAttribute("groupes", groupes);
		}
		
		RequestDispatcher dispatcher = this.getServletContext().getNamedDispatcher("classementParticipantFifa.jsp");
    	try {
    		dispatcher.forward(req, resp);
    	} catch (ServletException e) {
    		    			e.printStackTrace();
    		resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
    				"Can not redirect to JSP page cause : "+e.getMessage());
    	}
	}
}
