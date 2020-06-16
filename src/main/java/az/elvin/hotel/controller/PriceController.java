package az.elvin.hotel.controller;

import az.elvin.hotel.dao.PriceDao;
import az.elvin.hotel.dao.RoomTypeDao;
import az.elvin.hotel.model.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@RestController
public class PriceController {
    @Autowired
    private PriceDao priceDao;
    @Autowired
    private RoomTypeDao roomTypeDao;
    @GetMapping(value = "/getPriceList")
    public ModelAndView getPriceList() {
        ModelAndView model = new ModelAndView("price/priceList");
        List<RoomType> roomTypeList = (List<RoomType>) roomTypeDao.findAll();
        model.addObject("roomTypeList", roomTypeList);
        return model;
    }
}
