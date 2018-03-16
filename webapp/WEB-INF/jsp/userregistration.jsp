<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page session="true"%>


<!DOCTYPE html>
<html>
<head>

    <title>Registration</title>
	<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
	
	
</head>
<body>

	

	<div id="page">
		<%@ include file="header.jsp" %>
	 <div id="content">
		<c:url var="addUser" value="/userregistration/" ></c:url>


             <h2>Register</h2>		
<form:form method="post" action="${addUser}" modelAttribute="user">
				
 <form:errors path="*" cssClass="error" element="div" />			
	<c:if test="${not empty message}">
		<div class="error">${message}</div>
	</c:if>
<div class="form-style-5">

         <fieldset>
         <legend><span class="number">1</span> Login Credentials</legend>
		 
		    
		        <form:input path="username" name="username" placeholder="Username"/>
		    
		     
		     <form:password path="password" name="password" placeholder="Password" />
		     
		     </fieldset>
		     <fieldset>
		     <legend><span class="number">2</span> Other Information</legend>
			     <form:input path="firstname" name="firstname" placeholder="First Name"/>
			     <form:input path="lastname" name="lastname" placeholder="Last Name"/>		     
			     <form:input path="phoneno" name="phoneno" placeholder="Phone Number" />
			     <form:input path="email" name="email" placeholder="Email" />
			     <form:input path="address" name="address" placeholder="House Address" />
		     
		    <form:hidden path="active" value="1"/> 
		   
			<input type="submit" value="Register"/>	
			    
			
	
</div>
 

</form:form>

	</div>
		
		
	</div>
</body>
</html>


