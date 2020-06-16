package az.elvin.hotel.controller;

import az.elvin.hotel.dao.PositionDao;
import az.elvin.hotel.dao.StaffDao;
import az.elvin.hotel.model.Hotel;
import az.elvin.hotel.model.Position;
import az.elvin.hotel.model.Staff;
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
public class StaffController {
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private PositionDao positionDao;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private static final String UPLOAD_DIRECTORY = "upload";
    private static final int THRESHOLD_SIZE = 1024 * 1024 * 3;
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 10;
    private static final int REQUEST_SIZE = 1024 * 1024 * 50;

    @GetMapping(value = "/getStaffList")
    public ModelAndView getStaffList() {
        ModelAndView model = new ModelAndView("staff/staffList");
        List<Staff> staffList = (List<Staff>) staffDao.findAll();
        model.addObject("staffList", staffList);
        return model;
    }

    @GetMapping(value = "/newStaff")
    public ModelAndView newStaff() {
        ModelAndView model = new ModelAndView("staff/newStaff");
        List<Position> positionList = (List<Position>) positionDao.findAll();
        model.addObject("positionList", positionList);
        return model;
    }

    @PostMapping(value = "/addStaff")
    public @ResponseBody
    ModelAndView addRoomType(@RequestParam("name") String name,
                       @RequestParam("surname") String surname,
                       @RequestParam("email") String email,
                       @RequestParam("username") String username,
                       @RequestParam("password") String password,
                       @RequestParam("address") String address,
                       @RequestParam("dob") String dob,
                       @RequestParam("phone") String phone,
                       @RequestParam("jobStart") String jobStart,
                       @RequestParam("jobEnd") String jobEnd,
                       @RequestParam("salary") Double salary,
                       @RequestParam("ID1") String ID1,
                       @RequestParam("position") Long id_position,
                       @RequestParam("gender") String gender,
                       @RequestParam("fileName1") MultipartFile fileName1,

                       HttpServletRequest request, HttpServletResponse response) {

        ModelAndView model = new ModelAndView("staff/staffList");
        List<Staff> staffList = (List<Staff>) staffDao.findAll();
        model.addObject("staffList", staffList);
        Staff staff = new Staff();
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
            staff.setPicture(filePath);
            staff.setLast_name(surname);
            staff.setFirst_name(name);
            staff.setEmail(email);
            staff.setUsername(username);
            staff.setPassword(password);
            staff.setAddress(address);
            staff.setDob(df.parse(dob));
            staff.setPhone(phone);
            staff.setJob_start(jobStart);
            staff.setJob_end(jobEnd);
            staff.setSalary(salary);
            staff.setID(ID1);
            Position position = new Position();
            position.setId_position(id_position);
            staff.setPosition(position);
            staff.setGender(gender);
            staffDao.save(staff);
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

    @GetMapping("/editStaff/{id}")
    public ModelAndView editStaff(
            @PathVariable(value = "id") Long id_staff) {
        ModelAndView model = new ModelAndView("staff/editStaff");
        Optional<Staff> staff = staffDao.findById(id_staff);
        if (staff.isPresent()) {
            Staff staff1 = staff.get();
            model.addObject("staff1", staff1);
            String filePath = staff1.getPicture().substring(staff1.getPicture().lastIndexOf("\\"));
            String filePath2 = "upload" + File.separator + filePath;
            model.addObject("filePath2", filePath2);
            List<Position> positionList = (List<Position>) positionDao.findAll();
            model.addObject("positionList", positionList);
        }
        return model;
    }

    @PostMapping(value = "*/updateStaff")
    public @ResponseBody
    String updateStaff(@RequestParam("id") Long id_staff,
                       @RequestParam("name") String name,
                       @RequestParam("surname") String surname,
                       @RequestParam("email") String email,
                       @RequestParam("username") String username,
                       @RequestParam("password") String password,
                       @RequestParam("address") String address,
                       @RequestParam("dob") String dob,
                       @RequestParam("phone") String phone,
                       @RequestParam("jobStart") String jobStart,
                       @RequestParam("jobEnd") String jobEnd,
                       @RequestParam("salary") Double salary,
                       @RequestParam("ID1") String ID1,
                       @RequestParam("position") Long id_position,
                       @RequestParam("gender") String gender,
                       @RequestParam("fileName1") MultipartFile fileName1,

                       HttpServletRequest request, HttpServletResponse response) {

        ModelAndView model = new ModelAndView("staff/staffList");

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
            Optional<Staff> staff2 = staffDao.findById(id_staff);
            Optional<Position> position2 = positionDao.findById(id_position);
            if (position2.isPresent()) {
                if (staff2.isPresent()) {
                    Position position1 = position2.get();
                    Staff staff1 = staff2.get();
                    Staff staff = new Staff();
                    Position position = new Position();
                    Hotel hotel = new Hotel();
                    Star star = new Star();
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
                    staff.setPicture(filePath);
                    staff.setLast_name(surname);
                    staff.setFirst_name(name);
                    staff.setEmail(email);
                    staff.setUsername(username);
                    staff.setPassword(password);
                    staff.setAddress(address);
                    staff.setDob(df.parse(dob));
                    staff.setPhone(phone);
                    staff.setJob_start(jobStart);
                    staff.setJob_end(jobEnd);
                    staff.setSalary(salary);
                    staff.setID(ID1);
                    staff.setData_date(staff1.getData_date());
                    staff.setActive(staff1.getActive());
                    position.setId_position(id_position);
                    position.setData_date(staff1.getPosition().getData_date());
                    position.setActive(staff1.getPosition().getActive());
                    position.setPosition_value(staff1.getPosition().getPosition_value());
                    staff.setPosition(position);
                    staff.setGender(gender);
                    hotel.setId_hotel(staff1.getHotel().getId_hotel());
                    hotel.setName(staff1.getHotel().getName());
                    hotel.setAddress(staff1.getHotel().getAddress());
                    hotel.setEmail(staff1.getHotel().getEmail());
                    hotel.setPhone(staff1.getHotel().getPhone());
                    hotel.setLogo(staff1.getHotel().getLogo());
                    hotel.setActive(staff1.getHotel().getActive());
                    hotel.setData_date(staff1.getHotel().getData_date());
                    staff.setId_staff(id_staff);
                    star.setId_star(staff1.getHotel().getStar().getId_star());
                    star.setStar(staff1.getHotel().getStar().getStar());
                    star.setActive(staff1.getHotel().getStar().getActive());
                    star.setData_date(staff1.getHotel().getStar().getData_date());
                    hotel.setStar(star);
                    staff.setHotel(hotel);
                    staffDao.save(staff);
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
        // return model;
    }
    @GetMapping("/viewStaff/{id}")
    public ModelAndView viewStaff(
            @PathVariable(value = "id") Long id_staff) {
        ModelAndView model = new ModelAndView("staff/viewStaff");
        Optional<Staff> staff = staffDao.findById(id_staff);
        if (staff.isPresent()) {
            Staff staff1 = staff.get();
            model.addObject("staff1", staff1);
            String filePath = staff1.getPicture().substring(staff1.getPicture().lastIndexOf("\\"));
            String filePath2 = "upload" + File.separator + filePath;
            model.addObject("filePath2", filePath2);
            List<Position> positionList = (List<Position>) positionDao.findAll();
            model.addObject("positionList", positionList);
        }
        return model;
    }

    @PostMapping(value = "/deleteStaff")
    public @ResponseBody
    String deleteStaff(@RequestParam("staffId") Long id_staff) {

        ModelAndView model = new ModelAndView("staff/staffList");

            Optional<Staff> staff2 = staffDao.findById(id_staff);
                if (staff2.isPresent()) {
                    Staff staff1 = staff2.get();
                    Staff staff = new Staff();
                    Position position = new Position();
                    Hotel hotel = new Hotel();
                    Star star = new Star();
                    staff.setId_staff(id_staff);
                    staff.setPicture(staff1.getPicture());
                    staff.setLast_name(staff1.getLast_name());
                    staff.setFirst_name(staff1.getFirst_name());
                    staff.setEmail(staff1.getEmail());
                    staff.setUsername(staff1.getUsername());
                    staff.setPassword(staff1.getPassword());
                    staff.setAddress(staff1.getAddress());
                    staff.setDob(staff1.getDob());
                    staff.setPhone(staff1.getPhone());
                    staff.setJob_start(staff1.getJob_start());
                    staff.setJob_end(staff1.getJob_end());
                    staff.setSalary(staff1.getSalary());
                    staff.setID(staff1.getID());
                    staff.setData_date(staff1.getData_date());
                    staff.setActive(0);
                    staff.setGender(staff1.getGender());
                    position.setId_position(staff1.getPosition().getId_position());
                    position.setData_date(staff1.getPosition().getData_date());
                    position.setActive(staff1.getPosition().getActive());
                    position.setPosition_value(staff1.getPosition().getPosition_value());
                    staff.setPosition(position);
                    hotel.setId_hotel(staff1.getHotel().getId_hotel());
                    hotel.setName(staff1.getHotel().getName());
                    hotel.setAddress(staff1.getHotel().getAddress());
                    hotel.setEmail(staff1.getHotel().getEmail());
                    hotel.setPhone(staff1.getHotel().getPhone());
                    hotel.setLogo(staff1.getHotel().getLogo());
                    hotel.setActive(staff1.getHotel().getActive());
                    hotel.setData_date(staff1.getHotel().getData_date());
                    star.setId_star(staff1.getHotel().getStar().getId_star());
                    star.setStar(staff1.getHotel().getStar().getStar());
                    star.setActive(staff1.getHotel().getStar().getActive());
                    star.setData_date(staff1.getHotel().getStar().getData_date());
                    hotel.setStar(star);
                    staff.setHotel(hotel);
                    staffDao.save(staff);
                    return "success";
                    //  return model;
                }
        return "success";
        // return model;
    }
}
