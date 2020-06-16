package az.elvin.hotel.controller;

import az.elvin.hotel.dao.FloorDao;
import az.elvin.hotel.model.Floor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
public class FloorController {
    @Autowired
    private FloorDao floorDao;

    @GetMapping(value = "/getFloorList")
    public ModelAndView getFloorList() {
        ModelAndView model = new ModelAndView("floor/floorList");
        List<Floor> floorList = (List<Floor>) floorDao.findAll();
        model.addObject("floorList", floorList);
        return model;
    }

    @GetMapping(value = "/newFloor")
    public ModelAndView newFloor() {
        ModelAndView model = new ModelAndView("floor/newFloor");
        return model;
    }

    @PostMapping(value = "/addFloor")
    public @ResponseBody
    String addFloor(@RequestParam("name") String name,
                    @RequestParam("floorNumber") String floorNumber,
                    @RequestParam("description") String description,
                    @RequestParam("action1") Integer action1) {
        Floor floor = new Floor();
        floor.setName(name);
        floor.setFloor_number(floorNumber);
        floor.setDescription(description);
        floor.setAction(action1);
        floorDao.save(floor);
        return "succes";

    }

    @GetMapping("/editFloor/{id}")
    public ModelAndView editFloor(
            @PathVariable(value = "id") Long id_floor) {
        ModelAndView model = new ModelAndView("floor/editFloor");
        Optional<Floor> floor = floorDao.findById(id_floor);
        if (floor.isPresent()) {
            Floor floor1 = floor.get();
            model.addObject("floor1", floor1);
        }

        return model;
    }
    @GetMapping("/viewFloor/{id}")
    public ModelAndView viewFloor(
            @PathVariable(value = "id") Long id_floor) {
        ModelAndView model = new ModelAndView("floor/editFloor");
        Optional<Floor> floor = floorDao.findById(id_floor);
        if (floor.isPresent()) {
            Floor floor1 = floor.get();
            model.addObject("floor1", floor1);
        }

        return model;
    }
    @PostMapping(value = "*/updateFloor")
    public @ResponseBody
    String updateFloor(
            @RequestParam("name") String name,
            @RequestParam("floorNumber") String floorNumber,
            @RequestParam("description") String description,
            @RequestParam("action1") Integer action1,
            @RequestParam("floorId") Long id_floor) {
        Optional<Floor> floor2 = floorDao.findById(id_floor);
        if (floor2.isPresent()) {
            Floor floor1 = floor2.get();
            Floor floor = new Floor();
            floor.setId_floor(id_floor);
            floor.setName(name);
            floor.setDescription(description);
            floor.setAction(action1);
            floor.setFloor_number(floorNumber);
            floor.setActive(floor1.getActive());
            floor.setData_date(floor1.getData_date());
            floorDao.save(floor);
            return "success";
        }
        return "success";
    }

    @PostMapping(value = "/deleteFloor")
    public @ResponseBody
    String deleteFloor(
            @RequestParam("floorId") Long id_floor) {
        Optional<Floor> floor2 = floorDao.findById(id_floor);
        if (floor2.isPresent()) {
            Floor floor1 = floor2.get();
            Floor floor = new Floor();
            floor.setId_floor(id_floor);
            floor.setName(floor1.getName());
            floor.setDescription(floor1.getDescription());
            floor.setAction(floor1.getAction());
            floor.setFloor_number(floor1.getFloor_number());
            floor.setActive(0);
            floor.setData_date(floor1.getData_date());
            floorDao.save(floor);
            return "success";
        }
        return "success";

    }
}