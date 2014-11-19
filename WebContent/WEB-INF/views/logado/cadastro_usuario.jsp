<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url var="actionPath" value='${action}' />
<form:form method="post" action="${actionPath}" modelAttribute="usuario" cssClass="form-horizontal">
	 <form:hidden path="id" />
	  <div class="form-group">
	      <label for="user" class="col-sm-2 control-label">Usuário</label>
	      <div class="col-sm-10">
	      		<form:input path="user" id="user" placeholder="Usuário"/>							      	
		  </div>
	  </div>
	  <div class="form-group">
	      <label for="password" class="col-sm-2 control-label">Senha</label>
	      <div class="col-sm-10">
	      		<form:password path="password" id="password" placeholder="Senha" />							      	
		  </div>
	  </div>
	  <div class="form-group">
	   	<div class="col-sm-offset-2 col-sm-10">
		 	<button type="submit" class="btn btn-default">Salvar</button>
	 	</div>
	  </div>
</form:form>