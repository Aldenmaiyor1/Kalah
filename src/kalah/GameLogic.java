package kalah;

import java.util.ArrayList;

public class GameLogic {
    public static boolean checkHouseSteal(Player player, int finalHouse){
        ArrayList<Integer> emptyHouses = new ArrayList<>();
        for (int i = 0; i < player.getHouseList().size(); i++) {
            if(player.getHouseList().get(i).getSeeds() == 0){
                emptyHouses.add(i);
                System.out.println(i);
            }
        }
        return emptyHouses.contains(finalHouse) || finalHouse == 13;
    }

    public static void stealSeed(Player thief, Player victim, int houseNumber){
        int seedsStolen = victim.getHouseList().get(5-houseNumber).getSeeds();
        victim.getHouseList().get(5-houseNumber).setSeeds(0);
        thief.getHouseList().get(houseNumber).setSeeds(0);
        thief.getPlayerSeedStore().addSeeds(seedsStolen+1);
    }

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
        if(playerList.get(currentPlayerPosition) == currentPlayer
                &&playerList.get(currentPlayerPosition).getHouseList().get(houseNumber).getSeeds() == 1
                && playerList.get((currentPlayerPosition+1) % 2).getHouseList().get(5-houseNumber).getSeeds() != 0){
            System.out.println("sseeeededdstollenennn");
            System.out.println(houseNumber);
            stealSeed(currentPlayer, otherPlayer, houseNumber);
        }

    }
    public static boolean checkTurnSwitch(int startingHouse, int seeds){
        int nToReachPlayerHouse = 7 - startingHouse;
        return nToReachPlayerHouse == seeds % 14;
    }

    public static boolean checkGameEnd(Player player1, Player player2){
        return player1.checkEmptyHouses() || player2.checkEmptyHouses();
    }
}
