import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class CreateGame extends JPanel {
    Random r = new Random();
    int prev = 0;
    int current = 0;
    int nSize;
    int target;
    int moves;
    int startingMoves;
    int sum;
    JLabel sumLabel = new JLabel();
    JLabel movesLabel = new JLabel();
    JLabel targetLabel = new JLabel();
    JFrame gameFrame;

    public CreateGame(int nSize, int target, int moves, int sum){
      this.nSize = nSize;
      this.target = target;
      this.moves = moves;
      this.sum = sum;
    }

    public void runGame(){

        startingMoves = moves;

        gameFrame = new JFrame();
        gameFrame.setLayout( new BorderLayout());
        gameFrame.setVisible(true);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setBounds(450,100,1000,1000);

        JPanel moves_targetHolder = new JPanel();
        moves_targetHolder.setLayout( new GridLayout(1,3));

        JPanel fieldPanel = new JPanel();
        JLabel myLabel = new JLabel("Reach target to win ->", JLabel.CENTER);
        sumLabel = new JLabel("Current sum: "+sum, JLabel.CENTER);
        movesLabel = new JLabel("Moves left: "+moves, JLabel.CENTER);
        targetLabel = new JLabel("Target: "+target, JLabel.CENTER);

        gameFrame.add(fieldPanel, BorderLayout.CENTER);
        gameFrame.add(moves_targetHolder, BorderLayout.NORTH);
        moves_targetHolder.add(myLabel);
        moves_targetHolder.add(targetLabel);
        moves_targetHolder.add(movesLabel);
        gameFrame.add(sumLabel, BorderLayout.SOUTH);

        fieldPanel.setLayout( new GridLayout(nSize,nSize));
        for (int i = 0; i < nSize; i++) {
            for (int j = 0; j < nSize; j++) {
                fieldPanel.add( new Button(fieldPanel, i, j, r.nextInt(1,10), this));
            }
        }

    }

    public void closeGame(){
        gameFrame.setVisible(false);
        gameFrame.dispose();
    }

}
