package com.github.upatovav.islandCounter;


public interface IslandCounterFactory {
    IslandCounter getIslandCounter(int rowCount, int columnCount);
}
