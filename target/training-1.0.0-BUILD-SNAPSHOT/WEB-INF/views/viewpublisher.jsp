<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="include.html"%>
<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.Publisher"%>
<%@ page import="com.gcit.training.lms.dao.*"%>
<%@ page import="java.util.List"%>
<%@page import="javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="container">
	<h1>List of Publishers in LMS Application</h1>
	
	<div class="row">
<form action="searchPublisher" method="post">
<div class = "col-md-6 col-md-offset-3">
            <div class = "input-group">
               <input type = "text" class = "form-control" name="searchString" style="height:30pt;">
               
               <span class = "input-group-btn">
                  <input class = "btn btn-success" type = "submit" style="font-size:12pt; height:30pt;" valuee="Send">
                     
               </span>
               
            </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
</form>
</div>
</div>
<div class="container-fluid">
<div class="row">
<div class = "col-md-6 col-md-offset-3">
  	    ${result}
  </div>
</div>

</div>

