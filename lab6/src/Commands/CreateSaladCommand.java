package Commands;

import Model.Chef;

public class CreateSaladCommand implements Command {
    private Chef chef;

    public CreateSaladCommand(Chef chef) {
        this.chef = chef;
    }

    @Override
    public void execute() {
        chef.createSaladFromFile();
    }

    @Override
    public String toString() {
        return "Створити салат";
    }
}
