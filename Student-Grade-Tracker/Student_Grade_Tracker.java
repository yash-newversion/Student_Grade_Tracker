package Student_Grade_Tracker;
import java.util.ArrayList;
import java.util.Scanner;

//in this code, we will genrating the report of student just by the rollno 
class Student{
    String name;
    int Java;
    int DAA;
    int TAFL;
    int UHV;
    int OS;
    int DBMS;

    Student(String name, int java, int DAA, int TAFL, int UHV, int OS, int DBMS){
        this.name = name;
        this.SetMarkJava(java);
        this.SetMarkDaa(DAA);
        this.SetMarkTAFL(TAFL);
        this.SetMarkUHV(UHV);
        this.SetMarkOS(OS);
        this.SetMarkDBMS(DBMS);
    }

    private void SetMarkJava(int mark){
        this.Java = mark;
    }

    private void SetMarkDaa(int mark){
        this.DAA = mark;
    }

    private void SetMarkTAFL(int mark){
        this.TAFL = mark;
    }

    private void SetMarkUHV(int mark){
        this.UHV = mark;
    }

    private void SetMarkOS(int mark){
        this.OS = mark;
    }

    private void SetMarkDBMS(int mark){
        this.DBMS = mark;
    }

    // now getter function

    public int getJava(){
        return this.Java;
    }

    public int getDAA(){
        return this.DAA;
    }

    public int getTAFL(){
        return this.TAFL;
    }

    public int getUHV(){
        return this.UHV;
    }

    public int getOS(){
        return this.OS;
    }

    public int getDBMS(){
        return this.DBMS;
    }
}

class Student_Grade_Tracker{

    public static void main(String[] args){
        Scanner sc  = new Scanner(System.in);
        ArrayList<ArrayList<Student>> record = new ArrayList<>();

        while(true){
            System.out.println("\n==== Student Grade Tracker === ");
            System.out.println("1. Add Student ");
            System.out.println("2. Student report");
            System.out.println("3. Class report");
            System.out.println("4. Exit");
            System.out.println("Choose an option: ");
            int choice = sc.nextInt();
            boolean flag = false;

            switch(choice){
                case 1:
                    sc.nextLine();
                    System.out.println("Enter the name of Student");
                    String name = sc.nextLine();
                    ArrayList<Student> student = new ArrayList<>();
                    
                    // start taking the marks 
                    System.out.println("Enter java marks:");
                    int java = sc.nextInt();

                    System.out.println("Enter DAA marks:");
                    int DAA = sc.nextInt();

                    System.out.println("Enter TAFL marks:");
                    int TAFL = sc.nextInt();

                    System.out.println("Enter UHV marks:");
                    int UHV = sc.nextInt();

                    System.out.println("Enter OS marks:");
                    int OS = sc.nextInt();

                    System.out.println("Enter DBMS marks:");
                    int DBMS = sc.nextInt();

                    Student st = new Student(name, java, DAA, TAFL, UHV, OS, DBMS);
                    student.add(st);
                    record.add(student);
                    System.out.println("Student added successfully!");
                    break;

                case 2:
                    System.out.println("Enter the rollno: ");
                    int rollno = sc.nextInt();
                    if(record.isEmpty()){
                        System.out.println("No Student data available: ");
                    }else{
                        displaySummary(record, rollno);
                    }
                    break;

                case 3:
                    if(record.isEmpty()){
                        System.out.println("No Student data available: ");
                    }else{
                        classreport(record);
                    }
                    break;

                case 4:
                    flag = true;
                    System.out.println("Existing Database.....");
                    break;

                default:
                    System.out.println("Invalid option! Try again: ");
                    break;
            }

            if(flag){
                break;
            }
        }
        sc.close();
    }


    public static void displaySummary(ArrayList<ArrayList<Student>> record, int rollno){
        int roll_no = rollno;
        Scanner sc = new Scanner(System.in);

        while(roll_no <= 0 || roll_no > record.size()){
            System.out.print("rollno is not valid: ");
            roll_no = sc.nextInt();
        }

            ArrayList<Student> st = record.get(roll_no - 1);
            Student children = st.get(0);
            System.out.println("Result: " + children.name);
            System.out.println( );
        
            System.out.println("Java :  Marks = " + children.getJava()  + " | Status = " + passfailStatus(children.getJava()));
            System.out.println("DAA  :  Marks = " + children.getDAA()   + " | Status = " + passfailStatus(children.getDAA()));
            System.out.println("TAFL :  Marks = " + children.getTAFL()  + " | Status = " + passfailStatus(children.getTAFL()));
            System.out.println("UHV  :  Marks = " + children.getUHV()   + " | Status = " + passfailStatus(children.getUHV()));
            System.out.println("OS   :  Marks = " + children.getOS()    + " | Status = " + passfailStatus(children.getOS()));
            System.out.println("DBMS :  Marks = " + children.getDBMS()  + " | Status = " + passfailStatus(children.getDBMS()));
            System.out.println();

            int result = (children.getJava() + children.getDAA() + children.getTAFL() + children.getUHV() + children.getOS() + children.getDBMS()) / 6;

            System.out.println("Percentage: " + result + " % " + "Status: "+ passfailStatus(result));
            System.out.println();
            System.out.println("Class report: ");
            System.out.println( );
            
            classreport(record);
    }

    public static void classreport(ArrayList<ArrayList<Student>> record){
        int size = record.size();
        int total = 0;
        int highestpercentage = Integer.MIN_VALUE;
        StringBuilder highestScorer = new StringBuilder("");
        StringBuilder LowestScorer  = new StringBuilder("");
        int lowest_percenatge = Integer.MAX_VALUE;

        // here you get the average of percentage of class
        for(int i = 0; i < size; i++){
            ArrayList<Student> current = record.get(i);
            Student st = current.get(0);
            int curr = (st.getJava() + st.getUHV() + st.getDAA() + st.getDBMS() + st.getOS() + st.getTAFL()) / 6;
            total += curr;
            // logic to store get the name of student who score highest in class

            if(curr >= highestpercentage){
                highestpercentage = curr;
                highestScorer.replace(0,highestScorer.length(), st.name);
            }

            // logic to store & get the name of student who score lowest in class            
            if(curr < lowest_percenatge){
                lowest_percenatge = curr;
                LowestScorer.replace(0,LowestScorer.length(), st.name);

            }
            
        }
        System.out.println("Average result of class: " + (total/size));
        System.out.println("------------------------------------------");
        System.out.println(highestScorer + " is Score Highest in class: " + highestpercentage + "%");
        System.out.println("------------------------------------------");
        System.out.println(LowestScorer + " is Score Lowest in class: " + lowest_percenatge + "%");

    }


    private static String passfailStatus(int marks){
        String status;
        if(marks >= 33){
            status = "pass";
        }else{
            status = "fail";
        }
        return status;
    }
}