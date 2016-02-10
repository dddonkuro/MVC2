<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.Book"%>
<%@ page import="com.gcit.training.lms.dao.*"%>
<%@ page import="java.util.List"%>
<%@include file="include.html"%>

<!-- This form has been modified to add author and publisher information, the co -->
<div class="container">
	<div class="row">
			<h2>Book Details</h2>
		  <form id="createbook">
		    <div class="form-group" >
			   
			      <input type="text" class="form-control" name="title"   placeholder="Enter book title" required>
			    </div>
			   
			    <div class="form-group " >
			     	 <div class="form-group">
				      <label for="comment">Author(s):</label>
				      <textarea class="form-control" rows="5" class="form-control" name="authorName" placeholder="Enter Author names separated by commas, eg, John Smith, Ellen Davis " required></textarea>
				    </div>
	
			      <h2>Publisher Information</h2>
			    </div>
			     <div class="form-group publisher" >
			   	<input type="text" class="form-control" name="publisherName"   placeholder="Enter Publisher name" required>
			    </div>
			    
			     <div class="form-group publisherAddress" >
			   	<input type="text" class="form-control" name="publisherAddress"   placeholder="Enter Publisher address" required>
			    </div>
			    <div class="form-group publisherPhone" >
			   	<input type="text" class="form-control" name="publisherPhone"   placeholder="Enter Publisher phone" required>
			    </div>
			     <h2>Library Branch Information</h2>
			     <div class="form-group branch" >
			   	<input type="text" class="form-control" name="branchName"   placeholder="Enter library branch name" required>
			    </div>
			    <div class="form-group branchAddress" >
			   	<input type="text" class="form-control" name="branchAddress"   placeholder="Enter branch address" required>
			    </div>
			    <input type="submit" class="btn btn-success" value="Submit">
		  </form>
		
		  </div>
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