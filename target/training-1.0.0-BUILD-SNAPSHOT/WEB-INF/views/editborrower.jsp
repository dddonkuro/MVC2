<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.*"%>
<%@ page import="com.gcit.training.lms.dao.*"%>
<%@ page import="java.util.List"%>
<%@include file="include.html"%>

  
<div class="container">
	<div class="row">
	<div class="col-md-6 col-md-offset-3">
	<form id="borrower">
	<br><br>
		<h2>Borrower details:</h2>
			<div class="form-group">
		    <labe for="borrowerId"></labe>
		    <input type="hidden" name="borrowerId"	value='${borrowerId}'>
		    </div>
		    <div class="form-group">	
		    	<label for="borrowerName">Borrower Name:</label>
		    	 <input type="text" name="borrowerName"
			value='${borrowerName}'>
			</div>
			<div class="form-group">	
		    	<label for="branchAddress">Address:</label>
		    	 <input type="text" name="borrowerAddress"
			value='${borrowerAddress}'>
			</div>
			<div class="form-group">	
		    	<label for="">Phone:</label>
		    	 <input type="text" name="borrowerPhone"
			value='${borrowerPhone}'>
			</div>
			
		 <button type="submit" class="btn btn-sm btn-success">Submit
	</form>
	</div>
</div>
<script>
	$(document).ready(function() {
		$(function() {
			$('#borrower').on('submit', function(e) {
				e.preventDefault();
				 
				$.ajax({
					url : "updateBorrower", //this is the submit URL
					type : 'POST', //or POST
					data : $('#borrower').serialize(),
					success : function(data) {
						if (data.trim() == "success") {
							alert("Borrower Successfully updated.");
						} else {
							alert("There was a problem modifying Borrower.");

						}
						//$("#myModal1").modal("toggle");
					}
				});
			});
		});

	});
</script>
  





