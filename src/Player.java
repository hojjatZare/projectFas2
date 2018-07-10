import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


class Player {
    private String name;
    private int HP = 10000;
    private int maxHP = 10000;
    private int MP = 1;
    private int maxMP = 1;
    private long gilAmount = 0;
    private Player enemy;
    private Amulet equipedAmulet;
    Inventory inventory = new Inventory();
    Deck deck = new Deck();
    Hand hand = new Hand();
    MonsterField monsterField = new MonsterField();
    SpellField spellField = new SpellField();
    GraveYard graveYard = new GraveYard();
    private PlayerImage playerImage;
    private BattleRoot battleRoot;
    private Card lastCardClicked;
    Item lastItemClicked;
    private PlayFieldRoot playFieldRoot;
    Stage stage;
    Image image;

    public Player() {

    }

    public Player(String name, Image image, Stage stage) {
        this.stage = stage;
        this.name = name;
        this.image = image;
//        View.SCREEN_SIZE_X / 18     View.SCREEN_SIZE_Y / 8
        this.playerImage = new PlayerImage(View.SCREEN_SIZE_X / 18, View.SCREEN_SIZE_Y / 8, image, this);
        this.playFieldRoot = new PlayFieldRoot((int) stage.getWidth(), (int) stage.getHeight(),this);
        ImageView imageView = new ImageView(image);
        imageView.setScaleX(View.SCREEN_SIZE_X / (12 * imageView.getFitWidth()));
        imageView.setScaleY(View.SCREEN_SIZE_Y / (12 * imageView.getFitHeight()));
    }

    public PlayFieldRoot getPlayFieldRoot() {
        return playFieldRoot;
    }

    public void setStrokeForImage() {
        this.playerImage.getRectangle().setStroke(Color.RED);
    }

    public void unSetStrokeForImage() {
        this.playerImage.getRectangle().setStroke(null);
    }

    public void setStrokeForMonsterField() {
        for (Monster monster : this.getMonsterField().getCards()) {
            monster.getCardImage().rectangle.setStroke(Color.RED);
        }
    }

    public Card selectBetweenEneMysMonsterField(MouseEvent event) {
        Monster target = null;
        Integer screenWidth = getBattleRoot().getScreenWidth();
        Integer screenHeight = getBattleRoot().getScreenHeight();
        if (event.getX() > screenWidth / 3 && event.getX() < (screenWidth * 2) / 3 && event.getY() > screenHeight / 3 && event.getY() < screenHeight / 2) {
            Node node = event.getPickResult().getIntersectedNode();
            if (node != null) {
                target = (Monster) ((MyNode) node).getCard();
            }
        }
        return target;
    }

    public Card selectBetweenMyMonsterField(MouseEvent event){
        Monster target = null;
        Integer screenWidth = getBattleRoot().getScreenWidth();
        Integer screenHeight = getBattleRoot().getScreenHeight();
        if (event.getX() > screenWidth / 3 && event.getX() < (screenWidth * 2) / 3 && event.getY() > screenHeight / 2 && event.getY() < (2 * screenHeight) / 3) {
            Node node = event.getPickResult().getIntersectedNode();
            if (node != null) {
                target = (Monster) ((MyNode) node).getCard();
            }
        }
        return target;
    }

    public void unSetStrokeforMonsterField() {
        for (Monster monster : this.getMonsterField().getCards()) {
            monster.getCardImage().rectangle.setStroke(null);
        }
    }

    public boolean isClicked(MouseEvent event){
        Node node = event.getPickResult().getIntersectedNode();
        if(node==this.playerImage.rectangle || node == this.playerImage.imageView){
            return true;
        }
        return false;
    }

    public void setLastItemClicked(Item lastItemClicked) {
        this.lastItemClicked = lastItemClicked;
    }

    public Item getLastItemClicked() {
        return lastItemClicked;
    }

    public void setLastCardClicked(Card lastCardClicked) {
        this.lastCardClicked = lastCardClicked;
    }

    public Card getLastCardClicked() {
        return lastCardClicked;
    }

    public Stage getStage() {
        return this.stage;
    }


    public StackPane getPlayerImage() {
        return playerImage;
    }

    ///////// kinds of moving cards between fields. {

    public void moveCardFromFirstToSecond(Card card, Field field1, Field field2) {
        field2.add(card);
        field1.remove(card);
    }

    public void setBattle(BattleRoot battleRoot) {
        this.battleRoot = battleRoot;
        this.getEnemy().battleRoot = battleRoot;
    }

    public BattleRoot getBattleRoot() {
        return battleRoot;
    }

    public void moveCardFromFirstToSecond(Monster card, MonsterField field1, Field field2) {
        field2.add(card);
        field1.remove(card);
    }

    public void moveCardFromFirstToSecond(Card card, Field field1, MonsterField field2) {
        Monster monster = (Monster) card;
        field2.add(monster);
        field1.remove(card);
    }

    public void moveCardFromFirstToSecond(Spell card, SpellField field1, Field field2) {
        field2.add(card);
        field1.remove(card);
    }

    public void moveCardFromFirstToSecond(Card card, Field field1, SpellField field2) {
        Spell spell = (Spell) card;
        field2.add(spell);
        field1.remove(card);
    }

    public void moveNextCardFromDeckToHand() {
        if (!deck.getCards().isEmpty()) {
            hand.add(deck.getOneCard(0));
            deck.remove(0);
        }
    }

    public void setEquipedAmulet(Amulet equipedAmulet) {
        this.equipedAmulet = equipedAmulet;
        if (equipedAmulet != null)
            equipedAmulet.setOwner(this);

    }

    public void setGilAmount(long gil) {
        this.gilAmount = gil;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public void setMaxMP(int maxMP) {
        this.maxMP = maxMP;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMP(int MP) {
        this.MP = MP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void setGraveYard(GraveYard graveYard) {
        this.graveYard = graveYard;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public void setMonsterField(MonsterField monsterField) {
        this.monsterField = monsterField;
    }

    public void setSpellField(SpellField spellField) {
        this.spellField = spellField;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setEnemy(Player enemy) {
        this.enemy = enemy;
        if (enemy != null)
            enemy.enemy = this;
    }

    public Amulet getEquipedAmulet() {
        return equipedAmulet;
    }

//    public AmuletInventory getAmuletInventory() {
//        return amuletInventory;
//    }
//
//    public ItemInventory getItemInventory() {
//        return itemInventory;
//    }

    public long getGilAmount() {
        return gilAmount;
    }

    public String getName() {
        return name;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getMaxMP() {
        return maxMP;
    }

    public int getMP() {
        return MP;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public GraveYard getGraveYard() {
        return graveYard;
    }

    public Hand getHand() {
        return hand;
    }

    public Deck getDeck() {
        return deck;
    }

    public MonsterField getMonsterField() {
        return monsterField;
    }

    public SpellField getSpellField() {
        return spellField;
    }

    public int getHP() {
        return HP;
    }

    public Player getEnemy() {
        return enemy;
    }

    public int getMostDamageMonstersCanDo() {
        int answer = 0;
        for (Monster monster : this.getMonsterField().getCards()) {
            answer += monster.getAP();
        }
        return answer;
    }

    public void increaseHP(int amount) {
        this.HP += amount;
    }

    public void decreaseHP(int amount) {

        if (this.getEquipedAmulet() == null) {
            this.HP -= amount;
        } else {
            if (DemonKingsCrown.class.isAssignableFrom(this.equipedAmulet.getAmuletMagic().getClass())) {
                this.HP -= amount * 0.8;
            } else {
                this.HP -= amount;
            }
        }
    }

    public void increaseMaxHP(int amount) {
        this.maxHP += amount;
    }

    public void decreaseMaxHP(int amount) {
        this.maxHP -= amount;
    }

    public void increaseMaxMP(int amount) {
        this.maxMP += amount;
    }

    public void decreaseMaxMP(int amount) {
        this.maxMP -= amount;
    }

    public void increaseMP(int amount) {
        this.MP += amount;
    }

    public void decreaseMP(int amount) {

        this.MP -= amount;
    }


    public void updateMonsterField() {
        boolean shouldUpdat = true;
        while (shouldUpdat) {
            shouldUpdat = false;
            Monster deadMonster = null;
            for (Monster monster : this.getMonsterField().getCards()) {
                if (monster.getHP() <= 0) {
                    shouldUpdat = true;
                    deadMonster = monster;
                    break;
                }
            }
            if (deadMonster != null) {
                this.moveCardFromFirstToSecond(deadMonster, this.monsterField, this.graveYard);
                this.getPlayFieldRoot().getMonsterFieldView().getChildren().remove(deadMonster.getCardImage());
                this.getBattleRoot().getGraveYardView().removeCard(deadMonster);
                deadMonster.setHP(0);
            }
        }
    }

    public void doAutomaticAttackForAllMonsters() {
        for (int i = this.getMonsterField().getCards().size() - 1; i >= 0; i--) {
            if (this.getMonsterField().getOneCard(i).canAttack()) {
                this.getMonsterField().getOneCard(i).automaticAttack();
            }
        }
    }

    public void doAutomaticSpellForAllMonsters() {
        for (int i = this.getMonsterField().getCards().size() - 1; i >= 0; i--) {
            if (this.getMonsterField().getOneCard(i).canSpell() && this.getMonsterField().getOneCard(i) instanceof HasSpell) {
                ((HasSpell) this.getMonsterField().getOneCard(i)).spell();
            }
        }
    }

    public void AutomaticMoveCardToField() {
        boolean canEnterCard = true;
        while (canEnterCard) {
            canEnterCard = false;
            for (int i = this.hand.getCards().size() - 1; i >= 0; i--) {
                if (this.hand.getOneCard(i).getMP() <= this.getMP()) {
                    canEnterCard = true;
                    if (this.hand.getOneCard(i) instanceof Monster) {
                        this.decreaseMP(this.hand.getOneCard(i).getMP());
                        System.out.println(this.getName() + " entered " + this.hand.getOneCard(i).getName() + " to field");
                        this.monsterField.add((Monster) this.hand.getOneCard(i));
                        this.hand.remove(i);
                    } else if (this.hand.getOneCard(i) instanceof Instant) {
                        this.decreaseMP(this.hand.getOneCard(i).getMP());
                        HasAutomaticSpell instant = (HasAutomaticSpell) this.hand.getOneCard(i);
                        instant.automaticSpell();
                        this.graveYard.add(this.hand.getOneCard(i));
                        this.hand.remove(i);
                    } else {     // card is Aura or Continues
                        this.decreaseMP(this.hand.getOneCard(i).getMP());
                        System.out.println(this.getName() + " entered " + hand.getOneCard(i).getName() + " to field");
                        this.spellField.add((Spell) this.hand.getOneCard(i));
                        this.hand.remove(i);
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return (name + ",HP=" + HP);
    }

    public Player getCopy() {
        Player answer = new Player(this.getName(), this.image, this.stage);
        this.getInventory().getCopyOfObjectsThatAreInThisInventoryTo(answer.inventory, answer);
        this.getMonsterField().getCopyOfMonsterThatAreInThisField(answer.monsterField, answer);
        this.getSpellField().getCopyOfSpellThatAreInThisField(answer.spellField, answer);
        this.getGraveYard().getCopyOfCardsThatAreInThisField(answer.graveYard, answer);
        this.getHand().getCopyOfCardsThatAreInThisField(answer.hand, answer);
        this.getDeck().getCopyOfCardsThatAreInThisField(answer.deck, answer);
//        this.getAmuletInventory().getCopyOfAmuletsThatAreInThisField(answer.amuletInventory, answer);
//        this.getItemInventory().getCopyOfItemsThatAreInThisField(answer.itemInventory, answer);
        this.setEnemy(answer.enemy);
        if (this.equipedAmulet != null)
            answer.setEquipedAmulet(this.equipedAmulet.getCopy(answer));
        return answer;
    }

    public void showMonsterFieldForSelecting() {
        System.out.println(" select a target :");
        if (this.getMonsterField().getCards().isEmpty()) {
            System.out.println(this.name + "'s monsterField is empty");
        } else {
            int counter = 0;
            for (Monster monster : this.getMonsterField().getCards()) {
                System.out.println(counter + "." + monster);
                counter++;
            }
        }
    }

    public void showPlayreAndMonsterFieldForSelecting() {
        showMonsterFieldForSelecting();
        System.out.println("11." + "Player(" + this.name + ")");
    }

    public void showMonsterFieldAndSpellFieldForSelecting() {
        showMonsterFieldForSelecting();
        int counter = -1;
        for (Spell spell : this.getSpellField().getCards()) {
            System.out.println(counter + "." + spell);
            counter--;
        }
    }
}

class PlayerImage extends StackPane {
    PlayerRectangle rectangle;
    PlayerImageView imageView;

    class PlayerImageView extends ImageView {
        Player player;

        PlayerImageView(Image image, Player player) {
            super(image);
            this.player = player;
        }

        public Player getPlayer() {
            return player;
        }
    }

    class PlayerRectangle extends Rectangle {
        Player player;

        PlayerRectangle(Integer width, Integer height, Player player) {
            super(width, height);
            this.player = player;
        }

        public Player getPlayer() {
            return player;
        }
    }

    PlayerImage(Integer rectangleWidth, Integer rectangleHeight, Image image, Player player) {
        this.imageView = new PlayerImageView(image, player);
        this.rectangle = new PlayerRectangle(rectangleWidth, rectangleHeight, player);
        this.getChildren().addAll(this.rectangle, this.imageView);
    }

    public PlayerImageView getImageView() {
        return imageView;
    }

    public PlayerRectangle getRectangle() {
        return rectangle;
    }
}
