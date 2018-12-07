<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">

  <head>
    <!-- header -->
    <jsp:include flush="false" page="./common/header.jsp"/>
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
              <a href="#">Dashboard</a>
            </li>
            <li class="breadcrumb-item active">Overview</li>
          </ol>
          
          <!-- Area Chart Example-->
          <!-- DataTables Example -->
          <div class="card mb-3">
            <div class="card-header">
              <i class="fas fa-table"></i>
              Transactions</div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th>Transfer Date</th>
                      <th>From</th>
                      <th>To</th>                      
                      <th>Stlj</th>       
                      <th>Memo</th>
                    </tr>
                  </thead>                  
                  <tbody>
	                  <c:forEach var="item" items="${transResult}">
	                    <tr>
	                      <td>${item.age}</td>
	                      <td>${item.from}</td>
	                      <td>${item.to}</td>
	                      <td>${item.stlj}</td>
	                      <td>${item.memo}</td>                 
	                    </tr>
	                  </c:forEach>                   
                  </tbody>
                </table>
              </div>
            </div>            
          </div>

        </div>
        <!-- /.container-fluid -->

          <!-- DataTables Example -->
          <div class="card mb-3">
            <div class="card-header">
              <i class="fas fa-table"></i>
              Block</div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th>Block No</th>
                      <th>Mined By</th>
                      <th>Reward</th>                      
                      <th>Mined Date</th>       
                    </tr>
                  </thead>                  
                  <tbody>
					<c:forEach var="item" items="${blockResult}">
	                    <tr>
	                      <td>${item.no}</td>
	                      <td>${item.mined}</td>
	                      <td>${item.reward}</td>
	                      <td>${item.age}</td>                      
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
