package kalah.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private SeedStore playerSeedStore;
    private List<SeedStore> houseList;

    //this class holds the player object which represents a single player.
    //the number of houses and the initial number of seeds per house can be set when creating the player object
    public Player(int numberOfHouses, int initialSeeds){
        this.playerSeedStore = new SeedStore(0);
        this.houseList = new ArrayList<>();
        for (int i = 0; i < numberOfHouses; i++) {
            this.houseList.add(new SeedStore(initialSeeds));
        }
    }

    public SeedStore getPlayerSeedStore(){
        return playerSeedStore;
    }

    public List<SeedStore> getHouseList(){
        return houseList;
    }

    public void addSeed(int startingSeed){
        if(startingSeed == 6){
            playerSeedStore.addSeeds(1);
        } else{
            this.houseList.get(startingSeed).addSeeds(1);
        }
    }

    //empties out a specific house and returns the seeds contained in the house
    public int pickupSeed(int houseNumber){
        int seedsPicked = this.houseList.get(houseNumber-1).getSeeds();
        this.houseList.get(houseNumber-1).setSeeds(0);
        return seedsPicked;
    }

    //checks if the players houses are empty
    public boolean checkEmptyHouses(){
        for (int i = 0; i < this.houseList.size(); i++) {
            if(this.houseList.get(i).getSeeds() != 0){
                return false;
            }
        }
        return true;
    }

    public int getTotalSeeds(){
        int playerStoreSeeds = playerSeedStore.getSeeds();
        int playerHouseSeeds = 0;
        for (int i = 0; i < houseList.size(); i++) {
            playerHouseSeeds += houseList.get(i).getSeeds();
        }

        return playerHouseSeeds + playerStoreSeeds;
    }
}
