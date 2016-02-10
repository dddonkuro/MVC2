<%@include file="include.html"%>
 	<div class="container">
	 	<div class="row">
	 	${result }
		<form action="addBookCopy" method="post">
		 <div class = "col-md-6 col-md-offset-3">
		  <label for="searchString" style="font-size:2em;">Book Copy:</label>
            <div class = "input-group">
             <input type = "text" class = "form-control" name="searchstring" style="height:30pt;">
               
               <span class = "input-group-btn">
                  <button class = "btn btn-success" type = "submit" style="font-size:12pt; height:30pt;">
                     Add Book Copy
                  </button>
               </span>
               
            </div><!-- /input-group -->
      </div><!-- /.col-lg-6 -->
	</form>
		
		</div>
	</div>