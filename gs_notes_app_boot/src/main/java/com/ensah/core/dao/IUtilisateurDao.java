package com.ensah.core.dao;

import com.ensah.core.bo.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUtilisateurDao extends JpaRepository<Utilisateur, Long>, GenericJpa<Utilisateur, Long> {
    @Query("SELECT user FROM Utilisateur user where user.nom = :lastName and user.prenom =:firstName")
    List<Utilisateur> findByName(@Param("lastName")String name, @Param("firstName") String Prenom);
}
