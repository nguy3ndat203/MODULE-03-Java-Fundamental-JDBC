package Bai07;
public class Developer extends Employee {
  private String programmingLanguage;

  public Developer() {
    super();
  }

  public Developer(String name, String id, double salary, String programmingLanguage) {
    super(name, id, salary);
    this.programmingLanguage = programmingLanguage;
  }

  @Override
  public double getSalary() {
    return super.getSalary();
  }

  @Override
  public String toString() {
    return "Developer [id=" + id + ", name=" + name + ", baseSalary=" + salary + ", programmingLanguage=" + programmingLanguage + "]";
  }
}
