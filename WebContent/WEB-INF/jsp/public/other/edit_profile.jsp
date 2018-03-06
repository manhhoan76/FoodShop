<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/templates/taglib.jsp" %>
    <div class="container render-page">
      <div id="flash">
      </div>
<h1>Edit profile</h1>
<div class="row">
  <div class="col-md-6 col-md-offset-3">
    <form  enctype="multipart/form-data" action="${pageContext.request.contextPath }/sua-trang-ca-nhan/${objUser.id}" accept-charset="UTF-8" method="post">
  <div class="form-group">
    <label for="user_user_name">User name</label>
    <input class="form-control" type="text" value="${objUser.username }" name="username">
  </div>

  <div class="form-group">
    <label for="user_email">Email</label>
    <input class="form-control" type="email" value="${objUser.email }" name="email" >
  </div>

  <div class="form-group">
    <label for="user_password">Password</label>
    <input class="form-control" type="password" name="password">
  </div>

  <div class="form-group">
    <label for="user_address">Address</label>
    <input class="form-control" type="text" value="${objUser.address }" name="address" >
  </div>

  <div class="form-group">
    <label for="user_phone_number">Phone Number</label>
    <input class="form-control" type="text" value="${objUser.phone }" name="phone">
  </div>

  <div class="form-group">
    <label for="user_profile_image">Profile image</label>
    <input id="image-upload" type="file" name="image">
      <img alt="profile image" class="img-responsive" id="current-profile-image" src="${pageContext.request.contextPath }/files/${objUser.image }">
    <img alt="profile image" class="img-thumbnail hidden" id="img_prev" src="./edit_profile_files/saved_resource" width="300" height="300">
  </div>

  <input type="submit" name="commit" value="Save changes" class="btn btn-primary" data-disable-with="Save changes">
</form>
  </div>
</div>
</div>