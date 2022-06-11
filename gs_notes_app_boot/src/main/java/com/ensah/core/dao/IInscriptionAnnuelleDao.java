package com.ensah.core.dao;

import com.ensah.core.bo.InscriptionAnnuelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IInscriptionAnnuelleDao extends JpaRepository<InscriptionAnnuelle,Long>,GenericJpa<InscriptionAnnuelle,Long> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE InscriptionAnnuelle insc SET insc.rang=:rang,insc.validation=:validation WHERE insc.idInscription=:idInscr")
    public void update(@Param("rang") int rang, @Param("validation") String validation, @Param("idInscr") Long idInscr);
}
