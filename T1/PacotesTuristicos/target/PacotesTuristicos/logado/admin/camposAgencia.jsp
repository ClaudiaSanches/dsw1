<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table border="1">
	<caption>
		<c:choose>
			<c:when test="${agencia != null}">
				<fmt:message key="admin.update.agency"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="admin.insert.agency"/>
			</c:otherwise>
		</c:choose>
	</caption>
	<c:if test="${agencia != null}">
		<input type="hidden" name="id" value="<c:out value='${agencia.id}' />" />
	</c:if>
	<tr>
		<td><label for="nome"><fmt:message key="admin.table.name"/></label></td>
		<td><input type="text" id="nome" name="nome" size="45" required value="${agencia.nome}" /></td>
	</tr>
	<tr>
		<td><label for="email"><fmt:message key="admin.table.email"/></label></td>
		<td><input type="text" id="email" name="email" size="45" required value="${agencia.email}" /></td>
	</tr>
	<tr>
		<td><label for="senha"><fmt:message key="admin.table.pwd"/></label></td>
		<td><input type="password" id="senha" name="senha" size="45" required value="${agencia.senha}" /></td>
	</tr>
	<tr>
		<td><label for="cnpj"><fmt:message key="admin.table.cnpj"/></label></td>
		<td><input type="text" id="cnpj" name="cnpj" size="45" required value="${agencia.cnpj}" /></td>
	</tr>
	<tr>
		<td><label for="descricao"><fmt:message key="admin.table.description"/></label></td>
		<td><input type="text" id="descricao" name="descricao" size="45" required value="${agencia.descricao}" /></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit" value="<fmt:message key='save'/>"/></td>
	</tr>
</table>