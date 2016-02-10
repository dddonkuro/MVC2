<%@include file="include.html"%>
 	<div class="container">
	 	<div class="row">
	 	${result }
		<form action="addgenre" method="post">
		 <div class = "col-md-6 col-md-offset-3">
		  <label for="genreName" style="font-size:2em;">Genre Name:</label>
            <div class = "input-group">
             <input type = "text" class = "form-control" name="genreName" style="height:30pt;">
               
               <span class = "input-group-btn">
                  <button class = "btn btn-success" type = "submit" style="font-size:12pt; height:30pt;">
                     Add Genre
                  </button>
               </span>
               
            </div><!-- /input-group -->
      </div><!-- /.col-lg-6 -->
	</form>
		
		</div>
	</div>
	<script>
	$(document).ready(function() {
		$(function() {
			$('#addgenre').on('submit', function(e) {
				e.preventDefault();
				 
				$.ajax({
					url : "addgenre", //this is the submit URL
					type : 'POST', //or POST
					data : $('#addgenre').serialize(),
					success : function(data) {
						if (data.trim() == "success") {
							alert("Genre was successfully added.");
						} else {
							alert("There was a problem adding genre.");

						}
						//$("#myModal1").modal("toggle");
					}
				});
			});
		});

	});
</script>
  