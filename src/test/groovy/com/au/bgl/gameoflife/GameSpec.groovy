package com.au.bgl.gameoflife

import com.au.bgl.gameoflife.domain.Cell
import com.au.bgl.gameoflife.rules.GameRule
import com.au.bgl.gameoflife.rules.RuleData
import spock.lang.Specification

class GameSpec extends Specification {

    def rule1 = Mock(GameRule)
    def rule2 = Mock(GameRule)
    def game = new Game(200, [rule1, rule2])

    def "simulateGenerations should apply rules and collect generations"() {
        given:

        def initialCells = [new Cell(1, 1), new Cell(1, 2)]
        game.setLiveCells(initialCells)

        when:
        def result = game.generate(2)

        then:
        result.size() == 2
        2 * rule1.apply(_ as RuleData)
        2 * rule2.apply(_ as RuleData)
    }

    def "simulateGenerations should produce empty next generation if no rules add cells"() {
        given:
        def rule = Mock(GameRule)
        rule.apply(_) >> { RuleData data -> /* no-op */ }
        def game = new Game(200, [rule])
        def liveCells = [new Cell(10, 10)]
        game.setLiveCells(liveCells)

        when:
        def generations = game.generate(1)

        then:
        generations.size() == 1
        generations[0].generationNo() == 1
        generations[0].liveCells().isEmpty()
    }

    def "getCandidateCells should include neighbors and live cells only"() {
        given:
        def dummyRule = Mock(GameRule)
        def game = new Game(200, [dummyRule])
        def cell = new Cell(1, 1)
        game.setLiveCells([cell])

        when:
        game.generate(1)

        then:
        1 * dummyRule.apply({ RuleData data ->
            assert data.candidateCells().contains(cell)
            assert data.candidateCells().containsAll(cell.getNeighbors())
            true
        })
    }
}
