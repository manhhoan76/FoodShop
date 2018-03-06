<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/templates/taglib.jsp" %>
    <div class="container render-page">
     <div id="flash">
     <c:choose>
				<c:when test="${msg == 0 }">
              <div class="alert alert-danger">Gửi liên hệ thất bại</div>
              </c:when>
              
				<c:when test="${msg == 1 }">
              <div class="alert alert-success">Gửi liên hệ thành công</div>
              </c:when>
              </c:choose>
      </div>
      <h1>Liên Hệ</h1>

<div class="row">
    <div class="col-md-8">
      <div class="well well-sm">
        <div class="row">
          <form action="${pageContext.request.contextPath }/lien-he" method="POST" role="form">
	        <div class="form-group">
	        <div class="col-md-6">
	          <label for="">Họ tên</label>
	          <input type="text" class="form-control" name="name" id="" >
	      
	          <label for="">Email</label>
	          <input type="text" name="email" class="form-control" id="" >

	          <label for="">Số điện thoại</label>
	          <input type="text" name="phone" class="form-control" id="" >

	          <label for="">Địa chỉ</label>
	          <input type="text" name="address" class="form-control" id="" >
	        </div>
            <div class="float-left col-md-6">
              <label for="">Nội dung</label>
              <textarea  class="form-control " name="content" rows="9" cols="20" ></textarea>
              <br/>
              <button type="submit" class="btn btn-primary pull-right" >Gửi</button>  
            </div>
            </div>
          </form>
        </div>
      </div>
    </div>
      
    <div class="col-md-4">
      <legend><i class="fa fa-globe" aria-hidden="true"></i>Văn phòng</legend>
      <address>
        <p><b>Website: </b>H2 Đồ ăn vặt Online</span></p>
        <p><b>Địa chỉ: </b></font><span>Số 54 Nguyen Luong Bang, Hoa Khanh Bac, Lien Chieu, Da Nang</span></p>
        <p><b>Gmail: </b><span>manhhoan76.cntt@gmail.com</span></p>
        <p><b>Hotline: </b><span>0981.615.773</span></p>
      </address>
      <address>
        <i class="fa fa-phone" aria-hidden="true"></i><br>
        <a class="phone" href="tel:0981615773"> 0981615773</a><br>
        <i class="fa fa-envelope-o" aria-hidden="true"></i><br>
        <a class="email" href="mailto:manhhoan76.cntt@gmail.com">
           manhhoan76.cntt@gmail.com</a>
      </address>
    </div>
</div>

<div class="row">
  <br></br>
</div>

<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1355.4616470985113!2d108.15136078393697!3d16.07459496484928!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x314218d68dff9545%3A0x714561e9f3a7292c!2sDanang+University+of+Technology!5e0!3m2!1sen!2s!4v1490017936493" width="100%" height="400px" frameborder="0" style="border:0" allowfullscreen></iframe>
      
