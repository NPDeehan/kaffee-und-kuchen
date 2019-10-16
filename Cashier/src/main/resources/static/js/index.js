$( "#form" ).submit(function( event ) {

  event.preventDefault();
    var data = { orderName: $("#Name").val(), orderMessage: $("#Order").val() };
  $.ajax({
    method: "PUT",
    url: "http://localhost:8080/orderUp/",
    data: data
  })
    .done(function( msg ) {
      alert( "Data Saved: " + msg );
    });
});