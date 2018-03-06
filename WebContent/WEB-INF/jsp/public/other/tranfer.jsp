<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/templates/taglib.jsp" %>
   <div class="container render-page">
      <div id="flash">
       <c:choose>
				<c:when test="${msg == 0 }">
              <div class="alert alert-danger" style="text-align: center;">Mã giảm giá đã được sử dụng hoặc hết hạn</div>
              </c:when>
              
				<c:when test="${msg == 1 }">
              <div class="alert alert-success"  style="text-align: center;">Mã giảm giá được áp dụng thành công</div>
              </c:when>
              </c:choose>
      </div>
<div class="row">
  <h1 class="text-center">Thank You!!</h1>
</div>
<div class="row">
 <div class="col-md-8 col-md-offset-2">
	<h1>Số tiền bạn cần thanh toán là ${sum }</h1>
	<h2>Cảm ơn bạn đã ủng hộ của hàng</h2>
	<span>Đơn hàng của bạn đã được ghi nhận, hãy nhấn nút phía dưới để kết thúc mua hàng</span>
	 <a style="margin-left: 247px;" class="btn btn-success" href="${pageContext.request.contextPath }/save">
          Hoàn thành đặt hàng, lưu hóa đơn
        </a>
  </div>
  
  </div>
</div>