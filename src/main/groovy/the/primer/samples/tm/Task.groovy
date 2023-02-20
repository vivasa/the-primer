package the.primer.samples.tm

enum Status {
  TODO, IN_PROGRESS, DONE
}

enum Category {
  WORK, HOME
}

class Task {
  String name
  Status status
  Category category

  Task(String name, Status status, Category category) {
    this.name = name
    this.status = status
    this.category = category
  }
}
