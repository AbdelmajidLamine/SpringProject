package com.ensah.core.service.impl;

import com.ensah.core.bo.Module;
import com.ensah.core.dao.IModuleDao;
import com.ensah.core.service.IModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ModuleServiceImpl implements IModuleService {

    @Autowired
    IModuleDao iModuleDao;
    @Override
    public List<Module> getModulesByNiveau(Long idNiveau) {
        return iModuleDao.getModuleBy(idNiveau);
    }
}
