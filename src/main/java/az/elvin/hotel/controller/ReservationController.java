package az.elvin.hotel.controller;

import az.elvin.hotel.dao.*;
import az.elvin.hotel.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class ReservationController {
    @Autowired
    private GuestDao guestDao;
    @Autowired
    private ReservationDao reservationDao;
    @Autowired
    private PaymentStatusDao paymentStatusDao;
    @Autowired
    private BookingStatusDao bookingStatusDao;
    @Autowired
    private RoomTypeDao roomTypeDao;
    @Autowired
    private TaxDao taxDao;
    @Autowired
    private RoomDao roomDao;
    @Autowired
    private ServicesDao servicesDao;
    @Autowired
    private PaymentDao paymentDao;
    @Autowired
    private Room_ReservationDao room_reservationDao;
    @Autowired
    private Service_ReservationDao service_reservationDao;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @GetMapping(value = "/getReservationList")
    public ModelAndView getReservationList() {
        ModelAndView model = new ModelAndView("reservation/reservationList");
        List<Reservation> reservationList = (List<Reservation>) reservationDao.findAll();
        model.addObject("reservationList", reservationList);
        List<RoomType> roomTypeList = (List<RoomType>) roomTypeDao.findAll();
        model.addObject("roomTypeList", roomTypeList);
        List<Guest> guestList = (List<Guest>) guestDao.findAll();
        model.addObject("guestList", guestList);
        List<BookingStatus> bookingStatusList = (List<BookingStatus>) bookingStatusDao.findAll();
        model.addObject("bookingStatusList", bookingStatusList);
        List<PaymentStatus> paymentStatusList = (List<PaymentStatus>) paymentStatusDao.findAll();
        model.addObject("paymentStatusList", paymentStatusList);
        return model;
    }

    @GetMapping(value = "/newReservation")
    public ModelAndView newRoom() {
        ModelAndView model = new ModelAndView("reservation/newReservation");
        List<RoomType> roomTypeList = (List<RoomType>) roomTypeDao.findAll();
        model.addObject("roomTypeList", roomTypeList);
        List<Guest> guestList = (List<Guest>) guestDao.findAll();
        model.addObject("guestList", guestList);
        return model;
    }

    @PostMapping(value = "/addReservation")
    public @ResponseBody
    String addReservation(@RequestParam("guest") Long id_guest,
                          @RequestParam("roomType") Long id_room_type,
                          @RequestParam("adults") Long adults,
                          @RequestParam("kids") Long kids,
                          @RequestParam("checkIn") String checkIn,
                          @RequestParam("checkOut") String checkOut,
                          @RequestParam("extraBed") Integer extraBed,
                          @RequestParam("night") Long night) {
        try {
            Reservation reservation = new Reservation();
            Guest guest = new Guest();
            guest.setId_guest(id_guest);
            reservation.setGuest(guest);
            RoomType roomType = new RoomType();
            roomType.setId_room_type(id_room_type);
            reservation.setRoomType(roomType);
            reservation.setAdults(adults);
            reservation.setChildren(kids);
            reservation.setExtra_bed(extraBed);
            reservation.setNight(night);
            reservation.setCheck_in(df.parse(checkIn));
            reservation.setCheck_out(df.parse(checkOut));
            reservationDao.save(reservation);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @GetMapping("/editReservation/{id}")
    public ModelAndView editReservation(
            @PathVariable(value = "id") Long id_reservation) {
        ModelAndView model = new ModelAndView("reservation/editReservation");
        Optional<Reservation> reservation = reservationDao.findById(id_reservation);
        if (reservation.isPresent()) {
            Reservation reservation1 = reservation.get();
            model.addObject("reservation1", reservation1);
        }
        List<RoomType> roomTypeList = (List<RoomType>) roomTypeDao.findAll();
        model.addObject("roomTypeList", roomTypeList);
        List<Guest> guestList = (List<Guest>) guestDao.findAll();
        model.addObject("guestList", guestList);
        return model;
    }

    @GetMapping("/moreReservation/{id}")
    public ModelAndView moreReservation(
            @PathVariable(value = "id") Long id_reservation, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("reservation/moreReservation");
        Optional<Reservation> reservation = reservationDao.findById(id_reservation);
        if (reservation.isPresent()) {
            Reservation reservation2 = reservation.get();
            HttpSession session = request.getSession(true);
            // session.setAttribute("reservation2", reservation2);
            model.addObject("reservation2", reservation2);
        }
        List<RoomType> roomTypeList = (List<RoomType>) roomTypeDao.findAll();
        model.addObject("roomTypeList", roomTypeList);
        List<Guest> guestList = (List<Guest>) guestDao.findAll();
        model.addObject("guestList", guestList);
        List<BookingStatus> bookingStatusList = (List<BookingStatus>) bookingStatusDao.findAll();
        model.addObject("bookingStatusList", bookingStatusList);
        List<PaymentStatus> paymentStatusList = (List<PaymentStatus>) paymentStatusDao.findAll();
        model.addObject("paymentStatusList", paymentStatusList);
        Reservation reservation1 = new Reservation();
        reservation1.setId_reservation(id_reservation);
        Payment payment = paymentDao.findPaymentByReservation(reservation1);
        model.addObject("payment", payment);
        List<Room> roomList = (List<Room>) roomDao.findAll();
        // List<Room> roomList = roomDao.findRoomByActive();
        model.addObject("roomList", roomList);
        List<Room_Reservation> room_reservation = room_reservationDao.findReservation_RoomByReservation(reservation1);
        model.addObject("room_reservation", room_reservation);
        List<Services> servicesList = (List<Services>) servicesDao.findAll();
        model.addObject("servicesList", servicesList);
        List<Service_Reservation> service_reservationList = service_reservationDao.findService_ReservationsByReservation(reservation1);
        model.addObject("service_reservationList", service_reservationList);
        List<Tax> taxList = (List<Tax>) taxDao.findAll();
        model.addObject("taxList", taxList);
        return model;
    }

    @PostMapping(value = "*/updateReservation")
    public @ResponseBody
    String updateReservation(@RequestParam("reservationId") Long id_reservation,
                             @RequestParam("guest") Long id_guest,
                             @RequestParam("roomType") Long id_room_type,
                             @RequestParam("adults") Long adults,
                             @RequestParam("kids") Long kids,
                             @RequestParam("checkIn") String checkIn,
                             @RequestParam("checkOut") String checkOut,
                             @RequestParam("extraBed") Integer extraBed,
                             @RequestParam("night") Long night) {
        try {
            Optional<Reservation> reservation2 = reservationDao.findById(id_reservation);
            Optional<Guest> guest2 = guestDao.findById(id_guest);
            Optional<RoomType> roomType2 = roomTypeDao.findById(id_room_type);
            if (roomType2.isPresent()) {
                if (guest2.isPresent()) {
                    if (reservation2.isPresent()) {
                        Reservation reservation1 = reservation2.get();
                        Guest guest1 = guest2.get();
                        RoomType roomType1 = roomType2.get();
                        Reservation reservation = new Reservation();
                        Guest guest = new Guest();
                        guest.setId_guest(id_guest);
                        guest.setName(guest1.getName());
                        guest.setSurname(guest1.getSurname());
                        guest.setGender(guest1.getGender());
                        guest.setPhone(guest1.getPhone());
                        guest.setEmail(guest1.getEmail());
                        guest.setDob(guest1.getDob());
                        guest.setPassport_number(guest1.getPassport_number());
                        guest.setImage1(guest1.getImage1());
                        guest.setImage2(guest1.getImage2());
                        guest.setData_date(guest1.getData_date());
                        guest.setActive(guest1.getActive());
                        City city = new City();
                        city.setId_city(guest1.getCity().getId_city());
                        city.setName(guest1.getCity().getName());
                        city.setData_date(guest1.getCity().getData_date());
                        city.setActive(guest1.getCity().getActive());
                        Country country = new Country();
                        country.setId_country(guest1.getCity().getCountry().getId_country());
                        country.setName(guest1.getCity().getCountry().getName());
                        country.setSort_name(guest1.getCity().getCountry().getSort_name());
                        country.setData_date(guest1.getCity().getCountry().getData_date());
                        country.setActive(guest1.getCity().getCountry().getActive());
                        city.setCountry(country);
                        guest.setCity(city);
                        Passport passport = new Passport();
                        passport.setId_passport(guest1.getPassport().getId_passport());
                        passport.setPassport_type(guest1.getPassport().getPassport_type());
                        passport.setData_date(guest1.getPassport().getData_date());
                        passport.setActive(guest1.getPassport().getActive());
                        guest.setPassport(passport);
                        reservation.setGuest(guest);

                        RoomType roomType = new RoomType();
                        Price price = new Price();
                        price.setId_price(roomType1.getPrice().getId_price());
                        price.setBase_price(roomType1.getPrice().getBase_price());
                        price.setExtra_bed_price(roomType1.getPrice().getExtra_bed_price());
                        price.setData_date(roomType1.getPrice().getData_date());
                        price.setActive(roomType1.getPrice().getActive());
                        roomType.setPrice(price);
                        RoomStructure roomStructure = new RoomStructure();
                        roomStructure.setId_room_structure(roomType1.getRoomStructure().getId_room_structure());
                        roomStructure.setBase_occupancy(roomType1.getRoomStructure().getBase_occupancy());
                        roomStructure.setKids_occupancy(roomType1.getRoomStructure().getKids_occupancy());
                        roomStructure.setHigher_occupancy(roomType1.getRoomStructure().getHigher_occupancy());
                        roomStructure.setExtra_bed(roomType1.getRoomStructure().getExtra_bed());
                        roomStructure.setData_date(roomType1.getRoomStructure().getData_date());
                        roomStructure.setActive(roomType1.getRoomStructure().getActive());
                        roomType.setRoomStructure(roomStructure);
                        roomType.setId_room_type(id_room_type);
                        roomType.setRoom_type(roomType1.getRoom_type());
                        roomType.setSlug(roomType1.getSlug());
                        roomType.setShort_code(roomType1.getShort_code());
                        roomType.setDescription(roomType1.getDescription());
                        roomType.setImage(roomType1.getImage());
                        roomType.setData_date(roomType1.getData_date());
                        roomType.setActive(roomType1.getActive());
                        reservation.setRoomType(roomType);
                        Hotel hotel = new Hotel();
                        Star star = new Star();
                        star.setId_star(reservation1.getHotel().getStar().getId_star());
                        star.setStar(reservation1.getHotel().getStar().getStar());
                        star.setData_date(reservation1.getHotel().getStar().getData_date());
                        star.setActive(reservation1.getHotel().getStar().getActive());
                        hotel.setStar(star);
                        hotel.setId_hotel(reservation1.getHotel().getId_hotel());
                        hotel.setName(reservation1.getHotel().getName());
                        hotel.setAddress(reservation1.getHotel().getAddress());
                        hotel.setPhone(reservation1.getHotel().getPhone());
                        hotel.setEmail(reservation1.getHotel().getEmail());
                        hotel.setLogo(reservation1.getHotel().getLogo());
                        hotel.setData_date(reservation1.getHotel().getData_date());
                        hotel.setActive(reservation1.getHotel().getActive());
                        reservation.setHotel(hotel);
                        BookingStatus bookingStatus = new BookingStatus();
                        bookingStatus.setId_booking_status(reservation1.getBookingStatus().getId_booking_status());
                        bookingStatus.setBooking_status(reservation1.getBookingStatus().getBooking_status());
                        bookingStatus.setData_date(reservation1.getBookingStatus().getData_date());
                        bookingStatus.setActive(reservation1.getBookingStatus().getActive());
                        reservation.setBookingStatus(bookingStatus);
                        PaymentStatus paymentStatus = new PaymentStatus();
                        paymentStatus.setId_payment_status(reservation1.getPaymentStatus().getId_payment_status());
                        paymentStatus.setPayment_status(reservation1.getPaymentStatus().getPayment_status());
                        paymentStatus.setData_date(reservation1.getPaymentStatus().getData_date());
                        paymentStatus.setActive(reservation1.getPaymentStatus().getActive());
                        reservation.setPaymentStatus(paymentStatus);
                        reservation.setAdults(adults);
                        reservation.setChildren(kids);
                        reservation.setExtra_bed(extraBed);
                        reservation.setNight(night);
                        reservation.setCheck_in(df.parse(checkIn));
                        reservation.setCheck_out(df.parse(checkOut));
                        reservation.setData_date(reservation1.getData_date());
                        reservation.setActive(reservation1.getActive());
                        reservation.setId_reservation(id_reservation);
                        reservationDao.save(reservation);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @PostMapping(value = "/deleteReservation")
    public @ResponseBody
    String deleteReservation(@RequestParam("reservationId") Long id_reservation) {
        try {
            Optional<Reservation> reservation2 = reservationDao.findById(id_reservation);
            if (reservation2.isPresent()) {
                Reservation reservation1 = reservation2.get();
                Reservation reservation = new Reservation();
                Guest guest = new Guest();
                guest.setId_guest(reservation1.getGuest().getId_guest());
                guest.setName(reservation1.getGuest().getName());
                guest.setSurname(reservation1.getGuest().getSurname());
                guest.setGender(reservation1.getGuest().getGender());
                guest.setPhone(reservation1.getGuest().getPhone());
                guest.setEmail(reservation1.getGuest().getEmail());
                guest.setDob(reservation1.getGuest().getDob());
                guest.setPassport_number(reservation1.getGuest().getPassport_number());
                guest.setImage1(reservation1.getGuest().getImage1());
                guest.setImage2(reservation1.getGuest().getImage2());
                guest.setData_date(reservation1.getGuest().getData_date());
                guest.setActive(reservation1.getGuest().getActive());
                City city = new City();
                city.setId_city(reservation1.getGuest().getCity().getId_city());
                city.setName(reservation1.getGuest().getCity().getName());
                city.setData_date(reservation1.getGuest().getCity().getData_date());
                city.setActive(reservation1.getGuest().getCity().getActive());
                Country country = new Country();
                country.setId_country(reservation1.getGuest().getCity().getCountry().getId_country());
                country.setName(reservation1.getGuest().getCity().getCountry().getName());
                country.setSort_name(reservation1.getGuest().getCity().getCountry().getSort_name());
                country.setData_date(reservation1.getGuest().getCity().getCountry().getData_date());
                country.setActive(reservation1.getGuest().getCity().getCountry().getActive());
                city.setCountry(country);
                guest.setCity(city);
                Passport passport = new Passport();
                passport.setId_passport(reservation1.getGuest().getPassport().getId_passport());
                passport.setPassport_type(reservation1.getGuest().getPassport().getPassport_type());
                passport.setData_date(reservation1.getGuest().getPassport().getData_date());
                passport.setActive(reservation1.getGuest().getPassport().getActive());
                guest.setPassport(passport);
                reservation.setGuest(guest);

                RoomType roomType = new RoomType();
                Price price = new Price();
                price.setId_price(reservation1.getRoomType().getPrice().getId_price());
                price.setBase_price(reservation1.getRoomType().getPrice().getBase_price());
                price.setExtra_bed_price(reservation1.getRoomType().getPrice().getExtra_bed_price());
                price.setData_date(reservation1.getRoomType().getPrice().getData_date());
                price.setActive(reservation1.getRoomType().getPrice().getActive());
                roomType.setPrice(price);
                RoomStructure roomStructure = new RoomStructure();
                roomStructure.setId_room_structure(reservation1.getRoomType().getRoomStructure().getId_room_structure());
                roomStructure.setBase_occupancy(reservation1.getRoomType().getRoomStructure().getBase_occupancy());
                roomStructure.setKids_occupancy(reservation1.getRoomType().getRoomStructure().getKids_occupancy());
                roomStructure.setHigher_occupancy(reservation1.getRoomType().getRoomStructure().getHigher_occupancy());
                roomStructure.setExtra_bed(reservation1.getRoomType().getRoomStructure().getExtra_bed());
                roomStructure.setData_date(reservation1.getRoomType().getRoomStructure().getData_date());
                roomStructure.setActive(reservation1.getRoomType().getRoomStructure().getActive());
                roomType.setRoomStructure(roomStructure);
                roomType.setId_room_type(reservation1.getRoomType().getId_room_type());
                roomType.setRoom_type(reservation1.getRoomType().getRoom_type());
                roomType.setSlug(reservation1.getRoomType().getSlug());
                roomType.setShort_code(reservation1.getRoomType().getShort_code());
                roomType.setDescription(reservation1.getRoomType().getDescription());
                roomType.setImage(reservation1.getRoomType().getImage());
                roomType.setData_date(reservation1.getRoomType().getData_date());
                roomType.setActive(reservation1.getRoomType().getActive());
                reservation.setRoomType(roomType);
                Hotel hotel = new Hotel();
                Star star = new Star();
                star.setId_star(reservation1.getHotel().getStar().getId_star());
                star.setStar(reservation1.getHotel().getStar().getStar());
                star.setData_date(reservation1.getHotel().getStar().getData_date());
                star.setActive(reservation1.getHotel().getStar().getActive());
                hotel.setStar(star);
                hotel.setId_hotel(reservation1.getHotel().getId_hotel());
                hotel.setName(reservation1.getHotel().getName());
                hotel.setAddress(reservation1.getHotel().getAddress());
                hotel.setPhone(reservation1.getHotel().getPhone());
                hotel.setEmail(reservation1.getHotel().getEmail());
                hotel.setLogo(reservation1.getHotel().getLogo());
                hotel.setData_date(reservation1.getHotel().getData_date());
                hotel.setActive(reservation1.getHotel().getActive());
                reservation.setHotel(hotel);
                BookingStatus bookingStatus = new BookingStatus();
                bookingStatus.setId_booking_status(reservation1.getBookingStatus().getId_booking_status());
                bookingStatus.setBooking_status(reservation1.getBookingStatus().getBooking_status());
                bookingStatus.setData_date(reservation1.getBookingStatus().getData_date());
                bookingStatus.setActive(reservation1.getBookingStatus().getActive());
                reservation.setBookingStatus(bookingStatus);
                PaymentStatus paymentStatus = new PaymentStatus();
                paymentStatus.setId_payment_status(reservation1.getPaymentStatus().getId_payment_status());
                paymentStatus.setPayment_status(reservation1.getPaymentStatus().getPayment_status());
                paymentStatus.setData_date(reservation1.getPaymentStatus().getData_date());
                paymentStatus.setActive(reservation1.getPaymentStatus().getActive());
                reservation.setPaymentStatus(paymentStatus);
                reservation.setAdults(reservation1.getAdults());
                reservation.setChildren(reservation1.getChildren());
                reservation.setExtra_bed(reservation1.getExtra_bed());
                reservation.setNight(reservation1.getNight());
                reservation.setCheck_in(reservation1.getCheck_in());
                reservation.setCheck_out(reservation1.getCheck_out());
                reservation.setData_date(reservation1.getData_date());
                reservation.setActive(0);
                reservation.setId_reservation(id_reservation);
                reservationDao.save(reservation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @PostMapping(value = "*/updateReservation1")
    public @ResponseBody
    String updateReservation1(@RequestParam("reservationId") Long id_reservation,
                              @RequestParam("bookingStatus") String id_booking_status) {
        Reservation reservation = new Reservation();
        if (id_booking_status.equals("2")) {
            Optional<Reservation> reservation2 = reservationDao.findById(id_reservation);
            if (reservation2.isPresent()) {
                Reservation reservation1 = reservation2.get();
                Long night = reservation1.getNight();
                Long id_room_type = reservation1.getRoomType().getId_room_type();
                Optional<RoomType> roomType2 = roomTypeDao.findById(id_room_type);
                if (roomType2.isPresent()) {
                    RoomType roomType = roomType2.get();
                    Double roomTypeBasePrice = roomType.getPrice().getBase_price();
                    Double roomTypeExtraBedPrice=roomType.getPrice().getExtra_bed_price();
                    List<Tax> taxList = (List<Tax>) taxDao.findAll();
                    Double cem = 0.0;
                    for (Tax tax : taxList) {
                        Double rate = tax.getRate();
                        cem += rate;
                    }
                    Payment payment = new Payment();
                    if (reservation1.getExtra_bed()==1){
                        Double cost = Math.abs(night * (roomTypeBasePrice+roomTypeExtraBedPrice));
                        payment.setAll_night_cost(cost);
                        payment.setPending(cost);
                        payment.setSum(cost);
                        payment.setAmount(0.0);
                        payment.setTax_cost(Math.abs(cost / cem));
                    }else {
                        Double cost = Math.abs(night * roomTypeBasePrice);
                        payment.setAll_night_cost(cost);
                        payment.setPending(cost);
                        payment.setSum(cost);
                        payment.setAmount(0.0);
                        payment.setTax_cost(Math.abs(cost / cem));
                    }

                    reservation.setId_reservation(id_reservation);
                    payment.setReservation(reservation);
                    paymentDao.save(payment);
                }
            }

        }

        /* vg************jghgvyuhbkuhb************kub*/
        Optional<Reservation> reservation2 = reservationDao.findById(id_reservation);
        Optional<BookingStatus> bookingStatus2 = bookingStatusDao.findById(Long.parseLong(id_booking_status));
        if (bookingStatus2.isPresent()) {
            if (reservation2.isPresent()) {
                Reservation reservation1 = reservation2.get();
                BookingStatus bookingStatus3 = bookingStatus2.get();
                // Reservation reservation = new Reservation();
                Guest guest = new Guest();
                guest.setId_guest(reservation1.getGuest().getId_guest());
                guest.setName(reservation1.getGuest().getName());
                guest.setSurname(reservation1.getGuest().getSurname());
                guest.setGender(reservation1.getGuest().getGender());
                guest.setPhone(reservation1.getGuest().getPhone());
                guest.setEmail(reservation1.getGuest().getEmail());
                guest.setDob(reservation1.getGuest().getDob());
                guest.setPassport_number(reservation1.getGuest().getPassport_number());
                guest.setImage1(reservation1.getGuest().getImage1());
                guest.setImage2(reservation1.getGuest().getImage2());
                guest.setData_date(reservation1.getGuest().getData_date());
                guest.setActive(reservation1.getGuest().getActive());
                City city = new City();
                city.setId_city(reservation1.getGuest().getCity().getId_city());
                city.setName(reservation1.getGuest().getCity().getName());
                city.setData_date(reservation1.getGuest().getCity().getData_date());
                city.setActive(reservation1.getGuest().getCity().getActive());
                Country country = new Country();
                country.setId_country(reservation1.getGuest().getCity().getCountry().getId_country());
                country.setName(reservation1.getGuest().getCity().getCountry().getName());
                country.setSort_name(reservation1.getGuest().getCity().getCountry().getSort_name());
                country.setData_date(reservation1.getGuest().getCity().getCountry().getData_date());
                country.setActive(reservation1.getGuest().getCity().getCountry().getActive());
                city.setCountry(country);
                guest.setCity(city);
                Passport passport = new Passport();
                passport.setId_passport(reservation1.getGuest().getPassport().getId_passport());
                passport.setPassport_type(reservation1.getGuest().getPassport().getPassport_type());
                passport.setData_date(reservation1.getGuest().getPassport().getData_date());
                passport.setActive(reservation1.getGuest().getPassport().getActive());
                guest.setPassport(passport);
                reservation.setGuest(guest);

                RoomType roomType = new RoomType();
                Price price = new Price();
                price.setId_price(reservation1.getRoomType().getPrice().getId_price());
                price.setBase_price(reservation1.getRoomType().getPrice().getBase_price());
                price.setExtra_bed_price(reservation1.getRoomType().getPrice().getExtra_bed_price());
                price.setData_date(reservation1.getRoomType().getPrice().getData_date());
                price.setActive(reservation1.getRoomType().getPrice().getActive());
                roomType.setPrice(price);
                RoomStructure roomStructure = new RoomStructure();
                roomStructure.setId_room_structure(reservation1.getRoomType().getRoomStructure().getId_room_structure());
                roomStructure.setBase_occupancy(reservation1.getRoomType().getRoomStructure().getBase_occupancy());
                roomStructure.setKids_occupancy(reservation1.getRoomType().getRoomStructure().getKids_occupancy());
                roomStructure.setHigher_occupancy(reservation1.getRoomType().getRoomStructure().getHigher_occupancy());
                roomStructure.setExtra_bed(reservation1.getRoomType().getRoomStructure().getExtra_bed());
                roomStructure.setData_date(reservation1.getRoomType().getRoomStructure().getData_date());
                roomStructure.setActive(reservation1.getRoomType().getRoomStructure().getActive());
                roomType.setRoomStructure(roomStructure);
                roomType.setId_room_type(reservation1.getRoomType().getId_room_type());
                roomType.setRoom_type(reservation1.getRoomType().getRoom_type());
                roomType.setSlug(reservation1.getRoomType().getSlug());
                roomType.setShort_code(reservation1.getRoomType().getShort_code());
                roomType.setDescription(reservation1.getRoomType().getDescription());
                roomType.setImage(reservation1.getRoomType().getImage());
                roomType.setData_date(reservation1.getRoomType().getData_date());
                roomType.setActive(reservation1.getRoomType().getActive());
                reservation.setRoomType(roomType);
                Hotel hotel = new Hotel();
                Star star = new Star();
                star.setId_star(reservation1.getHotel().getStar().getId_star());
                star.setStar(reservation1.getHotel().getStar().getStar());
                star.setData_date(reservation1.getHotel().getStar().getData_date());
                star.setActive(reservation1.getHotel().getStar().getActive());
                hotel.setStar(star);
                hotel.setId_hotel(reservation1.getHotel().getId_hotel());
                hotel.setName(reservation1.getHotel().getName());
                hotel.setAddress(reservation1.getHotel().getAddress());
                hotel.setPhone(reservation1.getHotel().getPhone());
                hotel.setEmail(reservation1.getHotel().getEmail());
                hotel.setLogo(reservation1.getHotel().getLogo());
                hotel.setData_date(reservation1.getHotel().getData_date());
                hotel.setActive(reservation1.getHotel().getActive());
                reservation.setHotel(hotel);

                BookingStatus bookingStatus1 = new BookingStatus();
                bookingStatus1.setId_booking_status(bookingStatus3.getId_booking_status());
                bookingStatus1.setBooking_status(bookingStatus3.getBooking_status());
                bookingStatus1.setData_date(bookingStatus3.getData_date());
                bookingStatus1.setActive(bookingStatus3.getActive());
                reservation.setBookingStatus(bookingStatus1);
                PaymentStatus paymentStatus = new PaymentStatus();
                paymentStatus.setId_payment_status(reservation1.getPaymentStatus().getId_payment_status());
                paymentStatus.setPayment_status(reservation1.getPaymentStatus().getPayment_status());
                paymentStatus.setData_date(reservation1.getPaymentStatus().getData_date());
                paymentStatus.setActive(reservation1.getPaymentStatus().getActive());
                reservation.setPaymentStatus(paymentStatus);
                reservation.setAdults(reservation1.getAdults());
                reservation.setChildren(reservation1.getChildren());
                reservation.setExtra_bed(reservation1.getExtra_bed());
                reservation.setNight(reservation1.getNight());
                reservation.setCheck_in(reservation1.getCheck_in());
                reservation.setCheck_out(reservation1.getCheck_out());
                reservation.setData_date(reservation1.getData_date());
                reservation.setActive(reservation1.getActive());
                reservation.setId_reservation(id_reservation);
                reservationDao.save(reservation);
                return "success";
            }
        }
        /* hbhj*************vjygvty***********gb*/

        return "success";

    }

    @PostMapping(value = "*/updateReservation2")
    public @ResponseBody
    String updateReservation2(@RequestParam("reservationId") Long id_reservation,
                              @RequestParam("paymentStatus") String id_payment_status) {
        Reservation reservation = new Reservation();

        /* vg************jghgvyuhbkuhb************kub*/
        Optional<Reservation> reservation2 = reservationDao.findById(id_reservation);
        Optional<PaymentStatus> paymentStatus2 = paymentStatusDao.findById(Long.parseLong(id_payment_status));
        if (paymentStatus2.isPresent()) {
            if (reservation2.isPresent()) {
                Reservation reservation1 = reservation2.get();
                PaymentStatus paymentStatus3 = paymentStatus2.get();
                // Reservation reservation = new Reservation();
                Guest guest = new Guest();
                guest.setId_guest(reservation1.getGuest().getId_guest());
                guest.setName(reservation1.getGuest().getName());
                guest.setSurname(reservation1.getGuest().getSurname());
                guest.setGender(reservation1.getGuest().getGender());
                guest.setPhone(reservation1.getGuest().getPhone());
                guest.setEmail(reservation1.getGuest().getEmail());
                guest.setDob(reservation1.getGuest().getDob());
                guest.setPassport_number(reservation1.getGuest().getPassport_number());
                guest.setImage1(reservation1.getGuest().getImage1());
                guest.setImage2(reservation1.getGuest().getImage2());
                guest.setData_date(reservation1.getGuest().getData_date());
                guest.setActive(reservation1.getGuest().getActive());
                City city = new City();
                city.setId_city(reservation1.getGuest().getCity().getId_city());
                city.setName(reservation1.getGuest().getCity().getName());
                city.setData_date(reservation1.getGuest().getCity().getData_date());
                city.setActive(reservation1.getGuest().getCity().getActive());
                Country country = new Country();
                country.setId_country(reservation1.getGuest().getCity().getCountry().getId_country());
                country.setName(reservation1.getGuest().getCity().getCountry().getName());
                country.setSort_name(reservation1.getGuest().getCity().getCountry().getSort_name());
                country.setData_date(reservation1.getGuest().getCity().getCountry().getData_date());
                country.setActive(reservation1.getGuest().getCity().getCountry().getActive());
                city.setCountry(country);
                guest.setCity(city);
                Passport passport = new Passport();
                passport.setId_passport(reservation1.getGuest().getPassport().getId_passport());
                passport.setPassport_type(reservation1.getGuest().getPassport().getPassport_type());
                passport.setData_date(reservation1.getGuest().getPassport().getData_date());
                passport.setActive(reservation1.getGuest().getPassport().getActive());
                guest.setPassport(passport);
                reservation.setGuest(guest);

                RoomType roomType = new RoomType();
                Price price = new Price();
                price.setId_price(reservation1.getRoomType().getPrice().getId_price());
                price.setBase_price(reservation1.getRoomType().getPrice().getBase_price());
                price.setExtra_bed_price(reservation1.getRoomType().getPrice().getExtra_bed_price());
                price.setData_date(reservation1.getRoomType().getPrice().getData_date());
                price.setActive(reservation1.getRoomType().getPrice().getActive());
                roomType.setPrice(price);
                RoomStructure roomStructure = new RoomStructure();
                roomStructure.setId_room_structure(reservation1.getRoomType().getRoomStructure().getId_room_structure());
                roomStructure.setBase_occupancy(reservation1.getRoomType().getRoomStructure().getBase_occupancy());
                roomStructure.setKids_occupancy(reservation1.getRoomType().getRoomStructure().getKids_occupancy());
                roomStructure.setHigher_occupancy(reservation1.getRoomType().getRoomStructure().getHigher_occupancy());
                roomStructure.setExtra_bed(reservation1.getRoomType().getRoomStructure().getExtra_bed());
                roomStructure.setData_date(reservation1.getRoomType().getRoomStructure().getData_date());
                roomStructure.setActive(reservation1.getRoomType().getRoomStructure().getActive());
                roomType.setRoomStructure(roomStructure);
                roomType.setId_room_type(reservation1.getRoomType().getId_room_type());
                roomType.setRoom_type(reservation1.getRoomType().getRoom_type());
                roomType.setSlug(reservation1.getRoomType().getSlug());
                roomType.setShort_code(reservation1.getRoomType().getShort_code());
                roomType.setDescription(reservation1.getRoomType().getDescription());
                roomType.setImage(reservation1.getRoomType().getImage());
                roomType.setData_date(reservation1.getRoomType().getData_date());
                roomType.setActive(reservation1.getRoomType().getActive());
                reservation.setRoomType(roomType);
                Hotel hotel = new Hotel();
                Star star = new Star();
                star.setId_star(reservation1.getHotel().getStar().getId_star());
                star.setStar(reservation1.getHotel().getStar().getStar());
                star.setData_date(reservation1.getHotel().getStar().getData_date());
                star.setActive(reservation1.getHotel().getStar().getActive());
                hotel.setStar(star);
                hotel.setId_hotel(reservation1.getHotel().getId_hotel());
                hotel.setName(reservation1.getHotel().getName());
                hotel.setAddress(reservation1.getHotel().getAddress());
                hotel.setPhone(reservation1.getHotel().getPhone());
                hotel.setEmail(reservation1.getHotel().getEmail());
                hotel.setLogo(reservation1.getHotel().getLogo());
                hotel.setData_date(reservation1.getHotel().getData_date());
                hotel.setActive(reservation1.getHotel().getActive());
                reservation.setHotel(hotel);

                BookingStatus bookingStatus1 = new BookingStatus();
                bookingStatus1.setId_booking_status(reservation1.getBookingStatus().getId_booking_status());
                bookingStatus1.setBooking_status(reservation1.getBookingStatus().getBooking_status());
                bookingStatus1.setData_date(reservation1.getBookingStatus().getData_date());
                bookingStatus1.setActive(reservation1.getBookingStatus().getActive());
                reservation.setBookingStatus(bookingStatus1);

                PaymentStatus paymentStatus = new PaymentStatus();
                paymentStatus.setId_payment_status(paymentStatus3.getId_payment_status());
                paymentStatus.setPayment_status(paymentStatus3.getPayment_status());
                paymentStatus.setData_date(paymentStatus3.getData_date());
                paymentStatus.setActive(paymentStatus3.getActive());
                reservation.setPaymentStatus(paymentStatus);
                reservation.setAdults(reservation1.getAdults());
                reservation.setChildren(reservation1.getChildren());
                reservation.setExtra_bed(reservation1.getExtra_bed());
                reservation.setNight(reservation1.getNight());
                reservation.setCheck_in(reservation1.getCheck_in());
                reservation.setCheck_out(reservation1.getCheck_out());
                reservation.setData_date(reservation1.getData_date());
                reservation.setActive(reservation1.getActive());
                reservation.setId_reservation(id_reservation);
                reservationDao.save(reservation);
                return "success";
            }
        }
        /* hbhj*************vjygvty***********gb*/

        return "success";

    }

    @PostMapping(value = "*/addReservationRoom")
    public @ResponseBody
    String addReservationRoom(@RequestParam("reservationId") Long id_reservation,
                              @RequestParam("room") Long id_room) {
        Room_Reservation room_reservation = new Room_Reservation();
        Reservation reservation = new Reservation();
        reservation.setId_reservation(id_reservation);
        room_reservation.setReservation(reservation);
        Room room1 = new Room();
        room1.setId_room(id_room);
        room_reservation.setRoom(room1);
        room_reservationDao.save(room_reservation);
        return "success";
    }

    @PostMapping(value = "*/addServiceReservation")
    public @ResponseBody
    String addServiceReservation(@RequestParam("reservationId") Long id_reservation,
                                 @RequestParam("serviceId") Long id_services) {
        Reservation reservation5 = new Reservation();
        reservation5.setId_reservation(id_reservation);
        Payment payment = paymentDao.findPaymentByReservation(reservation5);
        Optional<Services> services2 = servicesDao.findById(id_services);
        Optional<Reservation> reservation2 = reservationDao.findById(id_reservation);
        if (reservation2.isPresent()) {
            if (services2.isPresent()) {
                Reservation reservation1 = reservation2.get();
                Services services = services2.get();

                List<Tax> taxList = (List<Tax>) taxDao.findAll();
                Double cem = 0.0;
                for (Tax tax : taxList) {
                    Double rate = tax.getRate();
                    cem += rate;
                }
                Payment payment1 = new Payment();
                if (payment.getServices_cost() == null) {
                    payment1.setServices_cost(services.getAmount());
                } else {
                    payment1.setServices_cost(Math.abs(payment.getServices_cost() + services.getAmount()));
                }


                payment1.setSum(Math.abs(services.getAmount() + payment.getSum()));
                payment1.setPending(Math.abs(payment.getPending() + services.getAmount()));
                payment1.setTax_cost(Math.abs((services.getAmount() + payment.getSum()) / cem));

                PayType payType = new PayType();
                payType.setId_pay_type(payment.getPayType().getId_pay_type());
                payType.setPay_type(payment.getPayType().getPay_type());
                payType.setData_date(payment.getPayType().getData_date());
                payType.setActive(payment.getPayType().getActive());
                payment1.setPayType(payType);


                payment1.setAmount(payment.getAmount());
                payment1.setAdded_date(payment.getAdded_date());
                payment1.setAll_night_cost(payment.getAll_night_cost());
                payment1.setData_date(payment.getData_date());
                payment1.setActive(payment.getActive());
                payment1.setId_payment(payment.getId_payment());
                Reservation reservation = new Reservation();
                Guest guest = new Guest();
                guest.setId_guest(reservation1.getGuest().getId_guest());
                guest.setName(reservation1.getGuest().getName());
                guest.setSurname(reservation1.getGuest().getSurname());
                guest.setGender(reservation1.getGuest().getGender());
                guest.setPhone(reservation1.getGuest().getPhone());
                guest.setEmail(reservation1.getGuest().getEmail());
                guest.setDob(reservation1.getGuest().getDob());
                guest.setPassport_number(reservation1.getGuest().getPassport_number());
                guest.setImage1(reservation1.getGuest().getImage1());
                guest.setImage2(reservation1.getGuest().getImage2());
                guest.setData_date(reservation1.getGuest().getData_date());
                guest.setActive(reservation1.getGuest().getActive());
                City city = new City();
                city.setId_city(reservation1.getGuest().getCity().getId_city());
                city.setName(reservation1.getGuest().getCity().getName());
                city.setData_date(reservation1.getGuest().getCity().getData_date());
                city.setActive(reservation1.getGuest().getCity().getActive());
                Country country = new Country();
                country.setId_country(reservation1.getGuest().getCity().getCountry().getId_country());
                country.setName(reservation1.getGuest().getCity().getCountry().getName());
                country.setSort_name(reservation1.getGuest().getCity().getCountry().getSort_name());
                country.setData_date(reservation1.getGuest().getCity().getCountry().getData_date());
                country.setActive(reservation1.getGuest().getCity().getCountry().getActive());
                city.setCountry(country);
                guest.setCity(city);
                Passport passport = new Passport();
                passport.setId_passport(reservation1.getGuest().getPassport().getId_passport());
                passport.setPassport_type(reservation1.getGuest().getPassport().getPassport_type());
                passport.setData_date(reservation1.getGuest().getPassport().getData_date());
                passport.setActive(reservation1.getGuest().getPassport().getActive());
                guest.setPassport(passport);
                reservation.setGuest(guest);

                RoomType roomType = new RoomType();
                Price price = new Price();
                price.setId_price(reservation1.getRoomType().getPrice().getId_price());
                price.setBase_price(reservation1.getRoomType().getPrice().getBase_price());
                price.setExtra_bed_price(reservation1.getRoomType().getPrice().getExtra_bed_price());
                price.setData_date(reservation1.getRoomType().getPrice().getData_date());
                price.setActive(reservation1.getRoomType().getPrice().getActive());
                roomType.setPrice(price);
                RoomStructure roomStructure = new RoomStructure();
                roomStructure.setId_room_structure(reservation1.getRoomType().getRoomStructure().getId_room_structure());
                roomStructure.setBase_occupancy(reservation1.getRoomType().getRoomStructure().getBase_occupancy());
                roomStructure.setKids_occupancy(reservation1.getRoomType().getRoomStructure().getKids_occupancy());
                roomStructure.setHigher_occupancy(reservation1.getRoomType().getRoomStructure().getHigher_occupancy());
                roomStructure.setExtra_bed(reservation1.getRoomType().getRoomStructure().getExtra_bed());
                roomStructure.setData_date(reservation1.getRoomType().getRoomStructure().getData_date());
                roomStructure.setActive(reservation1.getRoomType().getRoomStructure().getActive());
                roomType.setRoomStructure(roomStructure);
                roomType.setId_room_type(reservation1.getRoomType().getId_room_type());
                roomType.setRoom_type(reservation1.getRoomType().getRoom_type());
                roomType.setSlug(reservation1.getRoomType().getSlug());
                roomType.setShort_code(reservation1.getRoomType().getShort_code());
                roomType.setDescription(reservation1.getRoomType().getDescription());
                roomType.setImage(reservation1.getRoomType().getImage());
                roomType.setData_date(reservation1.getRoomType().getData_date());
                roomType.setActive(reservation1.getRoomType().getActive());
                reservation.setRoomType(roomType);
                Hotel hotel = new Hotel();
                Star star = new Star();
                star.setId_star(reservation1.getHotel().getStar().getId_star());
                star.setStar(reservation1.getHotel().getStar().getStar());
                star.setData_date(reservation1.getHotel().getStar().getData_date());
                star.setActive(reservation1.getHotel().getStar().getActive());
                hotel.setStar(star);
                hotel.setId_hotel(reservation1.getHotel().getId_hotel());
                hotel.setName(reservation1.getHotel().getName());
                hotel.setAddress(reservation1.getHotel().getAddress());
                hotel.setPhone(reservation1.getHotel().getPhone());
                hotel.setEmail(reservation1.getHotel().getEmail());
                hotel.setLogo(reservation1.getHotel().getLogo());
                hotel.setData_date(reservation1.getHotel().getData_date());
                hotel.setActive(reservation1.getHotel().getActive());
                reservation.setHotel(hotel);

                BookingStatus bookingStatus1 = new BookingStatus();
                bookingStatus1.setId_booking_status(reservation1.getBookingStatus().getId_booking_status());
                bookingStatus1.setBooking_status(reservation1.getBookingStatus().getBooking_status());
                bookingStatus1.setData_date(reservation1.getBookingStatus().getData_date());
                bookingStatus1.setActive(reservation1.getBookingStatus().getActive());
                reservation.setBookingStatus(bookingStatus1);

                PaymentStatus paymentStatus = new PaymentStatus();
                paymentStatus.setId_payment_status(reservation1.getPaymentStatus().getId_payment_status());
                paymentStatus.setPayment_status(reservation1.getPaymentStatus().getPayment_status());
                paymentStatus.setData_date(reservation1.getPaymentStatus().getData_date());
                paymentStatus.setActive(reservation1.getPaymentStatus().getActive());
                reservation.setPaymentStatus(paymentStatus);
                reservation.setAdults(reservation1.getAdults());
                reservation.setChildren(reservation1.getChildren());
                reservation.setExtra_bed(reservation1.getExtra_bed());
                reservation.setNight(reservation1.getNight());
                reservation.setCheck_in(reservation1.getCheck_in());
                reservation.setCheck_out(reservation1.getCheck_out());
                reservation.setData_date(reservation1.getData_date());
                reservation.setActive(reservation1.getActive());
                reservation.setId_reservation(id_reservation);
                payment1.setReservation(reservation);
                //boolean isUpdate = paymentService.updatePayment1(payment1);


                paymentDao.save(payment1);
                Service_Reservation service_reservation = new Service_Reservation();
                Reservation reservation3 = new Reservation();
                reservation3.setId_reservation(id_reservation);
                service_reservation.setReservation(reservation3);
                Services services1 = new Services();
                services1.setId_services(id_services);
                service_reservation.setServices(services1);
                service_reservationDao.save(service_reservation);
                return "success";
            }
        }
        //boolean isAdded = service_reservationService.addService_Reservation(service_reservation);
        return "success";
    }
}
