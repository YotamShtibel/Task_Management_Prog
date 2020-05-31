package system;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Assignment {
    public final DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static int NumOfAssignments = 0;
    private int assignmentNum;
    private LocalTime madeTime;
    private LocalDate madeDate;
    private Employee assignedTo;
    private int priority = 1; // 1 - low priority ... 3 - High priority
    private String title;
    private String context;
    private static Assignment[] assignments = setAssignmentsInFunc();


    //getters & setters
    public static Assignment[] getAssignments() {
        return assignments;
    }

    public int getAssignmentNum() {
        return assignmentNum;
    }

    public static int getNumOfAssignments() {
        return NumOfAssignments;
    }

    public int getPriority() {
        return priority;
    }

    public Employee getAssignedTo() {
        return assignedTo;
    }

    public LocalDate getMadeDate() {
        return madeDate;
    }

    public LocalTime getMadeTime() {
        return madeTime;
    }

    public String getTitle() {
        return title;
    }

    public String getContext() {
        return context;
    }

    //////////////////

    public Assignment(){}

    public Assignment(int assignmentNum, LocalTime madeTime, LocalDate madeDate, Employee assignTo,int priority, String title, String context) {
        this.assignmentNum = assignmentNum;
        this.priority = priority;
        this.assignedTo = assignTo;
        this.madeTime = madeTime;
        this.madeDate = madeDate;
        this.context = context;
        this.title = title;
    }

    public void printAssignment() {

        System.out.println("Assignment number: " + this.getAssignmentNum());
        System.out.println("Date: " + this.getMadeDate());
        System.out.println("Time: " + this.getMadeTime().format(myFormatObj));
        System.out.println("Assigned to: " + this.getAssignedTo().getName());
        System.out.println("Priority is: " + this.getPriority());
        System.out.println("Title:" + this.getTitle());
        System.out.println("Context: " + this.getContext());

    }

    private static Assignment[] setAssignmentsInFunc() { // return array of all the employees

        Scanner x;
        Assignment[] assignments = new Assignment[100];
        try {
            x = new Scanner(new File("Assignments_Data.txt"));
            while (x.hasNext()) {

                int tempAssignmentNum = Integer.parseInt(x.next());
                LocalTime tempTime = LocalTime.parse(x.next());
                LocalDate tempDate = LocalDate.parse(x.next());
                String tempEmployeeName = x.next();
                int tempPriority = Integer.parseInt(x.next());
                String tempTitle = x.next();
                String tempContext = x.next();

                Assignment tempAssignment = new Assignment(tempAssignmentNum, tempTime, tempDate,Employee.searchEmployee(tempEmployeeName),tempPriority,tempTitle,tempContext);
                assignments[NumOfAssignments] = tempAssignment;
                NumOfAssignments++;
            }
            x.close();
        } catch (Exception e) {
            System.out.println("error occurred in Assignment/setAssignmentsInFunc");
        }

        return assignments;
    } // collecting initial data from file

    public void addAssignmentToFile() {

        try{

            BufferedWriter writer = new BufferedWriter(new FileWriter("Assignments_Data.txt",true));
            if(NumOfAssignments != 0)
                writer.newLine();
            writer.append(Integer.toString(this.getAssignmentNum()));
            writer.newLine();
            writer.append(this.getMadeTime().toString());
            writer.newLine();
            writer.append(this.getMadeDate().toString());
            writer.newLine();
            writer.append(this.getAssignedTo().getName());
            writer.newLine();
            writer.append(Integer.toString(this.getPriority()));
            writer.newLine();
            writer.append(this.getTitle());
            writer.newLine();
            writer.append(this.getContext());
            writer.close();

        }catch (IOException e){
            System.out.println("error occurred in Assignment/addAssignmentToFile");

        }
    }

    public void addAssignment(){
        this.addAssignmentToFile();
        assignments[NumOfAssignments] = this;
        NumOfAssignments++;
    }

    public static void removeAssignment(int numOfAssignment){

        for(int i = 0 ;i < NumOfAssignments ; i++){
            if (assignments[i].getAssignmentNum() == numOfAssignment) {
                for (int j = i , k = 0; j <NumOfAssignments; j++ , k++)
                    assignments[i + k] = assignments[j+1];
                NumOfAssignments--;
            }
        }
        refreshData();
    }

    public static void refreshData(){

        try{

            BufferedWriter writer = new BufferedWriter(new FileWriter("Assignments_Data.txt"));
            for (int i = 0 ; i < NumOfAssignments ; i++) {

                writer.append(Integer.toString(assignments[i].getAssignmentNum()));
                writer.newLine();
                writer.append(assignments[i].getMadeTime().toString());
                writer.newLine();
                writer.append(assignments[i].getMadeDate().toString());
                writer.newLine();
                writer.append(assignments[i].getAssignedTo().getName());
                writer.newLine();
                writer.append(Integer.toString(assignments[i].getPriority()));
                writer.newLine();
                writer.append(assignments[i].getTitle());
                writer.newLine();
                writer.append(assignments[i].getContext());

            }
            writer.close();
        }catch (IOException e){
            System.out.println("error occurred in Assignment/refreshData");

        }
    } // moving data from array to file

    public static Assignment searchAssignment(int assignmentNum){

        for(int i = 0 ;i < NumOfAssignments ; i++){
            if (assignments[i].getAssignmentNum() == assignmentNum)
                return assignments[i];
        }
        return  null;

    }

    public static Assignment searchAssignment(String assignmentTitle){

        for(int i = 0 ;i < NumOfAssignments ; i++){
            if (assignments[i].getTitle().equalsIgnoreCase(assignmentTitle))
                return assignments[i];
        }
        return  null;

    }

    public int getNextAssignmentNum(){
        int highestNum=1;
        for(int i = 0 ; i < NumOfAssignments ; i++) {
            if (assignments[i].getAssignmentNum() > highestNum)
                highestNum = assignments[i].getNextAssignmentNum() + 1;
        }
        return highestNum;
        }



}
