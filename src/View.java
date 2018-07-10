import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View {
    static Integer SCREEN_SIZE_X = 1360;
    static Integer SCREEN_SIZE_Y = 760;
    Model model;
    Stage stage;
    BattleRoot battleRoot;
    Scene playingScene;
//    PlayFieldRoot playFieldRoot;
//    PlayFieldRoot enemysPlayFieldRoot;
//    TextFieldRoot textFieldRoot;
//    NoneSpellerMonsterCommand noneSpellerMonsterCommand;
//    SpellerMonsterCommand comandFieldClickOnSpellerMonster;
//    HandCommand handCommand;
//    ItemsCommand itemsCommand;
//    GraveYardView graveYardView;


    View(Stage stage, Model model,Player player) {
        this.model = model;
        this.stage = stage;
        battleRoot = new BattleRoot(SCREEN_SIZE_X, SCREEN_SIZE_Y,player);
        playingScene = new Scene(battleRoot);
        this.stage.setScene(this.playingScene);
//        battleRoot.getChildren().addAll(textFieldRoot, enemysPlayFieldRoot, playFieldRoot, handCommand);
    }
}

class BattleRoot extends Group {
    private Integer screenWidth;
    private Integer screenHeight;
    Player player;
    TextFieldRoot textFieldRoot;
    PlayFieldRoot playFieldRoot;
    PlayFieldRoot enemysPlayFieldRoot;
    GraveYardView myGraveYardView;
    HandCommand handCommand;
    ItemsCommand itemsCommand;
    SpellerMonsterCommand spellerMonsterCommand;
    NoneSpellerMonsterCommand noneSpellerMonsterCommand;

    BattleRoot(Integer screenWidth, Integer screenHeight, Player player) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.player = player;
        player.setBattle(this);
//        this.enemy = enemy;
        this.textFieldRoot = new TextFieldRoot(screenWidth, screenHeight);
        playFieldRoot = player.getPlayFieldRoot();
        playFieldRoot.setTranslateX(this.screenWidth / 3);
        playFieldRoot.setTranslateY(this.screenHeight / 2);
        enemysPlayFieldRoot = player.getEnemy().getPlayFieldRoot();
        enemysPlayFieldRoot.setTranslateX(this.screenWidth / 3);
        this.handCommand = new HandCommand(screenWidth, screenHeight, player);
        this.itemsCommand = new ItemsCommand(screenWidth, screenHeight, player);
        this.noneSpellerMonsterCommand = new SpellerMonsterCommand(screenWidth, screenHeight, player);
        this.spellerMonsterCommand = new SpellerMonsterCommand(screenWidth, screenHeight, player);
        myGraveYardView = new GraveYardView(screenWidth, screenHeight, player);
//        this.getChildren().addAll(textFieldRoot, enemysPlayFieldRoot, playFieldRoot, handCommand);
        for (Monster monster : this.player.getMonsterField().getCards()) {
            this.playFieldRoot.addMonsterCard(monster);
        }

        for (Spell spell : this.player.getSpellField().getCards()) {
            this.playFieldRoot.addSpellCard(spell);
        }

        for (Card card : this.player.getHand().getCards()) {
            this.handCommand.addCard(card);
        }

        for (Monster monster : this.player.getEnemy().getMonsterField().getCards()) {
            this.enemysPlayFieldRoot.addMonsterCard(monster);
        }

        for (Spell spell : this.player.getEnemy().getSpellField().getCards()) {
            this.enemysPlayFieldRoot.addSpellCard(spell);
        }
        for (Item item : this.player.getInventory().getItems()) {
            this.itemsCommand.addItem(item);
        }
        Button button = new Button("aaa");
//        Button button1 = new Button("dddd");
//        Button button2 = new Button("ffff");
//        handCommand.getChildren().add(button);
//        playFieldRoot.getChildren().addAll(button1);
//        enemysPlayFieldRoot.getChildren().addAll(button2);
        textFieldRoot.getChildren().addAll(button);
        this.getChildren().addAll(this.handCommand,this.playFieldRoot,this.enemysPlayFieldRoot,this.textFieldRoot);
    }

    public Integer getScreenHeight() {
        return screenHeight;
    }

    public Integer getScreenWidth() {
        return screenWidth;
    }

    //    public NoneSpellerMonsterCommand getNoneSpellerMonsterCommand() {
//        return noneSpellerMonsterCommand;
//    }

    public void setSpellerMonsterCommandOnRoot() {
        this.getChildren().removeAll(handCommand, itemsCommand, noneSpellerMonsterCommand);
        this.getChildren().add(this.spellerMonsterCommand);
    }

    public GraveYardView getGraveYardView() {
        return myGraveYardView;
    }

    public void setNoneSpellerMonsterCommandOnRoot() {
        this.getChildren().removeAll(handCommand, itemsCommand, spellerMonsterCommand);
        this.getChildren().add(this.noneSpellerMonsterCommand);
    }

    public void setItemsCommandOnCommandRoot() {
        this.getChildren().removeAll(handCommand, spellerMonsterCommand, noneSpellerMonsterCommand);
        this.getChildren().add(itemsCommand);
    }

    public void setHandCommandOnCommandRoot() {
        this.getChildren().removeAll(itemsCommand, spellerMonsterCommand, noneSpellerMonsterCommand);
        this.getChildren().add(handCommand);
    }

    //    public SpellerMonsterCommand getSpellerMonsterCommand() {
//        return spellerMonsterCommand;
//    }
//
//    public HandCommand getHandCommand() {
//        return handCommand;
//    }
//
//    public ItemsCommand getItemsCommand() {
//        return itemsCommand;
//    }
//
    public TextFieldRoot getTextFieldRoot() {
        return textFieldRoot;
    }


}

class PlayFieldRoot extends Group {
    Integer screenWidth;
    Integer screenHeight;
    HBox monsterFieldView = new HBox();
    HBox spellFieldView = new HBox();
    HBox playerImageAndGraveYard = new HBox();
    Button graveYardButton = new Button("graveYard");
    Label MPlabel = new Label();
    Label HPlabel = new Label();
    Label deckCards = new Label();


    PlayFieldRoot(Integer screenWide, Integer screenHeight, Integer X, Integer Y,Player player) {
        this.screenWidth = screenWide;
        this.screenHeight = screenHeight;
        this.setTranslateX(X);
        this.setTranslateY(Y);


        monsterFieldView.setTranslateY(screenHeight / 48);
        monsterFieldView.setTranslateX(screenWide / 108);
        monsterFieldView.setSpacing(screenWide / 108);


        spellFieldView.setTranslateY((screenHeight * 3) / 16);
        spellFieldView.setTranslateX(screenWide / 48);
        spellFieldView.setSpacing(screenWide / 48);


        playerImageAndGraveYard.setTranslateY((screenHeight * 17) / 48);
        playerImageAndGraveYard.setAlignment(Pos.CENTER);
        playerImageAndGraveYard.getChildren().addAll(player.getPlayerImage());
    }

    PlayFieldRoot(Integer screenWide, Integer screenHeight,Player player) {
        this(screenWide, screenHeight, 0, 0,player);
    }

    public void addMonsterCard(Monster monster) {
        this.monsterFieldView.getChildren().add(monster.getCardImage());
    }

    public void addSpellCard(Spell spell) {
        this.spellFieldView.getChildren().add(spell.getCardImage());
    }

    public void setPlayerImage(ImageView imageView) {
        imageView.setTranslateX(screenWidth / 8);
        playerImageAndGraveYard.getChildren().add(imageView);
    }

    public HBox getMonsterFieldView() {
        return monsterFieldView;
    }

    public HBox getSpellFieldView() {
        return spellFieldView;
    }

    public HBox getPlayerImageAndGraveYard() {
        return playerImageAndGraveYard;
    }
}

//class EnemysPlayFieldRoot extends PlayFieldRoot {
//
//    EnemysPlayFieldRoot(Integer screenWidth, Integer screenHeight) {
//        super(screenWidth, screenHeight);
//        this.setTranslateY(screenHeight / 2);
//        this.screenWidth = screenWidth;
//        this.screenHeight = screenHeight;
//
//        playerImageAndGraveYard.setTranslateY(screenHeight / 48);
//
//        spellFieldView.setTranslateY((screenHeight * 3) / 16);
//        spellFieldView.setTranslateX(screenWidth / 48);
//        spellFieldView.setSpacing(screenWidth / 48);
//
//        monsterFieldView.setTranslateY((screenHeight * 17) / 48);
//        monsterFieldView.setTranslateX(screenWidth / 108);
//        monsterFieldView.setSpacing(screenWidth / 108);
//    }
//}

class TextFieldRoot extends Group {
    Integer screenWidth;
    Integer screenHeight;
    static Label LABEL = new Label();

    TextFieldRoot(Integer screenWidth, Integer screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.setTranslateX(0);
    }


    public void setText(String string) {
        LABEL.setText(string);
    }

}

abstract class CardGroupView extends Group {
    Integer screenWidth;
    Integer screenHeight;
    Player fieldOwner;
    HBox buttonsBox = new HBox();
    VBox firstCulmn = new VBox();
    VBox secondCulmn = new VBox();
    VBox thiredCulmn = new VBox();
    VBox fourthCulmn = new VBox();

    public void removeCard(Card card){
        firstCulmn.getChildren().remove(card);
        secondCulmn.getChildren().remove(card);
        thiredCulmn.getChildren().remove(card);
        fourthCulmn.getChildren().remove(card);
    }

    CardGroupView(Integer screenWidth, Integer screenHeight, final Player FieldOwner) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.fieldOwner = FieldOwner;
        this.setTranslateX((2 * screenWidth) / 3);
        buttonsBox.setTranslateY((screenHeight * 5) / 6);
        buttonsBox.setSpacing(screenWidth / 200);
        firstCulmn.setSpacing(-screenHeight / 24);
        secondCulmn.setSpacing(-screenHeight / 24);
        thiredCulmn.setSpacing(-screenHeight / 24);
        fourthCulmn.setSpacing(-screenHeight / 24);
        firstCulmn.setTranslateX((1 * screenWidth) / 135);
        secondCulmn.setTranslateX((11 * screenWidth) / 135);
        thiredCulmn.setTranslateX((21 * screenWidth) / 135);
        fourthCulmn.setTranslateX((31 * screenWidth) / 135);
        this.getChildren().addAll(firstCulmn, secondCulmn, thiredCulmn, fourthCulmn, buttonsBox);
    }

    public void addCard(Card card) {
        if (firstCulmn.getChildren().size() < 9) {
            firstCulmn.getChildren().add(card.getCardImage());
        } else if (secondCulmn.getChildren().size() < 9) {
            secondCulmn.getChildren().add(card.getCardImage());
        } else if (thiredCulmn.getChildren().size() < 9) {
            thiredCulmn.getChildren().add(card.getCardImage());
        } else {
            fourthCulmn.getChildren().add(card.getCardImage());
        }
        card.setOnMouseClicked();
    }
}

class GraveYardView extends CardGroupView {
    Button back = new Button("back");

    GraveYardView(Integer screenWidth, Integer screenHeight, Player GraveYardOwner) {
        super(screenWidth, screenHeight, GraveYardOwner);
        this.buttonsBox.getChildren().add(back);
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                fieldOwner.getBattleRoot().setHandCommandOnCommandRoot();
            }
        });
    }
}

class HandCommand extends CardGroupView {
    Button playCardButton = new Button("play Card");
    Button showItemsButton = new Button("show items");
    Button nextTurn = new Button("next turn");

    HandCommand(Integer screenWidth, Integer screenHeight, Player fieldOwner) {
        super(screenWidth, screenHeight, fieldOwner);
        buttonsBox.getChildren().addAll(playCardButton, showItemsButton, nextTurn);
        playCardButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (fieldOwner.getLastCardClicked() != null) {
                    if (fieldOwner.getLastCardClicked().getMP() <= fieldOwner.getMP()) {
                        if (fieldOwner.getLastCardClicked() instanceof Monster) {
                            Monster temp = (Monster) fieldOwner.getLastCardClicked();
                            fieldOwner.moveCardFromFirstToSecond(temp, fieldOwner.getHand(), fieldOwner.getMonsterField());
                            System.out.println(fieldOwner.getName() + " entered the card(" + temp.getName() + ") to monster field");

                        } else {     //  card is spell card
                            if (fieldOwner.getLastCardClicked() instanceof Aura || fieldOwner.getLastCardClicked() instanceof Continuous) {
                                Spell temp = (Spell) fieldOwner.getLastCardClicked();
                                fieldOwner.moveCardFromFirstToSecond(temp, fieldOwner.getHand(), fieldOwner.getSpellField());
                                System.out.println(fieldOwner.getName() + " entered the card(" + temp.getName() + ") to spell field");
                            } else {     // card is InstantSpell
                                Spell temp = (Spell) fieldOwner.getLastCardClicked();
                                temp.spell();
                                fieldOwner.moveCardFromFirstToSecond(temp, fieldOwner.getHand(), fieldOwner.getGraveYard());
                            }
                        }

                        fieldOwner.updateMonsterField();
                        fieldOwner.getEnemy().updateMonsterField();
                    } else {
                        TextFieldRoot.LABEL.setText("you need more MP");
                    }
                }
            }
        });

        showItemsButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                fieldOwner.getBattleRoot().setItemsCommandOnCommandRoot();
            }
        });
    }
}

class ItemsCommand extends Group {
    Integer screenWidth;
    Integer screenHeight;
    Player itemOwner;
    Button useItemButton = new Button("use item");
    Button backButton = new Button("back");
    VBox firstRow = new VBox();
    VBox secondRow = new VBox();
    VBox thiredRow = new VBox();
    VBox fourthRow = new VBox();

    ItemsCommand(Integer screenWidth, Integer screenHeight, Player itemOwner) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.itemOwner = itemOwner;
        firstRow.setTranslateX((1 * screenWidth) / 135);
        secondRow.setTranslateX((11 * screenWidth) / 135);
        thiredRow.setTranslateX((21 * screenWidth) / 135);
        fourthRow.setTranslateX((31 * screenWidth) / 135);
        this.useItemButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (itemOwner.getLastItemClicked() != null) {
                    itemOwner.getLastItemClicked().action();
                    removeItem(itemOwner.lastItemClicked);
                }
            }
        });
        this.backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                itemOwner.getBattleRoot().setHandCommandOnCommandRoot();
            }
        });
    }

    public void addItem(Item item) {
        if (firstRow.getChildren().size() < 9) {
            firstRow.getChildren().add(item.getItemImage());
        } else if (secondRow.getChildren().size() < 9) {
            secondRow.getChildren().add(item.getItemImage());
        } else if (thiredRow.getChildren().size() < 9) {
            thiredRow.getChildren().add(item.getItemImage());
        } else {
            fourthRow.getChildren().add(item.getItemImage());
        }
    }

    public void removeItem(Item item) {
        firstRow.getChildren().remove(item.getItemImage());
        secondRow.getChildren().remove(item.getItemImage());
        thiredRow.getChildren().remove(item.getItemImage());
        fourthRow.getChildren().remove(item.getItemImage());
    }
}

class NoneSpellerMonsterCommand extends VBox {
    Integer screenWidth;
    Integer screenHeight;
    Player monsterOwner;
    Button attackButton = new Button("attack");
    Button backButton = new Button("back");

    NoneSpellerMonsterCommand(Integer screenWidth, Integer screenHeight, Player monsterOwner) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.monsterOwner = monsterOwner;
        this.setTranslateX((2 * this.screenWidth) / 3);
        this.setAlignment(Pos.TOP_CENTER);
        this.getChildren().add(attackButton);
        this.backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                monsterOwner.getBattleRoot().setHandCommandOnCommandRoot();
            }
        });
        this.attackButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (monsterOwner.getLastCardClicked() != null && Monster.class.isAssignableFrom(monsterOwner.getLastCardClicked().getClass())) {
                    Monster clickedMonster = (Monster) monsterOwner.getLastCardClicked();
                    if (! clickedMonster.isSleep()) {
                        if (clickedMonster.canAttack()) {
                            clickedMonster.attack();
                        } else {
                            TextFieldRoot.LABEL.setText("this card can not attack this turn\n");
                        }
                    } else {
                        TextFieldRoot.LABEL.setText("this card is sleeping\n");
                    }
                }
            }
        });
    }
}

class SpellerMonsterCommand extends NoneSpellerMonsterCommand {
    Button spellButton = new Button("spell");

    SpellerMonsterCommand(Integer screenWidth, Integer screenHeight, Player monsterOwner) {
        super(screenWidth, screenHeight, monsterOwner);
        this.getChildren().add(spellButton);
    }
}