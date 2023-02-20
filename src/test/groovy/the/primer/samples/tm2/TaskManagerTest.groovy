package the.primer.samples.tm2


import spock.lang.Specification

import java.time.LocalDate

class TaskManagerTest extends Specification {
  TaskManager taskManager = new TaskManagerImpl()

  def "test addTask()"() {
    given:
    // Add a few tasks to the task manager
    taskManager.addTask("Write report", LocalDate.parse("2022-01-01"), Priority.HIGH, Category.WORK, NotificationMethod.EMAIL)
    taskManager.addTask("Buy groceries", null, Priority.LOW, Category.PERSONAL, NotificationMethod.SMS)
    taskManager.addTask("Call mom", LocalDate.parse("2022-01-01"), null, Category.PERSONAL, NotificationMethod.EMAIL)

    when:
    List<Task> incompleteTasks = taskManager.viewIncompleteTasks()

    then:
    incompleteTasks.size() == 3
    incompleteTasks[0].note == "Write report"
    incompleteTasks[1].note == "Buy groceries"
    incompleteTasks[2].note == "Call mom"
  }

  def "test markTaskComplete()"() {
    given:
    // Add a few tasks to the task manager
    taskManager.addTask("Write report", LocalDate.parse("2022-01-01"), Priority.HIGH, Category.WORK, NotificationMethod.EMAIL)
    taskManager.addTask("Buy groceries", null, Priority.LOW, Category.PERSONAL, NotificationMethod.SMS)
    taskManager.addTask("Call mom", LocalDate.parse("2022-01-01"), null, Category.PERSONAL, NotificationMethod.EMAIL)

    when:
    // Mark the second task as complete
    taskManager.markTaskComplete(1)

    then:
    // Verify that the task was moved from the list of incomplete tasks to the list of complete tasks
    List<Task> incompleteTasks = taskManager.viewIncompleteTasks()
    incompleteTasks.size() == 2
    incompleteTasks[0].note == "Write report"
    incompleteTasks[1].note == "Call mom"
    List<Task> completeTasks = taskManager.viewCompleteTasks()
    completeTasks.size() == 1
    completeTasks[0].note == "Buy groceries"
  }

  def "test viewIncompleteTasks()"() {
    given:
    // Add a few tasks to the task manager
    taskManager.addTask("Write report", LocalDate.parse("2022-01-01"), Priority.HIGH, Category.WORK, NotificationMethod.EMAIL)
    taskManager.addTask("Buy groceries", null, Priority.LOW, Category.PERSONAL, NotificationMethod.SMS)
    taskManager.addTask("Call mom", LocalDate.parse("2022-01-01"), null, Category.PERSONAL, NotificationMethod.EMAIL)

    when:
    List<Task> incompleteTasks = taskManager.viewIncompleteTasks()

    then:
    incompleteTasks.size() == 3
    incompleteTasks[0].note == "Write report"
    incompleteTasks[1].note == "Buy groceries"
    incompleteTasks[2].note == "Call mom"
  }
}