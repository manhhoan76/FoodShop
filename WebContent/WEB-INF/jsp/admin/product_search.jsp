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
              <c:when test="${msg == 3 }">
              <div class="alert alert-danger">Bạn không thể xóa</div>
              </c:when>
              </c:choose>
      </div>
      
<div class="row product-list">
  <hr>
  <aside class="col-md-2">
    <form action="${pageContext.request.contextPath }/admin/product/search" accept-charset="UTF-8" method="get">
  <div>
    Name<input type="text" name="name" id="name" class="form-control">
  </div>
  <div>
  Max Price
  <input  min="0" step="1000" type="number" name="price" >
  </div>
  <div>Category<select name="category" id="category" class="select form-control">
  <option value="">All</option>
  <option value="1">Đồ ăn vặt</option>
<option value="2">Hoa quả dầm</option>
<option value="3">Sinh tố</option>
<option value="4">Chè</option>
<option value="5">Trà sữa</option></select>
  </div>
  <button type="submit" class="btn btn-primary full-width-button">
    Search
  </button>

    <br><br>
    <div>
      <a class="btn btn-success full-width-button" href="${pageContext.request.contextPath }/admin/product/add">Add product</a>
    </div>
</form>
  </aside>
  <div class="col-md-10">
  
   <c:forEach items="${listProduct }" var="objProduct">
  		<div class="col-sm-6 col-md-3">
    <div class="pro">
        <div class="ov-img">
            <a href="${pageContext.request.contextPath }/chi-tiet-san-pham/${objProduct.id }">
            <img alt="product image" class="img-responsive" src="${pageContext.request.contextPath }/files/${objProduct.image }" /></a>
        </div>
        <div class="profile">
          <div class="product-rate" data-score="NaN"></div>
          <a class="name" href="${pageContext.request.contextPath }/chi-tiet-san-pham/${objProduct.id }">${objProduct.name }</a> <br>
          <p class="price">$${objProduct.price } VNĐ</p>
			 <a class="inline-btn btn btn-danger" data-confirm="Are you sure?" href="${pageContext.request.contextPath }/admin/product/del/${objProduct.id }">Delete</a>
          <a class="inline-btn btn btn-success" href="${pageContext.request.contextPath }/admin/product/edit/${objProduct.id }">Edit</a>	       
          <div id="active-${objProduct.id  }">
          <a class="inline-btn btn btn-info" onclick="active(${objProduct.id},${objProduct.slide})" href="javascript:void(0)">Trend</a>
			</div>        
        </div>
    </div>
</div>
  </c:forEach>
  <script>
				function  active(id, slide) {
					$.ajax({
						url: '${pageContext.request.contextPath }/admin/product/active',
						type: 'POST',
						cache: false,
						data: {
								//Dữ liệu gửi đi
								uid : id,
								uactive : slide,
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
			<c:set value="${pageContext.request.contextPath }/admin/product/search/${page-1 }/${name }/${id_cat }/${priceHight }"  var="urlPre"></c:set>
                <c:set value="${pageContext.request.contextPath }/admin/product/search/${page+1 }/${name }/${id_cat }/${priceHight }" var="urlNext"></c:set>
                   <c:if test="${(page-1) > 0 }">
                    <li><a href="${urlPre }"><<</a></li>
                    </c:if>
                    <c:choose>
                    	<c:when test="${sumPage < 4 }">
                    		<c:forEach begin="1"  end="${sumPage }" var="i">
                      	 		<li><a <c:if test="${i == page }">class=active</c:if> href="${pageContext.request.contextPath }/admin/product/search/${i }/${name }/${id_cat }/${priceHight }">${i } <span class="sr-only"></span></a></li>
                      		</c:forEach>
                    	</c:when>
                    	<c:when test="${page<(sumPage-3) && sumPage>3 }">
                    		<c:forEach begin="${page }"  end="${page+3 }" var="i">
                      			<li> <a <c:if test="${i == page }">class=active</c:if> href="${pageContext.request.contextPath }/admin/product/search/${i }/${name }/${id_cat }/${priceHight }">${i } <span class="sr-only"></span></a></li>
                     		 </c:forEach>
                    	</c:when>
                    	<c:when test="${page>=(sumPage-3) && sumPage>3 }">
                    		<c:forEach begin="${sumPage-3 }"  end="${sumPage }" var="i">
                      			 <li><a <c:if test="${i == page }">class=active</c:if> href="${pageContext.request.contextPath }/admin/product/search/${i }/${name }/${id_cat }/${priceHight }">${i } <span class="sr-only"></span></a></li>
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
