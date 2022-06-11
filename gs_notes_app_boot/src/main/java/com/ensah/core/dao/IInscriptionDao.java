package com.ensah.core.dao;

import com.ensah.core.bo.InscriptionAnnuelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IInscriptionDao extends JpaRepository<InscriptionAnnuelle,Long>,GenericJpa<InscriptionAnnuelle,Long> {
    @Query("SELECT inscr.annee FROM  InscriptionAnnuelle inscr where inscr.niveau.idNiveau=:idNiveau")
    public int getAnnee(@Param("idNiveau") Long idNiveau);
}
