<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
	<fmt:bundle basename="message">
		<head>
			<title><fmt:message key="purchases.list"/></title>
		</head>
		<body>
			<%
				String contextPath = request.getContextPath().replace("/", "");
			%>
			<a href="/<%=contextPath%>/index.jsp"><fmt:message key="homepage"/></a><br/>
			<a href="/<%=contextPath%>/cliente"><fmt:message key="client.title"/></a><br/>
			<a href="/<%=contextPath%>/logout/logout"><fmt:message key="logout"/></a>
			<div align="center">
				<h1><fmt:message key="client.trip.buy"/></h1>
				<h2><a href="lista"><fmt:message key="client.trip.list"/></a></h2>
			</div>
			<div align="center">
				<form action="insercao" method="post">
					<%@include file="campos.jsp"%>
				</form>
			</div>
			<c:if test="${!empty requestScope.mensagens}">
				<ul class="erro">
					<c:forEach items="${requestScope.mensagens}" var="mensagem">
						<li>${mensagem}</li>
					</c:forEach>
				</ul>
			</c:if>
		</body>
	</fmt:bundle>
</html>