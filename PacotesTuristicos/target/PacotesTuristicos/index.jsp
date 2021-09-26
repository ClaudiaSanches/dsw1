<!-- Página inicial:
	 Apresenta os pacotes turísticos disponíveis (não requer autenticação) 
	 e um link para a página de login. 
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
	<fmt:bundle basename="message">
		<head>
			<title><fmt:message key="homepage.title"/></title>
		</head>
		<body>
			<a href="login.jsp"><fmt:message key="homepage.login"/></a>
			<a href="listaPacotes.jsp">Listar pacotes</a>
		</body>
	</fmt:bundle>
</html>
