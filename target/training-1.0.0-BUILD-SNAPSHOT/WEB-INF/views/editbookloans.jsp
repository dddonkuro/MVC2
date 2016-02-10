<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.*"%>
<%@ page import="com.gcit.training.lms.dao.*"%>
<%@ page import="java.util.List"%>

<%
	AdministrativeService adminService = new AdministrativeService();
%>


  
<div class="container">
	<div class="row">
	<div class="col-md-6 col-md-offset-3">
	<form id="branch">
	<br><br>
		<h2>Branch details:</h2>
			<div class="form-group">
		    <labe for="branchId">Branch ID: </labe>
		    <input type="text" name="branchId"	value='${branchId}'>
		    </div>
		    <div class="form-group">	
		    	<label for="branchName">Branch Name:</label>
		    	 <input type="text" name="branchName"
			value='${branchName}'>
			</div>
			<div class="form-group">	
		    	<label for="branchName">Branch Address:</label>
		    	 <input type="text" name="branchName"
			value='${branchName}'>
			</div>
		 <button type="submit" class="btn btn-sm btn-success">Submit
	</form>
	</div>
</div>
<script>
	$(document).ready(function() {
		$(function() {
			$('#branch').on('submit', function(e) {
				e.preventDefault();
				 
				$.ajax({
					url : "edddBranch", //this is the submit URL
					type : 'POST', //or POST
					data : $('#branch').serialize(),
					success : function(data) {
						if (data.trim() == "success") {
							alert("Branch Successfully added.");
						} else {
							alert("There was a problem adding branch.");

						}
						$("#myModal1").modal("toggle");
					}
				});
			});
		});

	});
</script>
  





