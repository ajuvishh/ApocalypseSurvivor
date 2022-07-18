package com.pwc.survivorcamp.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Survivors {
    @Id
    int id;

    int age;

    @NotNull
    String name;

    @NotNull
    String gender;

    boolean infected;

    double latitude;

    double longitude;


}
