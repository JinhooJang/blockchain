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
   			
   			var param = "from=" + $('#jobSeeker').val();
   				param += "&to=" + $('#company').val();
   				param += "&action=" + $('#action').val();
   			
   			$.ajax({
				url: "http://localhost:8080/steelj/job-search/create",
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
              <a href="#">Job Search</a>
            </li>
          </ol>
          
        <!-- 전송 -->
		<div class="col-md-8">
			<div class="card my-8">
	            <h5 class="card-header">Job Search</h5>
	            <div class="card-body">
	            	<table width="800px;">
	            		<tr>
	            			<td>구직자</td>
	            			<td>
								<div class="input-group">
									<!-- <input type="text" id="name" class="form-control" > -->								                
									<select id="jobSeeker" name="jobSeeker">
										<c:forEach var="item" items="${jobSeeker}">
									  		<option value="${item.address}">${item.address}</option>
									  	</c:forEach>
									</select>
					            </div>					            
				            </td>
			            </tr>
			            <tr>
			            	<td>회사</td>
			            	<td>
			            		<div class="input-group">
									<!-- <input type="text" id="name" class="form-control" > -->								                
									<select id="company" name="company">
										<c:forEach var="item" items="${company}">
									  		<option value="${item.address}">${item.address}</option>
									  	</c:forEach>
									</select>
					            </div>
			            	</td>
			            </tr>
			            <tr>
			            	<td>action</td>
			            	<td>
			            		<div class="input-group">
									<!-- <input type="text" id="name" class="form-control" > -->								                
									<select id="action" name="action">
										<option value="apply">지원</option>
										<option value="scrap">스크랩</option>	
										<option value="interview">면접</option>								  	
									</select>
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
		<!-- 선택 -->
		<div class="col-md-8">
			<div class="card my-8">
	            <h5 class="card-header">Address List</h5>
	            <div class="card-body">
	            	<table width="800px;">
	            		<tr>
	            			<td>주소선택</td>
	            			<td>
								<div class="input-group">
									<!-- <input type="text" id="name" class="form-control" > -->								                
									<select id="address" name="address" onchange="goTo(this.value);">
										<c:forEach var="item" items="${jobSeeker}">
									  		<option value="${item.address}">${item.address}</option>
									  	</c:forEach>
									  	<c:forEach var="item" items="${company}">
									  		<option value="${item.address}">${item.address}</option>
									  	</c:forEach>
									</select>
					            </div>					            
				            </td>
			            </tr> 			            
					</table>		            
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
