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
				url: "http://localhost:8080/steelj/job-seeker/create",
		        type: 'GET',
		        data : param,
		        dataType: 'json', // added data type
		        success: function(data) {
		        	alert(data.success);		        	
		       	}
	        });
		});
    });
    
    function goTo( value ) {
    	$('#address_val').val(value);
    	$('#showForm').submit();
    }
   	</script>
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

           <!-- DataTables Example -->
          <div class="card mb-3">
            <div class="card-header">
              <i class="fas fa-table"></i>
              Address ${address}</div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th>from</th>
                      <th>to</th>
                      <th>action</th>
                    </tr>
                  </thead>                  
                  <tbody>
	                  <c:forEach var="item" items="${result}">
	                    <tr>
	                      <td>
		                      <c:if test="${item.from eq address}">
		                      	<b>${item.from}</b>
		                      </c:if>
		                      <c:if test="${item.from ne address}">
		                      	 <a href="javascript:goTo('${item.from}');">${item.from}</a>
		                      </c:if>
	                      </td>
	                      <td>
							  <c:if test="${item.to eq address}">
		                      	<b>${item.to}</b>
		                      </c:if>
		                      <c:if test="${item.to ne address}">
		                      	<a href="javascript:goTo('${item.to}');">${item.to}</a>
		                      </c:if>
	                      </td>
	                      <td>${item.action}</td>
	                    </tr>
	                  </c:forEach>
                  </tbody>
                </table>
              </div>
            </div>            
          </div>

        </div>
        <!-- /.container-fluid -->
        <form name="showForm" id="showForm" action="show-address">
        	<input type="hidden" name="address" id="address_val" value="" />
        </form>

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
