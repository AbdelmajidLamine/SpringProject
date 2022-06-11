package com.ensah.core.service;

import com.ensah.core.bo.InscriptionModule;

import java.util.List;

public interface IInscriptionModuleService {
    public void update(double note,String validation,Long idModule,Long idInscr);
    public List<InscriptionModule> getAll();
}
