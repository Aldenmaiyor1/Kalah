package kalah.controller;

import com.qualitascorpus.testsupport.MockIO;
import com.qualitascorpus.testsupport.IO;

public class Input {
    private IO io;
    public Input(IO io){
        this.io = io;
    }

    public int getPlayerInput(int currentPlayer){
        return  io.readInteger(
                String.format("Player P%d's turn - Specify house number or 'q' to quit: ", currentPlayer+1),
                1,
                6,
                -10,
                "q"
        );
    }
}
