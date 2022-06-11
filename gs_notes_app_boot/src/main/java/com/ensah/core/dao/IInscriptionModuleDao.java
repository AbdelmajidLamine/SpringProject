package com.ensah.core.dao;

import com.ensah.core.bo.InscriptionModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IInscriptionModuleDao extends JpaRepository<InscriptionModule,Long>,GenericJpa<InscriptionModule,Long> {
    @Modifying(clearAutomatically = true)
    @Query("UPDATE InscriptionModule insc SET insc.noteFinale=:note,insc.validation=:validation WHERE insc.module.idModule=:idModule and insc.inscriptionAnnuelle.idInscription=:idInscr")
    public void update(@Param("note") Double note,@Param("validation") String validation,@Param("idModule") Long idModule,@Param("idInscr") Long idInscr);


}
