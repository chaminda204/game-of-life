package com.au.bgl.gameoflife

import com.au.bgl.gameoflife.domain.Cell
import com.au.bgl.gameoflife.rules.ReproductionRule
import com.au.bgl.gameoflife.rules.SurvivalRule
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class GameE2ESpec extends Specification {


    @Subject
    def game = new Game(100, [new SurvivalRule(), new ReproductionRule()])

    def "should return empty generations when initial state has single cell"() {
        given:
        game.setLiveCells([new Cell(10, 10)])

        when:
        def result = game.generate(1)

        then:
        result.size() == 1
        result[0].liveCells().isEmpty()
    }

    def "still life block should remain the same after multiple generations"() {
        given:
        def block = [
                new Cell(1, 1), new Cell(1, 2),
                new Cell(2, 1), new Cell(2, 2)
        ]
        game.setLiveCells(block)

        when:
        def generations = game.generate(3)

        then:
        generations*.liveCells().every { new HashSet<>(it) == new HashSet<>(block) }
    }

    def "oscillator blinker should switch state every generation"() {
        given:
        def horizontal = [
                new Cell(2, 1), new Cell(2, 2), new Cell(2, 3)
        ]
        game.setLiveCells(horizontal)

        and:
        def vertical = [
                new Cell(1, 2), new Cell(2, 2), new Cell(3, 2)
        ]

        when:
        def generations = game.generate(2)

        then:
        new HashSet<>(generations[0].liveCells()) == new HashSet<>(vertical)
        new HashSet<>(generations[1].liveCells()) == new HashSet<>(horizontal)
    }

    def "dead cell with three neighbors should become alive - reproduction rule"() {
        given:
        def initial = [
                new Cell(1, 1), new Cell(1, 2), new Cell(2, 1)
        ]
        game.setLiveCells(initial)

        and:
        def expected = new Cell(2, 2)


        when:
        def result = game.generate(1)

        then:
        result[0].liveCells().contains(expected)
    }

    @Unroll
    def "should return empty for grid boundary test - #scenario "() {
        given:
        game.setLiveCells(cells)

        when:
        def result = game.generate(1)

        then:
        result[0].liveCells().isEmpty()

        where:
        scenario              | cells
        "top-left corner"     | [new Cell(0, 10)]
        "top-right corner"    | [new Cell(0, 199)]
        "bottom-left corner"  | [new Cell(199, 0)]
        "bottom-right corner" | [new Cell(199, 199)]
    }

}
