package Commands;

import Model.Chef;

public class SortIngredientsByIdCommand implements Command {
    private Chef chef;

    public SortIngredientsByIdCommand(Chef chef) {
        this.chef = chef;
    }

    @Override
    public void execute() {
        chef.sortIngredientsById();
    }

    @Override
    public String toString() {
        return "Відсортувати інгредиєнти у файлі по ID";
    }
}
