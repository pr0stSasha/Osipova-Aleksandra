package org.example.animals;

public class Tiger extends Predator implements Land {


    public Tiger(){
        super("meat");
    }

    @Override
    public void walk() {
        System.out.println("Tiger walk");
    }

}
