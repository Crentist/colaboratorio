
<%@ page import="bfcrowd.Project" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'project.label', default: 'Project')}" />
	<title><g:message code="bfcrowd.label.researcher.showProject.title"/></title>
</head>

<body>
<ul id="Menu" class="nav nav-pills margin-top-small">
	
	<li class="${ params.action == "list" ? 'active' : '' }">
		<g:link action="list"><i class="glyphicon glyphicon-th-list"></i> <g:message code="bfcrowd.label.showProject.navbar.list"/></g:link>
	</li>
	<li class="${ params.action == "create" ? 'active' : '' }">
		<g:link action="create"><i class="glyphicon glyphicon-plus"></i> <g:message code="bfcrowd.label.showProject.navbar.create"/></g:link>
	</li>
	
	<g:if test="${ params.action == 'show' || params.action == 'edit' }">
		<!-- the item is an object (not a list) -->
		<li class="${ params.action == "edit" ? 'active' : '' }">
			<g:link action="edit" id="${params.id}"><i class="glyphicon glyphicon-pencil"></i> <g:message code="bfcrowd.label.showProject.navbar.edit"/></g:link>
		</li>
		<li class="">
			<g:render template="/_common/modals/deleteTextLink"/>
		</li>
	</g:if>
	
</ul> 
<section id="show-project" class="first">

	<table class="table">
		<tbody>

			<tr class="prop">
				<td valign="top" class="name"><g:message code="bfcrowd.label.researcher.createProject.name" default="Name" /></td>
				<td valign="top" class="value">${fieldValue(bean: projectInstance, field: "name")}</td>	
			</tr>
			
			<tr class="prop">
				<td valign="top" class="description"><g:message code="bfcrowd.label.researcher.createProject.description" default="Description" /></td>
				<td valign="top" class="value">${fieldValue(bean: projectInstance, field: "description")}</td>	
			</tr>
			
			<tr class="prop">
				<td valign="top" class="logo"><g:message code="bfcrowd.label.researcher.createProject.logo" default="Logo" /></td>
				<td valign="top" class="value">
					<g:if test="${fieldValue(bean: projectInstance, field: "logo")}">
					  <img class="logo" src="${createLink(controller:'project', action:'logo_image', id:projectInstance.ident())}" />
					</g:if>
				</td>
			</tr>
			
			<tr class="prop">
				<td valign="top" class="xpValue"><g:message code="bfcrowd.label.researcher.createProject.xpValue" default="XP Value" /></td>
				<td valign="top" class="value">${projectInstance.xpValue}</td>	
			</tr>
			
			<tr class="prop">
				<td valign="top" class="bonusXP"><g:message code="bfcrowd.label.researcher.createProject.bonusXP" default="Bonus XP" /></td>
				<td valign="top" class="value">${fieldValue(bean: projectInstance, field: "bonusXP")}</td>
			</tr>
			
			<tr class="prop">
				<td valign="top" class="requiredForBonus"><g:message code="bfcrowd.label.researcher.createProject.requiredForBonus" default="Required For Bonus" /></td>
				<td valign="top" class="value">${fieldValue(bean: projectInstance, field: "requiredForBonus")}</td>
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="bfcrowd.label.researcher.showProject.recommendations" default="Recommendations" /></td>
				<td valign="top" style="text-align: left;" class="value">
					<ul>
					<g:each in="${projectInstance?.recommendations}" var="r">
						<li><g:link controller="task" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
					</g:each>
					</ul>
				</td>
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="bfcrowd.label.researcher.showProject.collaborators" default="Collaborators" /></td>
				<td valign="top" style="text-align: left;" class="value">
					<ul>
					<g:each in="${projectInstance?.users}" var="u">
						<li><g:link controller="user" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></li>
					</g:each>
					</ul>
				</td>
			</tr>
			
			<tr class="prop">
				<td valign="top" class="name"><g:message code="bfcrowd.label.researcher.showProject.researchers" default="Researchers" /></td>
				<td valign="top" style="text-align: left;" class="value">
					<ul>
					<g:each in="${projectInstance?.owners}" var="u">
						<li><g:link controller="user" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></li>
					</g:each>
					</ul>
				</td>
			</tr>			
		
		</tbody>
	</table>
</section>

</body>

</html>
