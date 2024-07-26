package kalah;

public class Player  {
    public int playerSeed;
    public Player(){
        this.playerSeed = 0;
    }

    public void setPlayerSeed(int newSeed){
        this.playerSeed = newSeed;
    }
    public int getPlayerSeed(){
        return playerSeed;
    }

    public void addSeed(){
        playerSeed += 1;
    }

}
