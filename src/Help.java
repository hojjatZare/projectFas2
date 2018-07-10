/// A Class for Printing Help of Different parts

class Help{

    public String afterFightHelp() {
        return "1. Enter Shop: To enter shop and buy or sell cards and items\n" +
                "2. Edit Inventory: To edit your amulet or deck\n" +
                "3. Next: To go to deck and amulet customization\n";
    }

    public String ShopHelp(long gilAmount) {
        return "Remaining Gil: " + gilAmount + " Gil\n" +
                "1. Card Shop\n" +
                "2. Item Shop\n" +
                "3. Amulet Shop\n" +
                "4. Exit\n";
    }

    public String cardShopHelp(long gilAmount) {
        return gilAmount + " Gil\n" +
                "1. Buy \"Card Name\" - #NumberToBuy: To buy a certain number of a card from shop\n" +
                "2. Sell \"Card Name\" - #NumberToSell: To sell a certain number of a card from\n" +
                "inventory\n" +
                "3. Info \"Card Name\": To get more information about a card\n" +
                "4. Edit Deck: To edit Deck and remove and add cards to it\n" +
                "5. Exit: To return to shop menu\n";
    }

    public String itemShopHelp(long gilAmount) {
        return gilAmount + " Gil\n" +
                "1. Buy \"Item Name\" - #NumberToBuy: To buy an item from the shop\n" +
                "2. Sell \"Item Name\" - #NumberToSell: To sell an item from your item inventory\n" +
                "3. Info \"Item Name\": To view the full information of the item\n" +
                "4. Exit: To exit back to the shop menu\n";
    }

    public String amuletShopHelp(long gilAmount) {
        return gilAmount + " Gil\n" +
                "1. Buy \"Amulet Name\": To buy an amulet from the shop\n" +
                "2. Sell \"Amulet Name\": To sell an amulet from amulet\n" +
                "inventory\n" +
                "3. Info \"Amulet Name\": To get full info on an amulet\n" +
                "4. Edit Amulets: To equip or remove your ’heros amulet\n" +
                "5. Exit: To exit to the shop menu\n";
    }

    public String editDeckHelp() {
        return "1. Add \"Card Name\" #CardSlotNum: To add cards to your deck\n" +
                "2. Remove \"Card Name\" #CardSlotNum: To remove cards from your deck\n" +
                "3. Info \"Card Name\": To get more information about a specific card\n" +
                "4. Exit: To return to the previous section\n";
    }

    public String fight() {
        return "5. Next: To start to fight\n";
    }

    public String editAmuletsHelp() {
        return "1. Equip \"Amulet Name\": To equip the player with an amulet\n" +
                "2. Remove Amulet: To remove the amulet equipped with the player (if the player is\n" +
                "equipped with one)\n" +
                "3. Info \"Amulet Name\": To get more information about a specific amulet\n" +
                "4. Exit: To return to the previous section\n";
    }

    public String editInventoryHelp() {
        return "1. Card Inventory: To view your cards\n" +
                "2. Item Inventory: To view your items\n" +
                "3. Amulet Inventory: To view your amulets\n" +
                "4. Edit Deck: To edit your card deck\n" +
                "5. Edit Amulets: To equip or remove your amulets\n" +
                "6. Exit: To exit to previous menu\n";
    }
}

