<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
	<fmt:bundle basename="message">
		<head>
			<title><fmt:message key="agency.trip.insert"/></title>
		</head>
		<body>
			<%
				String contextPath = request.getContextPath().replace("/", "");
			%>
			<a href="/<%=contextPath%>/index.jsp"><fmt:message key="homepage"/></a><br>
			<a href="/<%= contextPath %>/agencia"><fmt:message key="agency.title"/></a><br/>
			<a href="/<%=contextPath%>/logout/logout"><fmt:message key="logout"/></a>
			<div align="center">
				<c:choose>
					<c:when test="${pacote == null}">
						<form action="inserirPacote" method="post">
							<%@include file="campos.jsp"%>
						</form>
					</c:when>
					<c:otherwise>
						<form action="atualizarPacote" method="post">
							<%@include file="campos.jsp"%>
						</form>
					</c:otherwise>
				</c:choose>
			</div>
			<h6><fmt:message key="required.fields"/></h6>
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