package ua.varus.stoplist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.varus.stoplist.domain.Codificator;
import ua.varus.stoplist.domain.StoplistRow;
import ua.varus.stoplist.domain.StoplistSearchForm;
import ua.varus.stoplist.service.StoplistService;

import java.util.List;

@RestController
@RequestMapping("/stoplist")
public class StoplistController {

    @Autowired
    private StoplistService stoplistService;


    @RequestMapping(value = {"/ajax/searchStopList"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<StoplistRow> getListStoplist(@RequestBody StoplistSearchForm form){
        return stoplistService.getList(form);
    }

    @RequestMapping(value = {"/ajax/insertStopList"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public StoplistRow insertStopList(@RequestBody StoplistRow stoplistRow){
        return stoplistService.insertStopList(stoplistRow);
    }

    @RequestMapping(value = {"/ajax/deleteStoplistRow"}, method = RequestMethod.POST)
    @ResponseBody
    public boolean deleteStoplistRow(@RequestParam int id){
        return stoplistService.deleteStoplistRow(id);
    }

    @RequestMapping(value = {"/ajax/editStoplistRow"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public StoplistRow editStoplistRow(@RequestBody StoplistRow stoplistRow){
        return stoplistService.editStoplistRow(stoplistRow);
    }

    @RequestMapping(value = {"/ajax/getListCodificators"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Codificator> getListCodificators(){
        return stoplistService.getListCodificators();
    }

    @RequestMapping(value = {"/ajax/insertCodificator"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Codificator> insertStopCodificator(@RequestBody Codificator codificator){
        return stoplistService.insertStopCodificator(codificator);
    }

    @RequestMapping(value = {"/ajax/getListCompany"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<String> getListCompany(){
        return stoplistService.getListCompany();
    }

    @RequestMapping(value = {"/ajax/getListDepartment"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<String> getListDepartmnet(){
        return stoplistService.getListDepartmnet();
    }

    @RequestMapping(value = {"/ajax/getListSources"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<String> getListSources(){
        return stoplistService.getListSources();
    }


}
