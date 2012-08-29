<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*,riskyfoot.coupedumonde.model.Participant" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Classement participant Fifa</title>
</head>
<body>

<br/>
Classement des participants : <br />
<%
List<Participant> cpf = (List<Participant>) request.getAttribute("cpf");

if(cpf!=null) {	
	int ip = 1;
	for(Participant p : cpf) {
		out.println(ip+"- "+p.getPseudo()+" : "+p.getClassementRiskyfoot()+" : "+p.getEquipe()+"<br />");
		ip++;
	}
}
%>
<br/>
Groupes possibles :<br/>
<p>
<%
List<List<Participant>> groupes = (List<List<Participant>>) request.getAttribute("groupes");

if(groupes!=null) {
	int ig = 1;
	for(List<Participant> lp : groupes) {
		out.println("Groupe "+ig+" :<br/>");
		
		int ip = 1;
		for(Participant p : lp) {
			out.println(ip+"- "+p.getPseudo()+" : "+p.getClassementRiskyfoot()+" : "+p.getEquipe()+"<br />");
			ip++;
		}
		
		out.println("<br/>");
		ig++;
	}
}

%>
</p>
</body>
</html>