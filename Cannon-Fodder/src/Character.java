import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public abstract class Character implements characterMethods {

    private String name;
    private double strength, vitality, intelligence, healthPoints, damage, maxHP;
    private boolean dead;
    protected ArrayList<Item> inventory;

    public Character() {
        name         = "UNKNOWN";
        strength     = 0.0;
        vitality     = 0.0;
        intelligence = 0.0;
        healthPoints = 0.0;
        damage       = 0.0;
        maxHP        = 0.0;
        inventory    = new ArrayList<>();
    }

    /*
    public Character(String name, double strength, double vitality, double intelligence, double healthPoints, double damage) {
        this.name         = name;
        this.strength     = strength;
        this.vitality     = vitality;
        this.intelligence = intelligence;
        this.healthPoints = healthPoints;
        this.damage       = damage;
    }
     */

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getStrength() { return strength; }
    public void setStrength(double strength) { this.strength = strength; }
    public double getVitality() { return vitality; }
    public void setVitality(double vitality) { this.vitality = vitality; }
    public double getIntelligence() { return intelligence; }
    public void setIntelligence(double intelligence) { this.intelligence = intelligence; }
    public double getHealthPoints() { return healthPoints; }
    public void setHealthPoints(double healthPoints) { this.healthPoints = healthPoints; }
    public double getDamage() { return damage; }
    public void setDamage(double damage) { this.damage = damage; }
    public boolean isDead() { return dead; }
    public void setDead(boolean dead) { this.dead = dead; }
    public ArrayList<Item> getInventory() { return inventory; }
    public void setInventory(ArrayList<Item> inventory) { this.inventory = inventory; }
    public double getMaxHP() { return maxHP; }
    public void setMaxHP(double maxHP) { this.maxHP = maxHP; }
    public static int killcounter=0;

    @Override
    public Integer attack(int userChoice, EnemySoldier[] enemies, Scanner input) {
        boolean checkpoint = false;
        while (!checkpoint) {
            System.out.println(
                    "Which enemy you want to attack?" + "\n" +
                            "Valid values : '1', '2', etc..."
            );
            printEnemies(enemies);
            userChoice = input.nextInt();
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");

            if (!enemies[userChoice - 1].isDead()) {
                System.out.println("USER Attacking...");
                enemies[userChoice - 1].setHealthPoints(enemies[userChoice - 1].getHealthPoints() - getDamage());
                System.out.println(getName() + " attack Enemy " + userChoice + " and does " + getDamage() + " damage.");
                if (enemies[userChoice - 1].getHealthPoints() < 0.0) {
                    enemies[userChoice - 1].setHealthPoints(0.0);
                    System.out.println("***** Enemy " + userChoice + " is dead. *****");
                    enemies[userChoice - 1].setDead(true);
                    killcounter++;
                } else {
                    System.out.println("Enemy " + userChoice + " has " + enemies[userChoice - 1].getHealthPoints() + " HP left." + "\n");
                }
                checkpoint = true;
            } else {
                System.out.println("This enemy can not be attacked because it is dead, please choose another one.");
            }
        }
        return killcounter;
    }

    @Override
    public void specialAction(Character FighterCharacter, Character TankCharacter, Character HealerCharacter, Weapons weapons, int W, Scanner input, boolean breakboint) {
        if (weapons.getItems().get(W).getType() == "wand") {
            System.out.println(
                    "Which character you want to heal?" + "\n" +
                            "Valid values : 'FIGHTER', 'TANK', 'HEALER'"
            );
            String chosenCharacter = input.nextLine();
            chosenCharacter = chosenCharacter.toUpperCase();
            switch (chosenCharacter) {
                case "FIGHTER":
                    FighterCharacter.updateHP();
                    break;
                case "TANK":
                    TankCharacter.updateHP();
                    break;
                case "HEALER":
                    HealerCharacter.updateHP();
                    break;
            }
            breakboint = true;
        }
        if (weapons.getItems().get(W).getType() == "shield") {
            System.out.println("This weapon has no special action.");
            breakboint = false;
        }
        if (weapons.getItems().get(W).getType() == "sword") {
            System.out.println("This weapon has no special action.");
            breakboint = false;
        }
    }

    @Override
    public void updateHP() { System.out.println("Updating the health point..."); }

    @Override
    public void printInventory() {
        System.out.println("The items owned by the character are : ");
        for(int i = 0; i < inventory.size(); i++) {
            System.out.println((i+1) + " | " + inventory.get(i).getItemName() + " weight | " + inventory.get(i).getWeight());
        }
    }

    @Override
    public void printEnemies(EnemySoldier[] enemies) {
        System.out.println("Enemies :");
        for (int i = 0; i < enemies.length; i++) {
            System.out.println("Enemy " + (i+1) + " with " + enemies[i].getHealthPoints() + " HP.");
        }
        System.out.println();
    }

    /*
    Weapon Type                 Damage
    ---------------------------------------------------
    Sword           Damage * Strength of character
    Shield          Damage * Vitality of character
    Wand            Damage * Intelligence of character
*/

    //Damage calculating function. Also, can be used to give damage.
    public static void calculateDamage(Character FighterCharacter, Character TankCharacter, Character HealerCharacter, Weapons weapons, int W1, int W2, int W3, String command) {
        switch (command) {
            case "FIGHTER":
                switch (weapons.getItems().get(W1-1).getType()) {
                    case "sword"  : FighterCharacter.damage = (weapons.getItems().get(W1-1).getValue() * FighterCharacter.getStrength())       / 100;        break;
                    case "shield" : FighterCharacter.damage = (weapons.getItems().get(W1-1).getValue() * FighterCharacter.getVitality())       / 100;        break;
                    case "wand"   : FighterCharacter.damage = (weapons.getItems().get(W1-1).getValue() * FighterCharacter.getIntelligence())   / 100;        break;
                }
            case "TANK":
                switch (weapons.getItems().get(W2-1).getType()) {
                    case "sword"  : TankCharacter.damage = (weapons.getItems().get(W2-1).getValue()    * TankCharacter.getStrength())          / 100;        break;
                    case "shield" : TankCharacter.damage = (weapons.getItems().get(W2-1).getValue()    * TankCharacter.getVitality())          / 100;        break;
                    case "wand"   : TankCharacter.damage = (weapons.getItems().get(W2-1).getValue()    * TankCharacter.getIntelligence())      / 100;        break;
                }
            case "HEALER":
                switch (weapons.getItems().get(W3-1).getType()) {
                    case "sword"  : HealerCharacter.damage = (weapons.getItems().get(W3-1).getValue()  * HealerCharacter.getStrength())        / 100;        break;
                    case "shield" : HealerCharacter.damage = (weapons.getItems().get(W3-1).getValue()  * HealerCharacter.getVitality())        / 100;        break;
                    case "wand"   : HealerCharacter.damage = (weapons.getItems().get(W3-1).getValue()  * HealerCharacter.getIntelligence())    / 100;        break;
                }
        }
    }

    public static int generateRandomValue(int upperBound, int lowerBound) {
        Random rand = new Random();
        int value = rand.nextInt(upperBound - lowerBound + 1) + lowerBound;
        return value;
    }
}
