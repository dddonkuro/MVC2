<%@include file="include.html"%>
	<div class="container">
		<h2>Branch details:</h2><br>
		  <form action="createBookLoan" method="post">
		    <div class="form-group">
		    	<div class="form-group">
			      <label for="bookId"></label>
			      <input type="hidden" class="form-control" name="bookId" value='${bookId}'>
			    </div>
			     <div class="form-group">
			      <label for="branchId"></label>
			      <input type="hidden" class="form-control" name="branchId" value='${branchId}'>
			    </div>
			      <label for="cardNo">Card Number</label>
			      <input type="text" class="form-control" name="cardNo"   placeholder="Enter card number" required>
			    </div>
			    
			    <input type="submit" class="btn btn-success" value=" Check Out">
		  </form>
</div>
<script>
	$(document).ready(function() {
		$(function() {
			$('#createBookLoan').on('submit', function(e) {
				e.preventDefault();
				 //alert($('#n').val()+", "+$('#p').val()+', '+$('#a').val());
				 
				$.ajax({
					url : 'createBookLoan', //this is the submit URL
					type : 'POST', //or POST
					data : $('#createBookLoan').serialize(),
					
				});
			});
		});

	});
</script>