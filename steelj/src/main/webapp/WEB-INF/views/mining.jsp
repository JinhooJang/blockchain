<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">

  <head>
    <!-- header -->
    <jsp:include flush="false" page="./common/header.jsp"/>
    
    <script>
    var mining = false;
	$( document ).ready(function() {
		$('#startBtn').click(function(){
			$.ajax({
		        url: "http://localhost:8080/steelj/mining/start",
		        type: 'GET',
		        dataType: 'json', // added data type
		        success: function(data) {
		        	alert(data.isMining);
		        	
		        	$('#mining-status').text("mining : " + data.isMining);
		        	//alert(data.account.address);
		       	}
	        });
		});
		
		$('#endBtn').click(function(){
			$.ajax({
		        url: "http://localhost:8080/steelj/mining/end",
		        type: 'GET',
		        dataType: 'json', // added data type
		        success: function(data) {
		        	alert(data.isMining);
		        	
		        	$('#mining-status').text("mining : " + data.isMining);
		        	//alert(data.account.address);
		       	}
	        });
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
              <a href="#">Blocks</a>
            </li>
            <li class="breadcrumb-item active">Mining Status</li>
          </ol>
     
          <div class="col-md-4">
			<div class="card my-4">
	            <h5 class="card-header">Mining</h5>
	            <div class="card-body">
					<div class="input-group">
		                <span class="input-group-btn">
		                  <button class="btn btn-secondary" id="startBtn" type="button">start</button>
		                  <button class="btn btn-secondary" id="endBtn" type="button">end</button>
		                </span>
		            </div>
		            
		            <br>
		            <div id="mining-status" class="input-group">
		               mining : ${isMining}
		            </div>
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
    
    <!-- Navigation -->
    <jsp:include flush="false" page="./common/footer.jsp"/>
    
  </body>

</html>
