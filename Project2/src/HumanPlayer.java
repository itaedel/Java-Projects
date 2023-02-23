import java.util.Scanner;

/**
 * the class of the human player which reads from system.in
 */
public class HumanPlayer implements Player{

    /**
     * constructor
     */
    public HumanPlayer(){
    }

    /**
     * asks for instruction from the user and tries to place the mark
     * until it is in range and a blank one - aka valid
     * @param board - the game board
     * @param mark - the mark to be placed
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Player " + mark + ", type coordinates: ");
        while(true){
            int inp = Integer.parseInt(scan.nextLine());
            if(board.putMark(mark, inp / board.getSize(),
                    inp % board.getSize())){
                return;
            }
            System.out.println("Invalid coordinates, type again: ");
        }
    }
}
