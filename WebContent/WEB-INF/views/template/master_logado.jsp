<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html class="no-js">
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<meta name="description" content="Slide and push menus that are initially hidden off screen, and transition into view with CSS transitions." />
<title>Onde Estou???</title>
<head>
<!-- css -->
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,700italic,400,600,700' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Bitter:400,700' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/font-awesome/css/font-awesome.min.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/css/base.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>" />
</head>
 <c:set var="req" value="${pageContext.request}" />
<body>

<nav class="menu slide-menu-left">
    <ul>
        <li><button class="close-menu">&larr; Fechar</button></li>
        <li> <a href="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}/principal/" >Onde Estou???</a></li>
        <li><a href="#">Meus Amigos</a></li>
        <li><a href="#">Histórico Percurso</a></li>
        <li><a href="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}/inicio/alterar_cadastro/" >Cadastro</a></li>
        <li><a href="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}/sair/" > Logout </a></li>
        <li><a href="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}/inicio/deletar/" >Deletar Cadastro</a></li>
        
    </ul>
</nav><!-- /slide menu left -->
<header>
 <div>
	 <div class="buttons">

	       
	       <button class="nav-toggler toggle-slide-left" 
	       		   style="background-color:transparent; border-color:transparent;">
	        <img src="<c:url value='/resources/images/menu.png'/>" height="35"/>
	       </button>

	   </div><!-- /buttons -->
 </div>
</header>
  
<div id="main">
        <div class="container">
			<tiles:insertAttribute name="body" />
		</div>
</div>
<footer></footer>
<!-- js -->
<script src="<c:url value='/resources/js/classie.js'/>"></script>
<script src="<c:url value='/resources/js/nav.js'/>"></script>

</body>
</html>