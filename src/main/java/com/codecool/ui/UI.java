package com.codecool.ui;

import java.util.Collection;

public class UI {
    private IO io;

    public UI() {
        io = new IO();
    }

    public void displayMenu() {
        print(new String[] { "(1) Start new game",
                             "(2) How to play",
                             "(3) About authors",
                             "(0) Exit" });
    }

    public void print(Collection<String> collection) {
        collection.stream()
                  .forEach(System.out::println);
    }

    public void print(String[] toPrint) {
        for (String string : toPrint) {
            System.out.print(string);
        }
    }

    public void gatherEmptyInput(String message) {
        io.gatherEmptyInput(message);
    }

    public String gatherInput(String message) {
        return io.gatherInput(message);
    }

    public int gatherIntInput(String message, int rangeMin, int rangeMax) {
        return io.gatherIntInput(message, rangeMin, rangeMax);
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
    }
    public void printFlipTable(Iterable i, Class obj) {
        System.out.println(TableSupport.fromIterable(i,obj));
    }
}
