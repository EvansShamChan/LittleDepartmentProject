package execution;

public class CountExecutor extends Executor{
    public void execute(String value) {
        System.out.println(jdbcDao.getNumberOfEmployee(value));
    }
}
