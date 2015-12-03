
<%@ page import="importer.RecommendationCsvImporter" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'recommendationCsvImporter.label', default: 'RecommendationCsvImporter')}" />
	<title><g:message code="default.index.label" args="[entityName]" /></title>
</head>

<body>

<section id="index-recommendationCsvImporter" class="first">
<g:if test="${flash.success}">
  <div class="alert alert-success" style="display: block">${flash.success}</div>
</g:if>
<g:if test="${flash.error}">
  <div class="alert alert-error" style="display: block">${flash.error}</div>
</g:if>
</section>

</body>

</html>
