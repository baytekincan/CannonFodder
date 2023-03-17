public class Healer extends Character {

    public Healer() {
        setName("Healer");
        setStrength(generateRandomValue(7,3));
        setVitality(generateRandomValue(5,1));
        setIntelligence(generateRandomValue(10,6));
        setHealthPoints(Math.round(0.7 * getVitality() + 0.2 * getStrength() + 0.1 * getIntelligence()));
        setDead(false);
    }

    public void createCharacter3(Weapons weapons, int W3) {
        setMaxHP(getHealthPoints());
        System.out.print(
                getName() + " created with S: " + getStrength() + ", V: " + getVitality() +
                ", I: " + getIntelligence() + ". The HP is " + getHealthPoints() + "."
        );
        for (int iii = 0; iii < weapons.getItems().size(); iii++) {
            if ((W3-1) == iii) {
                System.out.print(
                        " Healer wields " + weapons.getItems().get(iii).getItemName() +
                        ", with " + weapons.getItems().get(iii).getValue() + " damage and "
                        + weapons.getItems().get(iii).getWeight() + " units of weight." + "\n"
                );
                inventory.add(weapons.items.get(iii));
            }
        }
    }

    @Override
    public void updateHP() {
        super.updateHP();
        System.out.println("Healer health points increased by " + (getHealthPoints()/2));
        setHealthPoints(getHealthPoints() + (getHealthPoints() / 2));
        System.out.println("Healer has " + getHealthPoints() + " HP now." + "\n");
        if (getHealthPoints() > getMaxHP()) {
            setHealthPoints(getMaxHP());
        }
    }
}
