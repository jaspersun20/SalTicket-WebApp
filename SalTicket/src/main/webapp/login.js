const firstText = document.querySelector("#logusername");
const secondText = document.querySelector("#logpassword");
const signupBtn = document.querySelector("#signup");
const loginBtn = document.querySelector("#log");

var username;
var password;

function getResult(uname, pw){
	fetch("RegisterServlet", {
	      method: "POST",
	      headers: {
	        "Content-Type": "application/x-www-form-urlencoded"
	      },
	      body: "&Name=" + encodeURIComponent(uname) + "&password=" + encodeURIComponent(pw) 
	      
	    })
	      .then((response) => {
	        if (response.ok) {
	          return response.text();
	        } else {
	            throw new Error("Error sending data to the servlet.");
	        }
	       
	      })
	      .then((jsonResponse) => {
	    	  console.log(jsonResponse);
	            // Handle the servlet response here
	            let operationResult = jsonResponse;
	            handleOperationResult(operationResult);
	        })
	      .catch((error) => {
	        console.error("Error:", error);
	      });
	      
	function handleOperationResult(operationResult) {
		    if (operationResult === 0) {
		        alert("Error: Invalid Username or Invalid Password. Try Again.");
		    } else if (operationResult === 1) {
		        setCookie("Name", uname, 365);
	   			window.location.href = "index.html";
		    }
	}
	 
	      
}

signupBtn.addEventListener("click", (event) => {
	const password = document.getElementById("logpassword").value;
	const confirm = document.getElementById("confirmpassword").value;

		// Check if the passwords match
		if (password !== confirm) {
			// Prevent the form from submitting
			event.preventDefault();
			// Display an error message
			alert("Error: Passwords do not match");
			// Reload the page
			location.reload();
		}
		else{
			 setCookie("Name", firstText.value, 365);
    		 setCookie("Password", secondText.value, 365);
		}
});

loginBtn.addEventListener("click", (event) => {
	event.preventDefault();
	const uname = document.getElementById("username").value;
	const password = document.getElementById("password").value;
	getResult(uname, password);
	
    
});



function setCookie(name, value, daysToLive){
    const date = new Date();
    date.setTime(date.getTime() +  (daysToLive * 24 * 60 * 60 * 1000));
    let expires = "expires=" + date.toUTCString();
    document.cookie = `${name}=${value}; ${expires}; path=/`
}

function getCookie(name){
    const cDecoded = decodeURIComponent(document.cookie);
    const cArray = cDecoded.split("; ");
    let result = null;
    
    cArray.forEach(element => {
        if(element.indexOf(name) == 0){
            result = element.substring(name.length + 1)
        }
    })
    return result;
}

  if (window.location.search.includes("emailerror=true")) {
	    alert("Error: This email already exists");
	}