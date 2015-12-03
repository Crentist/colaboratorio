<html>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="grails.plugin.nimble.core.UserBase"%>
<%@page import="grails.plugin.nimble.core.Role"%>
<%@page import="bfcrowd.Project"%>
<head>
	<title><g:message code="default.welcome.title" args="[meta(name:'app.name')]"/> </title>
	<meta name="layout" content="kickstart" />
</head>

<body>

<div class="container">

	<div class="span6">
		<h1><g:message code="bfcrowd.label.welcome"/></h1>
		<p>
			<g:message code="bfcrowd.description.index"/>
		</p>
	</div>
	
	<div class="span5">
		<h1><g:message code="bfcrowd.label.projectList"/></h1>
		<g:set var="allProjects" value="${Project.getAll()}" />
		<!-- Listar los proyectos disponibles dentro de recuadros o algo por el estilo -->
		<g:each var="proj" in="${allProjects}">
			<div class="show-component"> 
				<strong> ${proj.name } </strong>
				<p> ${proj.description } </p>
			</div>
			<br/>
		</g:each>
	</div>

</div>

</body>

</html>