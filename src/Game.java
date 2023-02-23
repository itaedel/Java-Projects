/**
 * class for managing a single game
 */
public class Game {
    private static final int DEF_WIN_STREAK = 3; // DEF_WIN_STREAK
    private Player playerX;
    private Player playerO;
    private Renderer renderer;
    private int winStreak;
    private Board gameBoard; // the board object


    /**
     * constructor with default values
     *
     * @param playerX  - the X player
     * @param playerO  - the O player
     * @param renderer - the renderer object that will render the game
     */
    public Game(Player playerX, Player playerO, Renderer renderer) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.renderer = renderer;

        gameBoard = new Board();
        this.winStreak = DEF_WIN_STREAK;
    }

    /**
     * constructor with given size and win streak
     *
     * @param playerX   - player x
     * @param playerO   - player o
     * @param size      - the board size
     * @param winStreak - the win streak
     * @param renderer  - the renderer object that will render the game
     */
    public Game(Player playerX, Player playerO,
                int size, int winStreak, Renderer renderer) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.renderer = renderer;

        gameBoard = new Board(size);
        this.winStreak = Math.min(size, winStreak);
    }

    /**
     * a getter for win streak
     * @return an int rep. the win streak
     */
    public int getWinStreak() {
        return this.winStreak;
    }

    /**
     * a running of the game- playerX is first
     * @return the winner sign or blank if there is no winner
     */
    public Mark run() {
        int places = 0;
        renderer.renderBoard(gameBoard);
        while (places < gameBoard.getSize() * gameBoard.getSize()) {
            playerX.playTurn(gameBoard, Mark.X);
            places++;
            if(didPlayerWin(Mark.X)){
                return Mark.X;
            }
            renderer.renderBoard(gameBoard);
            if(places >= gameBoard.getSize()*gameBoard.getSize()){
                break;
            }
            playerO.playTurn(gameBoard, Mark.O);
            places++;
            if(places >= gameBoard.getSize()*gameBoard.getSize()){
                break;
            }
            renderer.renderBoard(gameBoard);
            if(didPlayerWin(Mark.O)){
                return Mark.O;
            }
        }
        return Mark.BLANK;
    }

    /**
     * function to check if a player won
     * @param mark - the player's mark
     * @return true if won, false otherwise
     */
    private boolean didPlayerWin(Mark mark) {
        int count = 0;
        for (int i = 0; i < gameBoard.getSize(); i++) {
            for (int j = 0; j < gameBoard.getSize(); j++) {
                if (gameBoard.getMark(i, j) == mark) {
                    if (checkAllDirections(i, j, mark)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * checks all the direction for a win streak
     * @param row - row to check
     * @param col - col to check
     * @param mark - the player mark
     * @return true if won, false otherwise
     */
    private boolean checkAllDirections(int row, int col, Mark mark) {
        return checkRow(row, col, mark) || checkCol(row, col, mark) ||
                checkDiagTopLeft(row, col, mark) ||
                checkDiagBottomLeft(row,col,mark);
    }

    /**
     * checks row if there are enough marks for win
     * @param row - the row to check
     * @param col - the col to check
     * @param mark - the mark needed
     * @return true if there are enough marks, false otherwise
     */
    private boolean checkRow(int row, int col, Mark mark){
        int count_marks = 1;
        Mark[][] board = gameBoard.getBoard();
        for (int i = col+1; i < gameBoard.getSize(); i++) {
            if(board[row][i] == mark){
                count_marks++;
            } else{
                break;
            }
        }
        if(count_marks >= winStreak){
            return true;
        }
        for(int i = col-1; i >= 0; i--){
            if(board[row][i] == mark){
                count_marks++;
            } else{
                break;
            }
        }
        return count_marks >= winStreak;
    }

    /**
     * checks col if there are enough marks for win
     * @param row - the row to check
     * @param col - the col to check
     * @param mark - the mark needed
     * @return true if there are enough marks, false otherwise
     */
    private boolean checkCol(int row, int col, Mark mark){
        int count_marks = 1;
        Mark[][] board = gameBoard.getBoard();
        for (int i = row+1; i < gameBoard.getSize(); i++) {
            if(board[i][col] == mark){
                count_marks++;
            } else{
                break;
            }
        }
        if(count_marks >= winStreak){
            return true;
        }
        for(int i = row-1; i >= 0; i--){
            if(board[i][col] == mark){
                count_marks++;
            } else{
                break;
            }
        }
        return count_marks >= winStreak;
    }

    /**
     * checks diagonley from top Left to bottom Right
     * @param row - the row
     * @param col - the col
     * @param mark - the mark to check for
     * @return true if enough marks, false otherwise
     */
    private boolean checkDiagTopLeft(int row, int col, Mark mark){
        int count_marks = 1;
        Mark[][] board = gameBoard.getBoard();
        for (int i = 1; i < gameBoard.getSize(); i++) {
            if(row - i < 0 || col - i < 0){
                break;
            }
            else if(board[row-i][col-i] == mark){
                count_marks++;
            } else{
                break;
            }
        }
        if(count_marks >= winStreak){
            return true;
        }
        for(int i = 1; i < gameBoard.getSize(); i++){
            if(row+i >= gameBoard.getSize() || col+i >= gameBoard.getSize()){
                break;
            } else if(board[row+i][col+i] == mark){
                count_marks++;
            } else{
                break;
            }
        }
        return count_marks >= winStreak;
    }

    /**
     * checks diagonley from bottom Left to top Right
     * @param row - the row
     * @param col - the col
     * @param mark - the mark to check for
     * @return true if enough marks, false otherwise
     */
    private boolean checkDiagBottomLeft(int row, int col, Mark mark){
        int count_marks = 1;
        Mark[][] board = gameBoard.getBoard();
        for (int i = 1; i < gameBoard.getSize(); i++){
            if(row + i >= gameBoard.getSize() || col - i < 0){
                break;
            }
            else if(board[row+i][col-i] == mark){
                count_marks++;
            } else{
                break;
            }
        }
        if(count_marks >= winStreak){
            return true;
        }
        for(int i = 1; i < gameBoard.getSize(); i++){
            if(row - i < 0 || col+i >= gameBoard.getSize()){
                break;
            } else if(board[row-i][col+i] == mark){
                count_marks++;
            } else{
                break;
            }
        }
        return count_marks >= winStreak;
    }
}
