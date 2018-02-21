 //execute code when DOM is ready.
$(document).ready(function(){
            var that = this;
        //send a AJAX request to the games database(/api/games).
        $.getJSON('http://localhost:8080/api/games', function(data) {
            that.gamesInfo = data;
            //Run a each loop over the retrieved JSON data.
            $.each(data, function(i, e){
            //Convert the date in millisecond to a readable string.
            var date = new Date(e.created).toLocaleString();
            //Append the retrieved JSON data to the OL element in the HTML.
            $('#gamesList').append($('<li>').text('Time' + ' ' + date + ' ' + 'Player1:' + ' ' + e.gamePlayers[0].player.email + ' ' + 'VS' + ' ' + 'Player2:' + ' ' + e.gamePlayers[1].player.email))
    
                });
    
            });
    
        });