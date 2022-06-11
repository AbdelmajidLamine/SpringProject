package com.ensah.core.service;

import com.ensah.core.bo.InscriptionMatiere;

import java.util.List;


public interface IInscriptionElementService {

    public List<InscriptionMatiere> getAllInscriptionElement();
    public InscriptionMatiere getInscriptionMatiereBy(Long idMatiere);
    public void Update(double note,Long idMatiere,Long idInscription);
}
