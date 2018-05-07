package execution;

import java.util.List;

public class GlobalExecutor extends Executor{
    public void execute(String value) {
        List<String> searchLectors = jdbcDao.getSearchLectors(value);
        for (String searchLector : searchLectors) {
            System.out.println(searchLector);
        }
    }
}
