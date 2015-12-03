
<%@ page import="bfcrowd.User" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'colaborator.label', default: 'Colaborator')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>

<body>

<section id="show-myProjects" class="first">
    <div id="joinedProjects">
        <g:render template="/colaborator/myProjects"/>
    </div>
</section>

</body>

</html>
