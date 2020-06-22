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
		$.get(href,{},function(tempEmployee,status){
			$('.myForm #first-name').val(tempEmployee.firstName);
			$('.myForm #last-name').val(tempEmployee.lastName);
			$('.myForm #email').val(tempEmployee.email);
		});
		$('.myForm #exampleModal').modal();
	})
});