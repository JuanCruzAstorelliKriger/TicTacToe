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

        Game game = new Game();

        try {
            game.start();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PositionCantFollowVectorException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}