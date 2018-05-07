package execution;

import dao.JdbcDao;

public abstract class Executor {
    JdbcDao jdbcDao = new JdbcDao();

    abstract void execute(String value);
}
