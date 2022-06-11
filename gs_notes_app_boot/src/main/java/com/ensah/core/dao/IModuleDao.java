package com.ensah.core.dao;

import com.ensah.core.bo.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IModuleDao extends JpaRepository<Module,Long> ,GenericJpa<Module,Long>{

    @Query("SELECT module FROM Module module where module.niveau.idNiveau = :idNiveau")
    public List<Module> getModuleBy(@Param("idNiveau") Long idNiveau);
}
