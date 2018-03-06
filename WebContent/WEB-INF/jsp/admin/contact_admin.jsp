<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/templates/taglib.jsp" %>
    <div class="container render-page">
      <div id="flash">
      <c:choose>
				<c:when test="${msg == 0 }">
              <div class="alert alert-danger">Xóa thất bại</div>
              </c:when>
              
				<c:when test="${msg == 1 }">
              <div class="alert alert-success">Xóa thành công</div>
              </c:when>
              </c:choose>
      </div>
      <div class="row">
  <div class="col-md-8 col-md-offset-2">
    <h2>Orders Management</h2>
    <form id="seach-order" class="search-box" action="http://tso-plus.herokuapp.com/admin/orders" accept-charset="UTF-8" method="get"><input name="utf8" type="hidden" value="✓">
      <select name="status_id" id="status_id" class="select form-control status-id-select">
      <option value="">All</option>
      <option value="1">Đã đọc</option>
<option value="2">Chưa đọc</option>
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
              <th>Name</th>
              <th>Email</th>
              <th>Address</th>
               <th>Phone</th>
                <th>Content</th>
                <th></th>
            </tr>
          </thead>
          <tbody>
          <c:forEach items="${listContact }" var="objContact">
          <tr>
          <c:choose>
           <c:when test="${objContact.readed == 0 }">
           	<tr style="color: #D74B33;" class="product-in-cart">
           </c:when>
           <c:otherwise>
          	 <tr  class="product-in-cart">
           </c:otherwise>
          </c:choose>
          
          	
			  <td class="w15">
			    <a href="http://tso-plus.herokuapp.com/admin/orders/1">#${objContact.id }</a>
			  </td>
			  <td class="w25">
			   <c:choose>
           <c:when test="${objContact.readed == 0 }">
           <a href="" style="color: #D74B33;">${objContact.name }</a>
           </c:when>
           <c:otherwise>
           <a href="" >${objContact.name }</a>
           </c:otherwise>
          </c:choose>
			    
			  </td>
			  <td class="w15">
			    <p>${objContact.email }</p>
			  </td>
			  <td class="w15">
			    <p>${objContact.address }</p>
			  </td>
			  <td >
				${objContact.phone }  
			  </td>
			  <td class="w25">
			    <p>${objContact.content }</p>
			  </td>
			  <td class="w25">
			    <a data-confirm="Are you sure?"  href="${pageContext.request.contextPath }/admin/contact/del/${objContact.id}">Delete</a>
			  </td>
			</tr>
          </c:forEach>
          </tbody>
        </table>
        <div class="pagination">
    <ul class="pagination">
			<c:set value="${pageContext.request.contextPath }/admin/contact/index/${page-1 }"  var="urlPre"></c:set>
                <c:set value="${pageContext.request.contextPath }/admin/contact/index/${page+1 }" var="urlNext"></c:set>
                   <c:if test="${(page-1) > 0 }">
                    <li><a href="${urlPre }"><<</a></li>
                    </c:if>
                    <c:choose>
                    	<c:when test="${sumPage < 4 }">
                    		<c:forEach begin="1"  end="${sumPage }" var="i">
                      	 		<li><a <c:if test="${i == page }">class=active</c:if> href="${pageContext.request.contextPath }/admin/contact/index/${i }">${i } <span class="sr-only"></span></a></li>
                      		</c:forEach>
                    	</c:when>
                    	<c:when test="${page<(sumPage-3) && sumPage>3 }">
                    		<c:forEach begin="${page }"  end="${page+3 }" var="i">
                      			<li> <a <c:if test="${i == page }">class=active</c:if> href="${pageContext.request.contextPath }/admin/contact/index/${i }">${i } <span class="sr-only"></span></a></li>
                     		 </c:forEach>
                    	</c:when>
                    	<c:when test="${page>=(sumPage-3) && sumPage>3 }">
                    		<c:forEach begin="${sumPage-3 }"  end="${sumPage }" var="i">
                      			 <li><a <c:if test="${i == page }">class=active</c:if> href="${pageContext.request.contextPath }/admin/contact/index/${i }">${i } <span class="sr-only"></span></a></li>
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

      
