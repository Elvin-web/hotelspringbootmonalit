package az.elvin.hotel.controller;

import az.elvin.hotel.dao.RoomTypeDao;
import az.elvin.hotel.model.Price;
import az.elvin.hotel.model.RoomStructure;
import az.elvin.hotel.model.RoomType;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class RoomTypeController {
    @Autowired
    private RoomTypeDao roomTypeDao;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private static final String UPLOAD_DIRECTORY = "upload";
    private static final int THRESHOLD_SIZE = 1024 * 1024 * 3;
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 10;
    private static final int REQUEST_SIZE = 1024 * 1024 * 50;

    @GetMapping(value = "/getRoomTypeList")
    public ModelAndView getRoomTypeList() {
        ModelAndView model = new ModelAndView("roomType/roomTypeList");
        List<RoomType> roomTypeList = (List<RoomType>) roomTypeDao.findAll();
        model.addObject("roomTypeList", roomTypeList);
        return model;
    }

    @GetMapping(value = "/newRoomType")
    public ModelAndView newRoomType() {
        ModelAndView model = new ModelAndView("roomType/newRoomType");
        return model;
    }

    @PostMapping(value = "/addRoomType")
    public @ResponseBody
    String addRoomType(@RequestParam("name") String name,
                       @RequestParam("slug") String slug,
                       @RequestParam("shortCode") String shortCode,
                       @RequestParam("description") String description,
                       @RequestParam("baseOccupancy") Long baseOccupancy,
                       @RequestParam("higherOccupancy") Long higherOccupancy,
                       @RequestParam("kidsOccupancy") Long kidsOccupancy,
                       @RequestParam("extraBed") Long extraBed,
                       @RequestParam("basePice") Double basePice,
                       @RequestParam("extraBedPrice") Double extraBedPrice,
                       @RequestParam("fileName1") MultipartFile fileName1,

                       HttpServletRequest request, HttpServletResponse response) {

        ModelAndView model = new ModelAndView("roomType/roomTypeList");
        RoomType roomType = new RoomType();
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
            roomType.setImage(filePath);
            roomType.setSlug(slug);
            roomType.setRoom_type(name);
            roomType.setDescription(description);
            roomType.setShort_code(shortCode);
            RoomStructure roomStructure = new RoomStructure();
            roomStructure.setBase_occupancy(baseOccupancy);
            roomStructure.setHigher_occupancy(higherOccupancy);
            roomStructure.setKids_occupancy(kidsOccupancy);
            roomStructure.setExtra_bed(extraBed);
            roomType.setRoomStructure(roomStructure);
            Price price = new Price();
            price.setBase_price(basePice);
            price.setExtra_bed_price(extraBedPrice);
            roomType.setPrice(price);
            roomTypeDao.save(roomType);
            response.setContentType("text/html");
            return "success";
            //  return model;
        } catch (Exception ex) {
            request.setAttribute("message", "There was an error:" + ex.getMessage());
            ex.printStackTrace();
        }
        return "success";
        // return model;
    }

    @GetMapping("/editRoomType/{id}")
    public ModelAndView editRoomType(
            @PathVariable(value = "id") Long id_room_type) {
        ModelAndView model = new ModelAndView("roomType/editRoomType");
        Optional<RoomType> roomType = roomTypeDao.findById(id_room_type);
        if (roomType.isPresent()) {
            RoomType roomType1 = roomType.get();
            model.addObject("roomType1", roomType1);
            String filePath = roomType1.getImage().substring(roomType1.getImage().lastIndexOf("\\"));
            String filePath2 = "upload" + File.separator + filePath;
            model.addObject("filePath2", filePath2);
        }
        return model;
    }

    @PostMapping(value = "*/updateRoomType")
    public @ResponseBody
    String updateRoomType(@RequestParam("id") Long id_room_type,
                          @RequestParam("name") String name,
                          @RequestParam("slug") String slug,
                          @RequestParam("shortCode") String shortCode,
                          @RequestParam("description") String description,
                          @RequestParam("baseOccupancy") Long baseOccupancy,
                          @RequestParam("higherOccupancy") Long higherOccupancy,
                          @RequestParam("kidsOccupancy") Long kidsOccupancy,
                          @RequestParam("extraBed") Long extraBed,
                          @RequestParam("basePice") Double basePice,
                          @RequestParam("extraBedPrice") Double extraBedPrice,
                          @RequestParam("fileName1") MultipartFile fileName1,

                          HttpServletRequest request, HttpServletResponse response) {

        ModelAndView model = new ModelAndView("roomType/roomTypeList");
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
            Optional<RoomType> roomType2 = roomTypeDao.findById(id_room_type);

                if (roomType2.isPresent()) {
                    RoomType roomType1 = roomType2.get();
                    RoomType roomType = new RoomType();
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
                    roomType.setImage(filePath);
                    roomType.setSlug(slug);
                    roomType.setRoom_type(name);
                    roomType.setDescription(description);
                    roomType.setShort_code(shortCode);
                    roomType.setData_date(roomType1.getData_date());
                    roomType.setActive(roomType1.getActive());
                    RoomStructure roomStructure = new RoomStructure();
                    roomStructure.setBase_occupancy(baseOccupancy);
                    roomStructure.setHigher_occupancy(higherOccupancy);
                    roomStructure.setKids_occupancy(kidsOccupancy);
                    roomStructure.setExtra_bed(extraBed);
                    roomStructure.setId_room_structure(roomType1.getRoomStructure().getId_room_structure());
                    roomStructure.setData_date(roomType1.getRoomStructure().getData_date());
                    roomStructure.setActive(roomType1.getRoomStructure().getActive());
                    roomType.setRoomStructure(roomStructure);
                    Price price = new Price();
                    price.setBase_price(basePice);
                    price.setExtra_bed_price(extraBedPrice);
                    price.setId_price(roomType1.getPrice().getId_price());
                    price.setData_date(roomType1.getPrice().getData_date());
                    price.setActive(roomType1.getPrice().getActive());
                    roomType.setPrice(price);
                    roomType.setId_room_type(id_room_type);
                    roomTypeDao.save(roomType);
                    response.setContentType("text/html");
                    return "success";
                    //  return model;
                }
        } catch (Exception ex) {
            request.setAttribute("message", "There was an error:" + ex.getMessage());
            ex.printStackTrace();
        }
        return "success";
        // return model;
    }
    @PostMapping(value = "/deleteRoomType")
    public @ResponseBody
    String deleteRoomType(@RequestParam("roomTypeId") Long id_room_type) {

        ModelAndView model = new ModelAndView("roomType/roomTypeList");

            Optional<RoomType> roomType2 = roomTypeDao.findById(id_room_type);

            if (roomType2.isPresent()) {
                RoomType roomType1 = roomType2.get();
                RoomType roomType = new RoomType();
                roomType.setImage(roomType1.getImage());
                roomType.setSlug(roomType1.getSlug());
                roomType.setRoom_type(roomType1.getRoom_type());
                roomType.setDescription(roomType1.getDescription());
                roomType.setShort_code(roomType1.getShort_code());
                roomType.setData_date(roomType1.getData_date());
                roomType.setActive(0);
                RoomStructure roomStructure = new RoomStructure();
                roomStructure.setBase_occupancy(roomType1.getRoomStructure().getBase_occupancy());
                roomStructure.setHigher_occupancy(roomType1.getRoomStructure().getHigher_occupancy());
                roomStructure.setKids_occupancy(roomType1.getRoomStructure().getKids_occupancy());
                roomStructure.setExtra_bed(roomType1.getRoomStructure().getExtra_bed());
                roomStructure.setId_room_structure(roomType1.getRoomStructure().getId_room_structure());
                roomStructure.setData_date(roomType1.getRoomStructure().getData_date());
                roomStructure.setActive(roomType1.getRoomStructure().getActive());
                roomType.setRoomStructure(roomStructure);
                Price price = new Price();
                price.setBase_price(roomType1.getPrice().getBase_price());
                price.setExtra_bed_price(roomType1.getPrice().getExtra_bed_price());
                price.setId_price(roomType1.getPrice().getId_price());
                price.setData_date(roomType1.getPrice().getData_date());
                price.setActive(roomType1.getPrice().getActive());
                roomType.setPrice(price);
                roomType.setId_room_type(id_room_type);
                roomTypeDao.save(roomType);
                return "success";
                //  return model;
            }
        return "success";
        // return model;
    }
    @GetMapping("/viewRoomType/{id}")
    public ModelAndView viewRoomType(
            @PathVariable(value = "id") Long id_room_type) {
        ModelAndView model = new ModelAndView("roomType/viewRoomType");
        Optional<RoomType> roomType = roomTypeDao.findById(id_room_type);
        if (roomType.isPresent()) {
            RoomType roomType1 = roomType.get();
            model.addObject("roomType1", roomType1);
            String filePath = roomType1.getImage().substring(roomType1.getImage().lastIndexOf("\\"));
            String filePath2 = "upload" + File.separator + filePath;
            model.addObject("filePath2", filePath2);
        }
        return model;
    }
}
