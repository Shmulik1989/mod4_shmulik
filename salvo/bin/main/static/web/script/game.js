//Create the single source of truth.
function DataHandler(){

    //Init function
    this.init = function(){
        //We call createGrid function twice because we want to create two grids, One for the player's ships and one for the Salvoes(hits).
        this.createGrid('grid1');
        this.createGrid('grid2');

    }
    //A function that creates the grid using two nested for loops, One for the raws and one for the columns
    this.createGrid = function(grid) {

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

        $('.container').append(grid);

    };

}

this.getGameData = function(){

    fetch('http://localhost:8080/api/api/game_view/1')

}

//Instantiate the Datahandler.
var dataHandler = new DataHandler();
dataHandler.init();