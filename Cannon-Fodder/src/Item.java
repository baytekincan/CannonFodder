public class Item {

    private String itemName;
    private double weight; // the weight affect the character's strength ----
    private double value;  // "damage or protection value” this increase the character's vitality or strength ++++
    private String type;

    public Item() {
        itemName = "UNKNOWN";
        weight   = 0.0;
        value    = 0.0;
        type     = "UNKNOWN";
    }

    public Item(String name, String type, double value, double weight) {
        this.itemName = name;
        this.weight   = weight;
        this.value    = value;
        this.type     = type;
    }

    public String getItemName() { return itemName; }
    public void setItemName(String name) { this.itemName = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }
}
