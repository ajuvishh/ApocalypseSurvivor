package com.pwc.survivorcamp.dao;

import com.pwc.survivorcamp.model.Survivors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SurvivorsRepo extends JpaRepository<Survivors,Integer> {

    public List<Survivors> findByInfected(Boolean infectedFlag);

    public Optional<Survivors> findById(Integer id);
}
