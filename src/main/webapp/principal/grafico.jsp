
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
											<input type="hidden" id="url" value="<%=request.getContextPath()+"/ServletUsuarioController" %>">
											<div class="col-md-12 col-lg-6">
                                                <div class="card">
                                                    <div class="card-header">
                                                        <h5>Site Visit Chart</h5>
                                                        <button type="button" class="btn btn-secondary waves-effect waves-light" type="button" onclick="construir()">Gráfico</button>
                                                    </div>
                                                    <div class="card-block">
                                                        <div >
                                                        	<canvas id="myChart"></canvas>
                                                        </div>
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
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script type="text/javascript">


	const ctx = document.getElementById('myChart');
	var myChart = new Chart(ctx);
	
	function construir(){
		
		var url = document.getElementById("url").value
		console.log(url);
		$.ajax({
			method : "get",
			url : url,
			data : "acao=gerarGrafico",
			success : function(response, textStatus, xhr) {
				var json = JSON.parse(response);
				var labels = ["usuarios"]
				myChart.destroy();
				myChart= new Chart(ctx, {
					    type: 'bar',
					    data: {
					      labels: labels,
					      datasets: [{
						        label:json.rotulos[0],
						        data: [json.valores[0]],
						        borderWidth: 1
						      },{
							       label:json.rotulos[1] ,
							       data: [json.valores[1]],
							       borderWidth: 1
							      }]
					    },
					    options: {
					    	responsive: true,
					      scales: {
					        y: {
					          beginAtZero: true
					        }
					      }
					    }
					  });


			}}).fail(
				function(xhr, status, errorThrown){
					alert("Erro ao pesquisar o usuario: "+ xhr.responseText);
				}

			);
	}

	

</script>

</html>
