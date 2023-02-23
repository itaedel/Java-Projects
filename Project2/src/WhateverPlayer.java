import java.util.Random;

/**
 * Class of a player who plays randomly
 */
public class WhateverPlayer implements Player{

    /**
     * constructor
     */
    public WhateverPlayer(){

    }

    /**
     * plays turn randomly by choosing a random number in [0, board_size^2)
     * range and trying to place mark in it until successful
     * @param board - the board
     * @param mark - the mark to place
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        Random rand = new Random();
        int board_size = board.getSize();
        int rand_int = rand.nextInt(board_size * board_size);
        while(!board.putMark(mark, rand_int / board_size,
                rand_int % board_size)){
            rand_int = rand.nextInt(board_size * board_size);
        }
        
    }
}
