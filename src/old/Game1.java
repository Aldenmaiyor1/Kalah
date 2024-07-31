package old;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

public class Game1 {

    private IO mock;

    private int currentPlayerTurn;
    public Game1(IO mock){
        this.currentPlayerTurn = 0;
        this.mock = mock;
    }
    public void startGame(){
        MockIO io = new MockIO();
        Board1 boardInstance = new Board1();
        boolean changeTurn = false;
        while (!boardInstance.gameEnd()){
            boardInstance.showBoard(mock);
            int playerInput = mock.readInteger(
                    String.format("Player P%d's turn - Specify house number or 'q' to quit: ", currentPlayerTurn+1),
                    1,
                    6,
                    -1,
                    "q"
            );
            if(playerInput == -1){
                io.println("Thanks for playing");
                return;
            }
            changeTurn = boardInstance.takePlayerTurn(currentPlayerTurn, playerInput - 1);
            if(changeTurn){
                this.currentPlayerTurn = (this.currentPlayerTurn + 1) %2;
            }

            System.out.println(this.currentPlayerTurn);
        }
    }

//    public static void main(String[] args) {
//        Game m = new Game();
//        m.startGame();
//    }
}
