package az.elvin.hotel.controller;

import az.elvin.hotel.dao.ExpensesTypeDao;
import az.elvin.hotel.model.ExpensesType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ExpensesTypeController {
    @Autowired
    private ExpensesTypeDao expensesTypeDao;


    @GetMapping(value = "/getExpensesTypeList")
    public ModelAndView getExpensesTypeList() {
        ModelAndView model = new ModelAndView("expensesType/expensesTypeList");
        List<ExpensesType> expensesTypeList = (List<ExpensesType>) expensesTypeDao.findAll();
        model.addObject("expensesTypeList", expensesTypeList);
        return model;
    }

    @GetMapping(value = "/newExpensesType")
    public ModelAndView newExpensesType() {
        ModelAndView model = new ModelAndView("expensesType/newExpensesType");
        return model;
    }

    @PostMapping(value = "/addExpensesType")
    public @ResponseBody
    String addExpensesType(
            @RequestParam("name") String name) {
        ExpensesType expensesType = new ExpensesType();
        expensesType.setExpenses_type(name);
        expensesTypeDao.save(expensesType);
        return "success";
    }

    @GetMapping("/editExpensesType/{id}")
    public ModelAndView editExpensesType(
            @PathVariable(value = "id") Long id_expenses_type) {
        ModelAndView model = new ModelAndView("expensesType/editExpensesType");
        Optional<ExpensesType> expensesType = expensesTypeDao.findById(id_expenses_type);
        if (expensesType.isPresent()) {
            ExpensesType expensesType1 = expensesType.get();
            model.addObject("expensesType1", expensesType1);
        }

        return model;
    }

    @RequestMapping(value = "*/updateExpensesType", method = RequestMethod.POST)
    public @ResponseBody
    String updateExpensesType(
            @RequestParam("name") String name,
            @RequestParam("expensesCategoryId") Long id_expenses_type) {
        Optional<ExpensesType> expensesType1 = expensesTypeDao.findById(id_expenses_type);
        if (expensesType1.isPresent()) {
            ExpensesType expensesType2 = expensesType1.get();
            ExpensesType expensesType = new ExpensesType();
            expensesType.setId_expenses_type(id_expenses_type);
            expensesType.setExpenses_type(name);
            expensesType.setActive(expensesType2.getActive());
            expensesType.setData_date(expensesType2.getData_date());
            expensesTypeDao.save(expensesType);
            return "success";
        }
        return "success";
    }

    @PostMapping(value = "/deleteExpensesType")
    public @ResponseBody
    String deleteExpensesType(
            @RequestParam("expensesCategoryId") Long id_expenses_type) {
        Optional<ExpensesType> expensesType1 = expensesTypeDao.findById(id_expenses_type);
        if (expensesType1.isPresent()) {
            ExpensesType expensesType2 = expensesType1.get();
            ExpensesType expensesType = new ExpensesType();
            expensesType.setId_expenses_type(id_expenses_type);
            expensesType.setExpenses_type(expensesType2.getExpenses_type());
            expensesType.setActive(0);
            expensesType.setData_date(expensesType2.getData_date());
            expensesTypeDao.save(expensesType);
            return "success";
        }
        return "success";
    }
}