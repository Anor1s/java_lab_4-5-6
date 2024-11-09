package ModelFileManager;

import Model.Ingredient;
import Model.Meat;
import Model.Vegetable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Scanner;

public class IngredientFileManager {
    private static final String FILE_PATH = "ingredients.txt";
    Scanner scanner = new Scanner(System.in);

    public List<Ingredient> loadIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 5) {
                    int id = Integer.parseInt(parts[0].trim());
                    String type = parts[1].trim().toLowerCase();
                    String name = parts[2].trim();
                    double calories = Double.parseDouble(parts[3].trim());
                    double weight = Double.parseDouble(parts[4].trim());

                    Ingredient ingredient;
                    switch (type) {
                        case "м'ясо":
                            ingredient = new Meat(id, type, name, calories, weight);
                            break;
                        case "овоч":
                            ingredient = new Vegetable(id, type, name, calories, weight);
                            break;
                        default:
                            System.out.println("\nНеправильний тип інгредієнта");
                            continue;
                    }

                    ingredients.add(ingredient);
                }
            }
        } catch (IOException e) {
            System.out.println("Помилка при зчитуванні файлу: " + e.getMessage());
        }
        return ingredients;
    }


    public void addIngredient(int id, String type, String name, double calories, double weight) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(id + ", " + type + ", "  + name + ", " + calories + ", " + weight);
            writer.newLine();
        } catch (IOException error) {
            System.out.println("Помилка при додаванні інгредієнта: " + error.getMessage());
        }
    }

    public boolean ingredientExists(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (Integer.parseInt(parts[0]) == id) {
                    return true;
                }
            }
        } catch (IOException error) {
            System.out.println("Помилка при перевірці існування інгредієнта: " + error.getMessage());
        }
        return false;
    }

    public void deleteIngredient(int id) {
        List<Ingredient> ingredients = loadIngredients();
        boolean removed = ingredients.removeIf(ingredient -> ingredient.getId() == id);

        if (removed) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                for (Ingredient ingredient : ingredients) {
                    writer.write(ingredient.getId() + ", " + ingredient.getType() + ", " + ingredient.getName()
                            + ", " + ingredient.getCalories() + ", " + ingredient.getWeight());
                    writer.newLine();
                }
                System.out.println("Інгредієнт з ID " + id + " успішно видалено з файлу!");
            } catch (IOException error) {
                System.out.println("Помилка при видаленні інгредієнта: " + error.getMessage());
            }
        } else {
            System.out.println("Інгредієнт з id " + id + " не знайдено.");
        }
    }

    public void sortIngredientsById() {
        List<Ingredient> ingredients = loadIngredients();

        ingredients.sort(Comparator.comparingInt(Ingredient::getId));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Ingredient ingredient : ingredients) {
                writer.write(ingredient.getId() + ", " + ingredient.getType() + ", "+ ingredient.getName() + ", " + ingredient.getCalories() + ", " + ingredient.getWeight());
                writer.newLine();
            }
            System.out.println("Інгредієнти успішно відсортовані за ID!");
        } catch (IOException e) {
            System.out.println("Помилка при запису у файл: " + e.getMessage());
        }
    }
}
