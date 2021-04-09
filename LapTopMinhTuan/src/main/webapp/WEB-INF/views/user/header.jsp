<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>

<base href="${pageContext.servletContext.contextPath}/">

<div id="header">
	<div class="container">
		<div id="welcomeLine" class="row">
			<div class="span6 header">
				<span class="btn btn-lg btn-success"><i class="fa fa-phone"
					style="margin-right: 5px;"></i>HOTLINE: 0377730290</span>
				<c:choose>
					<c:when test="${empty sessionScope.user.fullName}">
						<div class="pull-right">
							<a href="user/register"><span class="btn btn-lg btn-info"><i
									class="fa fa-key" style="margin-right: 5px;"></i>Đăng ký</span></a> <a
								href="user"> <span class="btn btn-lg btn-success"><i
									class="fa fa-sign-in" style="margin-right: 5px;"></i>Đăng nhập</span>
							</a>
						</div>
					</c:when>
					<c:when test="${sessionScope.user.fullName != null}">
						<span class="btn btn-lg btn-danger" style="min-width: 165px;">Xin
							chào: <strong>${sessionScope.user.fullName }</strong>
						</span>
						<div style="position: absolute; right: 0px; top: 0px;">
							<a href="user/change-info/${sessionScope.user.id}.htm"><span
								class="btn btn-lg btn-info">Cập nhật</span></a> <a
								href="user/change-pass/${sessionScope.user.id }.htm"><span
								class="btn btn-lg btn-success">Đổi mật khẩu</span></a> <a
								href="user/logout"
								onclick="return confirm ('Bạn chắc chắn muốn đăng xuất ?')">
								<span class="btn btn-lg btn-danger"> <i
									class="fa fa-sign-in" style="margin-right: 5px;"></i>Đăng xuất
							</span>
							</a>
						</div>

					</c:when>
				</c:choose>
			</div>
		</div>
		<!------ Navbar ------>
		<div id="logoArea" class="navbar">
			<a id="smallScreen" data-target="#topMenu" data-toggle="collapse"
				class="btn btn-navbar"> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <span class="icon-bar"></span>
			</a>
			<div class="navbar-inner">
				<div>
					<!-- src="themes/images/logo.png"-->
					<a class="brand" href="user/home"><img
						style="margin-left: 75px" src="themes/images/logoshop.png"
						alt="Bootsshop"> </a>
					<form action="user/product/search-product.htm"
						style="margin-left: 76px;" class="form-inline navbar-search"
						method="post">
						<input id="" name="search" type="text" style="width: 360px;" placeholder="Nhập sản phẩm...">
						<button type="submit" id="submitButton" class="btn btn-danger"
							style="width: 45px; line-height: 18px;">
							<i class="fa fa-search" style="font-size: 16px"></i>
						</button>
					</form>
					<div class="cart-btn">
						<a href="shopping-cart/index">
							<span class="nav-icon"> 
								<i class="fa fa-cart-plus"></i>
							</span>
						</a>
						<div class="cart-items"><label style="text-align: center; margin-top: 5px;">${cart.count }</label> </div>
					</div>
				</div>
				<div>
					<ul id="topMenu" class="nav pull-right"
						style="width: 380px; margin: 0px 374px;">
						<li class=""><a href="user/home"
							style="line-height: 21px; padding: 15px 10px; font-size: 18px;">Trang
								chủ</a></li>
						<li class=""><a href="user/about"
							style="line-height: 21px; padding: 15px 10px; font-size: 18px;">Giới
								thiệu</a></li>
						<li class=""><a href="user/contact"
							style="line-height: 21px; padding: 15px 10px; font-size: 18px;">Liên
								hệ</a></li>
						<li class=""><a href="user/product/list"
							style="line-height: 21px; padding: 15px 10px; font-size: 18px;">Sản
								phẩm</a></li>
					</ul>
				</div>

			</div>
		</div>
	</div>
</div>
<script>
	$(function() {
		$(".language a").click(function() {
			$.ajax({
				url : "user/set-language.htm?language=" + $(this).attr("href"),
				success : function() {
					location.reload();
				}
			});
			return false;
		});
	});
</script>
