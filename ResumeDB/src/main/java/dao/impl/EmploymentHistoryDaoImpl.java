package dao.impl;

import dao.inter.ConnectionAbstract;
import dao.inter.EmploymentHistoryDaoInter;
import entity.EmploymentHistory;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmploymentHistoryDaoImpl extends ConnectionAbstract implements EmploymentHistoryDaoInter {

    private EmploymentHistory getUserEmploymentHistory(ResultSet resultSet) throws Exception {
        int userId = resultSet.getInt("user_id");
        String header = resultSet.getString("header");
        String jobDescription = resultSet.getString("job_description");
        Date beginDate = resultSet.getDate("begin_date");
        Date endDate = resultSet.getDate("end_date");
        return new EmploymentHistory(userId, header, beginDate, endDate, jobDescription, new User(userId));
    }

    @Override
    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId) {
        List<EmploymentHistory> userList = new ArrayList<>();
        try(Connection c = connect()){
            PreparedStatement statement = c.prepareStatement("select * from employment_history where user_id = ?");
            statement.setInt(1, userId);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                EmploymentHistory user = getUserEmploymentHistory(resultSet);
                userList.add(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return userList;
    }
}
