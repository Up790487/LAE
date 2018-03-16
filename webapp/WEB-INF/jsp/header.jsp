        
        
       <img class="centerimage" src="${pageContext.request.contextPath}/resources/images/logo.jpg" alt="logo" width=256 height=125 />
		<div class="menu_simple">

			<ul>
				<li><a href="${pageContext.request.contextPath}/home">Home</a></li>
				<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_TOURIST')">
					<li><a href="${pageContext.request.contextPath}/attraction">Attraction Management</a></li>
					
				</sec:authorize>
				
			</ul>	
		
		</div>
		
		<div id="clear"></div>
		<div id="welcome">
	    <c:url value="/logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logout">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	     </form>
			<script>
				function formSubmit() {
					document.getElementById("logout").submit();
				}
			</script>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h3>
			You are logged in as ${pageContext.request.userPrincipal.name} - <a
				href="javascript:formSubmit()"> Logout</a>
		</h3>
	</c:if>
	<c:if test="${pageContext.request.userPrincipal.name == null}">
		<h3>
			<a href="login"> Login</a>
		</h3>
	</c:if>
</div>