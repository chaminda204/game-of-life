package com.au.bgl.gameoflife.rules;

import com.au.bgl.gameoflife.domain.Cell;
import java.util.Set;

public interface GameRule {
    void apply(RuleData data);

    default long countLiveNeighbors(Cell cell, Set<Cell> currentLiveCells) {
        return cell.getNeighbors().stream()
            .filter(currentLiveCells::contains)
            .count();
    }
}
