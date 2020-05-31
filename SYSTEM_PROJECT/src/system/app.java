package system;

import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.text.PasswordView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class app extends JFrame {
    private JComboBox employeeBox;
    private JPanel MainPanel;

    private JButton Add_Assignment_Button;
    private JButton logInButton;
    private JButton addTaskButton;
    private JButton removeEmployeeButton;
    private JLabel UserNameText;
    private JLabel StatusText;
    private JButton deleteTaskButton;
    private JScrollBar scrollBar1;
    private JList<String> assignmentList;
    private static Employee User = null;



    /// Setters & Getters


    public void setUserNameText(String userNameText) {
        UserNameText.setText(userNameText);
    }

    public void setStatusText(String statusText) {
        StatusText.setText(statusText);
    }

    public void setAssignmentList() {
        assignmentList.setModel(setElementsToLIstInFunc(Assignment.getAssignments())); //setting AssignmentList with current assignments
    }

    public static void setUser(Employee user) {
        User = user;

    }

    ////////////////////////////////

    //Functions

    private DefaultListModel<String> setElementsToLIstInFunc (Assignment[] assignments){

        DefaultListModel<String> l1 = new DefaultListModel<>();

        for(int i= 0 ; i < Assignment.getNumOfAssignments() ; i++){
            if (assignments[i].getAssignedTo() == User)
                l1.addElement(Integer.toString(assignments[i].getAssignmentNum()) + " | " + assignments[i].getTitle());
        }
        return l1;
    }

    public app(){

        // Frame Properties
        this.add(MainPanel);
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //setting manager
        Employee manager = new Employee(205662398,"Yotam",1111,true);
        if (Employee.searchEmployee(205662398).getId() != 205662398)
            manager.addEmployee();

        //setting employBox
        Employee[] employees = Employee.getEmployees();
        for(int i = 0 ; i < Employee.getNumOfEmployees() ; i++)
            employeeBox.addItem(employees[i].getName());




        // Add_Employee Button Listener
        Add_Assignment_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (User == null)
                    new popUP("Must log-in first");
                else{if(!User.isManager()){
                    new popUP("Only mannager can enter!");}
                else{new AddEmployeeForm();}}

}
        });

        // Log - In Button Listener
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new LogInForm(app.this);


            }
        });

        //Deleting Employee Listener
        removeEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (User == null)
                    new popUP("Must log-in first");
                else{if(!User.isManager()){
                    new popUP("Only mannager can enter!");}
                else{new RemoveEmployee();}}

            }
        });

        //Creating Assignment Listener
        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new addTaskForm(app.this);

            }
        });

        //Delete Task Listener
        deleteTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new deleteTaskForm(app.this);

            }
        });
    }

    public static void main(String[] args) {
        File employeesFile = new File("Employees_Data.txt");
        File AssignmentsFile = new File("Assignments_Data.txt");

        if (!employeesFile.exists()){
            try{
                employeesFile.createNewFile();
            }catch(IOException e){
                new popUP("Fatal Error!");
            }
        }

        if (!AssignmentsFile.exists()){
            try{
                AssignmentsFile.createNewFile();
            }catch(IOException e){
                new popUP("Fatal Error!");
            }
        }

        Assignment[] assignments = Assignment.getAssignments();
        for(int i=0 ; i < (Assignment.getNumOfAssignments()) ; i++){

            System.out.println("Assignment number " + (i+1) +" is:\n");
            assignments[i].printAssignment();

        }
        new app();
    }
}
