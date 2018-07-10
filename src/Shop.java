/// A Class for implementing Shop part of the game

import java.util.Map;

class Shop extends arrayListOfAllCardsItemsAmulets{

    /// a method for entering card shop

    public String cardShop(Player owner) {
        String output = owner.getGilAmount() + " Gil\n" +
                "Shop List:\n";
        output += listOfAllCards();
        output += "Card Inventory:\n";
        output += listOfInventoryandDeckCards(owner.getInventory(),owner.getDeck());
        return output;
    }

    /// a method for entering item shop

    public String itemShop(Player owner) {
        String output = owner.getGilAmount() + " Gil\n" +
                "Shop List:\n";
        output += listOfAllItems();
        output += "Item Inventory:\n";
        output += listOfItemsInventory(owner.getInventory());
        return output;
    }

    public String amuletShop(Player owner) {
        String output = owner.getGilAmount() + " Gil\n" +
                "Shop List:\n";
        output += listOfAllAmulets();
        output += "\nEquipped Amulet: \"" + owner.getEquipedAmulet() + "\"\n\n";
        output += "Amulet Inventory:\n";
        output += listOfAmuletsInventory(owner.getInventory());
        return output;
    }

    public String editAmulet(Player owner) {
        String output = "Amulets\n";
        output += listOfAmuletsInventory(owner.getInventory());
        if (!(owner.getEquipedAmulet() == null)) {
            output += "Player is equipped with \"" + owner.getEquipedAmulet().getName() + "\"\n";
        }
        return output;
    }

    public void cardInventory(Player owner) {
        System.out.println("Card Inventory:\n" +
                listOfInventoryandDeckCards(owner.getInventory(),owner.getDeck()));
    }

    public void itemInventory(Player owner) {
        System.out.println("Item Inventory:\n" +
                listOfItemsInventory(owner.getInventory()));
    }

    public void amuletInventory(Player owner) {
        System.out.println("Amulet Inventory:\n" +
                listOfAmuletsInventory(owner.getInventory()));
    }

    /// a method for printing list of cards

    public String listOfAllCards() {
        String output = "";
        int counter = 1;
        for (Monster monster: getMonsterCards()) {
            output += counter + ". \"" + monster.getName() + "\" " + monster.getCost() + " - " + monster.getNumbersAbleToBuy() + " available\n";
            counter++;
        }
        for (Spell spell: getSpellCards()) {
            output += counter + ". \"" + spell.getName() + "\" " + spell.getCost() + " - " + spell.getNumbersAbleToBuy() + " available\n";
            counter++;
        }
        return output;
    }

    /// a method for printing list of items

    public String listOfAllItems() {
        String output = "";
        int counter = 1;
        for (Item item: getItems()) {
            output += counter + ". \"" + item.getName() + "\" " + item.getCost() + "\n";
        }
        return output;
    }

    /// a method for printing list of amulets
    public String listOfAllAmulets() {
        String output = "";
        int counter = 1;
        for (Amulet amulet:getAmulets()) {
            output += counter + ". \"" + amulet.getName() + "\" " + amulet.getCost() + " - " + amulet.getNumbersAbleToBuy() + " available\n";
        }
        return output;
    }

    public String listOfInventoryandDeckCards(Inventory inventory, Deck deck) {
        String output= "";
        int counter = 1;
        for (Map.Entry<String,Integer> entry:inventory.getCardsNumber().entrySet()) {
            output += counter + ". " + entry.getValue() + " \"" + entry.getKey() + "\" / " + deck.getNumberOfCard(entry.getKey()) + " on deck\n";
        }
        return output;
    }

    public String listOfItemsInventory(Inventory inventory) {
        String output = "";
        int counter = 1;
        for (Map.Entry<String,Integer> entry:inventory.getItemsNumber().entrySet()) {
            output += counter + ". " + entry.getValue() + " \"" + entry.getKey() + "\"\n";
        }
        return output;
    }

    public String listOfAmuletsInventory(Inventory inventory) {
        String output = "";
        int counter = 1;
        for (Amulet amulet:inventory.getAmulets()) {
            output += counter + ". \"" + amulet.getName() + "\"\n";
        }
        return output;
    }

    public void buyCard(String name, int number, Player owner) {
        System.out.println("in buyCard");
        if (getCostThroughName(name) * number <= owner.getGilAmount()) {
            if (isMonster(name)) {
                if (getOneMonsterCard(name).getNumbersAbleToBuy() < number) {
                    return;
                }
                for (int i = 0 ; i < number ; i++) {
                    owner.getInventory().addCard(getOneMonsterCard(name).getCopy(owner));
                    getOneMonsterCard(name).setNumbersAbleToBuy(getOneMonsterCard(name).getNumbersAbleToBuy() - 1);
                }
                owner.setGilAmount(owner.getGilAmount() - getCostThroughName(name) * number);
            } else {
                if (getOneSpellCard(name).getNumbersAbleToBuy() < number) {
                    return;
                }
                for (int i = 0 ; i < number ; i++) {
                    owner.getInventory().addCard(getOneSpellCard(name).getCopy(owner));
                    getOneSpellCard(name).setNumbersAbleToBuy(getOneSpellCard(name).getNumbersAbleToBuy() - 1);
                }
                owner.setGilAmount(owner.getGilAmount() - getCostThroughName(name) * number);
            }
        } else {
            System.out.println("Not enough Gil!");
            return;
        }
        System.out.println("Successfully bought " + number + " of \"" + name + "\"!");
    }

    public void sellCard(String name, int number, Player owner) {
        if (owner.getInventory().hasEnoughCard(name,number)) {
            owner.setGilAmount(owner.getGilAmount() + getCostThroughName(name) / 2 * number);
            owner.getInventory().removeCard(name,number,owner.getDeck());
            if (isMonster(name)) {
                getOneMonsterCard(name).setNumbersAbleToBuy(getOneMonsterCard(name).getNumbersAbleToBuy() + number);
            } else {
                getOneSpellCard(name).setNumbersAbleToBuy(getOneSpellCard(name).getNumbersAbleToBuy() + number);
            }
            System.out.println("Successfully sold " + number + " of \"" + name + "\"!");
        } else {
            System.out.println("Not enough cards!");
        }
    }

    public void buyItem(String name,int number, Player owner) {
        if (getOneItem(name).getCost() * number <= owner.getGilAmount()) {
            for (int i = 0 ; i < number ; i++) {
                owner.getInventory().addItem(getOneItem(name).getCopy(owner));
            }
            owner.setGilAmount(owner.getGilAmount() - getOneItem(name).getCost() * number);
        } else {
            System.out.println("Not enough Gil!");
            return;
        }
        System.out.println("Successfully bought " + number + " of \"" + name + "\"!");
    }

    public void sellItem(String name, int number ,Player owner) {
        if (owner.getInventory().hasEnoughItems(name,number)) {
            owner.setGilAmount(owner.getGilAmount() + getOneItem(name).getCost() / 2);
            owner.getInventory().removeItem(name,number);
            System.out.println("Successfully sold " + number + " of \"" + name + "\"!");
        } else {
            System.out.println("Not enough items!");
        }
    }

    public void buyAmulet(String name, Player owner) {
        if (getOneAmulet(name).getCost() <= owner.getGilAmount()) {
            if (getOneAmulet(name).getNumbersAbleToBuy() == 0) {
                return;
            }
            owner.getInventory().addAmulet(getOneAmulet(name));
            getOneAmulet(name).setNumbersAbleToBuy(0);
            owner.setGilAmount(owner.getGilAmount() - getOneAmulet(name).getCost());
            System.out.println("Successfully bought \"" + name + "\"!");
        } else {
            System.out.println("Not enough Gil!");
        }
    }

    public void sellAmulet(String name, Player owner) {
        if (owner.getInventory().hasAmulet(name)) {
            owner.setGilAmount(owner.getGilAmount() + getOneAmulet(name).getCost() / 2);
            owner.getInventory().removeAmulet(name);
            getOneAmulet(name).setNumbersAbleToBuy(1);
            System.out.println("Successfully sold \"" + name + "\"!");
        } else {
            System.out.println("Not enough amulets!");
        }
    }

    public void infoCard(String name) {
        if (isMonster(name)) {
            System.out.println(getOneMonsterCard(name).info());
        } else {
            System.out.println(getOneSpellCard(name).info());
        }
    }

    public void infoItem(String name) {
        System.out.println(getOneItem(name).info());
    }

    public void infoAmulet(String name) {
        System.out.println(getOneAmulet(name).info());
    }

    public String showDeckAndInventory(Player owner) {
        String output = "";
        output = owner.getDeck().showDeck() + "\n" +
                "Other Cards: \n";
        output += this.listOfInventoryandDeckCards(owner.getInventory(),owner.getDeck());
        return output;
    }

    /// edit deck methods

    public void addCardToDeck(String name, Player owner) {
        if(!owner.getInventory().getOneCardThroughName(name).equals(null)) {
            owner.getDeck().add(owner.getInventory().getOneCardThroughName(name));
            owner.getInventory().removeCard(name, 1, owner.getDeck());
            System.out.println("\"" + name + "\" was added to slot " + owner.getDeck().getCards().size() + ".\n");
        }
    }

    public void removeCardFromDeck(int slotNumber, Player owner) {
        String name = owner.getDeck().getOneCard(slotNumber - 1).getName();
        owner.getInventory().addCard(owner.getDeck().getOneCard(slotNumber - 1));
        owner.getDeck().remove(slotNumber - 1);
        System.out.println("\"" + name + "\" was removed from slot " + slotNumber + ".\n");
    }

    public void equipAmulet(String name, Player owner) {
        if (owner.getInventory().hasAmulet(name)) {
            owner.setEquipedAmulet(owner.getInventory().getOneAmuletThroughName(name));
            owner.getInventory().removeAmulet(name);
            System.out.println("\"" + name + "\" was equipped on the player.\n");
        }
    }

    public void removeAmulet(Player owner) {
        if (!owner.getEquipedAmulet().equals(null)) {
            String nameOfEquippedAmulet = owner.getEquipedAmulet().getName();
            owner.getInventory().addAmulet(owner.getEquipedAmulet());
            owner.setEquipedAmulet(null);
            System.out.println("\"" + nameOfEquippedAmulet + "\" was removed!\n");
        }
    }

    public static void editDeckPartComplete(Shop shop, Help help, Player owner) {
        EditDeck:
        while(true) {
            System.out.println(shop.showDeckAndInventory(owner));
            System.out.println(help.editDeckHelp());
            System.out.println("\n!!!please input correct order in string!!!\n");
            Scan.scanner.nextLine();
            String input1 = Scan.scanner.nextLine();
            switch(input1.split(" ")[0]) {
                case "Add":
                    try {
                        shop.addCardToDeck(input1.substring(input1.indexOf("\"") + 1, input1.lastIndexOf("\"")), owner);
                    }
                    catch (Exception e) {
                        System.out.println("Invalid input, try again");
                    }
                    break;
                case "Remove":
                    try {
                        shop.removeCardFromDeck(Integer.parseInt(input1.substring(input1.lastIndexOf("\"") + 2)), owner);
                    }
                    catch (Exception e) {
                        System.out.println("Invalid input, try again");
                    }
                    break;
                case "Info":
                    try {
                        shop.infoCard(input1.substring(input1.indexOf("\"") + 1, input1.lastIndexOf("\"")));
                    }
                    catch (Exception e) {
                        System.out.println("Invalid input, try again");
                    }
                    break;
                case "Exit":
                    break EditDeck;
                default:
                    System.out.println("Invalid input, try again");
                    break;
            }
        }
    }

    public static void editAmuletsPartComplete(Shop shop, Help help, Player owner) {
        EditAmulet:
        while(true) {
            System.out.println(shop.editAmulet(owner));
            System.out.println(help.editAmuletsHelp());
            System.out.println("\n!!!please input correct order in string!!!\n");
            Scan.scanner.nextLine();
            String input1 = Scan.scanner.nextLine();
            switch(input1.split(" ")[0]) {
                case "Equip":
                    try {
                        shop.equipAmulet(input1.substring(input1.indexOf("\"") + 1, input1.lastIndexOf("\"")), owner);
                    }
                    catch (Exception e) {
                        System.out.println("Invalid input, try again");
                    }
                    break;
                case "Remove":
                    shop.removeAmulet(owner);
                    break;
                case "Info":
                    try {
                        shop.infoAmulet(input1.substring(input1.indexOf("\"") + 1, input1.lastIndexOf("\"")));
                    }
                    catch(Exception e) {
                        System.out.println("Invalid input, try again");
                    }
                    break;
                case "Exit":
                    break EditAmulet;
                default:
                    System.out.println("Invalid input, try again");
                    break;
            }
        }
    }
}