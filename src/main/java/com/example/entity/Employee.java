package com.example.entity;

import java.time.LocalDate;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    
    public String name;
    
    public LocalDate joinedDate;
    
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public LocalDate getJoinedDate() {
//        return joinedDate;
//    }
//
//    public void setJoinedDate(LocalDate joinedDate) {
//        this.joinedDate = joinedDate;
//    }

    public Employee() {}

    public Employee(String name, LocalDate joinedDate) {
        super();
        this.name = name;
        this.joinedDate = joinedDate;
    }
    
}
