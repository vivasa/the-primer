package the.primer.samples.tm2

class TaskManagerClient {
  public static void main(String[] args) {
    TaskManager manager = TaskManager.getInstance()
    Scanner scanner = new Scanner(System.in)

    while (true) {
      println("1. Create a task")
      println("2. Modify a task")
      println("3. Delete a task")
      println("4. Complete a task")
      println("5. View current tasks")
      println("6. View previous tasks")
      println("7. Exit")

      int option = scanner.nextInt()

      if (option == 1) {
        println("Enter task name:")
        String name = scanner.next()
        println("Enter task status:")
        String status = scanner.next()
        println("Enter task category:")
        String category = scanner.next()
        manager.createTask(name, status, category)
        println("Task created!")
      } else if (option == 2) {
        println("Enter task name to modify:")
        String name = scanner.next()
        Task task = manager.findTask(name)
        if (task != null) {
          println("Enter new task name:")
          String newName = scanner.next()
          println("Enter new task status:")
          String newStatus = scanner.next()
          println("Enter new task category:")
          String newCategory = scanner.next()
          manager.modifyTask(task, newName, newStatus, newCategory)
          println("Task modified!")
        } else {
          println("Task not found!")
        }
      } else if (option == 3) {
        println("Enter task name to delete:")
        String name = scanner.next()
        Task task = manager.findTask(name)
        if (task != null) {
          manager.deleteTask(task)
          println("Task deleted!")
        } else {
          println("Task not found!")
        }
      } else if (option == 4) {
        println("Enter task name to mark as complete:")
        String name = scanner.next()
        Task task = manager.findTask(name)
        if (task != null) {
          manager.completeTask(task)
          println("Task marked as complete!")
        } else {
          println("Task not found!")
        }
      } else if (option == 5) {
        manager.viewCurrentTasks()
      } else if (option == 6) {
        manager.viewPreviousTasks()
      } else if (option == 7) {
        println("Exiting...")
        break
      } else {
        println("Invalid option. Please try again.")
      }
    }
  }
}