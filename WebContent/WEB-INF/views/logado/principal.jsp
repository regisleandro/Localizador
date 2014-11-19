<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <style>
    .round {
        border-radius: 50%;
        overflow: hidden;
        width: 50px;
        height: 50px;
        float: left;
    }
    .round img {
   	   display: block;
       min-width: 100%;
       min-height: 100%;
    }
</style>
<header>
Olá ${usuario.user}!
</header>

<c:forEach items="${facebookFriends}" var="p">
	<div class="round">
            <img src="http://graph.facebook.com/${p.id}/picture?type=small"></img>
    </div>
	${p.name}
</c:forEach>

<div>
<br/>
<button id="btnEnviar">Minha Localização </button></p>
<button id="btnPosition" style="visibility:hidden">Ver Mapa </button></p>
</div>
<div id="mapholder"></div>


<script src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script>
var username = "${usuario.user}" 

function getLocation()
  {
  if (navigator.geolocation){
    navigator.geolocation.getCurrentPosition(getCoordinates,showError);
    }
  else{x.innerHTML="Geolocation is not supported by this browser.";}
  }

function getCoordinates(position){
	console.log(position.coords.latitude + " " + position.coords.longitude);
	sendMessage(position);
}

function showPosition(lat, lon){
  latlon=new google.maps.LatLng(lat, lon)
  mapholder=document.getElementById('mapholder') 
  mapholder.style.height='350px';
  mapholder.style.width='100%';

  var myOptions={
  	center:latlon,zoom:14,
  	mapTypeId:google.maps.MapTypeId.ROADMAP,
  	mapTypeControl:false,
  	navigationControlOptions:{style:google.maps.NavigationControlStyle.SMALL}
  };
  var map=new google.maps.Map(document.getElementById("mapholder"),myOptions);
  var marker=new google.maps.Marker({position:latlon,map:map,title:"Eu estou aqui!"});
  
 }
  
function showError(error){
  switch(error.code) 
    {
    case error.PERMISSION_DENIED:
      x.innerHTML="User denied the request for Geolocation."
      break;
    case error.POSITION_UNAVAILABLE:
      x.innerHTML="Location information is unavailable."
      break;
    case error.TIMEOUT:
      x.innerHTML="The request to get user location timed out."
      break;
    case error.UNKNOWN_ERROR:
      x.innerHTML="An unknown error occurred."
      break;
    }
  }
  
  
  function log(msg) {
	   if (typeof console !== "undefined") console.log(msg); 
   } 
   if ('WebSocket' in window) { 
	 var websocket = new WebSocket("ws://" + document.location.host + "/Localizador/websocket?username=" + username); 
   } else if ('MozWebSocket' in window) { 
	   var websocket = new WebSocket("ws://" + document.location.host + "/Localizador/websocket?username=" + username); 
    } else {
		   alert("Browser não suporta WebSocket"); 
	}
    if (websocket != undefined) { 
	   websocket.onopen = function() { 
		   log("Conectou com sucesso");
		}; 
		websocket.onclose = function() { 
			log("Desconectou com sucesso"); 
			alert("Desconectou com sucesso"); 
		}; 
		websocket.onerror = function(e) { 
			log("Aconteceu um erro");
			log(e);
		}; 
		websocket.onmessage = function(data) {
			console.log("Recebeu " + data.data);
			var btnPosition = document.getElementById("btnPosition");
			var position = data.data;
			if (position != btnPosition && position !== '"${usuario.user}" se conectou.'){
				
				posicao = position.split("|");
				console.log('posicao' + posicao);
				lat = posicao[1];
				lon = posicao[2];
				showPosition(lat,lon);
/*				
				btnPosition.style.visibility = "visible";
				btnPosition.onclick = function(){
					posicao = position.split("|");
					console.log('posicao' + posicao);
					lat = posicao[1];
					lon = posicao[2];
					showPosition(lat,lon);
				};
*/			
			}
			
		}; 
		function sendMessage(position) { 
			  lat=position.coords.latitude;
			  lon=position.coords.longitude;
			websocket.send("|"+lat+"|"+lon); 
		} 
		document.getElementById("btnEnviar").onclick = function() {
			getLocation();			 
			return false;
		}; 
	} 
</script>
