package com.github.upatovav.islandCounter;


import java.util.HashSet;

/**
 * row-based implementation
 * will work properly only in case there is no lakes in islands
 */
public class RowReadingIslandCounter implements IslandCounter {

    private short islandCount = 0;

    private short islandNumber = 0;

    private short[] previousRow;

    public RowReadingIslandCounter(final int rowCount){
        previousRow = new short[rowCount];
    }

    @Override
    public void addRow(boolean[] row) {
        final short[] parsedRow = new short[row.length];
        for (int i = 0; i < row.length; i++) {
            if (row[i]) {
                if (previousRow[i] > 0) {
                    //island was on previous row
                    parsedRow[i] = previousRow[i];
                    //all previous islands merge to one from previous row
                    fillPreviousLand(parsedRow, i, previousRow[i]);
                } else {
                    if (i == 0 || !row[i-1]) {
                        //we assume that we found new island
                        islandCount++;
                        islandNumber++;
                        parsedRow[i] = islandNumber;
                    } else {
                        //we found new piece of previous island
                        if (row[i-1]) {
                            parsedRow[i] = parsedRow[i-1];
                        }
                    }
                }
            }
        }
        previousRow = parsedRow;
    }

    private void fillPreviousLand(
            final short[] parsedRow,
            final int index,
            final short islandNumber) {
        int i = index-1;
        HashSet<Short> mergedIslands = new HashSet<>();
        mergedIslands.add(islandNumber);
        while (i >0 && parsedRow[i]>0) {
            if (parsedRow[i]>0 && mergedIslands.add(parsedRow[i])) {
                //each time we find connected islands we decrease
                //island counter - no lakes in our task so each island can connect
                //other only once
                islandCount--;
                //TODO readability didn't we connect all land into one island once we found it before?
                //refactor from complicated set to simple if
            }
            parsedRow[i] = islandNumber;
            i--;
        }
    }


    @Override
    public int getIslandCount() {
        return islandCount;
    }
}
