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
          <div class="card mb-3">
            <div class="card-header">
            <i class="fas fa-chart-area"></i>
              SteelJ Transaction History in 14 days </div>
            <div class="card-body">
              <canvas id="myAreaChart" width="100%" height="30"></canvas>
            </div>
            <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
          </div>

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
                    <tr>
                      <td>654</td>
                      <td>0x289389283927983</td>
                      <td>3.54</td>
                      <td>2018/11/30</td>                      
                    </tr>
                    <tr>
                      <td>653</td>
                      <td>0x289389283927983</td>
                      <td>3.54</td>
                      <td>2018/11/30</td>                      
                    </tr>
                    <tr>
                      <td>654</td>
                      <td>0x289389283927983</td>
                      <td>3.54</td>
                      <td>2018/11/30</td>                      
                    </tr>
                    <tr>
                      <td>653</td>
                      <td>0x289389283927983</td>
                      <td>3.54</td>
                      <td>2018/11/30</td>                      
                    </tr>
                    <tr>
                      <td>654</td>
                      <td>0x289389283927983</td>
                      <td>3.54</td>
                      <td>2018/11/30</td>                      
                    </tr>
                    <tr>
                      <td>653</td>
                      <td>0x289389283927983</td>
                      <td>3.54</td>
                      <td>2018/11/30</td>                      
                    </tr>
                    <tr>
                      <td>654</td>
                      <td>0x289389283927983</td>
                      <td>3.54</td>
                      <td>2018/11/30</td>                      
                    </tr>
                    <tr>
                      <td>653</td>
                      <td>0x289389283927983</td>
                      <td>3.54</td>
                      <td>2018/11/30</td>                      
                    </tr>
                    <tr>
                      <td>654</td>
                      <td>0x289389283927983</td>
                      <td>3.54</td>
                      <td>2018/11/30</td>                      
                    </tr>
                    <tr>
                      <td>653</td>
                      <td>0x289389283927983</td>
                      <td>3.54</td>
                      <td>2018/11/30</td>                      
                    </tr>                            
                  </tbody>
                </table>
              </div>
            </div>
            <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
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
