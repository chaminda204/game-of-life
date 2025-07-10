package com.au.bgl.gameoflife.rules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReproductionRule implements GameRule {

    private static final Logger log = LoggerFactory.getLogger(ReproductionRule.class);

    @Override
    public void apply(RuleData data) {

        data.candidateCells().forEach(candidate -> {
                    long neighbors = countLiveNeighbors(candidate, data.currentLiveCells());
                    if (!data.currentLiveCells().contains(candidate) && neighbors == 3) {
                        log.debug("ReproductionRule : Cell {} is born with exactly 3 neighbors", candidate);
                        data.futureLiveCells().add(candidate);
                    }
                }

        );
    }
}
