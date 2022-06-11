package com.ensah.core.service.impl;

import com.ensah.core.bo.InscriptionModule;
import com.ensah.core.dao.IInscriptionModuleDao;
import com.ensah.core.service.IInscriptionModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InscriptionModuleServiceImpl implements IInscriptionModuleService {
    @Autowired
    IInscriptionModuleDao iInscriptionModuleDao;
    @Override
    public void update(double note,String validation,Long idModule,Long idInscr) {
        iInscriptionModuleDao.update(note,validation,idModule,idInscr);
    }

    @Override
    public List<InscriptionModule> getAll() {
        return iInscriptionModuleDao.findAll();
    }

}
