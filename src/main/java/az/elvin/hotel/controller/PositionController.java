package az.elvin.hotel.controller;

import az.elvin.hotel.dao.PositionDao;
import az.elvin.hotel.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
public class PositionController {
    @Autowired
    private PositionDao positionDao;

    @GetMapping(value = "/getPositionList")
    public ModelAndView getPositionList() {
        ModelAndView model = new ModelAndView("position/positionList");
        List<Position> positionList = (List<Position>) positionDao.findAll();
        model.addObject("positionList", positionList);
        return model;
    }

    @GetMapping(value = "/newPosition")
    public ModelAndView newPosition() {
        ModelAndView model = new ModelAndView("position/newPosition");
        return model;
    }

    @PostMapping(value = "/addPosition")
    public @ResponseBody
    String addPosition(
            @RequestParam("name") String name) {
        Position position = new Position();
        position.setPosition_value(name);
        positionDao.save(position);
        return "success";
    }

    @GetMapping("/editPosition/{id}")
    public ModelAndView editPosition(
            @PathVariable(value = "id") Long id_position) {
        ModelAndView model = new ModelAndView("position/editPosition");
        Optional<Position> position = positionDao.findById(id_position);
        if (position.isPresent()) {
            Position position1 = position.get();
            model.addObject("position1", position1);
        }

        return model;
    }

    @PostMapping(value = "*/updatePosition")
    public @ResponseBody
    String updatePosition(
            @RequestParam("name") String name,
            @RequestParam("positionId") Long id_position) {
        Optional<Position> position2 = positionDao.findById(id_position);
        if (position2.isPresent()) {
            Position position1 = position2.get();
            Position position = new Position();
            position.setId_position(id_position);
            position.setPosition_value(name);
            position.setActive(position1.getActive());
            position.setData_date(position1.getData_date());
            positionDao.save(position);
            return "success";
        }
        return "success";
    }

    @PostMapping(value = "/deletePosition")
    public @ResponseBody
    String deletePosition(
            @RequestParam("positionId") Long id_position) {
        Optional<Position> position2 = positionDao.findById(id_position);
        if (position2.isPresent()) {
            Position position1 = position2.get();
            Position position = new Position();
            position.setId_position(id_position);
            position.setPosition_value(position1.getPosition_value());
            position.setActive(0);
            position.setData_date(position1.getData_date());
            positionDao.save(position);
            return "success";
        }
        return "success";

    }
}
