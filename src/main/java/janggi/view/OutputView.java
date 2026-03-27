package janggi.view;

import janggi.domain.Piece;
import janggi.domain.Team;
import janggi.domain.position.Column;
import janggi.domain.position.Position;
import java.util.Map;

public class OutputView {

    private static final String ANSI_RED   = "\u001B[1;31m";
    private static final String ANSI_BLUE  = "\u001B[1;34m";
    private static final String ANSI_RESET = "\u001B[0m";

    private static final int FIRST_ROW = 1;
    private static final int LAST_ROW  = 10;
    private static final int FIRST_COL = Column.COLUMN_LOWER_THRESH_HOLD;
    private static final int LAST_COL  = Column.COLUMN_UPPER_THRESH_HOLD;

    private static final String CROSS  = " + ";
    private static final String H_LINE = "---";
    private static final String V_LINE = " | ";

    private static final Map<String, String> HAN_LABEL = Map.of(
            "차", " C ", "마", " H ", "상", " E ", "사", " G ",
            "장", " K ", "포", " B ", "졸", " S "
    );

    private static final Map<String, String> CHO_LABEL = Map.of(
            "차", " c ", "마", " h ", "상", " e ", "사", " g ",
            "장", " k ", "포", " b ", "졸", " s "
    );

    public void printBoard(Map<Position, Piece> board) {
        System.out.println();
        printColumnHeader();
        for (int row = FIRST_ROW; row <= LAST_ROW; row++) {
            printPieceRow(board, row);
            if (row < LAST_ROW) {
                printLineBetweenRows();
            }
        }
        System.out.println();
        printLegend();
    }

    private void printColumnHeader() {
        System.out.print("    ");
        for (int col = FIRST_COL; col <= LAST_COL; col++) {
            System.out.printf(" %d ", col);
            if (col < LAST_COL) System.out.print("   ");
        }
        System.out.println();
    }

    private void printPieceRow(Map<Position, Piece> board, int row) {
        System.out.print(" " + toRowLabel(row) + " ");
        for (int col = FIRST_COL; col <= LAST_COL; col++) {
            Piece piece = board.get(createPosition(row, col));
            System.out.print(formatPiece(piece));
            if (col < LAST_COL) System.out.print(H_LINE);
        }
        System.out.println();
    }

    private void printLineBetweenRows() {
        System.out.print("   ");
        for (int col = FIRST_COL; col <= LAST_COL; col++) {
            System.out.print(V_LINE);
            if (col < LAST_COL) System.out.print("   ");
        }
        System.out.println();
    }

    private String toRowLabel(int row) {
        return row == LAST_ROW ? "0" : String.valueOf(row);
    }

    private Position createPosition(int row, int col) {
        if (row == LAST_ROW) return Position.from("0" + col);
        return Position.from("" + row + col);
    }

    private String formatPiece(Piece piece) {
        if (piece.isEmptyPiece()) return CROSS;
        if (piece.isSame(Team.HAN)) {
            return ANSI_RED + HAN_LABEL.get(piece.getDisplayName()) + ANSI_RESET;
        }
        return ANSI_BLUE + CHO_LABEL.get(piece.getDisplayName()) + ANSI_RESET;
    }

    private void printLegend() {
        System.out.println(ANSI_RED + "  [HAN] C=車 H=馬 E=相 G=仕 K=將 B=包 S=兵" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "  [CHO] c=車 h=馬 e=象 g=士 k=將 b=包 s=卒" + ANSI_RESET);
    }

    public void printWinner(Team winner) {
        String name = winner == Team.HAN
                ? ANSI_RED + "한(漢)" + ANSI_RESET
                : ANSI_BLUE + "초(楚)" + ANSI_RESET;
        System.out.println(name + " 승리! 게임을 종료합니다.");
    }

    public void printError(String message) {
        System.out.println(message);
    }

    public void printGameEnd() {
        System.out.println("게임을 종료합니다.");
    }
}
