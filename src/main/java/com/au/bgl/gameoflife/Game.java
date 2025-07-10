package com.au.bgl.gameoflife;

import java.util.*;

import com.au.bgl.gameoflife.domain.Cell;
import com.au.bgl.gameoflife.rules.*;

public class Game {
    private final int gridSize;
    private Set<Cell> currentLiveCells;
    private final List<GameRule> rules;

    public Game(int gridSize, List<GameRule> rules) {
        this.gridSize = gridSize;
        this.rules = rules;
        this.currentLiveCells = new HashSet<>();
    }

    public void setLiveCells(List<Cell> liveCells) {
        this.currentLiveCells = new HashSet<>(liveCells);
    }

    public List<GenerationResponse> generate(int generationCount) {
        List<GenerationResponse> generationResponses = new ArrayList<>();
        for (int generationNumber = 1; generationNumber <= generationCount; generationNumber++) {
            Set<Cell> nextGenerationLiveCells = new HashSet<>();
            Set<Cell> candidateCells = getCandidateCells();
            RuleData ruleData = new RuleData(candidateCells, currentLiveCells, nextGenerationLiveCells);
            rules.forEach(rule -> rule.apply(ruleData));
            currentLiveCells = nextGenerationLiveCells;
            generationResponses.add(new GenerationResponse(generationNumber, new ArrayList<>(currentLiveCells)));
        }
        return generationResponses;
    }

    private Set<Cell> getCandidateCells() {
        Set<Cell> candidateCells = new HashSet<>();
        for (Cell liveCell : currentLiveCells) {
            candidateCells.add(liveCell);
            candidateCells.addAll(liveCell.getNeighbors());
        }
        return candidateCells;
    }
}
