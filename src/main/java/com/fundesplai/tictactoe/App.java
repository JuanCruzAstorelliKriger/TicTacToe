package com.fundesplai.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.print.attribute.standard.PrinterInfo;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        Integer[] inte = {0};
        Combination comb = new Combination(inte);

        Set<Vector> vecs = Utils.inicDirCombination(comb);
        
        int i = 0;
        for (Vector vec : vecs) {

            System.out.println("----Vec " + i);
            for (Map.Entry<Integer, Boolean> entry : vec.getAxisDirs().entrySet()) {

                System.out.println("Ax: " + entry.getKey() + " / dir: " + entry.getValue());
            }
            System.out.println();
            i++;
        }
    }
}