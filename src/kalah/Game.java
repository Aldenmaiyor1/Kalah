package kalah;

import com.qualitascorpus.testsupport.MockIO;
import old.Player1;

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
    public void setPlayerSeeds(Player player, int a, int b, int c, int d, int e, int f ){
        player.getHouseList().get(0).setSeeds(a);
        player.getHouseList().get(1).setSeeds(b);
        player.getHouseList().get(2).setSeeds(c);
        player.getHouseList().get(3).setSeeds(d);
        player.getHouseList().get(4).setSeeds(e);
        player.getHouseList().get(5).setSeeds(f);
    }

    public void play(){
        int housePicked = 0;
        int seedsPickedUp = 0;

//        playerList.get(0).getHouseList().get(5).setSeeds(11);
//        setPlayerSeeds(playerList.get(0), 0,0,1,0,0,0);
//        setPlayerSeeds(playerList.get(1), 0,0,0,0,0,0);
//        playerList.get(0).getPlayerSeedStore().setSeeds(31);
//        playerList.get(1).getPlayerSeedStore().setSeeds(16);


        while(!GameLogic.checkGameEnd(playerList.get(currentPlayer))){
            this.showUI.showBoard(playerList.get(0), playerList.get(1));
            housePicked = this.input.getPlayerInput(currentPlayer);
            if(housePicked == -10){
                showUI.gameOver();
                showUI.showBoard(playerList.get(0), playerList.get(1));
                return;
            }
            if(this.playerList.get(currentPlayer).getHouseList().get(housePicked-1).getSeeds() == 0){
                showUI.emptyHouseMessage();
                continue;
            }
            seedsPickedUp = this.playerList.get(currentPlayer).pickupSeed(housePicked);
            System.out.println("seeds picked up");
            System.out.println(seedsPickedUp);
            GameLogic.distributeSeeds(
                    playerList.get(currentPlayer),
                    playerList.get((currentPlayer + 1)%2),
                    housePicked,
                    seedsPickedUp
                    );
            if (!GameLogic.checkTurnSwitch(housePicked, seedsPickedUp)) {
                currentPlayer = (currentPlayer + 1) % 2;
            }
        }
        showUI.showBoard(playerList.get(0), playerList.get(1));
        showUI.gameOver();
        showUI.showBoard(playerList.get(0), playerList.get(1));
        showUI.gameFinished(playerList.get(0), playerList.get(1));

    }

    public Player getPlayer(int playerNumber) {
        return this.playerList.get(playerNumber);
    }

//    public static void main(String[] args) {
//        Game m = new Game(new Player(6,4), new Player(6,4), new ShowUI(new MockIO()));
//        Player f = m.getPlayer(0);
//        m.getPlayer(0).getHouseList().get(2).setSeeds(0);
//        System.out.println(m.getPlayer(0).getHouseList());
//        m.play();
//    }
}
