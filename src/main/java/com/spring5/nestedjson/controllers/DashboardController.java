package com.spring5.nestedjson.controllers;

import com.spring5.nestedjson.services.IDashboardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/excise")
@RestController
public class DashboardController {

    @Autowired
    private IDashboardService dashboardService;

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);

    @RequestMapping("/getJsonDataList")
    public String getJsonDataList(@RequestBody String dashboardRequest) throws Exception {
        LOGGER.trace("#-> Starting from getJsonDataList() from DashboardController with Request:: "+dashboardRequest);
        String jsonString;
        jsonString = this.dashboardService.getJsonDataList(dashboardRequest);
        LOGGER.trace("#-> Exiting from getJsonDataList() from DashboardController with Response:: "+jsonString);
        return jsonString;
    }
}
