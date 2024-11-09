package Model;

public abstract class Ingredient {
    private int id;
    private String type;
    private String name;
    private double calories;
    private double weight;

    public Ingredient(int id, String type, String name, double calories, double weight) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.calories = calories;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public double getCalories() {
        return calories;
    }

    public double getWeight() {
        return weight;
    }
}
