<%@page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<style>
#cart-image {
	background-size: 100% 100%;
}
</style>

<div id="sidebar" class="span3">
	<c:choose>
		<c:when test="${sessionScope.user.fullName != null}">
			<div class="well well-small">
				<a href="order/list">
					<span class="btn btn-lg btn-info" style="width: 226px;">
						<img src="themes/images/ico-cart.png" alt="cart" style="margin-right: 6px;">Thông tin đơn hàng 
					</span>
				</a>
			
				<!--a id="myCart" href="order/list">
					<img src="themes/images/ico-cart.png" alt="cart">Thông tin đơn hàng 
				</a-->
				<br>
			</div>
		</c:when>
	</c:choose>
	<ul class="nav nav-tabs nav-stacked">
		<li class="subMenu open">
			<span class="title"> CHỦNG LOẠI</span>
			<ul>
				<!-- Show Danh Mục -->
				<c:forEach var="category" items="${category}">
					<li><a href="user/product/list-by-category/${category.id}.htm">
							<i class="icon-chevron-right"></i>${category.nameCategory } <%-- <c:choose>
							<c:when test="${language == 'en'}">${c.name}</c:when>
							<c:otherwise>${c.nameVN}</c:otherwise>
						</c:choose> --%>
					</a></li>
				</c:forEach>
			</ul>
		</li>
	</ul>
	<br>
	<ul class="nav nav-tabs nav-stacked">
		<li class="subMenu open">
			<span class="title"> NHÀ CUNG CẤP</span>
			<ul>
				<!-- Show Nhà Sản Xuất -->
				<c:forEach var="producer" items="${producer}">
					<li><a href="user/product/list-by-producer/${producer.id}.htm">
							<i class="icon-chevron-right"></i>${producer.nameProducer } <%-- <c:choose>
							<c:when test="${language == 'en'}">${c.name}</c:when>
							<c:otherwise>${c.nameVN}</c:otherwise>
						</c:choose> --%>
					</a></li>
				</c:forEach>
			</ul></li>
	</ul>
	<br>
	<ul class="nav nav-tabs nav-stacked">
		<li class="subMenu open">
			<span class="title"> HÀNG ĐẶC BIỆT</span>
			<ul>
				<li><a href="user/product/type/saleoff.htm"><i
						class="icon-chevron-right"></i>Hàng giảm giá</a></li>
			</ul>
		</li>
	</ul>

	<br>


</div>
