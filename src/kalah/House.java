package kalah;

public class House implements SeedHolder{
    public int houseSeed;
    public int housePosition;
    public House(int housePosition){

        this.houseSeed = 4;
        this.housePosition = housePosition;
    }

    public int pickupSeed(){
        int seedsPickedUp = houseSeed;
        this.houseSeed = 0;
        System.out.println("skeet");
        System.out.println(seedsPickedUp);
        System.out.println(houseSeed);
        return seedsPickedUp;
    }

    public void addSeed(){
        houseSeed += 1;
    }

    public int getSeedNumber(){
        return houseSeed;
    }

    public int getHousePosition(){
        return housePosition;
    }
}
