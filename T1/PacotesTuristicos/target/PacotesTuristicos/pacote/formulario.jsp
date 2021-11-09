<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
	<!-- <fmt:bundle basename="message"> -->
		<head>
			<title>Cadastro</title>
		</head>
		<body>
			<%
				String contextPath = request.getContextPath().replace("/", "");
			%>
			<div align="center">
				<c:choose>
					<c:when test="${pacote == null}">
						<form action="inserirPacote" method="post">
							<%@include file="camposPacote.jsp"%>
						</form>
					</c:when>
					<c:otherwise>
						<form action="atualizarPacote" method="post">
							<%@include file="camposPacote.jsp"%>
						</form>
					</c:otherwise>
				</c:choose>
			</div>
			<c:if test="${!empty requestScope.mensagens}">
				<ul class="erro">
					<c:forEach items="${requestScope.mensagens}" var="mensagem">
						<li>${mensagem}</li>
					</c:forEach>
				</ul>
			</c:if>
		</body>
	<!-- </fmt:bundle> -->
</html>