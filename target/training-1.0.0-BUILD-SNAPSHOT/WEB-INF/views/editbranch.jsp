<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.*"%>
<%@ page import="com.gcit.training.lms.dao.*"%>
<%@ page import="java.util.List"%>
<%@include file="include.html"%>

<div class="container">
		<h2>Branch details:</h2><br><br>
		  <form action="updateBranch" method ="post">
		    <div class="form-group">
			      <label for="branchName">Branch Name</label>
			      <input type="text" class="form-control" name="branchName"   placeholder="Enter branch name" required>
			    </div>
			     <div class="form-group">
			      <label for="branchId"></label>
			      <input type="hidden" class="form-control" name="branchId" value='${branchId}'>
			    </div>
			    <div class="form-group">
			      <label for="pubId">Branch address:</label>
			      <input type="text" class="form-control" name="branchAddress"  placeholder="branch address" required>
			    </div>
			    
			    <input type="submit" class="btn btn-success" value="Submit">
		  </form>
</div>
<script>
	$(document).ready(function() {
		$(function() {
			$('#updateBranch').on('submit', function(e) {
				e.preventDefault();
				 
				$.ajax({
					url : "updateBranch", //this is the submit URL
					type : 'POST', //or POST
					data : $('#updateBranch').serialize(),
					success : function(data) {
						if (data.trim() == "success") {
							alert("Branch Successfully updated.");
						} else {
							alert("There was a problem updating branch.");

						}
						//$("#myModal1").modal("toggle");
					}
				});
			});
		});

	});
</script>
  





