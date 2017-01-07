package com.shreya;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by shreya on 3/1/17.
 */
@Data
@Table(name="student")
public class Student {
    @Id
    private String studentName;
    private int age;

    public Student(){}

    public Student (String name,int age){
        this.studentName=name;
        this.age=age;
    }

}
