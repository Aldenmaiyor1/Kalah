package kalah.controller;

import kalah.model.Player;
import kalah.view.ShowUI;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> playerList;
    private ShowUI showUI;
    private int currentPlayer;
    private Input input;


    public Game(Player player1, Player player2, ShowUI showUI, Input input){
        this.currentPlayer = 0;
        this.input = input;
        this.showUI = showUI;

        this.playerList = new ArrayList<>();
        this.playerList.add(player1);
        this.playerList.add(player2);

    }

    //testing code to set state of each players house
    public void setPlayerSeeds(Player player, int a, int b, int c, int d, int e, int f ){
        player.getHouseList().get(0).setSeeds(a);
        player.getHouseList().get(1).setSeeds(b);
        player.getHouseList().get(2).setSeeds(c);
        player.getHouseList().get(3).setSeeds(d);
        player.getHouseList().get(4).setSeeds(e);
        player.getHouseList().get(5).setSeeds(f);
    }

    //main method of the game, call to run game.
    public void play(){
        int housePicked = 0;
        int seedsPickedUp = 0;

        //while loop check each players turn if is valid (houses still have seeds), if not then ends game
        while(!GameLogic.checkGameEnd(playerList.get(currentPlayer))){
            this.showUI.showBoard(playerList.get(0), playerList.get(1));
            housePicked = this.input.getPlayerInput(currentPlayer);

            //if player inputs "q" game will quit
            if(housePicked == -10){
                showUI.gameOver();
                showUI.showBoard(playerList.get(0), playerList.get(1));
                return;
            }

            // if player inputs an empty house, will show empty house message
            if(this.playerList.get(currentPlayer).getHouseList().get(housePicked-1).getSeeds() == 0){
                showUI.emptyHouseMessage();
                continue;
            }

            //picks up seeds and distributes them
            seedsPickedUp = this.playerList.get(currentPlayer).pickupSeed(housePicked);
            GameLogic.distributeSeeds(
                    playerList.get(currentPlayer),
                    playerList.get((currentPlayer + 1)%2),
                    housePicked,
                    seedsPickedUp
                    );

            //checks if current players turn is over by seeing if the seed did not landed in the players store
            if (!GameLogic.checkTurnSwitch(housePicked, seedsPickedUp)) {
                currentPlayer = (currentPlayer + 1) % 2;
            }
        }

        //ending sequence, displays board and final scores
        showUI.showBoard(playerList.get(0), playerList.get(1));
        showUI.gameOver();
        showUI.showBoard(playerList.get(0), playerList.get(1));
        showUI.gameFinished(playerList.get(0), playerList.get(1));

    }
}
