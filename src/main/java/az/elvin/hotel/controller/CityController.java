package az.elvin.hotel.controller;

import az.elvin.hotel.dao.CityDao;
import az.elvin.hotel.dao.CountryDao;
import az.elvin.hotel.model.City;
import az.elvin.hotel.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
public class CityController {
    @Autowired
    private CountryDao countryDao;
    @Autowired
    private CityDao cityDao;

    @GetMapping(value = "/getCityList")
    public ModelAndView getCityList() {
        ModelAndView model = new ModelAndView("city/cityList");
        List<City> cityList = (List<City>) cityDao.findAll();
        model.addObject("cityList", cityList);
        return model;
    }

    @GetMapping(value = "/newCity")
    public ModelAndView newCity() {
        ModelAndView model = new ModelAndView("city/newCity");
        List<Country> countryList = (List<Country>) countryDao.findAll();
        model.addObject("countryList", countryList);
        return model;
    }

    @PostMapping(value = "/addCity")
    public @ResponseBody
    String addCity(
            @RequestParam("name") String name, @RequestParam("countryId") String countryId) {
        City city = new City();
        city.setName(name);
        Country country = new Country();
        country.setId_country(Long.parseLong(countryId));
        city.setCountry(country);
        cityDao.save(city);
        return "success";
    }

    @GetMapping("/editCity/{id}")
    public ModelAndView editCity(
            @PathVariable(value = "id") Long id_city) {
        ModelAndView model = new ModelAndView("city/editCity");
        Optional<City> city = cityDao.findById(id_city);
        if (city.isPresent()) {
            City city1 = city.get();
            model.addObject("city1", city1);
        }
        List<Country> countryList = (List<Country>) countryDao.findAll();
        model.addObject("countryList", countryList);
        return model;
    }


    @PostMapping(value = "*/updateCity")
    public @ResponseBody
    String updateCity(
            @RequestParam("name") String name,
            @RequestParam("cityId") Long id_city,
            @RequestParam("countryId") Long id_country) {
        Optional<City> city2 = cityDao.findById(id_city);
        Optional<Country> country2 = countryDao.findById(id_country);
        if (country2.isPresent()) {
            if (city2.isPresent()) {
                City city1 = city2.get();
                Country country1 = country2.get();
                City city = new City();
                Country country = new Country();
                country.setId_country(id_country);
                country.setName(country1.getName());
                country.setSort_name(country1.getSort_name());
                country.setActive(country1.getActive());
                country.setData_date(country1.getData_date());
                city.setCountry(country);
                city.setName(name);
                city.setActive(city1.getActive());
                city.setData_date(city1.getData_date());
                city.setId_city(id_city);
                cityDao.save(city);
                return "success";
            }
        }
        return "success";
    }

    @PostMapping(value = "/deleteCity")
    public @ResponseBody
    String deleteCity(@RequestParam("cityId") Long id_city) {
        Optional<City> city2 = cityDao.findById(id_city);
            if (city2.isPresent()) {
                City city1 = city2.get();
                City city = new City();
                Country country = new Country();
                country.setId_country(city1.getCountry().getId_country());
                country.setName(city1.getCountry().getName());
                country.setSort_name(city1.getCountry().getSort_name());
                country.setActive(city1.getCountry().getActive());
                country.setData_date(city1.getCountry().getData_date());
                city.setCountry(country);
                city.setName(city1.getName());
                city.setActive(0);
                city.setData_date(city1.getData_date());
                city.setId_city(id_city);
                cityDao.save(city);
                return "success";

        }
        return "success";
    }

}
