package execution;

public class HeadExecutor extends Executor{
    public void execute(String value) {
        String head = jdbcDao.getHeadOfDepartment(value);
        if(head == null){
            System.out.println("Error: department doesn't exist");
            return;
        }
        System.out.println("Head of " + value + " department is " + head + ".");
    }
}
