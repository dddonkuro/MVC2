<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.*"%>
<%@ page import="com.gcit.training.lms.dao.*"%>
<%@ page import="java.util.List"%>
<%@include file="include.html"%>
<div class="container">
		  <form id="publisher">
		    <div class="form-group">
			      <label for="name">Name:</label>
			      <input type="text" class="form-control" name="name" value='${publisherName}'  placeholder="Enter publisher name">
			    </div>
			    <div class="form-group">
			      <input type="hidden" class="form-control" name="pubId" value='${pubId}'>
			    </div>
			    <div class="form-group">
			      <label for="">Address:</label>
			      <input type="text" class="form-control" name="address" placeholder="Address" value='${address}'>
			    </div>
			    <div class="form-group">
			      <label for="phone">Phone:</label>
			      <input type="text" class="form-control" name="phone" placeholder="phone" value='${phone}'>
			    </div>
			    
			    <input type="submit" class="btn btn-success" value="Submit">
		  </form>
</div>
<script>
	$(document).ready(function() {
		$(function() {
			$('#publisher').on('submit', function(e) {
				e.preventDefault();
				 
				$.ajax({
					url : "edddPublisher", //this is the submit URL
					type : 'POST', //or POST
					data : $('#publisher').serialize(),
					success : function(data) {
						if (data.trim() == "success") {
							alert("Publisher Successfully added.");
						} else {
							alert("There was a problem adding Publisher.");

						}
						$("#myModal1").modal("toggle");
					}
				});
			});
		});

	});
</script>
  





