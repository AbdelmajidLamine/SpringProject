package com.ensah.core.dao;

import com.ensah.core.bo.InscriptionMatiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IInscriptionElementDao extends JpaRepository<InscriptionMatiere,Long>,GenericJpa<InscriptionMatiere,Long> {
    @Query("SELECT inscription FROM InscriptionMatiere inscription where inscription.matiere.idMatiere = :idMatiere")
    public InscriptionMatiere getInscriptionMatiereBy(@Param("idMatiere") Long idMatiere);
    @Modifying(clearAutomatically = true)
    @Query("UPDATE InscriptionMatiere inscription SET inscription.noteFinale=:note WHERE inscription.matiere.idMatiere=:idMatiere and inscription.inscriptionAnnuelle.idInscription=:idInscr")
    public void update(@Param("note") Double note,@Param("idMatiere") Long idMatiere,@Param("idInscr") Long idInscr);

}
