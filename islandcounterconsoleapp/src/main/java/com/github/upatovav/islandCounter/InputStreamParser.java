package com.github.upatovav.islandCounter;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class InputStreamParser {

    private static final String HEADER_SEPARATOR = " ";
    private static final char SEA_CHAR = '~';
    private static final char LAND_CHAR = '*';

    static int parseInputStream(
            final InputStreamReader inputStreamReader,
            final IslandCounterFactory islandCounterFactory)
        throws IOException{

        BufferedReader reader = new BufferedReader(inputStreamReader);
        final String mapHeader = reader.readLine();
        if (!mapHeader.contains(HEADER_SEPARATOR)) {
            throw new IllegalArgumentException("Space not found in first line");
        }
        final int expectedRowCount = Integer.parseInt(
                mapHeader.substring(0, mapHeader.indexOf(HEADER_SEPARATOR)));
        final int columnCount = Integer.parseInt(
                mapHeader.substring(mapHeader.indexOf(HEADER_SEPARATOR) + 1));
        if (expectedRowCount < 1 || columnCount < 1
                || expectedRowCount > 5000 || columnCount > 5000) {
            throw new IllegalArgumentException("arguments should be 1 ≤ N, M ≤ 5000");
        }
        int currentRowCount = 0;
        final IslandCounter islandCounter =
                islandCounterFactory.getIslandCounter(expectedRowCount, columnCount);

        while (currentRowCount < expectedRowCount) {
            final String currentRow = reader.readLine();
            if (currentRow.length() != columnCount) {
                throw new IllegalArgumentException(
                        String.format("Incorrect length at row %d", currentRowCount));
            }
            islandCounter.addRow(parseRowToBooleanArray(currentRow, currentRowCount));
            currentRowCount++;
        }
        return islandCounter.getIslandCount();
    }

    private static boolean[] parseRowToBooleanArray(
            final String row,
            final int rowNumberForExceptin)throws IllegalArgumentException{
        final boolean[] result = new boolean[row.length()];
        final char[] lineChars = row.toCharArray();
        int currentCharIndex = 0;
        while (currentCharIndex<lineChars.length) {
            char currentChar = lineChars[currentCharIndex];
            switch (currentChar) {
                case SEA_CHAR  :
                    result[currentCharIndex] = false;
                    break;
                case LAND_CHAR :
                    result[currentCharIndex] = true;
                    break;
                default:
                    throw new IllegalArgumentException(
                            String.format("unknown character %c at row %d : %d",
                                    currentChar, rowNumberForExceptin + 1, currentCharIndex + 1));
            }
            currentCharIndex++;
        }
        return result;
    }
}
