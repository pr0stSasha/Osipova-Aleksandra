package org.example.animals;

public class Horse extends Herbivores implements Land{

    public Horse(){}

    @Override
    public void walk() {
        System.out.println("Horse walk");
    }
}
