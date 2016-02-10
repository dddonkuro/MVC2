<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="include.html"%>
<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.*"%>
<%@ page import="com.gcit.training.lms.dao.*"%>
<%@ page import="java.util.List"%>
<%@page import="javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<div class="container-fluid">
<div class="row">
<h1>List of Book Loans in LMS Application</h1>
<form action="searchBookLoan" method="post">
<div class = "col-md-6 col-md-offset-3">
            <div class = "input-group">
               <input type = "text" class = "form-control" name="searchstring" style="height:30pt;">
               
               <span class = "input-group-btn">
                  <input class = "btn btn-success" type = "submit" value="Search" style="font-size:12pt; height:30pt;">
                  
               </span>
               
            </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
</form>
</div>
</div>
<div class="page-header">
	${result }
</div>
 ${pagination}
<script>
	$(document).ready(function() {
		$(function() {
			$('#searchBookLoan').on('submit', function(e) {
				e.preventDefault();
				 //alert($('#n').val()+", "+$('#p').val()+', '+$('#a').val());
				 
				$.ajax({
					url : 'searchBookLoan', //this is the submit URL
					type : 'POST', //or POST
					data : $('#searchBookLoan').serialize(),
					
				});
			});
		});
	
	});
</script>