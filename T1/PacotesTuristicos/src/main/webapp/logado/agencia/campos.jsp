<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table border="1">
	<caption>
		<c:choose>
			<c:when test="${pacote != null}">
				<fmt:message key="agency.trip.update"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="agency.trip.insert"/>
			</c:otherwise>
		</c:choose>
	</caption>
	<c:if test="${pacote != null}">
		<input type="hidden" name="id" value="<c:out value='${pacote.id}' />" />
	</c:if>
	<tr>
		<td><label for="nome"><fmt:message key="agency.table.name"/></label></td>
		<td><input type="text" id="nome" name="nome" size="45" required value="${pacote.nome}" /></td>
	</tr>
	<tr>
		<td><label for="cidade"><fmt:message key="agency.table.city"/></label></td>
		<td><input type="text" id="cidade" name="cidade" size="45" required value="${pacote.cidade}" /></td>
	</tr>
	<tr>
		<td><label for="estado"><fmt:message key="agency.table.state"/></label></td>
		<td><input type="text" id="estado" name="estado" size="45" value="${pacote.estado}" /></td>
	</tr>
	<tr>
		<td><label for="pais"><fmt:message key="agency.table.country"/></label></td>
		<td><input type="text" id="pais" name="pais" size="45" required value="${pacote.pais}" /></td>
	</tr>
	<tr>
		<td><label for="partida"><fmt:message key="agency.table.departure"/></label></td>
		<td><input type="date" id="partida" name="partida" size="45" required value="${pacote.partida}" /></td>
	</tr>
	<tr>
		<td><label for="duracao"><fmt:message key="agency.table.duration"/></label></td>
		<td><input type="number" id="duracao" name="duracao" size="45" required value="${pacote.duracao}" /></td>
	</tr>
	<tr>
		<td><label for="valor"><fmt:message key="agency.table.price"/></label></td>
		<td><input type="number" id="valor" name="valor" size="45" required value="${pacote.valor}" /></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit" value="<fmt:message key='save'/>" /></td>
	</tr>
</table>