<%@include file="include.html"%>

<div class=" container page-header">
        <h1>Borrower</h1>
        <h2>Pick an option</h2>
    </div>
    <div class="container">
      <div class="row">
      
        <div class="col-md-6 col-md-offset">
          <ul class="list-group">
            <li class="list-group-item"><a href="borrowerView"> View Books</a></li>
             <li class="list-group-item"><a href="viewMyBook?cardNo=38"> My Books</a></li>
          </ul>
        </div><!-- /.col-sm-4 -->
    </div>
 </div>
<div class="container">
	<div class="row">
		<div class="col-md-8 col-md-offset-2">
				${books}
				<input type="hidden" name="cardNo" value="38">
		</div>
	</div>
</div>