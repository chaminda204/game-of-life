package com.au.bgl.gameoflife.rules;

import com.au.bgl.gameoflife.domain.Cell;
import java.util.Set;

public record RuleData(Set<Cell> candidateCells, Set<Cell> currentLiveCells, Set<Cell> futureLiveCells) {}
