<%@include file="include.html"%>
 	<div class="container">
		  <form action="createBranch" method="post">
		    <div class="form-group">
			      <label for="branchName">Branch Name</label>
			      <input type="text" class="form-control" name="branchName"   placeholder="Enter branch name" required>
			    </div>
			     
			    <div class="form-group">
			      <label for="pubId">Branch address:</label>
			      <input type="text" class="form-control" name="branchAddress"  placeholder="Address" required>
			    </div>
			    
			    <input type="submit" class="btn btn-success" value="Submit">
		  </form>
</div>
	<script>
	$(document).ready(function() {
		$(function() {
			$('#createBranch').on('submit', function(e) {
				e.preventDefault();
				$.ajax({
					url : "createBranch", //this is the submit URL
					type : 'POST', //or POST
					data : $('#createBranch').serialize(),
					success : function(data) {
						if (data.trim() == "success") {
							alert("Branch was Successfully added.");
						} else {
							alert("There was a problem adding branch.");

						}
						//$("#myModal1").modal("toggle");
					}
				});
			});
		});

	});
</script>