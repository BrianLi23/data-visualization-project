package cpt;

import java.io.*;
import java.util.*;
import java.lang.Math;

public class main {

    public static void main(String[] args) throws IOException {

        listData completeData = new listData();
        completeData.sortYear(false);

        for (int i = 0; i < 20; i++) {
            System.out.println(completeData.getElement(i).getYear());
        }

        completeData.sortEntity(false);

        for (int i = 0; i < 20; i++) {
            System.out.println(completeData.getElement(i).getEntity());
        }

        System.out.println("-");

        completeData.sortEntity(true);

        for (int i = 0; i < 20; i++) {
            System.out.println(completeData.getElement(i).getEntity());
        }
    }

}
