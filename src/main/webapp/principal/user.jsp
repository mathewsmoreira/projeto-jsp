<%@page import="model.ModelLoginServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


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
														<h5>Cadastro usuario</h5>
														<!--<span>Add class of <code>.form-control</code> with <code>&lt;input&gt;</code> tag</span>-->
													</div>
													<div class="card-block">
														<form class="form-material"
															action="<%=request.getContextPath()%>/ServletUsuarioController"
															method="post" id="formUser" enctype="multipart/form-data">
															<input type="hidden" name="acao" id="acao" value="">

															<div class="form-group form-default form-static-label" style="padding-bottom: 4px">
																<div class="card" style="width: 7rem; height: 8rem">
																
																<c:if test="${modelLogin.fotoUser !='' && modelLogin.fotoUser != null}">
																	<a href="<%=request.getContextPath() %>/ServletUsuarioController?acao=downloadFoto&id=${modelLogin.id}"><img class="card-img-top" src="${modelLogin.fotoUser }" alt="Card image cap"
																	style="height: 90%" id="imgPreview" name="imgPreview"></a>
																</c:if>
																<c:if test="${modelLogin.fotoUser =='' || modelLogin.fotoUser ==null}">
																	<img class="card-img-top" src="assets\images\user.png" alt="Card image cap"
																	style="height: 90%" id="imgPreview" name="imgPreview">
																</c:if>
																	
																		
																	<div class="form-group form-default form-static-label"
																		style="width: 100%; height: 5%">
																		<label class="btn btn-primary" style="width: 100%;">
																			Upload
																			<input type="file" value="Upload Photo"
																			style="width: 0px; height: 0px; overflow: hidden;"							
																			id="imgFile" name="imgFile">

																		</label>
																	</div>

																</div>



															</div>


															<div class="form-group form-default form-static-label">
																<input type="text" name="Id" id="Id" class="form-control" required="required" readonly="readonly" value="${modelLogin.id}"> 
																<span class="form-bar"></span>
																<label for="Id" class="float-label">Id:</label>
															</div>



															<div class="form-group form-default form-static-label">
																<input type="text" name="nome" id="nome"
																	class="form-control" required="required"
																	value="${modelLogin.nome}"> <span
																	class="form-bar"></span> <label class="float-label">Nome:</label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="text" name="login" id="login"
																	class="form-control" required="required"
																	autocomplete="off" value="${modelLogin.login}">
																<span class="form-bar"></span> <label
																	class="float-label">Login:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="dataNascimento" id="dataNascimento"
																	class="form-control" required="required"
																	autocomplete="off" value="${modelLogin.datanascimento}">
																<span class="form-bar"></span> <label
																	class="float-label">Data Nascimento:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="rendaMensal" id="rendaMensal"
																	class="form-control" required="required"
																	autocomplete="off" value="${modelLogin.rendamensal }">
																<span class="form-bar"></span> <label
																	class="float-label">Renda Mensal:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="email" name="email" id="email"
																	class="form-control" required="required"
																	autocomplete="off" value="${modelLogin.email}">
																<span class="form-bar"></span> <label
																	class="float-label">Email:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="password" name="senha" id="senha"
																	class="form-control" required="required"
																	value="${modelLogin.senha}"> <span
																	class="form-bar"></span> <label class="float-label">Senha:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="radio" name="sexoUser" value="MASCULINO"
																	id="masculino"
																	<%ModelLoginServlet model = new ModelLoginServlet();
if (request.getAttribute("modelLogin") != null) {
	model = (ModelLoginServlet) request.getAttribute("modelLogin");
	if (model.getSexo().equals("MASCULINO")) {
		out.print(" ");
		out.print("checked=\"checked\"");
		out.print(" ");

	}
}%>>
																<label for="masculino">Masculino</label> <input
																	type="radio" name="sexoUser" value="FEMININO"
																	id="feminino"
																	<%ModelLoginServlet model2 = new ModelLoginServlet();
if (request.getAttribute("modelLogin") != null) {
	model2 = (ModelLoginServlet) request.getAttribute("modelLogin");
	if (model.getSexo().equals("FEMININO")) {
		out.print(" ");
		out.print("checked=\"checked\"");
		out.print(" ");

	}
}%>>
																<label for="Feminino">Feminino</label>

															</div>
																<div class="form-group form-default form-static-label">
																	<input type="text" class="form-control" placeholder="" required="" id="cep" name="cep" onkeypress="soNumero(event)" value="${modelLogin.cep }">
																	<span class="form-bar"></span>
																	<label class="float-label">Cep</label>
																</div>
																<div class="form-group form-default form-static-label">
																	<input type="text" class="form-control" placeholder="" required="" id="logradouro" name="logradouro" value="${modelLogin.logradouro }">
																	<span class="form-bar"></span>
																	<label class="float-label">Rua</label>
																</div>
																<div class="form-group form-default form-static-label">
																	<input type="text" class="form-control" placeholder="" required="" id="numero" name="numero" value="${modelLogin.numero }">
																	<span class="form-bar"></span>
																	<label class="float-label">N°</label>
																</div>
																<div class="form-group form-default form-static-label">
																	<input type="text" class="form-control" placeholder="" required="" id="complemento" name="complemento" value="${modelLogin.complemento }">
																	<span class="form-bar"></span>
																	<label class="float-label">Complemento</label>
																</div>
																<div class="form-group form-default form-static-label">
																	<input type="text" class="form-control" placeholder="" required="" id="bairro" name="bairro" value="${modelLogin.bairro }">
																	<span class="form-bar"></span>
																	<label class="float-label">Bairro</label>
																</div>
																<div class="form-group form-default form-static-label">
																	<input type="text"  class="form-control" required="" placeholder="" id="localidade" name="localidade" value="${modelLogin.localidade }">
																	<span class="form-bar"></span>
																	<label class="float-label">Cidade</label>
																</div>
																<div class="form-group form-default form-static-label">
																	<input type="text" class="form-control" required="" placeholder="" id="uf" name="uf" value="${modelLogin.uf }">
																	<span class="form-bar"></span>
																	<label class="float-label">Estado</label>
																</div>
															<button class="btn btn-primary waves-effect waves-light"
																onclick="limparForm();" type="button">Novo</button>
															<button class="btn btn-success waves-effect waves-light"
																type="submit">Salvar</button>
															<button type="button"
																class="btn btn-secondary waves-effect waves-light"
																type="button" data-toggle="modal"
																data-target="#exampleModal">Buscar</button>

															<c:if test="${modelLogin.id !=null && modelLogin.id !=''}">
																<a href="<%=request.getContextPath() %>/ServletTelefone?acao=buscarUser&idUser=${modelLogin.id}" class="btn btn-success waves-effect waves-light">telefone</a>
															</c:if>
															<button class="btn btn-info waves-effect waves-light"
																type="button" onclick="criarDeleteAjax();">Excluir</button>

														</form>
													</div>
												</div>
												<!-- <span id="msg">${msg}</span> <br> <br> <span>Usuarios
													Carregados</span>

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
													</table> -->

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



		<!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Pesquisar Usuario</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">

						<div class="input-group mb-3">
							<input type="text" class="form-control"
								placeholder="Nome do usuario" name="ndBusca" id="ndBusca"
								aria-label="nome" aria-describedby="basic-addon2">
							<div class="input-group-append">
								<button class="btn btn-success waves-effect waves-light"
									type="button" onclick="buscarUsuario();">Buscar</button>
							</div>
						</div>

						<div id="tabelaDeDados" style="height: 300px; overflow: scroll;">
							<table class="table" id="tabelaUsuario">
								<thead>
									<tr>
										<th scope="col">ID</th>
										<th scope="col">Nome</th>
										<th scope="col">Ver</th>
									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
						</div>
						<span id="nResultados"></span>

					</div>
					<nav aria-label="Page navigation example">
					  <ul class="pagination" id="modalPagina">
					  	

					  </ul>
					</nav>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Fechar</button>
					</div>
				</div>
			</div>
		</div>

		<script type="text/javascript">
			function limparForm() {
				var elementos = document.getElementById("formUser").elements;

				for (p = 0; p < elementos.length; p++) {
					elementos[p].value = "";
				}
				var el = document.getElementById("msg");
				el.value = "";

			}

			function criarDelete() {
				if (confirm("Deseja realmente excluir o registro?")) {

					document.getElementById("formUser").method = "get";
					document.getElementById("acao").value = "deletar";
					document.getElementById("formUser").submit();
				}

			}

			function criarDeleteAjax() {
				if (confirm("Deseja realmente excluir o registro?")) {

					var urlAction = document.getElementById("formUser").action;
					var idUser = document.getElementById("Id").value;

					$
							.ajax(
									{
										method : "get",
										url : urlAction,
										data : "Id=" + idUser
												+ "&acao=deletarAjax",
										success : function(response) {
											limparForm();

											document.getElementById("msg").innerHTML = response;
										}

									})
							.fail(
									function(xhr, status, errorThrown) {
										alert("Erro ao deletar o usuario por Id: "
												+ xhr.responseText);

									});

				}

			}
			
			function buscarUsuarioPg(urlModal){
				
				var ndBusca = document.getElementById("ndBusca").value;
				var urlAction = document.getElementById("formUser").action;
				if (ndBusca != null && ndBusca != '' && ndBusca.trim() != '') {

					$
							.ajax(
									{
										method : "get",
										url : urlAction,
										data : urlModal,
										success : function(response, textStatus, xhr) {
											var json = JSON.parse(response);

											$('#tabelaUsuario > tbody > tr').remove();
											$('#modalPagina > li').remove();
											
											for (var p = 0; p < json.length; p++) {
												$("#tabelaUsuario > tbody")
														.append(
																'<tr> <td>'
																		+ json[p].id
																		+ '</td> <td>'
																		+ json[p].nome
																		+ '</td> <td><button class="btn btn-info waves-effect waves-light btn-icon" onclick="showEdit('
																		+ json[p].id
																		+ ');" style="background-color:#fff; border-color:#fff"><i class="icofont icofont-eye-alt" style="color:#448aff"></i></button></td></tr>');

											}
											document.getElementById("nResultados").innerHTML = "Total de Resultados: "+ json.length;
											
											
											var totalPagina = xhr.getResponseHeader("totalPaginas");
											for(var p =0; p < totalPagina; p++){
												var urlModal="nome="+ndBusca+"&acao=paginarModal&pagina="+(p*5);
												$("#modalPagina").append('<li class="page-item"><a class="page-link" href="#" onclick="buscarUsuarioPg(\''+urlModal+'\')">'+(p+1)+'</a></li>')
												
												
												
												
												
												
											}

										}

									}).fail(
									function(xhr, status, errorThrown) {
										alert("Erro ao pesquisar o usuario: "
												+ xhr.responseText);

									});

				}

				
				
			}

			function buscarUsuario() {
				var ndBusca = document.getElementById("ndBusca").value;
				var urlAction = document.getElementById("formUser").action;
				if (ndBusca != null && ndBusca != '' && ndBusca.trim() != '') {

					$
							.ajax(
									{
										method : "get",
										url : urlAction,
										data : "nome=" + ndBusca
												+ "&acao=pesquisarAjax",
										success : function(response, textStatus, xhr) {
											var json = JSON.parse(response);

											$('#tabelaUsuario > tbody > tr')
													.remove();
											$('#modalPagina > li').remove();
											for (var p = 0; p < json.length; p++) {
												$("#tabelaUsuario > tbody")
														.append(
																'<tr> <td>'
																		+ json[p].id
																		+ '</td> <td>'
																		+ json[p].nome
																		+ '</td> <td><button class="btn btn-info waves-effect waves-light btn-icon" onclick="showEdit('
																		+ json[p].id
																		+ ');" style="background-color:#fff; border-color:#fff"><i class="icofont icofont-eye-alt" style="color:#448aff"></i></button></td></tr>');

											}
											document.getElementById("nResultados").innerHTML = "Total de Resultados: "+ json.length;
											
											
											var totalPagina = xhr.getResponseHeader("totalPaginas");
											for(var p =0; p < totalPagina; p++){
												var urlModal="nome="+ndBusca+"&acao=paginarModal&pagina="+(p*5);
												$("#modalPagina").append('<li class="page-item"><a class="page-link" href="#" onclick="buscarUsuarioPg(\''+urlModal+'\')">'+(p+1)+'</a></li>')
												
												
												
												
												
												
											}

										}

									}).fail(
									function(xhr, status, errorThrown) {
										alert("Erro ao pesquisar o usuario: "
												+ xhr.responseText);

									});

				}

			}

			function showEdit(id) {
				var urlAction = document.getElementById("formUser").action;
				window.location.href = urlAction + "?acao=buscarId" + "&id="
						+ id;

			}
			
			
		let input      = document.querySelector("#imgFile");
		let previewImg = document.querySelector("#imgPreview");

		input.addEventListener("change",(e)=>{handleChange(e)})

		function handleChange(event){
			let file     = event.target.files[0];
			const reader = new FileReader();
			
			reader.readAsDataURL(file);
			
			reader.onload = (e)=>{
			previewImg.src        = e.target.result;
			}
		}
		$(document).ready(function() {

		function limpa_formulario_cep() {
			// Limpa valores do formulÃ¡rio de cep.
			$("#rua").val("");
			$("#logradouro").val("");
			$("#localidade").val("");
			$("#uf").val("");
		}})




		$("#cep").blur(function() {

			//Nova variÃ¡vel "cep" somente com dÃ­gitos.
			var cep = $(this).val().replace(/\D/g, '');

			//Verifica se campo cep possui valor informado.
			if (cep != "") {

				//ExpressÃ£o regular para validar o CEP.
				var validacep = /^[0-9]{8}$/;

				//Valida o formato do CEP.
				if(validacep.test(cep)) {

					//Preenche os campos com "..." enquanto consulta webservice.
					$("#logradouro").val("...");
					$("#bairro").val("...");
					$("#localidade").val("...");
					$("#uf").val("...");

					//Consulta o webservice viacep.com.br/
					$.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

						if (!("erro" in dados)) {
							//Atualiza os campos com os valores da consulta.
							$("#logradouro").val(dados.logradouro);
							$("#bairro").val(dados.bairro);
							$("#localidade").val(dados.localidade);
							$("#uf").val(dados.uf);
						} //end if.
						else {
							//CEP pesquisado nÃ£o foi encontrado.
							limpa_formulario_cep();
							alert("CEP não encontrado.");
						}
					});
				} //end if.
				else {
					//cep Ã© invÃ¡lido.
					limpa_formulario_cep();
					alert("Formato de CEP invalido.");
				}
			} //end if.
			else {
				//cep sem valor, limpa formulÃ¡rio.
				limpa_formulario_cep();
			}
		});
		
		$( function() {
			  
			  $("#dataNascimento").datepicker({
				    dateFormat: 'dd/mm/yy',
				    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
				    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
				    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
				    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
				    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
				    nextText: 'Próximo',
				    prevText: 'Anterior'
				});
		} );
		
		var rendaMensalInput = document.getElementById("rendaMensal");

		// Adicione um listener para o evento "input"
		rendaMensalInput.addEventListener("input", function() {
	        var valor = this.value;
	        valor = valor + '';
	        valor = parseInt(valor.replace(/[\D]+/g, ''));
	        valor = valor + '';
	        valor = valor.replace(/([0-9]{2})$/g, ",$1");

	        if (valor.length > 6) {
	            valor = valor.replace(/([0-9]{3}),([0-9]{2}$)/g, ".$1,$2");
	        }

	        this.value = 'R$ '+valor;
	        if(valor == 'NaN') this.value = '';
		});
		
		
		const formatter = new Intl.NumberFormat('pt-BR', {
			  currency: 'BRL',
			  minimumFractionDigits: 2
			});

		$("#rendaMensal").val('R$ '+formatter.format($('#rendaMensal').val()));
		$('#rendaMensal').focus();
		
		var dataNascimento = $("#dataNascimento").val();
		if (dataNascimento !=null && dataNascimento !=""){
		var data = new Date(dataNascimento);
			$("#dataNascimento").val(data.toLocaleDateString('pt-br',{timeZone: 'UTC'}));
			$("dataNascimento").focus()
		}
		
		
		function soNumero() {
			  $("#cep").on("input", function() {
			    var cep = $(this).val();
			    var numericCep = cep.replace(/\D/g, ""); // Remove todos os caracteres não numéricos

			    $(this).val(numericCep); // Define o valor do campo como somente números
			  });
			}
		$(document).ready(function() {

			function limpa_formulario_cep() {
				
				$("#rua").val("");
				$("#logradouro").val("");
				$("#localidade").val("");
				$("#uf").val("");
			}});
		$("#form-material").submit(function(event) {
			  var campoRenda = $("#rendaMensal");
			  var valorCampo = campoInput.val();

			  // Remover a máscara (por exemplo, remover todos os caracteres não numéricos)
			  var valorSemMascara = valorCampo.replace(/\D/g, "");

			  // Atualizar o valor do campo de entrada sem a máscara
			  campoInput.val(valorSemMascara);
			  
			  
			  $("#form-material").submit();
			});

		
		
	
		</script>
</body>

</html>