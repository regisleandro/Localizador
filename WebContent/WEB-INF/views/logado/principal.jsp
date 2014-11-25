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
	.popin{
	  background:#fff;
	  padding:15px;
	  box-shadow: 0 0 20px #999;
	  border-radius:2px;
	}
	
	#mapholder {
 	 height:300px;
  	 background:#6699cc;
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

<div id="map" class="popin">
	<div id="mapholder" ></div>
</div>
</div>

<script src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script src="<c:url value='/resources/js/gmaps.js'/>"></script>
<script>
var username = "${usuario.user}" 

document.getElementById("map").style.visibility="hidden";

function getLocation()
  {
  if (navigator.geolocation){
    navigator.geolocation.getCurrentPosition(getCoordinates,showError);
    }
  else{x.innerHTML="Geolocation is not supported by this browser.";}
  }

function getCoordinates(position){
	console.log(position.coords.latitude + " - " + position.coords.longitude);
	sendMessage(position);
}

function getActualPosition(position){
	return position;
}

function showPosition(lat, lon){
	var path = [];
	navigator.geolocation.getCurrentPosition(function(actualPosition){
		var map = new GMaps({
			  div: '#mapholder',
			  lat: lat,
			  lng: lon
			});
		map.drawRoute({
			  origin: [lat, lon],
			  destination: [actualPosition.coords.latitude, actualPosition.coords.longitude],
			  travelMode: 'driving',
			  strokeColor: '#131540',
			  strokeOpacity: 0.6,
			  strokeWeight: 6
			});
	});
	document.getElementById("map").style.visibility="visible";
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
		}; 
		websocket.onerror = function(e) { 
			log("Aconteceu um erro");
			log("-- " + e);
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
			//	showPosition(lat,lon);
				v
				btnPosition.style.visibility = "visible";
				btnPosition.onclick = function(){
					posicao = position.split("|");
					console.log('posicao' + posicao);
					lat = posicao[1];
					lon = posicao[2];
					showPosition(lat,lon);
				};
			
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
