<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.*"%>
<%@ page import="com.gcit.training.lms.dao.*"%>
<%@ page import="java.util.List"%>
<%@include file="include.html"%>
  
<div class="container-fluid">
<div class="row">
<form action ="editGenre" method="post">
<div class = "col-md-6 col-md-offset-3">
	<div class="form-group">
      <label  for="genreId"></label>
      <div class="col-md-6 col-md-offset-3">
        <input type="hidden" class="form-control" name="genreId" value='${genreId}'>
      </div>
    </div>
    <label for="genreName">Genre Name:</label>
            <div class = "input-group">
             <input type = "text" class = "form-control" name="genreName" style="height:30pt;">
               
               <span class = "input-group-btn">
                  <input type="submit" class="btn btn-sm btn-success" value="Update" style="height:30pt;">
               </span>
               
            </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
</form>
</div>
</div>

<script>
	$(document).ready(function() {
		$(function() {
			$('#editGenre').on('submit', function(e) {
				e.preventDefault();
				 
				$.ajax({
					url : "editGenre", //this is the submit URL
					type : 'POST', //or POST
					data : $('#editGenre').serialize(),
					success : function(data) {
						if (data.trim() == "success") {
							alert("Genre Successfully updated.");
						} else {
							alert("There was a problem updating Genre.");

						}
						//$("#myModal1").modal("toggle");
					}
				});
			});
		});

	});
</script>
  





