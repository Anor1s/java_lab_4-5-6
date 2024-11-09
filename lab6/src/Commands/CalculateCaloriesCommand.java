package Commands;

import Model.Chef;

public class CalculateCaloriesCommand implements Command {
    private Chef chef;

    public CalculateCaloriesCommand(Chef chef) {
        this.chef = chef;
    }

    @Override
    public void execute() {
        chef.calculateCalories();
    }

    @Override
    public String toString() {
        return "Порахувати калорії салату";
    }
}
