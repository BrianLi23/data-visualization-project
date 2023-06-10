package cpt;

import java.io.*;
import java.util.*;
import java.lang.Math;

public class main {

    public static void main(String[] args) throws IOException {

        listData completeData = new listData();
        completeData.sortYear(true);

        for (int i = 0; i < completeData.getSize(); i++) {
            System.out.println(completeData.getElement(i).getYear());
        }

        System.out.println("-");

        completeData.sortParameter(false);

        for (int i = 0; i < 20; i++) {
            System.out.println(completeData.getElement(i).getParameter());
        }

        System.out.println("-");

        completeData.sortDomain(true);

        for (int i = 0; i < 20; i++) {
            System.out.println(completeData.getElement(i).getDomain());
        }
    }

}
