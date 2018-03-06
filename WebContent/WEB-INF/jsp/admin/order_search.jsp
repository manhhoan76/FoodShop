<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/templates/taglib.jsp" %>
    <div class="container render-page">
      <div id="flash">
      </div>
      <div class="row">
  <div class="col-md-8 col-md-offset-2">
    <h2>Orders Management</h2>
    <form id="seach-order" class="search-box" action="${pageContext.request.contextPath }/admin/order/search" accept-charset="UTF-8" method="get">
      <select name="status_id" id="status_id" class="select form-control status-id-select">
	<c:forEach items="${listStatus }" var="objStatus">
		<option value="${objStatus.id }">${objStatus.name }</option>
	</c:forEach>
</select>
      <button class="btn btn-danger" form="seach-order" type="submit">Search</button>
</form>  </div>
</div>
<div class="row">
    <div class="col-md-8 col-md-offset-2">
      <div class="user-table">
        <table class="table ecommerce-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Username</th>
               <th>Address</th>
              <th>Total</th>
              <th>Status</th>
               <th></th>
            </tr>
          </thead>
          <tbody>
          <c:forEach items="${listOrder }" var="objOrder">
            <tr class="product-in-cart">
  <td class="w10">
    <a href="${pageContext.request.contextPath }/chi-tiet-don-hang/${objOrder.id }">#${objOrder.id }</a>
  </td>
  <td class="w20">
    <a href="${pageContext.request.contextPath }/trang-ca-nhan/${objOrder.id_user }" >${objOrder.username }</a>
  </td>
  <td class="w10">
    <p>${objOrder.address }</p>
  </td>
  <td class="w10">
    <p>$${objOrder.sumPrice }</p>
  </td>
  <td class="w10">
    <p>${objOrder.status }</p>
  </td>
  <c:if test="${objOrder.id_order_status == 5 }">
  </c:if>
 <c:if test="${objOrder.id_order_status != 5 }">
  <td>
  	 <form class="search-box" action="${pageContext.request.contextPath }/admin/order/change/${page}/${objOrder.id}" accept-charset="UTF-8" method="get">
      <select name="id_order_status">
	<c:forEach items="${listStatus }" var="objStatus">
		 <c:choose>
	    	<c:when test="${objStatus.id == objOrder.id_order_status }">
	    	 <option  selected ="selected"  value="${objStatus.id }">${objStatus.name }</option>
	    	</c:when>
	    	<c:otherwise>
	    	<option value="${objStatus.id }">${objStatus.name }</option>
	    	</c:otherwise>
	    </c:choose>
	</c:forEach>
	</select>
      <button class="btn btn-info" type="submit">Change</button>
</form> 
  </td>
   </c:if>
</tr>
</c:forEach>
          </tbody>
        </table>
        <div class="pagination">
    <ul class="pagination">
			<c:set value="${pageContext.request.contextPath }/admin/order/search/${page-1 }/${id}"  var="urlPre"></c:set>
                <c:set value="${pageContext.request.contextPath }/admin/order/search/${page+1 }/${id}" var="urlNext"></c:set>
                   <c:if test="${(page-1) > 0 }">
                    <li><a href="${urlPre }"><<</a></li>
                    </c:if>
                    <c:choose>
                    	<c:when test="${sumPage < 4 }">
                    		<c:forEach begin="1"  end="${sumPage }" var="i">
                      	 		<li><a <c:if test="${i == page }">class=active</c:if> href="${pageContext.request.contextPath }/admin/order/search/${i }/${id}">${i } <span class="sr-only"></span></a></li>
                      		</c:forEach>
                    	</c:when>
                    	<c:when test="${page<(sumPage-3) && sumPage>3 }">
                    		<c:forEach begin="${page }"  end="${page+3 }" var="i">
                      			<li> <a <c:if test="${i == page }">class=active</c:if> href="${pageContext.request.contextPath }/admin/order/search/${i }/${id}">${i } <span class="sr-only"></span></a></li>
                     		 </c:forEach>
                    	</c:when>
                    	<c:when test="${page>=(sumPage-3) && sumPage>3 }">
                    		<c:forEach begin="${sumPage-3 }"  end="${sumPage }" var="i">
                      			 <li><a <c:if test="${i == page }">class=active</c:if> href="${pageContext.request.contextPath }/admin/order/search/${i }/${id}">${i } <span class="sr-only"></span></a></li>
                     		 </c:forEach>
                    	</c:when>
                    </c:choose>
                      <c:if test="${page+1 <= sumPage }">
                      <li> <a href="${urlNext }"> >> </a></li>
                    </c:if>
             </ul>       
      </div>
      </div>
    </div>
</div>

      
