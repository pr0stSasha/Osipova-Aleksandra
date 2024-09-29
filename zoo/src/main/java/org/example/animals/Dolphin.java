package org.example.animals;

public class Dolphin extends Predator implements Waterfowl {

    public Dolphin() {
        super("fish");
    }

    @Override
    public void swim() {
        System.out.println("Dolphin swimming");
    }
}
