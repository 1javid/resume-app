package main;

import dao.impl.CountryDaoImpl;
import dao.impl.SkillsDaoImpl;
import dao.impl.UserDaoImpl;
import dao.impl.UserSkillsDaoImpl;
import dao.inter.SkillsDaoInter;
import dao.inter.UserDaoInter;
import dao.inter.UserSkillsDaoInter;
import entity.Country;
import entity.Skills;
import entity.User;
import dao.inter.CountryDaoInter;
import entity.UserSkills;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainUser extends JFrame {

    private JPanel mainPanel;
    private JTextField txtName;
    private JTextField txtSurname;
    private JLabel lblName;
    private JLabel lblSurname;
    private JPanel pnlUserInfo;
    private JTabbedPane tpUserInfo;
    private JPanel pnlProfile;
    private JPanel pnlSkills;
    private JPanel pnlDetails;
    private JTextArea txtAreaProfile;
    private JButton btnSave;
    private JTextField txtAddress;
    private JLabel lblAddress;
    private JLabel lblPhone;
    private JTextField txtPhone;
    private JTextField txtEmail;
    private JLabel lblEmail;
    private JLabel lblBirthdate;
    private JLabel lblBirthplace;
    private JLabel lblNationality;
    private JTextField txtBirthdate;
    private JComboBox<Country> cbCountry;
    private JComboBox<Country> cbNationality;
    private JLabel lblSkills;
    private JTextField txtSkillName;
    private JLabel lblPower;
    private JSlider slPower;
    private JComboBox<Skills> cbSkills;
    private JTable  tblSkills;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnSkillsSave;

    private UserDaoInter userDao = new UserDaoImpl();
    User loggedInUser;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private CountryDaoInter countryDaoInter = new CountryDaoImpl();
    private SkillsDaoInter skillsDaoInter = new SkillsDaoImpl();
    private UserSkillsDaoInter userSkillsDaoInter = new UserSkillsDaoImpl();
    private List<UserSkills> list;
    private int flag = 1;

    public MainUser(String title) {
        super(title);

        loggedInUser = userDao.getById(1);
        fillUserComponents();

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name = txtName.getText();
                String surname = txtSurname.getText();
                String address = txtAddress.getText();
                String phone = txtPhone.getText();
                String email = txtEmail.getText();
                String dob = txtBirthdate.getText();

                long l;
                try {
                    Date dtUtil = simpleDateFormat.parse(dob);
                    l = dtUtil.getTime();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                java.sql.Date bd = new java.sql.Date(l);

                loggedInUser.setName(name);
                loggedInUser.setSurname(surname);
                loggedInUser.setAddress(address);
                loggedInUser.setPhone(phone);
                loggedInUser.setEmail(email);
                loggedInUser.setBirthdate(bd);
                loggedInUser.setProfileDesc(txtAreaProfile.getText());


                userDao.updateUser(loggedInUser);
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String skillName = txtSkillName.getText();
                Skills skills;
                if (skillName != null && !skillName.trim().isEmpty()) {
                    skills = new Skills(0, skillName);
                    skillsDaoInter.insertSkills(skills);
                } else {
                    skills = (Skills) cbSkills.getSelectedItem();
                }
                int power = slPower.getValue();

                UserSkills userSkill = new UserSkills(null, loggedInUser, skills, power);
                userSkillsDaoInter.insertUserSkills(userSkill);
                fillWindow(0);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tblSkills.getSelectedRow();
                UserSkills userSkill = list.get(index);
                userSkillsDaoInter.deleteUserSkills(userSkill.getId());
                cbSkills.removeItemAt(index);
                flag = 2;
                fillWindow(index);
            }
        });
        btnSkillsSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserSkills selectedUserSkills = list.get(tblSkills.getSelectedRow());

                String skillName = txtSkillName.getText();
                Skills skills;
                if (skillName != null && !skillName.trim().isEmpty()) {
                    skills = new Skills(0, skillName);
                    skillsDaoInter.insertSkills(skills);
                } else {
                    skills = (Skills) cbSkills.getSelectedItem();
                }
                int power = slPower.getValue();

                selectedUserSkills.setPower(power);
                selectedUserSkills.setSkills(skills);

                userSkillsDaoInter.updateUserSkills(selectedUserSkills);
                fillWindow(0);
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }

    private void fillUserComponents() {
        txtName.setText(loggedInUser.getName());
        txtSurname.setText(loggedInUser.getSurname());
        txtAreaProfile.setText(loggedInUser.getProfileDesc());
        txtEmail.setText(loggedInUser.getEmail());
        txtPhone.setText(loggedInUser.getPhone());
        txtAddress.setText(loggedInUser.getAddress());

        String date = simpleDateFormat.format(loggedInUser.getBirthdate());
        txtBirthdate.setText(date);

        countryDaoInter = new CountryDaoImpl();
        List<Country> countryList = countryDaoInter.getAllCountries();
        for(Country country : countryList) {
            cbCountry.addItem(country);
            cbNationality.addItem(country);
        }

        fillWindow(0);
    }

    private void fillWindow(int index) {
        cbSkills.removeAllItems();
        List<Skills> skills = skillsDaoInter.getAllSkills();
        if(flag == 2) {
            skills.remove(index);
        }
        for (Skills item : skills) {
            cbSkills.addItem(item);
        }
        flag = 1;
        fillTable();
    }

    private void fillTable() {
        User user = loggedInUser;
        int id = user.getId();
        list = userSkillsDaoInter.getAllSkillsByUserId(id);

        Vector<Vector> rows = new Vector<>();
        for (UserSkills l : list) {
            Vector<Object> row = new Vector<>();
            row.add(l.getSkills());
            row.add(l.getPower());
            rows.add(row);
        }

        Vector<String> columns = new Vector<>();
        columns.add("Skill");
        columns.add("Power");

        DefaultTableModel model = new DefaultTableModel(rows, columns);

        tblSkills.setModel(model);
    }

    public static void main(String[] args) {
        JFrame mainFrame = new MainUser("Resume Desktop App");
        mainFrame.setVisible(true);
    }
}
