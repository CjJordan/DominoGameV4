package dominogamev4;

public class GameState {

    private Board board;
    private BoneYard boneYard;
    private static GameState instance;
    private Hand[] hands;
    private int currentPlayer;
    private int numPlayers;
    private int winner;
    private int[] scores;
    private Status status;
    private Strategy[] strategies;

    private GameState() {
        this.numPlayers = 2;
        deal();
    }

    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    public Board getBoard() {
        return this.board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public BoneYard getBoneYard() {
        return this.boneYard;
    }

    public void setBoneYard(BoneYard boneYard) {
        this.boneYard = boneYard;
    }

    public Hand[] getHands() {
        return this.hands;
    }

    public void setHands(Hand[] hands) {
        this.hands = hands;
    }

    public int getCurrentPlayer() {
        return this.currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int getNumPlayers() {
        return this.numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public int getWinner() {
        return this.winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public int[] getScores() {
        return this.scores;
    }

    public void setScores(int[] scores) {
        this.scores = scores;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Strategy[] getStrategies() {
        return this.strategies;
    }

    public void setStrategies(Strategy[] strategies) {
        this.strategies = strategies;
    }

    public Hand getHand(int player) {
        return hands[player];
    }

    public void setHand(int player, Hand hand) {
        this.hands[player] = hand;
    }

    public void deal() {
        //make hands array length of numPlayers
        hands = new Hand[numPlayers];
        this.boneYard = new BoneYard();
        this.board = new Board();
        System.out.println("Board size in gamestate: " + board.getDoms().size());
        
        //deal to the players
        for (int i = 0; i < numPlayers; i++) {
            this.hands[i] = new Hand();
            for (int j = 0; j < 7; j++) {
                hands[i].addDom(j, boneYard.removeDom(j));
            }
        }
    }
}
