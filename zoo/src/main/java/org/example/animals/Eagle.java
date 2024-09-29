package org.example.animals;

public class Eagle extends Predator implements Flying{
    public Eagle(){
        super("fish or meat");
    }

    @Override
    public void fly() {
        System.out.println("Eagle flying");
    }


}
