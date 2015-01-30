package dominogamev4;

import java.awt.Color;
import javax.swing.JFrame;

public class GameManager {

    static GameState game;
    static Strategy strategyOne;
    static Strategy strategyTwo;
    static Hand handOne;
    static Hand handTwo;
    static Board board;
    static Status status;
    static int deadLock = 0;//used to determine if deadlock is reached
    static Display frame;
    static int scoreOne = 0;
    static int scoreTwo = 0;
    static int maxScore = 50; //set default max score to 50
    static int indexStrategy1;
    static int indexStrategy2;

    public static void main(String[] args) throws InterruptedException {
        game = GameState.getInstance();
        status = null;
        handOne = game.getHand(0);
        handTwo = game.getHand(1);
        board = game.getBoard();

        //create display
        createDisplay();
        //wait until status is not null
        while (status == null) {
            Thread.sleep(500);
        }

        if (status == Status.INTRO_STATE) {
            beginGame();
        }

    }

    public static void beginGame() throws InterruptedException {
        //play the first domino
        game.setCurrentPlayer(playFirstDom());

        //set the strategies
        strategyOne = setStrategy(indexStrategy1);
        strategyTwo = setStrategy(indexStrategy2);

        //while the game is not complete do the folliwing
        while (status == Status.PLAY_STATE) {
            //if any endgame conditions are met, 
            //find a winner and set status to Status.DONE_STATE
            if (handOne.getDoms().isEmpty()
                    || handTwo.getDoms().isEmpty()
                    || board.getDoms().isEmpty()
                    || deadLock == 2) {
                setScore();
                if (scoreOne >= maxScore) {
                    status = Status.DONE_STATE;
                    Display.displayWinner(strategyOne.getName());
                    break;
                } else if (scoreTwo >= maxScore) {
                    status = Status.DONE_STATE;
                    Display.displayWinner(strategyTwo.getName());
                    break;

                }

                game.deal(); // deal a new hand
                Display.newGame(); //clear the display for a new game
                board = game.getBoard();
                handOne = game.getHand(0);
                handTwo = game.getHand(1);
                game.setCurrentPlayer(playFirstDom());
            }
            play();
        }
    }

    /**
     * Plays the heaviest double or, barring any doubles being present, 
     * finds the heaviest Dom.
     *
     * @return: returns the number of the player who played the 
     * first Dom.
     */
    private static int playFirstDom() {
        Dom largestDouble = new Dom(-1, -1);
        Dom heaviest = new Dom(-1, -1);
        int playedFromHand = -1;
        int playedFromLocation = -1;
        int heaviestHand = -1;
        int heaviestLocation = -1;

        // find largest double and heaviest dom in hand 1
        for (int i = 0; i < handOne.getDoms().size(); i++) {
            Dom dom = handOne.getDoms().get(i);
            if (dom.isDouble() && dom.points() > largestDouble.points()) {
                largestDouble = dom;
                playedFromHand = 0;
                playedFromLocation = i;
            }
            if (dom.points() > heaviest.points()) {
                heaviest = dom;
                heaviestHand = 0;
                heaviestLocation = i;
            }
        }

        // find largest double and heaviest dom in both hands
        for (int i = 0; i < handTwo.getDoms().size(); i++) {
            Dom dom = handTwo.getDoms().get(i);
            if (dom.isDouble() && dom.points() > largestDouble.points()) {
                largestDouble = dom;
                playedFromHand = 1;
                playedFromLocation = i;
            }
            if (dom.points() > heaviest.points()) {
                heaviest = dom;
                heaviestHand = 1;
                heaviestLocation = i;
            }
        }

        //add largest double or heaviest dom to board, remove from hand,
        //return player number
        if (playedFromHand == 0 && largestDouble.points() != -2) {
            handOne.removeDom(playedFromLocation);
            board.addDom(Loc.LEFT, largestDouble);
            status = Status.PLAY_STATE;
            frame.validate();
            frame.repaint();
            return (1);
        } else if (playedFromHand == 1 && largestDouble.points() != -2) {
            handTwo.removeDom(playedFromLocation);
            board.addDom(Loc.LEFT, largestDouble);
            status = Status.PLAY_STATE;
            frame.validate();
            frame.repaint();
            return (0);
        } else if (heaviestHand == 0) {
            handOne.removeDom(heaviestLocation);
            board.addDom(Loc.LEFT, heaviest);
            status = Status.PLAY_STATE;
            frame.validate();
            frame.repaint();
            return (1);
        } else {
            handTwo.removeDom(heaviestLocation);
            board.addDom(Loc.LEFT, heaviest);
            status = Status.PLAY_STATE;
            frame.validate();
            frame.repaint();
            return (0);
        }



    }

    /**
     * calls playTile for currentPlayer, then changes currentPlayer
     * to the next player. If a player cannot play, deadlock is 
     * incremented. If a player can play, deadlock is reset to 0. Thus,
     * if deadlock = 2, the game is permanently stalled.
     *
     * @throws InterruptedException
     */
    private static void play() throws InterruptedException {
        //sleep between moves to make animiation easier to see
        Thread.sleep(1000);

        //play from current player hand if possible, 
        //increment deadlock variable if not 
        if (game.getCurrentPlayer() == 0) {
            if (!strategyOne.playTile()) {
                deadLock++;
            } else {
                deadLock = 0;
            }
            game.setCurrentPlayer(1);
        } else if (game.getCurrentPlayer() == 1) {
            if (!strategyTwo.playTile()) {
                deadLock++;
            } else {
                deadLock = 0;
            }
            game.setCurrentPlayer(0);
        }

        //refresh display
        frame.validate();
        frame.repaint();

    }

    /**
     * Sets the score for each player at the end of each game.
     */
    public static void setScore() {
        //calculates pips in hand 2 minus pips in hand 1
        int score = 0;
        for (Dom dom : handTwo.getDoms()) {
            score += dom.points();
        }
        for (Dom dom : handOne.getDoms()) {
            score -= dom.points();
        }

        //if the pips in hand 2 out weigh hand 1, player 1's score is set to
        //the difference
        if (score > 0) {
            scoreOne += score;
        }

        score = 0;
        //calculates pips in hand 1 minus pips in hand 2
        for (Dom dom : handTwo.getDoms()) {
            score -= dom.points();
        }
        for (Dom dom : handOne.getDoms()) {
            score += dom.points();
        }

        //if the pips in hand 1 out weigh hand 2, player 2's score is set to
        //the difference
        if (score > 0) {
            scoreTwo += score;
        }

        //refresh the display to reflect the score change
        Display.setScore();
        frame.validate();
        frame.repaint();
    }

    /**
     * Creates an instance of display.
     */
    public static void createDisplay() {
        frame = new Display();
        frame.setTitle("Dominoes");
        frame.setExtendedState(frame.MAXIMIZED_BOTH);
        frame.setBackground(Color.GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

    /**
     * This method is used to set the strategy variables to
     * instances of the appropriate strategy classes.
     *
     * @param index: receives the index of the combo-box 
     * selection as a parameter
     * @return Strategy: returns an instance of the strategy 
     * that corresponds with the combo-box selection on the display
     */
    public static Strategy setStrategy(int index) {
        switch (index) {
            case 1:
                return new AdamStrategy();
            case 2:
                return new CalumStrategy();
            case 3:
                return new CJStrategy();
            case 4:
                return new EthanStrategy();
            case 5:
                return new FaithStrategy();
            case 6:
                return new JacobStrategy();
            case 7:
                return new MattStrategy();
            case 8:
                return new CJStrategy();
            case 9:
                return new SebastianStrategy();
            case 10:
                return new CJStrategy();
            case 11:
                return new CJStrategy();
            default:
                return new CJStrategy();
        }
    }
}
