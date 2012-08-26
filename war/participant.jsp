<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*,riskyfoot.coupedumonde.model.Participant" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Inscription</title>
</head>
<body>

<form method="POST" action="participant">
	<LABEL for="pseudo">Pseudo :</LABEL>
	<input name="pseudo" value="" />
	<br/>
	ou
	<br/>
	<textarea name="pseudoList" rows="3" cols="25"></textarea>
	<br/>
	<BUTTON type="submit" value="Rechercher">Ajouter</BUTTON>
</form>

<br/>
Liste des participants : <br />
<%
List<Participant> lp = (List<Participant>) request.getAttribute("lp");

	if(lp!=null) {	
		int ip = 1;
		for(Participant p : lp) {
			out.println(ip+"- "+p.getPseudo()+"<br />");
			ip++;
		}
	}
%>

</body>
</html>