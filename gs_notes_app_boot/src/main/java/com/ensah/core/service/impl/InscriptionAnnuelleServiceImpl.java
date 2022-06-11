package com.ensah.core.service.impl;

import com.ensah.core.dao.IInscriptionAnnuelleDao;
import com.ensah.core.service.IInscriptionAnnuelleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class InscriptionAnnuelleServiceImpl implements IInscriptionAnnuelleService {
    @Autowired
    IInscriptionAnnuelleDao iInscriptionAnnuelleDao;
    @Override
    public void update(int rang, String Validation, Long idInsc) {
        iInscriptionAnnuelleDao.update(rang,Validation,idInsc);
    }
}
