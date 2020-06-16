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
import java.time.LocalDate;
import java.util.*;

@RestController
public class MainController {
    @Autowired
    private LoginDao loginDao;
    @Autowired
    private PaymentDao paymentDao;
    private static final String UPLOAD_DIRECTORY = "upload";
    private static final int THRESHOLD_SIZE = 1024 * 1024 * 3;
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 10;
    private static final int REQUEST_SIZE = 1024 * 1024 * 50;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @GetMapping(value = {"/", "/webSite"})
    public ModelAndView webSite() {
        ModelAndView model = new ModelAndView("webSite");
        return model;
    }

    @GetMapping(value = "/login1")
    public ModelAndView login() {

        ModelAndView model = new ModelAndView("login");
        return model;
    }


    @PostMapping(value = "/loginU")
    public @ResponseBody
    ModelAndView loginU(
            @RequestParam("username") String username,
            @RequestParam("password") String password, HttpServletRequest request) {

        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            LoginUser loginUser = loginDao.findLoginUserByUsernameAndPassword(username, password);
            if (loginUser != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("loginUser", loginUser);
                List<Payment>  paymentList= (List<Payment>) paymentDao.findAll();
                session.setAttribute("paymentList", paymentList);
                Map<Object,Object> map = null;
                List<List<Map<Object,Object>>> list = new ArrayList<List<Map<Object,Object>>>();
                List<Map<Object,Object>> dataPoints1 = new ArrayList<Map<Object,Object>>();
/*    /* List<LocalDate> stringList = new ArrayList<>();
                stringList.add(LocalDate.now().minusDays(7));
                stringList.add(LocalDate.now().minusDays(6));
                stringList.add(LocalDate.now().minusDays(5));
                stringList.add(LocalDate.now().minusDays(4));
                stringList.add(LocalDate.now().minusDays(3));
                stringList.add(LocalDate.now().minusDays(2));
                stringList.add(LocalDate.now().minusDays(1));
                stringList.add(LocalDate.now());*/

              /*  for (Payment payment : paymentList) {
                    //  for (int i = 0; stringList.size() > 0; i++) {
                    map = new HashMap<Object, Object>();
                    //    LocalDate element = stringList.get(i);
                    //  if (element.equals(payment.getData_date())) {
                    map.put("x", payment.getData_date());
                    map.put("y", payment.getSum());
                    dataPoints1.add(map);
                    //} else {
                    //  map.put("x", stringList.get(i));
                    // map.put("y", 0);
                    //dataPoints1.add(map);
                    // }
                    //}
                    list.add(dataPoints1);
                }*/
                for (Payment payment:paymentList) {
                    map = new HashMap<Object,Object>();
                    map.put("x", payment.getData_date());
                    map.put("y", payment.getSum());
                    dataPoints1.add(map);
                }
                list.add(dataPoints1);
                session.setAttribute("dataPointsList", list);
                ModelAndView model = new ModelAndView("index");
                return model;
            } else {
                ModelAndView model = new ModelAndView("login");
                //  return "invalid, Username or password is invalid!";
                return model;
            }
        } else {
            ModelAndView model = new ModelAndView("login");
            //return "invalid , Username or password is empty!";
            return model;
        }
    }

}

