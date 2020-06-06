package system;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;

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
    private JList<String> assignmentList;
    private JList<String> generalAssignmentsList;
    private JList<String> chooseEmployeeAssignmentsList;
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

        if (User != null)
            assignmentList.setModel(setElementsToLIstInFunc
                    (Assignment.getAssignments(), User.getName())); //setting AssignmentList with current assignments to current employee

        generalAssignmentsList.setModel(setElementsToLIstInFunc
                (Assignment.getAssignments(), "no employee")); //setting AssignmentList with no employee-assigned Assignments

        chooseEmployeeAssignmentsList.setModel(setElementsToLIstInFunc
                (Assignment.getAssignments(), (String) employeeBox.getSelectedItem())); //setting AssignmentList with no employee-assigned Assignments


    }

    public static void setUser(Employee user) {
        User = user;

    }

    //////////////////////////////////////////////////

    //Functions

    private DefaultListModel<String> setElementsToLIstInFunc(Assignment[] assignments, String userName) {

        DefaultListModel<String> l1 = new DefaultListModel<>();
        for (int i = 0; i < Assignment.getNumOfAssignments(); i++) {
            if (assignments[i].getAssignedTo().getName() == userName) {
                if (assignments[i].getPriority() == 1)
                    l1.addElement(Integer.toString(assignments[i].getAssignmentNum()) + " | " + assignments[i].getTitle()
                            + " | " + "Low Priority");
                else {
                    if (assignments[i].getPriority() == 2)
                        l1.addElement(Integer.toString(assignments[i].getAssignmentNum()) + " | " + assignments[i].getTitle()
                                + " | " + "Medium Priority");
                    else
                        l1.addElement(Integer.toString(assignments[i].getAssignmentNum()) + " | " + assignments[i].getTitle()
                                + " | " + "High Priority");
                }
            }
        }
        return l1;
    }

    private Assignment getListenerData(JList<String> list) {

        String selectedAssignmentName = list.getSelectedValue();
        String first2Chars = selectedAssignmentName.substring(0, 2);//substring containing first 2 characters
        String numOnly = first2Chars.replaceAll("[^0-9]", "");
        int numAssignment = Integer.parseInt(numOnly);
        Assignment selectedAssignment = Assignment.searchAssignment(numAssignment);
        return selectedAssignment;

    }

    public void refreshEmployeeBox(){

        employeeBox.removeAllItems();

        Employee[] employees = Employee.getEmployees();
        employeeBox.addItem(null);
        for(int i = 0 ; i < Employee.getNumOfEmployees() ; i++)
            employeeBox.addItem(employees[i].getName());

    }

    //////////////////////////////////////////////////////



    public app(){

        // Frame Properties
        this.add(MainPanel);
        this.setSize(1280,720);
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
        refreshEmployeeBox();//setting employBox

        //setting Lists
        setAssignmentList();


        //setting Lists Helping Functions
        generalAssignmentsList.setCellRenderer(new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof String && !((String)value).isEmpty()) {
                    Assignment assignment;
                    int assignmentNum;
                    for(int i = 0 ; i < generalAssignmentsList.getComponents().length && !((String)value).isEmpty() ; i++){
                        String first2Chars = ((String)value).substring(0, 2);//substring containing first 2 characters
                        String numOnly = first2Chars.replaceAll("[^0-9]", "");
                        int numAssignment = Integer.parseInt(numOnly);
                        assignment = Assignment.searchAssignment(numAssignment);
                        if (assignment.getStatus() == 3) {
                            setBackground(Color.GREEN);
                        } else { if(assignment.getStatus() == 2)
                            setBackground(Color.cyan);
                        else{
                            setBackground(Color.white);}
                } }}
                return c;
            }

        });

        assignmentList.setCellRenderer(new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof String && !((String)value).isEmpty()) {
                    Assignment assignment;
                    int assignmentNum;
                    for(int i = 0 ; i < assignmentList.getComponents().length && !((String)value).isEmpty() ; i++){
                        String first2Chars = ((String)value).substring(0, 2);//substring containing first 2 characters
                        String numOnly = first2Chars.replaceAll("[^0-9]", "");
                        int numAssignment = Integer.parseInt(numOnly);
                        assignment = Assignment.searchAssignment(numAssignment);
                        if (assignment.getStatus() == 3) {
                            setBackground(Color.GREEN);
                        } else { if(assignment.getStatus() == 2)
                            setBackground(Color.cyan);
                        else{
                            setBackground(Color.white);}
                        } }}
                return c;
            }

        });

        chooseEmployeeAssignmentsList.setCellRenderer(new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof String && !((String)value).isEmpty()) {
                    Assignment assignment;
                    int assignmentNum;
                    for(int i = 0 ; i < chooseEmployeeAssignmentsList.getComponents().length && !((String)value).isEmpty() ; i++){
                        String first2Chars = ((String)value).substring(0, 2);//substring containing first 2 characters
                        String numOnly = first2Chars.replaceAll("[^0-9]", "");
                        int numAssignment = Integer.parseInt(numOnly);
                        assignment = Assignment.searchAssignment(numAssignment);
                        if (assignment.getStatus() == 3) {
                            setBackground(Color.GREEN);
                        } else { if(assignment.getStatus() == 2)
                            setBackground(Color.cyan);
                        else{
                            setBackground(Color.white);}
                        } }}
                return c;
            }

        });

        //////////////////////////////////////////////////////


        // Add_Employee Button Listener
        Add_Assignment_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (User == null)
                    new popUP("Must log-in first");
                else{if(!User.isManager()){
                    new popUP("Only manager can enter!");}
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
                    new popUP("Only manager can enter!");}
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


        //MayAssignments List Listener
        assignmentList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if(!assignmentList.isSelectionEmpty()){

                    if( !e.getValueIsAdjusting()){

                        new AssignmentDetails(app.this, getListenerData(assignmentList));

                    }
                }


            }
        });

        //GeneralAssignments List Listener
        generalAssignmentsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if(!generalAssignmentsList.isSelectionEmpty()){

                    if( !e.getValueIsAdjusting()){

                        new AssignmentDetails(app.this, getListenerData(generalAssignmentsList));

                    }
                }


            }
        });

        //ChooseEmployeeAssignments List Listener
        chooseEmployeeAssignmentsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if(!chooseEmployeeAssignmentsList.isSelectionEmpty()){

                    if( !e.getValueIsAdjusting()){

                        new AssignmentDetails(app.this, getListenerData(chooseEmployeeAssignmentsList));


                    }
                }

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
