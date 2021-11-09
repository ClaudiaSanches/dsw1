<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
	<fmt:bundle basename="message">
		<head>
			<title><fmt:message key="admin.title"/></title>
		</head>
		<body>
			<h2><fmt:message key="admin.title"/></h2>
			<%
				String contextPath = request.getContextPath().replace("/", "");
			%>
			<a href="/<%=contextPath%>/admin/cadastroCliente"><fmt:message key="admin.insert.client.index"/></a><br/>
			<a href="/<%=contextPath%>/admin/cadastroAgencia"><fmt:message key="admin.insert.agency.index"/></a><br/>
			<a href="/<%=contextPath%>/admin/listaClientes"><fmt:message key="admin.list.client"/></a><br/>
			<a href="/<%=contextPath%>/admin/listaAgencias"><fmt:message key="admin.list.agency"/></a><br/>
			<a href="/<%=contextPath%>/index.jsp"><fmt:message key="homepage"/></a><br/>
			<a href="/<%=contextPath%>/logout/logout"><fmt:message key="logout"/></a>
		</body>
	</fmt:bundle>
</html>