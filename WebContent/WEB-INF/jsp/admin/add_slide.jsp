<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/templates/taglib.jsp" %>
   <div class="container render-page">
      <div id="flash">
      </div>
<div class="row">
  <h1 class="text-center">Edit product</h1>
</div>
<div class="row">
  <form class="edit_product" enctype="multipart/form-data" action="${pageContext.request.contextPath }/admin/slide/add" accept-charset="UTF-8" method="post">
    <div class="col-md-6 col-md-offset-3">
    <div class="form-group">
        <label for="product_image">Image</label>
        <input id="image-upload" type="file" name="name">
        <img alt="suggested product image" class="img-thumbnail hidden" id="img_prev" src="./edit_product_files/saved_resource" width="300" height="300">
      </div>
      <div class="form-group">
        <label for="product_name">Link</label>
        <input class="form-control" type="text" name="link" >
      </div>
    </div>
    <div class="col-md-6 col-md-offset-3">
      <input type="submit" name="commit" value="Add Slide" class="btn btn-primary" data-disable-with="Add Slide">
    </div>
</form></div>
</div>