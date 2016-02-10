<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.Borrower"%>
<%@ page import="com.gcit.training.lms.dao.BorrowerDAO"%>
<%@ page import="java.util.List"%>
<%@include file="include.html"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<div class="container">
		  <form id="createborrower">
		    <div class="form-group">
			      <label for="name">Name:</label>
			      <input type="text" class="form-control" name="name"  id="n" placeholder="Enter borrower name">
			    </div>
			    <div class="form-group">
			      <label for="">Address:</label>
			      <input type="text" class="form-control" name="address" id="a" placeholder="Address">
			    </div>
			    <div class="form-group">
			      <label for="phone">Phone:</label>
			      <input type="text" class="form-control" name="phone" id="p" placeholder="phone">
			    </div>
			    
			    <input type="submit" class="btn btn-success" value="Submit">
		  </form>
</div>
	
<script>
	$(document).ready(function() {
		$(function() {
			$('#createborrower').on('submit', function(e) {
				e.preventDefault();
				 //alert($('#n').val()+", "+$('#p').val()+', '+$('#a').val());
				 
				$.ajax({
					url : 'createBorrower', //this is the submit URL
					type : 'POST', //or POST
					data : $('#createborrower').serialize(),
					
				});
			});
		});

	});
</script>
  