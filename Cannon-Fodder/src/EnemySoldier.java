import java.io.IOException;

public class EnemySoldier extends Character {

    public EnemySoldier() {
        setName("Enemy Soldiers");
        setDead(false);
    }

    public static EnemySoldier[] createEnemy(int noe) {
        EnemySoldier[] enemies = new EnemySoldier[noe];
        for (int i = 0; i < noe; i++) {
            enemies[i] = new EnemySoldier();
            enemies[i].setStrength(generateRandomValue(70,69));
            enemies[i].setVitality(generateRandomValue(70,1));
            enemies[i].setIntelligence(generateRandomValue(5,1));
            enemies[i].setHealthPoints(0.7 * enemies[i].getVitality() + 0.2 * enemies[i].getStrength() + 0.1 * enemies[i].getIntelligence());
        }
        return enemies;
    }
    public void printCreatEnemy(int noe, EnemySoldier[] enemies) {
        for (int i = 0; i < noe; i++) {
            System.out.println(
                    "Enemy Soldiers Number. " + (i + 1) + " created with S: " + enemies[i].getStrength() +
                            ", V: " + enemies[i].getVitality() + ", I: " + enemies[i].getIntelligence()
                            + ". The HP is " + enemies[i].getHealthPoints() + ". "
            );
        }
    }
    public void enemyAttack(Character FighterCharacter, Character TankCharacter, Character HealerCharacter, Character EnemyCharacter, Weapons weapons, EnemySoldier[] enemies, int noe) {
        int chosenCharacter, chosenWeapon, chosenEnemy;
        if (!TankCharacter.isDead()) {
            chosenCharacter = 2;
        } else {
            chosenCharacter = generateRandomValue(3, 1);
        }
        chosenWeapon    = generateRandomValue(2,1);
        if (noe == 1) { chosenEnemy = 1; } else { chosenEnemy = generateRandomValue(noe,1); }
        boolean breakpoint = false;
        while (!breakpoint) {
            switch (chosenCharacter) {
                case 1:
                    if (!FighterCharacter.isDead()) {
                        if (!enemies[chosenEnemy - 1].isDead()) {
                            System.out.println("ENEMY " + chosenEnemy + " Attacking...");
                            EnemyCharacter.setDamage((weapons.getItems().get(chosenWeapon).getValue() * enemies[chosenEnemy - 1].getStrength()) / 100);
                            FighterCharacter.setHealthPoints(FighterCharacter.getHealthPoints() - EnemyCharacter.getDamage());
                            System.out.println("Enemy " + chosenEnemy + " attack fighter and does " + EnemyCharacter.getDamage() + " damage.");
                            if (FighterCharacter.getHealthPoints() < 0.0) {
                                FighterCharacter.setHealthPoints(0.0);
                                System.out.println("***** Fighter is dead. *****");
                                FighterCharacter.setDead(true);
                                if (FighterCharacter.isDead() && TankCharacter.isDead() && HealerCharacter.isDead()) {
                                    System.out.println("Game Over, you lose!");
                                    try {
                                        Main.makeinfointotext();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    System.out.close();
                                }
                            } else {
                                System.out.println("Fighter has " + FighterCharacter.getHealthPoints() + " HP left." + "\n");
                            }
                            breakpoint = true;
                        } else {
                            //enemy can not attack because it is dead.
                            chosenEnemy = generateRandomValue(noe, 1);
                        }
                    } else {
                        //dead character can not be attacked
                        chosenCharacter = generateRandomValue(3,1);
                    }
                    break;
                case 2:
                    if (!TankCharacter.isDead()) {
                        if (!enemies[chosenEnemy - 1].isDead()) {
                            System.out.println("ENEMY " + chosenEnemy + " Attacking...");
                            EnemyCharacter.setDamage((weapons.getItems().get(chosenWeapon).getValue() * enemies[chosenEnemy - 1].getStrength()) / 100);
                            TankCharacter.setHealthPoints(TankCharacter.getHealthPoints() - EnemyCharacter.getDamage());
                            System.out.println("Enemy " + chosenEnemy + " attack tank and does " + EnemyCharacter.getDamage() + " damage.");
                            if (TankCharacter.getHealthPoints() < 0.0) {
                                TankCharacter.setHealthPoints(0.0);
                                System.out.println("***** Tank is dead. *****");
                                TankCharacter.setDead(true);
                                if (FighterCharacter.isDead() && TankCharacter.isDead() && HealerCharacter.isDead()) {
                                    System.out.println("Game Over, you lose!");
                                    try {
                                        Main.makeinfointotext();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    System.out.close();
                                }
                            } else {
                                System.out.println("Tank has " + TankCharacter.getHealthPoints() + " HP left." + "\n");
                            }
                            breakpoint = true;
                        } else {
                            //enemy can not attack because it is dead.
                            chosenEnemy = generateRandomValue(noe, 1);
                        }
                    } else {
                        //dead character can not be attacked
                        chosenCharacter = generateRandomValue(3,1);
                    }
                    break;
                case 3:
                    if (!HealerCharacter.isDead()) {
                        if (!enemies[chosenEnemy - 1].isDead()) {
                            System.out.println("ENEMY " + chosenEnemy + " Attacking...");
                            EnemyCharacter.setDamage((weapons.getItems().get(chosenWeapon).getValue() * enemies[chosenEnemy - 1].getStrength()) / 100);
                            HealerCharacter.setHealthPoints(HealerCharacter.getHealthPoints() - EnemyCharacter.getDamage());
                            System.out.println("Enemy " + chosenEnemy + " attack healer and does " + EnemyCharacter.getDamage() + " damage.");
                            if (HealerCharacter.getHealthPoints() < 0.0) {
                                HealerCharacter.setHealthPoints(0.0);
                                System.out.println("***** Healer is dead. *****");
                                HealerCharacter.setDead(true);
                                if (FighterCharacter.isDead() && TankCharacter.isDead() && HealerCharacter.isDead()) {
                                    System.out.println("Game Over, you lose!");
                                    try {
                                        Main.makeinfointotext();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    System.out.close();
                                }
                            } else {
                                System.out.println("Healer has " + HealerCharacter.getHealthPoints() + " HP left." + "\n");
                            }
                            breakpoint = true;
                        } else {
                            //enemy can not attack because it is dead.
                            chosenEnemy = generateRandomValue(noe, 1);
                        }
                    } else {
                        //dead character can not be attacked
                        chosenCharacter = generateRandomValue(3,1);
                    }
                    break;
            }
        }
    }
}
