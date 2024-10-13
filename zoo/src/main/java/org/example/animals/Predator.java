package org.example.animals;

public class Predator extends Animals {
    private String food = null;

    public Predator(String food) {
        this.food = food;
    }

    public void eat() {
        System.out.println("This animal eates " + food);
    }
}
