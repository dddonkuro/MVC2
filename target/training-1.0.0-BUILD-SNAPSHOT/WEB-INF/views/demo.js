$(document).ready(function(){
   $(".msg").on('blur',validate);
   $(".phone").on('blur',phone);
   $(".email").on('blur',email);
   $(".fullname").on('blur',nameValidator);
   	$(".footer").css("min-width",$(window).width);
   	
	   $('form').bind('submit',function(event)
	   {
	   		
	   		event.preventDefault();
	   		$.ajax({
	   			type:'post',
	   			url: 'contact.php',
	   			data: $('form').serialize(),
	   			success: function(){
	   				$(location).attr('href', '../index.html');
	   				
	   			}
	   		});
	   	});
	$('.get_started').on('click',function(){
		$(location).attr('href','../pages/contact.html');
	});

	$('.request').on('click',function(){
		$(location).attr('href','../pages/contact.html');
	});
});

function validate(){
	//var msgerr =
   if($('.msg').val().trim().length < 20){
  		$('.msg').css({'border':'1px solid #ff5632',
  			'background-image':'url(../images/invalid.PNG)',
  			'background-repeat':'no-repeat',
  			'background-position':'top right'
  		});
  		$('.msg').after('<div class="msgerr" style="color:red;">The message field must be at least twenty characters long. </div>');
    }
	else{
  		$('.msg').css({'border':'1px solid #00ff00',
  			'background-image':'url(../images/valid.PNG)',
  			'background-repeat':'no-repeat',
  			'background-position':'top right'
  		});
  		$('.msgerr').hide();
  	}  
}

function nameValidator(){
   if($('.fullname').val().trim().length < 5){
  		$('.fullname').css({'border':'1px solid #ff5632',
  			'background-image':'url(../images/invalid.PNG)',
  			'background-repeat':'no-repeat',
  			'background-position':'top right'
  		});
  		$('.fullname').after('<div class="nameErr" style="color:red;">The name field must be at least five characters long.</div>');
    }
	else{
  		$('.fullname').css({'border':'1px solid #00ff00',
  			'background-image':'url(../images/valid.PNG)',
  			'background-repeat':'no-repeat',
  			'background-position':'top right'
  		});
  		$('.nameErr').hide();
  	}  
}
function phone(){
	var re =/^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
	if(!(re.test($(this).val()))){
		$('.phone').css({'border':'1px solid #ff5632',
  			'background-image':'url(../images/invalid.PNG)',
  			'background-repeat':'no-repeat',
  			'background-position':'top right'
  		});
  		$('.phone').after('<div class="phoneErr"style="color:red;">Please enter a valid phone number.</div>');
	}
	else{
		$('.phone').css({'border':'1px solid #00ff00',
  			'background-image':'url(../images/valid.PNG)',
  			'background-repeat':'no-repeat',
  			'background-position':'top right'
  		});
  		$('.phoneErr').hide();
	}
}

function email(){
	var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{3}))$/;
	if(!(re.test($(this).val()))){
		$('.email').css({'border':'1px solid #ff5632',
  			'background-image':'url(../images/invalid.PNG)',
  			'background-repeat':'no-repeat',
  			'background-position':'top right'
  		});
  		$('.email').after('<div class="emailErr" style="color:red;">Please enter a valid e-mail address.</div>');
	}
	else{
		$('.email').css({'border':'1px solid #00ff00',
  			'background-image':'url(../images/valid.PNG)',
  			'background-repeat':'no-repeat',
  			'background-position':'top right'
  		});
  		$('.emailErr').hide();
	}
}
