
<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.Author"%>
<%@ page import="com.gcit.training.lms.dao.AuthorDAO"%>
<%@ page import="java.util.List"%>
<%@include file="include.html"%>
<div class="container">
		  
		  <form id="updateAuthor">
		    <div class="form-group">
			      <input type="hidden" class="form-control" name="authorId" value='${authorId}' >
			    </div>
			    <div class="form-group">
			      <label for="authorName">Author name:</label>
			      <input type="text" class="form-control" name="authorName"  placeholder="Author name">
			    </div>
			    <input type="submit" class="btn btn-success" value="Update">
		  </form>
</div>
<script>
	$(document).ready(function() {
		$(function() {
			$('#updateAuthor').on('submit', function(e) {
				e.preventDefault();
				$.ajax({
					url : "editAuthor", //this is the submit URL
					type : 'POST', //or POST
					data : $('#updateAuthor').serialize(),
					success : function(data) {
						if (data.trim() == "success") {
							$("#SuccessEdit").show();
							alert("Author Successfully edited.");
							$('#SuccessEdit').delay(5000).fadeOut('slow');
						} else {
							$("#ErrorEdit").show();
							alert("There was a problem updating author.");
							$('#ErrorEdit').delay(5000).fadeOut('slow');
						}
						
					}
				});
			});
		});

	});
</script>