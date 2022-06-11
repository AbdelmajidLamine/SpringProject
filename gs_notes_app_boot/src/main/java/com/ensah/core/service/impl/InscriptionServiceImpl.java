package com.ensah.core.service.impl;

import com.ensah.core.dao.IInscriptionDao;
import com.ensah.core.service.IInscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InscriptionServiceImpl implements IInscriptionService {
    @Autowired
    IInscriptionDao iInscriptionDao;
    @Override
    public int getAnnee(Long idNiveau) {
        return iInscriptionDao.getAnnee(idNiveau);
    }
}
