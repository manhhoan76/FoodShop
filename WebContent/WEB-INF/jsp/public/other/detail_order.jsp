<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/templates/taglib.jsp" %>
    <div class="container render-page">
      <div id="flash">
      </div>
		<h2>Order #${idOrder }</h2>
		<div class="row">
		  <div class="col-md-8">
		      <table class="table ecommerce-table">
		        <thead>
		          <tr>
		            <th>
		              <h4>${totalProductDetail } product</h4>
		            </th>
		            <th></th>
		            <th>ITEM PRICE</th>
		            <th>QUANTITY</th>
		            <th></th>
		          </tr>
		        </thead>
		        <tbody>
		        <c:forEach items="${listDetail }" var="objDetail">
		        	<tr class="product-in-cart">
					  <td class="w15">
					    <img alt="product image" class="img-responsive" src="${pageContext.request.contextPath }/files/${objDetail.image}">
					  </td>
					  <td class="w45">
					    <p>${objDetail.name }</p>
					  </td>
					  <td class="w15">
					    <p>$${objDetail.price }</p>
					  </td>
					  <td class="w20 quantity">
					    <p>${objDetail.quantity } item</p>
					  </td>
					</tr>
		        </c:forEach>
		        </tbody>
		      </table>
		      <div class="pagination">
    <ul class="pagination">
			<c:set value="${pageContext.request.contextPath }/chi-tiet-don-hang/${idOrder}/${page-1 }"  var="urlPre"></c:set>
                <c:set value="${pageContext.request.contextPath }/chi-tiet-don-hang/${idOrder}/${page+1 }" var="urlNext"></c:set>
                   <c:if test="${(page-1) > 0 }">
                    <li><a href="${urlPre }"><<</a></li>
                    </c:if>
                    <c:choose>
                    	<c:when test="${sumPage < 4 }">
                    		<c:forEach begin="1"  end="${sumPage }" var="i">
                      	 		<li><a <c:if test="${i == page }">class=active</c:if> href="${pageContext.request.contextPath }/chi-tiet-don-hang/${idOrder}/${i }">${i } <span class="sr-only"></span></a></li>
                      		</c:forEach>
                    	</c:when>
                    	<c:when test="${page<(sumPage-3) && sumPage>3 }">
                    		<c:forEach begin="${page }"  end="${page+3 }" var="i">
                      			<li> <a <c:if test="${i == page }">class=active</c:if> href="${pageContext.request.contextPath }/chi-tiet-don-hang/${idOrder}/${i }">${i } <span class="sr-only"></span></a></li>
                     		 </c:forEach>
                    	</c:when>
                    	<c:when test="${page>=(sumPage-3) && sumPage>3 }">
                    		<c:forEach begin="${sumPage-3 }"  end="${sumPage }" var="i">
                      			 <li><a <c:if test="${i == page }">class=active</c:if> href="${pageContext.request.contextPath }/chi-tiet-don-hang/${idOrder}/${i }">${i } <span class="sr-only"></span></a></li>
                     		 </c:forEach>
                    	</c:when>
                    </c:choose>
                      <c:if test="${page+1 <= sumPage }">
                      <li> <a href="${urlNext }"> >> </a></li>
                    </c:if>
             </ul>       
      </div>
		  </div>
		  <div class="col-md-4 order-summary">
		    <h4><span class="translation_missing" title="translation missing: en.orders.order_summary">Order Summary</span></h4>
		    <hr>
		    <p>Total 
		    <span>
		      (Total payment amount)  
		    </span>
		      <span class="pull-right total">
		        $${sumPriceDetail }
		      </span>
		    </p>
		  </div>
		</div>
		</div>
	      
