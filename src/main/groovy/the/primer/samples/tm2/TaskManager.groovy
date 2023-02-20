package the.primer.samples.tm2

import java.time.LocalDate

enum Priority {
  HIGH, MEDIUM, LOW
}

enum Category {
  WORK, PERSONAL
}

enum NotificationMethod {
  EMAIL, SMS
}

class Task {
  String note
  LocalDate dueDate
  Priority priority = Priority.MEDIUM
  Category category = Category.WORK
  NotificationMethod notificationMethod = NotificationMethod.EMAIL
  boolean isComplete = false

  Task(String note, LocalDate dueDate, Priority priority, Category category, NotificationMethod notificationMethod) {
    this.note = note
    this.dueDate = dueDate
    this.priority = priority
    this.category = category
    this.notificationMethod = notificationMethod
  }
}

interface TaskManager {
  void addTask(String note, LocalDate dueDate, Priority priority, Category category, NotificationMethod notificationMethod)

  void markTaskComplete(int taskIndex)

  List<Task> viewIncompleteTasks()

  List<Task> viewCompleteTasks()

  void run()
}

class TaskManagerImpl implements TaskManager {
  List<Task> incompleteTasks = []
  List<Task> completeTasks = []
  Scanner scanner = new Scanner(System.in)

  void addTask(String note, LocalDate dueDate, Priority priority, Category category, NotificationMethod notificationMethod) {
    Task task = new Task(note, dueDate, priority, category, notificationMethod)
    incompleteTasks.add(task)
  }

  void markTaskComplete(int taskIndex) {
    Task task = incompleteTasks.remove(taskIndex)
    task.isComplete = true
    completeTasks.add(task)
  }

  List<Task> viewIncompleteTasks() {
    return incompleteTasks
  }

  List<Task> viewCompleteTasks() {
    return completeTasks
  }

  void run() {
    while (true) {
      println("Task Manager")
      println("------------")
      println("1. View incomplete tasks")
      println("2. View complete tasks")
      println("3. Add task")
      println("4. Mark task complete")
      println("5. Quit")
      print("Enter your choice: ")

      int choice = scanner.nextInt()

      switch (choice) {
        case 1:
          List<Task> incompleteTasks = viewIncompleteTasks()
          println("Incomplete tasks: ")
          incompleteTasks.eachWithIndex { task, index ->
            println("${index + 1}. ${task.note}")
          }
          break
        case 2:
          List<Task> completeTasks = viewCompleteTasks()
          println("Complete tasks: ")
          completeTasks.eachWithIndex { task, index ->
            println("${index + 1}. ${task.note}")
          }
          break
        case 3:
          print("Enter task note: ")
          String note = scanner.next()
          print("Enter task due date (yyyy-mm-dd): ")
          LocalDate dueDate = LocalDate.parse(scanner.next())
          print("Enter task priority (HIGH, MEDIUM, LOW): ")
          Priority priority = Priority.valueOf(scanner.next())
          print("Enter task category (WORK, PERSONAL): ")
          Category category = Category.valueOf(scanner.next())
          print("Enter notification method (EMAIL, SMS): ")
          NotificationMethod notificationMethod = NotificationMethod.valueOf(scanner.next())
          addTask(note, dueDate, priority, category, notificationMethod)
          break
        case 4:
          print("Enter task index: ")
          int taskIndex = scanner.nextInt()
          markTaskComplete(taskIndex)
          break
        case 5:
          println("Exiting Task Manager")
          return
        default:
          println("Invalid choice")
          break
      }
    }
  }

  static void main(String[] args) {
    TaskManager taskManager = new TaskManagerImpl()
    taskManager.run()
  }
}