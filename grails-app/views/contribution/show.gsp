
<%@ page import="bfcrowd.Contribution" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'contribution.label', default: 'Contribution')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>

<body>

<section id="show-contribution" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="contribution.date.label" default="Date" /></td>
				
				<td valign="top" class="value"><g:formatDate date="${contributionInstance?.date}" /></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="contribution.recomendation.label" default="Recomendation" /></td>
				
				<td valign="top" class="value"><g:link controller="task" action="show" id="${contributionInstance?.recomendation?.id}">${contributionInstance?.recomendation?.encodeAsHTML()}</g:link></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="contribution.state.label" default="State" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: contributionInstance, field: "state")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="contribution.text.label" default="Text" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: contributionInstance, field: "text")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="contribution.user.label" default="User" /></td>
				
				<td valign="top" class="value"><g:link controller="user" action="show" id="${contributionInstance?.user?.id}">${contributionInstance?.user?.encodeAsHTML()}</g:link></td>
				
			</tr>
		
		</tbody>
	</table>
</section>

</body>

</html>
