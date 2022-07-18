package com.pwc.survivorcamp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ReportInfectedKey implements Serializable {
    @ManyToOne
    @JoinColumn(name = "reporter_id_fk", referencedColumnName = "id")
    private Survivors reporterId;


    @ManyToOne
    @JoinColumn(name = "infected_id_fk", referencedColumnName = "id")
    private Survivors infectedId;
}
