package com.example.devedbaseproject.models;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "name")
    private String name;

//    @ManyToMany
//    @JoinTable(name="Employees",
//            joinColumns=@JoinColumn(name="roleId"),
//            inverseJoinColumns=@JoinColumn(name="employeeId"))
//    private List<Employee> employees;

//    @Override
//    public String toString() {
//        return "{" +
//                "Id: " + Id +
//                ", name: '" + name +
//               "'}";
//    }

    @Override
    public String toString() {
        return "" + name + "";

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
