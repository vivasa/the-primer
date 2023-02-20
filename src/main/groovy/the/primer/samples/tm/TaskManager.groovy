package the.primer.samples.tm

class TaskManager {
  // define fields for storing tasks
  List<Task> previousTasks = []
  List<Task> currentTasks = []
  private AbstractTaskFactory taskFactory

  // define a private constructor to prevent external instantiation
  private TaskManager() {}

  // define a static field to hold the singleton instance
  private static TaskManager instance

  // define a public static method for accessing the instance
  public static TaskManager getInstance() {
    if (instance == null) {
      instance = new TaskManager()
      instance.taskFactory = new TaskFactory()
    }
    return instance
  }

  // define methods for creating, modifying, and deleting tasks, as well as for
  // marking tasks as complete and viewing lists of tasks
  // define a method for creating a task
  void createTask(String name, String status, Category category) {
    currentTasks << taskFactory.createTask(name, status, category)
  }

  // define a method for modifying a task
  void modifyTask(Task task, String name, String status, Category category) {
    task.name = name
    task.status = status
    task.category = category
  }

  // define a method for deleting a task
  void deleteTask(Task task) {
    currentTasks.remove(task)
  }

  // define a method for marking a task as complete
  void completeTask(Task task) {
    currentTasks.remove(task)
    previousTasks << task
  }

  // define a method for viewing a list of current tasks
  void viewCurrentTasks() {
    currentTasks.each { println "$it.name ($it.status, $it.category)" }
  }

  // define a method for viewing a list of previous tasks
  void viewPreviousTasks() {
    previousTasks.each { println "$it.name ($it.status, $it.category)" }
  }
}