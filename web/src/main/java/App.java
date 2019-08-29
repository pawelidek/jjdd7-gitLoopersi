import com.infoshareacademy.gitLoopersi.entity.Employee;
import com.infoshareacademy.gitLoopersi.entity.Holiday;
import com.infoshareacademy.gitLoopersi.entity.Team;
import com.infoshareacademy.gitLoopersi.entity.Vacation;
import java.time.LocalDate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {

  public static void main(String[] args) {
    createEmployee();
    createHoliday();
    createVacation();
  }

  private static void createEmployee() {
    SessionFactory factory = new Configuration()
        .configure("hibernate.cfg.xml")
        .addAnnotatedClass(Employee.class)
        .addAnnotatedClass(Team.class)
        .buildSessionFactory();

    Session session = factory.getCurrentSession();
    System.out.println("Creating new student object");
    Team team =new Team("alfa");
    Employee employee1 = new Employee("Marek", "asdasd",team);
    Employee employee2 = new Employee("Adam", "asdasd",team);
    session.beginTransaction();
    session.save(employee1);
    session.save(employee2);
    session.getTransaction().commit();
    factory.close();
  }

  private static void createHoliday() {
    SessionFactory factory = new Configuration()
        .configure("hibernate.cfg.xml")
        .addAnnotatedClass(Holiday.class)
        .buildSessionFactory();

    Session session = factory.getCurrentSession();
    System.out.println("Creating new student object");
    Holiday holiday = new Holiday();
    session.beginTransaction();
    session.save(holiday);
    session.getTransaction().commit();
    factory.close();
  }

  private static void createVacation() {
    SessionFactory factory = new Configuration()
        .configure("hibernate.cfg.xml")
        .addAnnotatedClass(Vacation.class)
        .addAnnotatedClass(Employee.class)
        .addAnnotatedClass(Team.class)
        .buildSessionFactory();

    Session session = factory.getCurrentSession();
    System.out.println("Creating new student object");
    Employee employee = new Employee("Andrzej","Pawian",new Team("deba"));
    Vacation vacation1 = new Vacation(employee,LocalDate.now(),LocalDate.now(),3);
    Vacation vacation2 = new Vacation(employee,LocalDate.now(),LocalDate.now(),3);
    Vacation vacation3 = new Vacation(employee,LocalDate.now(),LocalDate.now(),3);
    session.beginTransaction();
    session.save(vacation1);
    session.save(vacation2);
    session.save(vacation3);
    session.getTransaction().commit();
    factory.close();
  }
}
