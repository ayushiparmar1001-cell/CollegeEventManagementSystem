
import java.util.ArrayList;
import java.util.Scanner;

// Abstraction
abstract class CollegeActivity {
    private int id;
    private String name;
    private String date;
    private String time;
    private String venue;
    private String description;

    // Constructor
    public CollegeActivity(int id, String name, String date,
                           String time, String venue,
                           String description) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.description = description;
    }

    // Encapsulation: Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Abstract method
    public abstract void displayDetails();

    public void displayBasicDetails() {
        System.out.println("Event ID: " + id);
        System.out.println("Event Name: " + name);
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);
        System.out.println("Venue: " + venue);
        System.out.println("Description: " + description);
    }
}

// Inheritance
class Event extends CollegeActivity {
    private String category;

    public Event(int id, String name, String date,
                 String time, String venue,
                 String description, String category) {
        super(id, name, date, time, venue, description);
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Method Overriding: Runtime Polymorphism
    @Override
    public void displayDetails() {
        displayBasicDetails();
        System.out.println("Category: " + category);
        System.out.println("----------------------------");
    }
}

// Student class
class Student {
    private int studentId;
    private String name;
    private String department;

    public Student(int studentId, String name, String department) {
        this.studentId = studentId;
        this.name = name;
        this.department = department;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public void displayStudent() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
    }
}

// Registration class
class Registration {
    private Student student;
    private Event event;

    public Registration(Student student, Event event) {
        this.student = student;
        this.event = event;
    }

    public Student getStudent() {
        return student;
    }

    public Event getEvent() {
        return event;
    }

    public void displayRegistration() {
        System.out.println("Student ID: "
                + student.getStudentId());
        System.out.println("Student Name: "
                + student.getName());
        System.out.println("Event ID: "
                + event.getId());
        System.out.println("Event Name: "
                + event.getName());
        System.out.println("----------------------------");
    }
}

// Main class
public class PROJECT {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Event> events = new ArrayList<>();
    static ArrayList<Student> students = new ArrayList<>();
    static ArrayList<Registration> registrations =
            new ArrayList<>();

    // Method Overloading: Compile-time Polymorphism
    static Event findEvent(int id) {
        for (Event e : events) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    static Event findEvent(String name) {
        for (Event e : events) {
            if (e.getName().equalsIgnoreCase(name)) {
                return e;
            }
        }
        return null;
    }

    static Student findStudent(int id) {
        for (Student s : students) {
            if (s.getStudentId() == id) {
                return s;
            }
        }
        return null;
    }

    // Add Event
    static void addEvent() {
        System.out.print("Enter Event ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (findEvent(id) != null) {
            System.out.println("Event ID already exists!");
            return;
        }

        System.out.print("Enter Event Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Date (DD-MM-YYYY): ");
        String date = sc.nextLine();

        System.out.print("Enter Time: ");
        String time = sc.nextLine();

        System.out.print("Enter Venue: ");
        String venue = sc.nextLine();

        System.out.print("Enter Description: ");
        String description = sc.nextLine();

        System.out.print("Enter Category: ");
        String category = sc.nextLine();

        events.add(new Event(id, name, date, time,
                venue, description, category));

        System.out.println("Event added successfully!");
    }

    // View Events
    static void viewEvents() {
        if (events.isEmpty()) {
            System.out.println("No events available.");
            return;
        }

        // Polymorphism
        for (CollegeActivity activity : events) {
            activity.displayDetails();
        }
    }

    // Update Event
    static void updateEvent() {
        System.out.print("Enter Event ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        Event e = findEvent(id);

        if (e == null) {
            System.out.println("Event not found!");
            return;
        }

        System.out.print("Enter New Name: ");
        e.setName(sc.nextLine());

        System.out.print("Enter New Date: ");
        e.setDate(sc.nextLine());

        System.out.print("Enter New Time: ");
        e.setTime(sc.nextLine());

        System.out.print("Enter New Venue: ");
        e.setVenue(sc.nextLine());

        System.out.print("Enter New Description: ");
        e.setDescription(sc.nextLine());

        System.out.print("Enter New Category: ");
        e.setCategory(sc.nextLine());

        System.out.println("Event updated successfully!");
    }

    // Delete Event
    static void deleteEvent() {
        System.out.print("Enter Event ID to delete: ");
        int id = sc.nextInt();

        Event e = findEvent(id);

        if (e == null) {
            System.out.println("Event not found!");
            return;
        }

        registrations.removeIf(
                r -> r.getEvent().getId() == id);

        events.remove(e);

        System.out.println("Event deleted successfully!");
    }

    // Register Student
    static void registerStudent() {
        System.out.print("Enter Student ID: ");
        int studentId = sc.nextInt();
        sc.nextLine();

        Student student = findStudent(studentId);

        if (student == null) {
            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Department: ");
            String department = sc.nextLine();

            student = new Student(studentId, name, department);
            students.add(student);
        }

        System.out.print("Enter Event ID: ");
        int eventId = sc.nextInt();

        Event event = findEvent(eventId);

        if (event == null) {
            System.out.println("Event not found!");
            return;
        }

        for (Registration r : registrations) {
            if (r.getStudent().getStudentId() == studentId
                    && r.getEvent().getId() == eventId) {
                System.out.println(
                        "Already registered for this event!");
                return;
            }
        }

        registrations.add(new Registration(student, event));

        System.out.println("Registration successful!");
    }

    // View Participants
    static void viewParticipants() {
        System.out.print("Enter Event ID: ");
        int id = sc.nextInt();

        Event event = findEvent(id);

        if (event == null) {
            System.out.println("Event not found!");
            return;
        }

        boolean found = false;

        System.out.println("Participants for: " + event.getName());

        for (Registration r : registrations) {
            if (r.getEvent().getId() == id) {
                r.getStudent().displayStudent();
                System.out.println("----------------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No participants registered.");
        }
    }

    // Search Event
    static void searchEvent() {
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        Event event = null;

        if (choice == 1) {
            System.out.print("Enter Event ID: ");
            event = findEvent(sc.nextInt());
        } else if (choice == 2) {
            System.out.print("Enter Event Name: ");
            event = findEvent(sc.nextLine());
        } else {
            System.out.println("Invalid choice!");
            return;
        }

        if (event != null) {
            event.displayDetails();
        } else {
            System.out.println("Event not found!");
        }
    }

    // Cancel Registration
    static void cancelRegistration() {
        System.out.print("Enter Student ID: ");
        int studentId = sc.nextInt();

        System.out.print("Enter Event ID: ");
        int eventId = sc.nextInt();

        boolean removed = registrations.removeIf(
                r -> r.getStudent().getStudentId() == studentId
                  && r.getEvent().getId() == eventId);

        if (removed) {
            System.out.println("Registration cancelled!");
        } else {
            System.out.println("Registration not found!");
        }
    }

    // View all registrations
    static void viewRegistrations() {
        if (registrations.isEmpty()) {
            System.out.println("No registrations available.");
            return;
        }

        for (Registration r : registrations) {
            r.displayRegistration();
        }
    }

    // Main Menu
    public static void main(String[] args) {

        int choice = 0;

        do {
            try {
                System.out.println("\n================================");
                System.out.println(" COLLEGE EVENT MANAGEMENT SYSTEM");
                System.out.println("================================");
                System.out.println("1. Add Event");
                System.out.println("2. View Events");
                System.out.println("3. Update Event");
                System.out.println("4. Delete Event");
                System.out.println("5. Register Student");
                System.out.println("6. View Participants");
                System.out.println("7. Search Event");
                System.out.println("8. Cancel Registration");
                System.out.println("9. View All Registrations");
                System.out.println("10. Exit");
                System.out.print("Enter your choice: ");

                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        addEvent();
                        break;

                    case 2:
                        viewEvents();
                        break;

                    case 3:
                        updateEvent();
                        break;

                    case 4:
                        deleteEvent();
                        break;

                    case 5:
                        registerStudent();
                        break;

                    case 6:
                        viewParticipants();
                        break;

                    case 7:
                        searchEvent();
                        break;

                    case 8:
                        cancelRegistration();
                        break;

                    case 9:
                        viewRegistrations();
                        break;

                    case 10:
                        System.out.println("Thank you for using the system!");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (java.util.InputMismatchException ex) {
                System.out.println(
                        "Invalid input! Please enter a number.");
                sc.nextLine();
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }

        } while (choice != 10);

        sc.close();
    }
}
