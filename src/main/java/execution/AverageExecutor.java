package execution;

public class AverageExecutor extends Executor{
    public void execute(String value) {
        System.out.println("The average salary of " + value + " is " + jdbcDao.getAverageSalary(value));
    }
}
