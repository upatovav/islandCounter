package com.github.upatovav.islandCounter;

/**
 * Abstract realisation to test recursive & non-recursive approach
 */
abstract class AbstractPathFindingIslandCounter implements IslandCounter {
    protected final boolean[][] field;
    protected final int maxX;
    protected final int maxY;
    private int addedRowCount;

    AbstractPathFindingIslandCounter(final int rowCount, final int columnCount){
        this.maxX = rowCount;
        this.maxY = columnCount;
        this.field = new boolean[rowCount][columnCount];
        this.addedRowCount = 0;
    }

    /**
     * Constructor for UT only
     * @param rowCount - expected row counr
     * @param columnCount - expected column count
     * @param field - our entire field
     */
    AbstractPathFindingIslandCounter(
            final int rowCount,
            final int columnCount,
            final boolean[][] field
    ){
        this.maxX = rowCount;
        this.maxY = columnCount;
        this.field = field;
        this.addedRowCount = field.length;
    }

    public void addRow(boolean[] row) throws IllegalArgumentException {
        if (addedRowCount == field.length) {
            throw new IllegalArgumentException("too much rows added");
        }
        field[addedRowCount] = row;
        addedRowCount++;
    }

    public int getIslandCount() throws IllegalStateException{
        if (addedRowCount < maxY) {
            throw new IllegalStateException("not enough rows added");
        }
        int islandCount = 0;
        for (int y=0; y < maxY; y++){
            for (int x=0; x < maxX; x++) {
                if (field[x][y]) {
                    fillIsland(x, y);
                    islandCount++;
                }
            }
        }
        return islandCount;
    }

    abstract protected void fillIsland(final int x, final int y);
}
