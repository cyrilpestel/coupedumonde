<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*,riskyfoot.coupedumonde.model.MancheClassement" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Match - Coupe du monde</title>
</head>
<body>

<FORM action="match" method="GET"/>

<LABEL for="manche1">Choix de la manche de Ligue 1 :</LABEL>
<SELECT name="manche1">
	<OPTION value="1" selected="selected">Journée 1</OPTION>
	<OPTION value="2">Journée 2</OPTION>
	<OPTION value="3">Journée 3</OPTION>
</SELECT>
<br/>

<LABEL for="manche2">Choix de la manche d'Europe :</LABEL>
<SELECT name="manche2">
	<OPTION value="" selected="selected">Journée 1</OPTION>
	<OPTION value="2012">Journée 2</OPTION>
	<OPTION value="2011">Journée 3</OPTION>
</SELECT>
<br/>

<input type="text" name="participant1" /> vs <input type="text" name="participant2" />

</FORM>


</body>
</html>