package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        CustomArrayList<Integer> arrayList = new CustomArrayList<>();
        arrayList.add(444);
        arrayList.add(57);
        arrayList.add(179);
        for (int i = 0; i < arrayList.length(); i++) {
            System.out.print(arrayList.get(i) + " "); //добавление элемента
        }
        arrayList.remove(1);
        for (int i = 0; i < arrayList.length(); i++) {
            System.out.print(arrayList.get(i) + " "); //добавление элемента
        }
    }
}