package chess.datatype;

/**
 * Created by Qing on 2/10/2015.
 * A chess.data type represents either black side or white side
 */
public enum PlayerSide {
    WHITE{
        /**
         *
         * @return BLACK
         */
        @Override
        public PlayerSide getOpponentSide(){
            return BLACK;
        }
    }, BLACK{
        /**
         *
         * @return WHITE
         */
        @Override
        public PlayerSide getOpponentSide(){
            return WHITE;
        }
    };

    /**
     *
     * @return the opponent side of the current side
     */
    public abstract PlayerSide getOpponentSide();
}
