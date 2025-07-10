
package com.au.bgl.gameoflife.domain;

import java.util.*;

public record Cell(int xCoordinate, int yCoordinate) {
    private static final int[][] NEIGHBOR_DIRECTIONS = {
        {-1, -1}, {-1, 0}, {-1, 1},
        {0, -1},           {0, 1},
        {1, -1},  {1, 0},  {1, 1}
    };

    private static boolean isWithinBounds(Cell cell) {
        return cell.xCoordinate() >= 0 && cell.yCoordinate() >= 0 && cell.xCoordinate() < 200 && cell.yCoordinate() < 200;
    }

    public List<Cell> getNeighbors() {
        return Arrays.stream(NEIGHBOR_DIRECTIONS)
            .map(offset -> new Cell(xCoordinate + offset[0], yCoordinate + offset[1]))
            .filter(Cell::isWithinBounds)
            .toList();
    }
}
