package com.ensah.core.service.impl;

import com.ensah.core.bo.Niveau;
import com.ensah.core.dao.INiveauDao;
import com.ensah.core.service.INiveauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NiveauServiceImpl implements INiveauService {

    @Autowired
    INiveauDao iNiveauDao;



    @Override
    public List<Niveau> getAllNiveau() {
        return iNiveauDao.findAll();
    }
}
