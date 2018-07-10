import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

class Item {
    private String name;
    private int cost;
    private Player owner;
    private ItemMagic itemMagic;
    private StackPane itemImage;

    public Item(String name, int cost, Player owner, ItemMagic itemMagic) {
        this.name = name;
        this.cost = cost;
        this.owner = owner;
        this.itemMagic = itemMagic;
        itemMagic.setItem(this);
        itemImage = new StackPane();
        Rectangle rectangle = new Rectangle((View.SCREEN_SIZE_X * 2) / 27, View.SCREEN_SIZE_Y / 12);
        Label label = new Label(this.name);
        itemImage.getChildren().addAll(rectangle, label);
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public StackPane getItemImage() {
        return itemImage;
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

    public void action() {
        itemMagic.action();
        this.owner.getInventory().removeItem(this.name, 1);
//        this.owner.getBattleRoot().update
    }

    public Item getCopy(Player owner) {
        return new Item(this.name, this.cost, owner, this.itemMagic.getCopy());
    }

    @Override
    public String toString() {
        return (this.name + " : (" + this.itemMagic.info() + ")");
    }

    public String info() {
        return itemMagic.info();
    }
}

abstract class ItemMagic {
    private Item item;

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    abstract public void action();

    abstract public String info();

    abstract public ItemMagic getCopy();
}

class SmallHPPotion extends ItemMagic {
    @Override
    public void action() {
        this.getItem().getOwner().increaseHP(500);
    }

    @Override
    public String info() {
        return ("Increase Player’s HP by 500");
    }

    @Override
    public SmallHPPotion getCopy() {
        return new SmallHPPotion();
    }

}

class MediumHPPotion extends ItemMagic {
    @Override
    public void action() {
        this.getItem().getOwner().increaseHP(1000);
    }

    @Override
    public String info() {
        return ("Increase Player’s HP by 1000");
    }

    @Override
    public MediumHPPotion getCopy() {
        return new MediumHPPotion();
    }

}

class LargeHPPotion extends ItemMagic {

    @Override
    public void action() {
        this.getItem().getOwner().increaseHP(2000);
    }

    @Override
    public String info() {
        return ("Increase Player’s HP by 2000");
    }

    @Override
    public LargeHPPotion getCopy() {
        return new LargeHPPotion();
    }
}

class SmallMPPotion extends ItemMagic {
    @Override
    public void action() {
        this.getItem().getOwner().increaseMP(2);
    }

    @Override
    public String info() {
        return ("Increase Player’s MP by 2");
    }

    @Override
    public SmallMPPotion getCopy() {
        return new SmallMPPotion();
    }
}

class MediumMPPotion extends ItemMagic {

    @Override
    public void action() {
        this.getItem().getOwner().increaseMP(4);
    }

    @Override
    public String info() {
        return ("Increase Player’s MP by 4");
    }

    @Override
    public MediumMPPotion getCopy() {
        return new MediumMPPotion();
    }

}

class LargeMPPotion extends ItemMagic {
    @Override
    public void action() {
        this.getItem().getOwner().increaseMP(8);
    }

    @Override
    public String info() {
        return ("Increase Player’s MP by 8");
    }

    @Override
    public LargeMPPotion getCopy() {
        return new LargeMPPotion();
    }

}

class LesserRestorative extends ItemMagic {

    @Override
    public void action() {
        this.getItem().getOwner().increaseHP(500);
        this.getItem().getOwner().increaseMP(2);
    }

    @Override
    public String info() {
        return ("Increase Player’s HP by 500 and MP by 2");
    }

    @Override
    public LesserRestorative getCopy() {
        return new LesserRestorative();
    }
}

class GreaterRestorative extends ItemMagic {

    @Override
    public void action() {
        this.getItem().getOwner().increaseHP(1000);
        this.getItem().getOwner().increaseMP(4);
    }

    @Override
    public String info() {
        return ("Increase Player’s HP by 1000 and MP by 4");
    }

    @Override
    public GreaterRestorative getCopy() {
        return new GreaterRestorative();
    }
}