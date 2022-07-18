package com.pwc.survivorcamp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportInfected  {

    @EmbeddedId
    private ReportInfectedKey reportInfectedKey;

}
