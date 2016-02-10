<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.*"%>
<%@ page import="com.gcit.training.lms.dao.*"%>
<%@ page import="java.util.List"%>
<%@include file="include.html"%>

<div class="container">
<h2>Update details:</h2>
		  <form action="updateBookLoans" method="post">
		  
		    <div class="form-group">
			      <label for="bookId"></label>
			      <input type="hidden" class="form-control" name="bookId" >
			    </div>
			   
			     <div class="form-group">
			      <label for="branchId"></label>
			      <input type="hidden" class="form-control" name="branchId">
			    </div>
			    <div class="form-group">
			      <label for="dateOut">Date out</label>
			      <input type="text" class="form-control" name="dateOut" placeholder="Example: yyyy-mm-dd">
			    </div>
			     <div class="form-group">
			      <label for="cardNo"></label>
			      <input type="hidden" class="form-control" name="cardNo">
			    </div>
			    <div class="form-group">
			      <label for="dateIn">Date In:</label>
			      <input type="text" class="form-control" name="dateIn"  placeholder="Example: yyyy-mm-dd">
			    </div>
			    <input type="submit" class="btn btn-success" value="Submit">
		  </form>
</div>
<script>
	$(document).ready(function() {
		$(function() {
			$('#updateBookLoans').on('submit', function(e) {
				e.preventDefault();
				 
				$.ajax({
					url : "updateBookLoans", //this is the submit URL
					type : 'POST', //or POST
					data : $('#updateBookLoans').serialize(),
					success : function(data) {
						if (data.trim() == "success") {
							alert("Book loan was Successfully update.");
						} else {
							alert("There was a problem updating book loan.");

						}
						//$("#myModal1").modal("toggle");
					}
				});
			});
		});

	});
</script>
  





