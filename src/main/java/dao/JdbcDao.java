package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDao {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    public final String HEAD_QUERY =
            "select name, surname from lectors where lector_id " +
                    "in(select id from departments where department = ? and isHead = 1)";
    public final String STATISTIC_QUERY =
            "select count(lector_id) number from lectors l3 where degree = ? and lector_id " +
                    "in(select id from departments where department = ?);";
    public final String AVERAGE_QUERY =
            "select avg(salary) average from lectors where lector_id in(select id from departments where department = ?);";
    public final String COUNT_QUERY =
            "select count(*) number from lectors where lector_id in(select id from departments where department = ?);";
    public final String GLOBAL_QUERY = "select name, surname from lectors";

    public String getHeadOfDepartment(String department){
        String result = null;
        try {
            connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/littlebotscrewproject?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
            preparedStatement = connection.prepareStatement(HEAD_QUERY);
            preparedStatement.setString(1, department);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                result = resultSet.getString("name") + " " + resultSet.getString("surname");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String getStatisticOfDepartment(String value){
        String[] arr = {"assistant", "associate professor", "professor"};
        List<Integer> list = new ArrayList<Integer>();
        try {
            connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/littlebotscrewproject?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
            preparedStatement = connection.prepareStatement(STATISTIC_QUERY);
            for (String s : arr) {
                preparedStatement.setString(1, s);
                preparedStatement.setString(2, value);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    list.add(Integer.valueOf(resultSet.getString("number")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(list.isEmpty()){
            return "No values";
        }
        String result = convertIntoValidResult(list);
        return result;
    }

    public String convertIntoValidResult(List<Integer> list){
        String result = "assistant - " + list.get(0) + "\n" +
                        "associate professor - " + list.get(1) + "\n" +
                        "professor - " + list.get(2) + "\n";
        return result;
    }

    public int getAverageSalary(String value){
        int result = 0;
        try {
            connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/littlebotscrewproject?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
            preparedStatement = connection.prepareStatement(AVERAGE_QUERY);
            preparedStatement.setString(1, value);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                result = resultSet.getInt("average");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public int getNumberOfEmployee(String value){
        int result = 0;
        try {
            connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/littlebotscrewproject?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
            preparedStatement = connection.prepareStatement(COUNT_QUERY);
            preparedStatement.setString(1, value);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                result = resultSet.getInt("number");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public List<String> getSearchLectors(String key){
        List<String> resultList = new ArrayList<String>();
        try {
            connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/littlebotscrewproject?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
            preparedStatement = connection.prepareStatement(GLOBAL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                if(name.contains(key) || surname.contains(key)){
                    resultList.add(name + " " + surname);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }
}
