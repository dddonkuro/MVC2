<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.*"%>
<%@ page import="com.gcit.training.lms.dao.*"%>
<%@ page import="java.util.List"%>
<%@include file="include.html"%>

<div class="container">
		  <form action="updatePublisher" method="post">
		    <div class="form-group">
			      <label for="name">Publisher Name:</label>
			      <input type="text" class="form-control" name="name" value='${name}'  placeholder="Enter publisher name">
			    </div>
			    <div class="form-group">
			      <input type="hidden" class="form-control" name="publisherId" value='${publisherId}'>
			    </div>
			    <div class="form-group">
			      <label for="address">Publisher Address:</label>
			      <input type="text" class="form-control" name="address" placeholder="Address" value='${address}'>
			    </div>
			    <div class="form-group">
			      <label for="phone">Publisher Phone:</label>
			      <input type="text" class="form-control" name="phone" placeholder="phone" value='${phone}'>
			    </div>
			    
			    <input type="submit" class="btn btn-success" value="update">
		  </form>
</div>
<script>
	$(document).ready(function() {
		$(function() {
			$('#updatePublisher').on('submit', function(e) {
				e.preventDefault();
				 
				$.ajax({
					url : "updatePublisher", //this is the submit URL
					type : 'POST', //or POST
					data : $('#updatePublisher').serialize(),
					success : function(data) {
						if (data.trim() == "success") {
							alert("Publisher Successfully update.");
						} else {
							alert("There was a problem updating Publisher.");

						}
						$("#myModal1").modal("toggle");
					}
				});
			});
		});

	});
</script>
  





