package org.toandeaf.company.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.toandeaf.company.api.dao.OrganizationDao;
import org.toandeaf.company.api.model.Organization;

import java.util.List;
import java.util.Optional;


@Service
public class OrganizationService
{
    @Autowired
    OrganizationDao organizationDao;

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

    private ResponseEntity convertToResponseEntity(Object content, HttpStatus status)
    {
        if(content == null)
        {
            return new ResponseEntity(null, null, HttpStatus.NOT_FOUND);
        }
        else
        {
            return new ResponseEntity(content, null, status);
        }
    }
}
