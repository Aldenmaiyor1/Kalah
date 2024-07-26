package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

import java.util.Scanner;
public class Game {

    private int currentPlayerTurn;
    public Game(){
        this.currentPlayerTurn = 0;
    }
    public void startGame(){
        MockIO io = new MockIO();
        Board boardInstance = new Board();
        while (!boardInstance.gameEnd()){
            boardInstance.showBoard(io);
            int playerInput = io.readInteger(
                    String.format("Player %d's turn - Specify house number or 'q' to quit: ", currentPlayerTurn+1),
                    1,
                    6,
                    -1,
                    "q"
            );
            if(playerInput == -1){
                io.println("Thanks for playing");
                return;
            }
            boardInstance.takePlayerTurn(currentPlayerTurn, playerInput - 1);
            this.currentPlayerTurn = (this.currentPlayerTurn + 1) %2 ;
            System.out.println(this.currentPlayerTurn);
        }
    }

    public static void main(String[] args) {
        Game m = new Game();
        m.startGame();
    }
}
