package com.shreya;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shreya on 3/1/17.
 */
@Repository
public class MySqlRepository {

    @Autowired
    JdbcTemplate template;

    public List<Student> showAllStudents(){
        String sql="select * from student";
        List<Student> students=template.query(sql,new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student student= new Student();
                student.setStudentName(rs.getString("studentName"));
                student.setAge(rs.getInt("age"));
                return student;
            }
        });
        return students;
    }

    public Student getStudentByName(String name){
        String sql="select * from student where studentName=?";
        return template.query(sql, new Object[]{name}, new ResultSetExtractor<Student>(){
            @Override
            public Student extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {
                    Student student= new Student();
                    student.setStudentName(rs.getString("studentName"));
                    student.setAge(rs.getInt("age"));
                    return student;
                }
                return null;
            }

        });
    }

    public String addStudent(Student student){
        if(!StringUtils.isEmpty(student.getStudentName())&& student.getAge()>0){
            String sql="insert into student "+ "values(?,?)";
            template.update(sql,student.getStudentName(),student.getAge());
            return "student is added";
        }
        else
            return "student is not added";
    }

    public void deleteStudentByName(String name) {
        String sql="delete from student where studentName=?";
        template.update(sql, name);
    }

    public String updateStudent(Student student){
        String sql="update student set age=? where studentName=?";
        template.update(sql,student.getAge(),student.getStudentName());
        if(sql!=null)
            return "updated student";
        return "student not found";
    }

    public List<Team> showAllTeams(){
        String sql="select * from team";
        List<Team> teams=template.query(sql,new RowMapper<Team>() {
            @Override
            public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
                Team team= new Team();
                team.setTeamName(rs.getString("teamName"));
                team.setLocation(rs.getString("location"));
                return team;
            }
        });
        return teams;
    }

    public Team getTeamByName(String name){
        String sql="select * from team where teamName=?";
        return template.query(sql, new Object[]{name}, new ResultSetExtractor<Team>(){
            @Override
            public Team extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {
                    Team team= new Team();
                    team.setTeamName(rs.getString("teamName"));
                    team.setLocation(rs.getString("location"));
                    return team;
                }
                return null;
            }

        });
    }

    public String addTeam(Team team){
        if(!StringUtils.isEmpty(team.getTeamName())&& (!StringUtils.isEmpty(team.getLocation()))){
            String sql="insert into team "+ "values(?,?)";
            template.update(sql,team.getTeamName(),team.getLocation());
            return "team is added";
        }
            return "team is not added";
    }

    public void deleteTeamByName(String name) {
        String sql = "delete from team where teamName=?";
        template.update(sql,name);
    }

    public String updateTeam(Team team){
        String sql="update team set location=? "+ "where teamName=?";
        template.update(sql,team.getLocation(),team.getTeamName());
        if(sql!=null)
            return "updated team";
        return "team not found";
    }

    public List<StudentTeam> getAllTeamStudents(){
        String sql="select teamName, GROUP_CONCAT(studentName) as students from studentteam GROUP BY teamName";
        return template.query(sql, new ResultSetExtractor<List<StudentTeam>>(){
            @Override
            public List<StudentTeam> extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                List<StudentTeam> studentTeams = new ArrayList<StudentTeam>();
                while (rs.next()) {
                    StudentTeam studentTeam= new StudentTeam();
                    studentTeam.setTeamName(rs.getString("teamName"));
                    studentTeam.setStudents(rs.getString("students"));
                    studentTeams.add(studentTeam);
                }
                return studentTeams;
            }

        });
    }

    public StudentTeam getStudentTeamByTeamName(String name){
        String sql="select teamName,GROUP_CONCAT(studentName) as students from studentteam where teamName=? GROUP BY teamName";
        return template.query(sql,new Object[]{name}, new ResultSetExtractor<StudentTeam>() {
            @Override
            public StudentTeam extractData(ResultSet rs) throws SQLException, DataAccessException {

                if(rs.next()){
                    StudentTeam studentTeam=new StudentTeam();
                    studentTeam.setTeamName(rs.getString("teamName"));
                    studentTeam.setStudents(rs.getString("students"));
                    return studentTeam;
                }
                return null;
            }
        });
    }

    public void addStudentTeam(StudentTeam st){
        String sql="insert into studentteam values(?,?)";
        template.update(sql,st.getStudents(),st.getTeamName());
    }

    public void deleteStudentTeamByStudent(StudentTeam st){
        String sql="delete from studentteam where teamName =?";
        template.update(sql,st.getTeamName());
    }
}