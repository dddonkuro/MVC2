<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="include.html"%>


<div class="container">
       <p style="display:none;"></p>  
       <ul class="pagination">        
  		<li><a href="pageNo" >1</a></li>
	    <li><a href="pageNo">2</a></li>
	    <li><a href="pageNo">3</a></li>
	    <li><a href="pageNo">4</a></li>
	    <li><a href="pageNo">5</a></li>
	    </ul>
</div>

<div class="container">
<div class="row">
	<div class="col-md-6" id = "pageData">
			${result}
	</div></div>
	</div>
<input type="hidden" value ="${pageSize}" id ="pageSize">
<input type="hidden" value ="${link}" id ="link">
<script>
$(document).ready(function(){
	var page =0;
    $("li").click(function(){
    	page = parseInt($(this).children("a").text());
    	url1 : $(this).data("href") page,
    	alert("href: "+url);
    	$.ajax({
			url: url1,
		}).done(function(data) {
			$("#pageData").html(data);
		})
    });
});
</script>

