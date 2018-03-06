<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/templates/taglib.jsp" %>
    <div class="container render-page">
      <div id="flash">
      </div>
      


<h1>Sign up</h1>
<div class="row">
  <div class="col-md-6 col-md-offset-3">
    <form enctype="multipart/form-data" action="${pageContext.request.contextPath }/dang-ky" accept-charset="UTF-8" method="post">
  <div class="form-group">
    <label for="user_user_name">User name</label>
    <input class="form-control" type="text" name="username"  />
  </div>

  <div class="form-group">
    <label for="user_email">Email</label>
    <input class="form-control" type="email" name="email" />
  </div>

  <div class="form-group">
    <label for="user_password">Password</label>
    <input class="form-control" type="password" name="password" />
  </div>

  <div class="form-group">
    <label for="user_address">Address</label>
    <input class="form-control" type="text" name="address" />
  </div>

  <div class="form-group">
    <label for="user_phone_number">Phone Number</label>
    <input class="form-control" type="text" name="phone" />
  </div>

  <div class="form-group">
    <label for="user_profile_image">Profile image</label>
    <input  type="file" name="image" />
  </div>

  <input type="submit" class="btn btn-primary" value="Create Account" />
</form>
  </div>
</div>
</div>
      
