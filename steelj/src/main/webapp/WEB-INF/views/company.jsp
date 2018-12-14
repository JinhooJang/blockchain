<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="ko">

  <head>
    <!-- header -->
    <jsp:include flush="false" page="./common/header.jsp"/>
    <script>
    $( document ).ready(function() {
   		
   		$('#createBtn').click(function(){
   			var param = "name=" + $('#name').val();
   			
			$.ajax({
				url: "http://localhost:8080/steelj/company/create",
		        type: 'GET',
		        data : param,
		        dataType: 'json', // added data type
		        success: function(data) {
		        	alert(data.success);		        	
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
              <a href="#">Company</a>
            </li>
          </ol>
          
        <!-- 전송 -->
		<div class="col-md-8">
			<div class="card my-8">
	            <h5 class="card-header">create company</h5>
	            <div class="card-body">
	            	<table width="800px;">
	            		<tr>
	            			<td>name</td>
	            			<td>
								<div class="input-group">
									<input type="text" id="name" class="form-control" >								                
					            </div>
				            </td>
			            </tr>            
					</table>
		            </br>
		            <div class="input-group">
		                <span class="input-group-btn">
		                  <button class="btn btn-secondary" id="createBtn" type="button">create</button>
		                </span>
		            </div>
		        </div>
			</div>
		</div>			
        </br>
          <!-- DataTables Example -->
          <div class="card mb-3">
            <div class="card-header">
              <i class="fas fa-table"></i>
              Company List</div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th>name</th>
                      <th>address</th>
                    </tr>
                  </thead>                  
                  <tbody>
	                  <c:forEach var="item" items="${result}">
	                    <tr>
	                      <td>${item.name}</td>
	                      <td>${item.address}</td>              
	                    </tr>
	                  </c:forEach>
                  </tbody>
                </table>
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
