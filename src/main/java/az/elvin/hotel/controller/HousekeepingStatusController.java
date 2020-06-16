package az.elvin.hotel.controller;

import az.elvin.hotel.dao.HousekeepingStatusDao;
import az.elvin.hotel.model.HousekeepingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
public class HousekeepingStatusController {
    @Autowired
    private HousekeepingStatusDao housekeepingStatusDao;

    @GetMapping(value = "/getHousekeepingStatusList")
    public ModelAndView getHousekeepingStatusList() {
        ModelAndView model = new ModelAndView("housekeepingStatus/housekeepingStatusList");
        List<HousekeepingStatus> housekeepingStatusList = (List<HousekeepingStatus>) housekeepingStatusDao.findAll();
        model.addObject("housekeepingStatusList", housekeepingStatusList);
        return model;
    }

    @GetMapping(value = "/newHousekeepingStatus")
    public ModelAndView newhousekeepingStatus() {
        ModelAndView model = new ModelAndView("housekeepingStatus/newHousekeepingStatus");
        return model;
    }

    @PostMapping(value = "/addHousekeepingStatus")
    public @ResponseBody
    String addHousekeepingStatus(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("action1") Integer action1) {
        HousekeepingStatus housekeepingStatus = new HousekeepingStatus();
        housekeepingStatus.setName(name);
        housekeepingStatus.setDescription(description);
        housekeepingStatus.setAction(action1);
        housekeepingStatusDao.save(housekeepingStatus);
        return "success";
    }

    @GetMapping("/editHousekeepingStatus/{id}")
    public ModelAndView editHousekeepingStatus(
            @PathVariable(value = "id") Long id_housekeeping_status) {
        ModelAndView model = new ModelAndView("housekeepingStatus/editHousekeepingStatus");
        Optional<HousekeepingStatus> housekeepingStatus = housekeepingStatusDao.findById(id_housekeeping_status);
        if (housekeepingStatus.isPresent()) {
            HousekeepingStatus housekeepingStatus1 = housekeepingStatus.get();
            model.addObject("housekeepingStatus1", housekeepingStatus1);
        }

        return model;
    }

    @PostMapping(value = "*/updateHousekeepingStatus")
    public @ResponseBody
    String updateHousekeepingStatus(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("action1") Integer action1,
            @RequestParam("housekeepingStatusId") Long id_housekeeping_status) {
        Optional<HousekeepingStatus> housekeepingStatus2 = housekeepingStatusDao.findById(id_housekeeping_status);
        if (housekeepingStatus2.isPresent()) {
            HousekeepingStatus housekeepingStatus1 = housekeepingStatus2.get();
            HousekeepingStatus housekeepingStatus = new HousekeepingStatus();
            housekeepingStatus.setId_housekeeping_status(id_housekeeping_status);
            housekeepingStatus.setName(name);
            housekeepingStatus.setDescription(description);
            housekeepingStatus.setAction(action1);
            housekeepingStatus.setActive(housekeepingStatus1.getActive());
            housekeepingStatus.setData_date(housekeepingStatus1.getData_date());
            housekeepingStatusDao.save(housekeepingStatus);
            return "success";
        }
        return "success";
    }
    @PostMapping(value = "/deleteHousekeepingStatus")
    public @ResponseBody
    String deleteHousekeepingStatus(
            @RequestParam("housekeepingStatusId") Long id_housekeeping_status) {
        Optional<HousekeepingStatus> housekeepingStatus2 = housekeepingStatusDao.findById(id_housekeeping_status);
        if (housekeepingStatus2.isPresent()) {
            HousekeepingStatus housekeepingStatus1 = housekeepingStatus2.get();
            HousekeepingStatus housekeepingStatus = new HousekeepingStatus();
            housekeepingStatus.setId_housekeeping_status(id_housekeeping_status);
            housekeepingStatus.setName(housekeepingStatus1.getName());
            housekeepingStatus.setDescription(housekeepingStatus1.getDescription());
            housekeepingStatus.setAction(housekeepingStatus1.getAction());
            housekeepingStatus.setActive(0);
            housekeepingStatus.setData_date(housekeepingStatus1.getData_date());
            housekeepingStatusDao.save(housekeepingStatus);
            return "success";
        }
        return "success";

    }
}