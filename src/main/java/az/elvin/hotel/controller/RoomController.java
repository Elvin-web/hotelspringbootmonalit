package az.elvin.hotel.controller;

import az.elvin.hotel.dao.*;
import az.elvin.hotel.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class RoomController {
    @Autowired
    private RoomDao roomDao;
    @Autowired
    private RoomTypeDao roomTypeDao;
    @Autowired
    private AmenitiesDao amenitiesDao;
    @Autowired
    private FloorDao floorDao;
    @Autowired
    private Room_AmenitiesDao room_amenitiesDao;
    @Autowired
    private Room_ReservationDao room_reservationDao;
    @Autowired
    private ReservationDao reservationDao;

    @GetMapping(value = "/getRoomList")
    public ModelAndView getRoomList() {
        ModelAndView model = new ModelAndView("room/roomList");
        List<Room> roomList = (List<Room>) roomDao.findAll();
        model.addObject("roomList", roomList);
        long roomAcount = roomDao.roomAcount();
        model.addObject("roomAcount", roomAcount);
        long floorAcount = floorDao.countFloor();
        model.addObject("floorAcount", floorAcount);
        long roomTypeAcount = roomTypeDao.roomTypeAcount();
        model.addObject("roomTypeAcount", roomTypeAcount);
        Long reservationTodayAcount = room_reservationDao.reservationTodayAcount();
        model.addObject("reservationTodayAcount", reservationTodayAcount);
        return model;


    }

    @GetMapping(value = "/newRoom")
    public ModelAndView newRoom() {
        ModelAndView model = new ModelAndView("room/newRoom");
        List<RoomType> roomTypeList = (List<RoomType>) roomTypeDao.findAll();
        model.addObject("roomTypeList", roomTypeList);
        List<Amenities> amenitiesList = (List<Amenities>) amenitiesDao.findAll();
        model.addObject("amenitiesList", amenitiesList);
        List<Floor> floorList = (List<Floor>) floorDao.findAll();
        model.addObject("floorList", floorList);
        return model;
    }

    @PostMapping(value = "/addRoom")
    public @ResponseBody
    String addRoom(@RequestParam("roomType") Long id_room_type,
                   @RequestParam("room") String room,
                   @RequestParam("floor") Long id_floor,
                   @RequestParam("amenities[]") String[] amenities,
                   @RequestParam("amenitiesUnselect[]") String[] amenitiesUnselect) {

        Room room1 = new Room();
        Optional<RoomType> roomType2 = roomTypeDao.findById(id_room_type);
        Optional<Floor> floor2 = floorDao.findById(id_floor);
        if (roomType2.isPresent()) {
            if (floor2.isPresent()) {
                RoomType roomType1 = roomType2.get();
                Floor floor1 = floor2.get();
                RoomType roomType = new RoomType();
                roomType.setId_room_type(roomType1.getId_room_type());
                roomType.setRoom_type(roomType1.getRoom_type());
                roomType.setSlug(roomType1.getSlug());
                roomType.setShort_code(roomType1.getShort_code());
                roomType.setDescription(roomType1.getDescription());
                roomType.setImage(roomType1.getImage());
                roomType.setData_date(roomType1.getData_date());
                roomType.setActive(roomType1.getActive());
                RoomStructure roomStructure = new RoomStructure();
                roomStructure.setId_room_structure(roomType1.getRoomStructure().getId_room_structure());
                roomStructure.setBase_occupancy(roomType1.getRoomStructure().getBase_occupancy());
                roomStructure.setHigher_occupancy(roomType1.getRoomStructure().getHigher_occupancy());
                roomStructure.setKids_occupancy(roomType1.getRoomStructure().getKids_occupancy());
                roomStructure.setExtra_bed(roomType1.getRoomStructure().getExtra_bed());
                roomStructure.setActive(roomType1.getRoomStructure().getActive());
                roomStructure.setData_date(roomType1.getRoomStructure().getData_date());
                roomType.setRoomStructure(roomStructure);
                Price price = new Price();
                price.setId_price(roomType1.getPrice().getId_price());
                price.setBase_price(roomType1.getPrice().getBase_price());
                price.setExtra_bed_price(roomType1.getPrice().getExtra_bed_price());
                price.setActive(roomType1.getPrice().getActive());
                price.setData_date(roomType1.getPrice().getData_date());
                roomType.setPrice(price);
                room1.setRoomType(roomType);
                Floor floor = new Floor();
                floor.setId_floor(floor1.getId_floor());
                floor.setFloor_number(floor1.getFloor_number());
                floor.setName(floor1.getName());
                floor.setDescription(floor1.getDescription());
                floor.setAction(floor1.getAction());
                floor.setData_date(floor1.getData_date());
                floor.setActive(floor1.getActive());
                room1.setFloor(floor);
                room1.setNumber(Long.parseLong(room));
                roomDao.save(room1);
            }
        }
        if (amenities != null) {
            for (String amenities2 : amenities) {
                Long id_amenities = Long.valueOf(amenities2);
                Optional<Amenities> amenities3 = amenitiesDao.findById(id_amenities);
                if (amenities3.isPresent()) {
                    Amenities amenities4 = amenities3.get();
                    Room_Amenities room_amenities = new Room_Amenities();
                    Amenities amenities5 = new Amenities();
                    amenities5.setId_amenities(amenities4.getId_amenities());
                    amenities5.setName(amenities4.getName());
                    amenities5.setDescription(amenities4.getDescription());
                    amenities5.setAction(amenities4.getAction());
                    amenities5.setActive(amenities4.getActive());
                    amenities5.setData_date(amenities4.getData_date());
                    amenities5.setImage(amenities4.getImage());
                    System.out.println("Long.parseLong(amenities2)=" + amenities2);
                    room_amenities.setAmenities(amenities5);
                    room_amenities.setRoom(room1);
                    room_amenities.setActive(1);
                    System.out.println("room_amenities1" + room_amenities);
                    room_amenitiesDao.save(room_amenities);

                }
            }//return "success";
        }
        if (amenitiesUnselect != null) {
            for (String amenities3 : amenitiesUnselect) {
                Long id_amenities = Long.valueOf(amenities3);
                Optional<Amenities> amenities6 = amenitiesDao.findById(id_amenities);
                if (amenities6.isPresent()) {
                    Amenities amenities4 = amenities6.get();
                    Room_Amenities room_amenities1 = new Room_Amenities();
                    Amenities amenities5 = new Amenities();
                    amenities5.setId_amenities(amenities4.getId_amenities());
                    amenities5.setName(amenities4.getName());
                    amenities5.setDescription(amenities4.getDescription());
                    amenities5.setAction(amenities4.getAction());
                    amenities5.setActive(amenities4.getActive());
                    amenities5.setData_date(amenities4.getData_date());
                    amenities5.setImage(amenities4.getImage());
                    System.out.println("Long.parseLong(amenities3)=" + id_amenities);
                    room_amenities1.setAmenities(amenities5);
                    room_amenities1.setRoom(room1);
                    room_amenities1.setActive(0);
                    System.out.println("room_amenities2" + room_amenities1);
                    room_amenitiesDao.save(room_amenities1);
                    // return "success";
                }
            }
        }
        return "success";
    }

    @GetMapping("/editRoom/{id}")
    public ModelAndView editRoom(@PathVariable(value = "id") Long id_room) {
        ModelAndView model = new ModelAndView("room/editRoom");
        List<RoomType> roomTypeList = (List<RoomType>) roomTypeDao.findAll();
        model.addObject("roomTypeList", roomTypeList);
        List<Amenities> amenitiesList = (List<Amenities>) amenitiesDao.findAll();
        model.addObject("amenitiesList", amenitiesList);
        List<Floor> floorList = (List<Floor>) floorDao.findAll();
        model.addObject("floorList", floorList);
        Optional<Room> room = roomDao.findById(id_room);
        Room room2 = new Room();
        room2.setId_room(id_room);
        List<Room_Amenities> room_amenitiesList = room_amenitiesDao.findRoom_AmenitiesByRoom(room2);
        model.addObject("room_amenitiesList", room_amenitiesList);
        if (room.isPresent()) {
            Room room1 = room.get();
            model.addObject("room1", room1);
        }
        return model;
    }

    @PostMapping(value = "*/updateRoom")
    public @ResponseBody
    String updateRoom(@RequestParam("id_room") Long id_room,
                      @RequestParam("roomType") Long id_room_type,
                      @RequestParam("room") String room,
                      @RequestParam("floor") Long id_floor,
                      @RequestParam("amenities[]") String[] amenities,
                      @RequestParam("amenitiesUnselect[]") String[] amenitiesUnselect) {

        Room room1 = new Room();
        Optional<RoomType> roomType2 = roomTypeDao.findById(id_room_type);
        Optional<Floor> floor2 = floorDao.findById(id_floor);
        Optional<Room> room2 = roomDao.findById(id_room);
        System.out.println("id_room" + id_room);
        if (roomType2.isPresent()) {
            if (floor2.isPresent()) {
                if (room2.isPresent()) {
                    RoomType roomType1 = roomType2.get();
                    Floor floor1 = floor2.get();
                    Room room5 = room2.get();
                    RoomType roomType = new RoomType();
                    roomType.setId_room_type(roomType1.getId_room_type());
                    roomType.setRoom_type(roomType1.getRoom_type());
                    roomType.setSlug(roomType1.getSlug());
                    roomType.setShort_code(roomType1.getShort_code());
                    roomType.setDescription(roomType1.getDescription());
                    roomType.setImage(roomType1.getImage());
                    roomType.setData_date(roomType1.getData_date());
                    roomType.setActive(roomType1.getActive());
                    RoomStructure roomStructure = new RoomStructure();
                    roomStructure.setId_room_structure(roomType1.getRoomStructure().getId_room_structure());
                    roomStructure.setBase_occupancy(roomType1.getRoomStructure().getBase_occupancy());
                    roomStructure.setHigher_occupancy(roomType1.getRoomStructure().getHigher_occupancy());
                    roomStructure.setKids_occupancy(roomType1.getRoomStructure().getKids_occupancy());
                    roomStructure.setExtra_bed(roomType1.getRoomStructure().getExtra_bed());
                    roomStructure.setActive(roomType1.getRoomStructure().getActive());
                    roomStructure.setData_date(roomType1.getRoomStructure().getData_date());
                    roomType.setRoomStructure(roomStructure);
                    Price price = new Price();
                    price.setId_price(roomType1.getPrice().getId_price());
                    price.setBase_price(roomType1.getPrice().getBase_price());
                    price.setExtra_bed_price(roomType1.getPrice().getExtra_bed_price());
                    price.setActive(roomType1.getPrice().getActive());
                    price.setData_date(roomType1.getPrice().getData_date());
                    roomType.setPrice(price);
                    room1.setRoomType(roomType);

                    RoomStatus roomStatus=new RoomStatus();
                    roomStatus.setId_room_status(room5.getRoomStatus().getId_room_status());
                    roomStatus.setRoom_status(room5.getRoomStatus().getRoom_status());
                    roomStatus.setData_date(room5.getRoomStatus().getData_date());
                    roomStatus.setActive(room5.getRoomStatus().getActive());
                    room1.setRoomStatus(roomStatus);
                    Floor floor = new Floor();
                    floor.setId_floor(floor1.getId_floor());
                    floor.setFloor_number(floor1.getFloor_number());
                    floor.setName(floor1.getName());
                    floor.setDescription(floor1.getDescription());
                    floor.setAction(floor1.getAction());
                    floor.setData_date(floor1.getData_date());
                    floor.setActive(floor1.getActive());
                    room1.setFloor(floor);
                    room1.setNumber(Long.parseLong(room));
                    room1.setId_room(room5.getId_room());
                    room1.setData_date(room5.getData_date());
                    room1.setActive(room5.getActive());
                    roomDao.save(room1);
                }
            }
        }
        if (amenities != null) {
            for (String amenities2 : amenities) {
                Long id_amenities = Long.valueOf(amenities2);
                Amenities amenities11 = new Amenities();
                amenities11.setId_amenities(id_amenities);
                Room room11 = new Room();
                room11.setId_room(id_room);
                Room_Amenities room_amenities = room_amenitiesDao.findRoom_AmenitiesByRoomAndAmenities(room11, amenities11);
                if (room_amenities != null) {
                    Room_Amenities room_amenities1 = new Room_Amenities();
                    Amenities amenities1 = new Amenities();
                    amenities1.setId_amenities(room_amenities.getAmenities().getId_amenities());
                    amenities1.setDescription(room_amenities.getAmenities().getDescription());
                    amenities1.setImage(room_amenities.getAmenities().getImage());
                    amenities1.setAction(room_amenities.getAmenities().getAction());
                    amenities1.setActive(room_amenities.getAmenities().getActive());
                    amenities1.setData_date(room_amenities.getAmenities().getData_date());
                    amenities1.setName(room_amenities.getAmenities().getName());
                    room_amenities1.setAmenities(amenities1);

                    Room room12 = new Room();
                    RoomType roomType = new RoomType();
                    roomType.setId_room_type(room_amenities.getRoom().getRoomType().getId_room_type());
                    roomType.setRoom_type(room_amenities.getRoom().getRoomType().getRoom_type());
                    roomType.setSlug(room_amenities.getRoom().getRoomType().getSlug());
                    roomType.setShort_code(room_amenities.getRoom().getRoomType().getShort_code());
                    roomType.setDescription(room_amenities.getRoom().getRoomType().getDescription());
                    roomType.setImage(room_amenities.getRoom().getRoomType().getImage());
                    roomType.setData_date(room_amenities.getRoom().getRoomType().getData_date());
                    roomType.setActive(room_amenities.getRoom().getRoomType().getActive());
                    RoomStructure roomStructure = new RoomStructure();
                    roomStructure.setId_room_structure(room_amenities.getRoom().getRoomType().getRoomStructure().getId_room_structure());
                    roomStructure.setBase_occupancy(room_amenities.getRoom().getRoomType().getRoomStructure().getBase_occupancy());
                    roomStructure.setHigher_occupancy(room_amenities.getRoom().getRoomType().getRoomStructure().getHigher_occupancy());
                    roomStructure.setKids_occupancy(room_amenities.getRoom().getRoomType().getRoomStructure().getKids_occupancy());
                    roomStructure.setExtra_bed(room_amenities.getRoom().getRoomType().getRoomStructure().getExtra_bed());
                    roomStructure.setActive(room_amenities.getRoom().getRoomType().getRoomStructure().getActive());
                    roomStructure.setData_date(room_amenities.getRoom().getRoomType().getRoomStructure().getData_date());
                    roomType.setRoomStructure(roomStructure);
                    Price price = new Price();
                    price.setId_price(room_amenities.getRoom().getRoomType().getPrice().getId_price());
                    price.setBase_price(room_amenities.getRoom().getRoomType().getPrice().getBase_price());
                    price.setExtra_bed_price(room_amenities.getRoom().getRoomType().getPrice().getExtra_bed_price());
                    price.setActive(room_amenities.getRoom().getRoomType().getPrice().getActive());
                    price.setData_date(room_amenities.getRoom().getRoomType().getPrice().getData_date());
                    roomType.setPrice(price);
                    room12.setRoomType(roomType);
                    RoomStatus roomStatus = new RoomStatus();
                    roomStatus.setId_room_status(room_amenities.getRoom().getRoomStatus().getId_room_status());
                    roomStatus.setRoom_status(room_amenities.getRoom().getRoomStatus().getRoom_status());
                    roomStatus.setData_date(room_amenities.getRoom().getRoomStatus().getData_date());
                    roomStatus.setActive(room_amenities.getRoom().getRoomStatus().getActive());
                    room12.setRoomStatus(roomStatus);
                    Floor floor = new Floor();
                    floor.setId_floor(room_amenities.getRoom().getFloor().getId_floor());
                    floor.setFloor_number(room_amenities.getRoom().getFloor().getFloor_number());
                    floor.setName(room_amenities.getRoom().getFloor().getName());
                    floor.setDescription(room_amenities.getRoom().getFloor().getDescription());
                    floor.setAction(room_amenities.getRoom().getFloor().getAction());
                    floor.setData_date(room_amenities.getRoom().getFloor().getData_date());
                    floor.setActive(room_amenities.getRoom().getFloor().getActive());
                    room12.setFloor(floor);
                    room12.setId_room(id_room);
                    room12.setNumber(room_amenities.getRoom().getNumber());
                    room12.setActive(room_amenities.getRoom().getActive());
                    room12.setData_date(room_amenities.getRoom().getData_date());
                    room_amenities1.setRoom(room12);
                    room_amenities1.setActive(1);
                    room_amenities1.setData_date(room_amenities.getData_date());
                    room_amenities1.setId_room_amenities(room_amenities.getId_room_amenities());
                    room_amenitiesDao.save(room_amenities1);
                }
                if (room_amenities == null) {
                    Room_Amenities room_amenities2 = new Room_Amenities();
                    room_amenities2.setAmenities(amenities11);
                    room_amenities2.setRoom(room11);
                    room_amenities2.setActive(1);
                    room_amenitiesDao.save(room_amenities2);
                }
            }//return "success";
        }
        if (amenitiesUnselect != null) {
            for (String amenities3 : amenitiesUnselect) {
                Long id_amenities = Long.valueOf(amenities3);
                Amenities amenities11 = new Amenities();
                amenities11.setId_amenities(id_amenities);
                Room room11 = new Room();
                room11.setId_room(id_room);
                Room_Amenities room_amenities = room_amenitiesDao.findRoom_AmenitiesByRoomAndAmenities(room11, amenities11);
                if (room_amenities != null) {
                    Room_Amenities room_amenities1 = new Room_Amenities();
                    Amenities amenities1 = new Amenities();
                    amenities1.setId_amenities(room_amenities.getAmenities().getId_amenities());
                    amenities1.setDescription(room_amenities.getAmenities().getDescription());
                    amenities1.setImage(room_amenities.getAmenities().getImage());
                    amenities1.setAction(room_amenities.getAmenities().getAction());
                    amenities1.setActive(room_amenities.getAmenities().getActive());
                    amenities1.setData_date(room_amenities.getAmenities().getData_date());
                    amenities1.setName(room_amenities.getAmenities().getName());
                    room_amenities1.setAmenities(amenities1);
                    Room room12 = new Room();
                    RoomType roomType = new RoomType();
                    roomType.setId_room_type(room_amenities.getRoom().getRoomType().getId_room_type());
                    roomType.setRoom_type(room_amenities.getRoom().getRoomType().getRoom_type());
                    roomType.setSlug(room_amenities.getRoom().getRoomType().getSlug());
                    roomType.setShort_code(room_amenities.getRoom().getRoomType().getShort_code());
                    roomType.setDescription(room_amenities.getRoom().getRoomType().getDescription());
                    roomType.setImage(room_amenities.getRoom().getRoomType().getImage());
                    roomType.setData_date(room_amenities.getRoom().getRoomType().getData_date());
                    roomType.setActive(room_amenities.getRoom().getRoomType().getActive());
                    RoomStructure roomStructure = new RoomStructure();
                    roomStructure.setId_room_structure(room_amenities.getRoom().getRoomType().getRoomStructure().getId_room_structure());
                    roomStructure.setBase_occupancy(room_amenities.getRoom().getRoomType().getRoomStructure().getBase_occupancy());
                    roomStructure.setHigher_occupancy(room_amenities.getRoom().getRoomType().getRoomStructure().getHigher_occupancy());
                    roomStructure.setKids_occupancy(room_amenities.getRoom().getRoomType().getRoomStructure().getKids_occupancy());
                    roomStructure.setExtra_bed(room_amenities.getRoom().getRoomType().getRoomStructure().getExtra_bed());
                    roomStructure.setActive(room_amenities.getRoom().getRoomType().getRoomStructure().getActive());
                    roomStructure.setData_date(room_amenities.getRoom().getRoomType().getRoomStructure().getData_date());
                    roomType.setRoomStructure(roomStructure);
                    Price price = new Price();
                    price.setId_price(room_amenities.getRoom().getRoomType().getPrice().getId_price());
                    price.setBase_price(room_amenities.getRoom().getRoomType().getPrice().getBase_price());
                    price.setExtra_bed_price(room_amenities.getRoom().getRoomType().getPrice().getExtra_bed_price());
                    price.setActive(room_amenities.getRoom().getRoomType().getPrice().getActive());
                    price.setData_date(room_amenities.getRoom().getRoomType().getPrice().getData_date());
                    roomType.setPrice(price);
                    room12.setRoomType(roomType);
                    RoomStatus roomStatus = new RoomStatus();
                    roomStatus.setId_room_status(room_amenities.getRoom().getRoomStatus().getId_room_status());
                    roomStatus.setRoom_status(room_amenities.getRoom().getRoomStatus().getRoom_status());
                    roomStatus.setData_date(room_amenities.getRoom().getRoomStatus().getData_date());
                    roomStatus.setActive(room_amenities.getRoom().getRoomStatus().getActive());
                    room12.setRoomStatus(roomStatus);
                    Floor floor = new Floor();
                    floor.setId_floor(room_amenities.getRoom().getFloor().getId_floor());
                    floor.setFloor_number(room_amenities.getRoom().getFloor().getFloor_number());
                    floor.setName(room_amenities.getRoom().getFloor().getName());
                    floor.setDescription(room_amenities.getRoom().getFloor().getDescription());
                    floor.setAction(room_amenities.getRoom().getFloor().getAction());
                    floor.setData_date(room_amenities.getRoom().getFloor().getData_date());
                    floor.setActive(room_amenities.getRoom().getFloor().getActive());
                    room12.setFloor(floor);
                    room12.setId_room(id_room);
                    room12.setNumber(room_amenities.getRoom().getNumber());
                    room12.setActive(room_amenities.getRoom().getActive());
                    room12.setData_date(room_amenities.getRoom().getData_date());
                    room_amenities1.setRoom(room12);
                    room_amenities1.setActive(0);
                    room_amenities1.setData_date(room_amenities.getData_date());
                    room_amenities1.setId_room_amenities(room_amenities.getId_room_amenities());
                    room_amenitiesDao.save(room_amenities1);

                }
                if (room_amenities == null) {
                    Room_Amenities room_amenities2 = new Room_Amenities();
                    room_amenities2.setAmenities(amenities11);
                    room_amenities2.setRoom(room11);
                    room_amenities2.setActive(0);
                    room_amenitiesDao.save(room_amenities2);
                }
            }
        }

        return "success";
    }

    @GetMapping("/viewRoom/{id}")
    public ModelAndView viewRoom(
            @PathVariable(value = "id") Long id_room) {
        ModelAndView model = new ModelAndView("room/viewRoom");
        Optional<Room> room = roomDao.findById(id_room);
        Room room2 = new Room();
        room2.setId_room(id_room);
        List<Room_Amenities> room_amenitiesList = room_amenitiesDao.findRoom_AmenitiesByRoom(room2);
        model.addObject("room_amenitiesList", room_amenitiesList);
        if (room.isPresent()) {
            Room room1 = room.get();
            model.addObject("room1", room1);
        }
        return model;
    }

    @PostMapping(value = "/deleteRoom")
    public @ResponseBody
    String deleteRoom(@RequestParam("dictionaryId") Long id_room) {
        Optional<Room> room2 = roomDao.findById(id_room);
        if (room2.isPresent()) {
            Room room1 = room2.get();
            Room room = new Room();
            RoomType roomType = new RoomType();
            roomType.setId_room_type(room1.getRoomType().getId_room_type());
            roomType.setRoom_type(room1.getRoomType().getRoom_type());
            roomType.setSlug(room1.getRoomType().getSlug());
            roomType.setShort_code(room1.getRoomType().getShort_code());
            roomType.setDescription(room1.getRoomType().getDescription());
            roomType.setImage(room1.getRoomType().getImage());
            roomType.setData_date(room1.getRoomType().getData_date());
            roomType.setActive(room1.getRoomType().getActive());
            RoomStructure roomStructure = new RoomStructure();
            roomStructure.setId_room_structure(room1.getRoomType().getRoomStructure().getId_room_structure());
            roomStructure.setBase_occupancy(room1.getRoomType().getRoomStructure().getBase_occupancy());
            roomStructure.setHigher_occupancy(room1.getRoomType().getRoomStructure().getHigher_occupancy());
            roomStructure.setKids_occupancy(room1.getRoomType().getRoomStructure().getKids_occupancy());
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
            RoomStatus roomStatus = new RoomStatus();
            roomStatus.setId_room_status(room1.getRoomStatus().getId_room_status());
            roomStatus.setRoom_status(room1.getRoomStatus().getRoom_status());
            roomStatus.setData_date(room1.getRoomStatus().getData_date());
            roomStatus.setActive(room1.getRoomStatus().getActive());
            room.setRoomStatus(roomStatus);
            Floor floor = new Floor();
            floor.setId_floor(room1.getFloor().getId_floor());
            floor.setFloor_number(room1.getFloor().getFloor_number());
            floor.setName(room1.getFloor().getName());
            floor.setDescription(room1.getFloor().getDescription());
            floor.setAction(room1.getFloor().getAction());
            floor.setData_date(room1.getFloor().getData_date());
            floor.setActive(room1.getFloor().getActive());
            room.setFloor(floor);
            room.setId_room(id_room);
            room.setNumber(room1.getNumber());
            room.setActive(0);
            room.setData_date(room1.getData_date());
            roomDao.save(room);
        }
        return "success";
    }
}
