package com.au.bgl.gameoflife.rules


import com.au.bgl.gameoflife.domain.Cell
import spock.lang.Specification
import spock.lang.Unroll

class SurvivalRuleSpec extends Specification {

    def rule = new SurvivalRule()

    def "should preserve a live cell with 2 or 3 live neighbors"() {
        given:
        def liveCell = new Cell(2, 2)
        def neighbors = [new Cell(1, 1), new Cell(3, 2)] as Set
        def candidateCells = [liveCell] as Set
        def currentLiveCells = neighbors + [liveCell] as Set
        def futureLiveCells = [] as Set
        def ruleData = new RuleData(candidateCells, currentLiveCells, futureLiveCells)

        when:
        rule.apply(ruleData)

        then:
        futureLiveCells.contains(liveCell)
    }

    @Unroll
    def "should only preserve live cell with #scenario - #shouldSurvive"() {
        given:
        def futureLiveCells = [] as Set
        def candidateCells = [cell] as Set
        def ruleData = new RuleData(candidateCells, liveCells, futureLiveCells)
        def rule = new SurvivalRule()

        when:
        rule.apply(ruleData)

        then:
        futureLiveCells.contains(cell) == shouldSurvive

        where:
        scenario      | cell           | liveCells                                                                               || shouldSurvive
        "1 neighbor"  | new Cell(1, 1) | [new Cell(1, 1), new Cell(0, 0)] as Set                                                 || false
        "2 neighbors" | new Cell(1, 1) | [new Cell(1, 1), new Cell(0, 0), new Cell(2, 2)] as Set                                 || true
        "3 neighbors" | new Cell(1, 1) | [new Cell(1, 1), new Cell(0, 0), new Cell(0, 1), new Cell(2, 2)] as Set                 || true
        "4 neighbors" | new Cell(1, 1) | [new Cell(1, 1), new Cell(0, 0), new Cell(0, 1), new Cell(0, 2), new Cell(1, 0)] as Set || false
        "dead cell"   | new Cell(1, 1) | [new Cell(0, 0), new Cell(0, 1), new Cell(0, 2)] as Set                                 || false
    }
}
