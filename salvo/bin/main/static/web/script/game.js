//Create the single source of truth.
function DataHandler(){

    //Init function to start the chain of event
    this.init = function(){
        //We call createGrid function twice because we want to create two grids, One for the player's ships and one for the Salvoes(hits).
        this.createGrid('#ships', 'grid1');
        this.createGrid('#salvoes', 'grid2');
        this.getGameData();

    }
    //A function that creates the grid using two nested for loops, One for the raws and one for the columns
    this.createGrid = function(container, grid) {

        var grid = $('<div>').addClass("grid");

        for(var row = 0; row <= 10; row++){
            for(var col = 0; col <= 10; col++){
                var letter = String.fromCharCode(64+col);
                var cellcode = letter + row;

                var cell = $('<div>').addClass("cell").addClass(cellcode);

                if( row == 0 && col != 0 ){
                    cell.text(letter)
                }
                if( col == 0 && row != 0 ){
                   cell.text(row)
                }

                cell.appendTo(grid);

            }
        }

        $(container).append(grid);

    };

    //Get the current player data according to the player id using the getParamterByName method.
    this.getGameData = function(){
        
            var that = this;
        
            $.getJSON('http://localhost:8080/api/api/game_view/' + this.getParameterByName('gp'), function(response){

                that.gamePlayerData = response;
        
                console.log(that.gamePlayerData);
                
        });    
        
    }

    //
    this.getParameterByName = function(name, url) {
        if (!url) url = window.location.href;
        name = name.replace(/[\[\]]/g, "\\$&");
        var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, " "));
    }


}

//Instantiate the Datahandler.
var dataHandler = new DataHandler();
dataHandler.init();