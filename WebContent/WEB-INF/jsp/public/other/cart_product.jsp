<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/templates/taglib.jsp" %>
   <div class="container render-page">
      <div id="flash">
      </div>
      <h2>My shopping cart</h2>
<div class="row">
    <div class="col-md-8">
      <table class="table ecommerce-table">
        <thead>
          <tr>
            <th>
              <h4>${totalProduct } products</h4>
            </th>
            <th></th>
            <th>Price</th>
            <th>Quantity</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
        <c:forEach items="${cart }" var="objCart">
        		<tr class="product-in-cart">
  <td class="w15">
    <img crop="fill" alt="${objCart.name }" class="img-responsive" src="${pageContext.request.contextPath }/files/${objCart.image}" width="260" height="180">
  </td>
  <td class="w45">
    <p>${objCart.name }</p>
  </td>
  <td class="w15">
    <p>$${objCart.price }</p>
  </td>
  <td class="w20 quantity">
    <div class="row">
      <div class="col-md-12 col-sm-12">
        <div class="input-group number-spinner">
          <span class="input-group-btn">
            <a class="btn btn-default" onclick="down(${objCart.id_product})" onmouseout="total()" href="javascript:void(0)">
              <span class="glyphicon glyphicon-minus"></span>
			</a>         
          </span>
          <div id="showNumber-${objCart.id_product }">
          <input type="text" class="form-control text-center only-number" id="numberQuantity-${objCart.id_product }" value="${objCart.quantity }">
          </div>
          <span class="input-group-btn">
  					<a class="btn btn-success" onclick="up(${objCart.id_product})" onmouseout="total()" href="javascript:void(0)"  >
              <span class="glyphicon glyphicon-plus"></span>
              </a>
  				</span>
        </div>
      </div>
    </div>
  </td>
  <td class="remove">
    <a href="javascript:;" class="btn-remove-product" >×</a>
  </td>
</tr>
        </c:forEach>
        </tbody>
      </table>
      <script>
				function  down(id) {
					var number = document.getElementById('numberQuantity-'+id).value;
					$.ajax({
						url: '${pageContext.request.contextPath }/downNumber',
						type: 'POST',
						cache: false,
						data: {
								//Dữ liệu gửi đi
								nid : id,
								nnumber: number,
								},
						success: function(data){
							// Xử lý thành công
							$('#showNumber-'+id).html(data);
						},
						error: function (){
						// Xử lý nếu có lỗi
						alert('Loi');
						}
					});
				}
				function  up(id) {
					var number = document.getElementById('numberQuantity-'+id).value;
					$.ajax({
						url: '${pageContext.request.contextPath }/upNumber',
						type: 'POST',
						cache: false,
						data: {
								//Dữ liệu gửi đi
								nid : id,
								nnumber: number,
								},
						success: function(data){
							// Xử lý thành công
							$('#showNumber-'+id).html(data);
						},
						error: function (){
						// Xử lý nếu có lỗi
						alert('Loi');
						}
					});
				}
				function  total() {
					$.ajax({
						url: '${pageContext.request.contextPath }/total',
						type: 'POST',
						cache: false,
						data: {
								//Dữ liệu gửi đi
								},
						success: function(data){
							// Xử lý thành công
							$('#sumTotal').html(data);
						},
						error: function (){
						// Xử lý nếu có lỗi
						alert('Loi');
						}
					});
				}
				
			</script>
    </div>
    <div class="col-md-4 order-summary">
      <h4>Order summary</h4>
      <hr>
      <p>Total <span>(Total payment amout)</span>
      <br />
          <span>$</span> <span id="sumTotal">${sumTotal }</span> <span>VNĐ</span>
      </p>

      <div class="row">
      <a href="${pageContext.request.contextPath }/pay" class="btn btn-primary btn-checkout" >Proceed to checkout</a>
            </div>
    </div>
</div>
</div>
      