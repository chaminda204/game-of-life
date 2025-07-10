package com.au.bgl.gameoflife

import com.au.bgl.gameoflife.domain.Cell
import com.au.bgl.gameoflife.rules.RuleData

trait GameFixtures {

    def ruleData(Map options = [:]) {
        def data = [
                candidateCells  : [new Cell(1, 1)] as Set,
                currentLiveCells: [new Cell(1, 1)] as Set,
                futureLiveCells : [] as Set,
        ] << options
        return new RuleData(data.candidateCells, data.currentLiveCells, data.futureLiveCells)
    }


}