package system;

import javax.swing.*;

public class popUP extends JFrame{
    private JPanel popPanel;
    private JLabel Message;

    public popUP(){
        //Creating Frame
        this.add(popPanel);
        this.setSize(400,300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public popUP(String message){
        //Creating Frame
        this.add(popPanel);
        this.setSize(400,300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        Message.setText(message);

    }
}
