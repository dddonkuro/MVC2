<?php
	 	
 $emailTo = "daridonkuro@gmail.com";
 $subject = sanitize_input($_POST["fullname"]);
 $phone = sanitize_input($_POST["phone"]);
 $body = sanitize_input($_POST["msg"]);
 $body = $body . $phone;
 $headers="From:".sanitize_input($_POST["email"]);
 
 	if(!filter_var($_POST["email"], FILTER_VALIDATE_EMAIL)){
	 		echo "Invalid email";
		}
	else{
		if (mail($emailTo, $subject, $body, $headers)) {		
		 echo "Mail sent successfully!";		
	 	} else {		
	 	echo "Mail not sent!";		
	 }

	}

function sanitize_input($data){
  $data = trim($data);
  $data = stripslashes($data);
  $data = htmlspecialchars($data);
  return $data;
} 	
	 	
?>