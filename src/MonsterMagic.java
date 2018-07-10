abstract class SpellCasterMagic {
    private Monster monster;


    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public Monster getMonster() {
        return monster;
    }

    abstract public void spell();

    abstract public String info();

    abstract public SpellCasterMagic getCopy();
}

abstract class GeneralMagic {
    private Monster monster;

    abstract public void battleCry();

    abstract public void will();

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public Monster getMonster() {
        return monster;
    }

    abstract public GeneralMagic getCopy();

    abstract String info();
}

abstract class HeroMagic {
    private Monster monster;

    abstract public void spell();

    abstract public void battleCry();

    abstract public void will();

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    abstract String info();

    abstract public HeroMagic getCopy();
}

/// Elven Monster Magics

class ElvenDruid extends SpellCasterMagic {

    @Override
    public void spell() {
        this.getMonster().getOwner().showMonsterFieldForSelecting();
        int targetNumber = Scan.scanner.nextInt();
        Monster target = this.getMonster().getOwner().getMonsterField().getOneCard(targetNumber);
        target.increaseAP(300);
        target.increaseHP(500);
    }

    public String info() {
        return ("[Spell Details] (Rejuvenation: Increase a selected friendly monster card’s HP by 500 and AP by 300)");
    }

    @Override
    public ElvenDruid getCopy() {
        return new ElvenDruid();
    }
}

class ElvenSorceress extends SpellCasterMagic {


    public void spell() {
        MonsterField monsterFieldOfEnemy = this.getMonster().getOwner().getEnemy().getMonsterField();
        for (Monster monster : monsterFieldOfEnemy.getCards()) {
            monster.decreaseHP(400);
        }
        if (!this.getMonster().getOwner().getEnemy().getSpellField().getCards().isEmpty()) {
            this.getMonster().getOwner().getEnemy().moveCardFromFirstToSecond(this.getMonster().getOwner().getEnemy().getSpellField().getOneCard(0),
                    this.getMonster().getOwner().getEnemy().getSpellField(), this.getMonster().getOwner().getEnemy().getGraveYard());
        }
    }

    public String info() {
        return ("[Spell Details] (Arcane Explosion: Deal 400 damage to all enemy monster cards and remove a random spell card from enemy field and move it to graveyard.)\n");
    }

    @Override
    public ElvenSorceress getCopy() {
        return new ElvenSorceress();
    }
}

class NobleElf extends GeneralMagic {

    public void battleCry() {
        for (int i = this.getMonster().getOwner().getEnemy().getSpellField().getCards().size() - 1; i >= 0; i++) {
            this.getMonster().getOwner().getEnemy().moveCardFromFirstToSecond(this.getMonster().getOwner().getEnemy().getSpellField()
                    .getOneCard(i), this.getMonster().getOwner().getEnemy().getSpellField().getOneCard(i).getOwner()
                    .getSpellField(), this.getMonster().getOwner().getEnemy().getSpellField().getOneCard(i).getOwner().getHand());

        }
    }

    public void will() {
        this.getMonster().getOwner().getMonsterField().getOneCard(0).increaseHP(800);
        this.getMonster().getOwner().getMonsterField().getOneCard(0).increaseAP(600);
    }

    @Override
    public GeneralMagic getCopy() {
        return new NobleElf();
    }

    public String info() {
        return ("[BattleCry Details] (Purge: Remove all enemy spell cards on the field and move them to hand)\n" +
                "[Will Details] (Increase a random friendly Elven monster card on the field’s HP by 800 and AP by 600)");
    }
}

class HighPriestess extends HeroMagic {

    public void spell() {
        this.getMonster().getOwner().showPlayreAndMonsterFieldForSelecting();
        int targetNumber = Scan.scanner.nextInt();
        if (targetNumber == 11) {
            this.getMonster().getOwner().increaseHP(2500);
        } else {
            Monster target = this.getMonster().getOwner().getMonsterField().getOneCard(targetNumber);
            target.increaseHP(2500);
        }
    }

    public void battleCry() {
        for (int i = 1; i <= 2; i++) {
            if (!this.getMonster().getOwner().getGraveYard().getCards().isEmpty()) {
                if (Monster.class.isAssignableFrom(this.getMonster().getOwner().getGraveYard().getOneCard(0).getClass())) {
                    Monster monster = (Monster) this.getMonster().getOwner().getGraveYard().getOneCard(0);
                    monster.reborn();
                }
                this.getMonster().getOwner().moveCardFromFirstToSecond(this.getMonster().getOwner().getGraveYard().getOneCard(0)
                        , this.getMonster().getOwner().getGraveYard(), this.getMonster().getOwner().getHand());
            }
        }
    }

    public void will() {
        for (Monster monster : this.getMonster().getOwner().getMonsterField().getCards()) {
            monster.increaseAP(200);
            monster.increaseHP(500);
        }
        this.getMonster().getOwner().increaseHP(500);
    }

    public String info() {
        return ("[Spell Details] (Divine Blessing: Increase HP of a friendly monster card or player by 2500)\n" +
                "[BattleCry Details] (Revive Allies: move two random cards from your graveyard to hand)\n" +
                "[Will Details] (Burst of Light: Increase HP of all friendly monster cards and player by 500 and increase AP of all friendly monster cards by 200)");
    }

    @Override
    public HighPriestess getCopy() {
        return new HighPriestess();
    }
}

//// DragonBreed Monsters{

class BlueDragon extends SpellCasterMagic {

    public void spell() {
        this.getMonster().getOwner().getEnemy().showMonsterFieldForSelecting();
        if (!this.getMonster().getOwner().getEnemy().getMonsterField().getCards().isEmpty()) {
            int targetNumber = Scan.scanner.nextInt();
            Monster target = this.getMonster().getOwner().getEnemy().getMonsterField().getOneCard(targetNumber);


            this.getMonster().getOwner().getEnemy().moveCardFromFirstToSecond(target
                    , this.getMonster().getOwner().getEnemy().getMonsterField(), this.getMonster().getOwner().getGraveYard());
        }
    }

    public String info() {
        return ("[Spell Details] (Magical Fire: Move an enemy monster card from field to graveyard)");
    }

    @Override
    public BlueDragon getCopy() {
        return new BlueDragon();
    }
}

class VolcanicDragon extends SpellCasterMagic {

    @Override
    public void spell() {
        this.getMonster().getOwner().getEnemy().showMonsterFieldForSelecting();
        if (!this.getMonster().getOwner().getEnemy().getMonsterField().getCards().isEmpty()) {
            int targetNumber = Scan.scanner.nextInt();
            Monster target = this.getMonster().getOwner().getEnemy().getMonsterField().getOneCard(targetNumber);

            target.decreaseHP(500);
            target.decreaseAP(500);
        }
    }

    public String info() {
        return ("[Spell Details] (Lava Spit: Deal 500 damage to an enemy monster card and reduce its AP by 500)");
    }

    @Override
    public VolcanicDragon getCopy() {
        return new VolcanicDragon();
    }
}

class GreaterDragon extends GeneralMagic {

    public void battleCry() {
        if (!this.getMonster().getOwner().getEnemy().getMonsterField().getCards().isEmpty()) {
            this.getMonster().getOwner().getEnemy().moveCardFromFirstToSecond(this.getMonster().getOwner().getEnemy().getMonsterField().getOneCard(0),
                    this.getMonster().getOwner().getEnemy().getMonsterField(), this.getMonster().getOwner().getEnemy().getGraveYard());
        }
    }

    public void will() {
        for (int i = 0; i < 2; i++) {
            this.getMonster().getOwner().moveNextCardFromDeckToHand();
        }
    }

    @Override
    public GreaterDragon getCopy() {
        return new GreaterDragon();
    }

    public String info() {
        return ("[BattleCry Details] (Devour: Send a random enemy monster card from field to graveyard)\n" +
                "[Will Details] (Dragon’s Call: draw two cards from deck to hand)");
    }
}

class DragonKing extends HeroMagic {

    public void spell() {
        for (Monster monster : this.getMonster().getOwner().getEnemy().getMonsterField().getCards()) {
            monster.decreaseHP(600);
        }
    }

    public void battleCry() {
        for (int i = this.getMonster().getOwner().getMonsterField().getCards().size() - 1; i >= 0; i--) {
            if (!this.getMonster().getOwner().getMonsterField().getOneCard(i).getType().equals("Hero")) {
                this.getMonster().getOwner().moveCardFromFirstToSecond(this.getMonster().getOwner().getMonsterField().getOneCard(i),
                        this.getMonster().getOwner().getMonsterField(), this.getMonster().getOwner().getGraveYard());
            }
        }
        for (int i = this.getMonster().getOwner().getEnemy().getMonsterField().getCards().size() - 1; i >= 0; i--) {
            if (!this.getMonster().getOwner().getEnemy().getMonsterField().getOneCard(i).getType().equals("Hero")) {
                this.getMonster().getOwner().moveCardFromFirstToSecond(this.getMonster().getOwner().getEnemy().getMonsterField().getOneCard(i),
                        this.getMonster().getOwner().getEnemy().getMonsterField(), this.getMonster().getOwner().getEnemy().getGraveYard());
            }
        }
    }

    public void will() {
        for (Monster monster : this.getMonster().getOwner().getEnemy().getMonsterField().getCards()) {
            monster.decreaseAP(400);
        }
    }

    public String info() {
        return ("[Spell Details] (King’s Wing Slash: Deal 600 damage to all enemy monster cards and player)\n" +
                "[BattleCry Details] (King’s Grace: Send all non-Hero monster cards on both sides of field to their graveyards)\n" +
                "[Will Details] (King’s Wail: Decrease all enemy monster cards’ AP by 400)");
    }

    @Override
    public DragonKing getCopy() {
        return new DragonKing();
    }
}

//// Atlantian Monster cards{

class NagaSiren extends SpellCasterMagic {

    public void spell() {
        for (Monster monster : this.getMonster().getOwner().getMonsterField().getCards()) {
            monster.increaseHP(300);
            monster.increaseAP(200);
        }
    }

    public String info() {
        return ("[Spell Details] (Song of the Siren: Increase HP of all friendly monster cards by 300 and their AP by 200)");
    }

    @Override
    public NagaSiren getCopy() {
        return new NagaSiren();
    }
}

class SeaSerpent extends SpellCasterMagic {

    public void spell() {
        this.getMonster().getOwner().getEnemy().showPlayreAndMonsterFieldForSelecting();
        if (!this.getMonster().getOwner().getEnemy().getMonsterField().getCards().isEmpty()) {
            int targetNumber = Scan.scanner.nextInt();
            if (targetNumber < this.getMonster().getOwner().getEnemy().getMonsterField().getCards().size()) {
                Monster target = this.getMonster().getOwner().getEnemy().getMonsterField().getOneCard(targetNumber);

                target.decreaseHP(1000);
            } else if (targetNumber == 11) {
                this.getMonster().getOwner().getEnemy().decreaseHP(1000);
            }
        }
    }

    public String info() {
        return ("[Spell Details] (Serpent’s Bite: Deal 1000 damage to an enemy monster card or player)");
    }

    @Override
    public SeaSerpent getCopy() {
        return new SeaSerpent();
    }
}

class Kraken extends GeneralMagic {

    public void battleCry() {
        if (!this.getMonster().getOwner().getEnemy().getMonsterField().getCards().isEmpty()) {
            this.getMonster().getOwner().getEnemy().moveCardFromFirstToSecond(this.getMonster().getOwner().getEnemy().getMonsterField().getOneCard(0),
                    this.getMonster().getOwner().getEnemy().getMonsterField(), this.getMonster().getOwner().getEnemy().getHand());
        }
        for (Monster monster : this.getMonster().getOwner().getEnemy().getMonsterField().getCards()) {
            monster.decreaseAP(200);
        }
    }

    public void will() {
        for (Monster monster : this.getMonster().getOwner().getMonsterField().getCards()) {
            monster.decreaseHP(400);
        }
        this.getMonster().getOwner().getEnemy().decreaseHP(400);
    }

    @Override
    public Kraken getCopy() {
        return new Kraken();
    }

    public String info() {
        return ("[BattleCry Details] (Titan’s Presence: Return one random enemy monster card from field to hand and reduce all enemy monsters’ AP by 200)\n" +
                "[Will Details] (Titan’s Fall: Deal 400 damage to all enemy monster cards and player)");
    }
}

class KingOfAtlantis extends HeroMagic {

    public void spell() {
        for (int i = 0; i < 2; i++) {
            if (this.getMonster().getOwner().getEnemy().getMonsterField().getOneCard(i) != null) {
                this.getMonster().getOwner().getEnemy().getMonsterField().getOneCard(i).decreaseHP(800);
            }
            this.getMonster().getOwner().getEnemy().decreaseHP(800);
        }
    }

    public void battleCry() {

        System.out.println("select a target");
        int counter = 0;
        for (Card card : this.getMonster().getOwner().getHand().getCards()) {
            System.out.println(counter + "." + card.getName());
            counter++;
        }
        if (!this.getMonster().getOwner().getHand().getCards().isEmpty()) {
            int targetNumber = Scan.scanner.nextInt();
            Card target = this.getMonster().getOwner().getHand().getOneCard(targetNumber);

            if (target instanceof Monster) {
                Monster monster = (Monster) target;
                this.getMonster().getOwner().moveCardFromFirstToSecond(monster, this.getMonster().getOwner().getHand(),
                        this.getMonster().getOwner().getMonsterField());
            } else {
                Spell spell = (Spell) target;
                this.getMonster().getOwner().moveCardFromFirstToSecond(spell, this.getMonster().getOwner().getHand(),
                        this.getMonster().getOwner().getSpellField());
            }
        }
    }

    public void will() {
        for (Monster monster : this.getMonster().getOwner().getEnemy().getMonsterField().getCards()) {
            monster.decreaseHP(400);
        }
        this.getMonster().getOwner().getEnemy().decreaseHP(400);
        for (Monster monster : this.getMonster().getOwner().getMonsterField().getCards()) {
            monster.increaseAP(500);
        }
    }

    public String info() {
        return ("[Spell Details] (Trident Beam: Deal 800 damage to three random enemy monster cards or player)\n" +
                "[BattleCry Details] (Call to Arms: Select and move a card from hand to play field)\n" +
                "[Will Details] (Ocean’s Cry: Deal 400 damage to all enemy monster cards and player and increase all friendly)");
    }

    @Override
    public KingOfAtlantis getCopy() {
        return new KingOfAtlantis();
    }
}
////////////// Goblin Monster {

class GoblinShaman extends SpellCasterMagic {

    public void spell(Monster card) {
        card.increaseHP(400);
    }

    public void spell(Player myPlayer) {
        myPlayer.increaseHP(400);
    }

    public void spell() {
        if (this.getMonster().getOwner().getHP() < this.getMonster().getOwner().getMaxHP()) {
            spell(this.getMonster().getOwner());
        } else if (!this.getMonster().getOwner().getMonsterField().getCards().isEmpty()) {
            Monster target = this.getMonster().getOwner().getMonsterField().getOneCard(0);
            for (Monster monster : this.getMonster().getOwner().getMonsterField().getCards()) {
                if (monster.getMP() > target.getMP()) {
                    target = monster;
                }
            }
            spell(target);
        }
    }

    public String info() {
        return ("[Spell Details] (Mend: Increase a friendly monster card or player’s HP by 400)");
    }

    @Override
    public GoblinShaman getCopy() {
        return new GoblinShaman();
    }
}

///// Ogre classes {


class OgreMagi extends SpellCasterMagic {


    public void spell() {
        if (!this.getMonster().getOwner().getMonsterField().getCards().isEmpty()) {
            Monster target = this.getMonster().getOwner().getMonsterField().getOneCard(0);
            for (Monster monster : this.getMonster().getOwner().getMonsterField().getCards()) {
                if (monster.getMP() > target.getMP()) {
                    target = monster;
                }
            }
            spell(target);
        }
    }

    public void spell(Monster myCard) {
        myCard.increaseAP(400);
    }

    public String info() {
        return ("[Spell Details] (Enrage: Increase a friendly monster card’s AP by 400)");
    }

    @Override
    public OgreMagi getCopy() {
        return new OgreMagi();
    }
}

class OgreWarchief extends GeneralMagic {

    public void battleCry() {
        for (Monster monster : this.getMonster().getOwner().getEnemy().getMonsterField().getCards()) {
            monster.decreaseHP(400);
        }
        this.getMonster().getOwner().getEnemy().decreaseHP(400);
    }

    public void will() {
        for (Monster monster : this.getMonster().getOwner().getMonsterField().getCards()) {
            monster.increaseAP(300);
        }
    }

    @Override
    public OgreWarchief getCopy() {
        return new OgreWarchief();
    }

    public String info() {
        return ("[BattleCry Details] (War Stomp: Deal 400 damage to all enemy monster cards and player)\n" +
                "[Will Details] (Last Order: Increase all friendly monster cards’ AP by 300)");
    }
}

/////////// Vampiric classes {

class UndeadMage extends SpellCasterMagic {

    public void spell() {

        if (!this.getMonster().getOwner().getEnemy().getMonsterField().getCards().isEmpty()) {
            Monster target = this.getMonster().getOwner().getEnemy().getMonsterField().getOneCard(0);
            for (Monster monster : this.getMonster().getOwner().getEnemy().getMonsterField().getCards()) {
                if (monster.getMP() > target.getMP()) {
                    target = monster;
                }
            }
            spell(target);
        }
    }

    public void spell(Monster enemyMonster) {
        enemyMonster.decreaseAP(500);
    }

    public String info() {
        return ("[Spell Details] (Curse: Reduce an enemy monster card’s AP by 500)");
    }

    @Override
    public UndeadMage getCopy() {
        return new UndeadMage();
    }
}

class VampireAcolyte extends SpellCasterMagic {

    public void spell() {
        for (Monster monster : this.getMonster().getOwner().getEnemy().getMonsterField().getCards()) {
            monster.decreaseHP(300);
        }
        for (Monster monster : this.getMonster().getOwner().getMonsterField().getCards()) {
            monster.increaseHP(300);
        }
    }

    public String info() {
        return ("[Spell Details] (Black Wave: Deal 300 damage to all enemy monster cards and heal all friendly monster cards for 300 HP)");
    }

    @Override
    public VampireAcolyte getCopy() {
        return new VampireAcolyte();
    }
}

class VampirePrince extends GeneralMagic {

    public void battleCry() {
        for (int i = 0; i < 2; i++) {
            if (!this.getMonster().getOwner().getEnemy().getMonsterField().getCards().isEmpty()) {
                this.getMonster().getOwner().getEnemy().moveCardFromFirstToSecond(this.getMonster().
                        getOwner().getEnemy().getMonsterField().getOneCard(0), this.getMonster().getOwner().getEnemy().
                        getMonsterField(), this.getMonster().getOwner().getEnemy().getHand());
            }
        }
    }

    public void will() {
        for (Monster monster : this.getMonster().getOwner().getEnemy().getMonsterField().getCards()) {
            monster.decreaseAP(200);
        }
        for (Monster monster : this.getMonster().getOwner().getMonsterField().getCards()) {
            monster.increaseAP(200);
        }
    }

    @Override
    public VampirePrince getCopy() {
        return new VampirePrince();
    }

    public String info() {
        return ("[BattleCry Details] (Fear: Return two random enemy monster cards from field to hand)\n" +
                "[Will Details] (Darkness: Reduce all enemy monster cards’ AP by 200 and increase friendly monster cards’ AP by 200)");
    }
}

//////// Demonic Monster classes {

class EvilEye extends SpellCasterMagic {

    public void spell() {
        for (Monster monster : this.getMonster().getOwner().getEnemy().getMonsterField().getCards()) {
            monster.decreaseHP(800);
        }
        this.getMonster().getOwner().getEnemy().decreaseHP(800);
    }

    public String info() {
        return ("[Spell Details] (Evil Gaze: Deal 800 damage to all enemy monster cards and player)");
    }

    @Override
    public EvilEye getCopy() {
        return new EvilEye();
    }
}

class Necromancer extends SpellCasterMagic {

    public void spell() {
        if (!this.getMonster().getOwner().getGraveYard().getCards().isEmpty()) {
            if (Monster.class.isAssignableFrom(this.getMonster().getOwner().getGraveYard().getOneCard(0).getClass())) {
                Monster monster = (Monster) this.getMonster().getOwner().getGraveYard().getOneCard(0);
                monster.reborn();
            }
            this.getMonster().getOwner().moveCardFromFirstToSecond(this.getMonster().getOwner().getGraveYard().getOneCard(0),
                    this.getMonster().getOwner().getGraveYard(), this.getMonster().getOwner().getHand());
        }
    }

    public String info() {
        return ("[Spell Details] (Raise Dead: Move a random card from your graveyard to hand)");
    }

    @Override
    public Necromancer getCopy() {
        return new Necromancer();
    }
}

class DarkKnight extends GeneralMagic {

    public void battleCry() {

        if (!this.getMonster().getOwner().getEnemy().getHand().getCards().isEmpty()) {
            Card target = this.getMonster().getOwner().getEnemy().getHand().getOneCard(0);
            for (Card card : this.getMonster().getOwner().getEnemy().getHand().getCards()) {
                if (card.getMP() > target.getMP()) {
                    target = card;
                }
            }
            battleCry(target);
        }
    }


    public void battleCry(Card target) {
        target.getOwner().moveCardFromFirstToSecond(target, target.getOwner().getHand(), target.getOwner().getGraveYard());
    }

    public void will() {
        this.getMonster().getOwner().increaseHP(1000);
    }

    @Override
    public DarkKnight getCopy() {
        return new DarkKnight();
    }

    public String info() {
        return ("[BattleCry Details] (Sacrifice: Select and move a card from hand to graveyard)\n" +
                "[Will Details] (Loyalty: Heal your player for 1000 HP)");
    }
}

class GatekeeperOfHell extends HeroMagic {

    public void battleCry() {
        for (int i = 0; i < 3; i++) {
            this.getMonster().getOwner().moveNextCardFromDeckToHand();
        }
    }

    public void spell() {
        for (Monster monster : this.getMonster().getOwner().getEnemy().getMonsterField().getCards()) {
            monster.decreaseHP(300);
        }
        for (Monster monster : this.getMonster().getOwner().getMonsterField().getCards()) {
            monster.increaseAP(300);
            monster.increaseHP(300);
        }
    }

    public void will() {
        for (int i = 0; i < 2; i++) {
            if (!this.getMonster().getOwner().getEnemy().getMonsterField().getCards().isEmpty()) {
                this.getMonster().getOwner().getEnemy().moveCardFromFirstToSecond(this.getMonster().getOwner().getEnemy().
                                getMonsterField().getOneCard(0), this.getMonster().getOwner().getEnemy().getMonsterField(),
                        this.getMonster().getOwner().getEnemy().getGraveYard());
            }
        }
    }

    public String info() {
        return ("[Spell Details] (Hellfire: Deal 300 damage to all enemy monster cards and Increase HP and AP of all friendly monster cards by 300)\n" +
                "[BattleCry Details] (Open the Gate: Draw three cards from deck to hand)\n" +
                "[Will Details] (Revenge of the Two Heads: Send two random enemy monster cards from field to garveyard)");
    }

    @Override
    public GatekeeperOfHell getCopy() {
        return new GatekeeperOfHell();
    }
}