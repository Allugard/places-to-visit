function showHints(block){
	var cover = document.getElementById(block);
	cover.style.backgroundColor = "rgba(0,0,0,.5)"; 
	cover.style.visibility = "visible";
}

function hideHints(block){
	var cover = document.getElementById(block);
	cover.style.visibility = "hidden";
}