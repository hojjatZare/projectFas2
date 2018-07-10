import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Model {
    Player me;
    Player GoblinChieftain;
    Player OgreWarlord;
    Player VampireLord;
    Player Lucifer;

    public Player getGoblinChieftain() {
        return GoblinChieftain;
    }

    public Player getLucifer() {
        return Lucifer;
    }

    public Player getMe() {
        return me;
    }

    public Player getOgreWarlord() {
        return OgreWarlord;
    }

    public Player getVampireLord() {
        return VampireLord;
    }

    public Model(Stage stage){
        me = new Player("You",new Image("icons\\tree1.png"),stage);
//        System.out.println("from now please input all orders in integer but if string input needed we express\n");
        for (int i = 0; i < 2; i++) {
            me.hand.add(new NormalMonster("Elven Ranger", 1, 300, 400, "Normal", "Elven Monster", false, false, me, 0));
            me.hand.add(new NormalMonster("Elven Hunter", 3, 800, 600, "Normal", "Elven Monster", false, false, me, 0));
            me.hand.add(new NormalMonster("Lesser Whelp", 1, 500, 300, "Normal", "DragonBreed Monster", false, false, me, 0));
            me.deck.add(new NormalMonster("Dragonling", 3, 700, 700, "Normal", "DragonBreed Monster", false, false, me, 0));
            me.deck.add(new NormalMonster("Murloc Crawler", 1, 200, 500, "Normal", "Atlantian Monster", false, false, me, 0));
            me.deck.add(new NormalMonster("Murloc Warrior", 2, 600, 600, "Normal", "Atlantian Monster", false, false, me, 0));

        }
        me.deck.add(new NormalMonster("Elven Guardsman", 5, 1500, 500, "Normal", "Elven Monster", false, true, me, 0));
        me.deck.add(new NormalMonster("Elven Assassin", 5, 800, 1200, "Normal", "Elven Monster", true, false, me, 0));
        me.deck.add(new SpellCaster("Elven Druid", 5, 1200, 600, "Spell Caster", "Elven Monster", false, false, me, new ElvenDruid(), 0));
        me.deck.add(new NormalMonster("Armored Dragon", 5, 2000, 400, "Normal", "DragonBreed Monster", false, true, me, 0));
        me.deck.add(new NormalMonster("Yellow Drake", 5, 800, 1000, "Normal", "DragonBreed Monster", true, false, me, 0));
        me.deck.add(new SpellCaster("Blue Dragon", 5, 800, 1200, "Spell Caster", "DragonBreed Monster", false, false, me, new BlueDragon(), 0));
        me.deck.add(new NormalMonster("Giant Crab", 4, 1500, 300, "Normal", "Atlantian Monster", false, true, me, 0));
        me.deck.add(new NormalMonster("Shark Man", 5, 900, 1200, "Normal", "Atlantian Monster", true, false, me, 0));
        me.deck.add(new SpellCaster("Naga Siren", 6, 1200, 600, "Spell Caster", "Atlantian Monster", false, false, me, new NagaSiren(), 0));

        //////////////   myEnemy player {


        GoblinChieftain = new Player("Goblin Chieftain",new Image("icons\\tree1.png"),stage);
        for (int i = 1; i <= 10; i++) {
            GoblinChieftain.deck.add(new NormalMonster("Goblin Smuggler", 2, 600, 400, "Normal", "Demonic", false, false, GoblinChieftain, 0));
        }
        for (int i = 1; i <= 5; i++) {
            GoblinChieftain.deck.add(new SpellCaster("Goblin Shaman", 4, 1000, 700, "Spell Caster", "Demonic", false, false, GoblinChieftain, new GoblinShaman(), 0));
        }
        for (int i = 1; i <= 5; i++) {
            GoblinChieftain.deck.add(new Instant(3, "Throwing Knives", "Instant", new ThrowingKnives(), GoblinChieftain, 0));
        }

        /////////////////   }


        OgreWarlord = new Player("Ogre Warlord",new Image("icons\\tree1.png"),stage);
        for (int i = 0; i < 6; i++) {
            OgreWarlord. deck.add(new NormalMonster("Ogre Warrior", 3, 800, 500, "Normal", "Ogre Monster", false, false, OgreWarlord, 0));
        }
        for (int i = 0; i < 4; i++) {
            OgreWarlord.deck.add(new NormalMonster("Ogre Frontliner", 5, 1800, 600, "Normal", "Ogre Monster", false, true, OgreWarlord, 0));
        }
        for (int i = 0; i < 2; i++) {
            OgreWarlord.deck.add(new SpellCaster("Ogre Magi", 5, 1500, 800, "Spell Caster", "Ogre Monster", false, true, OgreWarlord, new OgreMagi(), 0));
        }
        OgreWarlord.deck.add(new General("Ogre Warchief", 7, 2500, 1500, "General", "Ogre Monster", false, false, OgreWarlord, new OgreWarchief(), 0));
        for (int i = 0; i < 3; i++) {
            OgreWarlord.deck.add(new Instant(3, "Throwing Knives", "Instant", new ThrowingKnives(), OgreWarlord, 0));
        }
        for (int i = 0; i < 3; i++) {
            OgreWarlord. deck.add(new Instant(3, "First Aid Kit", "Instant", new FirstAidKit(), OgreWarlord, 0));
        }
        OgreWarlord.deck.add(new Continuous(4, "Poisonous Cauldron", "Continuous", new PoisonousCauldron(), OgreWarlord, 0));

////////////////
        VampireLord = new Player("Vampire Lord",new Image("icons\\tree1.png"),stage);

        for (int i = 0; i < 4; i++) {
            VampireLord. deck.add(new NormalMonster("Undead", 1, 200, 400, "Normal", "Vampiric Monster", false, false, VampireLord, 0));
        }
        for (int i = 0; i < 3; i++) {
            VampireLord. deck.add(new NormalMonster("Giant Bat", 3, 500, 900, "Normal", "Vampiric Monster", true, false, VampireLord, 0));
        }
        for (int i = 0; i < 3; i++) {
            VampireLord. deck.add(new NormalMonster("Stout Undead", 4, 1200, 600, "Normal", "Vampiric Monster", false, true, VampireLord, 0));
        }
        for (int i = 0; i < 2; i++) {
            VampireLord. deck.add(new SpellCaster("Undead Mage", 6, 800, 1000, "Spell Caster", "Vampiric Monster", false, false, VampireLord, new UndeadMage(), 0));
        }
        VampireLord. deck.add(new SpellCaster("Vampire Acolyte", 7, 1500, 1500, "Spell Caster", "Vampiric Monster", true, false, VampireLord, new VampireAcolyte(), 0));
        VampireLord.deck.add(new General("Vampire Prince", 9, 2000, 2200, "General", "Vampiric Monster", false, false, VampireLord, new VampirePrince(), 0));
        for (int i = 0; i < 3; i++) {
            VampireLord. deck.add(new Instant(4, "Blood Feast", "Instant", new BloodFeast(), VampireLord, 0));
        }
        for (int i = 0; i < 2; i++) {
            VampireLord. deck.add(new Instant(3, "First Aid Kit", "Instant", new FirstAidKit(), VampireLord, 0));
        }
        VampireLord. deck.add(new Aura(6, "War Drum", "Aura", new WarDrum(), VampireLord, 0));
        VampireLord. deck.add(new Continuous(4, "Poisonous Cauldron", "Continuous", new PoisonousCauldron(), VampireLord, 0));
        VampireLord.deck.add(new Continuous(5, "Healing Ward", "Continuous", new HealingWard(), VampireLord, 0));
        VampireLord. deck.add(new Instant(7, "Greater Purge", "Instant", new GreaterPurge(), VampireLord, 0));

        //////////////////
        Lucifer = new Player("Lucifer",new Image("icons\\tree1.png"),stage);
        for (int i = 0; i < 4; i++) {
            Lucifer.deck.add(new NormalMonster("Imp", 2, 300, 500, "Normall", "Demonic", false, false, Lucifer, 0));
        }
        for (int i = 0; i < 3; i++) {
            Lucifer. deck.add(new NormalMonster("Shade", 3, 500, 800, "Normal", "Demonic", false, false, Lucifer, 0));
        }
        for (int i = 0; i < 3; i++) {
            Lucifer. deck.add(new NormalMonster("Living Armor", 5, 1500, 400, "Normal", "Demonic", false, true, Lucifer, 0));
        }
        for (int i = 0; i < 2; i++) {
            Lucifer. deck.add(new NormalMonster("Hellhound", 5, 800, 1000, "Normall", "Demonic", true, false, Lucifer, 0));
        }
        for (int i = 0; i < 2; i++) {
            Lucifer. deck.add(new SpellCaster("Evil Eye", 6, 400, 400, "SpellCaster", "Demonic", false, false, Lucifer, new EvilEye(), 0));
        }
        for (int i = 0; i < 2; i++) {
            Lucifer.deck.add(new SpellCaster("Necromancer", 7, 1200, 1500, "SpellCaster", "Demonic", true, false, Lucifer, new Necromancer(), 0));
        }
        Lucifer. deck.add(new General("Dark Knight", 8, 2500, 2500, "General", "Demonic", false, false, Lucifer, new DarkKnight(), 0));
        Lucifer.deck.add(new Hero("Cerberus Gatekeeper", 10, 3000, 2000, "Hero", "Demonic", true, false, Lucifer, new GatekeeperOfHell(), 0));
        for (int i = 0; i < 3; i++) {
            Lucifer.deck.add(new Instant(4, "Reaper's Scythe", "Instant", new ReapersScythe(), Lucifer, 0));
        }
        for (int i = 0; i < 3; i++) {
            Lucifer. deck.add(new Instant(3, "Reaper's Scythe", "Instant", new FirstAidKit(), Lucifer, 0));
        }
        for (int i = 0; i < 2; i++) {
            Lucifer. deck.add(new Continuous(5, "Healing Ward", "Continuous", new HealingWard(), Lucifer, 0));
        }
        Lucifer. deck.add(new Instant(6, "Strategic Retreat", "Instant", new StrategicRetreat(), Lucifer, 0));
        Lucifer.deck.add(new Aura(6, "War Drum", "Aura", new WarDrum(), Lucifer, 0));
        Lucifer. deck.add(new Continuous(8, "Meteor Shower", "Continuous", new MeteorShower(), Lucifer, 0));
        Lucifer. deck.add(new Instant(9, "Magic Seal", "Continuous", new MagicSeal(), Lucifer, 0));
        Lucifer.setEquipedAmulet(new Amulet("Demon King’s Crown", 0, Lucifer, new DemonKingsCrown()));
        me.setEnemy(GoblinChieftain);
//        View view = new View(stage,this,me);
//        stage.show();
    }
}
