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

</body>
</html>