<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/templates/taglib.jsp" %>
<!DOCTYPE html>
<html>
  
<!-- Mirrored from H2.herokuapp.com/products by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 28 Dec 2017 03:22:28 GMT -->
<!-- Added by HTTrack --><meta http-equiv="content-type" content="text/html;charset=utf-8" /><!-- /Added by HTTrack -->
<head>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath }/files/favicon.png">
    <meta name="description" content=""/>
<meta property="fb:app_id" content="219785741821521"/>
<meta property="og:site_name" content=""/>
<meta property="og:title" content=""/>
<meta property="og:type" content="article"/>
<meta property="og:url" content=""/>
<meta property="og:image" content=""/>
<meta property="og:locale" content="en_US"/>
<meta property="og:description" content=""/>
<meta name="viewport" content="width=device-width, initial-scale=1">
   

    <title>H2 Do an vat Online</title>
    <meta name="csrf-param" content="authenticity_token" />
<meta name="csrf-token" content="" />

    <link rel="stylesheet" media="all" href="${pageContext.request.contextPath }/templates/assets/style.css" data-turbolinks-track="reload" />
    <script src="${pageContext.request.contextPath }/templates/assets/main.js" data-turbolinks-track="reload"></script>
     <script src="https://www.paypalobjects.com/api/checkout.js"></script>
    <!--[if lt IE 9]>
  <script src="//cdnjs.cloudflare.com/ajax/libs/html5shiv/r29/html5.min.js">
  </script>
<![endif]-->

  </head>

  <body>
    <nav class="navbar">
   <div class="container">
    <div class="navbar-header">
      <a id="logo" href="${pageContext.request.contextPath }/">H2 Store</a>
    </div>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="${pageContext.request.contextPath }/gioi-thieu">Introduction</a></li>
       <c:if test="${objLogin.id == 1 }">
      
      <li><a href="${pageContext.request.contextPath }/admin/contact/index">Contact <span class="badge">${numberContact }</span></a></li>
      </c:if>
       <c:if test="${objLogin.id != 1}">
      
      <li><a href="${pageContext.request.contextPath }/lien-he">Contact</a></li>
      </c:if>
      <li><a href="${pageContext.request.contextPath }/tro-giup">Help</a></li>
        <li><a href="${pageContext.request.contextPath }/san-pham">Products</a></li>
        <c:if test="${objLogin.username != ''}">
        <li>
          <a class="header-cart" href="${pageContext.request.contextPath }/gio-hang">
            Cart <span id="numberCart" class="badge"><c:if test="${numberCart > 0 }">${numberCart }</c:if> </span>
		</a>   
	     </li>
	     </c:if>
		<c:if test="${objLogin != null && objLogin.username != '' }">
        <li class="dropdown">
          <a href="http://H2.herokuapp.com/admin/users#" class="dropdown-toggle" data-toggle="dropdown">
            ${objLogin.username } <b class="caret"></b>
          </a>
          <ul class="dropdown-menu">
            <li><a href="${pageContext.request.contextPath }/trang-ca-nhan/${objLogin.id}">Profile</a></li>
            <li><a href="${pageContext.request.contextPath }/sua-trang-ca-nhan/${objLogin.id}">Settings</a></li>
            <li><a href="${pageContext.request.contextPath }/goi-y-san-pham">Suggest product</a></li>
            <li><a rel="nofollow" data-method="delete" href="${pageContext.request.contextPath }/logout">Log out</a></li>
          </ul>
        </li>
        </c:if>
        <c:if test="${objLogin.id == 1 }">
        <li class="dropdown">
          <a href="http://H2.herokuapp.com/admin/users#" class="dropdown-toggle" data-toggle="dropdown">
            Dashboard <b class="caret"></b>
          </a>
          <ul class="dropdown-menu">
            <li><a href="${pageContext.request.contextPath }/admin/user/index">User management</a></li>
            <li><a href="${pageContext.request.contextPath }/admin/product/index">Product management</a></li>
            <li><a href="${pageContext.request.contextPath }/admin/order/index">Order management</a></li>
            <li><a href="${pageContext.request.contextPath }/admin/suggest/index">Suggest management</a></li>
            <li><a href="${pageContext.request.contextPath }/admin/cat/index">Categories management</a></li>
            <li><a href="${pageContext.request.contextPath }/admin/comment/index">Comment management</a></li>
              <li><a href="${pageContext.request.contextPath }/admin/slide/index">Slide management</a></li>
                <li><a href="${pageContext.request.contextPath }/admin/discount/index">Discount management</a></li>
          </ul>
        </li>
        </c:if>
        <c:if test="${objLogin.id == 4 }">
        <li class="dropdown">
          <a href="http://H2.herokuapp.com/admin/users#" class="dropdown-toggle" data-toggle="dropdown">
            Dashboard <b class="caret"></b>
          </a>
          <ul class="dropdown-menu">
            <li><a href="${pageContext.request.contextPath }/admin/product/index">Product management</a></li>
            <li><a href="${pageContext.request.contextPath }/admin/suggest/index">Suggest management</a></li>
            <li><a href="${pageContext.request.contextPath }/admin/cat/index">Categories management</a></li>
            <li><a href="${pageContext.request.contextPath }/admin/comment/index">Comment management</a></li>
              <li><a href="${pageContext.request.contextPath }/admin/slide/index">Slide management</a></li>
                <li><a href="${pageContext.request.contextPath }/admin/discount/index">Discount management</a></li>
          </ul>
        </li>
        </c:if>
        <c:if test="${objLogin.username == '' }">
         <li><a href="${pageContext.request.contextPath }/login">Login</a></li>
    	</c:if>
    </ul>
  </div>
</nav>