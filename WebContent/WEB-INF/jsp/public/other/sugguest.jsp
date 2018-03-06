<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/templates/taglib.jsp" %>
    <div class="container render-page">
      <div id="flash">
      </div>
      <h2>My shopping cart</h2>
<div class="row">
  <h1 class="text-center">New suggest product</h1>
</div>
<div class="row">
  <form class="new_suggested_product" enctype="multipart/form-data" action="${pageContext.request.contextPath }/goi-y-san-pham" accept-charset="UTF-8" method="post">
    <div class="col-md-5 col-md-offset-2">
      <div class="form-group">
        <label for="suggested_product_name">Name</label>
        <input class="form-control" type="text" name="name" >
      </div>

      <div class="form-group">
        <label for="suggested_product_content">Content</label>
        <textarea class="form-control" name="content"></textarea>
      </div>

      <div class="form-group">
        <label for="suggested_product_price">Price</label>
        <div class="input-group">
          <span class="input-group-addon">$</span>
          <input class="form-control" min="0" step="1000" type="number" name="price" >
        </div>
      </div>
    </div>

    <div class="col-md-5">
      <div class="form-group">
        <label for="suggested_product_image">Image</label>
        <input id="image-upload" type="file" name="image">
        <img alt="suggested product" class="img-thumbnail hidden" id="img_prev" src="./suggest_files/saved_resource" width="300" height="300">
      </div>
    </div>

    <div class="col-md-5 col-md-offset-2">
      <input type="submit" name="commit" value="Suggest" class="btn btn-primary" data-disable-with="Suggest">
    </div>
</form></div>
</div>
      
