package org.example.animals;

public class Herbivores extends Animals {
    public Herbivores() {}

    private String food = "grass";

    public void eat() {
        System.out.println("This animal eates " + food);
    }
}
