package com.ensah.core.dao;

import com.ensah.core.bo.Element;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IElementDao extends JpaRepository<Element,Long> , GenericJpa<Element, Long>{
    @Query("SELECT element FROM Element element where element.module.idModule = :idModule")
    public List<Element> getElementBy(@Param("idModule") Long idModule);
}
