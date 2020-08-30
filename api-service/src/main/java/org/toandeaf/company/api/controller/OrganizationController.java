package org.toandeaf.company.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.toandeaf.company.api.service.OrganizationService;

@RestController
@RequestMapping("/organizations")
public class OrganizationController
{
    @Autowired
    OrganizationService orgService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAllOrganisations()
    {
        return orgService.getAllOrganizations();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getOrganizationById(@PathVariable("id") String id)
    {
        return orgService.getOrganizationById(id);
    }
}
