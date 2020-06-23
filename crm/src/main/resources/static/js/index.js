/**
 * 
 */

$().ready(function(){
	"use strict";
	$('.addBtn, .table .eBtn').click(function(event){
		console.log("Inside JQuery");
		event.preventDefault();
		var href = $(this).attr('href');
		var text = $(this).text();
		if(text != "Add Employee"){
			$.get(href,{},function(tempEmployee,status){
				console.log(tempEmployee.firstName);
				$('.myForm #id').val(tempEmployee.id);
				$('.myForm #first-name').val(tempEmployee.firstName);
				$('.myForm #last-name').val(tempEmployee.lastName);
				$('.myForm #email').val(tempEmployee.email);
			});
			$('.myForm #exampleModal').modal();
		}
		else{
				$('.myForm #id').val(0);
				$('.myForm #first-name').val('');
				$('.myForm #last-name').val('');
				$('.myForm #email').val('');
				$('.myForm #exampleModal').modal();
		}
	})
});