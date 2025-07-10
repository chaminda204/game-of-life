package com.au.bgl.gameoflife.rules;

import com.au.bgl.gameoflife.domain.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Debugging purpose only. No need to apply this rule
 */
public class UnderpopulationRule implements GameRule {

    private static final Logger log = LoggerFactory.getLogger(UnderpopulationRule.class);

    @Override
    public void apply(RuleData data) {

        data.candidateCells().forEach(candidate -> {
            long liveNeighbors = countLiveNeighbors(candidate, data.currentLiveCells());
            if (liveNeighbors < 2) {
                log.debug("UnderpopulationRule: Cell {} dies due to underpopulation with {} neighbors", candidate, liveNeighbors);
            }
        });

    }
}