<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="${pageContext.servletContext.contextPath }/" />
<meta charset="utf-8">
<title>LapTopMinhTuan</title>
</head>
<script src='https://www.google.com/recaptcha/api.js'></script>
<script src="themes/js/jquery-1.7.1.min.js"></script>
<style type="text/css">
	*[id$=errors]{
	    margin-left: 10px;
		color:red;
		font-style:italic;
	}
</style>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div id="mainBody">
		<div class="container">
			<div class="row">
				<jsp:include page="menu.jsp"></jsp:include>
				<div class="span9">
					<ul class="breadcrumb">
						<li><a href="user/home">Home</a> <span class="divider">/</span></li>
						<li class="active">Checkout</li>
					</ul>
					<h3 class="title1"> 
						<span>
							Checkout
						</span>
					</h3>
					<div class="well">
						<h3>Kiểm tra đơn đặt hàng</h3>
						<label class="error">${message }</label>
						<form:form id="order" role="form" modelAttribute="order"
							class="form-horizontal" action="order/purchase" method="post"
							novalidate="novalidate">
							<div class="control-group">
								<label class="control-label">Username: </label>
								<div class="controls">
									<form:input path="customer.id" name="id"
										class="form-control valid" placeholder="Username"
										readonly="true" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">Order Date:</label>
								<div class="controls">
									<form:input path="orderDate" name="orderDate"
										class="form-control error" placeholder="Order Date"
										readonly="true" />
								</div>
							</div>
												
							<div class="control-group">
								<label class="control-label">Amount:</label>
								<div class="controls">
									<form:input path="amount" name="amount"
										class="form-control error" placeholder="Order Date"
										readonly="true" />(VND)
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">Receiver: <sup
									style="color: red">*</sup></label>
								<div class="controls">
									<form:input path="receiver" name="receiver"
										class="form-control error" placeholder="Receiver" /><form:errors path="receiver"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">Number Phone: <sup
									style="color: red">*</sup></label>
								<div class="controls">
									<form:input path="numberPhone" name="numberPhone"
										class="form-control error" placeholder="Number Phone" /><form:errors path="numberPhone"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">Address: <sup
									style="color: red">*</sup></label>
								<div class="controls">
									<form:input path="address" name="address"
										class="form-control error" placeholder="Address" /><form:errors path="address"/>
								</div>
							</div>
							<div class="control-group">
								<div class="controls">
									<input class="btn btn-large btn-danger" type="submit"
										value="Đặt Hàng">
								</div>
							</div>
							<form:hidden path="status" value="1" />
						</form:form>
						<hr class="soft">
						<table class="table table-bordered">
							<thead>
								<tr>

									<th>Name Product</th>
									<th>Photo</th>
									<th>Unit Price</th>
									<th>Quantity</th>
									<th>Discount</th>
									<th>Total</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="p" items="${cart.items}">
									<tr>
										<td>${p.nameProduct}</td>
										<td><img src="images/products/${p.photo}" height="80px"
											width="150px" /></td>
										<td><fmt:formatNumber value="${p.unitPrice}"
												pattern="###,###" /> VNĐ</td>
										<td>${p.quantity}</td>
										<td><fmt:formatNumber value="${p.discount}"
												type="percent" /></td>
										<td><fmt:formatNumber
												value="${p.unitPrice*p.quantity*(1-p.discount)}"
												pattern="###,###"></fmt:formatNumber> VNĐ</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>

				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>


<link href="assets/jquery-ui/jquery-ui.min.css" rel="stylesheet" />
<script src="assets/jquery-ui/jquery-ui.min.js"></script>
<script>
	$(function() {
		$(".requireDate").datepicker({
			dateFormat : 'dd/mm/yy'
		});
		$("input#requireDate").datepicker({
			dateFormat : 'dd/mm/yy'
		});
	});
</script>


</html>