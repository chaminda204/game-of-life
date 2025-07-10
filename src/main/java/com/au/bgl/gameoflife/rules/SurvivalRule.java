package com.au.bgl.gameoflife.rules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SurvivalRule implements GameRule {

    private static final Logger log = LoggerFactory.getLogger(SurvivalRule.class);

    @Override
    public void apply(RuleData data) {

        data.candidateCells().forEach(candidate -> {
            long neighbors = countLiveNeighbors(candidate, data.currentLiveCells());
            if (data.currentLiveCells().contains(candidate) && (neighbors == 2 || neighbors == 3)) {
                data.futureLiveCells().add(candidate);
                log.debug("SurvivalRule: Cell {} survives with {} neighbors", candidate, neighbors);
            }
        });
    }
}
