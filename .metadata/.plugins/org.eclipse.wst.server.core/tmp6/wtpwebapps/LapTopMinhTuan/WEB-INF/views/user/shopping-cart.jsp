<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="${pageContext.servletContext.contextPath }/" />
<meta charset="utf-8">
<title>LapTopMinhTuan</title>
<style>
td{
	text-align: center;
}
</style>
<script>
    var i = 1;
    function buttonClickIncrease() {
        document.getElementById(appendedInputButtons).value = i++;
    }
    function buttonClickDecrease() {
        if(document.getElementById('appendedInputButtons').value <= 1)
        {
        	document.getElementById('appendedInputButtons').value = 1;	
        }else
        {
        	document.getElementById('appendedInputButtons').value = i--;
        }
    }
</script>

</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div id="mainBody">
		<div class="container">
			<div class="row">
				<jsp:include page="menu.jsp"></jsp:include>
				<div class="span9">
					<ul class="breadcrumb">
						<li><a href="user/home">Home</a> <span class="divider">/</span></li>
						<li class="active">SHOPPING CART</li>
					</ul>
					<h3 class="title1"> 
						<span>
							SHOPPING CART
						</span>
					</h3>
					<c:if test="${cart.count >=1 }">
						<h4>
							[ <span id="cart-count">${cart.count} Sản phẩm</span> - <span
								id="cart-amount"><fmt:formatNumber value="${cart.amount}"
									pattern="###,###" /> VNĐ</span>]
						</h4>
					</c:if>

					<hr class="soft">
					<form action="shopping-cart/update" method="post"> 
						<table class="table table-bordered">
							<thead>
								<tr>
									<th>#</th>
									<th>Photo</th>
									<th>Name Product</th>
									<th>Quantity</th>
									<th>Discount</th>
									<th>UnitPrice</th>
									<th>Total</th>
									<th></th>
								</tr>
							</thead>
							<c:forEach var="product" items="${cart.items}">
								<tbody>
									<tr>
										<c:set value="${count+1 }" var="count"></c:set>
										<td>${count }</td>
										<td><img width="80"
											src="images/products/${product.photo }" alt=""></td>
										<td>${product.nameProduct }<br>Đơn vị tính:
											${product.unitBrief }
										</td>
										<td>
											${product.quantity }											
										</td>
										<td><fmt:formatNumber value="${product.discount }"
												type="percent" /></td>
										<td><fmt:formatNumber value="${product.unitPrice }"
												pattern="###,###" /> VNĐ</td>
										<td><fmt:formatNumber
												value="${product.unitPrice*product.quantity*(1-product.discount) }"
												pattern="###,###" /> VNĐ</td>
										<td>
											<a href="shopping-cart/remove/${product.id}" class=" btn btn-danger remove">
											Xóa </a>
										</td>
									</tr>
								</tbody>
							</c:forEach>

						</table>
						<div class="text-right">
							<span>
								<h4>Tổng thanh toán: </h4>
							</span>
							<span>
								<c:if test="${cart.count >=1 }">
									<h4>
										<span
										id="cart-amount" style="font-size: 18px; color: #ea1616;"><fmt:formatNumber value="${cart.amount}"
										pattern="###,###" /> VNĐ</span>
									</h4>
								</c:if>
								<c:if test="${cart.count == 0 }">
									<h4>
										<span id="cart-amount" style="font-size: 18px; color: #ea1616;">0 VNĐ</span>
									</h4>
								</c:if>
							</span>					
						</div>

						<div style="text-align: center;">
							<hr>
							<c:choose>
								<c:when test="${cart.count >=1 }">
									<a href="user/product/list" class="btn btn-large btn-success">
										Tiếp Thục Mua Sắm </a>
									<a href="shopping-cart/clear" class="btn btn-large btn-success">
										Xóa Hết </a>
									<c:choose>
										<c:when test="${!empty sessionScope.user && cart.count > 0}">
											<a href="order/checkout" class="btn btn-large btn-success">Đặt
												Hàng</a>
										</c:when>
										<c:otherwise>
											<a href="user" class="btn btn-large btn-success">Đặt Hàng
											</a>
										</c:otherwise>
									</c:choose>

								</c:when>
								<c:otherwise>
									<a href="user/product/list" class="btn btn-large btn-success">
										Tiếp Thục Mua Sắm </a>
								</c:otherwise>
							</c:choose>


						</div>

					</form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>

<script>
	$(function() {
		$("a.remove-from-cart").click(function() {
			tr = $(this).parents("tr");
			dataUrl = $(this).attr("data-url");
			$.ajax({
				url : dataUrl,
				data : {},
				dataType : "json",
				success : function(response) {
					$("span#cart-count").html(response.count + " items");
					$("span#cart-amount").html(response.amount + " VNĐ");
					tr.hide(500);
				}
			});
			return false;
		});
	});
</script>

</html>