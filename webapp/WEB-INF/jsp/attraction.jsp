<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>

    <title>Attraction Listing</title>

	<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
	
	
</head>
<body>
	
	<div id="page">
	   
		<%@ include file="header.jsp" %>
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_TOURIST')">	
<div id="content">
			

<div class="form-style-5">


<h2>Available Attractions</h2>		
	<c:url var="formAction" value="/attraction" ></c:url>
<form:form method="post"  modelAttribute="attraction" action="${formAction}?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data" >
	<c:if test="${not empty message}">
		<div class="error">${message}</div>
	</c:if>			
    <form:errors path="*" cssClass="error" element="div" />			
	

 
		  <c:if test="${attraction.idattraction>0}">
					<form:hidden path="idattraction" />
		  </c:if>
	
	
		<form:input path="name" name="name" placeholder="Name" />
		
	
		<form:select path="category">
					   <form:option value="" label="Choose Attraction Category" />
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
	    
	    
	    
	    <form:input path="starthour" name="starthour" placeholder="Start Hour" />
	    <form:input path="endhour" name="endhour" placeholder="End Hour" />

		
		<form:textarea path="description" name="Description"  placeholder="Description"  rows="5" cols="30"  />
		
		
		<form:input path="address" name="Address"  placeholder="Address"/>
		
	     <sec:authorize access="hasRole('ROLE_TOURIST')">
		
			<c:if  test="${empty attraction.idattraction}"> 
					<form:hidden path="status" value="Pending" />
					
			</c:if>
			<c:if  test="${attraction.idattraction>0}"> 
				<form:hidden path="status" />
				
			</c:if>
			
        </sec:authorize>
        
        <sec:authorize access="hasRole('ROLE_ADMIN')">
	   
			<form:select path="status">
						   <form:option value="" label="Attraction Status" />
						  <form:option value="Pending" label="Pending" />
						  <form:option value="Live" label="Live" />
		    </form:select>
		
		 </sec:authorize>
		
        
	 
	    <form:input path="picture" name="Picture"  type="file"/>
	  <br></br>
		
		
		<c:if test="${attraction.idattraction>0}">
			<form:hidden path="picture" />
		</c:if>


		<c:if  test="${attraction.idattraction>0}"> 
		<form:hidden path="username"/>    
			<input type="submit" value="UPDATE"/>
		</c:if>
		<c:if  test="${empty attraction.idattraction}"> 
		<form:hidden path="username" value="${pageContext.request.userPrincipal.name}" />  
			<input type="submit" value="ADD"/>
		</c:if>
		
	
</form:form>

</div>
</div>
</sec:authorize>
<div>
<br></br>
<h2>Attraction Listing</h2>
<c:choose>
		<c:when test="${!empty attractionList}">
		  
			<c:forEach items="${attractionList}" var="attraction">
			
				<div class="img">
					   <c:choose>
					          <c:when test="${empty attraction.picture}">
					          <a target="_blank" href="${pageContext.request.contextPath}/resources/images/noimage.png">
							  	<img src="${pageContext.request.contextPath}/resources/images/noimage.png" alt="attraction picture" width=150 height=150 />
							  	</a>
							  </c:when>    
						    <c:otherwise>
						    <a target="_blank" href="${pageContext.request.contextPath}/resources/images/${attraction.username}${attraction.name}${attraction.picture}">
						       <img src="${pageContext.request.contextPath}/resources/images/${attraction.username}${attraction.name}${attraction.picture}" alt="attraction picture" width=150 height=150 />
						     </a>
						    </c:otherwise>
					   </c:choose>	
					  
					  <div class="link"><h4>Name: </h4>${attraction.name}</div>
						  <div class="link"><h4>Description: </h4>${attraction.description}</div>
						  <div class="link"><h4>Category </h4>${attraction.category}</div>
						  <div class="link"><h4>Address: </h4>${attraction.address}</div>
						  
						  <div class="link"><h4>Opening Hours: </h4><fmt:formatDate pattern="HH:mm" value="${attraction.starthour}" /> --- <fmt:formatDate pattern="HH:mm" value="${attraction.endhour}" /></div>
						  
					   
						  <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_TOURIST')">
								 <div class="link"><h4>Status: </h4>${attraction.status}</div>							
						 </sec:authorize>
				      <div class="link">
						 <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_TOURIST')">
						 
									<a href="${pageContext.request.contextPath}/updateattraction/${attraction.idattraction}">Update</a>&nbsp;
									<a href="${pageContext.request.contextPath}/deleteattraction/${attraction.idattraction}">Delete</a>
									
						 </sec:authorize>
						 
					  <sec:authorize access="hasRole('ROLE_TOURIST')">
					    <a href="${pageContext.request.contextPath}/rateattraction/${attraction.idattraction}">Rate Attraction</a>
					  </sec:authorize>
					 </div>
					
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


