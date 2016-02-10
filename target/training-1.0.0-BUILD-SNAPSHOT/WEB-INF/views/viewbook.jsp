<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="include.html"%>
<%@ page import="com.gcit.training.lms.service.*"%>
<%@ page import="com.gcit.training.lms.service.*"%>
<%@ page import="com.gcit.training.lms.entity.*"%>
<%@ page import="com.gcit.training.lms.dao.*"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="container">
<div class="row">
	<div class="col-md-6 col-md-offset-3">
		<div class="alert alert-danger" role="alert" id="#ErrorEdit" style="display:none;">
		  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
		  <span class="sr-only">Error:</span>
		  Updation Failed
		</div>
	</div>
</div>
<div class="col-md-6 col-md-offset-3">
	<div class="alert alert-success" role="alert" id="#SuccessEdit" style="display:none;">
	  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
	  <span class="sr-only">Error:</span>
	  Book Successfully Updated.
	</div>
</div>
</div>

<div class ="container">
<div class= "row">
<div class="page-header">
	<h1>List of Books in LMS Application</h1>
	
</div>

<div class="row">
<div class = "col-md-6 col-md-offset-6">
  	<ul class="pagination">
		  ${pages}
</ul>     
  </div>
</div>

<form action="searchBook" method="get">
<div class="input-group">
  <span class="input-group-addon" id="basic-addon1">Search</span>
  <input type="text" class="form-control" value='${searchResult}' placeholder="Username" aria-describedby="basic-addon1" name="searchString" >
</div>
<input type="submit" class="btn btn-sm btn-success" value="Search">
</form>

<div class="row">
	<div class="col-md-6" id = "pageData">
		${books}
	</div>
</div>

<div id="myModal1" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg">
		<div class="modal-content"></div>
	</div>
</div>
</div>
</div>
<script>
	$(document).ready(function() {
		$(function() {
			$('#searchBook').on('submit', function(e) {
				e.preventDefault();
				$.ajax({
					url : "searchBook", //this is the submit URL
					type : 'POST', //or POST
					data : $('#searchBook').serialize()
					
			});
			});		
		});

	});
		
</script>
