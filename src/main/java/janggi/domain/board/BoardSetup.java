package janggi.domain.board;

import janggi.domain.Team;
import janggi.domain.piece.Piece;
import janggi.domain.position.Position;
import java.util.Map;

public enum BoardSetup {
    LEFT_ELEPHANT("1") {
        @Override
        public void apply(Map<Position, Piece> board, Team team) {
            if (team == Team.HAN) {
                swap(board, Position.from("12"), Position.from("13"));
                return;
            }
            swap(board, Position.from("07"), Position.from("08"));
        }
    },
    RIGHT_ELEPHANT("2") {
        @Override
        public void apply(Map<Position, Piece> board, Team team) {
            if (team == Team.HAN) {
                swap(board, Position.from("17"), Position.from("18"));
                return;
            }
            swap(board, Position.from("02"), Position.from("03"));
        }
    },
    INNER_ELEPHANT("3") {
        @Override
        public void apply(Map<Position, Piece> board, Team team) {
            if (team == Team.HAN) {
                swap(board, Position.from("17"), Position.from("18"));
                swap(board, Position.from("12"), Position.from("13"));
                return;
            }
            swap(board, Position.from("07"), Position.from("08"));
            swap(board, Position.from("02"), Position.from("03"));
        }
    },
    OUTER_ELEPHANT("4") {
        @Override
        public void apply(Map<Position, Piece> board, Team team) {
            // 기본 차림 — 변경 없음
        }
    };

    private final String code;

    BoardSetup(String code) {
        this.code = code;
    }

    public abstract void apply(Map<Position, Piece> board, Team team);

    public static BoardSetup from(String code) {
        for (BoardSetup setup : values()) {
            if (setup.code.equals(code)) {
                return setup;
            }
        }
        throw new IllegalArgumentException("[ERROR] 올바르지 않은 차림 번호입니다.");
    }

    private static void swap(Map<Position, Piece> board, Position pos1, Position pos2) {
        Piece piece1 = board.get(pos1);
        board.put(pos1, board.get(pos2));
        board.put(pos2, piece1);
    }
}
