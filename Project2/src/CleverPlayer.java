/**
 * the Clever player class
 */
public class CleverPlayer implements Player{
    /**
     * constructor
     */
    public CleverPlayer(){
    }

    /**
     * we will simply mark all the available spots in each row from left
     * to right until the player wins
     * @param board - the board
     * @param mark - the mark
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        for(int i = 0; i < board.getSize(); i++){
            for(int j = 0; j < board.getSize(); j++){
                if(board.putMark(mark, i, j)){
                    return;
                }
            }
        }
    }
}
