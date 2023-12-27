package ma.enset.chess.engine;

import ma.enset.chess.engine.player.BlackPlayer;
import ma.enset.chess.engine.player.WhitePlayer;
import ma.enset.chess.engine.player.player;

public enum Alliance {
    WHITE {
        @Override
        public boolean isWhite() {
            return true;
        }

        @Override
        public boolean isBlack() {
            return false;
        }

        @Override
        public player choosePlayer(final WhitePlayer whitePlayer,
                                   final BlackPlayer blackPlayer) {
            return whitePlayer;
        }

        @Override
        public int getDirection() {
            return -1;
        }
    },
    BLACK {
        @Override
        public boolean isWhite() {
            return false;
        }

        @Override
        public boolean isBlack() {
            return true;
        }

        @Override
        public player choosePlayer(final WhitePlayer whitePlayer,
                                   final BlackPlayer blackPlayer) {
            return blackPlayer;
        }

        @Override
        public int getDirection() {
            return 1;
        }
    };

    public abstract int getDirection();

    public abstract boolean isWhite();

    public abstract boolean isBlack();

    public abstract player choosePlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer);

}