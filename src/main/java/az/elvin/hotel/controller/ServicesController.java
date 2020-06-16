package az.elvin.hotel.controller;

import az.elvin.hotel.dao.PriceDao;
import az.elvin.hotel.dao.PriceTypeDao;
import az.elvin.hotel.dao.ServicesDao;
import az.elvin.hotel.model.PriceType;
import az.elvin.hotel.model.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
public class ServicesController {
    @Autowired
    private PriceTypeDao priceTypeDao;
    @Autowired
    private ServicesDao servicesDao;

    @GetMapping(value = "/getServicesList")
    public ModelAndView getServicesList() {
        ModelAndView model = new ModelAndView("services/servicesList");
        List<Services> servicesList = (List<Services>) servicesDao.findAll();
        model.addObject("servicesList", servicesList);
        return model;
    }

    @GetMapping(value = "/newServices")
    public ModelAndView newServices() {
        ModelAndView model = new ModelAndView("services/newServices");
        List<PriceType> priceTypeList = (List<PriceType>) priceTypeDao.findAll();
        model.addObject("priceTypeList", priceTypeList);
        return model;
    }

    @PostMapping(value = "/addServices")
    public @ResponseBody
    String addServices(
            @RequestParam("name") String name,
            @RequestParam("price") String price,
            @RequestParam("description") String description,
            @RequestParam("action1") String action1,
            @RequestParam("priceType") String priceType) {
        Services services = new Services();
        services.setName(name);
        services.setAmount(Double.valueOf(price));
        services.setDescription(description);
        services.setAction(Integer.valueOf(action1));
        PriceType priceType1 = new PriceType();
        priceType1.setId_price_type(Long.parseLong(priceType));
        services.setPriceType(priceType1);
        servicesDao.save(services);
        return "success";
    }

    @GetMapping("/editServices/{id}")
    public ModelAndView editServices(
            @PathVariable(value = "id") Long id_services) {
        ModelAndView model = new ModelAndView("services/editServices");
        Optional<Services> services = servicesDao.findById(id_services);
        if (services.isPresent()) {
            Services services1 = services.get();
            model.addObject("services1", services1);
        }
        List<PriceType> priceTypeList = (List<PriceType>) priceTypeDao.findAll();
        model.addObject("priceTypeList", priceTypeList);

        return model;
    }

    @PostMapping(value = "*/updateServices")
    public @ResponseBody
    String updateServices(
            @RequestParam("name") String name,
            @RequestParam("price") Double price,
            @RequestParam("description") String description,
            @RequestParam("action1") Integer action1,
            @RequestParam("priceType") Long id_price_type,
            @RequestParam("servicesId") Long id_services) {
        Optional<Services> services2 = servicesDao.findById(id_services);
        Optional<PriceType> priceType2 = priceTypeDao.findById(id_price_type);
        if (priceType2.isPresent()) {
            if (services2.isPresent()) {
                Services services1 = services2.get();
                PriceType priceType1 = priceType2.get();
                Services services = new Services();
                PriceType priceType = new PriceType();
                priceType.setId_price_type(id_price_type);
                priceType.setPrice_type(priceType1.getPrice_type());
                priceType.setActive(priceType1.getActive());
                priceType.setData_date(priceType1.getData_date());
                services.setPriceType(priceType);
                services.setName(name);
                services.setAmount(price);
                services.setDescription(description);
                services.setActive(services1.getActive());
                services.setData_date(services1.getData_date());
                services.setId_services(id_services);
                services.setAction(action1);
                servicesDao.save(services);
                return "success";
            }
        }
        return "success";
    }
    @PostMapping(value = "/deleteServices")
    public @ResponseBody
    String deleteServices(@RequestParam("servicesId") Long id_services) {
        Optional<Services> city2 = servicesDao.findById(id_services);
        if (city2.isPresent()) {
            Services services1 = city2.get();
            Services services = new Services();
            PriceType priceType = new PriceType();
            priceType.setId_price_type(services1.getPriceType().getId_price_type());
            priceType.setPrice_type(services1.getPriceType().getPrice_type());
            priceType.setActive(services1.getPriceType().getActive());
            priceType.setData_date(services1.getPriceType().getData_date());
            services.setPriceType(priceType);

            services.setName(services1.getName());
            services.setAmount(services1.getAmount());
            services.setDescription(services1.getDescription());
            services.setAction(services1.getAction());
            services.setActive(0);
            services.setData_date(services1.getData_date());
            services.setId_services(id_services);
            servicesDao.save(services);
            return "success";

        }
        return "success";
    }
}