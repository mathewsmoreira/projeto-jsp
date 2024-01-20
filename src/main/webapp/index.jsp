<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Projeto JSP</title>

	<!-- CSS bootstrap -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	
	<!---CSS proprio que vai substituir o css do bootstrap--->
	<link rel="stylesheet"  type="text/css" href="<%=request.getContextPath()%>/CSS/styles.css">
	<!--JS do bootstrap-->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

	<!--Icons do bootstrap-->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">


</head>
<body>
	<section class="vh-100 backgroud_custom">
		<div class="container py-4 h-50">
		  <div class="row d-flex justify-content-center align-items-center h-70">
			<div class="col-12 col-md-8 col-lg-6 col-xl-5">
			  <div class="card bg-dark text-white" style="border-radius: 1rem;">
				<div class="card-body p-5 text-center">
	  
				  <div class="mb-md-1 mt-md-5 pb-2">
	  
					<h3 class="fw-semibold fs-4 mb-2 text-uppercase">Bem Vindo ao Projeto de JSP.</h3>
					<p class="text-white-50 mb-5">Please enter your login and password!</p>
	  
					<form action="ServletLogin" method="post">
						<input type="hidden" name="url" value="<%=request.getParameter("url")%>">
						
						<div class="form-outline form-white mb-4">
						<input type="text" id="typeEmailX" class="form-control form-control-lg" name="login" required=""/>
						<label class="form-label" for="typeEmailX">login</label>
						</div>
		
						<div class="form-outline form-white mb-4">
						<input type="password" id="typePasswordX" class="form-control form-control-lg"  name="senha" required=""/>
						<label class="form-label" for="typePasswordX">senha</label>
						</div>

						<button class="btn btn-outline-light btn-lg px-5" type="submit">Login</button>
		
						<div class="d-flex justify-content-center text-center mt-4 pt-1">
						<p class="text-white-50 fs-4 mb-4 justify-content-center">${msg}</p>
						<a href="#!" class="text-white"><i class="fab fa-facebook-f fa-lg"></i></a>
						<a href="#!" class="text-white"><i class="fab fa-twitter fa-lg mx-4 px-2"></i></a>
						<a href="#!" class="text-white"><i class="fab fa-google fa-lg"></i></a>
						</div>
					</form>
					
				  </div>	  
				</div>
			  </div>
			</div>
			
		  </div>
		  
		</div>
		
	  </section>
	  
	  <script type="text/javascript">
		// Example starter JavaScript for disabling form submissions if there are invalid fields
		  (() => {
		    'use strict'
	
		    // Fetch all the forms we want to apply custom Bootstrap validation styles to
		    const forms = document.querySelectorAll('.needs-validation')
	
		    // Loop over them and prevent submission
		    Array.from(forms).forEach(form => {
		      form.addEventListener('submit', event => {
		        if (!form.checkValidity()) {
		          event.preventDefault()
		          event.stopPropagation()
		        }
	
		        form.classList.add('was-validated')
		      }, false)
		    })
		  })()
	  
	  </script>
</body>
</html>