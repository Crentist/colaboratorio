
<%@ page import="bfcrowd.Project" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'project.label', default: 'Project')}" />
	<title><g:message code="bfcrowd.label.project.list.title"/></title>
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
<section id="list-project" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
				<g:sortableColumn property="name" title="${message(code: 'bfcrowd.label.researcher.createProject.name', default: 'Name')}" />
			</tr>
		</thead>
		<tbody>
		<g:each in="${projectInstanceList}" status="i" var="projectInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">	
				<td><g:link action="show" id="${projectInstance.id}">${fieldValue(bean: projectInstance, field: "name")}</g:link></td>
			</tr>
		</g:each>
		</tbody>
	</table>
	<div>
		<bs:paginate total="${projectInstanceCount}" />
	</div>
</section>

</body>

</html>
