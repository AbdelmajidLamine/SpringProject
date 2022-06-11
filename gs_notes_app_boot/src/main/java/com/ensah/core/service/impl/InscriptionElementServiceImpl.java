package com.ensah.core.service.impl;

import com.ensah.core.bo.InscriptionMatiere;
import com.ensah.core.dao.IInscriptionElementDao;
import com.ensah.core.service.IInscriptionElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class InscriptionElementServiceImpl implements IInscriptionElementService {
    @Autowired
    IInscriptionElementDao iInscriptionElementDao;

    @Override
    public List<InscriptionMatiere> getAllInscriptionElement() {
        return iInscriptionElementDao.findAll();
    }

    @Override
    public InscriptionMatiere getInscriptionMatiereBy(Long idMatiere) {
        return iInscriptionElementDao.getInscriptionMatiereBy(idMatiere);
    }

    @Override
    public void Update(double note, Long idMatiere,Long idInscription) {
        iInscriptionElementDao.update(note,idMatiere,idInscription);
    }
}
