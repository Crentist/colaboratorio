
<%@page import="bfcrowd.User"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="grails.plugin.nimble.core.UserBase"%>
<!DOCTYPE html>
<html>

<head>
<meta name="layout" content="kickstart" />
<g:set var="entityName"
	value="${message(code: 'colaborator.label', default: 'Colaborator')}" />
<title><g:message code="bfcrowd.label.collab.project.show" /></title>
<g:javascript src="autolinker.js" />
<g:javascript src="facebookLogin.js" />
</head>

<body>

	<div class="row-fluid">
		<div class="span8">
			<div class="show-component">
				<strong> ${project.name }
				</strong>
				<p>
					${project.description }
				</p>
			</div>
			<br />
			<div class="show-component">
				<g:if test="${recommendation}">
					<div class="show-component">
						<div id="instructions">
							<h4>
								<g:message code="bfcrowd.label.collab.project.instructions" />
							</h4>
							<p id="instruction">
								${recommendation.instructions }
							</p>
							<script>document.getElementById('instruction').innerHTML = Autolinker.link( "${recommendation.instructions.encodeAsHTML() }" );</script>
						</div>
					</div>
					<br />
					<div class="show-component">
						<h4>
							<g:message code="bfcrowd.label.collab.project.feedback" />
						</h4>
						<g:form controller="colaborator" action="project"
							params="${[id:recommendation.project.id]}"
							class="form-horizontal" role="form" class="row-fluid">
							<g:hiddenField name="recommendationId"
								value="${recommendation.id}" />
							<div class="span5">
								<ul id="feedbackOptions">
									<li><input type="${recommendation.checkboxMode }"
										name="state" value="Done" /> <g:message
											code="bfcrowd.label.collab.project.feedback.done" /></li>
									<li><input type="${recommendation.checkboxMode }"
										name="state" value="Moderated Page" /> <g:message
											code="bfcrowd.label.collab.project.feedback.moderated" /></li>
									<li><input type="${recommendation.checkboxMode }"
										name="state" value="Already Done" /> <g:message
											code="bfcrowd.label.collab.project.feedback.already" /></li>
								</ul>
							</div>
							<div class="span6">
								<g:textArea name="text" style="width:100%">
									<g:message code="bfcrowd.label.collab.project.comments" />
								</g:textArea>
							</div>


							<br>
							<div class="btn-group" style="float: right; padding-top: 5px">
								<g:actionSubmit name="save" action="saveContribution"
									class="btn btn-primary"
									value="${message(code: 'bfcrowd.label.collab.project.ok', default: 'Ok')}" />
								<g:actionSubmit name="skip" action="skip"
									class="btn btn-primary"
									value="${message(code: 'bfcrowd.label.collab.project.skip', default: 'Skip')}" />
							</div>
							<br>
							<br>
						</g:form>
					</div>

				</g:if>
				<g:else>
					<div id="emptyProject">
						<p style="text-align: center">
							<g:message code="bfcrowd.label.collab.project.noRecomm" />
						</p>
					</div>
				</g:else>
			</div>
		</div>

		<div class="span4">
			<div class="show-component">
				<strong><g:message
						code="bfcrowd.label.collab.project.myBadges" /> ${project.name }</strong>
				<div id="insignias" style="padding: 5px 5px 5px 5px">
					<!--  project.getUserBadges(UserBase.get(SecurityUtils.subject.principal)) -->
				
					<div class="row">

					<g:if test="${b[0]}">
						<div class="insignia">
							<img title="${b[0]["name"] }"
								src="https://ciencia.lifia.info.unlp.edu.ar/images/bfcrowdimages/firstContribution.png"
								alt="${b[0]["name"] }" class="img-circle img-responsive">
						</div>
					</g:if>	
					<g:if test="${b[1]}">
						<div class="insignia">
							<img title="${b[1]["name"] }"
								src="https://ciencia.lifia.info.unlp.edu.ar/images/bfcrowdimages/firstBonus.jpg"
								alt="${b[1]["name"] }" class="img-circle img-responsive">
						</div>
					</g:if>	

						<!-- Cuando supere las 12, link con "ver más" -->
					</div>



				</div>
				<strong><g:message
						code="bfcrowd.label.collab.project.stats" /> ${project.name }</strong>
				<div id="estadisticas" style="padding: 5px 5px 5px 5px">
					<ul>
						<li><g:message code="bfcrowd.label.collab.project.xp" />: ${project.getUserXPByID(UserBase.get(SecurityUtils.subject.principal).id)}
						</li>
						<li><g:message code="bfcrowd.label.collab.project.activity" />
							<div id="imgActividad" style="height: 50px"></div></li>
					</ul>
				</div>
			</div>
			<br />
			<div class="show-component">

				<strong><g:message
						code="bfcrowd.label.collab.project.ranking" /> ${project.name }</strong>
				<div class="table-responsive">
					<div class="nav-tabs-responsive">
						<ul class="nav nav-tabs">
							<li class="active"><a data-toggle="tab" href="#general"><g:message
										code="bfcrowd.label.collab.project.ranking.default" /></a></li>
							<g:if test="${UserBase.get(SecurityUtils.subject.principal).getFacebookID()}">			
								<li><a data-toggle="tab" href="#friends" onclick="facebookFriends();"><g:message
											code="bfcrowd.label.collab.project.ranking.fb" /></a></li>
							</g:if>			
						</ul>
					</div>
					<div class="tab-content">
						<div id="general" class="tab-pane fade in active">

							<table class="table">
								<thead>
									<tr>
										<th><g:message
												code="bfcrowd.label.collab.project.ranking.pos" /></th>
										<th><g:message
												code="bfcrowd.label.collab.project.ranking.user" /></th>
										<th><g:message
												code="bfcrowd.label.collab.project.ranking.xp" /></th>
									</tr>
								</thead>
								<tbody>
									<g:set var="pos" value="${1}" />
									<g:each var="user" in="${project.getUsersRanking()}">
										<tr>
											<th scope="row">
												${pos}
											</th>
											<td>
												${ User.get(user.key).getProfile().getFullName()}
											</td>
											<td>
												${user.value}
											</td>
										</tr>
										<g:set var="pos" value="${pos + 1}" />
									</g:each>
								</tbody>

							</table>

						</div>
						<div id="friends" class="tab-pane fade">
							<table class="table">
								<thead>
									<tr>
										<th><g:message
												code="bfcrowd.label.collab.project.ranking.pos" /></th>
										<th><g:message
												code="bfcrowd.label.collab.project.ranking.user" /></th>
										<th><g:message
												code="bfcrowd.label.collab.project.ranking.xp" /></th>
									</tr>
								</thead>
								<tbody>
									<g:set var="pos" value="${1}" />
									<g:each var="user" in="${project.getUsersRanking()}">
										<tr id="${User.get(user.key).getFacebookID() }" style="display: none">
											<th scope="row">
												${pos}
											</th>
											<td>
												${ User.get(user.key).getProfile().getFullName()}
											</td>
											<td>
												${user.value}
											</td>
										</tr>
										<g:set var="pos" value="${pos + 1}" />
									</g:each>
								</tbody>

							</table>
						</div>
					</div>

				</div>

			</div>

		</div>
	</div>
</body>

</html>
