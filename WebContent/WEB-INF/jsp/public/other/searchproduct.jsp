<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/templates/taglib.jsp" %>

    <div class="container render-page">
      <div id="flash">
      </div>
      <div class="row product-list">
  <hr>
  <aside class="col-md-2">
    <form action="${pageContext.request.contextPath }/search" accept-charset="UTF-8" method="get">
  <div>
    Name<input type="text" name="name" id="name" class="form-control" />
  </div>
   <div>
  Max Price
  <input  min="0" step="1000" type="number" name="price" >
  </div>
  <div>Category<select name="category" id="category" class="select form-control">
  <option value="0">All</option>
  <option value="1">Đồ ăn vặt</option>
<option value="2">Hoa quả dầm</option>
<option value="3">Sinh tố</option>
<option value="4">Chè</option>
<option value="5">Trà sữa</option></select>
  </div>
  
  <button type="submit" class="btn btn-primary full-width-button">
    Search
  </button>

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
          <a class="name" href="${pageContext.request.contextPath }/chi-tiet-san-pham/${objProduct.id }">${objProduct.name }</a>
          <p class="price">$${objProduct.price } VNĐ</p>
              <a onclick="addCart(${objProduct.id})" href="javascript:void(0)" class="btn full-width-button">Add To Cart</a> 
        </div>
    </div>
</div>
  </c:forEach>
  <script>
				function  addCart(id) {
					$.ajax({
						url: '${pageContext.request.contextPath }/gio-hang',
						type: 'POST',
						cache: false,
						data: {
								//Dữ liệu gửi đi
								pid : id,
								},
						success: function(data){
							// Xử lý thành công
							$('#numberCart').html(data);
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
			<c:set value="${pageContext.request.contextPath }/search/${page-1 }/${name }/${id_cat }/${priceHight }"  var="urlPre"></c:set>
                <c:set value="${pageContext.request.contextPath }/search/${page+1 }" var="urlNext"></c:set>
                   <c:if test="${(page-1) > 0 }">
                    <li><a href="${urlPre }"><<</a></li>
                    </c:if>
                    <c:choose>
                    	<c:when test="${sumPage < 4 }">
                    		<c:forEach begin="1"  end="${sumPage }" var="i">
                      	 		<li><a <c:if test="${i == page }">class=active</c:if> href="${pageContext.request.contextPath }/search/${i }/${name }/${id_cat }/${priceHight }">${i } <span class="sr-only"></span></a></li>
                      		</c:forEach>
                    	</c:when>
                    	<c:when test="${page<(sumPage-3) && sumPage>3 }">
                    		<c:forEach begin="${page }"  end="${page+3 }" var="i">
                      			<li> <a <c:if test="${i == page }">class=active</c:if> href="${pageContext.request.contextPath }/search/${i }/${name }/${id_cat }/${priceHight }">${i } <span class="sr-only"></span></a></li>
                     		 </c:forEach>
                    	</c:when>
                    	<c:when test="${page>=(sumPage-3) && sumPage>3 }">
                    		<c:forEach begin="${sumPage-3 }"  end="${sumPage }" var="i">
                      			 <li><a <c:if test="${i == page }">class=active</c:if> href="${pageContext.request.contextPath }/search/${i }/${name }/${id_cat }/${priceHight }">${i } <span class="sr-only"></span></a></li>
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

      
