package ru.job4j.chess;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.BishopBlack;
import ru.job4j.chess.firuges.black.PawnBlack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogicTest {

    @Test
    public void whenMoveThenFigureNotFoundException() {
        Logic logic = new Logic();
        FigureNotFoundException exception = assertThrows(FigureNotFoundException.class,
                () -> logic.move(Cell.C1, Cell.H6));
        assertThat(exception.getMessage()).isEqualTo("Figure not found on the board.");
    }

    @Test
    public void whenMoveThenImpossibleMoveException() {
        Logic logic = new Logic();
        Figure figure = new BishopBlack(Cell.A1);
        logic.add(figure);
        ImpossibleMoveException exception = assertThrows(ImpossibleMoveException.class,
                () -> logic.move(Cell.A1, Cell.A2));
        assertThat(exception.getMessage()).isEqualTo("Could not move by diagonal from A1 to A2");
    }

    @Test
    public void whenMoveThenOccupiedCellException() {
        Logic logic = new Logic();
        Figure bishop = new BishopBlack(Cell.A1);
        logic.add(bishop);
        logic.add(new PawnBlack(Cell.B2));
        assertThrows(OccupiedCellException.class, () -> logic.move(Cell.A1, Cell.B2));
    }

    @Test
    public void whenMoveThenNoException() {
        Logic logic = new Logic();
        Figure bishop = new BishopBlack(Cell.A1);
        logic.add(bishop);
        assertDoesNotThrow(() -> logic.move(Cell.A1, Cell.B2));
    }
}
