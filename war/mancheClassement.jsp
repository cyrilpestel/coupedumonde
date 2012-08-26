<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*,riskyfoot.coupedumonde.model.MancheClassement" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Classement de la manche</title>
</head>
<body>

<form method="GET" action="mancheClassement">
	<LABEL for="saison">Saison</LABEL>
	<SELECT name="saison">
		<OPTION value="" selected="selected">Courante</OPTION>
		<OPTION value="2012">2012</OPTION>
		<OPTION value="2011">2011</OPTION>
	</SELECT>
	<br/>
	
	<LABEL for="concours">Concours</LABEL>
	<SELECT name="concours">
		<OPTION value="l1" selected="selected">Ligue 1</OPTION>
		<OPTION value="aeu">Europe</OPTION>
	</SELECT>
	<br/>
	
	<LABEL>Manche</LABEL>
	<INPUT type="text" name="manche" value="1" />
	<br />
	<BUTTON type="submit" value="Rechercher">Afficher</BUTTON>
</form>

<%
List<MancheClassement> lmc = (List<MancheClassement>) request.getAttribute("lmc");

			//TODO
	if(lmc!=null) {		
		for(MancheClassement mc : lmc) {
			int pts = (int) (Double.valueOf(mc.getClassementManchePlayer().getPoints()) / 8.00);
			out.println(mc.getClassement()+"- "+mc.getPseudo()+" : "+pts+" buts<br/>");
		}
	}
%>

</body>
</html>
