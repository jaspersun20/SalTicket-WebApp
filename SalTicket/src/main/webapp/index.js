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

document.getElementById("sform").addEventListener("submit", function(event) {
  event.preventDefault(); // Prevent the default form submission behavior

  // Get the input field values using their IDs
  const kword = document.getElementById("kword").value;
  const loc = document.getElementById("location").value;
  const hiddenContent = document.getElementById("hiddentable");
  
  hiddenContent.style.display = "block";

  var url = "https://us-west2-csci201-376723.cloudfunctions.net/explore-events/search?keyword=" + kword + "&city=" + loc;

  fetch(url)
  .then(function(response){
  return response.json();
  })
  .then(function(tours){
	  let placeholder = document.getElementById("dataoutput");
	  let out = "";
	  for (let tour of tours){
	    out += `
	      <tr>
	        <td>${tour.localDate}</td>
	        <td> <img src='${tour.images}' onclick="displayspecified('${tour.eventId}')"> </td>
	        <td>${tour.name}</td>
	        <td>${tour.venue}</td>
	      </tr>
	    `;
  }
  placeholder.innerHTML = out;
});
});

const navold = document.getElementById("navbar");
const navnew = document.getElementById("navbar-logged");

username = getCookie("Name");
if(username){
	navnew.style.display = "block";
	navold.style.display = "none";
}
else{
	navnew.style.display = "none";
	navold.style.display = "block";
}

const logoutBtn = document.querySelector("#logout");
logoutBtn.addEventListener("click", () => {
    deleteCookie("Name");
});


function deleteCookie(name){
    setCookie(name, null, null);
}

function setCookie(name, value, daysToLive){
    const date = new Date();
    date.setTime(date.getTime() +  (daysToLive * 24 * 60 * 60 * 1000));
    let expires = "expires=" + date.toUTCString();
    document.cookie = `${name}=${value}; ${expires}; path=/`
}


