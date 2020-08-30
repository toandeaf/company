package org.toandeaf.company.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.toandeaf.company.api.model.Organization;

@Repository
public interface OrganizationDao extends JpaRepository<Organization, String>
{
}
