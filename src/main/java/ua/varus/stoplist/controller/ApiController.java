package ua.varus.stoplist.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.varus.stoplist.domain.StoplistRow;
import ua.varus.stoplist.domain.api.CodificatorApi;
import ua.varus.stoplist.domain.api.FlagCodificatorApi;
import ua.varus.stoplist.domain.api.FormFioDate;
import ua.varus.stoplist.service.api.ApiService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
@Secured({"ROLE_API"})
public class ApiController {

//    @Autowired
//    private UserService userService;
//
//
//    @PreAuthorize("hasAuthority('ROLE_RADMIN')")
//    @RequestMapping(value = "/testApi", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public List<User> test(){
//        return userService.getOnlineUsers();
//    }

    @Autowired
    private ApiService apiService;


    @PreAuthorize("hasAuthority('ROLE_API')")
    @RequestMapping(value = "/getCodificator", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<CodificatorApi> getCodificator(@RequestParam BigDecimal inn){
        return apiService.getListCodificator(inn);
    }

    @PreAuthorize("hasAuthority('ROLE_API')")
    @RequestMapping(value = "/getFlagCodificatorApi", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public FlagCodificatorApi getFlagCodificatorApi(@RequestParam BigDecimal inn){
        return apiService.getFlagCodificatorApi(inn);
    }

//    @PreAuthorize("hasAuthority('ROLE_API')")
//    @RequestMapping(value = "/getFlagByFirstFioDate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public FlagCodificatorApi getFlagByFirstFio(@RequestBody FormFioDate formFioDate){
//        return apiService.getFlagByFirstFio(formFioDate);
//    }
    @PreAuthorize("hasAuthority('ROLE_API')")
    @RequestMapping(value = "/getFlagByFioDate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public FlagCodificatorApi getFlagByFioDate(@RequestBody FormFioDate formFioDate){
        return apiService.getFlagByFioDate(formFioDate);
    }

    @PreAuthorize("hasAuthority('ROLE_API')")
    @RequestMapping(value = "/addStopList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean addStopList(@RequestBody StoplistRow stoplistRow){
        return apiService.addStopList(stoplistRow);
    }


}
