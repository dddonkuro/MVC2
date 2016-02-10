<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.Author"%>
<%@ page import="com.gcit.training.lms.dao.AuthorDAO"%>
<%@ page import="java.util.List"%>
<%@include file="include.html"%>


<div class="container">
		  
		  <form id="author">
		    <div class="form-group">
			      <label for="name">Author Name:</label>
			      <input type="text" class="form-control" name="authorName">
			    </div>
			    <div class="form-group">
			      <input type="hidden" class="form-control" name="id" >
			    </div>
			    <input type="submit" class="btn btn-success" value="Submit">
		  </form>
</div>
<script>
	$(document).ready(function() {
		$(function() {
			$('#author').on('submit', function(e) {
				e.preventDefault();
				 
				$.ajax({
					url : "createAuthor", //this is the submit URL
					type : 'POST', //or POST
					data : $('#author').serialize(),
					success : function(data) {
						if (data.trim() == "success") {
							alert("Book was successfully loaned.");
						} else {
							alert("There was a problem adding book loan.");

						}
						$("#myModal1").modal("toggle");
					}
				});
			});
		});

	});
</script>
  





