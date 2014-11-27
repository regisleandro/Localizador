<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
  <div class="col-sm-9">
    Histórico de Trajetos
    <c:forEach items="${historico}" var="h">
	    <div class="row">
	      <div class="col-xs-8 ">


			${h.data} : 	${h.solicitado.nome}
	      </div>

	    </div>
    </c:forEach>
  </div>
</div>
