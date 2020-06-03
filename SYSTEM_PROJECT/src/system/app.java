package system;

import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.PasswordView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.*;
import java.util.List;


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
<<<<<<< Updated upstream
    private JScrollBar scrollBar1;
=======
>>>>>>> Stashed changes
    private JList<String> assignmentList;
    private static Employee User = null;



    /// Setters & Getters


    public static Employee getUser() {
        return User;
    }

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

    public void refreshEmployeeBox(){

        employeeBox.removeAllItems();

        Employee[] employees = Employee.getEmployees();
        employeeBox.addItem(null);
        for(int i = 0 ; i < Employee.getNumOfEmployees() ; i++)
            employeeBox.addItem(employees[i].getName());

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

<<<<<<< Updated upstream
        //setting employBox
        Employee[] employees = Employee.getEmployees();
        for(int i = 0 ; i < Employee.getNumOfEmployees() ; i++)
            employeeBox.addItem(employees[i].getName());
=======
        refreshEmployeeBox();//setting employBox

>>>>>>> Stashed changes




        // Add_Employee Button Listener
        Add_Assignment_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (User == null)
                    new popUP("Must log-in first");
                else{if(!User.isManager()){
                    new popUP("Only mannager can enter!");}
                else{new AddEmployeeForm(app.this);}}

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

                if(User == null)
                    new popUP("Must log-in first!");
                else
                    new addTaskForm(app.this);

            }
        });

        //Delete Task Listener
        deleteTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (User == null)
                    new popUP("Must log-in first");
                else{if(!User.isManager()){
                    new popUP("Only manager can enter!");}
                else{new deleteTaskForm(app.this);}}

            }
        });
<<<<<<< Updated upstream
=======

        //employeeBox Listener
        employeeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (User == null) {
                    new popUP("Must log-in first");
                    employeeBox.setSelectedIndex(0);
                }else {
                    if (!User.isManager()) {
                        new popUP("Only manager can access");
                        employeeBox.setSelectedIndex(0);
                    }
                }

                setAssignmentList();


            }
        });


        assignmentList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if(!assignmentList.isSelectionEmpty()){

                if( !e.getValueIsAdjusting()){
                    String selectedAssignmentName = assignmentList.getSelectedValue();
                    String first2Chars = selectedAssignmentName.substring(0, 2);//substring containing first 2 characters
                    String numOnly = first2Chars.replaceAll("[^0-9]", "");
                    int numAssignment = Integer.parseInt(numOnly);
                    Assignment selectedAssignment = Assignment.searchAssignment(numAssignment);

                    new AssignmentDetails(app.this, selectedAssignment);


                }}


            }
        });
>>>>>>> Stashed changes
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
