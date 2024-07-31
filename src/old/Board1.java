package old;


import java.util.ArrayList;
import java.util.List;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;


public class Board1 {
    private List<List<House1>> houseList;
    private List<Player1> playerList;

    public Board1() {
        this.houseList = new ArrayList<>();
        this.playerList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            this.playerList.add(new Player1());
            List<House1> playerHouses = new ArrayList<>();
            for (int j = 0; j < 6; j++) {
                playerHouses.add(new House1(j));
            }
            this.houseList.add(playerHouses);
        }
    }

    public List<List<House1>> getHouseList() {
        return houseList;
    }

    public void showBoard(IO io) {
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
        io.print("| P2 |");
        for (int i = this.houseList.get(0).size() - 1; i >= 0; i--) {
            io.print(String.format(" %d[%2d] |",
                    this.houseList.get(1).get(i).getHousePosition() + 1,
                    this.houseList.get(1).get(i).getSeedNumber()));
        }
        io.print(String.format("  %d |", playerList.get(0).getPlayerSeed()));
        io.println("");
        io.println("|    |-------+-------+-------+-------+-------+-------|    |");
        io.print(String.format("|  %d |", playerList.get(1).getPlayerSeed()));
        for (int i = 0; i < this.houseList.get(1).size(); i++) {
            io.print(String.format(" %d[ %d] |",
                    this.houseList.get(0).get(i).getHousePosition() + 1,
                    this.houseList.get(0).get(i).getSeedNumber()));
        }
        io.print(" P1 |");
        io.println("");
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
    }

    public void incrementBoard() {
        for (int i = 0; i < this.houseList.size(); i++) {
            for (int j = 0; j < this.houseList.get(i).size(); j++) {
                this.houseList.get(i).get(j).addSeed();
            }
        }
        for (int i = 0; i < this.playerList.size(); i++) {
            this.playerList.get(i).addSeed();
        }
    }

    public boolean gameEnd() {
        int seedsOutOfPlay = 0;
        for (int i = 0; i < this.playerList.size(); i++) {
            seedsOutOfPlay += this.playerList.get(i).getPlayerSeed();
        }
        if (seedsOutOfPlay == 48) {
            return true;
        } else {
            return false;
        }
    }

    public boolean takePlayerTurn(int playerNumber, int houseNumber) {
        int seedsPickedUp = this.houseList.get(playerNumber).get(houseNumber).pickupSeed();
        int currentHouse = houseNumber;
        int currentPlayer = playerNumber;
        boolean changeTurn = true;

        while (seedsPickedUp > 0) {
            currentHouse++;
            if (currentHouse == 6 ) {
                currentHouse = -1;
                if(currentPlayer == playerNumber){
                    this.playerList.get(currentPlayer).addSeed();
                    currentPlayer = (currentPlayer + 1) % 2;
                } else {
                    currentPlayer = (currentPlayer + 1) % 2;
                    this.playerList.get(currentPlayer).addSeed();

                }
            } else {
                this.houseList.get(currentPlayer).get(currentHouse).addSeed();
            }
            seedsPickedUp--;
        }
        System.out.println(String.format("current house %d", currentHouse));
        System.out.println(String.format("current player %d", currentPlayer));
        if(currentHouse == -1 && currentPlayer != playerNumber){
            System.out.println("changed");
            changeTurn = false;
        }
        return changeTurn;
    }

    public static void main(String[] args) {
        Board1 m = new Board1();
        m.showBoard(new MockIO());
    }
}
