
<%@ page import="importer.RecommendationCsvImporter" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'recommendationCsvImporter.label', default: 'RecommendationCsvImporter')}" />
	<title><g:message code="default.index.label" args="[entityName]" /></title>
</head>

<body>

<div class="tabbable tabs-left">
  <ul class="nav nav-tabs">
  <li class="active"><a href="#lA" data-toggle="tab">1. <g:message code="bfcrowd.importer.instructive.label" /></a></li>
  <li><a href="#lB" data-toggle="tab">2. <g:message code="bfcrowd.importer.upload.label" /></a></li>
  </ul>
  <div class="tab-content">


<div class="tab-pane active" id="lA">
<section id="index-recommendationCsvImporter-instruct" class="first">
<p> <g:message code="bfcrowd.importer.instructive.part1" /> </p> 
<br><br>
<p class="text-center lead"><g:message code="bfcrowd.importer.instructive.tableexample" /></p>
<p> <g:message code="bfcrowd.importer.instructive.part2" /> </p>
<table class="table">
  <thead>
    <tr>
      <th>Property</th>
      <th>Path</th>
      <th>From Page</th>
      <th>To Page</th>
      <th>Instructions</th>
      <th>Checkbox Mode</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>P1property1</td>
      <td>P1path1</td>
      <td>P1fromPage1</td>
      <td>P1toPage1</td>
      <td>P1instructions1</td>
      <td>Checkbox</td>
    </tr>
    <tr>
      <td>P1property2</td>
      <td>P1path2</td>
      <td>P1fromPage2</td>
      <td>P1toPage2</td>
      <td>P1instructions2</td>
      <td>Checkbox</td>
    </tr>
    <tr>
      <td>P1property3</td>
      <td>P1path3</td>
      <td>P1fromPage3</td>
      <td>P1toPage3</td>
      <td>P1instructions3</td>
      <td>Checkbox</td>
    </tr>
    <tr>
      <td>P1property4</td>
      <td>P1path4</td>
      <td>P1fromPage4</td>
      <td>P1toPage4</td>
      <td>P1instructions4</td>
      <td>Checkbox</td>
    </tr>
    <tr>
      <td>P2property1</td>
      <td>P2path1</td>
      <td>P2fromPage1</td>
      <td>P2toPage1</td>
      <td>P2instructions1</td>
      <td>Radio</td>
    </tr>
    <tr>
      <td>P2property2</td>
      <td>P2path2</td>
      <td>P2fromPage2</td>
      <td>P2toPage2</td>
      <td>P2instructions2</td>
      <td>Radio</td>
    </tr>
    <tr>
      <td>P2property3</td>
      <td>P2path3</td>
      <td>P2fromPage3</td>
      <td>P2toPage3</td>
      <td>P2instructions3</td>
      <td>Radio</td>
    </tr>
    <tr>
      <td>P3property1</td>
      <td>P3path1</td>
      <td>P3fromPage1</td>
      <td>P3toPage1</td>
      <td>P3instructions1</td>
      <td>Radio</td>
    </tr>
    <tr>
      <td>P2property2</td>
      <td>P2path2</td>
      <td>P2fromPage2</td>
      <td>P2toPage2</td>
      <td>P2instructions2</td>
      <td>Radio</td>
    </tr>
  </tbody>
</table>

<br>
<div class="container">
<p class="text-center lead"><g:message code="bfcrowd.importer.instructive.csvexample" /></p>
<p><g:message code="bfcrowd.importer.instructive.part3" /></p>
<br>
<p>P1property1;P1path1;P1fromPage1;P1toPage1;P1instructions1;Checkbox<br>
P1property2;P1path2;P1fromPage2;P1toPage2;P1instructions2;Checkbox<br>
P1property3;P1path3;P1fromPage3;P1toPage3;P1instructions3;Checkbox<br>
P1property4;P1path4;P1fromPage4;P1toPage4;P1instructions4;Checkbox<br>
P2property1;P2path1;P2fromPage1;P2toPage1;P2instructions1;Radio<br>
P2property2;P2path2;P2fromPage2;P2toPage2;P2instructions2;Radio<br>
P2property3;P2path3;P2fromPage3;P2toPage3;P2instructions3;Radio<br>
P3property1;P3path1;P3fromPage1;P3toPage1;P3instructions1;Radio<br>
P3property2;P3path2;P3fromPage2;P3toPage2;P3instructions2;Radio</p>
</div><br>
</section>
</div>


<div class="tab-pane" id="lB">
<section id="index-recommendationCsvImporter" class="first">
<g:message code="bfcrowd.importer.project.label" /><g:form action="importFile" enctype="multipart/form-data" useToken="true" >
<select name="projectId">
	<g:each var="pid" in="${ownProjects }">
  		<option value="${pid.id }">${pid.name }</option>
	</g:each>
</select>

<span class="button">                   
        <input type="file" name="filecsv"/>
        <input class="btn btn-primary" type="submit" class="upload" value="${message(code: 'bfcrowd.importer.button.upload.label', default: 'Upload')}"/>
</span>

</g:form>
</section>
</div>

  </div>
</div>



</body>

</html>
