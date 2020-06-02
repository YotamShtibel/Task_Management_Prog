package system;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class addTaskForm extends JFrame{
    private JButton cancelButton;
    private JButton createButton;
    private JTextArea contextField;
    private JComboBox assignedBox;
    private JTextField titleField;
    private JPanel assignmentPanel;
    private JRadioButton lowRadioButton;
    private JRadioButton mediumRadioButton;
    private JRadioButton highRadioButton;
    private JCheckBox makeAReminderCheckBox;
    private LocalTime timeOfReminder;
    private LocalDate dateOfReminder;

    public void setTimeOfReminder(LocalTime timeOfReminder) {
        this.timeOfReminder = timeOfReminder;
    }

    public void setDateOfReminder(LocalDate dateOfReminder) {
        this.dateOfReminder = dateOfReminder;
    }

    public addTaskForm(app ap) {
        //Creating Frame
        this.add(assignmentPanel);
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        //setting assignmentBox
        Employee[] employees = Employee.getEmployees();
        assignedBox.addItem(null);
        for(int i = 0 ; i < Employee.getNumOfEmployees() ; i++)
            assignedBox.addItem(employees[i].getName());

        //initial radio buttons
        ButtonGroup buttons = new ButtonGroup();
        buttons.add(lowRadioButton);
        buttons.add(mediumRadioButton);
        buttons.add(highRadioButton);


        //cancel button listener
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();

            }
        });

        //create button listener
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               String name  = (String) assignedBox.getItemAt(assignedBox.getSelectedIndex());
               Employee employee = Employee.searchEmployee(name);
               String title = titleField.getText();
               String context = contextField.getText();
               int priority=1;
               if(lowRadioButton.isSelected())
                   priority = 1;
               if(mediumRadioButton.isSelected())
                   priority = 2;
               if(highRadioButton.isSelected())
                   priority = 3;
               Assignment assignment = new Reminder(Assignment.getNextAssignmentNum(), LocalTime.now(), LocalDate.now(),employee,priority, title, context, dateOfReminder,timeOfReminder);
               assignment.addAssignment();
               ap.setAssignmentList();
               dispose();

            }
        });

        //reminder checkBox listener
        makeAReminderCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new makeReminderForm(addTaskForm.this);

            }
        });
    }
}
