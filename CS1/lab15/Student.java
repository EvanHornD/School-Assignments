package lab15;

public class Student {

    // Set attributes

    int id;
    String name;
    double grade;

    // Constructors

    public Student() {
    }

    public Student(Student student){
        this.id = student.getId();
        this.name = student.getName();
        this.grade = student.getGrade();
    }

    public Student(int idIn, String nameIn) {
        this.id = idIn;
        this.name = nameIn;
    }

    public Student(int idIn, String nameIn, double gradeIn) {
        this.id = idIn;
        this.name = nameIn;
        this.grade = gradeIn;
    }

    // Getters
    
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }    

    public double getGrade() {
        return grade;
    }

    //Setters

    public void setId(int idIn) {
        this.id = idIn;
    }
    public void setName(String nameIn) {
        this.name = nameIn;
    }
    public void setGrade(double gradeIn) {
        this.grade = gradeIn;
    }

    // Student information

    public void printStudentInfo(){
        System.out.println("------"+this.name+"'s Information------");
        System.out.println("id: "+this.id);
        System.out.println("grade: "+this.grade);
        System.out.println("----------------------------------");
    }
}
