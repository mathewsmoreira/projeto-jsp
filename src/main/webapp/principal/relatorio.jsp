
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
											<div class="col-md-12">
												<div class="card">
													<div class="card-header">
														<h1>Relatório</h1>

													</div>
													<div class="card-block">
														<form class="form-inline" action="<%=request.getContextPath() %>/ServletUsuarioController" method="get" id="formData">
														<input type="hidden" name="acao" id ="acao" value="">
														  
														  <div class="input-group mb-2 mr-sm-2">
														    <div class="input-group-prepend">
														      <div class="input-group-text"></div>
														    </div>
														    <input type="text" class="form-control" id="dataInicial" name="dataInicial" placeholder="Data Inicial">
														  </div>
			
														  <div class="input-group mb-2 mr-sm-2">
														    <div class="input-group-prepend">
														      <div class="input-group-text"></div>
														    </div>
														    <input type="text" class="form-control" id="dataFinal" name="dataFinal" placeholder="Data Final">
														  </div>
														<div class="input-group mb-2 mr-sm-2">
															<button type="button" class="btn btn-primary waves-effect waves-light" id="pdf" onclick="sub(event)">Imprimir PDF</button>
														</div>
														<div class="input-group mb-2 mr-sm-2">
															<button type="button" class="btn btn-primary waves-effect waves-light" id="excel" onclick="sub(event)">Imprimir Excel</button>
														</div>
														  
														  
														</form>
													</div>
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
</body>

<script type="text/javascript">

function sub(event) {
	var rel = event.target.id;
	console.log(rel);
	var form = document.getElementById("formData");

	if ( rel == "pdf") {
		var acao = $("#acao");
		acao.val("relatorioUser");
		form.submit();
	}else{
		var acao = $("#acao");
		console.log("else")
		acao.val("relatorioUserXls");
		console.log(acao.val());
		form.submit();
	}
};


	$( function() {
		  
		  $("#dataInicial").datepicker({
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
	
	$( function() {
		  
		  $("#dataFinal").datepicker({
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


</script>

</html>
