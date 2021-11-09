<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
	<fmt:bundle basename="message">
		<head>
			<title><fmt:message key="admin.insert"/></title>
		</head>
		<body>
			<%
				String contextPath = request.getContextPath().replace("/", "");
			%>
			<a href="/<%=contextPath%>/index.jsp"><fmt:message key="homepage"/></a><br/>
			<a href="/<%=contextPath%>/admin"><fmt:message key="admin.title"/></a><br/>
			<a href="/<%=contextPath%>/logout/logout"><fmt:message key="logout"/></a>
			<div align="center">
				<c:choose>
					<c:when test="${!empty cliente && cliente == null || usuario.equals('cliente')}">
						<form action="inserirCliente" method="post">
							<%@include file="camposCliente.jsp"%>
						</form>
					</c:when>
					<c:when test="${!empty cliente && cliente != null || usuario.equals('cliente')}">
						<form action="atualizarCliente" method="post">
							<%@include file="camposCliente.jsp"%>
						</form>
					</c:when>
					<c:when test="${empty cliente && agencia == null}">
						<form action="inserirAgencia" method="post">
							<%@include file="camposAgencia.jsp"%>
						</form>
					</c:when>
					<c:otherwise>
						<form action="atualizarAgencia" method="post">
							<%@include file="camposAgencia.jsp"%>
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