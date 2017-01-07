package com.shreya;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shreya on 3/1/17.
 */
@Service
public class SqlService {

    @Autowired
    MySqlRepository mySqlRepository;

    public List<Student> showAllStudents(){
        return mySqlRepository.showAllStudents();
    }

    public Student getStudentByName(String name){
        return mySqlRepository.getStudentByName(name);
    }

    public String addStudent(Student student){
        return mySqlRepository.addStudent(student);
    }

    public void deleteStudentByName(String name){
         mySqlRepository.deleteStudentByName(name);
    }

    public String updateStudent(Student student){
        return mySqlRepository.updateStudent(student);
    }

    public List<Team> showAllTeams(){
        return mySqlRepository.showAllTeams();
    }

    public Team getTeamByName(String name){
        return mySqlRepository.getTeamByName(name);
    }

    public String addTeam(Team team){
        return mySqlRepository.addTeam(team);
    }

    public void deleteTeamByName(String name){
         mySqlRepository.deleteTeamByName(name);
    }

    public String updateTeam(Team team){
        return mySqlRepository.updateTeam(team);
    }

    public List<StudentTeam> getAllTeamStudents(){
        return mySqlRepository.getAllTeamStudents();
    }

    public StudentTeam getStudentTeamByTeamName(String name){
        return mySqlRepository.getStudentTeamByTeamName(name);
    }

    public String addStudentTeam(StudentTeam st){
        if((getStudentByName(st.getStudents()))!=null && (getTeamByName(st.getTeamName()))!=null && getStudentTeamByTeamName(st.getTeamName())==null) {
            mySqlRepository.addStudentTeam(st);
            return "student is added to team";
        }
        return "invalid student or team value";
    }

    public String deleteStudentTeamByStudent(StudentTeam st){
        if(getStudentTeamByTeamName(st.getTeamName())!=null){
            mySqlRepository.deleteStudentTeamByStudent(st);
            return "student deleted";
        }
        return "student is not found";
    }
}
