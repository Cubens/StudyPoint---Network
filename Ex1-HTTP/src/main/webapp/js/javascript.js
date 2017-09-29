function tryURL(url){
    fetch(url,{method: "get"})
    .then(function (response){
        if(!response.ok)
        {}
    }).then(function (json){
        alert("GET OK - "+url);
    }).catch (function(json) {
        alert("GET FAILED - "+url);  
    });

}