<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
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
				<div class="span9">
					<ul class="breadcrumb">
						<li><a href="user/home">Home</a> <span class="divider">/</span></li>
						<li class="active">OrderDetail List</li>
					</ul>
					<h3 class="title1"> 
						<span>
							Order List
						</span>
					</h3>
					<div class="well">
						<form:form modelAttribute="order">
							<div class="control-group">
								<label class="control-label">Id Order:</label>
								<div class="controls">
									<form:input path="id" class="form-control valid"
										placeholder="Mã hóa đơn" type="text" readonly="true" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">Order Date: </label>
								<div class="controls">
									<form:input path="orderDate" class="form-control valid"
										placeholder="Order Date" readonly="true" />
								</div>
							</div>


							<div class="control-group">
								<label class="control-label">Receiver: </label>
								<div class="controls">
									<form:input path="receiver" class="form-control valid"
										placeholder="Require Date" readonly="true" />
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">Number Phone: </label>
								<div class="controls">
									<form:input path="numberPhone" class="form-control valid"
										placeholder="Number Phone" readonly="true" />
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">Address: </label>
								<div class="controls">
									<form:input path="address" class="form-control valid"
										placeholder="address" readonly="true" />
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">Amount: <f:formatNumber value="${order.amount}" pattern="###,###" />VNĐ
								</label>
							</div>

						</form:form>
					</div>
					<hr class="soft">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>ID Product</th>
								<th>Product</th>
								<th>Photo</th>
								<th>Amount</th>
								<th>Discount</th>
								<th>Quantity</th>
								<th>Total</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="d" items="${detail}">
								<tr>
									<td>${d.product.id}</td>
									<td>${d.product.nameProduct}</td>
									<td><img src="images/products/${d.product.photo }"
										style="border-radius: 20px" width="120px" height="100px" /></td>
									<td><f:formatNumber value="${d.amount }" pattern="###,###" />
										VNĐ</td>
									<td><c:choose>
											<c:when test="${d.discount == null}">0%</c:when>
											<c:when test="${d.discount == 0}">0%</c:when>
											<c:otherwise>
												<f:formatNumber value="${d.discount }" type="percent" />
											</c:otherwise>
										</c:choose></td>
									<td>${d.quantity}</td>
									<td><f:formatNumber
											value="${d.amount*d.quantity*(1-d.discount)}"
											pattern="###,###" /> VNĐ</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>



</html>