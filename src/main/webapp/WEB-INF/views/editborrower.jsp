<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.*"%>
<%@ page import="com.gcit.training.lms.dao.*"%>
<%@ page import="java.util.List"%>
<%@include file="include.html"%>

<div class="container">
<h2>Borrower details:</h2>
		  <form action="updatetBorrower" method="post">
		    <div class="form-group">
		    	<div class="form-group">
			      <label for="cardNo"></label>
			      <input type="hidden" class="form-control" name="cardNo" value='${cardNo}'>
			    </div>
			     <div class="form-group">
			      <label for="borrowerName">Borrower Name:</label>
			      <input type="text" class="form-control" name="borrowerName" placeholder ="Enter borrower name">
			    </div>
			    <div class="form--group">
			      <label for="borrowerAddress">borrower address</label>
			      <input type="text" class="form-control" name="borrowerAddress"   placeholder="Enter borrower address" required>
			    </div>
			    <div class="form--group">
			      <label for="borrowerPhone">borrower phone</label>
			      <input type="text" class="form-control" name="borrowerPhone"   placeholder="Enter borrower phone" required>
			    </div>
			    <br>
			    <input type="submit" class="btn btn-success" value=" Update">
		  </form>
</div>
<script>
	$(document).ready(function() {
		$(function() {
			$('#updatetBorrower').on('submit', function(e) {
				e.preventDefault();
				 
				$.ajax({
					url : "updateBorrower", //this is the submit URL
					type : 'POST', //or POST
					data : $('#updatetBorrower').serialize(),
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
  





