package the.primer.samples.tm

abstract class AbstractTaskFactory {
  abstract Task createTask(String name, String status, Category category)
}

class TaskFactory extends AbstractTaskFactory {
  @Override
  Task createTask(String name, String status, Category category) {
    return new Task(name, status, category)
  }
}
