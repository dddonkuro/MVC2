<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.Book"%>
<%@ page import="com.gcit.training.lms.dao.*"%>
<%@ page import="java.util.List"%>
<%@include file="include.html"%>


<div class="container">
		  <form id="createbook">
		    <div class="form-group">
			      <label for="name">Book Title</label>
			      <input type="text" class="form-control" name="title"   placeholder="Enter book title" required>
			    </div>
			     <div class="form-group">
			      <label for="bookId">Book Id</label>
			      <input type="text" class="form-control" name="bookId"   placeholder="Enter book Id" required>
			    </div>
			    <div class="form-group">
			      <label for="pubId">Publisher Id:</label>
			      <input type="text" class="form-control" name="pubId"  placeholder="Publisher Id" required>
			    </div>
			    
			    <input type="submit" class="btn btn-success" value="Submit">
		  </form>
</div>
	
<script>
	$(document).ready(function() {
		$(function() {
			$('#createbook').on('submit', function(e) {
				e.preventDefault();
				$.ajax({
					url : "addBook", //this is the submit URL
					type : 'POST', //or POST
					data : $('#createbook').serialize(),
					success : function(data) {
						if (data.trim() == "success") {
							alert("Book was Successfully added.");
						} else {
							alert("There was a problem adding book.");

						}
						//$("#myModal1").modal("toggle");
					}
				});
			});
		});

	});
</script>