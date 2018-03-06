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
  <form class="edit_product" id="edit_product_7" enctype="multipart/form-data" action="http://tso-plus.herokuapp.com/admin/products/7" accept-charset="UTF-8" method="post"><input name="utf8" type="hidden" value="✓"><input type="hidden" name="_method" value="patch"><input type="hidden" name="authenticity_token" value="wAQ7WeVz0/YlCBPkVDSSON11MFa7A5LJbWjxVEvUww79lhlUpZP+KUB340pNAQTIWsW8/BBO2ZpFb65gnvIejA==">
    
    <div class="col-md-5 col-md-offset-2">
      <div class="form-group">
        <label for="product_name">Name</label>
        <input class="form-control" type="text" value="Chanh dây" name="product[name]" id="product_name">
      </div>

      <div>
        <span class="translation_missing" title="translation missing: en.search.classification">Classification</span><select class="select form-control" name="product[classification_id]" id="product_classification_id"><option value="1">Food</option>
<option value="2">Drink</option></select>
      </div>

      <div>
        <span class="translation_missing" title="translation missing: en.search.category">Category</span><select class="select form-control" name="product[category_id]" id="product_category_id"><option value="2">Hoa quả dầm</option>
<option value="3">Sinh tố</option>
<option value="4">Chè</option>
<option value="5">Trà sữa</option></select>
      </div>

      <div class="form-group">
        <label for="product_description">Description</label>
        <textarea class="form-control" id="product_description">Trong chanh leo (chanh dây), chứa rất nhiều loại acid amin rất phù hợp cho những ai bị suy nhược cơ thể. Là thức uống rất ngon so với chanh bình thường.</textarea>
      </div>

      <div class="form-group">
        <label for="product_price">Price</label>
        <div class="input-group">
          <span class="input-group-addon">$</span>
          <input class="form-control" min="0" step="1000" type="number" value="10000.0" name="product[price]" id="product_price">
        </div>
      </div>

      <div class="form-group">
        <label for="product_quantity">Quantity</label>
        <div class="input-group">
          <input class="form-control" min="0" step="1" type="number" value="2" name="product[quantity]" id="product_quantity">
        </div>
      </div>
    </div>
    <div class="col-md-5">
      <div class="form-group">
        <label for="product_image">Image</label>
        <input id="image-upload" type="file" name="product[image]">
          <img alt="logo" class="img-responsive" id="current-profile-image" src="./edit_product_files/chanhday.jpg">
        <img alt="suggested product image" class="img-thumbnail hidden" id="img_prev" src="./edit_product_files/saved_resource" width="300" height="300">
      </div>
    </div>
    <div class="col-md-5 col-md-offset-2">
      <input type="submit" name="commit" value="Save changes" class="btn btn-primary" data-disable-with="Save changes">
    </div>
</form></div>
</div>