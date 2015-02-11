package datatype;

/**
 * Created by Qing on 2/10/2015.
 * A data type represents either black side or white side
 */
public enum PlayerSide {
    WHITE{
        @Override
        public PlayerSide getOpponentSide(){
            return BLACK;
        }
    }, BLACK{
        @Override
        public PlayerSide getOpponentSide(){
            return WHITE;
        }
    };
    public abstract PlayerSide getOpponentSide();
}
