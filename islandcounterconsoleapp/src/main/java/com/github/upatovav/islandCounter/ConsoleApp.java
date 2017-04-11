package com.github.upatovav.islandCounter;


import java.io.*;

public class ConsoleApp
{
    public static void main( String[] args ) {
        try {
            if (args.length == 0) {
                //console input
                final int result = InputStreamParser.parseInputStream(new InputStreamReader(System.in),
                        (rowCount, columnCount) -> new RowReadingIslandCounter(rowCount));
                System.out.println(String.format("Parsing comlete. Island count was %d", result));
            } else {
                //file input
                final String path = args[0];
                File file = new File(path);
                if (file.canRead() && !file.isDirectory()) {
                    final int result = InputStreamParser.parseInputStream(new FileReader(file),
                            (rowCount, columnCount) -> new RowReadingIslandCounter(rowCount));
                    System.out.println(String.format("Parsing comlete. Island count was %d", result));
                } else {
                    throw new IllegalArgumentException("File not found");
                }
            }
        } catch (IOException e) {
            //how could we get there?
            e.printStackTrace();
        }
    }
}
