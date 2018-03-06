<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/templates/taglib.jsp" %>
    <div class="container render-page">
      <div id="flash">
      <c:choose>
				<c:when test="${msg == 0 }">
              <div class="alert alert-danger">Thất bại</div>
              </c:when>
              
				<c:when test="${msg == 1 }">
              <div class="alert alert-success">Thành công</div>
              </c:when>
              </c:choose>
      </div>
      <h1>Login</h1>
<div class="row">
  <div class="col-md-6 col-md-offset-3">
    <form  action="${pageContext.request.contextPath }/login" method="post" accept-charset="UTF-8" >
    <input name="utf8" type="hidden" value="&#x2713;" /><input type="hidden" name="authenticity_token" value="XQDq7WEd9VNW8OwP2FOhZuK+kpDSsLo9p6Oz6DoawwiCPLLiHNjqfzMUMerKvJKqiBuUwVOxk4Lk/Kj1KORIgw==" />
      <label for="sessions_email">Username</label>
      <input class="form-control" type="text" name="username"  />

      <label for="sessions_password">Password</label>
      <a href="#">(forgot password)</a>
      <input class="form-control" type="password" name="password" />

      <label class="checkbox inline" for="sessions_remember_me">
        <input  type="hidden" value="0" />
        <input type="checkbox" value="1" />
        <span>Remember me</span>
</label>
      <input type="submit" name="commit" value="Login" class="btn btn-primary" data-disable-with="Login" />
</form>    
     <a  class="col-md-12 btn btn-danger" href="${pageContext.request.contextPath }/dang-ky">Sign up now</a>
    
  </div>
</div>
</div>
      
