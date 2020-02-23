package com.spring5.nestedjson.services.serviceimpls;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring5.nestedjson.controllers.DashboardController;
import com.spring5.nestedjson.models.DashboardResponse;
import com.spring5.nestedjson.models.ExciseServiceMaster;
import com.spring5.nestedjson.repositories.IExciseServiceMasterRepository;
import com.spring5.nestedjson.services.IDashboardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardService implements IDashboardService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);
    private static ObjectMapper MAPPER = new ObjectMapper();
    private static final String AIRTIME_ON_CALL = "airtime_on_call";

    @Autowired
    private IExciseServiceMasterRepository exciseServiceMasterRepository;

    @Override
    public String getJsonDataList(String dashboardRequest) throws Exception {
        LOGGER.trace("#-> Starting from getJsonDataList() from DashboardController with Request:: "+dashboardRequest);
        String returnValue = null;
        DashboardResponse dashboardResponse = new DashboardResponse();
        try {
            JsonNode dashboardrequest = MAPPER.readTree(dashboardRequest);

            List<ExciseServiceMaster> subChildList = new ArrayList<>();
            Map<String, Object> childMap = new HashMap<>();
            Map<String, Object> parentMap = new HashMap<>();


            List<ExciseServiceMaster> exciseServiceMaster = this.exciseServiceMasterRepository.findAll();
            Object nestedJsonObject = exciseServiceMaster.stream().map(parentService -> {
                if(parentService.getUesmServiceName().equals(AIRTIME_ON_CALL)){
                    exciseServiceMaster.stream().map(childService -> {
                        if(childService.getUesmParentId().equals(parentService.getUesmId()) && !parentService.getUesmId().equals(childService.getUesmId())){
                            exciseServiceMaster.stream().map(subChildService -> {
                                if(subChildService.getUesmParentId().equals(childService.getUesmId()) && !childService.getUesmId().equals(subChildService.getUesmId())){
                                    subChildList.add(subChildService);
                                }
                                return subChildList;
                            });
                        }
                        childMap.put("name",childService.getUesmServiceName());
                        childMap.put("sub_type_service", subChildList);
                        return childMap;
                    });
                }
                parentMap.put("name", parentService.getUesmServiceName());
                parentMap.put("sub_type_service", childMap);
                return parentMap;
            }).collect(Collectors.toSet());

           /* if(nestedJsonObject != null){
                dashboardResponse.setStatusCode(1);
                dashboardResponse.setResponseData("", nestedJsonObject);
            }*/
            System.out.println("##################"+ nestedJsonObject);

        } catch(Exception e) {
            System.out.println(" | #EXCEPTION_OCCURED -> "+e.getStackTrace());
        }
        returnValue = MAPPER.writeValueAsString(dashboardResponse);
        LOGGER.trace("#-> Exiting from getJsonDataList() from DashboardController with Response:: "+returnValue);
        return returnValue;
    }
}
