<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table border="1">
	<caption>
		<c:choose>
			<c:when test="${cliente != null}">
				<fmt:message key="admin.update.cliente"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="admin.insert.client"/>
			</c:otherwise>
		</c:choose>
	</caption>
	<c:if test="${cliente != null}">
		<input type="hidden" name="id" value="<c:out value='${cliente.id}' />" />
	</c:if>
	<tr>
		<td><label for="nome"><fmt:message key="admin.table.name"/></label></td>
		<td><input type="text" id="nome" name="nome" size="45" required value="${cliente.nome}" /></td>
	</tr>
	<tr>
		<td><label for="email"><fmt:message key="admin.table.email"/></label></td>
		<td><input type="text" id="email" name="email" size="45" required value="${cliente.email}" /></td>
	</tr>
	<tr>
		<td><label for="senha"><fmt:message key="admin.table.pwd"/></label></td>
		<td><input type="password" id="senha" name="senha" size="45" required value="${cliente.senha}" /></td>
	</tr>
	<tr>
		<td><label for="cpf"><fmt:message key="admin.table.cpf"/></label></td>
		<td><input type="text" id="cpf" name="cpf" size="45" required value="${cliente.cpf}" /></td>
	</tr>
	<tr>
		<td><label for="telefone"><fmt:message key="admin.table.phone"/></label></td>
		<td><input type="text" id="telefone" name="telefone" size="45" required value="${cliente.telefone}" /></td>
	</tr>
	<tr>
		<td><label for="sexo"><fmt:message key="admin.table.gender"/></label></td>
		<td><input type="text" id="sexo" name="sexo" size="45" required value="${cliente.sexo}" /></td>
	</tr>
	<tr>
		<td><label for="nasc"><fmt:message key="admin.table.birth"/></label></td>
		<td><input type="date" id="nasc" name="nasc" size="45" required value="${cliente.nasc}" /></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit" value="<fmt:message key='save'/>" /></td>
	</tr>
</table>