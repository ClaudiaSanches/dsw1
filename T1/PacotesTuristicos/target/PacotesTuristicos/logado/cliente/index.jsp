<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
	<fmt:bundle basename="message">
		<head>
			<title><fmt:message key="client.title"/></title>
		</head>
		<body>
			<h2><fmt:message key="client.welcome"/>, ${sessionScope.usuarioLogado.nome}!!</h2>
			<%
				String contextPath = request.getContextPath().replace("/", "");
			%>
			<a href="/<%=contextPath%>/index.jsp"><fmt:message key="homepage"/></a><br>
			<a href="/<%=contextPath%>/compras"><fmt:message key="client.trip.list"/></a><br/>
			<a href="/<%=contextPath%>/logout/logout"><fmt:message key="logout"/></a>
		</body>
	</fmt:bundle>
</html>