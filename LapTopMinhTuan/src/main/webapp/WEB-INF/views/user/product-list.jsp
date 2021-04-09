<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="${pageContext.servletContext.contextPath }/" />
<meta charset="utf-8">
<title>LapTopMinhTuan</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div id="mainBody">
		<div class="container">
			<div class="row">
				<jsp:include page="menu.jsp"></jsp:include>
				<!--  -->
				<div class="span9">
					<ul class="breadcrumb">
						<li><a href="user/home">Trang Chủ</a> <span class="divider">/</span></li>
						<li class="active">Danh Sách Sản Phẩm</li>
					</ul>
					<!-- h3>
						Products Name
						<div id="myTab" class="pull-right">
							<a href="#listView" data-toggle="tab"><span
								class="btn btn-large"><i class="icon-list"></i></span></a> <a
								href="#blockView" data-toggle="tab"><span
								class="btn btn-large btn-success"><i
									class="icon-th-large"></i></span></a>
						</div>
					</h3-->
					<div style="display: inline-flex;">
						<div style="width: 758px;">
							<h3 class="title1"> 
								<span>
									Products Name
								</span>
							</h3>
						</div>
						<div id="myTab" class="pull-right">
							<a href="#listView" data-toggle="tab"><span
								class="btn btn-large"><i class="icon-list"></i></span></a> <a
								href="#blockView" data-toggle="tab"><span
								class="btn btn-large btn-success"><i
									class="icon-th-large"></i></span></a>
						</div>
					</div>
					

					<br class="clr">
					<div class="tab-content">
						<div class="tab-pane" id="listView">
							<c:forEach var="product" items="${list}">
								<div class="row">
									<div class="span2">
										<img src="images/products/${product.photo }" width="200px"
											height="150px">
									</div>
									<div style="position: relative;" class="span4">
										<h3>New | Available</h3>
										<hr class="soft">
										<h4>${product.nameProduct }</h4>
										<c:choose>
											<c:when test="${flat == 'AllProductSaleOff'}">
												<a style="position: absolute; top:85px; right:0px;" class="btn btn-warning">- <fmt:formatNumber
														value="${product.discount}" type="percent" />
												</a>
											</c:when>
										</c:choose>
										<h5>Số lượng: ${product.quantity }</h5>
										<h5>Đơn vị tính: ${product.unitBrief }</h5>
										<a class="btn btn-danger pull-right"
											href="user/product/detail/${product.id }.htm">Xem Chi
											Tiết</a> <br class="clr">
									</div>
									<div class="span3 alignR">
										<form class="form-horizontal qtyFrm">
											<c:choose>
												<c:when test="${flat == 'AllProductSaleOff'}">
													<div>
														<a style="text-decoration: line-through;margin-bottom: 5px;"
															class="btn btn-success">Giá gốc: <fmt:formatNumber
																value="${product.unitPrice}" pattern="###,###" /> VNĐ
														</a>
														<a class="btn btn-danger">Giá mới: <fmt:formatNumber
																value="${product.unitPrice*(1-product.discount)}"
																pattern="###,###" />VNĐ
														</a>
													</div>

												</c:when>
												<c:otherwise>
													<h3>
														<fmt:formatNumber value="${product.unitPrice }"
															pattern="###,###" />
														VNĐ
													</h3>
												</c:otherwise>
											</c:choose>


											<br> <br> <br> <a
												class="add-to-cart btn btn-large  btn-success"
												href="user/product/detail/${product.id }.htm"> Thêm vào giỏ <i
												class=" icon-shopping-cart"></i>
											</a> <a href="#" class="btn btn-large"><i
												class="icon-zoom-in"></i></a>

										</form>
									</div>
								</div>
								<hr class="soft">
							</c:forEach>
						</div>

						<div class="tab-pane  active" id="blockView">
							<ul class="thumbnails">
								<c:forEach var="product" items="${list }">
									<li class="span3">
										<div class="thumbnail">
											<a href="user/product/detail/${product.id }.htm"><img
												src="images/products/${product.photo }" width="200px"
												height=150px></a>
											<div class="caption">
												<h5>${product.nameProduct }</h5>
												<h5>Số lượng: ${product.quantity }</h5>
												<h4 style="text-align: center">
													<a class="btn"
														href="user/product/detail/${product.id }.htm"> <i
														class="icon-zoom-in"></i>
													</a>
													<c:choose>
														<c:when test="${flat == 'AllProductSaleOff'}">
															<a class="btn" title='Thêm vào'
																href="user/product/detail/${product.id }.htm">Thêm Vào <i
																class="icon-shopping-cart"></i>
															</a>
															<a class="btn btn-warning">- <fmt:formatNumber
																	value="${product.discount}" type="percent" />
															</a>
															<br>
															<a style="text-decoration: line-through;"
																class="btn btn-success">Giá gốc: <fmt:formatNumber
																	value="${product.unitPrice}" pattern="###,###" /> VNĐ
															</a>
															<br>
															<a class="btn btn-danger">Giá mới: <fmt:formatNumber
																	value="${product.unitPrice*(1-product.discount)}"
																	pattern="###,###" />VNĐ
															</a>
														</c:when>
														<c:otherwise>
															</a>
															<a class="btn" title='Thêm vào giỏ'
																href="user/product/detail/${product.id }.htm">Thêm Vào Giỏ
																<i class="icon-shopping-cart"></i>
															</a>
															<br>
															<a class="btn btn-success"> <fmt:formatNumber
																	value="${product.unitPrice }" pattern="###,###" /> VNĐ
															</a>
														</c:otherwise>
													</c:choose>

												</h4>
											</div>
										</div>
									</li>
								</c:forEach>
							</ul>
							<hr class="soft">
						</div>
					</div>

					<!-- Phân Trang -->
					<form class="page">
						<c:choose>
							<c:when test="${flat == 'AllProducts'}">
								<div style="margin: 10px; text-align: right;">
									<c:forEach begin="1" end="${rowCount}" varStatus="row">
										<a type="button" href="user/product/list/?page=${row.index}"
											class="btn btn-danger btn-xs btn-fill">${row.index}</a>
									</c:forEach>
								</div>
							</c:when>
							<c:when test="${flat == 'AllProductCategory'}">
								<div style="margin: 10px; text-align: right;">
									<c:forEach begin="1" end="${rowCount}" varStatus="row">
										<a type="button"
											href="user/product/list-by-category/${id}.htm/?page=${row.index}"
											class="btn btn-danger btn-xs btn-fill">${row.index}</a>
									</c:forEach>
								</div>
							</c:when>
							<c:when test="${flat == 'AllProductProducer'}">
								<div style="margin: 10px; text-align: right;">
									<c:forEach begin="1" end="${rowCount}" varStatus="row">
										<a type="button"
											href="user/product/list-by-producer/${id}.htm/?page=${row.index}"
											class="btn btn-danger btn-xs btn-fill">${row.index}</a>
									</c:forEach>
								</div>
							</c:when>
							<c:when test="${flat == 'AllProductSaleOff'}">
								<div style="margin: 10px; text-align: right;">
									<c:forEach begin="1" end="${rowCount}" varStatus="row">
										<a type="button"
											href="user/product/type/saleoff.htm/?page=${row.index}"
											class="btn btn-danger btn-xs btn-fill">${row.index}</a>
									</c:forEach>
								</div>
							</c:when>
						</c:choose>
					</form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>

<style>
.pager input {
	background-color: #C6C6C6;
	border-radius: 100%;
}

.pager input[disabled] {
	background-color: #F9F9F9;
}

.box>ul img[id]:hover {
	opacity: 0.6;
}

.box>ul:hover {
	box-shadow: 0 0 5px red;
}
</style>

<!--End Pagination  -->

</html>