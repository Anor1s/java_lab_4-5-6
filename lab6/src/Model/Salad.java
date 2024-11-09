package Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Salad {
    private List<Ingredient> ingredients = new ArrayList<>();

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public double calculateCalories() {
        return ingredients.stream().mapToDouble(ingredient -> (ingredient.getCalories() / 100) * ingredient.getWeight()).sum();
    }

    public void sortByParameter(Comparator<Ingredient> comparator) {
        ingredients.sort(comparator);
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Ingredient> findIngredientsByCalories(double minCalories, double maxCalories) {
        List<Ingredient> result = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getCalories() >= minCalories && ingredient.getCalories() <= maxCalories) {
                result.add(ingredient);
            }
        }
        return result;
    }
}
