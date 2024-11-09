package Model;

import ModelFileManager.IngredientFileManager;

import java.util.List;
import java.util.Scanner;

public class Chef {
    private Salad salad = new Salad();
    private IngredientFileManager fileManager = new IngredientFileManager();
    Scanner scanner = new Scanner(System.in);
    List<Ingredient> ingredients = fileManager.loadIngredients();

    public void createSaladFromFile() {
        salad = new Salad();

        System.out.println("\nСписок доступних інгредієнтів:");
        ingredientsFromFileInfo();

        while (true) {
            System.out.print("\nВведіть ID інгредієнта для додавання до салату (0 для завершення): ");
            int choice = scanner.nextInt();
            if (choice == 0) break; // Завершення вибору

            Ingredient selectedIngredient = null;
            for (Ingredient ingredient : ingredients) {
                if (ingredient.getId() == choice) {
                    selectedIngredient = ingredient;
                    break;
                }
            }

            if (selectedIngredient != null) {
                System.out.print("Введіть кількість грам для інгредієнту " + selectedIngredient.getName()
                        + " (" + selectedIngredient.getType() + " )" + ": ");
                double weight = scanner.nextDouble();
                double calories = (selectedIngredient.getCalories() / 100) * weight;

                if (selectedIngredient instanceof Vegetable) {
                    salad.addIngredient(new Vegetable(selectedIngredient.getId(), selectedIngredient.getType(),
                            selectedIngredient.getName(), calories, weight));
                } else if (selectedIngredient instanceof Meat) {
                    salad.addIngredient(new Meat(selectedIngredient.getId(), selectedIngredient.getType(),
                            selectedIngredient.getName(), calories, weight));
                }

                System.out.println(selectedIngredient.getName() + " додано до салату!");
            } else {
                System.out.println("\nНеправильний вибір, спробуйте ще раз.");
            }
        }

        System.out.println("\nСалат створено з обраних інгредієнтів:");
        ingredientsSaladInfo();
    }

    public void addIngredientToFile() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть ID інгредієнта: ");
        int id = scanner.nextInt();

        if (fileManager.ingredientExists(id)) {
            System.out.println("Інгредієнт з ID " + id + " вже існує. Спробуйте інший ID.");
            return;
        }

        scanner.nextLine();
        System.out.print("Введіть назву інгредієнта: ");
        String name = scanner.nextLine();
        System.out.print("Введіть калорійність (ккал на 100 г): ");
        double calories = scanner.nextDouble();

        scanner.nextLine();
        System.out.print("Введіть тип інгредієнта \n1.М'ясо \n2.овоч: ");
        String choice = scanner.nextLine();

        String type = scanner.nextLine();
        switch (choice) {
            case "1":
                type = "овоч";
                fileManager.addIngredient(id, type, name, calories, 100);
                break;
            case "2":
                type = "м'ясо";
                fileManager.addIngredient(id, type, name, calories, 100);
                break;
            default:
                System.out.println("\nНеправильний тип інгредієнта");
                addIngredientToFile();
        }

        System.out.println("Інгредієнт успішно додано до файлу!");
    }

    public void deleteIngredientFromFile() {
        System.out.println("\nСписок інгредієнтів:");
        ingredientsFromFileInfo();

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nВведіть ID інгредієнта для видалення: ");
        int id = scanner.nextInt();

        fileManager.deleteIngredient(id);
    }

    public void calculateCalories() {
        double totalCalories = salad.calculateCalories();
        System.out.println("\nКалорійність салату: " + String.format("%.2f", totalCalories) + " ккал");
    }

    public void sortVegetables(SortParameter parameter) {
        switch (parameter) {
            case CALORIES:
                salad.sortByParameter((i1, i2) -> Double.compare(i1.getCalories(), i2.getCalories()));
                System.out.println("\nІнгредієнти салату відсортовано за калорійністю!");
                ingredientsSaladInfo();
                break;
            case WEIGHT:
                salad.sortByParameter((i1, i2) -> Double.compare(i1.getWeight(), i2.getWeight()));
                System.out.println("\nІнгредієнти салату відсортовано за вагою!");
                ingredientsSaladInfo();
                break;
            default:
                System.out.println("\nНевірний параметр сортування!");
                break;
        }
    }

    public void sortIngredientsById() {
        fileManager.sortIngredientsById();
    }

    public void findIngredientsByCalories() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nВведіть мінімальну калорійність: ");
        double minCalories = scanner.nextDouble();
        System.out.print("Введіть максимальну калорійність: ");
        double maxCalories = scanner.nextDouble();

        List<Ingredient> foundIngredients = salad.findIngredientsByCalories(minCalories, maxCalories);
        System.out.println("Інгредієнти у вказаному діапазоні калорій:");
        ingredientsFromFileInfo();
    }

    public void ingredientsFromFileInfo(){
        for (Ingredient ingredient : ingredients) {
            System.out.println(ingredient.getId() + ", " + ingredient.getType() + ", " + ingredient.getName() + ", " +
                     ingredient.getCalories() + ", " + ingredient.getWeight());
        }
    }

    public void ingredientsSaladInfo(){
        for (Ingredient ingredient : salad.getIngredients()) {
            System.out.println(ingredient.getId() + ", " + ingredient.getName() + ", " + ingredient.getName()
                    + ", " + ingredient.getCalories() + ", " + ingredient.getWeight());
        }
    }
}
