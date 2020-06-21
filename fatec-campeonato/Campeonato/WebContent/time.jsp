<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="edu.curso.entidade.Time, java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestão de Times</title>
</head>
<body>
	<% String msg = (String)session.getAttribute("MENSAGEM");
	   session.setAttribute("MENSAGEM", null);
	   List<Time> times = (List<Time>)session.getAttribute("TIMES");
	   if (msg == null) {
	   	   msg = "";
	   }%>
	   
	<h1>Gestão de Times</h1>
	<h2><%=msg%></h2>
	<form action="./TimeController" method="post">
		<table>
			<tr>
				<td><label for="txtId">ID</label></td>
				<td><input id="txtId" type="text" name="txtId"/></td>
			</tr>
			<tr>
				<td><label for="txtNome">Nome</label></td>
				<td><input id="txtNome" type="text" name="txtNome"/></td>
			</tr>
			<tr>
				<td><label for="txtModalidade">Modalidade</label></td>
				<td><input id="txtModalidade" type="text" name="txtModalidade"/></td>
			</tr>
			<tr>
				<td><label for="txtMascote">Mascote</label></td>
				<td><input id="txtMascote" type="text" name="txtMascote"/></td>
			</tr>			
			<tr>
				<td><input type="submit" value="Adicionar" name="cmd"/></td>
				<td><input type="submit" value="Pesquisar" name="cmd"/></td>
			</tr>
		</table>
	</form>
	<%if (times != null && times.size() > 0){ %>
		<table border="2">
			<tr>
				<td>Id</td>
				<td>Nome</td>
				<td>Mascote</td>
				<td>Modalidade</td>
			</tr>
			<% for (Time t : times) { %>
				<tr>
					<td><%=t.getId() %></td>
					<td><%=t.getNome() %></td>
					<td><%=t.getMascote() %></td>
					<td><%=t.getModalidade() %></td>
				</tr>
			<% } %>
		</table>
	<% } %>
</body>
</html>