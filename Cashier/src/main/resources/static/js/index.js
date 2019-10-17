$( "#form" ).submit(function( event ) {

  event.preventDefault();
    var data = { orderName: $("#Name").val(), orderMessage: $("#Order").val() };
  $.ajax({
    method: "POST",
    contentType:"application/json; charset=utf-8",
    dataType:"json",
    url: "/localOrderUp",
    data: JSON.stringify(data)
  })
    .done(function( msg ) {
    		$('.response').append(msg);
      
    }).fail(function( jqXHR, textStatus ) {
    	  $('.response').append('<p>'+jqXHR.responseText+'</p>');
    });
});