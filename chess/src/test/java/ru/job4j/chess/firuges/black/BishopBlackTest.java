package ru.job4j.chess.firuges.black;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BishopBlackTest {

    @Test
    void whenGetPosition() {
        BishopBlack bishop = new BishopBlack(Cell.C1);
        assertThat(bishop.position()).isEqualTo(Cell.C1);
    }

    @Test
    void whenWayFromC1ToG5() {
        Cell[] expected = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        BishopBlack bishop = new BishopBlack(Cell.C1);
        assertThat(bishop.way(Cell.G5)).isEqualTo(expected);
    }

    @Test
    void whenWayFromA1ToD2NotDiagonalThenException() {
        BishopBlack bishop = new BishopBlack(Cell.A1);
        assertThrows(ImpossibleMoveException.class, () -> bishop.way(Cell.D2));
    }

    @Test
    void copy() {
        BishopBlack bishop = new BishopBlack(Cell.C1);
        Figure result = bishop.copy(Cell.D2);
        assertThat(result.position()).isEqualTo(Cell.D2);
    }
}