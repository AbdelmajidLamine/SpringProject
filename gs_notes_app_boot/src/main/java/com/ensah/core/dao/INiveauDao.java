package com.ensah.core.dao;

import com.ensah.core.bo.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INiveauDao extends JpaRepository<Niveau,Long>,GenericJpa<Niveau,Long> {
}
