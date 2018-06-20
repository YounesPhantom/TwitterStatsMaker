package com.les_napoleons;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        StatsUtils stats = new StatsUtils();
        //stats.getTwitterFollowers();
        try {
            System.out.println(stats.getAPI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
