<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.servletContext.contextPath }/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:message code="admin.account.add" /></title>
</head>
<style type="text/css">
*[id$=errors] {
	color: red;
	font-style: italic;
	padding-left: 33px;
	background: url("images/hieuboy2.gif") no-repeat left center;
}
</style>
<body>
	<div class="wrapper">
		<jsp:include page="menu.jsp"></jsp:include>
		<div class="main-panel">
			<jsp:include page="header.jsp"></jsp:include>
			<div class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-12">
							<div class="card">
								<div class="header">
									<h4 class="title">
										<s:message code="admin.product.edit" />
									</h4>					
								</div>

								<div class="content">
									<form:form
										action="admin/product/edit-product/${product.id}.htm"
										method="POST" modelAttribute="product" enctype="multipart/form-data">
										<!-- Dòng đầu -->
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label><s:message code="admin.product.nameProduct" />:
														(<span style="color: red;">*</span>)<form:errors
															path="nameProduct" /></label>
													<form:input path="nameProduct" placeholder="Tên sản phẩm"
														size="30" maxlength="30" class="form-control border-input" />
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label><s:message code="admin.product.quantity" />:
														(<span style="color: red;">*</span>)<form:errors
															path="quantity" /></label>
													<form:input path="quantity" placeholder="Số lượng"
														size="30" maxlength="30" class="form-control border-input"
														type="number" />
												</div>
											</div>
										</div>
										
										<!-- Dòng 2 -->
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label><s:message code="admin.product.unitBrief" />:
														(<span style="color: red;">*</span>)<form:errors
															path="unitBrief" /></label>
													<form:input path="unitBrief" placeholder="Đơn vị tính"
														size="30" maxlength="30" class="form-control border-input" />
												</div>
											</div>

											<div class="col-md-6">
												<div class="form-group">
													<label><i class="fa fa-calendar bigger-110"> </i> <s:message
															code="admin.product.productDate" />: (<span
														style="color: red;">*</span>)<form:errors
															path="productDate" /></label>
													<form:input path="productDate" id="datepicker"
														placeholder="dd/MM/yyyy" size="30" maxlength="30"
														class="form-control border-input" />
												</div>
											</div>
										</div>
										
										<!-- Dòng 3 -->
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label><s:message code="admin.product.unitPrice" />:
														(<span style="color: red;">*</span>)<form:errors
															path="unitPrice" /></label>
													<form:input path="unitPrice" accesskey=""
														placeholder="Đơn giá" size="30" maxlength="30"
														type="number" class="form-control border-input" />

												</div>
											</div>

											<div class="col-md-6">
												<div class="form-group">
													<label><s:message code="admin.product.discount" />:
													</label>
													<form:select path="discount"
														class="form-control border-input">
														<form:option value="0">
															<s:message code="admin.select" />
														</form:option>
														<form:option value="0.03">3 %</form:option>
														<form:option value="0.05">5 %</form:option>
														<form:option value="0.08">8 %</form:option>
														<form:option value="0.10">10 %</form:option>
													</form:select>
												</div>
											</div>
										</div>
										
										<!-- Dòng 4 -->
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label for="category.id"><s:message
															code="admin.product.nameCategory" /> (<span
														style="color: red;">*</span>) </label>
													<form:select path="category.id" items="${category}"
														itemValue="id" itemLabel="nameCategory"
														class="form-control border-input" />
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label for="producer.id"><s:message
															code="admin.product.nameProducer" /> (<span
														style="color: red;">*</span>) </label>
													<form:select path="producer.id" items="${producer}"
														itemValue="id" itemLabel="nameProducer"
														class="form-control border-input" />
												</div>
											</div>
										</div>
										
										<!-- Dòng 5 -->
										<div class="row">																		
											<div class="col-md-6">
												<div class="form-group">
													<label for="description"><s:message
															code="admin.product.description" />:</label>
													<form:textarea path="description" rows="5"
														style="width:570px; height:200px"
														class="form-control border-input" />
												</div>
											</div>										
										</div>
										
										<form:hidden path="photo"/>
										<div class="control-group">
											<label class="control-label">Photo: <sup
												style="color: red">*</sup></label>
											<div class="controls">
												<img alt="" src="images/products/${product.photo }" width="220px"
													height="150px" style="border-radius: 20px"> <br> <input
													id="photo" name="file_image" type="file" class="form-control" />
											</div>
										</div>
										
										
										<!-- Dòng 6 -->
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<br>
													<div class="text-center">
														<button type="submit"
															class="btn btn-danger btn-fill btn-wd">
															<s:message code="admin.product.edit" />
														</button>
													</div>
												</div>
											</div>
											
										</div>
										<form:hidden path="photo" />
									</form:form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>