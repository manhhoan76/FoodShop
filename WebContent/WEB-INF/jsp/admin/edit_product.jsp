<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/templates/taglib.jsp" %>
   <div class="container render-page">
      <div id="flash">
      </div>
<div class="row">
  <h1 class="text-center">Add product</h1>
</div>
<div class="row">
  <form class="edit_product"  enctype="multipart/form-data" action="${pageContext.request.contextPath }/admin/product/edit/${objProduct.id }" accept-charset="UTF-8" method="post">
    <div class="col-md-5 col-md-offset-2">
      <div class="form-group">
        <label for="product_name">Name</label>
        <input class="form-control" type="text" value="${objProduct.name }"  name="name" >
      </div>

      <div>
        <span class="translation_missing">Category</span>
        <select class="select form-control" name="id_cat" >
        <c:forEach items="${listCat }" var="objCat">
        	<option value="${objCat.id }">${objCat.name }</option>
        </c:forEach>
        </select>
      </div>
      <div class="form-group">
        <label for="product_description">Description</label>
        <textarea class="form-control" name="description">${objProduct.description }</textarea>
      </div>

      <div class="form-group">
        <label for="product_price">Price</label>
        <div class="input-group">
          <span class="input-group-addon">$</span>
          <input class="form-control" min="0" step="1000" type="number" value="${objProduct.price }" name="price" >
        </div>
      </div>
    </div>
    <div class="col-md-5">
      <div class="form-group">
        <label for="product_image">Image</label>
        <input id="image-upload" type="file" name="image">
         <img alt="logo" class="img-responsive" id="current-profile-image" src="${pageContext.request.contextPath }/files/${objProduct.image }">
        <img alt="suggested product image" class="img-thumbnail hidden" id="img_prev" src="./edit_product_files/saved_resource" width="300" height="300">
      </div>
    </div>
    <div class="col-md-5 col-md-offset-2">
      <input type="submit" name="commit" value="Save changes" class="btn btn-primary" data-disable-with="Add Product">
    </div>
</form></div>
</div>