import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    CreateGame game;
    public Main() {

        setLayout(new BorderLayout());
        setSize(350, 250);
        setTitle("My first Game - 89231157");

        JPanel difficultyPanel = new JPanel();
        JPanel customGamePanel = new JPanel();

        difficultyPanel.setLayout( new BoxLayout( difficultyPanel, BoxLayout.Y_AXIS));
        customGamePanel.setLayout( new BoxLayout( customGamePanel, BoxLayout.Y_AXIS));

        JFrame Menu = new JFrame("Menu");
        Menu.setLayout( new GridLayout(3,1));
        Menu.setVisible(false);
        Menu.setSize(300,300);

        JButton restartButton = new JButton("Restart");
        restartButton.setSize(200,100);
        JButton newGameButton = new JButton("New Game");
        newGameButton.setSize(200,100);
        JButton exitButton = new JButton("Quit");
        exitButton.setSize(200,100);

        Menu.add(restartButton);
        Menu.add(newGameButton);
        Menu.add(exitButton);

        JLabel cLabel = new JLabel("Make your own game");
        JLabel dLabel = new JLabel("Choose a difficulty");

        JButton easyButton = new JButton("Easy");
        JButton mediumButton = new JButton("Medium");
        JButton hardButton = new JButton("Hard");
        JButton veryHardButton = new JButton("Impossible");

        JTextField sizeField = new JTextField("Enter size:");
        JTextField movesField = new JTextField("Enter moves:");
        JTextField targetField = new JTextField("Enter target:");
        JButton customGameButton = new JButton("Create custom game");

        difficultyPanel.add(dLabel);
        difficultyPanel.add(easyButton);
        difficultyPanel.add(mediumButton);
        difficultyPanel.add(hardButton);
        difficultyPanel.add(veryHardButton);

        customGamePanel.add(cLabel);
        customGamePanel.add(sizeField);
        customGamePanel.add(movesField);
        customGamePanel.add(targetField);
        customGamePanel.add(customGameButton);

        add(difficultyPanel, BorderLayout.WEST);
        add(customGamePanel, BorderLayout.EAST);

        customGameButton.addActionListener( e -> {
            try{
                int size = Integer.parseInt( sizeField.getText() );
                int moves = Integer.parseInt( movesField.getText() );
                int target = Integer.parseInt( targetField.getText() );
                game = new CreateGame(size, target, moves, 0);
                game.runGame();
                setVisible(false);
                Menu.setVisible(true);
            }catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter integers for size, target and #moves!");
        }
        });

        easyButton.addActionListener( e -> {
            game = new CreateGame(10,50,15, 0);
            game.runGame();
            setVisible(false);
            Menu.setVisible(true);
        });

        mediumButton.addActionListener( e -> {
            game = new CreateGame(10,50,10, 0);
            game.runGame();
            setVisible(false);
            Menu.setVisible(true);
        });

        hardButton.addActionListener( e -> {
            game = new CreateGame(10,50,7, 0);
            game.runGame();
            setVisible(false);
            Menu.setVisible(true);
        });
        veryHardButton.addActionListener( e -> {
            game = new CreateGame(10,50,6, 0);
            game.runGame();
            setVisible(false);
            Menu.setVisible(true);
        });

        newGameButton.addActionListener( e-> {
            game.closeGame();
            setVisible(true);
            Menu.setVisible(false);
        });

        restartButton.addActionListener( e-> {
            game.closeGame();
            game = new CreateGame(game.nSize, game.target, game.startingMoves, 0);
            game.runGame();
        });

        exitButton.addActionListener( e -> System.exit(0));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }


    public static void main(String[] args) {
        new Main();
    }

}
