<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/templates/taglib.jsp" %>
   <div class="container render-page">
      <div id="flash">
		      
      </div>
<div class="row">
  <h1 class="text-center">Payment</h1>
</div>
<div class="row">
  	<div class="col-md-8 col-md-offset-2">
  		<h2>Số tiền bạn phải thanh toán: <span >${sumTotal } VNĐ </span> (${sumTotal/20000}$)</h2>
  		<form class="new_order" action="${pageContext.request.contextPath }/checkOut" accept-charset="UTF-8" method="post">
         	<div class="form-group">
            <label >Discount</label>
            <input class="form-control" type="text" name="discount">
          </div>
          <div class="form-group">
            <label for="order_address">Address</label>
            <input class="form-control" type="text" name="address">
          </div>
          <div class="form-group">
            <label for="order_full_name">Full name</label>
            <input class="form-control" type="text" name="username" >
          </div>

          <div class="form-group">
            <label for="order_phone">Phone</label>
            <input class="form-control" type="text" name="phone">
          </div>
          <div class="form-group">
            <label for="order_phone">Hình thức thanh toán</label>
            <select name="pay">
            	<option value="1">Nhận tiền khi giao hàng</option>
            	<option value="2">PayPal</option>
            </select>
          </div>
          <input type="submit" name="commit" value="Proceed to checkout" class="btn btn-primary btn-checkout" data-disable-with="Proceed to checkout">
</form>  
 
  	</div>
  </div>
  
</div>