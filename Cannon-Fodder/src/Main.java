import java.io.*;
import java.util.Scanner;

public class Main {

    public static int Level;   public static int NOE; //Number of enemies
    public static EnemySoldier[] e; //array store the enemies
    public static Scanner input = new Scanner(System.in);
    public static String command; public static int userChoice;
    static String gangName = "";

    public static void makeinfointotext() throws IOException {
        String results= "The gang name is: " + gangName + " Total dead enemies: " + killcounter;
        File file= new File("kod.txt");
        if (!file.exists()){
            file.createNewFile();
        }
        FileWriter fWriter = new FileWriter(file, false);
        BufferedWriter bufferedWriter= new BufferedWriter(fWriter);
        bufferedWriter.write(results);
        bufferedWriter.close();

    }
    public static void main(String [] args) {

        String results= "The gang name is: " + gangName + " Total dead enemies: " + killcounter;

        int W1,W2,W3;

        Weapons   weapons   = new Weapons();
        Clothings clothings = new Clothings();

        Tank TankCharacter            = new Tank();
        Fighter FighterCharacter      = new Fighter();
        Healer HealerCharacter        = new Healer();
        EnemySoldier EnemyCharacter   = new EnemySoldier();

        //Starting...                                                                                                                                                        *******
        //Display screen for the player and a description of the games purpose
        System.out.println(
                "Welcome to Cannon Fodder traveler! Another mysterious and yet dangerous adventure you are heading in! You have three characters : "                        + "\n" +
                        " -------------------- " + "The Fighter" + " -------------------- " + "The Tank" + " -------------------- " + "The Healer" + " -------------------- "       + "\n" +
                        "Each of them are significantly powerful and precious characters and among your journey, you need to keep them alive and clear all the levels of dungeons." + "\n" +
                        "In each level, the amount of the enemy you need to fought with will increase 2 times."                                                                     + "\n" +
                        "The game has 5 levels"                                                                                                                                     + "\n" +
                        "---------------------------------------------------------------------------------------------------------------------------------------------------------" + "\n" +
                        "Press '1' to start the game or '2' to quit it"                                                                                                             + "\n" +
                        "1. Start New Game"                                                                                                                                         + "\n" +
                        "2. Exit"                                                                                                                                                   + "\n" +
                        "---------------------------------------------------------------------------------------------------------------------------------------------------------"
        );

        userChoice = input.nextInt();
        input.nextLine();

        switch (userChoice) {
            //running the game
            case 1:
                System.out.println("Enter your gang name");
                gangName= input.nextLine();
                //creating items
                weapons.CreateWeapons();
                clothings.CreateClothings();
                System.out.println(
                        "Please, choose a weapon for each character : "                                                                                                     + "\n" +
                                "Valid values : '1', '2', '3', etc..."                                                                                                              + "\n"
                );
                printWeaponsList(weapons);
                //character 1. items
                System.out.println("Choose a weapon for character one (FIGHTER)   - (swords are recommended for fighter) : ");
                W1 = input.nextInt();
                //character 2. items
                System.out.println("Choose a weapon for character two (TANK)      - ( shields are recommended for tank ) : ");
                W2 = input.nextInt();
                //character 3. items
                System.out.println("Choose a weapon for character three (HEALER)  - ( wands are recommended for healer ) : ");
                W3 = input.nextInt();

                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");

                //creating new level                                                                                     - LEVEL 0 -
                createNewLevel();

                //creating characters
                FighterCharacter.createCharacter1(weapons, W1);
                TankCharacter.createCharacter2(weapons, W2);
                HealerCharacter.createCharacter3(weapons, W3);
                e = EnemySoldier.createEnemy(NOE);
                EnemyCharacter.printCreatEnemy(NOE, e);

                join(NOE);

                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");

                while (true) {
                    boolean checkpoint = false;
                    while (!checkpoint) {
                        //attacking...
                        System.out.println(
                                "Which character you will use?" + "\n" +
                                        "Valid values : 'FIGHTER', 'TANK', 'HEALER'" + "\n"
                        );

                        input.nextLine();
                        command = input.nextLine();
                        command = command.toUpperCase();

                        switch (command) {
                            case "FIGHTER":
                                if (FighterCharacter.isDead()) {
                                    System.out.println("Fighter is dead you can not use it, please try another character.");
                                } else {
                                    checkpoint = true;
                                }
                                break;
                            case "TANK":
                                if (TankCharacter.isDead()) {
                                    System.out.println("Tank is dead you can not use it, please try another character.");
                                } else {
                                    checkpoint = true;
                                }
                                break;
                            case "HEALER":
                                if (HealerCharacter.isDead()) {
                                    System.out.println("Healer is dead you can not use it, please try another character.");
                                } else {
                                    checkpoint = true;
                                }
                                break;
                        }
                    }

                    //calculating the damage for each weapon and character
                    Character.calculateDamage(FighterCharacter, TankCharacter, HealerCharacter, weapons, W1, W2, W3, command);

                    boolean breakpoint = false;
                    while (!breakpoint) {
                        System.out.println(
                                "\n" +
                                        "Please choose an action from the actions list : "                              + "\n" +
                                        "To attack enter 'ATTACK'."                                             + "\n" +
                                        "To make a special action enter 'SPECIAL ACTION'."                      + "\n" +
                                        "To wear an armor enter 'WEAR'."                                        + "\n" +
                                        "To show the items in the inventory enter 'INVENTORY'."                 + "\n"
                        );

                        String option = input.nextLine();
                        option = option.toUpperCase();

                        switch (option) {
                            case "ATTACK":
                                attackAction(e, command, userChoice, FighterCharacter, TankCharacter, HealerCharacter, NOE, input);
                                breakpoint = true;
                                break;
                            case "SPECIAL ACTION":
                                specialAction(FighterCharacter, TankCharacter, HealerCharacter, weapons, W1, W2, W3, command, breakpoint);
                                break;
                            case "WEAR":
                                printClothingList(clothings);
                                System.out.println(
                                        "Please, choose an armor for the character : " + "\n" +
                                                "Valid values : '1', '2', etc..."
                                );
                                int C = input.nextInt();
                                switch (command) {
                                    case "FIGHTER":
                                        for (int i = 0; i < clothings.getItems().size(); i++) {
                                            if (C - 1 == i) {
                                                System.out.print(
                                                        clothings.getItems().get(i).getItemName() + ", with " + clothings.getItems().get(i).getValue() + " protection and "
                                                                + clothings.getItems().get(i).getWeight() + " units of weight has been added to the fighter's inventory successfully." + "\n"
                                                );
                                                FighterCharacter.inventory.add(clothings.items.get(i));
                                            }
                                        }
                                        break;
                                    case "TANK":
                                        for (int i = 0; i < clothings.getItems().size(); i++) {
                                            if (C - 1 == i) {
                                                System.out.print(
                                                        clothings.getItems().get(i).getItemName() + ", with " + clothings.getItems().get(i).getValue() + " protection and "
                                                                + clothings.getItems().get(i).getWeight() + " units of weight has been added to the tank's inventory successfully." + "\n"
                                                );
                                                TankCharacter.inventory.add(clothings.items.get(i));
                                            }
                                        }
                                        break;
                                    case "HEALER":
                                        for (int i = 0; i < clothings.getItems().size(); i++) {
                                            if (C - 1 == i) {
                                                System.out.print(
                                                        clothings.getItems().get(i).getItemName() + ", with " + clothings.getItems().get(i).getValue() + " protection and "
                                                                + clothings.getItems().get(i).getWeight() + " units of weight has been added to the healer's inventory successfully." + "\n"
                                                );
                                                HealerCharacter.inventory.add(clothings.items.get(i));
                                            }
                                        }
                                        break;
                                }
                                break;
                            case "INVENTORY":
                                printInventoryList(FighterCharacter, TankCharacter, HealerCharacter, command);
                                break;
                        }
                    }
                    //check point
                    if (e[0].isDead()) {
                        e = null;
                        break;
                    }

                    EnemyCharacter.enemyAttack(FighterCharacter, TankCharacter, HealerCharacter, EnemyCharacter, weapons, e, NOE);

                }

                System.out.println("YOU WON, please write 'NEXT' if you wanna continue to the next level.");
                input.nextLine();
                command = input.nextLine();

                if (command.equals("NEXT") || command.equals("next")) { Level++; }

                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");

                //creating new level                                                                                     - LEVEL 1 -
                createNewLevel();

                //creating characters
                e = EnemySoldier.createEnemy(NOE);
                EnemyCharacter.printCreatEnemy(NOE, e);

                join(NOE);

                while (true) {
                    boolean checkpoint = false;
                    while (!checkpoint) {
                        //attacking...
                        System.out.println(
                                "Which character you will use?" + "\n" +
                                        "Valid values : 'FIGHTER', 'TANK', 'HEALER'" + "\n"
                        );

                        input.nextLine();
                        command = input.nextLine();
                        command = command.toUpperCase();

                        switch (command) {
                            case "FIGHTER":
                                if (FighterCharacter.isDead()) {
                                    System.out.println("Fighter is dead you can not use it, please try another character.");
                                } else {
                                    checkpoint = true;
                                }
                                break;
                            case "TANK":
                                if (TankCharacter.isDead()) {
                                    System.out.println("Tank is dead you can not use it, please try another character.");
                                } else {
                                    checkpoint = true;
                                }
                                break;
                            case "HEALER":
                                if (HealerCharacter.isDead()) {
                                    System.out.println("Healer is dead you can not use it, please try another character.");
                                } else {
                                    checkpoint = true;
                                }
                                break;
                        }
                    }

                    //calculating the damage for each weapon and character
                    Character.calculateDamage(FighterCharacter, TankCharacter, HealerCharacter, weapons, W1, W2, W3, command);

                    boolean breakpoint = false;
                    while (!breakpoint) {
                        System.out.println(
                                "\n" +
                                        "Please choose an action from actions list : "                          + "\n" +
                                        "To attack enter 'ATTACK'."                                             + "\n" +
                                        "To make a special action enter 'SPECIAL ACTION'."                      + "\n" +
                                        "To wear an armor enter 'WEAR'."                                        + "\n" +
                                        "To show the items in the inventory enter 'INVENTORY'."                 + "\n"
                        );

                        String option = input.nextLine();
                        option = option.toUpperCase();

                        switch (option) {
                            case "ATTACK":
                                attackAction(e, command, userChoice, FighterCharacter, TankCharacter, HealerCharacter, NOE, input);
                                breakpoint = true;
                                break;
                            case "SPECIAL ACTION":
                                specialAction(FighterCharacter, TankCharacter, HealerCharacter, weapons, W1, W2, W3, command, breakpoint);
                                break;
                            case "WEAR":
                                printClothingList(clothings);
                                System.out.println(
                                        "Please, choose an armor for the character : " + "\n" +
                                                "Valid values : '1', '2', etc..."
                                );
                                int C = input.nextInt();
                                switch (command) {
                                    case "FIGHTER":
                                        for (int i = 0; i < clothings.getItems().size(); i++) {
                                            if (C - 1 == i) {
                                                System.out.print(
                                                        clothings.getItems().get(i).getItemName() + ", with " + clothings.getItems().get(i).getValue() + " protection and "
                                                                + clothings.getItems().get(i).getWeight() + " units of weight has been added to the fighter's inventory successfully." + "\n"
                                                );
                                                FighterCharacter.inventory.add(clothings.items.get(i));
                                            }
                                        }
                                        break;
                                    case "TANK":
                                        for (int i = 0; i < clothings.getItems().size(); i++) {
                                            if (C - 1 == i) {
                                                System.out.print(
                                                        clothings.getItems().get(i).getItemName() + ", with " + clothings.getItems().get(i).getValue() + " protection and "
                                                                + clothings.getItems().get(i).getWeight() + " units of weight has been added to the tank's inventory successfully." + "\n"
                                                );
                                                TankCharacter.inventory.add(clothings.items.get(i));
                                            }
                                        }
                                        break;
                                    case "HEALER":
                                        for (int i = 0; i < clothings.getItems().size(); i++) {
                                            if (C - 1 == i) {
                                                System.out.print(
                                                        clothings.getItems().get(i).getItemName() + ", with " + clothings.getItems().get(i).getValue() + " protection and "
                                                                + clothings.getItems().get(i).getWeight() + " units of weight has been added to the healer's inventory successfully." + "\n"
                                                );
                                                HealerCharacter.inventory.add(clothings.items.get(i));
                                            }
                                        }
                                        break;
                                }
                                break;
                            case "INVENTORY":
                                printInventoryList(FighterCharacter, TankCharacter, HealerCharacter, command);
                                break;
                        }
                    }

                    //check point
                    if (e[0].isDead() && e[1].isDead()) {
                        e = null;
                        break;
                    }

                    EnemyCharacter.enemyAttack(FighterCharacter, TankCharacter, HealerCharacter, EnemyCharacter, weapons, e, NOE);

                }

                System.out.println("YOU WON, please write 'NEXT' if you wanna continue to the next level.");
                input.nextLine();
                command = input.nextLine();
                if (command.equals("NEXT") || command.equals("next")) { Level++; }

                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");

                //creating new level                                                                                     - LEVEL 2 -
                createNewLevel();

                //creating characters
                e = EnemySoldier.createEnemy(NOE);
                EnemyCharacter.printCreatEnemy(NOE, e);

                join(NOE);

                while (true) {
                    boolean checkpoint = false;
                    while (!checkpoint) {
                        //attacking...
                        System.out.println(
                                "Which character you will use?" + "\n" +
                                        "Valid values : 'FIGHTER', 'TANK', 'HEALER'" + "\n"
                        );

                        input.nextLine();
                        command = input.nextLine();
                        command = command.toUpperCase();

                        switch (command) {
                            case "FIGHTER":
                                if (FighterCharacter.isDead()) {
                                    System.out.println("Fighter is dead you can not use it, please try another character.");
                                } else {
                                    checkpoint = true;
                                }
                                break;
                            case "TANK":
                                if (TankCharacter.isDead()) {
                                    System.out.println("Tank is dead you can not use it, please try another character.");
                                } else {
                                    checkpoint = true;
                                }
                                break;
                            case "HEALER":
                                if (HealerCharacter.isDead()) {
                                    System.out.println("Healer is dead you can not use it, please try another character.");
                                } else {
                                    checkpoint = true;
                                }
                                break;
                        }
                    }

                    //calculating the damage for each weapon and character
                    Character.calculateDamage(FighterCharacter, TankCharacter, HealerCharacter, weapons, W1, W2, W3, command);

                    boolean breakpoint = false;
                    while (!breakpoint) {
                        System.out.println(
                                "\n" +
                                        "Please choose an action from the actions list : "                      + "\n" +
                                        "To attack enter 'ATTACK'."                                             + "\n" +
                                        "To make a special action enter 'SPECIAL ACTION'."                      + "\n" +
                                        "To wear an armor enter 'WEAR'."                                        + "\n" +
                                        "To show the items in the inventory enter 'INVENTORY'."                 + "\n"
                        );

                        String option = input.nextLine();
                        option = option.toUpperCase();

                        switch (option) {
                            case "ATTACK":
                                attackAction(e, command, userChoice, FighterCharacter, TankCharacter, HealerCharacter, NOE, input);
                                breakpoint = true;
                                break;
                            case "SPECIAL ACTION":
                                specialAction(FighterCharacter, TankCharacter, HealerCharacter, weapons, W1, W2, W3, command, breakpoint);
                                break;
                            case "WEAR":
                                printClothingList(clothings);
                                System.out.println(
                                        "Please, choose an armor for the character : " + "\n" +
                                                "Valid values : '1', '2', etc..."
                                );
                                int C = input.nextInt();
                                switch (command) {
                                    case "FIGHTER":
                                        for (int i = 0; i < clothings.getItems().size(); i++) {
                                            if (C - 1 == i) {
                                                System.out.print(
                                                        clothings.getItems().get(i).getItemName() + ", with " + clothings.getItems().get(i).getValue() + " protection and "
                                                                + clothings.getItems().get(i).getWeight() + " units of weight has been added to the fighter's inventory successfully." + "\n"
                                                );
                                                FighterCharacter.inventory.add(clothings.items.get(i));
                                            }
                                        }
                                        break;
                                    case "TANK":
                                        for (int i = 0; i < clothings.getItems().size(); i++) {
                                            if (C - 1 == i) {
                                                System.out.print(
                                                        clothings.getItems().get(i).getItemName() + ", with " + clothings.getItems().get(i).getValue() + " protection and "
                                                                + clothings.getItems().get(i).getWeight() + " units of weight has been added to the tank's inventory successfully." + "\n"
                                                );
                                                TankCharacter.inventory.add(clothings.items.get(i));
                                            }
                                        }
                                        break;
                                    case "HEALER":
                                        for (int i = 0; i < clothings.getItems().size(); i++) {
                                            if (C - 1 == i) {
                                                System.out.print(
                                                        clothings.getItems().get(i).getItemName() + ", with " + clothings.getItems().get(i).getValue() + " protection and "
                                                                + clothings.getItems().get(i).getWeight() + " units of weight has been added to the healer's inventory successfully." + "\n"
                                                );
                                                HealerCharacter.inventory.add(clothings.items.get(i));
                                            }
                                        }
                                        break;
                                }
                                break;
                            case "INVENTORY":
                                printInventoryList(FighterCharacter, TankCharacter, HealerCharacter, command);
                                break;
                        }
                    }

                    //check point
                    if (e[0].isDead() && e[1].isDead() && e[2].isDead() && e[3].isDead()) {
                        e = null;
                        break;
                    }

                    EnemyCharacter.enemyAttack(FighterCharacter, TankCharacter, HealerCharacter, EnemyCharacter, weapons, e, NOE);

                }

                System.out.println("YOU WON, please write 'NEXT' if you wanna continue to the next level.");
                input.nextLine();
                command = input.nextLine();
                if (command.equals("NEXT") || command.equals("next")) { Level++;  }

                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");

                //creating new level                                                                                     - LEVEL 3 -
                createNewLevel();

                //creating characters
                e = EnemySoldier.createEnemy(NOE);
                EnemyCharacter.printCreatEnemy(NOE, e);

                join(NOE);

                while (true) {
                    boolean checkpoint = false;
                    while (!checkpoint) {
                        //attacking...
                        System.out.println(
                                "Which character you will use?" + "\n" +
                                        "Valid values : 'FIGHTER', 'TANK', 'HEALER'" + "\n"
                        );

                        input.nextLine();
                        command = input.nextLine();
                        command = command.toUpperCase();

                        switch (command) {
                            case "FIGHTER":
                                if (FighterCharacter.isDead()) {
                                    System.out.println("Fighter is dead you can not use it, please try another character.");
                                } else {
                                    checkpoint = true;
                                }
                                break;
                            case "TANK":
                                if (TankCharacter.isDead()) {
                                    System.out.println("Tank is dead you can not use it, please try another character.");
                                } else {
                                    checkpoint = true;
                                }
                                break;
                            case "HEALER":
                                if (HealerCharacter.isDead()) {
                                    System.out.println("Healer is dead you can not use it, please try another character.");
                                } else {
                                    checkpoint = true;
                                }
                                break;
                        }
                    }

                    //calculating the damage for each weapon and character
                    Character.calculateDamage(FighterCharacter, TankCharacter, HealerCharacter, weapons, W1, W2, W3, command);

                    boolean breakpoint = false;
                    while (!breakpoint) {
                        System.out.println(
                                "\n" +
                                        "Please choose an action from the actions list : "                      + "\n" +
                                        "To attack enter 'ATTACK'."                                             + "\n" +
                                        "To make a special action enter 'SPECIAL ACTION'."                      + "\n" +
                                        "To wear an armor enter 'WEAR'."                                        + "\n" +
                                        "To show the items in the inventory enter 'INVENTORY'."                 + "\n"
                        );

                        String option = input.nextLine();
                        option = option.toUpperCase();

                        switch (option) {
                            case "ATTACK":
                                attackAction(e, command, userChoice, FighterCharacter, TankCharacter, HealerCharacter, NOE, input);
                                breakpoint = true;
                                break;
                            case "SPECIAL ACTION":
                                specialAction(FighterCharacter, TankCharacter, HealerCharacter, weapons, W1, W2, W3, command, breakpoint);
                                break;
                            case "WEAR":
                                printClothingList(clothings);
                                System.out.println(
                                        "Please, choose an armor for the character : " + "\n" +
                                                "Valid values : '1', '2', etc..."
                                );
                                int C = input.nextInt();
                                switch (command) {
                                    case "FIGHTER":
                                        for (int i = 0; i < clothings.getItems().size(); i++) {
                                            if (C - 1 == i) {
                                                System.out.print(
                                                        clothings.getItems().get(i).getItemName() + ", with " + clothings.getItems().get(i).getValue() + " protection and "
                                                                + clothings.getItems().get(i).getWeight() + " units of weight has been added to the fighter's inventory successfully." + "\n"
                                                );
                                                FighterCharacter.inventory.add(clothings.items.get(i));
                                            }
                                        }
                                        break;
                                    case "TANK":
                                        for (int i = 0; i < clothings.getItems().size(); i++) {
                                            if (C - 1 == i) {
                                                System.out.print(
                                                        clothings.getItems().get(i).getItemName() + ", with " + clothings.getItems().get(i).getValue() + " protection and "
                                                                + clothings.getItems().get(i).getWeight() + " units of weight has been added to the tank's inventory successfully." + "\n"
                                                );
                                                TankCharacter.inventory.add(clothings.items.get(i));
                                            }
                                        }
                                        break;
                                    case "HEALER":
                                        for (int i = 0; i < clothings.getItems().size(); i++) {
                                            if (C - 1 == i) {
                                                System.out.print(
                                                        clothings.getItems().get(i).getItemName() + ", with " + clothings.getItems().get(i).getValue() + " protection and "
                                                                + clothings.getItems().get(i).getWeight() + " units of weight has been added to the healer's inventory successfully." + "\n"
                                                );
                                                HealerCharacter.inventory.add(clothings.items.get(i));
                                            }
                                        }
                                        break;
                                }
                                break;
                            case "INVENTORY":
                                printInventoryList(FighterCharacter, TankCharacter, HealerCharacter, command);
                                break;
                        }
                    }

                    //check point
                    if (e[0].isDead() && e[1].isDead() && e[2].isDead() && e[3].isDead() && e[4].isDead() && e[5].isDead()) {
                        e = null;
                        break;
                    }

                    EnemyCharacter.enemyAttack(FighterCharacter, TankCharacter, HealerCharacter, EnemyCharacter, weapons, e, NOE);

                }

                System.out.println("YOU WON, please write 'NEXT' if you wanna continue to the next level.");
                input.nextLine();
                command = input.nextLine();
                if (command.equals("NEXT") || command.equals("next")) { Level++; }

                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");


                //creating new level                                                                                     - LEVEL 4 -
                createNewLevel();

                //creating characters
                e = EnemySoldier.createEnemy(NOE);
                EnemyCharacter.printCreatEnemy(NOE, e);

                join(NOE);

                while (true) {
                    boolean checkpoint = false;
                    while (!checkpoint) {
                        //attacking...
                        System.out.println(
                                "Which character you will use?" + "\n" +
                                        "Valid values : 'FIGHTER', 'TANK', 'HEALER'" + "\n"
                        );

                        input.nextLine();
                        command = input.nextLine();
                        command = command.toUpperCase();

                        switch (command) {
                            case "FIGHTER":
                                if (FighterCharacter.isDead()) {
                                    System.out.println("Fighter is dead you can not use it, please try another character.");
                                } else {
                                    checkpoint = true;
                                }
                                break;
                            case "TANK":
                                if (TankCharacter.isDead()) {
                                    System.out.println("Tank is dead you can not use it, please try another character.");
                                } else {
                                    checkpoint = true;
                                }
                                break;
                            case "HEALER":
                                if (HealerCharacter.isDead()) {
                                    System.out.println("Healer is dead you can not use it, please try another character.");
                                } else {
                                    checkpoint = true;
                                }
                                break;
                        }
                    }

                    //calculating the damage for each weapon and character
                    Character.calculateDamage(FighterCharacter, TankCharacter, HealerCharacter, weapons, W1, W2, W3, command);

                    boolean breakpoint = false;
                    while (!breakpoint) {
                        System.out.println(
                                "\n" +
                                        "Please choose an action from the actions list : "                      + "\n" +
                                        "To attack enter 'ATTACK'."                                             + "\n" +
                                        "To make a special action enter 'SPECIAL ACTION'."                      + "\n" +
                                        "To wear an armor enter 'WEAR'."                                        + "\n" +
                                        "To show the items in the inventory enter 'INVENTORY'."                 + "\n"
                        );

                        String option = input.nextLine();
                        option = option.toUpperCase();

                        switch (option) {
                            case "ATTACK":
                                attackAction(e, command, userChoice, FighterCharacter, TankCharacter, HealerCharacter, NOE, input);
                                breakpoint = true;
                                break;
                            case "SPECIAL ACTION":
                                specialAction(FighterCharacter, TankCharacter, HealerCharacter, weapons, W1, W2, W3, command, breakpoint);
                                break;
                            case "WEAR":
                                printClothingList(clothings);
                                System.out.println(
                                        "Please, choose an armor for the character : " + "\n" +
                                                "Valid values : '1', '2', etc..."
                                );
                                int C = input.nextInt();
                                switch (command) {
                                    case "FIGHTER":
                                        for (int i = 0; i < clothings.getItems().size(); i++) {
                                            if (C - 1 == i) {
                                                System.out.print(
                                                        clothings.getItems().get(i).getItemName() + ", with " + clothings.getItems().get(i).getValue() + " protection and "
                                                                + clothings.getItems().get(i).getWeight() + " units of weight has been added to the fighter's inventory successfully." + "\n"
                                                );
                                                FighterCharacter.inventory.add(clothings.items.get(i));
                                            }
                                        }
                                        break;
                                    case "TANK":
                                        for (int i = 0; i < clothings.getItems().size(); i++) {
                                            if (C - 1 == i) {
                                                System.out.print(
                                                        clothings.getItems().get(i).getItemName() + ", with " + clothings.getItems().get(i).getValue() + " protection and "
                                                                + clothings.getItems().get(i).getWeight() + " units of weight has been added to the tank's inventory successfully." + "\n"
                                                );
                                                TankCharacter.inventory.add(clothings.items.get(i));
                                            }
                                        }
                                        break;
                                    case "HEALER":
                                        for (int i = 0; i < clothings.getItems().size(); i++) {
                                            if (C - 1 == i) {
                                                System.out.print(
                                                        clothings.getItems().get(i).getItemName() + ", with " + clothings.getItems().get(i).getValue() + " protection and "
                                                                + clothings.getItems().get(i).getWeight() + " units of weight has been added to the healer's inventory successfully." + "\n"
                                                );
                                                HealerCharacter.inventory.add(clothings.items.get(i));
                                            }
                                        }
                                        break;
                                }
                                break;
                            case "INVENTORY":
                                printInventoryList(FighterCharacter, TankCharacter, HealerCharacter, command);
                                break;
                        }
                    }

                    //check point
                    if (e[0].isDead() && e[1].isDead() && e[2].isDead() && e[3].isDead() && e[4].isDead() && e[5].isDead() && e[6].isDead() && e[7].isDead()) {
                        e = null;
                        break;
                    }

                    EnemyCharacter.enemyAttack(FighterCharacter, TankCharacter, HealerCharacter, EnemyCharacter, weapons, e, NOE);

                }

                System.out.println("YOU WON, this is the end of the game, thank you for your valiant fisticuffery. <33");

                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");

                //break;
                //quit the game
            case 2:
                System.out.println("Exit the game...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid value!");
        }
    }





    // methods ----------------------------------------------------------------------------------------------------------------------------------------------------------

    private static void printWeaponsList(Weapons weapons) {
        System.out.println("Weapons List : ");
        for (int i = 0; i < weapons.getItems().size(); i++) {
            System.out.println(
                    (i + 1) + ". " + weapons.getItems().get(i).getItemName() + " - Weight : " +
                            weapons.getItems().get(i).getWeight() + " - Damage : " +
                            weapons.getItems().get(i).getValue()
            );
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    private static void printClothingList(Clothings clothings) {
        System.out.println("Clothings List : ");
        for ( int ii = 0; ii < clothings.getItems().size(); ii++) {
            System.out.println(
                    (ii + 1) + ". "  + clothings.getItems().get(ii).getItemName() + " - Weight : " +
                            clothings.getItems().get(ii).getWeight()   + " - Damage : " +
                            clothings.getItems().get(ii).getValue()
            );
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    private static void printInventoryList(Character FighterCharacter, Character TankCharacter, Character HealerCharacter, String command) {
        switch (command) {
            case "FIGHTER": if (FighterCharacter.getInventory().isEmpty()) { System.out.println("The inventory is empty."); } else { FighterCharacter.printInventory(); }  break;
            case "TANK":    if (TankCharacter.getInventory().isEmpty())    { System.out.println("The inventory is empty."); } else { TankCharacter.printInventory();    }  break;
            case "HEALER":  if (HealerCharacter.getInventory().isEmpty())  { System.out.println("The inventory is empty."); } else { HealerCharacter.printInventory();  }  break;
        }
    }
    static int killcounter=0;
    private static void attackAction(EnemySoldier[] e, String command, int userChoice, Character FighterCharacter, Character TankCharacter, Character HealerCharacter, int NOEnemy, Scanner input) {
        switch (command) {
            case "FIGHTER":  killcounter+= FighterCharacter.attack(userChoice, e, input);   break;
            case "TANK":    killcounter+=  TankCharacter.attack(userChoice, e, input);      break;
            case "HEALER":  killcounter+=  HealerCharacter.attack(userChoice, e, input);    break;
        }
    }
    private static void specialAction(Character FighterCharacter, Character TankCharacter, Character HealerCharacter, Weapons weapons, int W1, int W2, int W3, String command, Boolean breakpoint) {
        switch (command) {
            case "FIGHTER":   FighterCharacter.specialAction(FighterCharacter, TankCharacter, HealerCharacter, weapons, W1, input, breakpoint);   break;
            case "TANK":      TankCharacter.specialAction(FighterCharacter, TankCharacter, HealerCharacter, weapons, W2, input, breakpoint);      break;
            case "HEALER":    HealerCharacter.specialAction(FighterCharacter, TankCharacter, HealerCharacter, weapons, W3, input, breakpoint);    break;
        }
    }

    public static void createNewLevel() {
        double noe;
        // The levels will start from 0. For each level n, there will be 2n enemies.
        if (Level == 0) {
            noe = 1.0;
            NOE = (int)noe;
        } else {
            noe = Math.pow(2,Level);
            NOE = (int)noe;
        }
        System.out.println(
                "Creating Level " + Level + ", with " + NOE + " enemy soldier/s." +
                        "\n" + "Entering Level "+ Level + " ..."
        );
    }

    private static void join(int NOE) {
        System.out.print(
                "Fighter enters. Tank enters. Healer enters."
        );
        for (int i = 1; i <= NOE; i++) {
            System.out.print(" Enemy " + i + " enters.");
        }

        System.out.println();
    }
}

