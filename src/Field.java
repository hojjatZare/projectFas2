///List of all cards are implemented in a class

import java.util.*;

///List of all cards, Items and Amulets are implemented in a class

class arrayListOfAllCardsItemsAmulets {
    private ArrayList<Monster> MonsterCards = new ArrayList<>();
    private ArrayList<Spell> SpellCards = new ArrayList<Spell>();
    private ArrayList<Item> Items = new ArrayList<Item>();
    private ArrayList<Amulet> Amulets = new ArrayList<Amulet>();

    /// Creating Arraylist of Monster Cards
    {
        /// Adding Elven Monster Cards
        MonsterCards.add(new NormalMonster("Elven Ranger", 1, 300, 400, "Normal", "Elven Monster", false, false, null, 4));
        MonsterCards.add(new NormalMonster("Elven Hunter", 3, 800, 600, "Normal", "Elven Monster", false, false, null, 4));
        MonsterCards.add(new NormalMonster("Elven Guardsman", 5, 1500, 500, "Normal", "Elven Monster", false, true, null, 4));
        MonsterCards.add(new NormalMonster("Elven Assassin", 5, 800, 1200, "Normal", "Elven Monster", true, false, null, 4));
        MonsterCards.add(new SpellCaster("Elven Druid", 5, 1200, 600, "Spell Caster", "Elven Monster", false, false, null, new ElvenDruid(), 2));
        MonsterCards.add(new SpellCaster("Elven Sorceress", 7, 1000, 1000, "Spell Caster", "Elven Monster", true, false, null, new ElvenSorceress(), 2));
        MonsterCards.add(new General("Noble Elf", 9, 2000, 2400, "General", "Elven Monster", false, false, null, new NobleElf(), 2));
        MonsterCards.add(new Hero("Luthien, The High Priestess", 9, 2500, 2000, "Hero", "Elven Monster", false, false, null, new HighPriestess(), 1));
        /// Adding DragonBreed Monster Cards
        MonsterCards.add(new NormalMonster("Lesser Whelp", 1, 500, 300, "Normal", "DragonBreed Monster", false, false, null, 4));
        MonsterCards.add(new NormalMonster("Dragonling", 3, 700, 700, "Normal", "DragonBreed Monster", false, false, null, 4));
        MonsterCards.add(new NormalMonster("Armored Dragon", 5, 2000, 400, "Normal", "DragonBreed Monster", false, true, null, 4));
        MonsterCards.add(new NormalMonster("Yellow Drake", 5, 800, 1000, "Normal", "DragonBreed Monster", true, false, null, 4));
        MonsterCards.add(new SpellCaster("Blue Dragon", 5, 800, 1200, "Spell Caster", "DragonBreed Monster", false, false, null, new BlueDragon(), 2));
        MonsterCards.add(new SpellCaster("Volcanic Dragon", 8, 2500, 700, "Spell Caster", "DragonBreed Monster", false, true, null, new VolcanicDragon(), 2));
        MonsterCards.add(new General("Greater Dragon", 8, 2000, 1800, "General", "DragonBreed Monster", false, false, null, new GreaterDragon(), 2));
        MonsterCards.add(new Hero("Igneel, The Dragon King", 10, 4000, 800, "Hero", "DragonBreed Monster", false, false, null, new DragonKing(), 1));
        /// Adding Atlantian Monster Cards
        MonsterCards.add(new NormalMonster("Murloc Crawler", 1, 200, 500, "Normal", "Atlantian Monster", false, false, null, 4));
        MonsterCards.add(new NormalMonster("Murloc Warrior", 2, 600, 600, "Normal", "Atlantian Monster", false, false, null, 4));
        MonsterCards.add(new NormalMonster("Giant Crab", 4, 1500, 300, "Normal", "Atlantian Monster", false, true, null, 4));
        MonsterCards.add(new NormalMonster("Shark Man", 5, 900, 1200, "Normal", "Atlantian Monster", true, false, null, 4));
        MonsterCards.add(new SpellCaster("Naga Siren", 6, 1200, 600, "Spell Caster", "Atlantian Monster", false, false, null, new NagaSiren(), 2));
        MonsterCards.add(new SpellCaster("Sea Serpent", 7, 1500, 1200, "Spell Caster", "Atlantian Monster", true, false, null, new SeaSerpent(), 2));
        MonsterCards.add(new General("Kraken", 8, 1800, 2200, "General", "Atlantian Monster", false, false, null, new Kraken(), 2));
        MonsterCards.add(new Hero("Neptune, King of Atlantis", 10, 2200, 2500, "Hero", "Atlantian Monster", true, false, null, new KingOfAtlantis(), 1));
        /// Adding Goblin Monster Cards
        MonsterCards.add(new NormalMonster("Goblin Smuggler", 2, 600, 400, "Normal", "Goblin Monster", false, false, null, 0));
        MonsterCards.add(new SpellCaster("Goblin Shaman", 4, 1000, 700, "Spell Caster", "Goblin Monster", false, false, null, new GoblinShaman(), 0));
        /// Adding Ogre Monster Cards
        MonsterCards.add(new NormalMonster("Ogre Warrior", 3, 800, 500, "Normal", "Ogre Monster", false, false, null, 0));
        MonsterCards.add(new NormalMonster("Ogre Frontliner", 5, 1800, 600, "Normal", "Ogre Monster", false, true, null, 0));
        MonsterCards.add(new SpellCaster("Ogre Magi", 5, 1500, 800, "Spell Caster", "Ogre Monster", false, true, null, new OgreMagi(), 0));
        MonsterCards.add(new General("Ogre Warchief", 7, 2500, 1500, "General", "Ogre Monster", false, false, null, new OgreWarchief(), 0));
        /// Adding Vampiric Monster Cards
        MonsterCards.add(new NormalMonster("Undead", 1, 200, 400, "Normal", "Vampiric Monster", false, false, null, 0));
        MonsterCards.add(new NormalMonster("Giant Bat", 3, 500, 900, "Normal", "Vampiric Monster", true, false, null, 0));
        MonsterCards.add(new NormalMonster("Stout Undead", 4, 1200, 600, "Normal", "Vampiric Monster", false, true, null, 0));
        MonsterCards.add(new SpellCaster("Undead Mage", 6, 800, 1000, "Spell Caster", "Vampiric Monster", false, false, null, new UndeadMage(), 0));
        MonsterCards.add(new SpellCaster("Vampire Acolyte", 7, 1500, 1500, "Spell Caster", "Vampiric Monster", true, false, null, new VampireAcolyte(), 0));
        MonsterCards.add(new General("Vampire Prince", 9, 2000, 2200, "General", "Vampiric Monster", false, false, null, new VampirePrince(), 0));
        /// Adding Demonic Monster Cards
        MonsterCards.add(new NormalMonster("Imp", 2, 300, 500, "Normal", "Demonic Monster", false, false, null, 0));
        MonsterCards.add(new NormalMonster("Shade", 3, 500, 800, "Normal", "Demonic Monster", false, false, null, 0));
        MonsterCards.add(new NormalMonster("Living Armor", 5, 1500, 400, "Normal", "Demonic Monster", false, true, null, 0));
        MonsterCards.add(new NormalMonster("HellHound", 5, 800, 1000, "Normal", "Demonic Monster", true, false, null, 0));
        MonsterCards.add(new SpellCaster("Evil Eye", 6, 400, 400, "Spell Caster", "Demonic Monster", false, false, null, new EvilEye(), 0));
        MonsterCards.add(new SpellCaster("Necromancer", 7, 1200, 1500, "Spell Caster", "Demonic Monster", true, false, null, new Necromancer(), 0));
        MonsterCards.add(new General("Dark Knight", 8, 2500, 2500, "General", "Demonic Monster", false, false, null, new DarkKnight(), 0));
        MonsterCards.add(new Hero("Cerberus, Gatekeeper of Hell", 10, 3000, 2000, "Hero", "Demonic Monster", true, false, null, new GatekeeperOfHell(), 0));
    }

    public ArrayList<Monster> getMonsterCards() {
        return MonsterCards;
    }

    public boolean isMonster(String name) {
        for (Monster monster : MonsterCards) {
            if (monster.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public Monster getOneMonsterCard(String name) {
        for (Monster monster : MonsterCards) {
            if (monster.getName().equals(name)) {
                return monster;
            }
        }
        return null;
    }

    ///Creating Arraylist of Spell Cards

    {
        SpellCards.add(new Instant(3, "Throwing Knives", "Instent", new ThrowingKnives(), null, 3));
        SpellCards.add(new Continuous(4, "Poisonous Cauldron", "Continuous", new PoisonousCauldron(), null, 3));
        SpellCards.add(new Instant(3, "First Aid Kit", "Instant", new FirstAidKit(), null, 3));
        SpellCards.add(new Instant(4, "Reaper's Scythe", "Instant", new ReapersScythe(), null, 3));
        SpellCards.add(new Continuous(8, "Meteor Shower", "Continuous", new MeteorShower(), null, 2));
        SpellCards.add(new Aura(6, "Lunar Blessing", "Aura", new LunarBlessing(), null, 2));
        SpellCards.add(new Instant(6, "Strategic Retreat", "Instant", new StrategicRetreat(), null, 2));
        SpellCards.add(new Aura(6, "War Drum", "Aura", new WarDrum(), null, 2));
        SpellCards.add(new Continuous(5, "Healing Ward", "Continuous", new HealingWard(), null, 3));
        SpellCards.add(new Instant(4, "Blood Feast", "Instant", new BloodFeast(), null, 3));
        SpellCards.add(new Instant(6, "Tsunami", "Instant", new Tsunami(), null, 2));
        SpellCards.add(new Aura(7, "Take All You Can", "Aura", new TakeAllYouCan(), null, 2));
        SpellCards.add(new Instant(5, "Arcane Bolt", "Instant", new ArcaneBolt(), null, 3));
        SpellCards.add(new Instant(7, "Greater Purge", "Instant", new GreaterPurge(), null, 2));
        SpellCards.add(new Continuous(9, "Magic Seal", "continuous", new MagicSeal(), null, 2));
    }

    public ArrayList<Spell> getSpellCards() {
        return SpellCards;
    }

    public Spell getOneSpellCard(String name) {
        for (Spell spell : SpellCards) {
            if (spell.getName().equals(name)) {
                return spell;
            }
        }
        return null;
    }

    /// a method for getting the cost of a card(by giving the name of the card)

    public long getCostThroughName(String name) {
        if (isMonster(name)) {
            return getOneMonsterCard(name).getCost();
        } else {
            return getOneSpellCard(name).getCost();
        }
    }

    ///Creating Arraylist of Items

    {
        Items.add(new Item("SmallHPPotion", 1000, null, new SmallHPPotion()));
        Items.add(new Item("MediumHPPotion", 2000, null, new MediumHPPotion()));
        Items.add(new Item("LargeHPPotion", 4000, null, new LargeHPPotion()));
        Items.add(new Item("SmallMPPotion", 1000, null, new SmallMPPotion()));
        Items.add(new Item("MediumMPPotion", 2000, null, new MediumMPPotion()));
        Items.add(new Item("LargeMPPotion", 4000, null, new LargeMPPotion()));
        Items.add(new Item("LesserRestorative", 2000, null, new LesserRestorative()));
        Items.add(new Item("GreaterRestorative", 4000, null, new GreaterRestorative()));

    }

    public ArrayList<Item> getItems() {
        return Items;
    }

    public Item getOneItem(String name) {
        for (Item item : getItems()) {
            if (name.equals(item.getName())) {
                return item;
            }
        }
        return null;
    }

    ///Creating Arraylist of Amulets

    {
        Amulets.add(new Amulet("IronPendant", 2000, null, new IronRing()));
        Amulets.add(new Amulet("GoldPendant", 4000, null, new GoldPendant()));
        Amulets.add(new Amulet("DiamondPendant", 8000, null, new DiamondPendant()));
        Amulets.add(new Amulet("IronRing", 2000, null, new IronRing()));
        Amulets.add(new Amulet("GoldRing", 4000, null, new GoldRing()));
        Amulets.add(new Amulet("DiamondRing", 8000, null, new DiamondRing()));

    }

    public ArrayList<Amulet> getAmulets() {
        return Amulets;
    }

    public Amulet getOneAmulet(String name) {
        for (Amulet amulet : getAmulets()) {
            if (name.equals(amulet.getName())) {
                return amulet;
            }
        }
        return null;
    }
}


abstract class Field {
    private ArrayList<Card> cards = new ArrayList<>(10);

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void add(Card card) {
        if (card != null)
            this.cards.add(card);
    }


    public void remove(Card card) {
        for (int i = this.cards.size() - 1; i >= 0; i--) {
            if (cards.get(i) == card)
                cards.remove(i);
        }
    }

    public void remove(int index) {
        if (cards.size() > index) {
            cards.remove(index);
        }
    }

    public void removeAll() {
        cards.clear();
    }

    public Card getOneCard(int index) {
        if (cards.size() != 0)
            return cards.get(index);
        return null;//// cards may be empty.
    }

    public void getCopyOfCardsThatAreInThisField(Field targetField, Player newOwner) {
        for (Card card : this.getCards()) {
            targetField.add(card.getCopy(newOwner));
        }
    }
}

class Deck extends Field {
    @Override
    public void add(Card card) {
        if (this.getCards().size() <= 30 && card != null) {
            this.getCards().add(card);
        }
    }

    public Card takeCard() {

        if (this.getCards().size() >= 1) {
            Card card = this.getCards().get(0);
            this.getCards().remove(0);
            return card;
        }//////// cards may be empty.
        return null;
    }


    public void shuffle() {
        Collections.shuffle(this.getCards());
    }

/// a method for getting numbers of a specific card in deck

    public int getNumberOfCard(String name) {
        int numberOutput = 0;
        for (Card card : this.getCards()) {
            if (name.equals(card.getName())) {
                numberOutput++;
            }
        }
        return numberOutput;
    }

    public String showDeck() {
        String output = "Deck: \n";
        /// Showing slots with cards
        for (int i = 1; i < this.getCards().size() + 1; i++) {
            output += "Slot " + (i) + ": " + this.getCards().get(i - 1).getName() + "\n";
        }
        /// showing the rest of slots which are empty
        for (int i = this.getCards().size() + 1; i < 31; i++) {
            output += "Slot " + (i) + ": empty\n";
        }
        return output;
    }

    public Card getOneCardThroughName(String name) {
        for (Card card : this.getCards()) {
            if (name.equals(card.getName())) {
                return card;
            }
        }
        return null;
    }

}

class Hand extends Field {
}

class MonsterField {
    private ArrayList<Monster> cards = new ArrayList<Monster>(10);

    public ArrayList<Monster> getCards() {
        return cards;
    }

    public void add(Monster card) {
        if (getCards().size() < 5 && card != null) {
            getCards().add(card);
            if (HasBattleCryAndWill.class.isAssignableFrom(card.getClass()) && card.getOwner() != null) {
                if (card.getOwner().getHP() > 0) {
                    HasBattleCryAndWill monster = (HasBattleCryAndWill) card;
                    monster.battleCry();
                }
            }
        }
    }

    public void remove(Monster card) {
        for (int i = this.cards.size() - 1; i >= 0; i--) {
            if (cards.get(i) == card) {
                cards.remove(i);
                if (HasBattleCryAndWill.class.isAssignableFrom(card.getClass()) && card.getOwner() != null) {
                    if (card.getOwner().getHP() > 0) {
                        HasBattleCryAndWill monster = (HasBattleCryAndWill) card;
                        monster.will();
                    }
                }
            }
        }
    }

//    public void remove(Card card) {
//        Monster monster = (Monster) card;
//        this.remove(monster);
//    }

    public void remove(int index) {
        if (cards.size() > index) {
            cards.remove(index);
        }
    }

    public void removeAll() {
        cards.clear();
    }

    public void printAllCards() {
        for (int i = 0; i < getCards().size(); i++) {
            System.out.println(i + " " + getCards().get(i));
        }
    }

    public Monster getOneCard(int i) {
        if (cards.size() != 0)
            return cards.get(i);
        return null;
    }

    public boolean hasDefenderCard() {
        boolean hasDefenderCard = false;
        for (Monster monster : cards) {
            if (monster.isDefender()) {
                hasDefenderCard = true;
            }
        }
        return hasDefenderCard;
    }

    public void getCopyOfMonsterThatAreInThisField(MonsterField targetField, Player newOwner) {
        for (Monster monster : this.getCards()) {
            targetField.add(monster.getCopy(newOwner));
        }
    }
}


class SpellField {
    private ArrayList<Spell> cards = new ArrayList<Spell>(10);

    public void add(Spell spell) {
        if (this.getCards().size() <= 3 && spell != null) {
            this.getCards().add(spell);
            if (spell instanceof Aura && spell.getOwner() != null) {
                if (spell.getOwner().getHP() > 0)
                    spell.spell();
            }
        }
    }

    public ArrayList<Spell> getCards() {
        return cards;
    }

    public void remove(Spell card) {
        for (int i = this.cards.size() - 1; i >= 0; i--) {
            if (cards.get(i) == card) {
                cards.remove(i);
                if (card instanceof Aura && card.getOwner() != null) {
                    if (card.getOwner().getHP() > 0)
                        ((Aura) card).disSpell();
                }
            }
        }
    }

//    public void remove(Card card) {
//        Spell spell = (Spell) card;
//        this.remove(spell);
//        if (card instanceof Aura) {
//            ((Aura) card).disSpell();
//        }
//    }

    public void remove(int index) {
        if (cards.size() > index) {
            cards.remove(index);
            if (cards.get(index) instanceof Aura) {
                ((Aura) cards.get(index)).disSpell();
            }
        }
    }

    public void printAllCards() {
        for (int i = 0; i < getCards().size(); i++) {
            System.out.println(i + " " + getCards().get(i));
        }
    }

    public Spell getOneCard(int i) {
        if (cards.size() != 0)
            return getCards().get(i);
        return null;
    }

    public void getCopyOfSpellThatAreInThisField(SpellField targetField, Player newOwner) {
        for (Spell card : this.getCards()) {
            targetField.add(card.getCopy(newOwner));
        }
    }
}


class GraveYard extends Field {

    @Override
    public void add(Card card) {
        this.getCards().add(card);
        if (card instanceof Monster) {
//            System.out.println(card.getOwner().getName() + "'s card(" + card.getName() + ") died");
//            card.getOwner().getBattleRoot().get
        }
        if (card instanceof Spell) {
//            System.out.println(card.getOwner().getName() + " s' spellCard(" + card.getName() + ")" + " moved to graveYard");
        }
    }
}

class Inventory {
    ///Arraylist of Monster and Spell Cards
    private ArrayList<Card> Cards = new ArrayList<>();
    private Map<String, Integer> cardsNumber = new HashMap<>();
//    private ArrayList<Integer> CardsNumber = new ArrayList<>();

    ///Arraylist of Items
    private ArrayList<Item> Items = new ArrayList<>();
    private Map<String, Integer> itemsNumbers = new HashMap<>();
//    private ArrayList<Integer> ItemsNumber =  new ArrayList<>();

    ///Arraylist of Amulets
    private ArrayList<Amulet> Amulets = new ArrayList<>();

    ///getter methods

    public ArrayList<Card> getCards() {
        return Cards;
    }

    public void removeItem(Item item) {
        Items.remove(item);
        if (itemsNumbers.get(item.getName()) == 1) {
            itemsNumbers.remove(item.getName());
        } else {
            itemsNumbers.replace(item.getName(), itemsNumbers.get(item.getName()) - 1);
        }
    }

    public Map<String, Integer> getCardsNumber() {
        return cardsNumber;
    }

    public ArrayList<Item> getItems() {
        return Items;
    }

    public Map<String, Integer> getItemsNumber() {
        return itemsNumbers;
    }

    public ArrayList<Amulet> getAmulets() {
        return Amulets;
    }

    ///Method for adding a number of cards

    public void addCard(Card card) {
        this.Cards.add(card);
        if (cardsNumber.containsKey(card.getName())) {

            cardsNumber.replace(card.getName(), cardsNumber.get(card.getName()) + 1);
            return;
        }
        cardsNumber.put(card.getName(), 1);
    }

    ///Method for removing a number of cards (if the numbers are more than existing cards, it will return false)

    public void removeCard(String name, int numbers, Deck deck) {
        if (cardsNumber.get(name) == numbers) {
            if (deck.getNumberOfCard(name) == 0) {
                cardsNumber.remove(name);
            } else {
                cardsNumber.replace(name, 0);
            }
        } else {
            cardsNumber.replace(name, cardsNumber.get(name) - numbers);
        }
        for (int i = 0; i < Cards.size(); i++) {
            if (name.equals(Cards.get(i).getName()) && numbers > 0) {
                Cards.remove(i);
                numbers--;
            }
        }
    }

    public boolean hasEnoughCard(String name, int numbers) {
        return cardsNumber.containsKey(name) && cardsNumber.get(name) >= numbers;
    }

    /// a method for getting a card(through the name of the card)

    public Card getOneCardThroughName(String name) {
        for (Card card : this.getCards()) {
            if (name.equals(card.getName())) {
                return card;
            }
        }
        return null;
    }

    /// method for adding a number of items

    public void addItem(Item item) {
        this.Items.add(item);
        if (itemsNumbers.containsKey(item.getName())) {
            itemsNumbers.replace(item.getName(), itemsNumbers.get(item.getName()) + 1);
            return;
        }
        itemsNumbers.put(item.getName(), 1);
    }

    /// method for removing a number of items

    public void removeItem(String name, int numbers) {
        if (itemsNumbers.get(name) == numbers) {
            itemsNumbers.remove(name);
        } else {
            itemsNumbers.replace(name, itemsNumbers.get(name) - numbers);
        }
        for (int i = 0; i < Items.size(); i++) {
            if (name.equals(Items.get(i).getName()) && numbers > 0) {
                Items.remove(i);
                numbers--;
            }
        }
    }

    public void getCopyOfItemsThatAreInThisField(Inventory targetField, Player newOwner) {
        for (Item item : this.getItems()) {
            targetField.addItem(item.getCopy(newOwner));
        }
    }

    /// method for adding an amulet

    public void addAmulet(Amulet amulet) {
        this.getAmulets().add(amulet);
    }

    public void removeAmulet(String name) {
        for (int i = 0; i < Amulets.size(); i++) {
            if (name.equals(Amulets.get(i).getName())) {
                Amulets.remove(i);
                return;
            }
        }
    }

    public boolean hasEnoughItems(String name, int numbers) {
        return itemsNumbers.containsKey(name) && itemsNumbers.get(name) >= numbers;
    }

    public void getCopyOfAmuletsThatAreInThisField(Inventory targetField, Player newOwner) {
        for (Amulet amulet : this.getAmulets()) {
            targetField.addAmulet(amulet);
            amulet.setOwner(newOwner);
        }
    }

    public Item getOneItemThroughName(String name) {
        for (Item item : this.getItems()) {
            if (name.equals(item.getName())) {
                return item;
            }
        }
        return null;
    }

    public boolean hasAmulet(String name) {
        for (Amulet amulet : getAmulets()) {
            if (name.equals(amulet.getName())) {
                return true;
            }
        }
        return false;
    }

    public Amulet getOneAmuletThroughName(String name) {
        for (Amulet amulet : getAmulets()) {
            if (name.equals(amulet.getName())) {
                return amulet;
            }
        }
        return null;
    }

    public void getCopyOfObjectsThatAreInThisInventoryTo(Inventory newInventory, Player newOwner) {
        for (int i = 0; i < this.getCards().size(); i++) {
            newInventory.getCards().add(this.getCards().get(i).getCopy(newOwner));
//            newInventory.getCards().get(i).setOwner(newOwner);
        }
        for (int i = 0; i < this.getItems().size(); i++) {
            newInventory.getItems().add(this.getItems().get(i).getCopy(newOwner));
//            newInventory.getItems().get(i).setOwner(newOwner);
        }
        for (int i = 0; i < this.getAmulets().size(); i++) {
            newInventory.getAmulets().add(this.getAmulets().get(i).getCopy(newOwner));
//            newInventory.getAmulets().get(i)
        }
        for (Map.Entry<String, Integer> entry : this.getCardsNumber().entrySet()) {
            newInventory.getCardsNumber().put(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, Integer> entry : this.getItemsNumber().entrySet()) {
            newInventory.getItemsNumber().put(entry.getKey(), entry.getValue());
        }
    }

//    public void getCopy(Inventory newInventory) {
//        for (int i = 0 ; i < this.getCards().size() ; i++) {
//            newInventory.getCards().set(i,this.getCards().get(i));
//        }
//        for (int i = 0 ; i < this.getItems().size() ; i++) {
//            newInventory.getItems().set(i,this.getItems().get(i));
//        }
//        for (int i = 0 ; i < this.getAmulets().size() ; i++) {
//            newInventory.getAmulets().set(i,this.getAmulets().get(i));
//        }
//        for (Map.Entry<String,Integer> entry:this.getCardsNumber().entrySet()) {
//            newInventory.getCardsNumber().put(entry.getKey(),entry.getValue());
//        }
//        for (Map.Entry<String,Integer> entry:this.getItemsNumber().entrySet()) {
//            newInventory.getItemsNumber().put(entry.getKey(),entry.getValue());
//        }
//    }

}

//class AmuletInventory {
//    private ArrayList<Amulet> Amulets = new ArrayList<>(2);
//
//    public ArrayList<Amulet> getAmulets() {
//        return Amulets;
//    }
//
//    public void addAmulet(Amulet amulet) {
//        this.Amulets.add(amulet);
//    }
//
//    public void remove(Amulet amulet) {
//        for (int i = this.Amulets.size() - 1; i >= 0; i--) {
//            if (Amulets.get(i) == amulet)
//                Amulets.remove(i);
//        }
//    }
//
//    public void remove(int index) {
//        this.Amulets.remove(index);
//    }
//
//    public void getCopyOfAmuletsThatAreInThisField(AmuletInventory targetField, Player newOwner) {
//        for (Amulet amulet : this.getAmulets()) {
//            targetField.addAmulet(amulet);
//            amulet.setOwner(newOwner);
//        }
//    }
//}
//
//class ItemInventory {
//    private ArrayList<Item> Items = new ArrayList<>(2);
//
//    public void addItem(Item item) {
//        this.Items.add(item);
//    }
//
//    public ArrayList<Item> getItems() {
//        return Items;
//    }
//
//    public void remove(Item item) {
//        for (int i = this.Items.size() - 1; i >= 0; i++) {
//            if (Items.get(i) == item)
//                Items.remove(i);
//        }
//    }
//
//    public void remove(int index) {
//        this.Items.remove(index);
//    }
//
//    public void getCopyOfItemsThatAreInThisField(ItemInventory targetField, Player newOwner) {
//        for (Item item : this.getItems()) {
//            targetField.addItem(item.getCopy(newOwner));
//        }
//    }
//}

class Scan {
    static Scanner scanner = new Scanner(System.in);
}