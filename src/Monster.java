import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

abstract class Card {
    //    private int Cost;
    private int MP;
    private String name;
    private Player owner;
    private int numbersAbleToBuy;
    private CardImage cardImage;


    public void setCardImage(CardImage cardImage) {
        this.cardImage = cardImage;
    }

    public CardImage getCardImage() {
        return cardImage;
    }

    public boolean isClicked(MouseEvent event) {
        if (event.getPickResult().getIntersectedNode() == this.cardImage.rectangle || event.getPickResult().getIntersectedNode() == this.cardImage.label) {
            return true;
        }
        return false;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOnMouseClicked() {
        final Card temp = this;
        this.cardImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (owner.getMonsterField().getCards().contains(temp)) {
                    if (NormalMonster.class.isAssignableFrom(temp.getClass()) || General.class.isAssignableFrom(temp.getClass())) {
                        owner.getBattleRoot().setNoneSpellerMonsterCommandOnRoot();
                    } else if (SpellCaster.class.isAssignableFrom(temp.getClass()) || Hero.class.isAssignableFrom(temp.getClass())) {
                        owner.getBattleRoot().setSpellerMonsterCommandOnRoot();
                    }
//                }else if(owner.getHand().getCards().contains(temp)){
//                    owner.getBattleRoot().setHandCommandOnCommandRoot();
                }
                owner.getBattleRoot().getTextFieldRoot().setText(temp.info());
                owner.setLastCardClicked(temp);
            }
        });
    }

//    public void unsetOnMouseClick(){
//        this.cardImage.setOnMouseClicked(null);
//    }

    public void setMP(int MP) {
        this.MP = MP;
    }

    public void setNumbersAbleToBuy(int numbersAbleToBuy) {
        this.numbersAbleToBuy = numbersAbleToBuy;
    }

    public Player getOwner() {
        return owner;
    }

    public int getMP() {
        return MP;
    }

    public int getNumbersAbleToBuy() {
        return numbersAbleToBuy;
    }

    //    public int getCost() {
//        return Cost;
//    }

    public String getName() {
        return name;
    }

    public void increasMP(int amount) {
        this.MP += amount;
    }

    public void decreasMP(int amount) {
        this.MP -= amount;
    }

    @Override
    public String toString() {
        return (name + " : MP=" + MP);
    }

    abstract public Card getCopy(Player owner);

    abstract public int getCost();

    abstract public String info();

}

abstract class Monster extends Card {
    private int initialHP;
    private int initialAP;
    private int AP;
    private int HP;
    private boolean isNimble;
    private boolean isDefender;
    private boolean isSleep;
    private boolean canAttack;
    private boolean canSpell;
    private String type;
    private String tribe;

    /// Constructor of Monster

    Monster(String name, int MP, int HP, int AP, String type, String tribe, Boolean isNimble, Boolean isDefender, Player owner, int numbersAbleToBuy) {
        this.initialHP = HP;
        this.initialAP = AP;
        this.setName(name);
        this.setMP(MP);
        this.setHP(HP);
        this.setAP(AP);
        this.setType(type);
        this.setTribe(tribe);
        this.setIsNimble(isNimble);
        this.setIsSleep(!isNimble);
        this.setCanAttack(isNimble);
        this.setCanSpell(isNimble);
        this.setIsDefender(isDefender);
        this.setOwner(owner);
        this.setNumbersAbleToBuy(numbersAbleToBuy);
        String labelString = this.getName() + "\n" + "HP = " + this.getHP() + "\n" + "AP = " + this.getHP();
        this.setCardImage(new CardImage(View.SCREEN_SIZE_X / 18, View.SCREEN_SIZE_Y / 8, labelString, this));
        if (this.getTribe().equals("Elven Monster")) {
            getCardImage().getRectangle().setFill(Color.LIGHTGREEN);
        } else if (this.getTribe().equals("DragonBreed Monster")) {
            getCardImage().getRectangle().setFill(Color.LIGHTCORAL);
        } else if (this.getTribe().equals("Elven Monster")) {
            getCardImage().getRectangle().setFill(Color.LIGHTSLATEGREY);
        } else {
            getCardImage().getRectangle().setFill(Color.YELLOW);
        }
        /// Setting Cost of the Card
    }

    public void reborn() {
        this.HP = initialHP;
        this.AP = initialAP;
        this.isSleep = !isNimble;
        this.canAttack = isNimble;
        this.canSpell = isNimble;
    }


    @Override
    public int getCost() {

        if (this instanceof NormalMonster) {
            return getMP() * 300;
        } else if (this instanceof SpellCaster) {
            return getMP() * 500;
        } else if (this instanceof General) {
            return getMP() * 700;
        } else {                        /// if it was Hero
            return getMP() * 1000;
        }

    }

    ////// setter methods {


    public void setCanSpell(boolean canSpell) {
        this.canSpell = canSpell;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    public void setIsSleep(boolean isSleep) {
        this.isSleep = isSleep;
    }

    public void setAP(int AP) {
        this.AP = AP;
    }

    public void setTribe(String string) {
        this.tribe = string;
    }

    public void setIsNimble(boolean isNimble) {
        this.isNimble = isNimble;
    }

    public void setIsDefender(boolean isDefender) {
        this.isDefender = isDefender;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setType(String type) {
        this.type = type;
    }


    ///////// getter methods {


    public boolean isSleep() {
        return isSleep;
    }

    public boolean isDefender() {
        return isDefender;
    }

    public boolean isNimble() {
        return isNimble;
    }

    public boolean canAttack() {
        return canAttack;
    }

    public boolean canSpell() {
        return canSpell;
    }

    public String getType() {
        return type;
    }

    public int getAP() {
        return AP;
    }

    public int getHP() {
        return HP;
    }

    public String getTribe() {
        return tribe;
    }

    ////// other methods {

    public void decreaseHP(int amount) {
        if (this.getOwner().getEquipedAmulet() == null) {
            this.HP -= amount;
        } else {
            if (DemonKingsCrown.class.isAssignableFrom(this.getOwner().getEquipedAmulet().getAmuletMagic().getClass())) {
                this.HP -= amount * 0.8;
            } else {
                this.HP -= amount;
            }
        }
    }

    public void increaseHP(int amount) {
        this.HP += amount;
    }

    public void decreaseAP(int amount) {
        this.AP -= amount;
    }

    public void increaseAP(int amount) {
        this.AP += amount;
    }

    public void attack() {
//        int counter = 0;
        if (this.getOwner().getEnemy().getMonsterField().hasDefenderCard()) {
            for (Monster monster : this.getOwner().getEnemy().monsterField.getCards()) {
                if (monster.isDefender) {
                    monster.getCardImage().getRectangle().setStroke(Color.RED);
                    monster.getCardImage().getRectangle().setStrokeWidth(3);
                }
            }
            this.getOwner().getStage().getScene().getRoot().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getPickResult().getIntersectedNode() != null) {
                        Monster target = (Monster) getOwner().selectBetweenEneMysMonsterField(event);
                        if (target.isDefender) {
                            decreaseHP(target.getAP());
                            target.decreaseHP(AP);
                            canAttack = false;
                            getOwner().updateMonsterField();
                            getOwner().getEnemy().updateMonsterField();
                        }
                    }
                    getOwner().getStage().getScene().getRoot().setOnMouseClicked(null);
                }
            });
//            System.out.println("select a target");
//            for (Monster monster : this.getOwner().getEnemy().getMonsterField().getCards()) {
//                if (monster.isDefender) {
//                    System.out.println(counter + "." + monster.toString());
//                }
//                counter++;
//            }
//            int targetNumber = Scan.scanner.nextInt();
//            Monster target = this.getOwner().getEnemy().getMonsterField().getOneCard(targetNumber);

//            System.out.println(this.getOwner().getName() + "'s card(" + this.getName() + ")" + " attacked to " + target.getName());
        } else {
            this.getOwner().getEnemy().setStrokeForMonsterField();
            this.getOwner().getEnemy().setStrokeForImage();
//            this.getOwner().getEnemy().showPlayreAndMonsterFieldForSelecting();

            this.getOwner().getStage().getScene().getRoot().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (getOwner().getEnemy().isClicked(event)) {
                        getOwner().getEnemy().decreaseHP(AP);
                    } else {   // one of enemy's monster selected
                        if (getOwner().selectBetweenEneMysMonsterField(event) != null) {
                            Monster target = (Monster) getOwner().selectBetweenEneMysMonsterField(event);
                            decreaseHP(target.getAP());
                            target.decreaseHP(AP);
                            canAttack = false;
                            getOwner().updateMonsterField();
                            getOwner().getEnemy().updateMonsterField();

                        }
                    }
                    getOwner().getStage().getScene().getRoot().setOnMouseClicked(null);
                }
            });

            ////
//            int targetNumber = Scan.scanner.nextInt();
//            if (targetNumber == 11) {
//                Player target = this.getOwner().getEnemy();
//
//                System.out.println(this.getOwner().getName() + "'s card(" + this.getName() + ")" + " attacked to " + target.getName());
//            } else if (targetNumber < this.getOwner().getEnemy().getMonsterField().getCards().size()) {
//                Monster target = this.getOwner().getEnemy().getMonsterField().getOneCard(targetNumber);
//                if (target != null) {
//
//                    System.out.println(this.getOwner().getName() + "'s card(" + this.getName() + ")" + " attacked to " + target.getName());
//                } else {
//                    System.out.println("there is no target such this");
//                }
//            } else {
            System.out.println("wrong input!!");
//            }
        }
        this.canAttack = false;
        this.getOwner().updateMonsterField();
        this.getOwner().getEnemy().updateMonsterField();
    }

    private void attack(Monster monster) {
        this.decreaseHP(monster.getAP());
        monster.decreaseHP(this.getAP());
        System.out.println(this.getName() + " attacked to " + monster.getName());
    }

    private void attack(Player player) {
        player.decreaseHP(this.getAP());
        System.out.println(this.getName() + " attacked to " + this.getOwner().getName() + " damage=" + this.getAP());

    }

    public void automaticAttack() {
        if (this.getOwner().getEnemy().getMonsterField().hasDefenderCard()) {
            for (Monster monster : this.getOwner().getEnemy().getMonsterField().getCards()) {
                if (monster.isDefender) {
                    this.attack(monster);
                    break;
                }
            }
        } else {     //  enemy does not have defender
            int mostDamageMyMonstersCan = this.getOwner().getMostDamageMonstersCanDo();
            if ((mostDamageMyMonstersCan < this.getOwner().getEnemy().getHP() && this.getOwner().getHP() < 3000 && !this.getOwner().getEnemy().getMonsterField().getCards().isEmpty()) || this.getOwner().getEnemy().getMostDamageMonstersCanDo() >= 2500) {
                Monster target = this.getOwner().getEnemy().getMonsterField().getOneCard(0);
                boolean targetSelected = false;
                for (Monster monster : this.getOwner().getEnemy().getMonsterField().getCards()) {
                    if (monster.getAP() > target.getAP() && mostDamageMyMonstersCan >= monster.getHP()) {
                        target = monster;
                        targetSelected = true;
                    }
                }
                if (!targetSelected) {
                    for (Monster monster : this.getOwner().getEnemy().getMonsterField().getCards()) {
                        if (monster.getMP() > target.getMP() && mostDamageMyMonstersCan >= monster.getHP()) {
                            target = monster;
                            targetSelected = true;
                        }
                    }
                }
                if (!targetSelected) {
                    for (Monster monster : this.getOwner().getEnemy().getMonsterField().getCards()) {
                        if (mostDamageMyMonstersCan >= monster.getHP()) {
                            target = monster;
                            targetSelected = true;
                        }
                    }
                }
                this.attack(target);
            } else {
                this.attack(this.getOwner().getEnemy());
            }
        }
        this.getOwner().getEnemy().updateMonsterField();
        this.getOwner().updateMonsterField();
    }
    /// Info Method

    public String info() {
        return "\"" + getName() + "\" Info:\n" +
                "Name: \"" + getName() + "\"\n" +
                "HP: " + getHP() + "\n" +
                "AP: " + getAP() + "\n" +
                "MP cost: " + getCost() + "\n" +
                "Card Type: " + getType() + "\n" +
                "Card Tribe: " + getTribe() + "\n" +
                "Is Defensive: " + isDefender() + "\n" +
                "Is Nimble: " + isNimble() + "\n";
    }

    public String toString() {
        String temp;
        if (isSleep) {
            temp = "is sleeping";
        } else {
            temp = "$not sleeping$";
        }
        String temp2;
        if (isDefender) {
            temp2 = "#Defender#";
        } else {
            temp2 = "not Defender";
        }
        return ("Monster :{" + getName() + ":(( MP=" + getMP() + "--HP=" + getHP() + "--AP=" + getAP() + "--type=" + getType() + "--" + temp2 + "--" + temp + ")}");
    }

    abstract public Monster getCopy(Player owner);
}

class NormalMonster extends Monster {
    public NormalMonster(String name, int MP, int HP, int AP, String type, String tribe, Boolean isNimble, Boolean isDefender, Player owner, int numberAbleToBuy) {
        super(name, MP, HP, AP, type, tribe, isNimble, isDefender, owner, numberAbleToBuy);
    }

    @Override
    public NormalMonster getCopy(Player owner) {
        return new NormalMonster(getName(), getMP(), getHP(), getAP(), getType(), getTribe(), isNimble(), isDefender(), owner, getNumbersAbleToBuy());
    }
}

class SpellCaster extends Monster implements HasSpell {
    private SpellCasterMagic spellCasterMagic;

    public SpellCaster(String name, int MP, int HP, int AP, String type, String tribe, Boolean isNimble, Boolean isDefender, Player owner, SpellCasterMagic spellCasterMagic, int numberAbleToBuy) {
        super(name, MP, HP, AP, type, tribe, isNimble, isDefender, owner, numberAbleToBuy);
        this.spellCasterMagic = spellCasterMagic;
        spellCasterMagic.setMonster(this);
    }

    public void spell() {
        this.spellCasterMagic.spell();
        System.out.println(this.getName() + "'s spell executed.  detail: " + this.spellCasterMagic.info());
        this.setCanSpell(false);
        this.getOwner().getEnemy().updateMonsterField();
        this.getOwner().updateMonsterField();
    }

    @Override
    public SpellCaster getCopy(Player owner) {
        return new SpellCaster(getName(), getMP(), getHP(), getAP(), getType(), getTribe(), isNimble(), isDefender(), owner, spellCasterMagic.getCopy(), getNumbersAbleToBuy());
    }

    @Override
    public String info() {
        return (super.info() + this.spellCasterMagic.info() + "\n");
    }

    @Override
    public String MagicInfo() {
        return (this.spellCasterMagic.info());
    }
}

class General extends Monster implements HasBattleCryAndWill {
    GeneralMagic generalMagic;

    public General(String name, int MP, int HP, int AP, String type, String tribe, Boolean isNimble, Boolean isDefender, Player owner, GeneralMagic generalMagic, int numberAbleToBuy) {
        super(name, MP, HP, AP, type, tribe, isNimble, isDefender, owner, numberAbleToBuy);
        this.setGeneralMagic(generalMagic);
        generalMagic.setMonster(this);
    }

    public void setGeneralMagic(GeneralMagic generalMagic) {
        this.generalMagic = generalMagic;
        generalMagic.setMonster(this);
    }

    public void battleCry() {
        System.out.println(this.getName() + "'s battleCry executed. detail: " + this.generalMagic.info());
        generalMagic.battleCry();
        this.getOwner().getEnemy().updateMonsterField();
        this.getOwner().updateMonsterField();
    }

    public void will() {
        System.out.println(this.getName() + "'s will executed. detail: " + this.generalMagic.info());
        generalMagic.will();
        this.getOwner().getEnemy().updateMonsterField();
        this.getOwner().updateMonsterField();
    }

    @Override
    public General getCopy(Player owner) {
        return new General(getName(), getMP(), getHP(), getAP(), getType(), getTribe(), isNimble(), isDefender(), owner, generalMagic.getCopy(), getNumbersAbleToBuy());
    }

    @Override
    public String info() {
        return (super.info() + this.generalMagic.info() + "\n");
    }
}

class Hero extends Monster implements HasBattleCryAndWill, HasSpell {
    private HeroMagic heroMagic;

    public Hero(String name, int MP, int HP, int AP, String type, String tribe, Boolean isNimble, Boolean isDefender, Player owner, HeroMagic heroMagic, int numberAbleToBuy) {
        super(name, MP, HP, AP, type, tribe, isNimble, isDefender, owner, numberAbleToBuy);
        this.setHeroMagic(heroMagic);
        heroMagic.setMonster(this);
    }

    public void setHeroMagic(HeroMagic heroMagic) {
        this.heroMagic = heroMagic;
        heroMagic.setMonster(this);
    }

    public void battleCry() {
        System.out.println(this.getName() + "'s battleCry executed. detail :" + this.heroMagic.info());
        heroMagic.battleCry();
        this.getOwner().getEnemy().updateMonsterField();
        this.getOwner().updateMonsterField();
    }


    public void will() {
        System.out.println(this.getName() + "'s will executed. deatail: " + this.heroMagic.info());
        heroMagic.will();
        this.getOwner().getEnemy().updateMonsterField();
        this.getOwner().updateMonsterField();
    }

    public void spell() {
        heroMagic.spell();
        System.out.println(this.getName() + "'s spell executed. detail :" + this.heroMagic.info());
        this.setCanSpell(false);
        this.getOwner().getEnemy().updateMonsterField();
        this.getOwner().updateMonsterField();
    }

    @Override
    public Hero getCopy(Player owner) {
        return new Hero(getName(), getMP(), getHP(), getAP(), getType(), getTribe(), isNimble(), isDefender(), owner, heroMagic.getCopy(), getNumbersAbleToBuy());
    }

    @Override
    public String info() {
        return (super.info() + this.heroMagic.info() + "\n");
    }

    @Override
    public String MagicInfo() {
        return (this.heroMagic.info());
    }
}

interface HasBattleCryAndWill {
    public void battleCry();

    public void will();
}

interface HasSpell {
    public void spell();

    public boolean canAttack();

    public boolean canSpell();

    public void attack();

    public String info();

    public String MagicInfo();

    public String getName();
}

class CardImage extends StackPane {
    CardRectangle rectangle;
    CardLabel label;

    class CardRectangle extends Rectangle implements MyNode {
        private Card card;

        CardRectangle(Integer width, Integer height, Card card) {
            super(width, height);
            this.card = card;
            this.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (card.getOwner().getLastCardClicked() != null)
                        card.getOwner().getLastCardClicked().getCardImage().rectangle.setStroke(null);
                    rectangle.setStroke(Color.PERU);
                    rectangle.setStrokeWidth(4);
                }
            });
        }

        public Card getCard() {
            return card;
        }
    }

    class CardLabel extends Label implements MyNode {
        private Card card;

        CardLabel(String text, Card card) {
            super(text);
            this.card = card;
            this.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (card.getOwner().getLastCardClicked() != null)
                        card.getOwner().getLastCardClicked().getCardImage().rectangle.setStroke(null);
                    rectangle.setStroke(Color.PERU);
                    rectangle.setStrokeWidth(4);
                }
            });
        }

        public Card getCard() {
            return card;
        }
    }

    CardImage(Integer rectangleWidth, Integer rectangleHeight, String text, Card card) {
        rectangle = new CardRectangle(rectangleWidth, rectangleHeight, card);
        label = new CardLabel(text, card);
        this.getChildren().addAll(rectangle, label);
//        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                rectangle.setStroke(Color.RED);
//                rectangle.setStrokeWidth(3);
//            }
//        });
    }

    public CardRectangle getRectangle() {
        return rectangle;
    }

    public CardLabel getLabel() {
        return label;
    }
}

interface MyNode {
    public Card getCard();
}