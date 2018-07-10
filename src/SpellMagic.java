import java.util.ArrayList;

abstract class SpellMagic {
    private Spell spellCard;


    public void setSpellCard(Spell spellCard) {
        this.spellCard = spellCard;
    }

    public Spell getSpellCard() {
        return spellCard;
    }

    abstract public void spell();

    abstract public String info();

    abstract public SpellMagic getCopy();
}

class ThrowingKnives extends SpellMagic implements HasAutomaticSpell {

    @Override
    public void spell() {
        System.out.println("select a target");
        int counter = 0;
        System.out.println("11.enemy player: " + this.getSpellCard().getOwner().getEnemy());
        for (Monster monster : this.getSpellCard().getOwner().getEnemy().getMonsterField().getCards()) {
            System.out.println(counter + "." + monster.getName());
            counter++;
        }
        int targetNumber = Scan.scanner.nextInt();
        if (targetNumber == 11) {
            this.getSpellCard().getOwner().getEnemy().decreaseHP(500);
        } else {
            this.getSpellCard().getOwner().getEnemy().getMonsterField().getOneCard(targetNumber).decreaseHP(500);
        }
    }

    @Override
    public String info() {
        return (" Deal 500 damage to a selected enemy monster card on the field or to enemy player ");
    }

    @Override
    public ThrowingKnives getCopy() {
        return new ThrowingKnives();
    }

    @Override
    public void automaticSpell() {
        if (this.getSpellCard().getOwner().getHP() < 2000 && !this.getSpellCard().getOwner().getEnemy().getMonsterField().getCards().isEmpty()) {
            Monster target = this.getSpellCard().getOwner().getEnemy().getMonsterField().getOneCard(0);
            for (Monster monster : this.getSpellCard().getOwner().getEnemy().getMonsterField().getCards()) {
                if (monster.getMP() > target.getMP()) {
                    target = monster;
                }
            }
            spell(target);
        } else {
            spell(this.getSpellCard().getOwner().getEnemy());
        }
    }

    private void spell(Player enemyPlayer) {
        enemyPlayer.decreaseHP(500);
    }

    private void spell(Monster monster) {
        monster.decreaseHP(500);
    }
}

class PoisonousCauldron extends SpellMagic implements HasAutomaticSpell {

    @Override
    public void spell() {
        this.getSpellCard().getOwner().getEnemy().decreaseHP(100);
        for (Monster m : this.getSpellCard().getOwner().getEnemy().getMonsterField().getCards()) {
            m.decreaseHP(100);
        }
    }


    @Override
    public String info() {
        return ("Deal 100 damage to all enemy monster cards and enemy player");
    }

    @Override
    public PoisonousCauldron getCopy() {
        return new PoisonousCauldron();
    }

    @Override
    public void automaticSpell() {
        spell();
    }
}

class FirstAidKit extends SpellMagic implements HasAutomaticSpell {

    @Override
    public void spell() {
        System.out.println("select a target");
        int counter = 0;
        System.out.println("11.your player: " + this.getSpellCard().getOwner());
        for (Monster monster : this.getSpellCard().getOwner().getMonsterField().getCards()) {
            System.out.println(counter + "." + monster.getName());
            counter++;
        }
        int targetNumber = Scan.scanner.nextInt();
        if (targetNumber == 11) {
            this.getSpellCard().getOwner().increaseHP(500);
        } else {
            this.getSpellCard().getOwner().getMonsterField().getOneCard(targetNumber).increaseHP(500);
        }

    }

    @Override
    public String info() {
        return ("Increase HP of a selected friendly monster card or player by 500");
    }

    @Override
    public FirstAidKit getCopy() {
        return new FirstAidKit();
    }

    @Override
    public void automaticSpell() {
        if (this.getSpellCard().getOwner().getHP() < this.getSpellCard().getOwner().getMaxHP() - 500 || this.getSpellCard().getOwner().getMonsterField().getCards().isEmpty()) {
            spell(this.getSpellCard().getOwner());
        } else {
            Monster target = this.getSpellCard().getOwner().getMonsterField().getOneCard(0);
            for (Monster monster : this.getSpellCard().getOwner().getMonsterField().getCards()) {
                if (monster.getMP() > target.getMP()) {
                    target = monster;
                }
            }
            spell(target);
        }

    }

    private void spell(Player myPlayer) {
        myPlayer.increaseHP(500);
    }

    private void spell(Monster monster) {
        monster.increaseHP(500);
    }
}

class ReapersScythe extends SpellMagic implements HasAutomaticSpell {

    @Override
    public void spell() {
        this.getSpellCard().getOwner().getEnemy().showMonsterFieldAndSpellFieldForSelecting();
        if (!this.getSpellCard().getOwner().getMonsterField().getCards().isEmpty() || !this.getSpellCard().getOwner().getEnemy().getSpellField().getCards().isEmpty()) {
            int targetNumber = Scan.scanner.nextInt();
            if (targetNumber < 0 && !this.getSpellCard().getOwner().getEnemy().getSpellField().getCards().isEmpty()) {
                Spell target = this.getSpellCard().getOwner().getEnemy().getSpellField().getOneCard(-targetNumber - 1);
                this.getSpellCard().getOwner().getEnemy().moveCardFromFirstToSecond(target,
                        this.getSpellCard().getOwner().getEnemy().getSpellField(),
                        this.getSpellCard().getOwner().getEnemy().getGraveYard());
            } else if (targetNumber > 0 && !this.getSpellCard().getOwner().getEnemy().getMonsterField().getCards().isEmpty()) {
                Monster target = this.getSpellCard().getOwner().getEnemy().getMonsterField().getOneCard(targetNumber);
                this.getSpellCard().getOwner().getEnemy().moveCardFromFirstToSecond(target,
                        this.getSpellCard().getOwner().getEnemy().getMonsterField(),
                        this.getSpellCard().getOwner().getEnemy().getGraveYard());
            }
        }
    }


    @Override
    public String info() {
        return ("Send an enemy monster or spell card from field to graveyard");
    }

    @Override
    public ReapersScythe getCopy() {
        return new ReapersScythe();
    }

    @Override
    public void automaticSpell() {
        Card target = null;
        if (!this.getSpellCard().getOwner().getEnemy().getMonsterField().getCards().isEmpty()) {
            for (Monster monster : this.getSpellCard().getOwner().getEnemy().getMonsterField().getCards()) {
                if (target == null) {
                    target = monster;
                } else if (monster.getMP() > target.getMP()) {
                    target = monster;
                }
            }
        }
        if (!this.getSpellCard().getOwner().getEnemy().getSpellField().getCards().isEmpty()) {
            for (Spell spell : this.getSpellCard().getOwner().getEnemy().getSpellField().getCards()) {
                if (target == null) {
                    target = spell;
                } else if (spell.getMP() > target.getMP()) {
                    target = spell;
                }
            }
        }
        if (target != null) {
            spell(target);
        }

    }

    public void spell(Card target) {
        if (target instanceof Spell) {
            target.getOwner().moveCardFromFirstToSecond((Spell) target,
                    target.getOwner().getSpellField(),
                    target.getOwner().getGraveYard());
        } else {
            target.getOwner().moveCardFromFirstToSecond((Monster) target,
                    target.getOwner().getMonsterField(),
                    target.getOwner().getGraveYard());
        }
    }
}

class MeteorShower extends SpellMagic implements HasAutomaticSpell {


    @Override
    public void spell() {
        if (this.getSpellCard().getOwner().getEnemy().getMonsterField().getCards().isEmpty()) {
            this.getSpellCard().getOwner().getEnemy().decreaseHP(800);
        } else {
            this.getSpellCard().getOwner().getEnemy().getMonsterField().getOneCard(0).decreaseHP(800);
        }

    }

    @Override
    public String info() {
        return ("Deal 800 damage to a random enemy monster card on field or player");
    }

    @Override
    public MeteorShower getCopy() {
        return new MeteorShower();
    }

    @Override
    public void automaticSpell() {
        spell();
    }
}

class LunarBlessing extends SpellMagic implements AuraMagic {
    private ArrayList<Monster> targets = new ArrayList<>(3);

    @Override
    public void spell() {
        for (Monster m : this.getSpellCard().getOwner().getMonsterField().getCards()) {
            if (m.getTribe().equals("Elven")) {
                m.increaseHP(300);
                m.increaseAP(300);
                this.targets.add(m);
            }
        }

    }

    @Override
    public void disSpell() {
        for (Monster monster : this.targets) {
            if (monster.getOwner().getMonsterField().getCards().contains(monster)) {
                monster.decreaseHP(300);
                monster.decreaseAP(300);
            }
        }
        this.targets.clear();
        this.getSpellCard().getOwner().updateMonsterField();
    }

    @Override
    public String info() {
        return ("Increase AP and HP of friendly Elven monster cards by 300");
    }

    @Override
    public LunarBlessing getCopy() {
        return new LunarBlessing();
    }
}

class StrategicRetreat extends SpellMagic implements HasAutomaticSpell {

    @Override
    public void spell() {
        this.getSpellCard().getOwner().showMonsterFieldForSelecting();
        if (!this.getSpellCard().getOwner().getMonsterField().getCards().isEmpty()) {
            if (!this.getSpellCard().getOwner().getMonsterField().getCards().isEmpty()) {
                int targetNumber = Scan.scanner.nextInt();
                Monster target = this.getSpellCard().getOwner().getMonsterField().getOneCard(targetNumber);
                this.getSpellCard().getOwner().moveCardFromFirstToSecond(target,
                        this.getSpellCard().getOwner().getMonsterField(), this.getSpellCard().getOwner().getHand());
                this.getSpellCard().getOwner().moveNextCardFromDeckToHand();
            }
        }

    }

    @Override
    public String info() {
        return ("Select and move a monster card from field to hand and draw one card from deck");
    }

    @Override
    public StrategicRetreat getCopy() {
        return new StrategicRetreat();
    }

    @Override
    public void automaticSpell() {
        if (!this.getSpellCard().getOwner().getMonsterField().getCards().isEmpty()) {
            Monster target = null;
            for (Monster monster : this.getSpellCard().getOwner().getMonsterField().getCards()) {
                if (target != null) {
                    if (monster.getMP() < target.getMP()) {
                        target = monster;
                    }
                } else if (target == null && monster.getMP() < 4 && this.getSpellCard().getOwner().getMonsterField().getCards().size() == 5) {
                    target = monster;
                }
            }
            if (target != null) {
                spell(target);
            }
        }
    }

    public void spell(Monster monster) {
        monster.getOwner().moveCardFromFirstToSecond(monster,
                monster.getOwner().getMonsterField(),
                monster.getOwner().getGraveYard());
        monster.getOwner().moveNextCardFromDeckToHand();
    }
}

class WarDrum extends SpellMagic implements AuraMagic {
    private ArrayList<Monster> targets = new ArrayList<>(3);

    @Override
    public void spell() {
        for (Monster m : this.getSpellCard().getOwner().getMonsterField().getCards()) {
            m.increaseAP(300);
            targets.add(m);
        }
    }

    @Override
    public void disSpell() {
        for (Monster monster : this.targets) {
            if (monster.getOwner().getMonsterField().getCards().contains(monster)) {
                monster.decreaseAP(300);
            }
        }
    }

    @Override
    public String info() {
        return ("Increase all friendly monster cards’ AP by 300");
    }

    @Override
    public WarDrum getCopy() {
        return new WarDrum();
    }
}

class HealingWard extends SpellMagic implements HasAutomaticSpell {

    @Override
    public void spell() {
        for (Monster m : this.getSpellCard().getOwner().getMonsterField().getCards()) {
            m.increaseHP(200);
        }

    }

    @Override
    public String info() {
        return ("Increase all friendly monster cards’ HP by 200");
    }

    @Override
    public HealingWard getCopy() {
        return new HealingWard();
    }

    @Override
    public void automaticSpell() {
        spell();
    }
}

class BloodFeast extends SpellMagic implements HasAutomaticSpell {


    public void spell() {
        this.getSpellCard().getOwner().increaseHP(500);
        this.getSpellCard().getOwner().getEnemy().getEnemy().decreaseHP(500);

    }


    @Override
    public String info() {
        return ("Deal 500 damage to enemy player and heal your player for 500 HP");
    }

    @Override
    public BloodFeast getCopy() {
        return new BloodFeast();
    }

    @Override
    public void automaticSpell() {
        spell();
    }
}

class Tsunami extends SpellMagic {

    @Override
    public void spell() {
        for (Monster m : this.getSpellCard().getOwner().getMonsterField().getCards()) {
            if (!"Atlantian".equals(m.getTribe()))
                m.decreaseHP(500);
        }
        for (Monster m : this.getSpellCard().getOwner().getEnemy().getMonsterField().getCards()) {                 //check method in player class
            if (!"Atlantian".equals(m.getTribe())) {
                m.decreaseHP(500);
            }
        }

    }

    @Override
    public String info() {
        return ("Deal 500 damage to all non-Atlantian monster cards on both sides of field");
    }

    @Override
    public Tsunami getCopy() {
        return new Tsunami();
    }
}

class TakeAllYouCan extends SpellMagic implements AuraMagic {
    private ArrayList<Monster> targets = new ArrayList<>(3);

    @Override
    public void spell() {
        for (Monster m : this.getSpellCard().getOwner().getMonsterField().getCards()) {
            if (m != null && m.getType().equals("Normal")) {
                m.increaseAP(400);
                m.increaseHP(400);
                this.targets.add(m);
            }
        }


    }

    @Override
    public void disSpell() {
        for (Monster monster : this.targets) {
            if (monster.getOwner().getMonsterField().getCards().contains(monster)) {
                monster.decreaseAP(400);
                monster.decreaseHP(400);
            }
        }
        this.getSpellCard().getOwner().updateMonsterField();
    }

    @Override
    public String info() {
        return ("Increase all friendly normal monster cards’ HP and AP by 400");
    }

    @Override
    public TakeAllYouCan getCopy() {
        return new TakeAllYouCan();
    }
}

class ArcaneBolt extends SpellMagic {

    @Override
    public void spell() {
        this.getSpellCard().getOwner().getEnemy().decreaseHP(500);
        System.out.println("select a target");
        int counter = 0;
        for (Spell spell : this.getSpellCard().getOwner().getEnemy().getSpellField().getCards()) {
            System.out.println(counter + "." + spell.getName());
            counter++;
        }
        if (!this.getSpellCard().getOwner().getEnemy().getSpellField().getCards().isEmpty()) {
            int targetNumber = Scan.scanner.nextInt();
            this.getSpellCard().getOwner().getEnemy().moveCardFromFirstToSecond(this.
                            getSpellCard().getOwner().getEnemy().getSpellField().getOneCard(targetNumber),
                    this.getSpellCard().getOwner().getEnemy().getSpellField(), this.getSpellCard().getOwner().getEnemy().getGraveYard());

        }
    }


    @Override
    public String info() {
        return ("Deal 500 damage to enemy player and select and move an enemy spell card from field to graveyard");
    }

    @Override
    public ArcaneBolt getCopy() {
        return new ArcaneBolt();
    }
}

class GreaterPurge extends SpellMagic implements HasAutomaticSpell {


    public void spell() {
        int temp = this.getSpellCard().getOwner().getSpellField().getCards().size();
        for (int i = 0; i < temp; i++) {
            this.getSpellCard().getOwner().moveCardFromFirstToSecond(this.getSpellCard().getOwner().getSpellField().getOneCard(0), this.getSpellCard().getOwner().getSpellField(),
                    this.getSpellCard().getOwner().getHand());
        }
        temp = this.getSpellCard().getOwner().getEnemy().getSpellField().getCards().size();
        for (int i = 0; i < temp; i++) {
            this.getSpellCard().getOwner().getEnemy().moveCardFromFirstToSecond(this.getSpellCard().getOwner().getEnemy().getSpellField().getOneCard(i),
                    this.getSpellCard().getOwner().getEnemy().getSpellField(), this.getSpellCard().getOwner().getEnemy().getHand());
        }
    }

    @Override
    public String info() {
        return ("Remove all spell cards on field from both sides and move them to hand");
    }

    @Override
    public GreaterPurge getCopy() {
        return new GreaterPurge();
    }

    @Override
    public void automaticSpell() {
        spell();
    }
}


class MagicSeal extends SpellMagic implements HasAutomaticSpell {


    @Override
    public void spell() {
        int temp = this.getSpellCard().getOwner().getEnemy().getSpellField().getCards().size();
        for (int i = 0; i < temp; i++) {
            this.getSpellCard().getOwner().getEnemy().moveCardFromFirstToSecond(this.getSpellCard().getOwner().getEnemy()
                            .getSpellField().getOneCard(0),
                    this.getSpellCard().getOwner().getEnemy().getSpellField(),
                    this.getSpellCard().getOwner().getEnemy().getGraveYard());
        }

    }

    @Override
    public String info() {
        return ("Remove all enemy spell cards from field and move them to graveyard");
    }

    @Override
    public MagicSeal getCopy() {
        return new MagicSeal();
    }

    @Override
    public void automaticSpell() {
        spell();
    }
}

interface AuraMagic {
    public void spell();

    public void disSpell();
}