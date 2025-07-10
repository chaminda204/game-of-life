package com.au.bgl.gameoflife.rules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Debugging purpose only. No need to apply this rule
 */
public class OverpopulationRule implements GameRule {

    private static final Logger log = LoggerFactory.getLogger(OverpopulationRule.class);

    @Override
    public void apply(RuleData data) {

        data.candidateCells().forEach(candidate -> {
            long liveNeighbors = countLiveNeighbors(candidate, data.currentLiveCells());
            if (liveNeighbors > 3) {
                log.debug("OverpopulationRule: Cell {} dies due to overpopulation with {} neighbors", candidate, liveNeighbors);
            }
        });

    }
}
