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
              <c:when test="${msg == 4 }">
              <div class="alert alert-success">Cảm ơn bạn đã gợi ý sản phầm độc đáo cho cửa hàng!!</div>
              </c:when>
              </c:choose>
      
      </div>
      

<div class="row">
  <aside class="col-md-4">
    <section class="user_info">
      <h1>
        <img src="${pageContext.request.contextPath }/files/${objUser.image}" alt="${objUser.username}" width="300" height="300">
        ${objUser.username }
      </h1>
      <p class="text-center text-info"> ${objUser.email }</p>
      <c:if test="${objLogin.id ==1 || objUser.id == objLogin.id }">
      <a class="btn btn-success full-width-button" href="${pageContext.request.contextPath }/sua-trang-ca-nhan/${objUser.id}">Edit profile</a>
    </c:if>
    </section>
  </aside>
  <div class="col-md-8">
    <ul class="nav nav-tabs nav-justified" role="tablist">
      <li class="nav-item">
        <a class="nav-link active" data-toggle="tab" href="#suggested_product_tab" role="tab">
          Suggested products
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link active" data-toggle="tab" href="http://tso-plus.herokuapp.com/users/1#orders_tab" role="tab">
          History Orders
        </a>
      </li>
    </ul>
    <div class="tab-content">
      <div class="tab-pane active" id="suggested_product_tab" role="tabpanel">
        <table class="table recenty-viewed">
          <thead>
            <tr>
              <th>Product</th>
              <th>Description</th>
              <th>Price</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${listSuggestByUser }" var="objSuggest">
          <tr class="product-in-cart">
  <td class="w20">
  	<b>${objSuggest.name }</b>
  </td>
  <td class="w60">
    <p>${objSuggest.content }</p>
  </td>
  <td class="w15">
    <p>$${objSuggest.price }</p>
  </td>
</tr>
 </c:forEach>
          </tbody>
        </table>
        
      </div>
      <div class="tab-pane" id="orders_tab" role="tabpanel">
        <table class="table recenty-viewed">
          <thead>
            <tr>
              <th>Date</th>
              <th>Total </th>
              <th>Status</th>
               <th>Pay</th>
               <th></th>
            </tr>
          </thead>
          <tbody>
              <c:forEach items="${listOrderByUser}" var="objOrder">
          <tr class="product-in-cart">
  <td class="w15">
  	<p>${objOrder.create_at }</p>
  </td>
  <td class="w50">
    <p>${objOrder.sumPrice }</p>
  </td>
  <td class="w15">
    <p>${objOrder.status }</p>
  </td>
  <td class="w15">
    <p>${objOrder.pay }</p>
  </td>
  <c:if test="${objOrder.id_order_status == 1 }">
  <td><a href="${pageContext.request.contextPath }/cancel/${objOrder.id}" class="btn btn-danger">Cancel</a></td>
  </c:if>
</tr>
 </c:forEach>
          </tbody>
        </table>
        
      </div>
    </div>
  </div>
</div>
      
