import java.util.*;
import java.util.stream.*;
class Employee {
    int id=1;
    String name;
    int age;
    String gender;
    String department;
    int yearOfJoining;
    double salary;

    public String getGender() {
        return this.gender;
    }
    public String getDepartment() {
        return this.department;
    }
    public int getAge() {
        return this.age;
    }
    public double getSalary() {
        return this.salary;
    }
    public String getName() {
        return this.name;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }

    Employee(){

    }
    Employee(int id, String name, int age, String gender, String dept, int yoj, double salary) {
  this.id=id;
  this.name=name;
  this.age=age;
  this.gender=gender;
  this.department=dept;
  this.yearOfJoining=yoj;
  this.salary=salary;
    id=10;
    }
}
public class TestEmployee {
    public static void main(String[] args) {
        List<Employee> employeeList = new ArrayList<Employee>();

        employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
        employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
        employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
        employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
        employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
        employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
        incrementSalaryforLessThanA(employeeList,1.5,15000);
        youngestMaleEmployeeFromDept(employeeList,"HR","Female");

    }

    //write a function to retrieve Total salary and average salary of all employees
   public void findAverageSalary(List<Employee> employeeList){
       DoubleSummaryStatistics salarySummary=employeeList.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
       salarySummary.getAverage();
       salarySummary.getSum();
   }


    //write a function to retrieve the youngest male employee from the "Security And Transport" department

    public  static  void  youngestMaleEmployeeFromDept(List<Employee> employeeList,String dept,String gender){
        Optional<Employee> employ=  employeeList.stream().filter(emp->emp.getDepartment().equalsIgnoreCase(dept) && emp.getGender().equalsIgnoreCase(gender) ).min(Comparator.comparingInt(Employee::getAge));
        System.out.println(employ.get().name);

    }
    //Evaluate the output
    private static void incrementSalaryforLessThanA(List<Employee> employeeList, double incFactor, double lessThan) {
        final List<Employee> incrementedList = employeeList.stream()
                .filter(emp -> {
                    System.out.println("fitering employee " + emp.getName());
                    return emp.getSalary() < lessThan;
                })
                .map(emp -> {
                    System.out.println("Mapping employee " + emp.getName());
                    double newSal = emp.getSalary() * incFactor;
                    emp.setSalary(newSal);
                    return emp;
                })
                .collect(Collectors.toList());
    }

}
