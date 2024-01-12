import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Button extends JButton implements ActionListener {

    CreateGame parent;
    JPanel panel;
    int positionX;
    int positionY;
    int value;

    //  Button constructor
    public Button(JPanel panel, int positionX, int positionY, int value, CreateGame parent) {
        this.panel = panel;
        this.positionX = positionX;
        this.positionY = positionY;
        this.value = value;
        setText("" + value);
        addActionListener(this);
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Without this my colors don't change I have a MacBook
        setOpaque(true);
        setBorderPainted(true);

        //  Previous = current and current = new value clicked
        parent.prev = parent.current;
        int prevValue = parent.prev;
        parent.current = this.value;
        int currValue = this.value;

        Component[] components = panel.getComponents();
        panel.getComponentCount();

        // To reset the buttons on every click
        for (Component component : components) {
            Button rB = (Button) component;
            rB.setEnabled(true);
            //  I have a mac so, I have to use this commands for the colors to work
            rB.setOpaque(true);
            rB.setBorderPainted(true);
            rB.setBackground(Color.lightGray);
            rB.setForeground(Color.lightGray);
        }

        //  Go through the buttons and if the button is the button clicked then disable click option and store it
        for (int i = 0; i < components.length; i++) {
            Button c = (Button) components[i];
            if (c == this) {
                c.setEnabled(false);
            }
        }

        parent.sum += this.value;
        parent.sumLabel.setText("Current sum: " + parent.sum);
        parent.moves--;
        parent.movesLabel.setText("Moves left:" + parent.moves);

        if (parent.moves <= 0) {
            JOptionPane.showMessageDialog(parent, "No moves left, you have reached the end of the game.\nPress 'ok' for results.");
            // I used absolute here is the explanation. For example what if sum > target, then we get negative distance, therefore this way it will be always positive distance between these values
            int distance = Math.abs(parent.target - parent.sum);
            if (distance == 0) {
                JOptionPane.showMessageDialog(parent, "Result:\n"+"YOU WON\n"+"DEVIATION: "+distance+"\nCONGRATULATIONS: You have a prefect score!!!");
            } else if (distance > 0 && distance <= 3) {
                JOptionPane.showMessageDialog(parent, "Result:\n"+"DEVIATION: "+distance+"\nVery good score");
            } else if (distance > 3 && distance <= 6) {
                JOptionPane.showMessageDialog(parent, "Result:\n"+"DEVIATION: "+distance+"\nYou were close, Try again.");
            } else if (distance > 6 && distance <= 10) {
                JOptionPane.showMessageDialog(parent, "Result:\n"+"DEVIATION: "+distance+"\nThat was bad...");
            } else {
                JOptionPane.showMessageDialog(parent, "Result:\n"+"DEVIATION: "+distance+"\nDid you even try?");
            }
            parent.closeGame();

        }

        for (Component component : components) {
            Button validB = (Button) component;
            // With the for loop we go through all the buttons,
            // Then validB[0] is the button (1,1) therefore we get its position. -> validB[1] would be (1,2) and so on...
            // Then check if it is divisible by the value of the button clicked and the value of the previous button clicked

            // On the first move the prev value will be 0.Therefore, it will give and error because X % 0 is undefined
            if ( prevValue == 0) {
                if (validB.positionX % currValue == 0 || validB.positionY % currValue == 0) {
                    validB.setOpaque(true);
                    validB.setBorderPainted(true);
                    validB.setBackground(Color.GREEN);
                    validB.setForeground(Color.GREEN);
                } else {
                    //  Paint the invalid move buttons red and disable clicking
                    validB.setBackground(Color.RED);
                    validB.setForeground(Color.RED);
                    validB.setEnabled(false);
                }

            } else if (validB.positionX % prevValue == 0 && validB.positionY % currValue == 0) {
                validB.setOpaque(true);
                validB.setBorderPainted(true);
                validB.setBackground(Color.GREEN);
                validB.setForeground(Color.GREEN);
            } else if (validB.positionX % currValue == 0 && validB.positionY % prevValue == 0) {
                validB.setOpaque(true);
                validB.setBorderPainted(true);
                validB.setBackground(Color.GREEN);
                validB.setForeground(Color.GREEN);
            } else {
                //  Paint the invalid move buttons red and disable clicking
                validB.setBackground(Color.RED);
                validB.setForeground(Color.RED);
                validB.setEnabled(false);
            }


        }
    }
}

