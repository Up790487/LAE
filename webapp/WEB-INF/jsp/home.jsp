<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>
	<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">	
	
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <style>
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
      #map {
        height: 100%;
      }
      /* Optional: Makes the sample page fill the window. */
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
    </style>
    <script>
    
    // Note: This example requires that you consent to location sharing when
    // prompted by your browser. If you see the error "The Geolocation service
    // failed.", it means you probably did not give permission for the browser to
    // locate you.
    var map, infoWindow,attractions;
   
    function initMap() {
      map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 50.79899, lng: -1.09125},
        zoom:12
      });
      infoWindow = new google.maps.InfoWindow;
    
      // Try HTML5 geolocation.
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
          var pos = {
            lat: position.coords.latitude,
            lng: position.coords.longitude
            
          };
          var image=image="resources/images/person.jpg";
          var marker = new google.maps.Marker({
              map: map,
              position: pos,
              icon: image
            });
          infoWindow.setPosition(pos);
          infoWindow.setContent('This is Me');
          infoWindow.open(map,marker);
           map.setCenter(pos);
           google.maps.event.addListener(marker, 'mouseover', function() {
           	
               infoWindow.setContent('<strong> My Location </strong>');
               infoWindow.open(map, marker);
             });
          
        }, function() {
          handleLocationError(true, infoWindow, map.getCenter());
        });
      } else {
        // Browser doesn't support Geolocation
        handleLocationError(false, infoWindow, map.getCenter());
      }
      
      var geocoder = new google.maps.Geocoder();//get geocoder
      
      geocodeAddress(geocoder,map);
    }

    function handleLocationError(browserHasGeolocation, infoWindow, pos) {
      infoWindow.setPosition(pos);
      infoWindow.setContent(browserHasGeolocation ?
                            'Error: The Geolocation service failed.' :
                            'Error: Your browser doesn\'t support geolocation.');
      infoWindow.open(map);
    }
    
    function geocodeAddress(geocoder, map) {
       
       var attractionJson='${attractionJson}';
       attractions = JSON.parse(attractionJson);
       var resultsMap = map;
       var length = attractions.length;
       
       for (i = 0; i < length; i++) {
    	   console.log(i);
    	   geocoder.geocode({'address': attractions[i].address},makeCallback(i));
    	   function makeCallback(i) {
    	    	call= function(results, status) {
    		          if (status === 'OK') {
    		        	var images;
    		        	if(attractions[i].category=="Restaurant"){
    		        		image="resources/images/restaurant.jpg";
    		        	}else if(attractions[i].category=="Theatre"){
    		        		image="resources/images/theatre.jpg";
    		        	}else if(attractions[i].category=="Parks"){
    		        		image="resources/images/parks.jpg";
    		        	}else if(attractions[i].category=="Cinema"){
    		        		image="resources/images/cinema.jpg";
    		        	}else if(attractions[i].category=="Museum"){
    		        		image="resources/images/museum.jpg";
    		        	}else if(attractions[i].category=="Pubs"){
    		        		image="resources/images/pubs.jpg";
    		        	}else if(attractions[i].category=="Theatre"){
    		        		image="resources/images/theatre.jpg";
    		        	}else if(attractions[i].category=="Landmark"){
    		        		image="resources/images/landmark.jpg";
    		        	}else if(attractions[i].category=="Sport"){
    		        		image="resources/images/sport.jpg";
    		        	}else if(attractions[i].category=="NightLife"){
    		        		image="resources/images/nightlife.jpg";
    		        	}
    		            
    		            var marker = new google.maps.Marker({
    		              map: resultsMap,
    		              position: results[0].geometry.location,
    		              icon: image
    		            });
    		            console.log(i);
    		     	   
    		           
    		            infoWindow.setPosition(results[0].geometry.location);
    		            
    		            google.maps.event.addListener(marker, 'mouseover', function() {
    		            	var starthour=new Date(attractions[i].starthour);
    		            	var endhour=new Date(attractions[i].endhour);
    		                infoWindow.setContent('<div>Name: <strong>' + attractions[i].name + '</strong><br>'+
    		                        '<div>Category: <strong>' + attractions[i].category + '</strong><br>' 
    		                        );
    		                infoWindow.open(map, marker);
    		              });
    		            
    		            google.maps.event.addListener(marker, 'click', function() {
    		            	var starthour=new Date(attractions[i].starthour);
    		            	var endhour=new Date(attractions[i].endhour);
    		                infoWindow.setContent('<div>Name: <strong>' + attractions[i].name + '</strong><br>' +
    		                        'Description: ' +  attractions[i].description+ '<br>' +
    		                        'Address: ' +  attractions[i].address+ '<br>' +
    		                        'Open Time: ' + starthour.getHours() + ':'+starthour.getMinutes()+'<br>' +
    		                        'Closing Time: ' + endhour.getHours() + ':'+endhour.getMinutes()+'<br>' +
    		                        '<div>Category: <strong>' + attractions[i].category + '</strong><br>' +
    		                        'Latitude: ' +  results[0].geometry.location.lat()+ '<br>' +
    		                        'Longitude: ' +  results[0].geometry.location.lng()+ '</div>');
    		                infoWindow.open(map, marker);
    		              });
    		          } else {
    		            alert('Geocode was not successful for the following reason: ' + status);
    		          }
    		                   
    	       };
    	       return call; 
    	    }     
    	        
    	}   
       
       
        //resultsMap.setCenter(pos);
      }
    
    
 </script>
</head>
<body>
	
	<div id="page">
	   
		<%@ include file="header.jsp" %>
		
		
	
<div id="content">
<h1>Welcome To Portsmouth Attractions</h1>
			<p>
				This is a website designed to aid tourist and travellers in finding interesting location in the city of portsmouth
			</p>
			<p> 
				Just click on the Attractions menu to see a list of attraction and make your choice right away.
			</p>
			
<div class="form-style-5">
	
<c:url var="addAction" value="/search" ></c:url>
	
<form:form method="post"  modelAttribute="search" action="${addAction}" >
				
 <form:errors path="*" cssClass="error" element="div" />			
	<c:if test="${not empty message}">
		<div class="error">${message}</div>
	</c:if>

		<form:select path="filtertype">
					  <form:option value="" label="All Attractions" />
					  <form:option value="NightLife" label="NightLife" />
					  <form:option value="Pubs" label="Pubs" />
					  <form:option value="Restaurant" label="Restaurant" />
					  <form:option value="Cinema" label="Cinema" />
					  <form:option value="Museum" label="Museum" />
					  <form:option value="Sport" label="Sport" />
					  <form:option value="Theatre" label="Theatre" />
					  <form:option value="Parks" label="Parks" />
					  <form:option value="Landmark" label="Landmark" />			  
	    </form:select>
	    
	    <form:input path="search" name="search" placeholder="Search Attraction" />
	    <input type="submit" value="SEARCH"/>

</form:form>
</div>
</div>
<div>
<h2>Attraction Listing</h2>

  <div id="map" style="height:500px; width:1300px"></div>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDkmbw-nK--yjCduWYuAZhkO2XQCV3NczI&libraries=places&callback=initMap">
   </script>
<c:choose>
		<c:when test="${!empty attractionList}">
		 
			<c:forEach items="${attractionList}" var="attraction">
				
					<div class="img">
						   <c:choose>
						          <c:when test="${empty attraction.picture}">
						          <a target="_blank" href="${pageContext.request.contextPath}/resources/images/noimage.png">
								  	<img src="${pageContext.request.contextPath}/resources/images/noimage.png" alt="Attraction picture" width=150 height=150 />
								  	</a>
								  </c:when>    
							    <c:otherwise>
							    <a target="_blank" href="${pageContext.request.contextPath}/resources/images/${attraction.username}${attraction.name}${attraction.picture}">
							       <img src="${pageContext.request.contextPath}/resources/images/${attraction.username}${attraction.name}${attraction.picture}" alt="proilepic" width=150 height=150 />
							     </a>
							    </c:otherwise>
						   </c:choose>	
						  
						  <div class="link"><h4>Name: </h4>${attraction.name}</div>
						  <div class="link"><h4>Description: </h4>${attraction.description}</div>
						  <div class="link"><h4>Category </h4>${attraction.category}</div>
						  <div class="link"><h4>Address: </h4>${attraction.address}</div>
						  <div class="link"><h4>Opening Hours: </h4><fmt:formatDate pattern="HH:mm" value="${attraction.starthour}" /> --- <fmt:formatDate pattern="HH:mm" value="${attraction.endhour}" /></div>
						  
					
						
					</div>
			
				</c:forEach>
		
    </c:when>    
    <c:otherwise>
       <div>Attraction is Not Available</div>
    </c:otherwise>
</c:choose>	
</div>
	
</div>
		
	
</body>
</html>