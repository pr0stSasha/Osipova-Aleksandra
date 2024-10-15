package org.example.animals;

public class Camel extends Herbivores implements Land{

    public Camel(){}

    @Override
    public void walk() {
        System.out.println("Camel flying");
    }
}
