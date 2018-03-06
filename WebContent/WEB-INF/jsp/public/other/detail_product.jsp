<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/templates/taglib.jsp" %>
    <div class="container render-page">
      <div id="flash">
      </div>
      
  <head>
    
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Product Detail</title>
    <link href="../../maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700" rel="stylesheet">

  </head>

<detailbody>
  
  <div class="container">
    <div class="detailproduct-card">
      <div class="container-fliud">
        <div class="wrapper row">
          <div class="preview col-md-6">
            
            <div class="preview-pic tab-content">
              <div class="tab-pane active" id="pic-1"><img alt="alt.product_image" class="img-responsive" src="${pageContext.request.contextPath }/files/${objProduct.image }" /></div>
              <div class="tab-pane" id="pic-2"><img alt="alt.product_image" class="img-responsive" src="${pageContext.request.contextPath }/files/${objProduct.image }" /></div>
              <div class="tab-pane" id="pic-3"><img alt="alt.product_image" class="img-responsive" src="${pageContext.request.contextPath }/files/${objProduct.image }" /></div>
              <div class="tab-pane" id="pic-4"><img alt="alt.product_image" class="img-responsive" src="${pageContext.request.contextPath }/files/${objProduct.image }" /></div>
              <div class="tab-pane" id="pic-5"><img alt="alt.product_image" class="img-responsive" src="${pageContext.request.contextPath }/files/${objProduct.image }" /></div>
            </div>
            <ul class="preview-thumbnail nav nav-tabs">
              <li class="active"><a data-target="#pic-1" data-toggle="tab"><img alt="alt.product_image" class="img-responsive" src="${pageContext.request.contextPath }/files/${objProduct.image }" /></a></li>
              <li><a data-target="#pic-2" data-toggle="tab"><img alt="alt.product_image" class="img-responsive" src="${pageContext.request.contextPath }/files/${objProduct.image }" /></a></li>
              <li><a data-target="#pic-3" data-toggle="tab"><img alt="alt.product_image" class="img-responsive" src="${pageContext.request.contextPath }/files/${objProduct.image }" /></a></li>
              <li><a data-target="#pic-4" data-toggle="tab"><img alt="alt.product_image" class="img-responsive" src="${pageContext.request.contextPath }/files/${objProduct.image }" /></a></li>
              <li><a data-target="#pic-5" data-toggle="tab"><img alt="alt.product_image" class="img-responsive" src="${pageContext.request.contextPath }/files/${objProduct.image }" /></a></li>
            </ul>
            
          </div>
          <div class="details col-md-6">
            <h3 class="product-title">${objProduct.name }</h3>
            <div class="rating">
                <div class="product-rate" data-score="4.5">
                </div>
            </div>
            <p class="product-description">${objProduct.description }</p>
            <h4 class="price">Current Price: <span>$${objProduct.price }</span></h4>
            <div class="action">
            <a onclick="addCart(${objProduct.id})" href="javascript:void(0)" class="add-to-cart btn btn-default btn-add-to-cart">Add To Cart</a>
            </div>
            <div><br><b>SHARE PRODUCT</b>
              <div class='social-share-button' data-title='${objProduct.name }' data-img=''
data-url='' data-desc='' data-popup='true' data-via=''>
<a rel="nofollow " data-site="twitter" class="social-share-button-twitter" onclick="return SocialShareButton.share(this);" title="Share to Twitter" href="#"></a>
<a rel="nofollow " data-site="facebook" class="social-share-button-facebook" onclick="return SocialShareButton.share(this);" title="Share to Facebook" href="#"></a>
<a rel="nofollow " data-site="google_plus" class="social-share-button-google_plus" onclick="return SocialShareButton.share(this);" title="Share to Google+" href="#"></a>
<a rel="nofollow " data-site="delicious" class="social-share-button-delicious" onclick="return SocialShareButton.share(this);" title="Share to Delicious" href="#"></a>
<a rel="nofollow " data-site="tumblr" class="social-share-button-tumblr" onclick="return SocialShareButton.share(this);" title="Share to Tumblr" href="#"></a>
<a rel="nofollow " data-site="pinterest" class="social-share-button-pinterest" onclick="return SocialShareButton.share(this);" title="Share to Pinterest" href="#"></a>
</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</detailbody>
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
<div class="container">
  <div class="row">
    <div class="col-sm-10 col-sm-offset-1" id="logout">
        <div class="page-header">
            <h3 class="reviews">Leave your comment</h3>
            <div>
            
            </div>
            
        </div>
        <section class="comment_form">
              <form class="new_comment"  action="${pageContext.request.contextPath }/comment/${idProduct}" accept-charset="UTF-8" method="post">
				  <label class="col-sm-2 control-label">Comment</label>
				  <div class="col-sm-10">
				    <textarea placeholder="New comment..." rows="5" class="form-control" name="content"></textarea>
				  </div>
				
				  <div class="col-sm-offset-2 col-sm-3">
				      <input type="submit" name="commit" value="Submit comment" class="btn btn-success btn-circle text-uppercase" data-disable-with="Submit comment">  </div>
				  <div class="col-sm-7"><br><br></div>
				</form>
            </section>
 <c:if test="${objLogin.username == '' }">
 	<br>
          <p> Please <a href="${pageContext.request.contextPath }/login">log in</a> to post comment for this product!</p>
          <p> New user? <a href="${pageContext.request.contextPath }/dang-ky">Register now!</a> </p>
          <br>
 </c:if>
          
        <ul class="media-list col-sm-12">
          <div><br></br></div>
            <ol class="comments">
           <c:forEach items="${listCommentByID }" var="objComment">
           		<li class="media">
    <a class="pull-left" href="${pageContext.request.contextPath }/trang-ca-nhan/${objComment.id_user}">
      <img style="width: 100px; height: 100px;" class="media-object img-circle" src="${pageContext.request.contextPath }/files/${objComment.image }" alt="${objComment.username }" />
</a>    <div class="media-body">
      <div class="well well-lg">
          <h4>
            <span class="user media-heading text-uppercase reviews">
                <a href="${pageContext.request.contextPath }/trang-ca-nhan/${objComment.id_user}">${objComment.username}</a>
            </span> 
          </h4>
          <p class="media-comment">
            ${objComment.content }
         </p>
      </div>              
    </div>
  </li>
           </c:forEach> 
  
  <div class="pagination">
  <ul class="pagination">
			<c:set value="${pageContext.request.contextPath }/chi-tiet-san-pham/${idProduct}/${page-1 }"  var="urlPre"></c:set>
                <c:set value="${pageContext.request.contextPath }/chi-tiet-san-pham/${idProduct}/${page+1 }" var="urlNext"></c:set>
                   <c:if test="${(page-1) > 0 }">
                    <li><a href="${urlPre }"><<</a></li>
                    </c:if>
                    <c:choose>
                    	<c:when test="${sumPage < 4 }">
                    		<c:forEach begin="1"  end="${sumPage }" var="i">
                      	 		<li><a <c:if test="${i == page }">class=active</c:if> href="${pageContext.request.contextPath }/chi-tiet-san-pham/${idProduct}/${i }">${i } <span class="sr-only"></span></a></li>
                      		</c:forEach>
                    	</c:when>
                    	<c:when test="${page<(sumPage-3) && sumPage>3 }">
                    		<c:forEach begin="${page }"  end="${page+3 }" var="i">
                      			<li> <a <c:if test="${i == page }">class=active</c:if> href="${pageContext.request.contextPath }/chi-tiet-san-pham/${idProduct}/${i }">${i } <span class="sr-only"></span></a></li>
                     		 </c:forEach>
                    	</c:when>
                    	<c:when test="${page>=(sumPage-3) && sumPage>3 }">
                    		<c:forEach begin="${sumPage-3 }"  end="${sumPage }" var="i">
                      			 <li><a <c:if test="${i == page }">class=active</c:if> href="${pageContext.request.contextPath }/chi-tiet-san-pham/${idProduct}/${i }">${i } <span class="sr-only"></span></a></li>
                     		 </c:forEach>
                    	</c:when>
                    </c:choose>
                      <c:if test="${page+1 <= sumPage }">
                      <li> <a href="${urlNext }"> >> </a></li>
                    </c:if>
             </ul>       
   </div>
</ol>


        </ul> 
    </div>
  </div>
</div>
      
