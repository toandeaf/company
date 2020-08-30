package org.toandeaf.company.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.toandeaf.company.api.dao.ApplicationDao;
import org.toandeaf.company.api.model.Application;

import java.util.List;

import static org.toandeaf.company.api.util.HttpUtil.convertToResponseEntity;

@Service
public class ApplicationService
{
    @Autowired
    ApplicationDao applicationDao;

    public ResponseEntity getApplicationsByOrganisation(String id, String appName, String ordering)
    {
        List<Application> apps;

        try
        {
            Sort sort = handleSortParameter(ordering);

            if(appName != null)
            {
                apps = applicationDao.findByOrganisationIdAndAppName(id, appName, sort);
            }
            else
            {
                apps = applicationDao.findByOrganizationId(id, sort);
            }
        }
        catch (NullPointerException e)
        {
            return convertToResponseEntity(e.getMessage(), HttpStatus.NO_CONTENT);
        }
        catch (Exception e)
        {
            return convertToResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return convertToResponseEntity(apps, HttpStatus.ACCEPTED);
    }

    private Sort handleSortParameter(String order) throws Exception {

        if(order == null || order.trim().length() == 0)
        {
            return null;
        }

        if(order.contains(" "))
        {
            String [] params = order.split(" ");

            Sort.Direction direction;

            if(params[1].equalsIgnoreCase("ASC"))
            {
                direction = Sort.Direction.ASC;
            }
            else if(params[1].equalsIgnoreCase("DESC"))
            {
                direction = Sort.Direction.DESC;
            }
            else
            {
                throw new Exception("Invalid column ordering string: " + params[1]);
            }

            return Sort.by(new Sort.Order(direction, params[0].toLowerCase()));
        }
        else
        {
            throw new Exception("Invalid query formatting. Must be: 'DIRECTION COLUMN' but is " + order);
        }
    }
}
