<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.*"%>
<%@ page import="com.gcit.training.lms.dao.*"%>
<%@ page import="java.util.List"%>
<%@include file="include.html"%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<div class="container">
		  <form action="editBook" method="post">
		    <div class="form-group">
		    	<div class="form-group">
			      <label for="bookId"></label>
			      <input type="hidden" class="form-control" name="bookId" value='${bookId}'>
			    </div>
			     <div class="form-group">
			      <label for="title">Book title</label>
			      <input type="text" class="form-control" name="title" placeholder ="Enter book title">
			    </div>
			      <label for="pubId">Publisher Id</label>
			      <input type="text" class="form-control" name="pubId"   placeholder="Enter publisher Id" required>
			    </div>
			    
			    <input type="submit" class="btn btn-success" value=" Update">
		  </form>
</div>
<script>
	$(document).ready(function() {
		$(function() {
			$('#editBook').on('submit', function(e) {
				e.preventDefault();
				$.ajax({
					url : "editBook", //this is the submit URL
					type : 'POST', //or POST
					data : $('#editBook').serialize(),
					success : function(data) {
						if (data.trim() == "success") {
							$("#SuccessEdit").show();
							alert("Book Successfully updated.");
							$('#SuccessEdit').delay(5000).fadeOut('slow');
						} else {
							$("#ErrorEdit").show();
							alert("There was a problem updating book.");
							$('#ErrorEdit').delay(5000).fadeOut('slow');
						}
						
					}
				});
			});
		});

	});
</script>
