package dao.inter;

import entity.EmploymentHistory;

import java.util.List;

public interface EmploymentHistoryDaoInter {

    List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId);
}
