<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url var="actionPath" value='${action}' />
<form:form method="post" action="${actionPath}" modelAttribute="usuario" cssClass="form-horizontal">
	 <form:hidden path="id" />
	  
	  <div class="form-group">
	   	<div class="col-sm-offset-2 col-sm-10">
		 	<button type="deletar" class="btn btn-default">Deletar cadastro</button>
	 	</div>
	  </div>
</form:form>