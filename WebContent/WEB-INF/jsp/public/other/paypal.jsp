<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/templates/taglib.jsp" %>
   <div class="container render-page">
      <div id="flash">
      <c:choose>
				<c:when test="${msg == 0 }">
              <div class="alert alert-danger"  style="text-align: center;">Mã giảm giá đã được sử dụng hoặc hết hạn</div>
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
	<input id="totalMoney" type="hidden" value="${sum/ 20000 }">
	<h2>Cảm ơn bạn đã ủng hộ của hàng</h2>
	<div  style="padding-left: 298px;" id="paypal-button-container"></div>
  <script>
  		var money = "0";
  		money = $('#totalMoney').val();
        paypal.Button.render({
        	

            env: 'sandbox', // sandbox | production

            // PayPal Client IDs - replace with your own
            // Create a PayPal app: https://developer.paypal.com/developer/applications/create
            client: {
                sandbox:    'AYZClG7WaBz2ixz_msu67D3vEgBk8e-3ysWIFEfA2TUFtXUKZx6g6AjYBIWZyz9y8DOzS-gL6Y93mqt2',
                production: '<insert production client id>'
            },

            // Show the buyer a 'Pay Now' button in the checkout flow
            commit: true,

            // payment() is called when the button is clicked
            payment: function(data, actions) {

                // Make a call to the REST api to create the payment
                return actions.payment.create({
                    payment: {
                        transactions: [
                            {
                                amount: { total: money, currency: 'USD' }
                            }
                        ]
                    }
                });
            },

            // onAuthorize() is called when the buyer approves the payment
            onAuthorize: function(data, actions) {

                // Make a call to the REST api to execute the payment
                return actions.payment.execute().then(function() {
                    window.alert('Payment Complete!');
                });
            }

        }, '#paypal-button-container');

    </script>
    <a style="margin-left: 247px;" class="btn btn-success" href="${pageContext.request.contextPath }/save">
          Hoàn thành đặt hàng, lưu hóa đơn
        </a>
  </div>
   
  </div>
</div>