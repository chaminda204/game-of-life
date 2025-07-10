package com.au.bgl.gameoflife.domain

import spock.lang.Specification

class CellSpec extends Specification {

    def "should return 8 neighbors for a center cell"() {
        given:
        def cell = new Cell(100, 100)

        when:
        def neighbors = cell.getNeighbors()

        then:
        neighbors.size() == 8
        assert neighbors.every(isWithinBounds)
    }

    def "should not return out-of-bounds neighbors for corner cell"() {
        given:
        def corner = new Cell(0, 0)

        when:
        def neighbors = corner.getNeighbors()

        then:
        neighbors.every { it.xCoordinate() >= 0 && it.yCoordinate() >= 0 }
        neighbors.size() < 8
    }

    def isWithinBounds = { cell ->
        cell.xCoordinate() >= 0 && cell.yCoordinate() >= 0 &&
                cell.xCoordinate() < 200 && cell.yCoordinate() < 200
    }
}
