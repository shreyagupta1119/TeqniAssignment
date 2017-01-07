package com.shreya;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shreya on 3/1/17.
 */
@Data
@Table(name="team")
public class Team{
    @Id
    private String teamName;
    private String location;

    public Team(){}
    public Team(String teamName,String location){
        this.teamName=teamName;
        this.location=location;
    }

}
