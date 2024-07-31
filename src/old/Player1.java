package old;

public class Player1 {
    public int playerSeed;
    public Player1(){
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
