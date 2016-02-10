<%@include file="include.html"%>
	
	<div class="container">
	<h2>Publisher Details:</h2>
		  <form action="addPublisher" method="post">
		    <div class="form-group">
			      <label for="publisherName">Publisher Name:</label>
			      <input type="text" class="form-control" name="publisherName"   placeholder="Enter Publisher Name" required>
			    </div>
			     <div class="form-group">
			      <label for="publisherAddr">Publisher Address</label>
			      <input type="text" class="form-control" name="publisherAddr"   placeholder="Enter Publisher address" required>
			    </div>
			    <div class="form-group">
			      <label for="publisherPhone">Publisher Phone number</label>
			      <input type="text" class="form-control" name="publisherPhone"   placeholder="Enter Publisher phone" required>
			    </div>
			    <input type="submit" class="btn btn-success" value="Send">
		  </form>
</div>

<script>
	$(document).ready(function() {
		$(function() {
			$('#addPublisher').on('submit', function(e) {
				e.preventDefault();

				$.ajax({
					url : 'addPublisher', //this is the submit URL
					type : 'POST', //or POST
					data : $('#addPublisher').serialize(),
					
				});
			});
		});

	});
</script>