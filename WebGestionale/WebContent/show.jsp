<%@page import="it.beije.utils.CSVutils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dipendenti Azienda</title>
</head>
<body>
<p><a title = "Torna alla home" href = "start.jsp"> <input type = "submit" value = "Home Page" /></a></p>
 <% out.print(CSVutils.selectFromDB()); %>
 <br/>
 <a title = "Inserisci nuovo dipendente" href = "form.jsp"> Insert new employee</a>
</body>
</html>