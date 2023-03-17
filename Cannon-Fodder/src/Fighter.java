public class Fighter extends Character {

    public Fighter() {
        setName("Fighter");
        setStrength(generateRandomValue(10,6));
        setVitality(generateRandomValue(7,3));
        setIntelligence(generateRandomValue(5,1));
        setHealthPoints(Math.round(0.7 * getVitality() + 0.2 * getStrength() + 0.1 * getIntelligence()));
        setDead(false);
    }

    public void createCharacter1(Weapons weapons, int W1) {
        setMaxHP(getHealthPoints());
        System.out.print(
                getName() + " created with S: " + getStrength() + ", V: " + getVitality() +
                ", I: " + getIntelligence() + ". The HP is " + getHealthPoints() + "."
        );
        for (int i = 0; i < weapons.getItems().size(); i++) {
            if ((W1-1) == i) {
                System.out.print(
                        " Fighter wields " + weapons.getItems().get(i).getItemName() +
                        ", with " + weapons.getItems().get(i).getValue() + " damage and "
                        + weapons.getItems().get(i).getWeight() + " units of weight." + "\n"
                );
                inventory.add(weapons.items.get(i));
            }
        }
    }

    @Override
    public void updateHP() {
        super.updateHP();
        System.out.println("Fighter health points increased by " + (getHealthPoints()/2));
        setHealthPoints(getHealthPoints() + (getHealthPoints() / 2));
        System.out.println("Fighter has " + getHealthPoints() + " HP now." + "\n");
        if (getHealthPoints() > getMaxHP()) {
            setHealthPoints(getMaxHP());
        }
    }
}
