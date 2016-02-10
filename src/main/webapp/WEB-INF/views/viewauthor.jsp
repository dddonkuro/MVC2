<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="include.html"%>
<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.Author"%>
<%@ page import="com.gcit.training.lms.dao.AuthorDAO"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="page-header">
	<h1>List of Authors in LMS Application</h1>
	
</div>
<div class ="container">
<div class ="row">
<form action="searchAuthor" method="post">
	<div class="col-md-6 col-md-offset-3">
	<input type=hidden name="authorId" value=${authorId } />
	
	<div class = "input-group">
               <input type="text" class="form-control" value='${searchResult}' placeholder="Username"  name="searchString" style="font-size:12pt; height:30pt;">
               <span class = "input-group-btn">
                  <input class = "btn btn-success" type = "submit" style="font-size:12pt; height:30pt;" value="Search">
               </span>
               
         </div><!-- /input-group -->
	</div>
</form>
</div>
</div>
<div class="container">
<div class="row">
	<div class="col-md-6" id = "pageData">
			${result}
	</div>
	${pagination}
	</div>
</div>
	
<script>
	$(document).ready(function()
	{
		$(function() 
				{
			$('#searchAuthor').on('submit', function(e) 
					{
				e.preventDefault();
				$.ajax({
						url : "searchAuthor", //this is the submit URL
						type : 'POST', //or POST
						data : $('#searchAuthor').serialize(),
						success : function(data) {
						if (data.trim() == "success") {
							alert("Did you mean this Author?");
						}else
							alert("no Author found");
					});
				});
			});
		});
	});
</script>