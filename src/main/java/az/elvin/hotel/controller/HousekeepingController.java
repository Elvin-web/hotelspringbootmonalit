package az.elvin.hotel.controller;

import az.elvin.hotel.dao.HousekeepingDao;
import az.elvin.hotel.dao.HousekeepingStatusDao;
import az.elvin.hotel.dao.RoomDao;
import az.elvin.hotel.dao.StaffDao;
import az.elvin.hotel.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class HousekeepingController {
    @Autowired
    private HousekeepingDao housekeepingDao;
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private HousekeepingStatusDao housekeepingStatusDao;
    @Autowired
    private RoomDao roomDao;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @GetMapping("/housekeepingList/{id}")
    public ModelAndView housekeepingList(
            @PathVariable(value = "id") Long id_room) {
        ModelAndView model = new ModelAndView("housekeeping/housekeepingList");
        Room room = new Room();
        room.setId_room(id_room);
        List<Housekeeping> housekeepingList = housekeepingDao.findHousekeepingByRoom(room);
        model.addObject("housekeepingList", housekeepingList);
        model.addObject("id_room", id_room);
        return model;
    }

    @GetMapping("/viewHousekeeping/{id}")
    public ModelAndView viewHousekeeping(
            @PathVariable(value = "id") Long id_housekeeping) {
        ModelAndView model = new ModelAndView("housekeeping/viewHousekeeping");
        Optional<Housekeeping> housekeepingList = housekeepingDao.findById(id_housekeeping);
        if (housekeepingList.isPresent()) {
            Housekeeping housekeeping = housekeepingList.get();
            model.addObject("housekeeping", housekeeping);
        }
        return model;
    }

    @GetMapping("/newHousekeeping/{id}")
    public ModelAndView newHousekeeping(
            @PathVariable(value = "id") Long id_room) {
        ModelAndView model = new ModelAndView("housekeeping/newHousekeeping");
        List<Housekeeping> housekeepingList = (List<Housekeeping>) housekeepingDao.findAll();
        model.addObject("housekeepingList", housekeepingList);
        model.addObject("id_room", id_room);
        List<HousekeepingStatus> housekeepingStatusList = (List<HousekeepingStatus>) housekeepingStatusDao.findAll();
        model.addObject("housekeepingStatusList", housekeepingStatusList);
        List<Staff> staffList = (List<Staff>) staffDao.findAll();
        model.addObject("staffList", staffList);
        return model;
    }

    @PostMapping(value = "*/addHousekeeping")
    public @ResponseBody
    String addHousekeeping(
            @RequestParam("housekeepingStatus") Long housekeepingStatus,
            @RequestParam("roomId") Long roomId,
            @RequestParam("remark") String remark,
            @RequestParam("cleanDate") String cleanDate,
            @RequestParam("staff") Long staff) {
        try {
            Housekeeping housekeeping = new Housekeeping();
            housekeeping.setRemark(remark);
            housekeeping.setClean_date(df.parse(cleanDate));
            Room room = new Room();
            room.setId_room(roomId);
            housekeeping.setRoom(room);
            Staff staff1 = new Staff();
            staff1.setId_staff(staff);
            housekeeping.setStaff(staff1);
            HousekeepingStatus housekeepingStatus1 = new HousekeepingStatus();
            housekeepingStatus1.setId_housekeeping_status(housekeepingStatus);
            housekeeping.setHousekeepingStatus(housekeepingStatus1);
            housekeepingDao.save(housekeeping);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @GetMapping("/editHousekeeping/{id}")
    public ModelAndView editHousekeeping(
            @PathVariable(value = "id") Long id_housekeeping) {
        ModelAndView model = new ModelAndView("housekeeping/editHousekeeping");
        Optional<Housekeeping> housekeepingList = housekeepingDao.findById(id_housekeeping);
        if (housekeepingList.isPresent()) {
            Housekeeping housekeeping = housekeepingList.get();
            model.addObject("housekeeping", housekeeping);
        }

        List<HousekeepingStatus> housekeepingStatusList = (List<HousekeepingStatus>) housekeepingStatusDao.findAll();
        model.addObject("housekeepingStatusList", housekeepingStatusList);
        List<Staff> staffList = (List<Staff>) staffDao.findAll();
        model.addObject("staffList", staffList);
        return model;
    }

    @PostMapping(value = "*/updateHousekeeping")
    public @ResponseBody
    String updateHousekeeping(
            @RequestParam("housekeepingStatus") Long id_housekeeping_status,
            @RequestParam("remark") String remark,
            //  @RequestParam("room") Long number,
            @RequestParam("staff") Long id_staff,
            @RequestParam("roomId") Long id_room,
            @RequestParam("housekeepingId") Long id_housekeeping,
            @RequestParam("cleanDate") String clean_date) {
        try {
            Optional<Housekeeping> housekeeping2 = housekeepingDao.findById(id_housekeeping);
            Optional<HousekeepingStatus> housekeepingStatus2 = housekeepingStatusDao.findById(id_housekeeping_status);
            Optional<Room> room2 = roomDao.findById(id_room);
            System.out.println("id_room=" + id_room);
            Optional<Staff> staff2 = staffDao.findById(id_staff);
            if (room2.isPresent()) {
                if (staff2.isPresent()) {
                    if (housekeepingStatus2.isPresent()) {
                        if (housekeeping2.isPresent()) {
                            Staff staff1 = staff2.get();
                            Room room1 = room2.get();
                            Housekeeping housekeeping1 = housekeeping2.get();
                            HousekeepingStatus housekeepingStatus1 = housekeepingStatus2.get();
                            Housekeeping housekeeping = new Housekeeping();
                            Staff staff = new Staff();
                            staff.setId_staff(id_staff);
                            staff.setFirst_name(staff1.getFirst_name());
                            staff.setLast_name(staff1.getLast_name());
                            staff.setAddress(staff1.getAddress());
                            staff.setPicture(staff1.getPicture());
                            staff.setEmail(staff1.getEmail());
                            staff.setGender(staff1.getGender());
                            staff.setUsername(staff1.getUsername());
                            staff.setPassword(staff1.getPassword());
                            staff.setJob_start(staff1.getJob_start());
                            staff.setJob_end(staff1.getJob_end());
                            staff.setSalary(staff1.getSalary());
                            staff.setPhone(staff1.getPhone());
                            staff.setDob(staff1.getDob());
                            staff.setID(staff1.getID());
                            staff.setActive(staff1.getActive());
                            staff.setData_date(staff1.getData_date());
                            Position position = new Position();
                            position.setId_position(staff1.getPosition().getId_position());
                            position.setPosition_value(staff1.getPosition().getPosition_value());
                            position.setActive(staff1.getPosition().getActive());
                            position.setData_date(staff1.getPosition().getData_date());
                            staff.setPosition(position);
                            Hotel hotel = new Hotel();
                            hotel.setId_hotel(staff1.getHotel().getId_hotel());
                            hotel.setName(staff1.getHotel().getName());
                            hotel.setAddress(staff1.getHotel().getAddress());
                            hotel.setPhone(staff1.getHotel().getPhone());
                            hotel.setEmail(staff1.getHotel().getEmail());
                            hotel.setLogo(staff1.getHotel().getLogo());
                            hotel.setActive(staff1.getHotel().getActive());
                            hotel.setData_date(staff1.getHotel().getData_date());
                            staff.setHotel(hotel);

                            Star star = new Star();
                            star.setId_star(staff1.getHotel().getStar().getId_star());
                            star.setActive(staff1.getHotel().getStar().getActive());
                            star.setData_date(staff1.getHotel().getStar().getData_date());
                            hotel.setStar(star);
                            staff.setHotel(hotel);
                            housekeeping.setStaff(staff);


                            Room room = new Room();
                            room.setId_room(id_room);
                            // room.setNumber(number);
                            room.setNumber(room1.getNumber());
                            room.setActive(room1.getActive());
                            room.setData_date(room1.getData_date());
                            Floor floor = new Floor();
                            floor.setId_floor(room1.getFloor().getId_floor());
                            floor.setAction(room1.getFloor().getAction());
                            floor.setActive(room1.getFloor().getActive());
                            floor.setData_date(room1.getFloor().getData_date());
                            floor.setFloor_number(room1.getFloor().getFloor_number());
                            floor.setDescription(room1.getFloor().getDescription());
                            floor.setName(room1.getFloor().getName());
                            room.setFloor(floor);
                            RoomStatus roomStatus = new RoomStatus();
                            roomStatus.setId_room_status(room1.getRoomStatus().getId_room_status());
                            roomStatus.setRoom_status(room1.getRoomStatus().getRoom_status());
                            roomStatus.setActive(room1.getRoomStatus().getActive());
                            roomStatus.setData_date(room1.getRoomStatus().getData_date());
                            room.setRoomStatus(roomStatus);
                            RoomType roomType = new RoomType();
                            roomType.setId_room_type(room1.getRoomType().getId_room_type());
                            roomType.setRoom_type(room1.getRoomType().getRoom_type());
                            roomType.setSlug(room1.getRoomType().getSlug());
                            roomType.setShort_code(room1.getRoomType().getShort_code());
                            roomType.setDescription(room1.getRoomType().getDescription());
                            roomType.setImage(room1.getRoomType().getImage());
                            roomType.setActive(room1.getRoomType().getActive());
                            roomType.setData_date(room1.getRoomType().getData_date());
                            RoomStructure roomStructure = new RoomStructure();
                            roomStructure.setId_room_structure(room1.getRoomType().getRoomStructure().getId_room_structure());
                            roomStructure.setBase_occupancy(room1.getRoomType().getRoomStructure().getBase_occupancy());
                            roomStructure.setKids_occupancy(room1.getRoomType().getRoomStructure().getKids_occupancy());
                            roomStructure.setHigher_occupancy(room1.getRoomType().getRoomStructure().getHigher_occupancy());
                            roomStructure.setExtra_bed(room1.getRoomType().getRoomStructure().getExtra_bed());
                            roomStructure.setActive(room1.getRoomType().getRoomStructure().getActive());
                            roomStructure.setData_date(room1.getRoomType().getRoomStructure().getData_date());
                            roomType.setRoomStructure(roomStructure);
                            Price price = new Price();
                            price.setId_price(room1.getRoomType().getPrice().getId_price());
                            price.setBase_price(room1.getRoomType().getPrice().getBase_price());
                            price.setExtra_bed_price(room1.getRoomType().getPrice().getExtra_bed_price());
                            price.setActive(room1.getRoomType().getPrice().getActive());
                            price.setData_date(room1.getRoomType().getPrice().getData_date());
                            roomType.setPrice(price);
                            room.setRoomType(roomType);
                            housekeeping.setRoom(room);

                            HousekeepingStatus housekeepingStatus = new HousekeepingStatus();
                            housekeepingStatus.setId_housekeeping_status(id_housekeeping_status);
                            housekeepingStatus.setName(housekeepingStatus1.getName());
                            housekeepingStatus.setDescription(housekeepingStatus1.getDescription());
                            housekeepingStatus.setAction(housekeepingStatus1.getAction());
                            housekeepingStatus.setActive(housekeepingStatus1.getActive());
                            housekeepingStatus.setData_date(housekeepingStatus1.getData_date());
                            housekeeping.setHousekeepingStatus(housekeepingStatus);

                            housekeeping.setClean_date(df.parse(clean_date));
                            housekeeping.setRemark(remark);
                            housekeeping.setActive(housekeeping1.getActive());
                            housekeeping.setData_date(housekeeping1.getData_date());
                            housekeeping.setId_housekeeping(id_housekeeping);
                            housekeepingDao.save(housekeeping);
                            return "success";
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @PostMapping(value = "*/deleteHousekeeping")
    public @ResponseBody
    String deleteHousekeeping(
            @RequestParam("housekeepingId") Long id_housekeeping) {
        try {
            Optional<Housekeeping> housekeeping2 = housekeepingDao.findById(id_housekeeping);
                        if (housekeeping2.isPresent()) {
                            Housekeeping housekeeping1 = housekeeping2.get();
                            Housekeeping housekeeping = new Housekeeping();
                            Staff staff = new Staff();
                            staff.setId_staff(housekeeping1.getStaff().getId_staff());
                            staff.setFirst_name(housekeeping1.getStaff().getFirst_name());
                            staff.setLast_name(housekeeping1.getStaff().getLast_name());
                            staff.setAddress(housekeeping1.getStaff().getAddress());
                            staff.setPicture(housekeeping1.getStaff().getPicture());
                            staff.setEmail(housekeeping1.getStaff().getEmail());
                            staff.setGender(housekeeping1.getStaff().getGender());
                            staff.setUsername(housekeeping1.getStaff().getUsername());
                            staff.setPassword(housekeeping1.getStaff().getPassword());
                            staff.setJob_start(housekeeping1.getStaff().getJob_start());
                            staff.setJob_end(housekeeping1.getStaff().getJob_end());
                            staff.setSalary(housekeeping1.getStaff().getSalary());
                            staff.setPhone(housekeeping1.getStaff().getPhone());
                            staff.setDob(housekeeping1.getStaff().getDob());
                            staff.setID(housekeeping1.getStaff().getID());
                            staff.setActive(housekeeping1.getStaff().getActive());
                            staff.setData_date(housekeeping1.getStaff().getData_date());
                            Position position = new Position();
                            position.setId_position(housekeeping1.getStaff().getPosition().getId_position());
                            position.setPosition_value(housekeeping1.getStaff().getPosition().getPosition_value());
                            position.setActive(housekeeping1.getStaff().getPosition().getActive());
                            position.setData_date(housekeeping1.getStaff().getPosition().getData_date());
                            staff.setPosition(position);
                            Hotel hotel = new Hotel();
                            hotel.setId_hotel(housekeeping1.getStaff().getHotel().getId_hotel());
                            hotel.setName(housekeeping1.getStaff().getHotel().getName());
                            hotel.setAddress(housekeeping1.getStaff().getHotel().getAddress());
                            hotel.setPhone(housekeeping1.getStaff().getHotel().getPhone());
                            hotel.setEmail(housekeeping1.getStaff().getHotel().getEmail());
                            hotel.setLogo(housekeeping1.getStaff().getHotel().getLogo());
                            hotel.setActive(housekeeping1.getStaff().getHotel().getActive());
                            hotel.setData_date(housekeeping1.getStaff().getHotel().getData_date());
                            staff.setHotel(hotel);

                            Star star = new Star();
                            star.setId_star(housekeeping1.getStaff().getHotel().getStar().getId_star());
                            star.setActive(housekeeping1.getStaff().getHotel().getStar().getActive());
                            star.setData_date(housekeeping1.getStaff().getHotel().getStar().getData_date());
                            hotel.setStar(star);
                            staff.setHotel(hotel);
                            housekeeping.setStaff(staff);


                            Room room = new Room();
                            room.setId_room(housekeeping1.getRoom().getId_room());
                            // room.setNumber(number);
                            room.setNumber(housekeeping1.getRoom().getNumber());
                            room.setActive(housekeeping1.getRoom().getActive());
                            room.setData_date(housekeeping1.getRoom().getData_date());
                            Floor floor = new Floor();
                            floor.setId_floor(housekeeping1.getRoom().getFloor().getId_floor());
                            floor.setAction(housekeeping1.getRoom().getFloor().getAction());
                            floor.setActive(housekeeping1.getRoom().getFloor().getActive());
                            floor.setData_date(housekeeping1.getRoom().getFloor().getData_date());
                            floor.setFloor_number(housekeeping1.getRoom().getFloor().getFloor_number());
                            floor.setDescription(housekeeping1.getRoom().getFloor().getDescription());
                            floor.setName(housekeeping1.getRoom().getFloor().getName());
                            room.setFloor(floor);
                            RoomStatus roomStatus = new RoomStatus();
                            roomStatus.setId_room_status(housekeeping1.getRoom().getRoomStatus().getId_room_status());
                            roomStatus.setRoom_status(housekeeping1.getRoom().getRoomStatus().getRoom_status());
                            roomStatus.setActive(housekeeping1.getRoom().getRoomStatus().getActive());
                            roomStatus.setData_date(housekeeping1.getRoom().getRoomStatus().getData_date());
                            room.setRoomStatus(roomStatus);
                            RoomType roomType = new RoomType();
                            roomType.setId_room_type(housekeeping1.getRoom().getRoomType().getId_room_type());
                            roomType.setRoom_type(housekeeping1.getRoom().getRoomType().getRoom_type());
                            roomType.setSlug(housekeeping1.getRoom().getRoomType().getSlug());
                            roomType.setShort_code(housekeeping1.getRoom().getRoomType().getShort_code());
                            roomType.setDescription(housekeeping1.getRoom().getRoomType().getDescription());
                            roomType.setImage(housekeeping1.getRoom().getRoomType().getImage());
                            roomType.setActive(housekeeping1.getRoom().getRoomType().getActive());
                            roomType.setData_date(housekeeping1.getRoom().getRoomType().getData_date());
                            RoomStructure roomStructure = new RoomStructure();
                            roomStructure.setId_room_structure(housekeeping1.getRoom().getRoomType().getRoomStructure().getId_room_structure());
                            roomStructure.setBase_occupancy(housekeeping1.getRoom().getRoomType().getRoomStructure().getBase_occupancy());
                            roomStructure.setKids_occupancy(housekeeping1.getRoom().getRoomType().getRoomStructure().getKids_occupancy());
                            roomStructure.setHigher_occupancy(housekeeping1.getRoom().getRoomType().getRoomStructure().getHigher_occupancy());
                            roomStructure.setExtra_bed(housekeeping1.getRoom().getRoomType().getRoomStructure().getExtra_bed());
                            roomStructure.setActive(housekeeping1.getRoom().getRoomType().getRoomStructure().getActive());
                            roomStructure.setData_date(housekeeping1.getRoom().getRoomType().getRoomStructure().getData_date());
                            roomType.setRoomStructure(roomStructure);
                            Price price = new Price();
                            price.setId_price(housekeeping1.getRoom().getRoomType().getPrice().getId_price());
                            price.setBase_price(housekeeping1.getRoom().getRoomType().getPrice().getBase_price());
                            price.setExtra_bed_price(housekeeping1.getRoom().getRoomType().getPrice().getExtra_bed_price());
                            price.setActive(housekeeping1.getRoom().getRoomType().getPrice().getActive());
                            price.setData_date(housekeeping1.getRoom().getRoomType().getPrice().getData_date());
                            roomType.setPrice(price);
                            room.setRoomType(roomType);
                            housekeeping.setRoom(room);

                            HousekeepingStatus housekeepingStatus = new HousekeepingStatus();
                            housekeepingStatus.setId_housekeeping_status(housekeeping1.getHousekeepingStatus().getId_housekeeping_status());
                            housekeepingStatus.setName(housekeeping1.getHousekeepingStatus().getName());
                            housekeepingStatus.setDescription(housekeeping1.getHousekeepingStatus().getDescription());
                            housekeepingStatus.setAction(housekeeping1.getHousekeepingStatus().getAction());
                            housekeepingStatus.setActive(housekeeping1.getHousekeepingStatus().getActive());
                            housekeepingStatus.setData_date(housekeeping1.getHousekeepingStatus().getData_date());
                            housekeeping.setHousekeepingStatus(housekeepingStatus);

                            housekeeping.setClean_date(housekeeping1.getClean_date());
                            housekeeping.setRemark(housekeeping1.getRemark());
                            housekeeping.setActive(0);
                            housekeeping.setData_date(housekeeping1.getData_date());
                            housekeeping.setId_housekeeping(id_housekeeping);
                            housekeepingDao.save(housekeeping);
                            return "success";
                        }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
}
