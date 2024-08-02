package kalah.view;

import com.qualitascorpus.testsupport.IO;
import kalah.model.Player;

public class ShowUI {

    public IO io;
    public ShowUI(IO io){
        this.io = io;
    }

    //Shows Board UI
    public void showBoard(Player player1, Player player2){
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
        io.print("| P2 |");
        for (int i = player1.getHouseList().size() - 1; i >= 0; i--) {
            io.print(String.format(" %d[%2d] |",
                    i+1,
                    player2.getHouseList().get(i).getSeeds()));
        }
        io.print(String.format(" %2d |", player1.getPlayerSeedStore().getSeeds()));
        io.println("");
        io.println("|    |-------+-------+-------+-------+-------+-------|    |");
        io.print(String.format("| %2d |", player2.getPlayerSeedStore().getSeeds()));
        for (int i = 0; i < player2.getHouseList().size(); i++) {
            io.print(String.format(" %d[%2d] |",
                    i+1,
                    player1.getHouseList().get(i).getSeeds()));
        }
        io.print(" P1 |");
        io.println("");
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
    }

    // shows gameover screen
    public void gameOver(){
        io.println("Game over");
    }

    //shows game finsihed screen
    public void gameFinished(Player player1, Player player2){
        int playerOneScore = player1.getTotalSeeds();
        int playerTwoScore = player2.getTotalSeeds();
        io.println(String.format("\tplayer 1:%d", playerOneScore));
        io.println(String.format("\tplayer 2:%d", playerTwoScore));
        if(playerOneScore > playerTwoScore){
            io.println("Player 1 wins!");
        } else if (playerTwoScore > playerOneScore) {
            io.println("Player 2 wins!");
        } else {
            io.println("A tie!");
        }
    }

    //shows empty house message
    public void emptyHouseMessage(){
        io.println("House is empty. Move again.");
    }
}
