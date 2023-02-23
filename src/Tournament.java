/**
 * a class for the whole tournament management
 */
public class Tournament {
    private int rounds;
    private Renderer renderer;
    private Player[] players;

    /**
     * constructor for the Tournament
     * @param rounds - the num of rounds to play
     * @param renderer - the renderer of the board
     * @param players - an array of players
     */
    public Tournament(int rounds, Renderer renderer, Player[] players){
        this.rounds = rounds;
        this.renderer = renderer;
        this.players = players;
    }

    /**
     * play tournament accor. to how many rounds there are, print results
     * @param size - size of board
     * @param winStreak - win streak number
     * @param playerNames - the names of players
     */
    public void playTournament(int size, int winStreak, String[] playerNames){
        int [] playerWins = {0,0,0}; // [player1, player2, ties]
        if(winStreak > size){
            winStreak = size;
        }
        for(int i = 0; i < rounds; i++){
            changeWins(playerWins, runRound(i, size, winStreak), i);
        }
        System.out.println("#########Results#########");
        System.out.println("Player 1, " + playerNames[0] +
                " won: " + playerWins[0]);
        System.out.println("Player 2, " + playerNames[1] +
                " won: " + playerWins[1]);
        System.out.println("Ties: " + playerWins[2]);
    }

    /**
     * private function to run a single round according to inst.
     * @param cur_round - the current round of the game
     * @param size - the size of the board
     * @param winStreak - the win streak to win the game
     * @return the Symbol of the winner of the game
     */
    private Mark runRound(int cur_round, int size, int winStreak){
        Game game = null;
        if(cur_round % 2 == 0){ // player1 is X
            game = new Game(players[0], players[1], size,
                    winStreak, renderer);
        } else{ // player1 is O
            game = new Game(players[1], players[0], size,
                    winStreak, renderer);
        }
        return game.run();
    }

    /**
     * changes the player win array according to the rules of the game
     * @param playerWins - the array of wins
     * @param winner - the winner symbol of the round
     * @param cur_round - the current round of the game
     */
    private void changeWins(int [] playerWins, Mark winner, int cur_round){
        if(winner == Mark.BLANK){
            playerWins[2] += 1;
        } else if(cur_round % 2 == 0){
            if(winner == Mark.X){
                playerWins[0]++;
            } else{
                playerWins[1]++;
            }
        } else{
            if(winner == Mark.X){
                playerWins[1]++;
            } else{
                playerWins[0]++;
            }
        }
    }

    /**
     * the main method to run the game
     * @param args - player names are args[4] and args[5]
     */
    public static void main(String[] args){
        int rounds = Integer.parseInt(args[0]);
        int size = Integer.parseInt(args[1]);
        int win_streak = Integer.parseInt(args[2]);
        String [] playerNames = {args[4], args[5]};
        RendererFactory rendF = new RendererFactory();
        Renderer render = rendF.buildRenderer(args[3], size);
        PlayerFactory playerF = new PlayerFactory();
        Player [] players = { playerF.buildPlayer(args[4]),
                playerF.buildPlayer(args[5])};
        if(players[0] == null || players[1] == null){
            System.out.println("Choose a player, and start again");
            System.out.println(
                    "The players: [human, clever, whatever, genius]");
        } else{
            Tournament tournament = new Tournament(rounds, render, players);
            tournament.playTournament(size, win_streak, playerNames);
        }
    }
}
