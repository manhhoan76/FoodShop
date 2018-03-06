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
  <div class="col-md-8 col-md-offset-2">
    <h2>Management Discount</h2>
    <form id="seach-user" class="search-box" action="http://tso-plus.herokuapp.com/admin/comments" accept-charset="UTF-8" method="get"><input name="utf8" type="hidden" value="✓">
        <input type="text" name="keyword" class="form-control search-query" placeholder="Search user by user name, email">
        <button class="btn btn-danger" form="seach-user" type="submit">
          Search
        </button>
        <a style="margin-left: 2px;" class="btn btn-success" href="${pageContext.request.contextPath }/admin/discount/add">
          Make
        </a>
      </form></div>
  </div>
</div>
<div class="row">
    <div class="col-md-8 col-md-offset-2">
  <div class="user-table">
    <table class="table ecommerce-table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Percent</th>
          <th>Used</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
      <c:forEach items="${listDis }"  var="objDis">
      	<tr class="user-row">
  <td class="w10">
  ${objDis.id }
  </td>
  <td class="w40">
  ${objDis.name }
  </td>
   <td class="w20">
  ${objDis.percent }
  </td>
   <td class="w20">
   <c:if test="${objDis.used == 1 }">
 	Used  
   </c:if>
  </td>
  <td>
      <a data-confirm="Are you sure?" class="text-danger"  href="${pageContext.request.contextPath }/admin/discount/del/${objDis.id}">Delete | </a>
  </td>
</tr>
      </c:forEach>
          
      </tbody>
    </table>
    <div class="pagination">
    <ul class="pagination">
			<c:set value="${pageContext.request.contextPath }/admin/discount/index/${page-1 }"  var="urlPre"></c:set>
                <c:set value="${pageContext.request.contextPath }/admin/discount/index/${page+1 }" var="urlNext"></c:set>
                   <c:if test="${(page-1) > 0 }">
                    <li><a href="${urlPre }"><<</a></li>
                    </c:if>
                    <c:choose>
                    	<c:when test="${sumPage < 4 }">
                    		<c:forEach begin="1"  end="${sumPage }" var="i">
                      	 		<li><a <c:if test="${i == page }">class=active</c:if> href="${pageContext.request.contextPath }/admin/discount/index/${i }">${i } <span class="sr-only"></span></a></li>
                      		</c:forEach>
                    	</c:when>
                    	<c:when test="${page<(sumPage-3) && sumPage>3 }">
                    		<c:forEach begin="${page }"  end="${page+3 }" var="i">
                      			<li> <a <c:if test="${i == page }">class=active</c:if> href="${pageContext.request.contextPath }/admin/discount/index/${i }">${i } <span class="sr-only"></span></a></li>
                     		 </c:forEach>
                    	</c:when>
                    	<c:when test="${page>=(sumPage-3) && sumPage>3 }">
                    		<c:forEach begin="${sumPage-3 }"  end="${sumPage }" var="i">
                      			 <li><a <c:if test="${i == page }">class=active</c:if> href="${pageContext.request.contextPath }/admin/discount/index/${i }">${i } <span class="sr-only"></span></a></li>
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
