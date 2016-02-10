<%@include file="include.html"%>

<div class=" container page-header">
        <h1>Welcome Admin</h1>
        <h2>Pick an option</h2>
    </div>
    <div class="container">
      <div class="row">
      
        <div class="col-md-4">
          <ul class="list-group">
            <li class="list-group-item"><a href='addAuthor'> Add Author</a></li>
            <li class="list-group-item"><a href="authorValues"> View Authors</a></li>
            <li class="list-group-item"><a href="addBook"> Add Book</a></li>
            <li class="list-group-item"><a href="viewBook"> View Books</a></li>
             <li class="list-group-item"><a href="addGenre"> Add Genre</a></li>
            <li class="list-group-item"><a href="viewGenre"> View Genres</a></li>
            <li class="list-group-item"><a href="createPublisher"> Add publisher</a></li>
      		</ul>
            </div>
            <div class ="col-md-4">
            <ul class ="list-group">
            <li class="list-group-item"><a href="viewPublisher"> View Publishers</a></li>
            <li class="list-group-item"><a href="addBranch"> Add Branch</a></li>
            <li class="list-group-item"><a href="viewBranch"> View Branches</a></li>
            <li class="list-group-item"><a href="addBorrower"> Add Borrower</a></li>
            <li class="list-group-item"><a href="viewBorrower"> View Borrowers</a></li>
            <li class="list-group-item"><a href="viewBookLoan"> View Book Loan</a></li>
            <li class="list-group-item"><a href="viewBookCopy"> View Book Copies</a></li>
          </ul>
        </div><!-- /.col-sm-4 -->
    </div>
 </div>
<div id="myModal1" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg">
		<div class="modal-content"></div>
	</div>
</div>