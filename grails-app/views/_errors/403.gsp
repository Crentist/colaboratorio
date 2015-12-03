<html>
	<head>
		<title>403 - Forbidden!</title>
		<meta name="layout" content="kickstart" />
		<g:set var="layout_nomainmenu"		value="${true}" scope="request"/>
		<g:set var="layout_nosecondarymenu"	value="${true}" scope="request"/>
	</head>

<body>
	<content tag="header">
		<!-- Empty Header -->
	</content>
	
  	<section id="Error" class="">
		<div class="big-message">
			<div class="container">
				<h2>
					<g:message code="bfcrowd.label.error.forbidden.heading" />
				</h2>
				<p>
					<g:message code="bfcrowd.label.error.forbidden.description1" />
				</p>
				<p>
					<g:message code="bfcrowd.label.error.forbidden.description2" />
				</p>
				<div class="actions">
					<a href="${createLink(uri: '/')}" class="btn btn-large btn-primary">
						<i class="glyphicon glyphicon-chevron-left icon-white"></i>
						<g:message code="bfcrowd.label.error.forbidden.backToHome" />
						<!--<g:message code="error.button.backToHome"/>-->
					</a>
					<!--  <a href="${createLink(uri: '/contact')}" class="btn btn-large btn-success">
						<i class="glyphicon glyphicon-envelope"></i>
						<g:message code="error.button.contactSupport"/>
					</a>				-->	
				</div>
			</div>
		</div>
	</section>
  
  
  </body>
</html>