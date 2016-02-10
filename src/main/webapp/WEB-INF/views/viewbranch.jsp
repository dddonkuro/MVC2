<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="include.html"%>
<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.Branch"%>
<%@ page import="com.gcit.training.lms.dao.*"%>
<%@ page import="java.util.List"%>
<%@page import="javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="container-fluid">
<div class="row">
<form action="searchBranch" method="post">
<div class = "col-md-6 col-md-offset-3">
            <div class = "input-group">
               <input type = "text" class = "form-control" name="searchstring" style="height:30pt;">
               
               <span class = "input-group-btn">
                  <input class = "btn btn-success" type = "submit"  value ="Search" style="font-size:12pt; height:30pt;">
               </span>
               
            </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
</form>
</div>
</div>
<div class="page-header">
	<h1>List of Branches in LMS Application</h1>
	${result }
</div>

<nav>
  <ul class="pagination">
    <li>
      <a href="#" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
     
    </li>
    		
    	
    		<li><a href="pageAuthor?pageNo=1">1</a></li>
    		
  
    <li>
      <a href="#" aria-label="Next" id = "next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>
<script>
	$(document).ready(function() {
		$(function() {
			$('#searchBranch').on('submit', function(e) {
				e.preventDefault();
				 //alert($('#n').val()+", "+$('#p').val()+', '+$('#a').val());
				 
				$.ajax({
					url : 'searchBranch', //this is the submit URL
					type : 'POST', //or POST
					data : $('#searchBranch').serialize(),
					
				});
			});
		});

	});
</script>