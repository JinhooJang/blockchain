<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">

  <head>
    <!-- header -->
    <jsp:include flush="false" page="./common/header.jsp"/>    
    
	<script>
	$( document ).ready(function() {
		$('#createBtn').click(function(){
			$.ajax({
		        url: "http://localhost:8080/steelj/wallet/id",
		        type: 'GET',
		        dataType: 'json', // added data type
		        success: function(data) {
		        	alert(data.msg);
		        	
		        	$('#address_val').val(data.account.address);
		        	//alert(data.account.address);
		       	}
	        });
		});
		
		$.ajax({
	        url: "http://localhost:8080/steelj/wallet/get-address",
	        type: 'GET',
	        dataType: 'json', // added data type
	        success: function(data) {
	        	$('#address_val').val(data.address);
	        	//alert(data.account.address);
	       	}
        });
	});
	</script>
  </head>

  <body id="page-top">

   	<!-- nav -->
    <jsp:include flush="false" page="./common/nav.jsp"/>

    <div id="wrapper">

      <!-- Sidebar -->
      <jsp:include flush="false" page="./common/menu.jsp"/>

      <div id="content-wrapper">
      
        <div class="container-fluid">
			<!-- Breadcrumbs-->
			<ol class="breadcrumb">
				<li class="breadcrumb-item">
					<a href="index.html">Dashboard</a>
				</li>
				<li class="breadcrumb-item active">Register Page</li>
          	</ol>

			<!-- Page Content -->
			<h1>Register</h1>
			<hr>
			<div class="col-md-4">
				<div class="card my-4">
		            <h5 class="card-header">create wallet</h5>
		            <div class="card-body">
						<div class="input-group">
			                <span class="input-group-btn">
			                  <button class="btn btn-secondary" id="createBtn" type="button">create</button>
			                </span>
			            </div>
			            <br>
			            <div class="input-group">
			                <input type="text" id="address_val" class="form-control" >			                
			            </div>
			        </div>
				</div>
			</div>			
        <!-- /.container-fluid -->

        <!-- Sticky Footer -->
		<footer class="sticky-footer">
			<div class="container my-auto">
				<div class="copyright text-center my-auto">
					<span>Copyright © Steel 2018</span>
				</div>
			</div>
		</footer>

      </div>
      <!-- /.content-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- Scroll to Top Button-->
    <jsp:include flush="false" page="./common/footer.jsp"/>
    
  </body>

</html>
