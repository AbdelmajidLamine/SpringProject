package com.ensah.core.service;

import com.ensah.core.bo.Module;

import java.util.List;

public interface IModuleService {

    public List<Module> getModulesByNiveau(Long idNiveau);
}
