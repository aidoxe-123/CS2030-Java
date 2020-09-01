import java.util.Scanner;
import java.util.function.Consumer;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //input data
        int n = sc.nextInt();
        String useless = sc.nextLine();
        Roster myRoster = new Roster("unknown period");
        for (int i = 0; i < n; i++) {
            String str = sc.nextLine();
            String[] splited = str.split("\\s+");
            Assessment assessment = new Assessment(splited[2], splited[3]);
            /*if (myRoster.get(splited[0]) == null) {
                Module module = new Module(splited[1]).put(assessment);
                Student student = new Student(splited[0]).put(module);
                myRoster = myRoster.put(student);
            } else if (myRoster.get(splited[0]).get(splited[1]) == null) {
                Module module = new Module(splited[1]).put(assessment);
                myRoster.get(splited[0]).put(module);
            } else {
                myRoster.get(splited[0]).get(splited[1]).put(assessment);
            }*/
            Consumer<Module> f1 = module -> module.put(assessment);
            Runnable f2 = () -> myRoster
                               .get(splited[0])
                               .map(x -> x.put(new Module(splited[1])
                                                  .put(assessment)));
            Consumer<Student> f3 = student -> student.get(splited[1]).ifPresentOrElse(f1, f2);
            Runnable f4 = () -> myRoster
                               .put(new Student(splited[0])
                                       .put(new Module(splited[1])
                                               .put(assessment))); 
            myRoster.get(splited[0]).ifPresentOrElse(f3, f4);
        }
        //process queries
        while(sc.hasNext()) {
            try {
                String str = sc.nextLine();
                String[] splited = str.split("\\s+");
                String result = myRoster.getGrade(splited[0], splited[1], splited[2]);
                System.out.println(result);
            } catch (NoSuchRecordException e) {
                System.out.println("NoSuchRecordException: " + e.getMessage());
            }
        }
    }
}         
