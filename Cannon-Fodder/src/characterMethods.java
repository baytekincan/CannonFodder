import java.util.Scanner;

public interface characterMethods {
    void specialAction(Character FighterCharacter, Character TankCharacter, Character HealerCharacter, Weapons weapons , int W, Scanner input, boolean breakpoint);
    Integer attack(int userChoice, EnemySoldier[] enemies, Scanner input);
    void updateHP();
    void printInventory();
    void printEnemies(EnemySoldier[] enemies);
}

