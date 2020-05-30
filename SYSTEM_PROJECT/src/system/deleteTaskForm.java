package system;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class deleteTaskForm extends JFrame {
    private JButton deleteButton;
    private JButton cancelButton;
    private JTextField taskNumField;
    private JTextField taskTitleField;
    private JLabel Message;
    private JPanel MainDeleteTaskPanel;

    public deleteTaskForm() {
        //Creating Frame
        this.add(MainDeleteTaskPanel);
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        //cancel button listener
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();

            }
        });

        //delete button listener
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    if (!taskNumField.getText().isEmpty() ) {
                        Assignment assignment = Assignment.searchAssignment(Integer.parseInt(taskNumField.getText()));
                        if (assignment == null) {
                            Message.setText("can't find task by number");
                            Message.setVisible(true);
                        }else{
                            Assignment.removeAssignment(assignment.getAssignmentNum());
                            dispose();
                        }
                    }else {
                            if (!taskTitleField.getText().isEmpty()) {
                                 Assignment assignment = Assignment.searchAssignment(taskTitleField.getText());
                                if (assignment == null) {
                                    Message.setText("can't find task by title");
                                    Message.setVisible(true);
                                }else{
                                    Assignment.removeAssignment(assignment.getAssignmentNum());
                                    dispose();
                                }
                            }
                        }
                }catch(Exception ee){
                    Message.setText("Illegal input");
                    Message.setVisible(true);
                }



            }
        });
    }
}
