<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/templates/taglib.jsp" %>
    <div class="container render-page">
      <div id="flash">
      </div>
      <c:choose>
      	<c:when test="${objLogin.username == '' }">
      		<div class="center">
 			 <body>
		    <h1>Chào mừng đến với website đồ ăn vặt H2-Plus</h1>
		      <h2>
		        Hãy đăng ký tài khoản rồi chọn cho mình một vài món ăn nhé!
		      </h2>
    <a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath }/dang-ky">Đăng ký tài khoản!</a>
  </body>
</div>
      	</c:when>
      	<c:otherwise>
      		<div class="center">
  <body>
    <h1>Chào mừng ${objLogin.username } </h1> 
      <h2>
        Hãy chọn cho mình một vài món ăn rồi thưởng thức nhé! 
		<br />
		Cảm ơn bạn đã ủng hộ cửa hàng!
      </h2>
  </body>
</div>
      	</c:otherwise>
      </c:choose>


<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <style>
  .carousel-inner > .item > img,
  .carousel-inner > .item > a > img {
      width: 100%;
      margin: auto;
  }
  </style>
</head>


<div class="container">

  <br>
  <div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
      <li data-target="#myCarousel" data-slide-to="3"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
    <c:forEach items="${listSlide }" begin="0" end="0" var="objSlide">
    	<div class="item active">
    		<c:if test="${objSlide.link == '' }">
    			<a href="${pageContext.request.contextPath }/"><img src="${pageContext.request.contextPath }/files/${objSlide.name}" alt="Chania" width="460" height="345"></a>
    		</c:if>
    		<c:if test="${objSlide.link != '' }">
    			<a href="${objSlide.link }"><img src="${pageContext.request.contextPath }/files/${objSlide.name}" alt="Chania" width="460" height="345"></a>
    		</c:if>
      </div>
    </c:forEach>
     <c:forEach items="${listSlide }" begin="1"  var="objSlide">
    	<div class="item">
    		<c:if test="${objSlide.link == '' }">
    			<a href="${pageContext.request.contextPath }/"><img src="${pageContext.request.contextPath }/files/${objSlide.name}" alt="Chania" width="460" height="345"></a>
    		</c:if>
    		<c:if test="${objSlide.link != '' }">
    			<a href="${objSlide.link }"><img src="${pageContext.request.contextPath }/files/${objSlide.name}" alt="Chania" width="460" height="345"></a>
    		</c:if>
      </div>
    </c:forEach>
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
</div>

  <h1>Hot trend products</h1>
  <div class="row">
    <div class="col-md-12">
      <div class="hot-trends-items">
      <c:forEach items="${listProductSlide }" var="objTrend">
  <div class="col-sm-6 col-md-3">
    <div class="pro">
        <div class="ov-img">
            <a href="${pageContext.request.contextPath }/chi-tiet-san-pham/${objTrend.id }"><img alt="product image" class="img-responsive" src="${pageContext.request.contextPath }/files/${objTrend.image }" /></a>
        </div>
        <div class="profile">
          <div class="product-rate" data-score="4.0"></div>
          <a class="name" href="${pageContext.request.contextPath }/chi-tiet-san-pham/${objTrend.id }">${objTrend.name }</a>
          <p class="price">$${objTrend.price  } VNĐ</p>
 		<a onclick="addCart(${objTrend.id})" href="javascript:void(0)" class="btn full-width-button">Add To Cart</a>  </div>
    </div>
</div>
</c:forEach>
</div>
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
    </div>
  </div>
</div>

<head>
    <link rel="stylesheet" href="../maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css">
</head>
<div class="container">
<div class="row">
      <div class="col-md-12" data-wow-delay="0.2s">
          <div class="carousel slide" data-ride="carousel" id="quote-carousel">
              <!-- Bottom Carousel Indicators -->
              <ol class="carousel-indicators">
              <c:forEach items="${listCommentSlide }" begin="0" end="0" var="objComment">
                  <li data-target="#quote-carousel" data-slide-to="0" class="active">
                  <img class="img-responsive " src="${pageContext.request.contextPath }/files/${objComment.image }" alt="">
                  </li>
                  </c:forEach>
                  <c:forEach items="${listCommentSlide }" begin="1"  var="objComment">
                  <li data-target="#quote-carousel" data-slide-to="${objComment.id }">
                  <img class="img-responsive " src="${pageContext.request.contextPath }/files/${objComment.image }" alt="">
                  </li>
                  </c:forEach>
              </ol>

              <!-- Carousel Slides / Quotes -->
              <div class="carousel-inner text-center">

                  <!-- Quote 1 -->
                  <c:forEach items="${listCommentSlide }" begin="0" end="0" var="objComment">
                  <div class="item active">
                      <blockquote>
                          <div class="row">
                              <div class="col-sm-8 col-sm-offset-2">
                                  <p>${objComment.content }</p>
                                  <a href="${pageContext.request.contextPath }/trang-ca-nhan/${objComment.id_user }"><small>${objComment.username }</small></a>
                              </div>
                          </div>
                      </blockquote>
                  </div>
                  </c:forEach>
                  <c:forEach items="${listCommentSlide }" begin="1" var="objComment">
                  <div class="item">
                      <blockquote>
                          <div class="row">
                              <div class="col-sm-8 col-sm-offset-2">
                                  <p>${objComment.content }</p>
                                  <a href="${pageContext.request.contextPath }/trang-ca-nhan/${objComment.id_user }"><small>${objComment.username }</small></a>
                              </div>
                          </div>
                      </blockquote>
                  </div>
                  </c:forEach>
              </div>

              <!-- Carousel Buttons Next/Prev -->
              <a data-slide="prev" href="#quote-carousel" class="left carousel-control"><i class="fa fa-chevron-left"></i></a>
              <a data-slide="next" href="#quote-carousel" class="right carousel-control"><i class="fa fa-chevron-right"></i></a>
          </div>
      </div>
  </div>
</div>
<hr class="divider"/>
<center>
</center>

<div class="container new-product">
  <h1>New products</h1>
  <c:forEach items="${listProductNew }" var="objNew">
  <div class="col-sm-6 col-md-3">
    <div class="pro">
        <div class="ov-img">
            <a href="${pageContext.request.contextPath }/chi-tiet-san-pham/${objNew.id }">
            <img alt="${objNew.name  }" class="img-responsive" src="${pageContext.request.contextPath }/files/${objNew.image }" /></a>
        </div>
        <div class="profile">
          <div class="product-rate" data-score="5.0"></div>
          <a class="name" href="${pageContext.request.contextPath }/chi-tiet-san-pham/${objNew.id }">${objNew.name  }</a>
          <p class="price">$${objNew.price }VNĐ</p>
          <a onclick="addCart(${objNew.id})" href="javascript:void(0)" class="btn full-width-button">Add To Cart</a>
        </div>
    </div>
</div>
</c:forEach>
 <div class="pagination">
    <ul class="pagination">
			<c:set value="${pageContext.request.contextPath }/${page-1 }"  var="urlPre"></c:set>
                <c:set value="${pageContext.request.contextPath }/${page+1 }" var="urlNext"></c:set>
                   <c:if test="${(page-1) > 0 }">
                    <li><a href="${urlPre }"><<</a></li>
                    </c:if>
                    <c:choose>
                    	<c:when test="${sumPage < 4 }">
                    		<c:forEach begin="1"  end="${sumPage }" var="i">
                      	 		<li><a <c:if test="${i == page }">class=active</c:if> href="${pageContext.request.contextPath }/${i }">${i } <span class="sr-only"></span></a></li>
                      		</c:forEach>
                    	</c:when>
                    	<c:when test="${page<(sumPage-3) && sumPage>3 }">
                    		<c:forEach begin="${page }"  end="${page+3 }" var="i">
                      			<li> <a <c:if test="${i == page }">class=active</c:if> href="${pageContext.request.contextPath }/${i }">${i } <span class="sr-only"></span></a></li>
                     		 </c:forEach>
                    	</c:when>
                    	<c:when test="${page>=(sumPage-3) && sumPage>3 }">
                    		<c:forEach begin="${sumPage-3 }"  end="${sumPage }" var="i">
                      			 <li><a <c:if test="${i == page }">class=active</c:if> href="${pageContext.request.contextPath }/${i }">${i } <span class="sr-only"></span></a></li>
                     		 </c:forEach>
                    	</c:when>
                    </c:choose>
                      <c:if test="${page+1 <= sumPage }">
                      <li> <a href="${urlNext }"> >> </a></li>
                    </c:if>
             </ul>       
      </div>
</div>


      
