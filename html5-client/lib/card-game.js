var services = [];
services.push("http://locahost:8080/");

var service = "";
var user = "";
var selectedCards = [];
$(function () {
  $("#debug-toggle").button().on("click", function () { $("#debug-console").toggle("fade"); }); $(".btn").button();
  $("#tables").dialog({ closeOnEscape: false, modal: true, width: "auto", resize: "auto", open: function (event, ui) { $(".ui-dialog-titlebar-close", ui.dialog | ui).hide(); } });
  $("#connect").button().on("click", function () {
    $(".ui-dialog-titlebar-close").click();
    service = $("#servUrl").val();
    user = $("#userName").val()
    getInterface();
  });
  $("#servUrl").autocomplete({ source: services });



  //debug
  $(document).ajaxComplete(function (event, request, settings) {
    /*
    var dd = $("#debug-console").append("<p");
    dd.append('<div class="debug-event">' + JSON.stringify(event) + '</div>');
    dd.append('<div class="debug-request">' + JSON.stringify(request) + '</div>');
    dd.append('<div class="debug-settings">' + JSON.stringify(settings) + '</div>');
    console.log(settings);
*/

    var d = $("#debug-console").append("<p>")
    d.append('<span class="debug-event">'
      + (settings.type)
      + (" : " + settings.url)
      + '</span>');
    d.append("<br>")
    d.append(settings.data || "-- No request body --");
    d.append("<br>")
    d.append('<span class="debug-request">'
      + (" readyState:" + request.readyState)
      + (" status:" + request.status)
      + (" description:" + request.statusText)
      + '</span>');
    d.append("<br>")
    d.append(request.responseText || "-- No response body--");

  });

});

function getInterface() {
  $.ajax({
    url: service + user,
    method: "GET",
    crossDomain: true,
  }).done(function (data) {
    if (typeof data === "string") {
      interface(JSON.parse(data));
    } else {
      interface(data);
    }
  });
}

function interface(data) {
  selectedCards = [];
  $('#dealerHand').empty();
  $('#myHand').empty();
  $('#options').empty();
  data.dealerHand.forEach(function (element) {
    $('#dealerHand').append(createCard(element));
  });
  data.myHand.forEach(function (element) {
    $('#myHand').append(createCard(element));
  });
  data.options.forEach(function (element) {
    var button = $('<input type="button" value="' + element + '"/>');
    button.button().on("click", function () {
      $("#options").empty();
      $.ajax({
        url: service + (service.endsWith("/") ? "" : "/") + user + "/" + element,
        method: "POST",
        data: JSON.stringify(selectedCards),
        processData: false,
        crossDomain: true,
      }).done(function () {
        getInterface();
      }).error(function () {
        getInterface();
      });
    });
    button.hide();
    $("#options").append(button);
    button.show("highlight")
  });
  if (data.message) {
    $("<h1>" + data.message + "</h1>").dialog({ modal: true });
  }
  $("#gameTitle").html(data.title);
};

function createCard(element) {
  var img = $('<img>');
  img.attr('class', 'card');
  var card = element.toLowerCase();
  if (card === "??") {
    img.attr('src', 'cards/back.png');
    return img;
  }
  if (card === "rj") {
    img.attr("src", 'cards/red_joker.png');
    return img;
  }
  if (card === "bj") {
    img.attr("src", 'cards/black_joker.png');
    return img;
  }
  var src = 'cards/';
  switch (card.charAt(0)) {
    case 'a':
      src += "ace";
      break;
    case 'j':
      src += "jack";
      break;
    case 'k':
      src += "king";
      break;
    case 'q':
      src += "queen";
      break;
    case 'x':
      src += "10";
      break;
    default:
      src += card.charAt(0);
  }
  src += '_of_';
  switch (card.charAt(1)) {
    case 'c':
      src += "clubs"
      break;
    case 'h':
      src += "hearts"
      break;
    case 's':
      src += "spades"
      break;
    case 'd':
      src += "diamonds"
      break;
  }
  src += '.png';
  img.attr('src', src);
  return img;
}
