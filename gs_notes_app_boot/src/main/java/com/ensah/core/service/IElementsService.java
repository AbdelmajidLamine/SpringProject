package com.ensah.core.service;

import com.ensah.core.bo.Element;

import java.util.List;

public interface IElementsService {
       public List<Element> getAllElements();
       public List<Element> getElementsByModule(Long idModule);
}
