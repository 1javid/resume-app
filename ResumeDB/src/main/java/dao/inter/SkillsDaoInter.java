package dao.inter;

import entity.Country;
import entity.Skills;

import java.util.List;

public interface SkillsDaoInter {

    List<Skills> getAllSkills();

    Skills getById(int id);

    boolean insertSkills(Skills s);

    boolean updateSkills(Skills s);

    boolean deleteSkills(int id);
}
