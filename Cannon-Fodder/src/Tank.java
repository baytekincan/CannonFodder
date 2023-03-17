public class Tank extends Character {

    public Tank() {
        setName("Tank");
        setStrength(generateRandomValue(5,1));
        setVitality(generateRandomValue(10,6));
        setIntelligence(generateRandomValue(7,3));
        setHealthPoints(Math.round(0.7 * getVitality() + 0.2 * getStrength() + 0.1 * getIntelligence()));
        setDead(false);
    }

    public void createCharacter2(Weapons weapons, int W2) {
        setMaxHP(getHealthPoints());
        System.out.print(
                getName() + " created with S: " + getStrength() + ", V: " + getVitality() +
                ", I: " + getIntelligence() + ". The HP is " + getHealthPoints() + "."
        );
        for (int ii = 0; ii < weapons.getItems().size(); ii++) {
            if ((W2-1) == ii) {
                System.out.print(
                        " Tank wields " + weapons.getItems().get(ii).getItemName() +
                        ", with " + weapons.getItems().get(ii).getValue() + " damage and "
                        + weapons.getItems().get(ii).getWeight() + " units of weight." + "\n"
                );
                inventory.add(weapons.items.get(ii));
            }
        }
    }

    @Override
    public void updateHP() {
        super.updateHP();
        System.out.println("Tank health points increased by " + (getHealthPoints()/2));
        setHealthPoints(getHealthPoints() + (getHealthPoints() / 2));
        System.out.println("Tank has " + getHealthPoints() + " HP now." + "\n");
        if (getHealthPoints() > getMaxHP()) {
            setHealthPoints(getMaxHP());
        }
    }
}
