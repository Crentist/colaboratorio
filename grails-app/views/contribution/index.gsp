
<%@ page import="bfcrowd.Contribution" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'contribution.label', default: 'Contribution')}" />
	<title><g:message code="default.index.label" args="[entityName]" /></title>
</head>

<body>

<section id="index-contribution" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<g:sortableColumn property="date" title="${message(code: 'contribution.date.label', default: 'Date')}" />
			
				<th><g:message code="contribution.recomendation.label" default="Recomendation" /></th>
			
				<g:sortableColumn property="state" title="${message(code: 'contribution.state.label', default: 'State')}" />
			
				<g:sortableColumn property="text" title="${message(code: 'contribution.text.label', default: 'Text')}" />
			
				<th><g:message code="contribution.user.label" default="User" /></th>
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${contributionInstanceList}" status="i" var="contributionInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${contributionInstance.id}">${fieldValue(bean: contributionInstance, field: "date")}</g:link></td>
			
				<td>${fieldValue(bean: contributionInstance, field: "recomendation")}</td>
			
				<td>${fieldValue(bean: contributionInstance, field: "state")}</td>
			
				<td>${fieldValue(bean: contributionInstance, field: "text")}</td>
			
				<td>${fieldValue(bean: contributionInstance, field: "user")}</td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div>
		<bs:paginate total="${contributionInstanceCount}" />
	</div>
</section>

</body>

</html>
