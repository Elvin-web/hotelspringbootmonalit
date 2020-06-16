package az.elvin.hotel.controller;

import az.elvin.hotel.dao.HotelDao;
import az.elvin.hotel.dao.StarDao;
import az.elvin.hotel.model.Hotel;
import az.elvin.hotel.model.Star;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static sun.font.CreatedFontTracker.MAX_FILE_SIZE;

@RestController
public class HotelController {
    @Autowired
    private HotelDao hotelDao;
    @Autowired
    private StarDao starDao;
    private static final String UPLOAD_DIRECTORY = "upload";
    private static final int THRESHOLD_SIZE = 1024 * 1024 * 3;
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 10;
    private static final int REQUEST_SIZE = 1024 * 1024 * 50;

    @GetMapping(value = "/getHotelList")
    public ModelAndView getHotelList() {
        ModelAndView model = new ModelAndView("hotel/hotelList");
        List<Hotel> hotelList = (List<Hotel>) hotelDao.findAll();
        model.addObject("hotelList", hotelList);
        return model;
    }

    @GetMapping(value = "/newHotel")
    public ModelAndView newHotel() {
        ModelAndView model = new ModelAndView("hotel/newHotel");
        List<Star> starList = (List<Star>) starDao.findAll();
        model.addObject("starList", starList);
        return model;
    }

    @PostMapping(value = "/addHotel")
    public @ResponseBody
    ModelAndView addHotel(@RequestParam("name") String name,
                    @RequestParam("address") String address,
                    @RequestParam("phone") String phone,
                    @RequestParam("email") String email,
                    @RequestParam("star") Long star,
                    @RequestParam("fileName1") MultipartFile fileName1,
                    HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("hotel/hotelList");
        List<Hotel> hotelList = (List<Hotel>) hotelDao.findAll();
        model.addObject("hotelList", hotelList);
        Hotel hotel = new Hotel();
        String filePath = "";
        String fileName = "";
        String newFileName = "";
        if (fileName1.isEmpty()) {
           // return "Please select a file and try again";
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
            UUID uuid = UUID.randomUUID();
            byte[] bytes = fileName1.getBytes();
            String fileName2 = fileName1.getOriginalFilename();
            newFileName = fileName2.replace(fileName2.substring(0, fileName2.lastIndexOf(".")), uuid.toString());
            filePath = uploadPath + File.separator + newFileName;
            Path path = Paths.get(filePath);
            Files.write(path, bytes);
            hotel.setLogo(filePath);
            hotel.setName(name);
            hotel.setAddress(address);
            hotel.setPhone(phone);
            hotel.setEmail(email);
            Star star1 = new Star();
            star1.setId_star(star);
            hotel.setStar(star1);
            hotelDao.save(hotel);
            response.setContentType("text/html");
            //return "success";
              return model;
        } catch (Exception ex) {
            request.setAttribute("message", "There was an error:" + ex.getMessage());
            ex.printStackTrace();
        }
        //return "success";
        return model;
    }

    @GetMapping("/editHotel/{id}")
    public ModelAndView editHotel(
            @PathVariable(value = "id") Long id_hotel) {
        ModelAndView model = new ModelAndView("hotel/editHotel");
        Optional<Hotel> hotel = hotelDao.findById(id_hotel);
        if (hotel.isPresent()) {
            Hotel hotel1 = hotel.get();
            String filePath = hotel1.getLogo().substring(hotel1.getLogo().lastIndexOf("\\"));
            String filePath2 = "upload" + File.separator + filePath;
            model.addObject("filePath2", filePath2);
            model.addObject("hotel1", hotel1);
        }
        List<Star> starList = (List<Star>) starDao.findAll();
        model.addObject("starList", starList);
        return model;
    }

    @PostMapping(value = "*/updateHotel")
    public @ResponseBody
    String updateHotel(
            @RequestParam("id") Long id_hotel,
            @RequestParam("name") String name,
            @RequestParam("address") String address,
            @RequestParam("phone") String phone,
            @RequestParam("email") String email,
            @RequestParam("star") Long id_star,
            @RequestParam("fileName1") MultipartFile fileName1,
            HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("hotel/hotelList");
        String filePath = "";
        String fileName = "";
        String newFileName = "";
        String address1 = null;
        if (fileName1.isEmpty()) {
            return "Please select a file and try again";
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
            Optional<Hotel> hotel2 = hotelDao.findById(id_hotel);
            Optional<Star> star2 = starDao.findById(id_star);
            if (star2.isPresent()) {
                if (hotel2.isPresent()) {
                    Hotel hotel1 = hotel2.get();
                    Star star1 = star2.get();
                    Hotel hotel = new Hotel();
                    Star star = new Star();
                    UUID uuid = UUID.randomUUID();
                    byte[] bytes = fileName1.getBytes();
                    String fileName2 = fileName1.getOriginalFilename();
                    newFileName = fileName2.replace(fileName2.substring(0, fileName2.lastIndexOf(".")), uuid.toString());
                    filePath = uploadPath + File.separator + newFileName;
                    Path path = Paths.get(filePath);
                    Files.write(path, bytes);
                    hotel.setLogo(filePath);
                    hotel.setName(name);
                    hotel.setAddress(address);
                    hotel.setPhone(phone);
                    hotel.setEmail(email);
                    hotel.setData_date(hotel1.getData_date());
                    hotel.setActive(hotel1.getActive());
                    star.setId_star(id_star);
                    hotel.setStar(star);
                    star.setStar(star1.getStar());
                    star.setData_date(star1.getData_date());
                    star.setActive(star1.getActive());
                    hotel.setId_hotel(id_hotel);
                    hotelDao.save(hotel);
                    response.setContentType("text/html");
                    return "success";
                    //  return model;
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message", "There was an error:" + ex.getMessage());
            ex.printStackTrace();
        }
        return "success";


    }

    @PostMapping(value = "/deleteHotel")
    public @ResponseBody
    String deleteHotel(
            @RequestParam("hotelId") Long id_hotel) {
        ModelAndView model = new ModelAndView("hotel/hotelList");
        Optional<Hotel> hotel2 = hotelDao.findById(id_hotel);
            if (hotel2.isPresent()) {
                Hotel hotel1 = hotel2.get();
                Hotel hotel = new Hotel();
                Star star = new Star();
                hotel.setLogo(hotel1.getLogo());
                hotel.setName(hotel1.getName());
                hotel.setAddress(hotel1.getAddress());
                hotel.setPhone(hotel1.getPhone());
                hotel.setEmail(hotel1.getEmail());
                hotel.setData_date(hotel1.getData_date());
                hotel.setActive(0);
                star.setId_star(hotel1.getStar().getId_star());
                star.setStar(hotel1.getStar().getStar());
                star.setData_date(hotel1.getStar().getData_date());
                star.setActive(hotel1.getStar().getActive());
                hotel.setStar(star);
                hotel.setId_hotel(id_hotel);
                hotelDao.save(hotel);
                return "success";
                //  return model;
            }
        return "success";


    }
}
