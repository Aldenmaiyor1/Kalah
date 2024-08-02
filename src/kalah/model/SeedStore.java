package kalah.model;

public class SeedStore {
    private int seeds;
    public SeedStore(int seeds){
        this.seeds = seeds;
    }

    public void addSeeds(int seeds){
        this.seeds += seeds;
    }

    public int getSeeds(){
        return seeds;
    }

    public void setSeeds(int seeds){
        this.seeds = seeds;
    }
}
