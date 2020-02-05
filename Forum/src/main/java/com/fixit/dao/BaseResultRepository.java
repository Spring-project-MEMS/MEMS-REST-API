package com.fixit.dao;

import com.fixit.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseResultRepository<T extends Result> extends JpaRepository<T, Long> {

       // @Query("select e from #{#entityName} e")
        //List<EntityType> findThemAll();

}

