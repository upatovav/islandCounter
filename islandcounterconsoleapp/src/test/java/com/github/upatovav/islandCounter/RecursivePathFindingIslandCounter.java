package com.github.upatovav.islandCounter;

/**
 * Recursive island counter - simple, works fine on smaller fields
 * but 5000x5000 doesn't fit to stack size
 * saved for UT checks
 */
public class RecursivePathFindingIslandCounter extends AbstractPathFindingIslandCounter{

    RecursivePathFindingIslandCounter(int rowCount, int columnCount) {
        super(rowCount, columnCount);
    }

    /**
     * Constructor for UT only
     * @param rowCount
     * @param columnCount
     * @param field
     */
    /*package*/ RecursivePathFindingIslandCounter(
            final int rowCount,
            final int columnCount,
            final boolean[][] field
    ){
        super(rowCount,columnCount,field);
    }

    protected void fillIsland(final int x, final int y){
        field[x][y] = false;
        if (x > 0 && field[x-1][y]) {
            fillIsland(x-1 , y);
        }
        if (x < maxX-1 && field[x+1][y]) {
            fillIsland(x+1, y);
        }
        if (y > 0 && field[x][y-1]) {
            fillIsland(x, y-1);
        }
        if (y < maxY-1 && field[x][y+1]) {
            fillIsland(x, y+1);
        }

    }
}
