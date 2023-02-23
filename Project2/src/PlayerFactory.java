/**
 * a factory class to construct Player objects
 */
public class PlayerFactory {
    private static final String HUMAN = "human";
    private static final String WHATEVER = "whatever";
    private static final String CLEVER = "clever";
    private static final String GENIUS = "genius";


    /**
     * a func that constructs a player objects according to its type
     * @param type - the name of the type of player to be created
     * @return Player object
     */
    public Player buildPlayer(String type){
        if(type.equalsIgnoreCase(HUMAN)){
            return new HumanPlayer();
        } else if(type.equalsIgnoreCase(WHATEVER)){
            return new WhateverPlayer();
        } else if(type.equalsIgnoreCase(CLEVER)){
            return new CleverPlayer();
        } else if (type.equalsIgnoreCase(GENIUS)) {
            return new GeniusPlayer();
        } else{
            return null;
        }
    }
}
