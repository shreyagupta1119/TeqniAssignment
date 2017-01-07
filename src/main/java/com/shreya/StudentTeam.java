package com.shreya;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Table;

/**
 * Created by shreya on 7/1/17.
 */
@Data
@Table(name="studentteam")
public class StudentTeam {

    private String students;
    private String teamName;

    public StudentTeam(){}

}
