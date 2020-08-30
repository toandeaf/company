package org.toandeaf.company.api.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.toandeaf.company.api.model.Application;


import java.util.List;

@Repository
public interface ApplicationDao extends JpaRepository<Application, String>
{
    @Query("SELECT a from Application a join a.organizations o where o.id = :id and a.name LIKE %:name%" )
    List<Application> findByOrganisationIdAndAppName(@Param("id") String id, @Param("name") String name, Sort sort);

    @Query("SELECT a from Application a join a.organizations o where o.id = :id" )
    List<Application> findByOrganizationId(@Param("id") String id, Sort sort);
}
