package com.au.bgl.gameoflife;

import com.au.bgl.gameoflife.domain.Cell;
import com.au.bgl.gameoflife.rules.ReproductionRule;
import com.au.bgl.gameoflife.rules.SurvivalRule;

import java.util.*;
import java.util.stream.Collectors;

public class GameOfLife {
    private static final int GRID_SIZE = 200;
    private static final int TOTAL_GENERATIONS = 100;

    public static void main(String[] args) {
        List<Cell> initialLiveCells = Arrays.asList(
                new Cell(5, 5), new Cell(6, 5), new Cell(7, 5));

        Game game = new Game(GRID_SIZE, List.of(
                new SurvivalRule(),
                new ReproductionRule()
        ));
        game.setLiveCells(initialLiveCells);
        List<GenerationResponse> generations = game.generate(TOTAL_GENERATIONS);
        generations.forEach(gen -> {
            List<String> cellStrings = gen.liveCells().stream()
                    .sorted(Comparator.comparingInt(Cell::xCoordinate).thenComparingInt(Cell::yCoordinate))
                    .map(cell -> String.format("[%d, %d]", cell.xCoordinate(), cell.yCoordinate()))
                    .collect(Collectors.toList());
            System.out.println(gen.generationNo() + ": " + cellStrings);
        });
    }
}
