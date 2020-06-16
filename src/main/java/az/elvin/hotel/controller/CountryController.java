package az.elvin.hotel.controller;

import az.elvin.hotel.dao.CountryDao;
import az.elvin.hotel.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
public class CountryController {
    @Autowired
    private CountryDao countryDao;
    @GetMapping(value = "/getCountryList")
    public ModelAndView getCountryList() {
        ModelAndView model = new ModelAndView("country/countryList");
        List<Country> countryList = (List<Country>) countryDao.findAll();
        model.addObject("countryList", countryList);
        return model;
    }

    @GetMapping(value = "/newCountry")
    public ModelAndView newCountry() {
        ModelAndView model = new ModelAndView("country/newCountry");
        return model;
    }

    @PostMapping(value = "/addCountry")
    public @ResponseBody
    String addCountry(
            @RequestParam("name") String name, @RequestParam("sortName") String sortName) {
        Country country = new Country();
        country.setName(name);
        country.setSort_name(sortName);
        countryDao.save(country);
        return "success";
    }

    @GetMapping("/editCountry/{id}")
    public ModelAndView editCountry(
            @PathVariable(value = "id") Long id_country) {
        ModelAndView model = new ModelAndView("country/editCountry");
        Optional<Country> country = countryDao.findById(id_country);
        if (country.isPresent()) {
            Country country1 = country.get();
            model.addObject("country1", country1);
        }

        return model;
    }

    @PostMapping(value = "*/updateCountry")
    public @ResponseBody
    String updateCountry(
            @RequestParam("name") String name,
            @RequestParam("sortName") String sortName,
            @RequestParam("countryId") Long id_country) {
        Optional<Country> country2 = countryDao.findById(id_country);
        if (country2.isPresent()) {
            Country country1 = country2.get();
            Country country = new Country();
            country.setId_country(id_country);
            country.setName(country1.getName());
            country.setSort_name(country1.getSort_name());
            country.setActive(country1.getActive());
            country.setData_date(country1.getData_date());
            countryDao.save(country);
            return "success";
        }
        return "success";
    }
    @PostMapping(value = "/deleteCountry")
    public @ResponseBody String  deleteCountry( @RequestParam("countryId") Long id_country){
        Optional<Country> country2 = countryDao.findById(id_country);
        if (country2.isPresent()) {
            Country country1 = country2.get();
            Country country = new Country();
            country.setId_country(id_country);
            country.setName(country1.getName());
            country.setSort_name(country1.getSort_name());
            country.setActive(0);
            country.setData_date(country1.getData_date());
            countryDao.save(country);
            return "success";
        }
        return "success";
    }
}
