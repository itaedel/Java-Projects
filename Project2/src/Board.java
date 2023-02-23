/**
 * Class to handle the board and requested API
 */
public class Board {

    private int size;
    private static final int DEF_SIZE = 4; // default board size
    private Mark [][] board; //the board with the marks, will be blank on start


    /**
     * constructor with DEF_SIZE
     */
    public Board(){
        this.size = DEF_SIZE;
        this.board = new Mark[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                board[i][j] = Mark.BLANK;
            }
        }
    }

    /**
     * constructor with given size
     * @param size the size to build the board
     */
    public Board(int size){
        this.size = size;
        this.board = new Mark[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                board[i][j] = Mark.BLANK;
            }
        }
    }

    /**
     * a getter for the size of the array
     * @return int of board size
     */
    public int getSize(){
        return this.size;
    }

    /**
     * a getter for the board array
     * @return Mark[size][size]
     */
    public Mark[][] getBoard(){
        return this.board;
    }

    /**
     * func to place mark in (row,col) and returns bool according to success
     * @param mark - the mark to put
     * @param row - the row in board
     * @param col - the col in board
     * @return true if put mark, false otherwise
     */
    public boolean putMark(Mark mark, int row, int col){
        if(validCord(row, col) && board[row][col] ==  Mark.BLANK){
            board[row][col] = mark;
            return true;
        }
        return false;
    }

    /**
     * return the mark in a given valid coordinate, blank otherwise
     * @param row - the row
     * @param col - the col
     * @return Mark in board[row][col] if valid
     */
    public Mark getMark(int row, int col){
        if(validCord(row, col)){
            return board[row][col];
        }
        return Mark.BLANK;
    }

    /**
     * function to validate if coordinate is in valid range
     * @param row - the row to check
     * @param col - the col to check
     * @return true if valid, false otherwise
     */
    private boolean validCord(int row, int col){
        return ( (row >= 0 && row < size) && (col >= 0 && col < size) );
    }
}
