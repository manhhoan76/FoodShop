<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/templates/taglib.jsp" %>
    <div class="container render-page">
      <div id="flash">
      </div>
      <div class="row">
  <aside class="col-md-3 categories-list">
    <div id="main-menu">
      <div class="list-group panel">
      <c:forEach   items="${listCat }"  var="objCat">
       <div class="list-group-item list-group-item-default">
           ${objCat.name }
            <a data-confirm="Are you sure?" class="text-danger"  href="${pageContext.request.contextPath }/admin/cat/del/${objCat.id}"><i class="fa fa-times pull-right"></i></a>
            <a class="fa fa-edit pull-right" data-remote="true" onclick="edit(${objCat.id})" href="javascript:void(0)"></a>
		</div>         
      </c:forEach>
        </div>
    </div>
    <a class="btn btn-success full-width-button"  onclick="show()" href="javascript:void(0)" >New category</a>
  </aside>
   <script>
				function  show() {
					$.ajax({
						url: '${pageContext.request.contextPath }/admin/cat/show',
						type: 'POST',
						cache: false,
						data: {
								//Dữ liệu gửi đi
								},
						success: function(data){
							// Xử lý thành công
							$('#category_show').html(data);
						},
						error: function (){
						// Xử lý nếu có lỗi
						alert('Loi');
						}
					});
				}
				function  edit(id) {
					$.ajax({
						url: '${pageContext.request.contextPath }/admin/cat/showEdit',
						type: 'POST',
						cache: false,
						data: {
								//Dữ liệu gửi đi
								cid : id,
								},
						success: function(data){
							// Xử lý thành công
							$('#category_show').html(data);
						},
						error: function (){
						// Xử lý nếu có lỗi
						alert('Loi');
						}
					});
				}		
			</script>
  <div id="category_show" class="col-md-5 col-md-offset-1">
  </div>
</div>

      
