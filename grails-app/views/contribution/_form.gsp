<%@ page import="bfcrowd.Contribution" %>



			<div class="${hasErrors(bean: contributionInstance, field: 'recomendation', 'error')} required">
				<label for="recomendation" class="control-label"><g:message code="contribution.recomendation.label" default="Recomendation" /><span class="required-indicator">*</span></label>
				<div>
					<g:select class="form-control" id="recomendation" name="recomendation.id" from="${bfcrowd.Task.list()}" optionKey="id" required="" value="${contributionInstance?.recomendation?.id}" class="many-to-one"/>
					<span class="help-inline">${hasErrors(bean: contributionInstance, field: 'recomendation', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: contributionInstance, field: 'state', 'error')} ">
				<label for="state" class="control-label"><g:message code="contribution.state.label" default="State" /></label>
				<div>
					<g:textField class="form-control" name="state" value="${contributionInstance?.state}"/>
					<span class="help-inline">${hasErrors(bean: contributionInstance, field: 'state', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: contributionInstance, field: 'text', 'error')} ">
				<label for="text" class="control-label"><g:message code="contribution.text.label" default="Text" /></label>
				<div>
					<g:textField class="form-control" name="text" value="${contributionInstance?.text}"/>
					<span class="help-inline">${hasErrors(bean: contributionInstance, field: 'text', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: contributionInstance, field: 'user', 'error')} required">
				<label for="user" class="control-label"><g:message code="contribution.user.label" default="User" /><span class="required-indicator">*</span></label>
				<div>
					<g:select class="form-control" id="user" name="user.id" from="${bfcrowd.User.list()}" optionKey="id" required="" value="${contributionInstance?.user?.id}" class="many-to-one"/>
					<span class="help-inline">${hasErrors(bean: contributionInstance, field: 'user', 'error')}</span>
				</div>
			</div>

