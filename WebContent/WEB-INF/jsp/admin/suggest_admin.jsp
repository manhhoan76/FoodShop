<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/templates/taglib.jsp" %>
    <div class="container render-page">
      <div id="flash">
      <c:choose>
				<c:when test="${msg == 0 }">
              <div class="alert alert-danger">Thất bại</div>
              </c:when>
              
				<c:when test="${msg == 1 }">
              <div class="alert alert-success">Thành công</div>
              </c:when>
              </c:choose>
      </div>
      <div class="row">
  <h2 class="text-center">Suggested products management</h2>
</div>
<div class="row product-list">
  <hr>
  <div class="col-md-12">
      <table class="table recenty-viewed">
        <thead>
          <tr>
            <th>Product</th>
            <th>Description</th>
            <th>Price</th>
            <th>User</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
        <c:forEach items="${listSuggest }" var="objSuggest">
          <tr class="product-in-cart">
  <td class="w20">
<img alt="product image" class="img-responsive" src="${pageContext.request.contextPath }/files/${objSuggest.image }">
  </td>
  <td class="w60">
    <b>${objSuggest.name }</b>
    <p>${objSuggest.content }</p>
  </td>
  <td class="w15">
    <p>$${objSuggest.price }</p>
  </td>
  <td class="w15">
    <a href="${pageContext.request.contextPath }/trang-ca-nhan/${objSuggest.id_user}">Profile</a>
  </td>
  <td>
    <a data-confirm="Are you sure?"  href="${pageContext.request.contextPath }/admin/suggest/del/${page}/${objSuggest.id}"><i class="fa fa-times text-danger"></i></a>
  </td>
</tr>
 </c:forEach>
        </tbody>
      </table>
       <div class="pagination">
    <ul class="pagination">
			<c:set value="${pageContext.request.contextPath }/admin/suggest/index/${page-1 }"  var="urlPre"></c:set>
                <c:set value="${pageContext.request.contextPath }/admin/suggest/index/${page+1 }" var="urlNext"></c:set>
                   <c:if test="${(page-1) > 0 }">
                    <li><a href="${urlPre }"><<</a></li>
                    </c:if>
                    <c:choose>
                    	<c:when test="${sumPage < 4 }">
                    		<c:forEach begin="1"  end="${sumPage }" var="i">
                      	 		<li><a <c:if test="${i == page }">class=active</c:if> href="${pageContext.request.contextPath }/admin/suggest/index/${i }">${i } <span class="sr-only"></span></a></li>
                      		</c:forEach>
                    	</c:when>
                    	<c:when test="${page<(sumPage-3) && sumPage>3 }">
                    		<c:forEach begin="${page }"  end="${page+3 }" var="i">
                      			<li> <a <c:if test="${i == page }">class=active</c:if> href="${pageContext.request.contextPath }/admin/suggest/index/${i }">${i } <span class="sr-only"></span></a></li>
                     		 </c:forEach>
                    	</c:when>
                    	<c:when test="${page>=(sumPage-3) && sumPage>3 }">
                    		<c:forEach begin="${sumPage-3 }"  end="${sumPage }" var="i">
                      			 <li><a <c:if test="${i == page }">class=active</c:if> href="${pageContext.request.contextPath }/admin/suggest/index/${i }">${i } <span class="sr-only"></span></a></li>
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

      
