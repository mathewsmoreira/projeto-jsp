<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
																<h5>Cadastro de Telefone</h5>
																<!--<span>Add class of <code>.form-control</code> with <code>&lt;input&gt;</code> tag</span>-->
															</div>
															<div class="card-block">
																<form class="form-material"
																	action="<%=request.getContextPath()%>/ServletTelefone"
																	method="post" id="formTelefone">
																	<input type="hidden" name="acao" id="acao" value="">
																	<div
																		class="form-group form-default form-static-label">
																		<input type="text" name="Id" id="Id"
																			class="form-control" required="required"
																			readonly="readonly"
																			value="${modelLogin.usuario_pai_id.getId()}">
																		<span class="form-bar"></span> <label for="Id"
																			class="float-label">Id:</label>
																	</div>
																	<div
																		class="form-group form-default form-static-label">

																		<input type="text" name="Nome" id="Nome"
																			class="form-control" required="required"
																			readonly="readonly"
																			value="${modelLogin.usuario_pai_id.getNome() }">
																		<span class="form-bar"></span> <label for="Nome"
																			class="float-label">Nome:</label>
																	</div>
																	<div
																		class="form-group form-default form-static-label">
																		<input type="hidden"
																			value="${modelLogin.numero}" id="nuAnt"
																			name="nuAnt">
																		<input type="text" name="Numero" onblur="limitar_caracteres(event)" id="Numero"
																			class="form-control phone"
																			required="required"
																			value="${modelLogin.numero}"
																			onfocus="soNumero(event)">
																		<span class="form-bar"></span> <label
																			for="Numero"
																			class="float-label">Numero:</label>
																	</div>
																	<button
																		class="btn btn-primary waves-effect waves-light"
																		onclick="limparForm();"
																		type="button">Novo</button>
																	<button
																		class="btn btn-success waves-effect waves-light"
																		type="submit">Salvar</button>
																	<button
																		class="btn btn-info waves-effect waves-light"
																		type="button"
																		onclick="criarDelete();">Excluir</button>
																</form>
																<span id="msg" value="${msg}"></span>
															</div>

														</div>
														<div id="tabelaDeTelefones"
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
																			<td>
																				<c:out value="${ml.id}"></c:out>
																			</td>
																			<td>
																				<c:out value="${ml.numero}"></c:out>
																			</td>
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

													</div>




												</div>
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

			<script type="text/javascript">
			
			
			
			
				function showEdit(id) {
					var urlAction = 'http://localhost:8080/projeto-jsp/ServletTelefone';

					var idUser = $("#Id").val()

					window.location.href = urlAction + "?acao=editarTelefone"
						+ "&idTel=" + id + "&idUser=" + idUser;

				}



				function limparForm() {
					var numero = document.getElementById("Numero");
					numero.value = "";
					var el = document.getElementById("msg");
					el.value = "";

				}

				function soNumero(event) {
					this.addEventListener("keypress", function(event) {
					var inputCharacter = String.fromCharCode(event.keyCode);

					if (!/\d/.test(inputCharacter)) {
						event.preventDefault();
					}
				})
				};
				function criarDelete() {

					if (confirm("Deseja realmente excluir o registro?")) {

						document.getElementById("formTelefone").method = "get";
						document.getElementById("acao").value = "deletar";
						document.getElementById("formTelefone").submit();
					}

				}
				
				function limitar_caracteres(event){
					
					var elementoId = $(event.target).attr("id");
					
					if(elementoId == "Numero"){
						var valor = $(event.target).val();
						
						valor = valor.slice(0,50);
						$(event.target).val(valor)
					}
					}
					
					
				}
			</script>
		</body>

		</html>