<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/templates/taglib.jsp" %>
   <div class="container render-page">
      <div id="flash">
      </div>
<div class="row">
  <h1 class="text-center">Add Discount</h1>
</div>
<div class="row">
  <form class="edit_product"  action="${pageContext.request.contextPath }/admin/discount/add" accept-charset="UTF-8" method="post">
    <div class="col-md-6 col-md-offset-3">
      <div class="form-group">
        <label for="product_name">Long</label>
        <input class="form-control" type="text" name="longDis" >
      </div>
     <div class="form-group">
        <label for="product_name">Percent</label>
        <input class="form-control" type="text" name="percent" >
      </div>
      <div class="form-group">
        <label for="product_name">Number</label>
        <input class="form-control" type="text" name="number" >
      </div>
    </div>
    <div class="col-md-6 col-md-offset-3">
      <input type="submit" name="commit" value="Add Slide" class="btn btn-primary" data-disable-with="Make">
    </div>
</form></div>
</div>