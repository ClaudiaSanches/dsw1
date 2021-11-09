<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
	<fmt:bundle basename="message">
		<head>
			<title><fmt:message key="agency.title"/></title>
		</head>
		<body>
			<h2><fmt:message key="agency.welcome"/>, ${sessionScope.usuarioLogado.nome}!!</h2>
			<%
				String contextPath = request.getContextPath().replace("/", "");
			%>
			<a href="/<%=contextPath%>/agencia/cadastroPacote"><fmt:message key="agency.insert"/></a><br/>
			<a href="/<%=contextPath%>/agencia/listaPacotesAgencia"><fmt:message key="agency.list.trip"/></a><br/>
			<a href="/<%=contextPath%>/index.jsp"><fmt:message key="homepage"/></a><br/>
			<a href="/<%=contextPath%>/logout/logout"><fmt:message key="logout"/></a>
		</body>
	</fmt:bundle>
</html>