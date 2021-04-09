<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="${pageContext.servletContext.contextPath }/" />
<meta charset="utf-8">
<title>LapTopMinhTuan</title>
</head>
<script src="themes/js/jquery-1.7.1.min.js"></script>

<script>
	var soLuongCon = ${product.quantity };
    var i = 0;
    
    function buttonClickInc() {   	
        if(document.getElementById('inc').value == soLuongCon)
        {
        	var div = document.getElementById('mess');
        	console.log(document.getElementById('inc').value);
        	div.innerHTML = '*Số lượng không đủ!!!';
        	return;     	
        }
        document.getElementById('inc').value = ++i;
       
    };
    function buttonClickDec() {
        
        if(document.getElementById('inc').value == 1)
        {
        	return;
        }
        document.getElementById('inc').value = --i;
        if(document.getElementById('inc').value != soLuongCon)
        {
        	var div = document.getElementById('mess');
        	div.style.visibility='hidden';
        	return;
        }
    };
</script>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div id="mainBody">
		<div class="container">
			<div class="row">
				<jsp:include page="menu.jsp"></jsp:include>
				<!--  -->
				<div class="span9">
					<ul class="breadcrumb">
						<li><a href="user/home">Home</a> <span class="divider">/</span></li>
						<li><a href="user/product/list.htm">Products</a> <span
							class="divider">/</span></li>
						<li class="active">Product Details</li>
					</ul>
					<div class="row">
						<div id="gallery" class="span3">
							<a href="images/products/${product.photo }"
								title="${product.nameProduct }"> <img
								src="images/products/${product.photo }" style="width: 100%"
								alt="">

							</a>							

							<br class="soft">
							<hr class="soft">
						</div>
						<div class="span6">
						<form action="shopping-cart/add/${product.id }">
							<h3>${product.nameProduct }<span
									class="btn btn-warning pull-right">Giảm Giá: <c:choose>
										<c:when test="${product.discount == null}">0%</c:when>
										<c:when test="${product.discount == 0}">0%</c:when>
										<c:otherwise>
											<f:formatNumber value="${product.discount }" type="percent" />
										</c:otherwise>
									</c:choose>
								</span>
							</h3>

							<table class="table table-striped"
								style="font-stretch: ultra-condensed;">
								<tbody>
									<tr>
										<td style="font-size: 16px; font-weight: bold;" colspan="2"
											align="center">Thông Tin Sản Phẩm Chi Tiết</td>
									</tr>
									<tr>
										<td>Mã hàng hóa</td>
										<td>: ${product.id }</td>
									</tr>
									<tr>
										<td>Danh mục</td>
										<td>: ${product.category.nameCategory }</td>
									</tr>
									<tr>
										<td>Nhà cung cấp</td>
										<td>: ${product.producer.nameProducer }</td>
									</tr>
									<tr>
										<td>Đơn giá</td>
										<td>: <f:formatNumber value="${product.unitPrice }"
												pattern="###,###" /> VNĐ
										</td>
									</tr>
									<tr>
										<td>Ngày sản xuất</td>
										<td>: <f:formatDate value="${product.productDate }"
												pattern="dd/MM/yyy" /></td>
									</tr>
									<tr>
										<td>Số lượng còn</td>
										<td>: ${product.quantity }</td>
									</tr>
									<tr>
										<td>Đơn vị tính</td>
										<td>: ${product.unitBrief }</td>
									</tr>									
									<tr>
										<td>Số lượng mua</td>
										<td style="width: 300px;">
										<button class="btn" type="button" onclick="buttonClickDec()">
												<i class="icon-minus"></i>
										</button>
										<input class="span1" style="max-width: 34px; margin-top: 10px;"
											placeholder="1" value="1" id="inc"
											name="soLuong"
											type="text" />	
										<button class="btn" type="button" onclick="buttonClickInc()">
											<i class="icon-plus"></i>
										</button>
										<label id="mess" style="display: inline-block; color: red;"></label>
										</td>
									</tr>
								</tbody>
							</table>
							<hr class="soft">

							<div class="control-group">
								<div class="controls">
									<!--label class="control-label">
										<span>
											<f:formatNumber value="${product.unitPrice*(1-product.discount) }" pattern="###,###" /> VNĐ
										</span>
									</label-->
									<!-- <input type="number" class="span1" placeholder="Qty."> -->
									<input type="submit" class="btn btn-large btn-success pull-right" value="Add to cart">
									<!-- a href="shopping-cart/add/${product.id }" type="submit"
										class="btn btn-large btn-success pull-right"> Add to cart
										<i class=" icon-shopping-cart"></i>
									</a-->
								</div>
							</div>
						</form>
							


							<!-- hr class="soft"-->
						</div>
						<div class="span9">
							<p>${product.description }</p>
							<hr class="soft">
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>

<div id="fb-root"></div>
<script>
	(function(d, s, id) {
		var js, fjs = d.getElementsByTagName(s)[0];
		if (d.getElementById(id))
			return;
		js = d.createElement(s);
		js.id = id;
		js.src = 'https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v2.12&appId=830930687115334';
		fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));
</script>

</html>