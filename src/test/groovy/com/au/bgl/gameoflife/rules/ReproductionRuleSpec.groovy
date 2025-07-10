package com.au.bgl.gameoflife.rules

import com.au.bgl.gameoflife.GameFixtures
import com.au.bgl.gameoflife.domain.Cell
import spock.lang.Specification
import spock.lang.Subject

class ReproductionRuleSpec extends Specification implements GameFixtures {

    @Subject
    def rule = new ReproductionRule()

    def "should activate a dead cell with exactly 3 live neighbors"() {
        given:
        def cell = new Cell(1, 1)
        def liveNeighbors = [new Cell(0, 0), new Cell(0, 1), new Cell(0, 2)] as Set
        def candidateCells = [cell] as Set
        def futureLiveCells = [] as Set
        and:
        def ruleData = ruleData([candidateCells: candidateCells, currentLiveCells: liveNeighbors, futureLiveCells: futureLiveCells])

        when:
        rule.apply(ruleData)

        then:
        futureLiveCells.contains(cell)
    }

    def "should not activate dead cells at boundaries or with wrong neighbor count"() {
        given:
        def futureLiveCells = [] as Set
        def candidateCells = [cell] as Set
        def ruleData = new RuleData(candidateCells, liveNeighbors, futureLiveCells)

        when:
        rule.apply(ruleData)

        then:
        futureLiveCells.contains(cell) == shouldActivate

        where:
        cell               | liveNeighbors                                                           || shouldActivate
        new Cell(0, 0)     | [new Cell(0, 1), new Cell(1, 0), new Cell(1, 1)] as Set                 || true
        new Cell(199, 199) | [new Cell(198, 198), new Cell(198, 199), new Cell(199, 198)] as Set     || true
        new Cell(0, 1)     | [new Cell(0, 0), new Cell(1, 0)] as Set                                 || false
        new Cell(1, 1)     | [] as Set                                                               || false
        new Cell(1, 1)     | [new Cell(0, 0), new Cell(0, 1), new Cell(0, 2), new Cell(1, 0)] as Set || false
    }
}
