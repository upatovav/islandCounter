package com.github.upatovav.islandCounter;

/**
 * class for holding points in field.
 * hashCode() function takes into account that x<5000 and y<5000
 */
public class FieldPoint {
    private final int x;
    private final int y;
    private FieldPoint(final int x, final int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FieldPoint) {
            return ((FieldPoint) obj).getX() == x
                    && ((FieldPoint) obj).getY() == y;
        }
        return false;
    }
    @Override
    public int hashCode() {
        //to avoid collisions simply shift one operand
        //as our values are always <5000
        return x^y<<13;
    }

    public static FieldPoint of(
            final int x,
            final int y
    ){
        return new FieldPoint(x,y);
    }
}
