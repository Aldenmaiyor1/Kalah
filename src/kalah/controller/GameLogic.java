package kalah.controller;

import kalah.model.Player;

import java.util.ArrayList;

public class GameLogic {

    //empties selected players house and corresponding opponents house and adds it to players store
    public static void stealSeed(Player thief, Player victim, int houseNumber){
        int seedsStolen = victim.getHouseList().get(5-houseNumber).getSeeds();
        victim.getHouseList().get(5-houseNumber).setSeeds(0);
        thief.getHouseList().get(houseNumber).setSeeds(0);
        thief.getPlayerSeedStore().addSeeds(seedsStolen+1);
    }

    //distribute seeds based on given current player, house number and seeds
    public static void distributeSeeds(Player currentPlayer, Player otherPlayer, int houseNumber, int seeds){
        ArrayList<Player> playerList = new ArrayList<>();
        playerList.add(currentPlayer);
        playerList.add(otherPlayer);

        int currentPlayerPosition = 0;
        houseNumber -= 1;
        while(seeds > 0){
            houseNumber ++;
            if(houseNumber == 6){
                if(playerList.get(currentPlayerPosition) == currentPlayer){
                    playerList.get(currentPlayerPosition).getPlayerSeedStore().addSeeds(1);
                    seeds--;
                }
                currentPlayerPosition = (currentPlayerPosition +1) % 2;
                houseNumber = -1;
            } else if (houseNumber < 6) {
                playerList.get(currentPlayerPosition).getHouseList().get(houseNumber).addSeeds(1);
                seeds--;
            }

        }

        //checks if player steals seed
        if(playerList.get(currentPlayerPosition) == currentPlayer
                && playerList.get(currentPlayerPosition).getHouseList().get(houseNumber).getSeeds() == 1
                && playerList.get((currentPlayerPosition+1) % 2).getHouseList().get(5-houseNumber).getSeeds() != 0){
            stealSeed(currentPlayer, otherPlayer, houseNumber);
        }

    }

    //checks if game should change current player
    public static boolean checkTurnSwitch(int startingHouse, int seeds){
        int nToReachPlayerHouse = 7 - startingHouse;
        return nToReachPlayerHouse == seeds % 14;
    }


    //checks if game should end
    public static boolean checkGameEnd(Player player){
        return player.checkEmptyHouses();
    }
}
