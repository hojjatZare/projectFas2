import java.util.Scanner;

/////////////////////amulets////////////////////////////

class Amulet {
    private String name;
    private int cost;
    private Player owner;
    private AmuletMagic amuletMagic;
    private int numbersAbleToBuy = 1;

    public Amulet(String name, int cost, Player owner, AmuletMagic amuletMagic) {
        this.name = name;
        this.cost = cost;
        this.owner = owner;
        this.amuletMagic = amuletMagic;
        amuletMagic.setAmulet(this);
    }

    public void setNumbersAbleToBuy(int numbersAbleToBuy) {
        this.numbersAbleToBuy = numbersAbleToBuy;
    }

    public int getNumbersAbleToBuy() {
        return numbersAbleToBuy;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

    public void setcost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void actoin() {
        amuletMagic.action();
    }

    public AmuletMagic getAmuletMagic() {
        return amuletMagic;
    }

    public void setAmuletMagic(AmuletMagic amuletMagic) {
        this.amuletMagic = amuletMagic;
    }

    public Amulet getCopy(Player owner) {
        return (new Amulet(this.name, this.cost, owner, this.amuletMagic.getCopy()));
    }

    public String info() {
        return this.amuletMagic.info();
    }
}

abstract class AmuletMagic {
    private Amulet amulet;

    public void setAmulet(Amulet amulet) {
        this.amulet = amulet;
    }

    public Amulet getAmulet() {
        return amulet;
    }

    abstract public void action();

    abstract public String info();

    abstract public AmuletMagic getCopy();
}

class IronPendant extends AmuletMagic {
    @Override
    public void action() {
        this.getAmulet().getOwner().increaseMaxHP(500);
    }

    @Override
    public String info() {
        return ("Increase Player’s Max HP by 500");
    }

    @Override
    public IronPendant getCopy() {
        return new IronPendant();
    }
}

class GoldPendant extends AmuletMagic {

    @Override
    public void action() {
        this.getAmulet().getOwner().increaseMaxHP(1000);
    }

    @Override
    public String info() {
        return ("Increase Player’s Max HP by 1000");
    }

    @Override
    public GoldPendant getCopy() {
        return new GoldPendant();
    }
}

class DiamondPendant extends AmuletMagic {
    @Override
    public void action() {
        this.getAmulet().getOwner().increaseMaxHP(2000);
    }

    @Override
    public String info() {
        return ("Increase Player’s Max HP by 2000");
    }

    @Override
    public DiamondPendant getCopy() {
        return new DiamondPendant();
    }
}

class IronRing extends AmuletMagic {
    @Override
    public void action() {
        this.getAmulet().getOwner().increaseMaxMP(1);
    }

    @Override
    public String info() {
        return ("Increase Player’s Max MP by 1");
    }

    @Override
    public IronRing getCopy() {
        return new IronRing();
    }
}

class GoldRing extends AmuletMagic {

    @Override
    public void action() {
        this.getAmulet().getOwner().increaseMaxMP(2);
    }


    @Override
    public String info() {
        return ("Increase Player’s Max MP by 2");
    }

    @Override
    public GoldRing getCopy() {
        return new GoldRing();
    }
}

class DiamondRing extends AmuletMagic {


    @Override
    public void action() {
        this.getAmulet().getOwner().increaseMaxMP(3);
    }


    @Override
    public String info() {
        return ("Increase Player’s Max MP by 3");
    }

    @Override
    public DiamondRing getCopy() {
        return new DiamondRing();
    }
}

class DemonKingsCrown extends AmuletMagic {

    @Override
    public void action() {
        //Decrease All Incoming Damages by 20%
        // it does not have action but it's action considered in decrease and  methods
    }

    @Override
    public String info() {
        return ("Decrease All Incoming Damages by 20%");
    }

    @Override
    public DemonKingsCrown getCopy() {
        return new DemonKingsCrown();
    }
}