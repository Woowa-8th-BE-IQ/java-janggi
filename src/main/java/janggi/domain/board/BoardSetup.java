package janggi.domain.board;

import janggi.domain.Team;
import janggi.domain.piece.Piece;
import janggi.domain.position.Position;
import java.util.Map;

public enum BoardSetup {
    HAN_LEFT_SANG("1", Team.HAN) {
        @Override
        public void apply(Map<Position, Piece> board) {
            swap(board, Position.from("12"), Position.from("13"));
        }
    },
    HAN_RIGHT_SANG("2", Team.HAN) {
        @Override
        public void apply(Map<Position, Piece> board) {
            swap(board, Position.from("17"), Position.from("18"));
        }
    },
    HAN_INNER_SANG("3", Team.HAN) {
        @Override
        public void apply(Map<Position, Piece> board) {
            swap(board, Position.from("17"), Position.from("18"));
            swap(board, Position.from("12"), Position.from("13"));
        }
    },
    HAN_OUTER_SANG("4", Team.HAN) {
        @Override
        public void apply(Map<Position, Piece> board) {
        }
    },
    CHO_LEFT_SANG("1", Team.CHO) {
        @Override
        public void apply(Map<Position, Piece> board) {
            swap(board, Position.from("07"), Position.from("08"));
        }
    },
    CHO_RIGHT_SANG("2", Team.CHO) {
        @Override
        public void apply(Map<Position, Piece> board) {
            swap(board, Position.from("02"), Position.from("03"));
        }
    },
    CHO_INNER_SANG("3", Team.CHO) {
        @Override
        public void apply(Map<Position, Piece> board) {
            swap(board, Position.from("07"), Position.from("08"));
            swap(board, Position.from("02"), Position.from("03"));
        }
    },
    CHO_OUTER_SANG("4", Team.CHO) {
        @Override
        public void apply(Map<Position, Piece> board) {
        }
    };

    private final String code;
    private final Team team;

    BoardSetup(String code, Team team) {
        this.code = code;
        this.team = team;
    }

    public abstract void apply(Map<Position, Piece> board);

    public static BoardSetup from(String code, Team team) {
        for (BoardSetup setup : values()) {
            if (setup.code.equals(code) && setup.team == team) {
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
