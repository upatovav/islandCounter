package com.github.upatovav.islandCounter;


import java.util.*;

/**
 * Cycle  island counter - doesn't produce {@link StackOverflowError}
 */
public class CyclePathFindingIslandCounter extends AbstractPathFindingIslandCounter{


    CyclePathFindingIslandCounter(int rowCount, int columnCount) {
        super(rowCount, columnCount);
    }

    /**
     * Constructor for UT only
     * @param rowCount - row count
     * @param columnCount - column count
     * @param field - entire field
     */
    /*package*/ CyclePathFindingIslandCounter(
            final int rowCount,
            final int columnCount,
            final boolean[][] field
    ){
        super(rowCount,columnCount,field);
    }

    protected void fillIsland(final int x, final int y){
        final LinkedHashSet<FieldPoint> stack = new LinkedHashSet<>();
        stack.add(FieldPoint.of(x,y));
        while (!(stack.isEmpty())) {
            FieldPoint currentPoint = stack.iterator().next();
            field[currentPoint.getX()][currentPoint.getY()] = false;
            stack.remove(currentPoint);
            stack.addAll(getNearbyPoints(currentPoint.getX(),currentPoint.getY()));
        }

    }

    private List<FieldPoint> getNearbyPoints(final int x, final int y){
        //System.out.println(x+": "+y);
        final ArrayList<FieldPoint> result = new ArrayList<>(4);
        if (x > 0 && field[x-1][y]) {
            result.add(FieldPoint.of(x-1 , y));
        }
        if (y > 0 && field[x][y-1]) {
            result.add(FieldPoint.of(x, y-1));
        }
        if (x < maxX-1 && field[x+1][y]) {
            result.add(FieldPoint.of(x+1, y));
        }
        if (y < maxY-1 && field[x][y+1]) {
            result.add(FieldPoint.of(x, y+1));
        }
        return result;
    }
}
