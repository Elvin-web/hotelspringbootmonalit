package az.elvin.hotel.controller;

import az.elvin.hotel.dao.AmenitiesDao;
import az.elvin.hotel.model.Amenities;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class AmenitiesController {
    @Autowired
    private AmenitiesDao amenitiesDao;
    private static final String UPLOAD_DIRECTORY = "upload";
    private static final int THRESHOLD_SIZE = 1024 * 1024 * 3;
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 10;
    private static final int REQUEST_SIZE = 1024 * 1024 * 50;

    @GetMapping(value = "/getAmenitiesList")
    public ModelAndView getAmenitiesList() {
        ModelAndView model = new ModelAndView("amenities/amenitiesList");
        List<Amenities> amenitiesList = (List<Amenities>) amenitiesDao.findAll();
        model.addObject("amenitiesList", amenitiesList);
        return model;
    }

    @GetMapping(value = "/newAmenities")
    public ModelAndView newAmenities() {
        ModelAndView model = new ModelAndView("amenities/newAmenities");
        return model;
    }

    @PostMapping(value = "/addAmenities")
    public @ResponseBody
    ModelAndView addAmenities(@RequestParam("name") String name,
                        @RequestParam("action1") Integer action1,
                        @RequestParam("description") String description,
                        @RequestParam("fileName1") MultipartFile fileName1,
                        HttpServletRequest request, HttpServletResponse response) {

        ModelAndView model = new ModelAndView("amenities/amenitiesList");
        List<Amenities> amenitiesList = (List<Amenities>) amenitiesDao.findAll();
        model.addObject("amenitiesList", amenitiesList);
        Amenities amenities = new Amenities();
        String filePath = "";
        String fileName = "";
        String newFileName = "";
        String address1 = null;
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
            UUID uuid = UUID.randomUUID();
            byte[] bytes = fileName1.getBytes();
            String fileName2 = fileName1.getOriginalFilename();
            System.out.println("fileName2=" + fileName2);
            newFileName = fileName2.replace(fileName2.substring(0, fileName2.lastIndexOf(".")), uuid.toString());
            System.out.println("newFileName=" + newFileName);
            filePath = uploadPath + File.separator + newFileName;
            Path path = Paths.get(filePath);
            Files.write(path, bytes);
            System.out.println("filePath=" + filePath);
            amenities.setImage(filePath);
            amenities.setName(name);
            amenities.setDescription(description);
            amenities.setAction(action1);
            amenitiesDao.save(amenities);
            response.setContentType("text/html");
            // return "success";
             return model;
        } catch (Exception ex) {
            request.setAttribute("message", "There was an error:" + ex.getMessage());
            ex.printStackTrace();
        }
        // return "success";
         return model;
    }

    @GetMapping("/editAmenities/{id}")
    public ModelAndView editAmenities(
            @PathVariable(value = "id") Long id_amenities) {
        ModelAndView model = new ModelAndView("amenities/editAmenities");
        Optional<Amenities> amenities = amenitiesDao.findById(id_amenities);
        if (amenities.isPresent()) {
            Amenities amenities1 = amenities.get();
            model.addObject("amenities1", amenities1);
            String filePath = amenities1.getImage().substring(amenities1.getImage().lastIndexOf("\\"));
            String filePath2 = "upload" + File.separator + filePath;
            model.addObject("filePath2", filePath2);
        }
        return model;
    }

    @GetMapping("/viewAmenities/{id}")
    public ModelAndView viewAmenities(
            @PathVariable(value = "id") Long id_amenities) {
        ModelAndView model = new ModelAndView("amenities/viewAmenities");
        Optional<Amenities> amenities = amenitiesDao.findById(id_amenities);
        if (amenities.isPresent()) {
            Amenities amenities1 = amenities.get();
            model.addObject("amenities1", amenities1);
            String filePath = amenities1.getImage().substring(amenities1.getImage().lastIndexOf("\\"));
            String filePath2 = "upload" + File.separator + filePath;
            model.addObject("filePath2", filePath2);
        }
        return model;
    }

    @PostMapping(value = "*/updateAmenities")
    public @ResponseBody
    String updateAmenities(@RequestParam("id") Long id_amenities,
                           @RequestParam("name") String name,
                           @RequestParam("action1") Integer action1,
                           @RequestParam("description") String description,
                           @RequestParam("fileName1") MultipartFile fileName1,
                           HttpServletRequest request, HttpServletResponse response) {

        ModelAndView model = new ModelAndView("amenities/amenitiesList");

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
            Optional<Amenities> amenities2 = amenitiesDao.findById(id_amenities);

            if (amenities2.isPresent()) {
                Amenities amenities1 = amenities2.get();
                Amenities amenities = new Amenities();
            UUID uuid = UUID.randomUUID();
            byte[] bytes = fileName1.getBytes();
            String fileName2 = fileName1.getOriginalFilename();
            System.out.println("fileName2=" + fileName2);
            newFileName = fileName2.replace(fileName2.substring(0, fileName2.lastIndexOf(".")), uuid.toString());
            System.out.println("newFileName=" + newFileName);
            filePath = uploadPath + File.separator + newFileName;
            Path path = Paths.get(filePath);
            Files.write(path, bytes);
            System.out.println("filePath=" + filePath);
            amenities.setImage(filePath);
            amenities.setName(name);
            amenities.setDescription(description);
            amenities.setAction(action1);
            amenities.setId_amenities(id_amenities);
            amenities.setActive(amenities1.getActive());
            amenities.setData_date(amenities1.getData_date());
            amenitiesDao.save(amenities);
            response.setContentType("text/html");
            return "success";
            //  return model;'
        }
        } catch (Exception ex) {
            request.setAttribute("message", "There was an error:" + ex.getMessage());
            ex.printStackTrace();
        }
        return "success";
        // return model;
    }
    @PostMapping(value = "/deleteAmenities")
    public @ResponseBody
    String updateAmenities(@RequestParam("amenitiesId") Long id_amenities) {

        ModelAndView model = new ModelAndView("amenities/amenitiesList");


            Optional<Amenities> amenities2 = amenitiesDao.findById(id_amenities);

            if (amenities2.isPresent()) {
                Amenities amenities1 = amenities2.get();
                Amenities amenities = new Amenities();
                amenities.setImage(amenities1.getImage());
                amenities.setName(amenities1.getName());
                amenities.setDescription(amenities1.getDescription());
                amenities.setAction(amenities1.getAction());
                amenities.setId_amenities(id_amenities);
                amenities.setActive(0);
                amenities.setData_date(amenities1.getData_date());
                amenitiesDao.save(amenities);
                return "success";
                //  return model;'
            }
        return "success";
        // return model;
    }
}
