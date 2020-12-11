
$('#autocompleteAdresse').on('input', function() {
    var availableAddresses = [];
    var target = document.querySelector("#result");
    var template = "<div><label class=\"bo bp\"><input type=\"checkbox\" name=\"addees[~id~]\" value=\"~id~\">Test ~id~</label></div>";
    var baseLocation = window.location.href;
    var adresse = document.getElementById("autocompleteAdresse").value;
    var url = encodeURI(baseLocation +'/adresse/search?adresse=' + adresse);

    $.ajax({
       url: url,
       data: {
          format: 'json'
       },
       headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
        },
        type: "GET",
       error: function() {
          $('#info').html('<p>An error has occurred</p>');
       },
       success: function(data) {
           //console.log(data);
           availableAddresses = [];
           for(var i in data)
               availableAddresses.push(data[i].join(" "));
           target.innerHTML = '';

           availableAddresses.forEach(function(item) {
             target.insertAdjacentHTML("beforeend", template.replace(/~id~/g, item));
           });
       }
    });
 });