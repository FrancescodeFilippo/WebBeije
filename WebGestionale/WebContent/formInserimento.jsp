<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dipendente</title>
</head>
<body>


<form action = "Test" method = "post">
  Nome: <input type = "text" name = "nome">
  <br />
  Cognome: <input type = "text" name = "cognome" />
  <br/>
  Data di nascita: <input type = "text" name = "data_nascita">
  <br/>
  Luogo di nascita: <input type = "text" name = "luogo_nascita">
  <br/>
  Sesso: 
  <input type="radio" name="sesso" value ="M"> M
  <input type="radio" name="sesso" value = "F"> F
  <br/>
  Codice Fiscale: <input type = "text" name = "codice_fiscale">
  <br/>
  Telefono: <input type = "text" name = "telefono">
  <br/>
  Mail: <input type = "text" name = "mail">
  <br/>
  <input type = "submit" value = "Submit" />
</form>

<a href="start.jsp">Torna alla home</a>

</body>
</html>
