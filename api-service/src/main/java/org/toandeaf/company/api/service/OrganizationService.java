package org.toandeaf.company.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.toandeaf.company.api.dao.OrganizationDao;
import org.toandeaf.company.api.model.Organization;
import org.toandeaf.company.api.util.HttpUtil;

import java.util.List;
import java.util.Optional;

import static org.toandeaf.company.api.util.HttpUtil.convertToResponseEntity;


@Service
public class OrganizationService
{
    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    ApplicationService applicationService;

    public ResponseEntity getAllOrganizations()
    {
        List<Organization> organizationList = organizationDao.findAll();

        return convertToResponseEntity(organizationList, HttpStatus.ACCEPTED);
    }

    public ResponseEntity getOrganizationById(String id)
    {
        Optional<Organization> organization = organizationDao.findById(id);

        Organization organizationContent = organization.isPresent() ? organization.get() : null;

        return convertToResponseEntity(organizationContent, HttpStatus.ACCEPTED);
    }

    public ResponseEntity getApplicationsByOrganisation(String id, String appName, String ordering)
    {
        return applicationService.getApplicationsByOrganisation(id, appName, ordering);
    }
}
