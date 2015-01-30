package dominogamev4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Display extends JFrame {

    static JPanel hand1Panel;
    static JPanel[][] panelHolder;
    static JPanel hand2Panel;
    static JPanel boardPanel;
    static GameState game;
    static Hand handOne;
    static Hand handTwo;
    static Board board;
    static int rightCount = 1;
    static int leftCount = 0;
    static boolean newRowLeft = false;
    static boolean newRowRight = false;
    static JLabel playerOneScore;
    static JLabel playerTwoScore;
    static final String[] playerStrings = {"Select a player", "Adam",
        "Calum", "CJ", "Ethan", "Faith-Anne", "Jacob", "Matt", "Paul",
        "Sebastian"};

    /**
     * Display Constructor:creates and populates the necessary
     * panels and adds them to the frame. Also contains the needed 
     * action listeners.
     * 
     */
    public Display() {

       //get instance of game and retrieve hands and board arrays
        game = GameState.getInstance();
        handOne = game.getHand(0);
        handTwo = game.getHand(1);
        board = game.getBoard();
        
        
        //Create a panel for the board
        boardPanel = new JPanel();
        boardPanel.setPreferredSize(new Dimension(1115, 690));
        boardPanel.setLayout(null);
        //Add a bevelled border around the board
        boardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(), 
                BorderFactory.createLoweredBevelBorder()));
        boardPanel.setBackground(Color.decode("#4595c3"));
        JPanel boardContainerPanel = new JPanel();
        boardContainerPanel.setPreferredSize(new Dimension(1125, 700));
        boardContainerPanel.add(boardPanel);
        boardContainerPanel.setBackground(Color.decode("#4595c3"));



        //Add a drop list to enable user to select player one
        JPanel dropListPanel1 = new JPanel();
        dropListPanel1.setPreferredSize(new Dimension(150, 30));
        dropListPanel1.setBackground(Color.decode("#4595c3"));
        final JComboBox playerOneBox = new JComboBox(playerStrings);
        dropListPanel1.add(playerOneBox);

        //Add player 1 score
        JPanel scorePanel1 = new JPanel();
        scorePanel1.setPreferredSize(new Dimension(150, 30));
        scorePanel1.setBackground(Color.decode("#4595c3"));
        playerOneScore = new JLabel("Score: " + GameManager.scoreOne);
        playerOneScore.setForeground(Color.WHITE);
        playerOneScore.setFont(new Font(playerOneScore.getFont().getName(),
                playerOneScore.getFont().getStyle(), 22));
        scorePanel1.add(playerOneScore);

        //Create a 7 column grid layout for hand 1 dominoes
        hand1Panel = new JPanel();
        hand1Panel.setLayout(new GridLayout(7, 1));
        for (int i = 0; i < handOne.getDoms().size(); i++) {
            Dom domino = handOne.getDoms().get(i); // Get a domino
            hand1Panel.add(getImage(domino));

        }



        hand1Panel.setPreferredSize(new Dimension(150, 670));
        JPanel hand1ContainerPanel = new JPanel();
        hand1ContainerPanel.setPreferredSize(new Dimension(150, 700));
        hand1ContainerPanel.add(dropListPanel1, BorderLayout.NORTH);
        hand1ContainerPanel.add(hand1Panel, BorderLayout.CENTER);
        hand1ContainerPanel.add(scorePanel1, BorderLayout.SOUTH);
        hand1Panel.setBackground(Color.decode("#4595c3"));
        hand1ContainerPanel.setBackground(Color.decode("#4595c3"));


          //Add a drop list to enable user to select player two
        JPanel dropListPanel2 = new JPanel();
        dropListPanel2.setPreferredSize(new Dimension(1500, 30));
        dropListPanel2.setBackground(Color.decode("#4595c3"));
        final JComboBox playerTwoBox = new JComboBox(playerStrings);
        dropListPanel2.add(playerTwoBox);

        //add player 2 score
        JPanel scorePanel2 = new JPanel();
        scorePanel2.setPreferredSize(new Dimension(150, 30));
        scorePanel2.setBackground(Color.decode("#4595c3"));
        playerTwoScore = new JLabel("Score: " + GameManager.scoreTwo);
        playerTwoScore.setForeground(Color.WHITE);
        playerTwoScore.setFont(new Font(playerTwoScore.getFont().getName(), 
                playerTwoScore.getFont().getStyle(), 22));
        scorePanel2.add(playerTwoScore);

        //Create a 7 column grid layout for hand 2 dominoes
        hand2Panel = new JPanel();
        hand2Panel.setLayout(new GridLayout(7, 1));
        for (int i = 0; i < handTwo.getDoms().size(); i++) {
            Dom domino = handTwo.getDoms().get(i); // Get a domino
            hand2Panel.add(getImage(domino));

        }
        hand2Panel.setPreferredSize(new Dimension(150, 670));
        JPanel hand2ContainerPanel = new JPanel();
        hand2ContainerPanel.setPreferredSize(new Dimension(150, 700));
        hand2ContainerPanel.add(dropListPanel2, BorderLayout.NORTH);
        hand2ContainerPanel.add(hand2Panel, BorderLayout.CENTER);
        hand2ContainerPanel.add(scorePanel2, BorderLayout.SOUTH);
        hand2Panel.setBackground(Color.decode("#4595c3"));
        hand2ContainerPanel.setBackground(Color.decode("#4595c3"));


        //Create a 28 column grid layout for the 
        //boneyard displaying blank dominoes
        JPanel boneyardPanel = new JPanel();
        for (int i = 0; i < game.getBoneYard().getDoms().size(); i++) {
            boneyardPanel.add(new JLabel(
                    new ImageIcon("Images/dom2/boneyard.png")));
        }
        boneyardPanel.setPreferredSize(new Dimension(1300, 100));
        boneyardPanel.setBackground(Color.decode("#4595c3"));
        JPanel boneyardContainerPanel = new JPanel();
        boneyardContainerPanel.add(boneyardPanel);
        boneyardContainerPanel.setPreferredSize(new Dimension(1300, 110));
        boneyardContainerPanel.setBackground(Color.decode("#4595c3"));
        //add the play button to start game
        JPanel buttonHolder = new JPanel();
        JButton start = new JButton("Play");
        buttonHolder.add(start);
        //add a textfield for the maximum score to 
        //which the user wishes to play
        final JTextField max = new JTextField("Max Score");
        buttonHolder.add(max);
        buttonHolder.setBackground(Color.decode("#4595c3"));
        boneyardPanel.add(buttonHolder);


        //add content to the frame 
        add(hand1ContainerPanel, BorderLayout.WEST);
        add(boardContainerPanel, BorderLayout.CENTER);
        add(hand2ContainerPanel, BorderLayout.EAST);
        add(boneyardContainerPanel, BorderLayout.SOUTH);


        /**
         * Start button listener: changes game status to intro_state and
         * sets max score in game to the specified score in the max 
         * text field.
         */
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameManager.status = Status.INTRO_STATE;
                GameManager.maxScore = Integer.parseInt(max.getText());
            }
        });

       /**
        * playerOne ComboBox Listener: sets the player1 strategy in 
        * game to the specified strategy.
        */
        playerOneBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameManager.indexStrategy1 = (playerOneBox.getSelectedIndex());
            }
        });
        
        /**
        * playerTwo ComboBox Listener: sets the player 2 strategy in 
        * game to the specified strategy.
        */
        playerTwoBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameManager.indexStrategy2 = (playerTwoBox.getSelectedIndex());
            }
        });


    }

   /**
    * Retrieves the appropriate vertical image for the specified Dom.
    * This is used in the hand displays and for the vertical domino in 
    * the turn on the board.
    * @param domino: the Dom that needs to be graphically 
    * represented is passed in as a parameter
    * @return image: returns the  specified image as a JButton
    */
    public static JButton getImage(Dom domino) {
        JButton image;
        //the images are named after the left and right pips 
        // (ex: domino.getLeft() = 6 and domino.getRight()= 4, then image name is 64.png)
        int left = domino.getLeft();
        int right = domino.getRight();
        int num;
        num = (left * 10) + right;

        image = new JButton(new ImageIcon("Images/dom2/" + num + ".png"));
        Border emptyBorder = BorderFactory.createEmptyBorder();
        image.setBorder(emptyBorder);
        return image;
    }

    /**
     * Retrieves the flipped vertical image for the specified Dom.
     * That is, domino.getLeft value is displayed on the right of 
     * the image and domino.getRight value is displayed on the left. 
     * This method is called for the vertical domino in  the turn on
     * the board.
     * @param domino: the Dom that needs to be graphically 
     * represented is passed in as a parameter
     * @return image: returns the  specified image as a JButton 
     */
    public static JButton getFlipImage(Dom domino) {
        JButton image;
        int left = domino.getLeft();
        int right = domino.getRight();
        int num;
        num = (right * 10) + left;

        image = new JButton(new ImageIcon("Images/dom2/" + num + ".png"));
        Border emptyBorder = BorderFactory.createEmptyBorder();
        image.setBorder(emptyBorder);
        return image;
    }

     /**
    * Retrieves the appropriate horizontal image for the specified Dom.
    * @param domino: the Dom that needs to be graphically represented
    * is passed in as a parameter
    * @return image: returns the  specified image as a JButton
    */
    public static JButton getBoardImage(Dom domino) {
        JButton image;
        int left = domino.getLeft();
        int right = domino.getRight();
        int num;
        num = (left * 10) + right;

        image = new JButton(new ImageIcon("Images/dom2/" + num 
                + "Side.png"));
        Border emptyBorder = BorderFactory.createEmptyBorder();
        image.setBorder(emptyBorder);
        return image;
    }

    /**
     * Retrieves the flipped horizontal image for the specified Dom. 
     * That is domino.getLeft value is displayed on the right of the
     * image and domino.getRight value is displayed on the left.
     * @param domino: the Dom that needs to be graphically represented
     * is passed in as a parameter
     * @return image: returns the  specified image as a JButton 
     */
    public static JButton getBoardImageFlip(Dom domino) {
        JButton image;
        int left = domino.getLeft();
        int right = domino.getRight();
        int num;
        num = (right * 10) + left;

        image = new JButton(new ImageIcon("Images/dom2/" + num
                + "Side.png"));
        Border emptyBorder = BorderFactory.createEmptyBorder();
        image.setBorder(emptyBorder);
        return image;
    }

    /**
     * Displays the specified Dom image in the appropriate right
     * position on the board.
     * @param dom: the Dom to be placed on the board is passed
     * in as a parameter
     */
    public static void addDomRight(Dom dom) {
        
        //reset hand 1 display to reflect any changes
        hand1Panel.removeAll();
        hand1Panel.setLayout(new GridLayout(7, 1));
        for (int i = 0; i < handOne.getDoms().size(); i++) {
            Dom domino = handOne.getDoms().get(i); // Get a domino
            hand1Panel.add(Display.getImage(domino));
        }

        //reset hand 2 display to reflect any changes
        hand2Panel.removeAll();
        hand2Panel.setLayout(new GridLayout(7, 1));
        for (int i = 0; i < handTwo.getDoms().size(); i++) {
            Dom domino = handTwo.getDoms().get(i); // Get a domino
            hand2Panel.add(Display.getImage(domino));
        }


        Insets insets = boardPanel.getInsets();
        
        //if dominoes have reached the border, display a vertical
        //flip domino to begin the turn else if this is the initial
        // row display the domino to the right, if it's  a secondary
        // row display a flip of the domino

        if ((boardPanel.getWidth() / 2 + (rightCount * 91) + insets.left)
                > (insets.left + boardPanel.getWidth() - 90)
                && !newRowRight) {
            newRowRight = true;
            JButton x = Display.getFlipImage(dom);
            Dimension size = x.getPreferredSize();
            boardPanel.add(x);
            x.setBounds(boardPanel.getWidth() / 2 +
                    ((rightCount) * 91 - 45) + insets.left,
                    boardPanel.getHeight() / 2 - 90 + 
                    insets.top, size.width, size.height);
        } else if (!newRowRight) {
            JButton x = Display.getBoardImage(dom);
            Dimension size = x.getPreferredSize();
            boardPanel.add(x);
            x.setBounds(boardPanel.getWidth() / 2 +
                    (rightCount * 91) + insets.left,
                    boardPanel.getHeight() / 2 + 
                    insets.top, size.width, size.height);
            rightCount++;
        } else {
            rightCount--;
            JButton x = Display.getBoardImageFlip(dom);
            Dimension size = x.getPreferredSize();
            boardPanel.add(x);
            x.setBounds(boardPanel.getWidth() / 2 + 
                    (rightCount * 91) + insets.left,
                    boardPanel.getHeight() / 2 - 135 
                    + insets.top, size.width, size.height);
        }

    }

    /**
     * Displays the specified Dom image in the appropriate left 
     * position on the board.
     * @param dom: the Dom to be placed on the board is passed 
     * in as a parameter
     */
    public static void addDomLeft(Dom dom) {

        //reset hand 1 display to reflect any changes
        hand1Panel.removeAll();
        hand1Panel.setLayout(new GridLayout(7, 1));
        for (int i = 0; i < handOne.getDoms().size(); i++) {
            Dom domino = handOne.getDoms().get(i); // Get a domino
            hand1Panel.add(Display.getImage(domino));
        }
        
        //reset hand 2 display to reflect any changes
        hand2Panel.removeAll();
        hand2Panel.setLayout(new GridLayout(7, 1));
        for (int i = 0; i < handTwo.getDoms().size(); i++) {
            Dom domino = handTwo.getDoms().get(i); // Get a domino
            hand2Panel.add(Display.getImage(domino));
        }

        Insets insets = boardPanel.getInsets();

        //if dominoes have reached the border, display a vertical
        //flip domino to begin the turn else if this is the initial 
        //row display the domino to the left, if it's  a secondary 
        // row display a flip of the domino
        if ((boardPanel.getWidth() / 2 - (leftCount * 91) + insets.left)
                < insets.left && !newRowLeft) {
            newRowLeft = true;
            JButton x = Display.getFlipImage(dom);
            Dimension size = x.getPreferredSize();
            boardPanel.add(x);

            x.setBounds(boardPanel.getWidth() / 2 - ((leftCount - 1) * 91)
                    + insets.left,
                    boardPanel.getHeight() / 2 + 45 + insets.top,
                    size.width, size.height);
        } else if (!newRowLeft) {
            JButton x = Display.getBoardImage(dom);
            Dimension size = x.getPreferredSize();
            boardPanel.add(x);

            x.setBounds(boardPanel.getWidth() / 2 -
                    (leftCount * 91) + insets.left,
                    boardPanel.getHeight() / 2 + 
                    insets.top, size.width, size.height);
            leftCount++;
        } else {
            JButton x = Display.getBoardImageFlip(dom);
            Dimension size = x.getPreferredSize();
            boardPanel.add(x);
            leftCount--;
            x.setBounds(boardPanel.getWidth() / 2 - 
                    (leftCount * 91) + insets.left,
                    boardPanel.getHeight() / 2 + 135 + 
                    insets.top, size.width, size.height);
        }

    }

    /**
     * Displays the winning player's name in a JLabel when a player reaches
     * or exceeds the specified max score.
     * @param name: The winning strategy's name is passed in as a parameter
     */
    public static void displayWinner(String name) {
        Insets insets = boardPanel.getInsets();
        JLabel x = new JLabel("Winner: " + name);
        x.setFont(new Font(playerOneScore.getFont().getName(), 
                playerOneScore.getFont().getStyle(), 22));
        Dimension size = x.getPreferredSize();
        x.setForeground(Color.WHITE);
        boardPanel.add(x);
        x.setBounds(boardPanel.getWidth() / 2  - 45 + insets.left,
                45 + insets.top, size.width, size.height);
    }

    /**
     * Set score text for player one and player two.
     * This method is called after each game. 
     */
    public static void setScore() {
        playerOneScore.setText("Score: " + GameManager.scoreOne);
        playerTwoScore.setText("Score: " + GameManager.scoreTwo);
    }

   /**
    * Reset the display after each game and retrieves the 
    * newly dealt hands and blank board from game state.
    */
    public static void newGame() {

        handOne = game.getHand(0);
        handTwo = game.getHand(1);
        board = game.getBoard();
        Display.boardPanel.removeAll();
        Display.hand1Panel.removeAll();
        Display.hand2Panel.removeAll();
        Display.rightCount = 1;
        Display.leftCount = 0;
        Display.newRowLeft = false;
        Display.newRowRight = false;
    }
}

