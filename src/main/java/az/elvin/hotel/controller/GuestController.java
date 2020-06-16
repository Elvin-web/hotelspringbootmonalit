package az.elvin.hotel.controller;

import az.elvin.hotel.dao.*;
import az.elvin.hotel.model.*;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;



@RestController
public class GuestController {
    @Autowired
    private GuestDao guestDao;
    @Autowired
    private CountryDao countryDao;
    @Autowired
    private CityDao cityDao;
    @Autowired
    private PassportDao passportDao;
    @Autowired
    private PaymentDao paymentDao;
    @Autowired
    private ReservationDao reservationDao;
    @Autowired
    private PayTypeDao payTypeDao;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private static final String UPLOAD_DIRECTORY = "upload";
    private static final int THRESHOLD_SIZE = 1024 * 1024 * 3;
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 10;
    private static final int REQUEST_SIZE = 1024 * 1024 * 50;

    @GetMapping(value = "/getGuestList")
    public ModelAndView getGuestList() {
        ModelAndView model = new ModelAndView("guest/guestList");
        List<Guest> guestList = (List<Guest>) guestDao.findAll();
        model.addObject("guestList", guestList);
        Integer active1 = 1;
        Long id_booking_status1 = Long.valueOf(2);
        Long id_payment_status = Long.valueOf(1);
        long reservationGuestAcount = reservationDao.countReservationByActiveAndBookingStatusAndPaymentStatus(active1, id_booking_status1, id_payment_status);
        model.addObject("reservationGuestAcount", reservationGuestAcount);
        Integer active = 1;
        Long id_booking_status = Long.valueOf(1);
        long reservationAcount = reservationDao.countReservationByActiveAndBookingStatus(active, id_booking_status);
        model.addObject("reservationAcount", reservationAcount);
        Long ToWeekIncomeCount = paymentDao.ToWeekIncomeCount();
        if(ToWeekIncomeCount!=null){
            model.addObject("ToWeekIncomeCount", ToWeekIncomeCount);
        }

        return model;
    }

    @GetMapping(value = "/getCityListByCountryId")
    public @ResponseBody
    ModelAndView getCityListByCountryId(@RequestParam("countryId") Long country_id) {
        ModelAndView model = new ModelAndView("guest/cityCombo");
        Country country = new Country();
        country.setId_country(country_id);
        List<City> cityList = cityDao.findCityByCountry(country);
        model.addObject("cityList", cityList);
        return model;
    }

    @GetMapping(value = "/newGuest")
    public ModelAndView newGuest() {
        ModelAndView model = new ModelAndView("guest/newGuest");
        List<Passport> passportList = (List<Passport>) passportDao.findAll();
        model.addObject("passportList", passportList);
        List<Country> countryList = (List<Country>) countryDao.findAll();
        model.addObject("countryList", countryList);
        List<City> cityList = (List<City>) cityDao.findAll();
        model.addObject("cityList", cityList);
        return model;
    }

    @PostMapping(value = "/addGuest")
    public @ResponseBody
    ModelAndView addRoomType(@RequestParam("name") String name,
                             @RequestParam("surname") String surname,
                             @RequestParam("email") String email,
                             @RequestParam("passport") Long passport_id,
                             @RequestParam("dob") String dob,
                             @RequestParam("phone") String phone,
                             @RequestParam("ID1") String ID1,
                             @RequestParam("city") Long city_id,
                             @RequestParam("country") Long country_id,
                             @RequestParam("gender") String gender,
                             @RequestParam("fileName1") MultipartFile fileName1,
                             @RequestParam("fileName2") MultipartFile fileName2,
                             HttpServletRequest request, HttpServletResponse response) {

        ModelAndView model = new ModelAndView("guest/guestList");
        List<Guest> guestList = (List<Guest>) guestDao.findAll();
        model.addObject("guestList", guestList);
        Guest guest = new Guest();
        String filePath = "";
        String newFileName = "";
        String filePath1 = "";
        String newFileName1 = "";
        if (fileName1.isEmpty()) {
            //  return "Please select a file and try again";
            //return model;
        }
        try {

            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(THRESHOLD_SIZE);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(MAX_FILE_SIZE);
            upload.setSizeMax(REQUEST_SIZE);
            System.out.println(request.getServletContext().getRealPath(""));
            String uploadPath = request.getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
            System.out.println("uploadPath=" + uploadPath);
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            UUID uuid1 = UUID.randomUUID();
            UUID uuid2 = UUID.randomUUID();
            byte[] bytes = fileName1.getBytes();
            String fileName3 = fileName1.getOriginalFilename();
            System.out.println("fileName3=" + fileName3);
            newFileName = fileName3.replace(fileName3.substring(0, fileName3.lastIndexOf(".")), uuid1.toString());
            System.out.println("newFileName=" + newFileName);
            filePath = uploadPath + File.separator + newFileName;
            Path path = Paths.get(filePath);
            Files.write(path, bytes);
            System.out.println("filePath=" + filePath);
            guest.setImage1(filePath);
            byte[] bytes1 = fileName2.getBytes();
            String fileName4 = fileName2.getOriginalFilename();
            System.out.println("fileName4=" + fileName4);
            newFileName1 = fileName4.replace(fileName4.substring(0, fileName4.lastIndexOf(".")), uuid2.toString());
            System.out.println("newFileName1=" + newFileName1);
            filePath1 = uploadPath + File.separator + newFileName1;
            Path path1 = Paths.get(filePath1);
            Files.write(path1, bytes1);
            System.out.println("filePath1=" + filePath1);
            guest.setImage2(filePath1);
            guest.setName(name);
            guest.setSurname(surname);
            guest.setEmail(email);
            guest.setDob(df.parse(dob));
            guest.setPhone(phone);
            guest.setPassport_number(ID1);
            City city = new City();
            city.setId_city(city_id);
            Country country = new Country();
            country.setId_country(country_id);
            city.setCountry(country);
            guest.setCity(city);
            Passport passport = new Passport();
            passport.setId_passport(passport_id);
            guest.setPassport(passport);
            guest.setGender(gender);
            guestDao.save(guest);
            response.setContentType("text/html");
            // return "success";
            return model;
        } catch (Exception ex) {
            request.setAttribute("message", "There was an error:" + ex.getMessage());
            ex.printStackTrace();
        }
        //return "success";
        return model;
    }

    @GetMapping("/editGuest/{id}")
    public ModelAndView editGuest(
            @PathVariable(value = "id") Long id_guest) {
        ModelAndView model = new ModelAndView("guest/editGuest");
        Optional<Guest> guest = guestDao.findById(id_guest);
        if (guest.isPresent()) {
            Guest guest1 = guest.get();
            model.addObject("guest1", guest1);
            String filePath1 = guest1.getImage1().substring(guest1.getImage1().lastIndexOf("\\"));
            String filePath2 = "upload" + File.separator + filePath1;
            model.addObject("filePath2", filePath2);

            String filePath3 = guest1.getImage2().substring(guest1.getImage2().lastIndexOf("\\"));
            String filePath4 = "upload" + File.separator + filePath3;
            model.addObject("filePath4", filePath4);

        }
        List<Passport> passportList = (List<Passport>) passportDao.findAll();
        model.addObject("passportList", passportList);
        List<Country> countryList = (List<Country>) countryDao.findAll();
        model.addObject("countryList", countryList);
        List<City> cityList = (List<City>) cityDao.findAll();
        model.addObject("cityList", cityList);
        return model;
    }

    @PostMapping(value = "*/updateGuest")
    public @ResponseBody
    ModelAndView updateGuest(@RequestParam("id") Long id_guest,
                             @RequestParam("name") String name,
                             @RequestParam("surname") String surname,
                             @RequestParam("email") String email,
                             @RequestParam("passport") Long id_passport,
                             @RequestParam("dob") String dob,
                             @RequestParam("phone") String phone,
                             @RequestParam("ID1") String ID1,
                             @RequestParam("city") Long id_city,
                             @RequestParam("country") Long id_country,
                             @RequestParam("gender") String gender,
                             @RequestParam("fileName1") MultipartFile fileName1,
                             @RequestParam("fileName2") MultipartFile fileName2,
                             HttpServletRequest request, HttpServletResponse response) {

        ModelAndView model = new ModelAndView("guest/guestList");
        List<Guest> guestList = (List<Guest>) guestDao.findAll();
        model.addObject("guestList", guestList);
        String filePath = "";
        String newFileName = "";
        String filePath1 = "";
        String newFileName1 = "";
        if (fileName1.isEmpty()) {
            //  return "Please select a file and try again";
            //return model;
        }
        try {

            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(THRESHOLD_SIZE);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(MAX_FILE_SIZE);
            upload.setSizeMax(REQUEST_SIZE);
            System.out.println(request.getServletContext().getRealPath(""));
            String uploadPath = request.getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
            System.out.println("uploadPath=" + uploadPath);
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            Optional<Guest> guest2 = guestDao.findById(id_guest);
            Optional<Passport> passport2 = passportDao.findById(id_passport);
            Optional<City> city2 = cityDao.findById(id_city);
            Optional<Country> country2 = countryDao.findById(id_country);
            if (passport2.isPresent()) {
                if (city2.isPresent()) {
                    if (country2.isPresent()) {
                        if (guest2.isPresent()) {
                            Passport passport1 = passport2.get();
                            Guest guest1 = guest2.get();
                            City city1 = city2.get();
                            Country country1 = country2.get();
                            Guest guest = new Guest();
                            Passport passport = new Passport();
                            City city = new City();
                            Country country = new Country();
                            UUID uuid1 = UUID.randomUUID();
                            UUID uuid2 = UUID.randomUUID();
                            byte[] bytes = fileName1.getBytes();
                            String fileName3 = fileName1.getOriginalFilename();
                            System.out.println("fileName3=" + fileName3);
                            newFileName = fileName3.replace(fileName3.substring(0, fileName3.lastIndexOf(".")), uuid1.toString());
                            System.out.println("newFileName=" + newFileName);
                            filePath = uploadPath + File.separator + newFileName;
                            Path path = Paths.get(filePath);
                            Files.write(path, bytes);
                            System.out.println("filePath=" + filePath);
                            guest.setImage1(filePath);
                            byte[] bytes1 = fileName2.getBytes();
                            String fileName4 = fileName2.getOriginalFilename();
                            System.out.println("fileName4=" + fileName4);
                            newFileName1 = fileName4.replace(fileName4.substring(0, fileName4.lastIndexOf(".")), uuid2.toString());
                            System.out.println("newFileName1=" + newFileName1);
                            filePath1 = uploadPath + File.separator + newFileName1;
                            Path path1 = Paths.get(filePath1);
                            Files.write(path1, bytes1);
                            System.out.println("filePath1=" + filePath1);
                            guest.setImage2(filePath1);
                            guest.setName(name);
                            guest.setSurname(surname);
                            guest.setEmail(email);
                            guest.setDob(df.parse(dob));
                            guest.setPhone(phone);
                            guest.setPassport_number(ID1);
                            city.setId_city(city1.getId_city());
                            city.setName(city1.getName());
                            city.setData_date(city1.getData_date());
                            city.setActive(city1.getActive());
                            country.setId_country(country1.getId_country());
                            country.setName(country1.getName());
                            country.setSort_name(country1.getSort_name());
                            country.setData_date(country1.getData_date());
                            country.setActive(country1.getActive());
                            city.setCountry(country);
                            guest.setCity(city);
                            passport.setId_passport(passport1.getId_passport());
                            passport.setPassport_type(passport1.getPassport_type());
                            passport.setData_date(passport1.getData_date());
                            passport.setActive(passport1.getActive());
                            guest.setPassport(passport);
                            guest.setGender(gender);
                            guest.setId_guest(id_guest);
                            guest.setData_date(guest1.getData_date());
                            guest.setActive(guest1.getActive());
                            guestDao.save(guest);
                            response.setContentType("text/html");
                            // return "success";
                            return model;
                        }
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message", "There was an error:" + ex.getMessage());
            ex.printStackTrace();
        }
        //return "success";
        return model;
    }

    @PostMapping(value = "/deleteGuest")
    public @ResponseBody
    ModelAndView deleteGuest(@RequestParam("guestId") Long id_guest) {

        ModelAndView model = new ModelAndView("guest/guestList");
        List<Guest> guestList = (List<Guest>) guestDao.findAll();
        model.addObject("guestList", guestList);
        Optional<Guest> guest2 = guestDao.findById(id_guest);
        if (guest2.isPresent()) {
            Guest guest1 = guest2.get();
            Guest guest = new Guest();
            Passport passport = new Passport();
            City city = new City();
            Country country = new Country();
            guest.setImage1(guest1.getImage1());
            guest.setImage2(guest1.getImage2());
            guest.setName(guest1.getName());
            guest.setSurname(guest1.getSurname());
            guest.setEmail(guest1.getEmail());
            guest.setDob(guest1.getDob());
            guest.setPhone(guest1.getPhone());
            guest.setPassport_number(guest1.getPassport_number());
            city.setId_city(guest1.getCity().getId_city());
            city.setName(guest1.getCity().getName());
            city.setData_date(guest1.getCity().getData_date());
            city.setActive(guest1.getCity().getActive());
            country.setId_country(guest1.getCity().getCountry().getId_country());
            country.setName(guest1.getCity().getCountry().getName());
            country.setSort_name(guest1.getCity().getCountry().getSort_name());
            country.setData_date(guest1.getCity().getCountry().getData_date());
            country.setActive(guest1.getCity().getCountry().getActive());
            city.setCountry(country);
            guest.setCity(city);
            passport.setId_passport(guest1.getPassport().getId_passport());
            passport.setPassport_type(guest1.getPassport().getPassport_type());
            passport.setData_date(guest1.getPassport().getData_date());
            passport.setActive(guest1.getPassport().getActive());
            guest.setPassport(passport);
            guest.setGender(guest1.getGender());
            guest.setId_guest(id_guest);
            guest.setData_date(guest1.getData_date());
            guest.setActive(0);
            guestDao.save(guest);
            // return "success";
            return model;
        }
        //return "success";
        return model;
    }

    @GetMapping("/viewGuest/{id}")
    public ModelAndView viewGuest(
            @PathVariable(value = "id") Long id_guest) {
        ModelAndView model = new ModelAndView("guest/viewGuest");
        Optional<Guest> guest = guestDao.findById(id_guest);
        if (guest.isPresent()) {
            Guest guest1 = guest.get();
            model.addObject("guest1", guest1);
            String filePath1 = guest1.getImage1().substring(guest1.getImage1().lastIndexOf("\\"));
            String filePath2 = "upload" + File.separator + filePath1;
            model.addObject("filePath2", filePath2);

            String filePath3 = guest1.getImage2().substring(guest1.getImage2().lastIndexOf("\\"));
            String filePath4 = "upload" + File.separator + filePath3;
            model.addObject("filePath4", filePath4);

        }
        List<Passport> passportList = (List<Passport>) passportDao.findAll();
        model.addObject("passportList", passportList);
        List<Country> countryList = (List<Country>) countryDao.findAll();
        model.addObject("countryList", countryList);
        List<City> cityList = (List<City>) cityDao.findAll();
        model.addObject("cityList", cityList);
        return model;
    }

    @GetMapping("/paymentGuest/{id}")
    public ModelAndView paymentGuest(
            @PathVariable(value = "id") Long id_guest) {
        ModelAndView model = new ModelAndView("guest/paymentGuest");
        Guest guest = new Guest();
        guest.setId_guest(id_guest);
        Reservation reservation = new Reservation();
        reservation.setGuest(guest);
        List<Payment> paymentList = paymentDao.findPaymentByReservationGuest(guest);
        model.addObject("paymentList", paymentList);
        List<Passport> passportList = (List<Passport>) passportDao.findAll();
        model.addObject("passportList", passportList);
        List<City> cityList = (List<City>) cityDao.findAll();
        model.addObject("cityList", cityList);
        return model;
    }

    @GetMapping("*/invoicePayment/{id}")
    public ModelAndView invoicePayment(
            @PathVariable(value = "id") Long id_payment) {
        ModelAndView model = new ModelAndView("guest/invoicePayment");
        Optional<Payment> payment2 = paymentDao.findById(id_payment);
        if (payment2.isPresent()) {
            Payment payment = payment2.get();
            model.addObject("payment", payment);
        }
        List<Reservation> reservationList = (List<Reservation>) reservationDao.findAll();
        model.addObject("reservationList", reservationList);
        List<PayType> payTypeList = (List<PayType>) payTypeDao.findAll();
        model.addObject("payTypeList", payTypeList);
        return model;
    }
}
