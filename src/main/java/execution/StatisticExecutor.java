package execution;

public class StatisticExecutor extends Executor{
    public void execute(String value) {
        System.out.println(jdbcDao.getStatisticOfDepartment(value));
    }
}
