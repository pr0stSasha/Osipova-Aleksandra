package org.example;

import org.example.animals.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Horse horse = new Horse();
        Tiger tiger = new Tiger();
        Eagle eagle = new Eagle();
        Dolphin dolphin = new Dolphin();
        Camel camel = new Camel();
        horse.eat();
        horse.walk();
        tiger.eat();
        tiger.walk();
        eagle.eat();
        eagle.fly();
        dolphin.eat();
        dolphin.swim();
        camel.eat();
        camel.walk();
    }
}