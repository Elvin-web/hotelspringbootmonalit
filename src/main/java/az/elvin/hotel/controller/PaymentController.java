package az.elvin.hotel.controller;

import az.elvin.hotel.dao.PayTypeDao;
import az.elvin.hotel.dao.PaymentDao;
import az.elvin.hotel.dao.ReservationDao;
import az.elvin.hotel.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@RestController
public class PaymentController {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private PaymentDao paymentDao;
    @Autowired
    private ReservationDao reservationDao;
    @Autowired
    private PayTypeDao payTypeDao;

    @GetMapping("*/viewPayment/{id}")
    public ModelAndView viewPayment(
            @PathVariable(value = "id") Long id_payment) {
        ModelAndView model = new ModelAndView("payment/viewPayment");
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

    @GetMapping("*/newPayment/{id}")
    public ModelAndView newPayment(
            @PathVariable(value = "id") Long id_reservation) {
        ModelAndView model = new ModelAndView("payment/newPayment");
        Reservation reservation = new Reservation();
        reservation.setId_reservation(id_reservation);
        Payment payment = paymentDao.findPaymentByReservation(reservation);
        model.addObject("payment", payment);
        List<PayType> payTypeList = (List<PayType>) payTypeDao.findAll();
        model.addObject("payTypeList", payTypeList);
        return model;
    }

    @GetMapping("*/editPayment/{id}")
    public ModelAndView editPayment(
            @PathVariable(value = "id") Long id_payment) {
        ModelAndView model = new ModelAndView("payment/editPayment");
        Optional<Payment> payment2 = paymentDao.findById(id_payment);
        if (payment2.isPresent()) {
            Payment payment = payment2.get();
            model.addObject("payment", payment);
        }
        List<PayType> payTypeList = (List<PayType>) payTypeDao.findAll();
        model.addObject("payTypeList", payTypeList);
        return model;
    }

    @PostMapping(value = "*/*/addPayment")
    public @ResponseBody
    String addPayment(@RequestParam("paymentId") Long id_payment,
                      @RequestParam("reservationId") Long id_reservation,
                      @RequestParam("paymentMethod") Long id_pay_type,
                      @RequestParam("addedDate") String addedDate,
                      @RequestParam("amount") Double amount) {
        Optional<Payment> payment2 = paymentDao.findById(id_payment);
        Optional<PayType> payType2 = payTypeDao.findById(id_pay_type);
        Optional<Reservation> reservation2 = reservationDao.findById(id_reservation);
        if (reservation2.isPresent()) {
            if (payType2.isPresent()) {
                if (payment2.isPresent()) {
                    try {
                        Payment payment1 = payment2.get();
                        Reservation reservation1 = reservation2.get();
                        PayType payType1 = payType2.get();
                        Payment payment = new Payment();
                        // payment.setAmount(amount);
                        payment.setAmount(Math.abs(payment1.getAmount() + Double.valueOf(amount)));

                        payment.setAdded_date(df.parse(addedDate));

                        payment.setAll_night_cost(payment1.getAll_night_cost());
                        payment.setServices_cost(payment1.getServices_cost());
                        payment.setSum(payment1.getSum());
                        // payment.setPending(payment1.getPending());
                        payment.setPending(Math.abs(payment1.getPending() - Double.valueOf(amount)));
                        payment.setTax_cost(payment1.getTax_cost());
                        payment.setActive(payment1.getActive());
                        payment.setData_date(payment1.getData_date());


                        PayType payType = new PayType();
                        payType.setId_pay_type(id_pay_type);
                        payType.setPay_type(payType1.getPay_type());
                        payType.setData_date(payType1.getData_date());
                        payType.setActive(payType1.getActive());
                        payment.setPayType(payType);

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
                        reservation.setActive(reservation1.getActive());
                        reservation.setId_reservation(id_reservation);
                        payment.setReservation(reservation);
                        payment.setId_payment(id_payment);
                        paymentDao.save(payment);
                        return "success";
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return "success";
    }
    @PostMapping(value = "*/*/updatePayment")
    public @ResponseBody
    String updatePayment(@RequestParam("paymentId") Long id_payment,
                      @RequestParam("reservationId") Long id_reservation,
                      @RequestParam("paymentMethod") Long id_pay_type,
                      @RequestParam("addedDate") String addedDate,
                      @RequestParam("amount") Double amount) {
        Optional<Payment> payment2 = paymentDao.findById(id_payment);
        Optional<PayType> payType2 = payTypeDao.findById(id_pay_type);
        Optional<Reservation> reservation2 = reservationDao.findById(id_reservation);
        if (reservation2.isPresent()) {
            if (payType2.isPresent()) {
                if (payment2.isPresent()) {
                    try {
                        Payment payment1 = payment2.get();
                        Reservation reservation1 = reservation2.get();
                        PayType payType1 = payType2.get();
                        Payment payment = new Payment();
                        payment.setAmount(amount);
                       // payment.setAmount(Math.abs(payment1.getAmount() + Double.valueOf(amount)));

                        payment.setAdded_date(df.parse(addedDate));

                        payment.setAll_night_cost(payment1.getAll_night_cost());
                        payment.setServices_cost(payment1.getServices_cost());
                        payment.setSum(payment1.getSum());
                        // payment.setPending(payment1.getPending());
                        payment.setPending(Math.abs(payment1.getSum() - Double.valueOf(amount)));
                        payment.setTax_cost(payment1.getTax_cost());
                        payment.setActive(payment1.getActive());
                        payment.setData_date(payment1.getData_date());


                        PayType payType = new PayType();
                        payType.setId_pay_type(id_pay_type);
                        payType.setPay_type(payType1.getPay_type());
                        payType.setData_date(payType1.getData_date());
                        payType.setActive(payType1.getActive());
                        payment.setPayType(payType);

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
                        reservation.setActive(reservation1.getActive());
                        reservation.setId_reservation(id_reservation);
                        payment.setReservation(reservation);
                        payment.setId_payment(id_payment);
                        paymentDao.save(payment);
                        return "success";
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return "success";
    }
}
