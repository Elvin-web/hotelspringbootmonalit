package az.elvin.hotel.controller;

import az.elvin.hotel.business.CanvasjsChartService;
import az.elvin.hotel.dao.CanvasjsChartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

//@RestController
@RequestMapping("/canvasjschart")
public class CanvasjsChartController {
    @Autowired
    // private CanvasjsChartService canvasjsChartService;
    private CanvasjsChartDao canvasjsChartDao;

    @RequestMapping(method = RequestMethod.GET)
    public String springMVC(ModelMap modelMap) {
        //List<List<Map<Object, Object>>> canvasjsDataList = canvasjsChartService.getCanvasjsChartData();
        List<List<Map<Object, Object>>> canvasjsDataList = canvasjsChartDao.getCanvasjsChartData();
        modelMap.addAttribute("dataPointsList", canvasjsDataList);
        return "chart";
    }
}
