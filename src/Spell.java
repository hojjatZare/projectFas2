import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

abstract class Spell extends Card {
    private String type;
    private SpellMagic spellMagic;

    {
        StackPane stackPane = new StackPane();
        Rectangle rectangle = new Rectangle(View.SCREEN_SIZE_X/12,View.SCREEN_SIZE_Y/8);
        Label label = new Label(this.getName()+"/n"+this.getType());
        label.setScaleX(1);
        label.setScaleY(1);
    }

    public void setSpellMagic(SpellMagic spellMagic) {
        this.spellMagic = spellMagic;
    }

    public SpellMagic getSpellMagic() {
        return spellMagic;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public void setOrigin(String origin) {
//        this.origin = origin;
//    }

    public String getType() {
        return this.type;
    }

//    public String getOrigin() {
//        return this.origin;
//    }

    public String info() {
        return ("\"" + this.getName() + "\" Info:\n" +
                "Name:" + this.getName() + "\n" +
                "MP cost:" + this.getMP() + "\n" +
                "Card Type:" + this.getType() + "\n" +
                spellMagic.info()+"\n");
    }

    public void spell() {
        System.out.println(this.getOwner().getName()+"'s spellCard("+this.getName() + ") has cast a spell\ndetail : " + this.spellMagic.info());
        this.spellMagic.spell();
        this.getOwner().getEnemy().updateMonsterField();
        this.getOwner().updateMonsterField();

    }

    @Override
    public String toString() {
        return ("spell :" + getName() + "(MP=" + getMP() + "--type=$$" + getType() + "$$--" + spellMagic.info() + ")");
    }
    @Override
    public int getCost(){
        return this.getMP()*700;
    }

    abstract public Spell getCopy(Player newOwner);
}

class Instant extends Spell implements HasAutomaticSpell {

    public Instant(int MP, String name, String type, SpellMagic spellMagic, Player owner, int numberAbleToBuy) {
        this.setMP(MP);
        this.setName(name);
        this.setType(type);
        this.setSpellMagic(spellMagic);
        spellMagic.setSpellCard(this);
        this.setOwner(owner);
        this.setNumbersAbleToBuy(numberAbleToBuy);
    }

    @Override
    public void automaticSpell() {
        System.out.println(this.getOwner().getName()+"("+this.getName() + ") has cast a spell\ndetail : " + this.getSpellMagic().info());
        ((HasAutomaticSpell) getSpellMagic()).automaticSpell();
        this.getOwner().getEnemy().updateMonsterField();
        this.getOwner().getEnemy().updateMonsterField();
        this.getOwner().updateMonsterField();
    }

    @Override
    public Instant getCopy(Player owner) {
        return (new Instant(this.getMP(), this.getName(), this.getType(), this.getSpellMagic().getCopy(), owner,this.getNumbersAbleToBuy()));
    }
}


class Continuous extends Spell {

    public Continuous(int MP, String name, String type, SpellMagic spellMagic, Player owner, int numberAbleToBuy) {
        this.setMP(MP);
        this.setName(name);
        this.setType(type);
        spellMagic.setSpellCard(this);
        this.setSpellMagic(spellMagic);
        this.setOwner(owner);
        this.setNumbersAbleToBuy(numberAbleToBuy);
    }

    @Override
    public Continuous getCopy(Player owner) {
        return new Continuous(this.getMP(), this.getName(), this.getType(), this.getSpellMagic().getCopy(), owner,getNumbersAbleToBuy());
    }
}

class Aura extends Spell {

    public Aura(int MP, String name, String type, SpellMagic spellMagic, Player owner,int numberAbleToBuy) {
        this.setMP(MP);
        this.setName(name);
        this.setType(type);
        spellMagic.setSpellCard(this);
        this.setSpellMagic(spellMagic);
        this.setOwner(owner);
        this.setNumbersAbleToBuy(numberAbleToBuy);
    }

    public void disSpell() {
        System.out.println(this.getName() + "  spell has stoped!!  detail : " + this.getSpellMagic().info());
        ((AuraMagic) this.getSpellMagic()).disSpell();
    }

    @Override
    public Aura getCopy(Player owner) {
        return (new Aura(this.getMP(), this.getName(), this.getType(), this.getSpellMagic().getCopy(), owner,this.getNumbersAbleToBuy()));
    }
}

interface HasAutomaticSpell {
    public void automaticSpell();
}