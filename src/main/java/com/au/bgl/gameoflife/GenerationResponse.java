
package com.au.bgl.gameoflife;

import com.au.bgl.gameoflife.domain.Cell;

import java.util.List;

public record GenerationResponse(int generationNo, List<Cell> liveCells) {}
