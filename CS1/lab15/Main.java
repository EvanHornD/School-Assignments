package lab15;

import java.util.Scanner;

public class Main {

    // Print Name and Model of students array
    public static void printNamesAndIds(Student[] students){
        for (int i = 0; i < students.length; i++) {
            System.out.println("\nStudent number "+students[i].getId()+"'s name");
            System.out.println(students[i].getName());
        }
    }

    // prints all of the students information
    public static void printStudents(Student[] students){
        for (int i = 0; i < students.length; i++) {
            students[i].printStudentInfo();
        }
    }

    // gets all of the students IDs
    public static int[] getStudentIds(Student[] students){
        int[] ids = new int[students.length];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = students[i].getId();
        }
        return ids;
    }

    // returns the users next line of input
    public static String getInput(Scanner input){
        return(input.nextLine());
    }

    // parses a String for an integer returns a -1 if its not an integer
    public static int parseIntInput(String input){
        try {
            return(Integer.parseInt(input));
        } catch (Exception e) {
            return(-1);
        }
    }

    // parses a String for an double returns a -1 if its not an double
    public static double parseDoubleInput(String input){
        try {
            return(Double.parseDouble(input));
        } catch (Exception e) {
            return(-1.);
        }
    }

    // creates a new array of students adding all of the old students to the array and then initiallizes a new student as the last value of the array. returns the new array of students
    public static Student[] addstudentToArray(Student[] students,Student newstudent){
        Student[] newArray=new Student[students.length+1];
        for (int i = 0; i < students.length; i++) {
            newArray[i]=students[i];
        }
        newArray[newArray.length-1]=new Student(newstudent);
        return newArray;
    }

    // creates a new array of students adding all of the old students to the array and replaces an existing student by initializing a new one in its place. returns the new array of students
    public static Student[] replacestudentInArray(Student[] students,int studentIndex,Student newStudent){
        Student[] newArray=new Student[students.length];
        for (int i = 0; i < students.length; i++) {
            if(i==studentIndex){
                newArray[i]=new Student(newStudent);
            }else{
                newArray[i]=students[i];
            }
        }
        return newArray;
    }

    //creates a new array of students adding all of the old students to the array minus the one at the index provided. returns the new array of students
    public static Student[] removeStudentInArray(Student[] students,int studentIndex){
        Student[] newArray=new Student[students.length-1];
        for (int i = 0; i < students.length; i++) {
            if(i<studentIndex){
                newArray[i]=students[i];
            }
            if(i>studentIndex){
                newArray[i-1]=students[i];
            }
        }
        return newArray;
    }    

    // allows the user to pick the ID they want the student to have, it has to be 6 digits and it nust not already exist
    public static void pickId(Student[] students,Student student,Scanner input){
        String state = "pickingId";
        int[] studentIds = getStudentIds(students);
        while(state=="pickingId"){
            System.out.println("Enter the students ID?\n (it is a 6 digit integer)");
            String id = getInput(input);
            int idInt = parseIntInput(id);
            boolean idAlreadyExists = false;
            for (int i = 0; i < studentIds.length; i++) {
                if (idInt==studentIds[i]) {
                    idAlreadyExists=true;
                }
            }
            if(id.length()==6&&!idAlreadyExists){
                student.setId(idInt);
                state = "done";
            }else{
                if(idAlreadyExists){
                    System.out.println("you cannot repeat IDs");
                }else{
                    System.out.println("you made an error in your input, try again.");
                }
            }
        }
    }
    
    // allows the user to pick the name they want the student to have
    public static void pickName(Student student,Scanner input){
        String state = "pickingName";
        while(state=="pickingName"){
            System.out.println("Enter the students name?\n (it is a string)");
            String name = getInput(input);
            if(name!=""){
                student.setName(name);
                state = "done";
            }else{
                System.out.println("you made an error in your input, try again.");
            }
        }
    }

    // allows the user to pick the grade they want the student to have
    public static void pickGrade(Student student,Scanner input){
        String state = "pickingGrade";
        while(state=="pickingGrade"){
            System.out.println("Enter the students grade?\n (it is a double)");
            double grade = parseDoubleInput(getInput(input));
            if(grade>=0){
                student.setGrade(grade);
                state = "done";
            }else{
                System.out.println("you made an error in your input, try again.");
            }
        }
    }

    // creates a new student object and then the user sets all of the values of the new student. returns the student with all of the values initialized
    public static Student createNewStudent(Scanner input,Student[] students){
        Student newstudent = new Student();
        pickId(students,newstudent, input);
        pickName(newstudent, input);
        pickGrade(newstudent, input);
        return(newstudent);
    }

    // asks the user to enter the ID of the student they want to change in some way. returns the index of the student in the array of students
    public static int getStudentIndexFromId(Scanner input, Student[] currentStudents,String action){
        String state = "getingStudentId";
        int[] studentIds = getStudentIds(currentStudents);
        while(state.equals("getingStudentId")){
            System.out.println("please enter the ID of the student you want to "+action);
            printNamesAndIds(currentStudents);
            int id = parseIntInput(getInput(input));
            for (int i = 0; i < studentIds.length; i++) {
                if (id==studentIds[i]) {
                    return i;
                }
            }
            System.out.println("There was an error in your input");
        }
        return -1;
    }

    // lets the 
    public static void editStudentInformation(Scanner input, Student[] students, Student student){
        String state = "editing information";
        while(state.equals("editing information")){
            student.printStudentInfo();
            System.out.println("\nWhat do you want to edit:\n1. ID\n2. Name\n3. Grade\n4. Finish editing");
            switch (parseIntInput(getInput(input))) {
                case 1:
                    pickId(students, student, input);
                break;
                    
                case 2:
                    pickName(student, input);
                break;

                case 3:
                    pickGrade(student, input);
                break;

                case 4:
                    state="done";
                break;

                default:
                    System.out.println("There was and error in your input, try again.\n");
                break;
            } 
        }
    }


    
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);

        // construct the initial students
        Student student1 = new Student();
        student1.setId(100001);
        student1.setName("Ford");
        student1.setGrade(2315.32);

        Student student2 = new Student(100002,"MicroSoft");
        student2.setGrade(222233.99);
        String state = "starting";
        Student[] studentArray = {student1,student2};
        int studentIndex=0;

        // prints only the Names and models of the students, as asked by the instructor
        printNamesAndIds(studentArray);

        while(!state.equals("exit")){
            System.out.println("\nStudent Management System:\n1. Add a student to the system\n2. Display all students \n3. Search a student by ID \n4. Edit an existing students information \n5. Delete a Student by ID \n6. Exit the program");
            switch (parseIntInput(getInput(userInput))) {
                case 1:
                    studentArray = addstudentToArray(studentArray,createNewStudent(userInput,studentArray));
                break;

                case 2:
                    printStudents(studentArray);
                break;

                case 3:
                    studentIndex = getStudentIndexFromId(userInput, studentArray,"search for");
                    studentArray[studentIndex].printStudentInfo();
                break;

                case 4:
                    studentIndex = getStudentIndexFromId(userInput, studentArray,"edit");
                    editStudentInformation(userInput, studentArray, studentArray[studentIndex]);
                break;

                case 5:
                    studentIndex = getStudentIndexFromId(userInput, studentArray,"delete");
                    studentArray = removeStudentInArray(studentArray, studentIndex);
                break;

                case 6:
                    state="exit";
                break;

                default:
                    System.out.println("there was and error in your input, try again\n");
                break;
            } 
        }
    }
}
