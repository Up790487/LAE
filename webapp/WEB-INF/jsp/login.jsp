<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page session="true"%>
<!DOCTYPE html>
<head>

    <title>Login</title>
	

	<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
	
</head>
<body>
	
	<div id="page">
	   
			<%@ include file="header.jsp" %>
			
		<div id="content">
			<c:if test="${not empty error}">
			 <div class="error">${error}</div>
		    </c:if>
		    <c:if test="${not empty message}">
			   <div class="error">${message}</div>
		    </c:if>
			
			<form:form method="post" action="login" modelAttribute="user">
    			<div class="form-style-5">
    			<fieldset>
                   <legend><span class="number">[]</span> Login</legend>
	    			
	    			<form:input path="username" name='username' placeholder="Username" />
	    			<form:password path="password" name='password' placeholder="Password" />
	    			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	    			<input type="submit" value="LOGIN"/>
	    			</fieldset>
	    			
	    		<fieldset>
                   <legend><span class="number">+</span> Registration</legend>
	    			<div><a href="${pageContext.request.contextPath}/userregistration">Register</a></div>
	    			
	    		</fieldset>
    			</div>
    			
					
				
  			</form:form>
  		</div>
		
		
	</div>
</body>
</html>


