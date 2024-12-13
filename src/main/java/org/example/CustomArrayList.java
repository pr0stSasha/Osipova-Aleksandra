package org.example;

import java.lang.reflect.Array;

public class CustomArrayList<T extends Comparable <T>> implements methods<T> {

    T[] arrayList = (T[]) new Comparable[0];

    public int length() {
        return arrayList.length;
    }

    public void add(T adding_element) {
        int new_length;
        if (arrayList == null) {
            new_length = 1;
        } else {
            new_length = arrayList.length + 1;
        }
        final T[] rezulted_array_list = (T[]) Array.newInstance(arrayList.getClass().getComponentType(), new_length);
        for (int i = 0; i < arrayList.length; i++) {
            rezulted_array_list[i] = arrayList[i];
        }
        rezulted_array_list[new_length - 1] = adding_element;
        arrayList = rezulted_array_list;
        return;
    }

    public T get(int index) {
        return arrayList[index];
    }

    public void remove(int index) {
        int new_length;
        if (arrayList == null) {
            new_length = -1;
        } else {
            new_length = arrayList.length - 1;
        }
        final T[] rezulted_array_list = (T[]) Array.newInstance(arrayList.getClass().getComponentType(), new_length);
        for (int i = 0; i < new_length; i++) {
            int i_in_old = i;
            if (i >= index) {
                i_in_old++;
            }
            rezulted_array_list[i] = arrayList[i_in_old];
        }
        arrayList = rezulted_array_list;
        return;
    }
}
