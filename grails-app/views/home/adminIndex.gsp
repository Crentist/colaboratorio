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

<div class="row-fluid">

	<div class="span8">
		<!-- Proyectos del usuario & resto de proyectos a los cuales unirse (mockup vista de la página de inicio del colaborador, lado izquierdo) -->
		
		<h3> <g:message code="bfcrowd.label.admin.index.heading"/></h3>
			<div id="desc">
				<g:message code="bfcrowd.label.admin.index.description"/>
			</div>
			<br>
		<g:link class="btn btn-primary" controller="user" action="list"> <g:message code="bfcrowd.label.admin.index.go" />  </g:link>
	</div>
	
	<div class="span4">
	
	</div>

</div>

</body>

</html>