<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<base href="${pageContext.servletContext.contextPath}/">
<!-- Bootstrap style -->
<link id="callCss" rel="stylesheet"
	href="themes/bootshop/bootstrap.min.css" media="screen" />
<link href="themes/css/base.css" rel="stylesheet" media="screen" />
<!-- Bootstrap style responsive -->
<link href="themes/css/bootstrap-responsive.min.css" rel="stylesheet" />
<link href="themes/css/font-awesome.css" rel="stylesheet"
	type="text/css">
<!-- Google-code-prettify -->
<link href="themes/js/google-code-prettify/prettify.css"
	rel="stylesheet" />
<!-- fav and touch icons -->
<link rel="shortcut icon" href="themes/images/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="themes/images/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="themes/images/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="themes/images/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="themes/images/ico/apple-touch-icon-57-precomposed.png">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style type="text/css" id="enject">
.remove{
	background-color: red;
}
.navbar-inner{	
    background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#e0e6ea), color-stop(70%,#3086c1), to(#0d6098));

}

.header {
    width: 1170px;
  	position: relative;
}

.cart-btn {
    position: relative;
    cursor: pointer;
}

.nav-icon {
    font-size: 1.5rem;
}

.fa-cart-plus:before {
    content: "\f217";
}
.nav-icon{
	position: absolute;
    right: 130px;
    top: 11px;
    font-size: 42px;
    color: #e80d0d;}
.cart-items {
    position: absolute;
    top: 5px;
    right: 111px;
    background: #ffc800;
    padding: 0 5px;
    border-radius: 50%;
    height: 30px;
    width: 20px;
    color: #fff;
}

.title {
    width: 145px;
	font-size: 18px;
    display: inline-block;
    position: relative;
    font-weight: bold;
    background-color: #00aeef;
    padding: 0px 55px 0px 12px;
    line-height: 30px;
}

.title:before {
    border-color: transparent #ffffff transparent #b5595900;
    border-style: solid;
    border-width: 0px 30px 31px 0px;
    content: "";
    position: absolute;
    right: 0;
}

.title1 {
    color: #ffffff;
    font-size: 17px;
    position: relative;
    text-transform: uppercase;
    border-bottom: 3px solid #00aeef;
    line-height: 33px;
}

.title1 span {
	font-size: 17px;
    display: inline-block;
    position: relative;
    text-transform: uppercase;
    background-color: #00aeef;
    padding: 0 52px 0 10px;
}

.title1 span:before {
    border-color: transparent #ffffff transparent transparent;
    border-style: solid;
    border-width: 0 40px 33px 0;
    content: "";
    position: absolute;
    right: 0;
}

.text-right {
	text-align: right;
}

.text-right:before{
	display: table;
    content: " ";
}

.text-right:after{
	clear: both;
	display: table;
    content: " ";
    box-sizing: border-box;
}
</style>
<div id="footerSection" style="height: 70px;>
	<div class="container">
		<div class="row"">
		
			<div class="span3" style="margin-left: 95px;">
				<c:choose>
					<c:when test="${ sessionScope.user.fullName != null}">
						<h5>TÀI KHOẢN</h5>
						<a href="user/change-pass/${sessionScope.user.id }.htm">ĐỔI
							MẬT KHẨU</a>
						<a href="user/change-info/${sessionScope.user.id }.htm">CẬP
							NHẬT THÔNG TIN</a>
					</c:when>
				</c:choose>

			</div>		

			<div class="span3" style="padding-top: 10px; margin-left: 200px;">
				<div class="pull-right" style="font-size: 20px;">
					<b>Hotline hỗ trợ khách hàng</b>
				</div>
				<br>
				<p class="pull-right" style="margin-left: 34px;">© LapTopminhtuan.com - 0121.332.1897 (từ
					8h-21h, kể cả T7-CN)</p>
			</div>
			<div id="socialMedia" class="span3 pull-right">
				<h5>LIÊN HỆ VỚI CHÚNG TÔI</h5>
				<a href="#" target="blank"><img width="60" height="60"
					src="themes/images/facebook.png" title="facebook" alt="facebook"></a>
				<a href="#" target="blank"><img width="60" height="60"
					src="themes/images/twitter.png" title="twitter" alt="twitter"></a>
				<a href="#" target="blank"><img width="60" height="60"
					src="themes/images/youtube.png" title="youtube" alt="youtube"></a>

			</div>
		</div>

<!-- Placed at the end of the document so the pages load faster ============================================= -->
<script src="themes/js/jquery.js" type="text/javascript"></script>
<script src="themes/js/bootstrap.min.js" type="text/javascript"></script>
<script src="themes/js/google-code-prettify/prettify.js"></script>

<script src="themes/js/bootshop.js"></script>
<script src="themes/js/jquery.lightbox-0.5.js"></script>

<script src="themes/js/jquery-1.7.1.min.js"></script>


