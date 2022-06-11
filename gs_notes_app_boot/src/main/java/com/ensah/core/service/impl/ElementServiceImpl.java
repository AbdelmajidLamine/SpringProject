package com.ensah.core.service.impl;

import com.ensah.core.bo.Element;
import com.ensah.core.dao.IElementDao;
import com.ensah.core.service.IElementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ElementServiceImpl implements IElementsService {
    @Autowired
    IElementDao iElementDao;
    @Override
    public List<Element> getAllElements() {

        return iElementDao.findAll();
    }

    @Override
    public List<Element> getElementsByModule(Long idModule) {
        return iElementDao.getElementBy(idModule);
    }
}
