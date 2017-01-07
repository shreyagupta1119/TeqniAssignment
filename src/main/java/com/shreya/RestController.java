package com.shreya;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * Created by shreya on 3/1/17.
 */
@Controller
public class RestController {

    public static final String APPLICATION_JSON = "application/json";
    public static final String APPLICATION_XML = "application/xml";
    public static final String APPLICATION_HTML = "text/html";

    @Autowired MySqlRepository mysqlRepository;
    @Autowired SqlService sqlService;


    @RequestMapping(value = "/", method = RequestMethod.GET, produces=APPLICATION_HTML)
    public @ResponseBody String status() {
        return "Default Status Message";
    }


    @RequestMapping(value="/students",method=RequestMethod.GET)
    public @ResponseBody List<Student> showAllStudents(){
      List<Student> students=sqlService.showAllStudents();
      return students;
    }

    @RequestMapping(value="/student/{name}",method = RequestMethod.GET,headers="Accept=Application/json")
    public @ResponseBody Student getStudentByName(@PathVariable String name){
        return sqlService.getStudentByName(name);
    }

    @RequestMapping(value="/student",method = RequestMethod.POST,headers="Accept=Application/json")
    public @ResponseBody String addStudent(@RequestBody Student student){
        return sqlService.addStudent(student);

    }

    @RequestMapping(value="/student/{name}",method = RequestMethod.DELETE)
    public void deleteStudentByName(@PathVariable String name){
        sqlService.deleteStudentByName(name);
    }

    @RequestMapping(value="/student",method=RequestMethod.PUT)
    public @ResponseBody String updateStudent(@RequestBody Student student){
        return sqlService.updateStudent(student);
    }

    @RequestMapping(value="/teams",method = RequestMethod.GET)
    public @ResponseBody List<Team> showAllTeams(){
        return sqlService.showAllTeams();
    }

    @RequestMapping(value="/team/{name}",method = RequestMethod.GET,headers="Accept=Application/json")
    public @ResponseBody Team getTeamByName(@PathVariable String name){
        return sqlService.getTeamByName(name);
    }

    @RequestMapping(value="/team",method = RequestMethod.POST,headers="Accept=Application/json")
    public @ResponseBody String addTeam(@RequestBody Team team){
        return sqlService.addTeam(team);

    }

    @RequestMapping(value="/team/{name}",method = RequestMethod.DELETE)
    public void  deleteTeamByName(@PathVariable String name){
        sqlService.deleteTeamByName(name);
    }

    @RequestMapping(value="/team",method=RequestMethod.PUT)
    public @ResponseBody String updateTeam(@RequestBody Team team){
        return sqlService.updateTeam(team);
    }

    @RequestMapping(value="/studentteam",method = RequestMethod.GET)
    public @ResponseBody List<StudentTeam> getAllTeamStudents(){
        return sqlService.getAllTeamStudents();
    }

    @RequestMapping(value="/studentteam/{name}",method = RequestMethod.GET)
    public @ResponseBody StudentTeam getStudentTeamByTeamName(@PathVariable String name){
        return sqlService.getStudentTeamByTeamName(name);
    }

    @RequestMapping(value="/studentteam",method=RequestMethod.POST)
    public @ResponseBody String addStudentTeam(@RequestBody StudentTeam st){
        if(!StringUtils.isEmpty(st.getStudents())&& !StringUtils.isEmpty(st.getTeamName())) {
            return sqlService.addStudentTeam(st);
        }
        else
            return "invalid input";

    }

    @RequestMapping(value="/studentteam",method = RequestMethod.DELETE)
    public @ResponseBody String deleteStudentTeamByStudent(@RequestBody StudentTeam st){
        return sqlService.deleteStudentTeamByStudent(st);
    }
}
