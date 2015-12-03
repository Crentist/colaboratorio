
<%@ page import="bfcrowd.Task" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'recommendation.label', default: 'Recommendation')}" />
	<title><g:message code="default.index.label" args="[entityName]" /></title>
</head>

<body>

<section id="index-recommendation" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<g:sortableColumn property="checkboxMode" title="${message(code: 'recommendation.checkboxMode.label', default: 'Checkbox Mode')}" />
			
				<th><g:message code="recommendation.contribution.label" default="Contribution" /></th>
			
				<g:sortableColumn property="dateAssigned" title="${message(code: 'recommendation.dateAssigned.label', default: 'Date Assigned')}" />
			
				<g:sortableColumn property="fromPage" title="${message(code: 'recommendation.fromPage.label', default: 'From Page')}" />
			
				<g:sortableColumn property="instructions" title="${message(code: 'recommendation.instructions.label', default: 'Instructions')}" />
			
				<g:sortableColumn property="path" title="${message(code: 'recommendation.path.label', default: 'Path')}" />
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${recommendationInstanceList}" status="i" var="recommendationInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${recommendationInstance.id}">${fieldValue(bean: recommendationInstance, field: "checkboxMode")}</g:link></td>
			
				<td>${fieldValue(bean: recommendationInstance, field: "contribution")}</td>
			
				<td><g:formatDate date="${recommendationInstance.dateAssigned}" /></td>
			
				<td>${fieldValue(bean: recommendationInstance, field: "fromPage")}</td>
			
				<td>${fieldValue(bean: recommendationInstance, field: "instructions")}</td>
			
				<td>${fieldValue(bean: recommendationInstance, field: "path")}</td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div>
		<bs:paginate total="${recommendationInstanceCount}" />
	</div>
</section>

</body>

</html>
