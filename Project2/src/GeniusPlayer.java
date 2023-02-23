/**
 * the class of the genius player
 */
public class GeniusPlayer implements Player{

    /**
     * goes over each column starting with the middle one and marks all the
     * rows in a certain column until the rows are over and goes over to the
     * next cloumn. goes over the right half of the board and then the left
     * half of the board
     * @param board
     * @param mark
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        for(int col = board.getSize()/2; col < board.getSize(); col++){
            for(int row = 0; row < board.getSize(); row++){
                if(board.putMark(mark, row, col)){
                    return;
                }
            }
        }
        for(int col = (board.getSize()/2) - 1; col >= 0; col--){
            for(int row = 0; row < board.getSize(); row++){
                if(board.putMark(mark, row, col)){
                    return;
                }
            }
        }
    }
}
