$(document).ready(function(){
var $day,$month,$year,notes;
	$( ".ui-widget" ).click(function() {
		
		$day = $('a.ui-state-default.ui-state-active').text();
		$month= $('span.ui-datepicker-month').text();
		$year= $('span.ui-datepicker-year').text();

		document.getElementById('date-field').value = $day+" "+$month+" "+$year;
		console.log($day+" "+$month+" "+$year);

	});
	$('#autocomplete').on('keydown', function(e) {
	    if (e.which == 13) {
	        $("#planner").submit(function(e){
			    e.preventDefault();
			});
	    }
	});
	$("form#planner").change(function() {
	  	notes = document.getElementById('autocomplete').value + " ";
		notes += document.getElementById('date-field').value + " ";
		notes += document.getElementById('textarea-note').value;
		console.log(notes);
		$( "button#submit" ).click(function() {
			$( "#display-text").val(notes);
		});
	});
});