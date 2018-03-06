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
    <h2>Management Comment</h2>
    <form id="seach-user" class="search-box" action="http://tso-plus.herokuapp.com/admin/comments" accept-charset="UTF-8" method="get"><input name="utf8" type="hidden" value="✓">
        <input type="text" name="keyword" class="form-control search-query" placeholder="Search user by user name, email">
        <button class="btn btn-danger" form="seach-user" type="submit">
          Search
        </button>
      </form></div>
  </div>
</div>
<div class="row">
    <div class="col-md-8 col-md-offset-2">
  <div class="user-table">
    <table class="table ecommerce-table">
      <thead>
        <tr>
          <th>User</th>
          <th>User name</th>
          <th>Content</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
      <c:forEach items="${listComment }"  var="objUser">
      	<tr class="user-row">
  <td class="w20">
    <img alt="profile image" class="img-responsive" src="${pageContext.request.contextPath }/files/${objUser.image}">
  </td>
  <td class="w20">
    <strong><a href="${pageContext.request.contextPath }/trang-ca-nhan/${objUser.id_user }">${objUser.username}</a></strong>
  </td>
  <td class="w45">
    ${objUser.content }
  </td>
  <td>
      <a data-confirm="Are you sure?" class="text-danger"  href="${pageContext.request.contextPath }/admin/comment/del/${objUser.id}">Delete | </a>
  	<div style="display: inline-block;" id="active-${objUser.id}">
  	<a  class="text-danger" onclick="active(${objUser.id},${objUser.slide})" href="javascript:void(0)">
  	<c:choose>
	  	<c:when test="${objUser.slide == 0 }">
	  	Slide
	  	</c:when>
	  	<c:otherwise>
	  		 None
	  	</c:otherwise>
	  	</c:choose>
	  	</a>	
	  	</div>
  </td>
</tr>
      </c:forEach>
          
      </tbody>
    </table>
    <script>
				function  active(id, active) {
					$.ajax({
						url: '${pageContext.request.contextPath }/admin/comment/active',
						type: 'POST',
						cache: false,
						data: {
								//Dữ liệu gửi đi
								uid : id,
								uactive : active,
								},
						success: function(data){
							// Xử lý thành công
							$('#active-'+id).html(data);
						},
						error: function (){
						// Xử lý nếu có lỗi
						alert('Loi');
						}
					});
				}
						
				
				
			</script>
    <div class="pagination">
    <ul class="pagination">
			<c:set value="${pageContext.request.contextPath }/admin/comment/index/${page-1 }"  var="urlPre"></c:set>
                <c:set value="${pageContext.request.contextPath }/admin/comment/index/${page+1 }" var="urlNext"></c:set>
                   <c:if test="${(page-1) > 0 }">
                    <li><a href="${urlPre }"><<</a></li>
                    </c:if>
                    <c:choose>
                    	<c:when test="${sumPage < 4 }">
                    		<c:forEach begin="1"  end="${sumPage }" var="i">
                      	 		<li><a <c:if test="${i == page }">class=active</c:if> href="${pageContext.request.contextPath }/admin/comment/index/${i }">${i } <span class="sr-only"></span></a></li>
                      		</c:forEach>
                    	</c:when>
                    	<c:when test="${page<(sumPage-3) && sumPage>3 }">
                    		<c:forEach begin="${page }"  end="${page+3 }" var="i">
                      			<li> <a <c:if test="${i == page }">class=active</c:if> href="${pageContext.request.contextPath }/admin/comment/index/${i }">${i } <span class="sr-only"></span></a></li>
                     		 </c:forEach>
                    	</c:when>
                    	<c:when test="${page>=(sumPage-3) && sumPage>3 }">
                    		<c:forEach begin="${sumPage-3 }"  end="${sumPage }" var="i">
                      			 <li><a <c:if test="${i == page }">class=active</c:if> href="${pageContext.request.contextPath }/admin/comment/index/${i }">${i } <span class="sr-only"></span></a></li>
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
