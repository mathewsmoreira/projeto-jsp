<%@page import="model.ModelLoginServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"></jsp:include>

<body>
	<!-- Pre-loader start -->

	<jsp:include page="themeloader.jsp"></jsp:include>
	<!-- Pre-loader end -->
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">

			<jsp:include page="navbar.jsp"></jsp:include>

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">
					<jsp:include page="navBarMainMenu.jsp"></jsp:include>
					<div class="pcoded-content">
						<!-- Page-header start -->
						<jsp:include page="pageHeader.jsp"></jsp:include>

						<!-- Page-header end -->
						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">
										<div class="row">
											<!-- Main body page content -->
											<div class="col-md-12">
												<div class="card">
													<div class="card-header">
														<h5>Usuarios</h5>
														<!--<span>Add class of <code>.form-control</code> with <code>&lt;input&gt;</code> tag</span>-->
													</div>
													<div class="input-group mb-3">
														<input type="text" class="form-control"
															placeholder="Nome do usuario" name="ndBusca" id="ndBusca"
															aria-label="nome" aria-describedby="basic-addon2">
														<input type="hidden" name="acao" id="url" value="<%=request.getContextPath()%>/ServletUsuarioController">
														
														<div class="input-group-append">
															<button class="btn btn-success waves-effect waves-light"
																type="button" onclick="buscarUsuario();">Buscar</button>
														</div>
													</div>

													<div id="tabelaDeDadosUsuarios"
														style="height: 900px; overflow: scroll;">
														<table class="table" id="tabelaUsuarios">
															<thead>
																<tr>
																	<th scope="col">ID</th>
																	<th scope="col">Nome</th>
																	<th scope="col">Ver</th>
																</tr>
															</thead>
															<tbody>
																<c:forEach items="${modelLogins}" var="ml">
																	<tr>
																		<td><c:out value="${ml.id}"></c:out></td>
																		<td><c:out value="${ml.nome}"></c:out></td>
																		<td><button
																				class="btn btn-info waves-effect waves-light btn-icon"
																				onclick="showEdit('${ml.id}');"
																				style="background-color: #fff; border-color: #fff">
																				<i class="icofont icofont-eye-alt"
																					style="color: #448aff"></i>
																			</button></td>
																	</tr>
																</c:forEach>

															</tbody>
														</table>
														-->
													</div>
													<nav aria-label="Page navigation example">
														<ul class="pagination">
														<% 
															int totalPagina = (int) request.getAttribute("totalPagina");
															
															for(int p=0;p<totalPagina;p++){
																
																String url = request.getContextPath()+"/ServletUsuarioController?acao=paginar&pagina="+(p*5);
																out.print("<li class=\"page-item\"><a class=\"page-link\" href=\""+url+"\">"+(p+1)+"</a></li>");
															}
														
														
														
														%>
														</ul>
													</nav>

												</div>
												<!-- Page-body end -->
											</div>
											<div id="styleSelector"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>


				<jsp:include page="javaScript.jsp"></jsp:include>
</body>
<script type="text/javascript">

function showEdit(id) {
		var urlAction = document.getElementById("url").value;
		
		window.location.href = urlAction + "?acao=buscarId" + "&id="
				+ id;

	}
	
	
</script>

</html>