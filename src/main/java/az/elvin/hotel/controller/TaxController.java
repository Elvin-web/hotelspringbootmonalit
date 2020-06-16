package az.elvin.hotel.controller;

import az.elvin.hotel.dao.TaxDao;
import az.elvin.hotel.dao.TaxTypeDao;
import az.elvin.hotel.model.Tax;
import az.elvin.hotel.model.TaxType;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TaxController {
    @Autowired
    private TaxDao taxDao;
    @Autowired
    private TaxTypeDao taxTypeDao;
    @GetMapping(value = "/getTaxList")
    public ModelAndView getTaxList() {
        ModelAndView model = new ModelAndView("tax/taxList");
        List<Tax> taxList = (List<Tax>) taxDao.findAll();
        model.addObject("taxList", taxList);
        return model;
    }

    @GetMapping(value = "/newTax")
    public ModelAndView newTax() {
        ModelAndView model = new ModelAndView("tax/newTax");
        List<TaxType> taxTypeList = (List<TaxType>) taxTypeDao.findAll();
        model.addObject("taxTypeList", taxTypeList);
        return model;
    }

    @PostMapping(value = "/addTax")
    public @ResponseBody
    String addTax(
            @RequestParam("name") String name,
            @RequestParam("code") String code,
            @RequestParam("taxTypeId") String taxTypeId,
            @RequestParam("rate") Double rate) {
        Tax tax = new Tax();
        tax.setName(name);
        tax.setCode(code);
        tax.setRate(rate);
        TaxType taxType = new TaxType();
        taxType.setId_tax_type(Long.parseLong(taxTypeId));
        tax.setTaxType(taxType);
        taxDao.save(tax);
        return "success";
    }

    @GetMapping("/editTax/{id}")
    public ModelAndView editTax(
            @PathVariable(value = "id") Long id_tax) {
        ModelAndView model = new ModelAndView("tax/editTax");
        Optional<Tax> tax = taxDao.findById(id_tax);
        if (tax.isPresent()) {
            Tax tax1 = tax.get();
            model.addObject("tax1", tax1);
        }
        List<TaxType> taxTypeList = (List<TaxType>) taxTypeDao.findAll();
        model.addObject("taxTypeList", taxTypeList);
        return model;
    }
    @PostMapping(value = "*/updateTax")
    public @ResponseBody
    String updateTax(
            @RequestParam("name") String name,
            @RequestParam("code") String code,
            @RequestParam("taxType") Long id_tax_type,
            @RequestParam("rate") Double rate,
            @RequestParam("taxId") Long id_tax) {
        Optional<Tax> tax2 = taxDao.findById(id_tax);
        Optional<TaxType> taxType2 = taxTypeDao.findById(id_tax_type);
        if (taxType2.isPresent()) {
            if (tax2.isPresent()) {
                Tax tax1 = tax2.get();
                TaxType taxType1 = taxType2.get();
                Tax tax = new Tax();
                TaxType taxType = new TaxType();
                taxType.setId_tax_type(id_tax_type);
                taxType.setType(taxType1.getType());
                taxType.setActive(taxType1.getActive());
                taxType.setData_date(taxType1.getData_date());
                tax.setTaxType(taxType);
                tax.setName(name);
                tax.setCode(code);
                tax.setRate(rate);
                tax.setActive(tax1.getActive());
                tax.setData_date(tax1.getData_date());
                tax.setId_tax(id_tax);
                taxDao.save(tax);
                return "success";
            }
        }
        return "success";
    }



    @PostMapping(value = "/deleteTax")
    public @ResponseBody
    String deleteTax(@RequestParam("taxId") Long id_tax) {
        Optional<Tax> tax2 = taxDao.findById(id_tax);
        if (tax2.isPresent()) {
            Tax tax1 = tax2.get();
            Tax tax = new Tax();
            TaxType taxType = new TaxType();
            taxType.setId_tax_type(tax1.getTaxType().getId_tax_type());
            taxType.setType(tax1.getTaxType().getType());
            taxType.setActive(tax1.getTaxType().getActive());
            taxType.setData_date(tax1.getTaxType().getData_date());
            tax.setTaxType(taxType);
            tax.setActive(0);
            tax.setName(tax1.getName());
            tax.setCode(tax1.getCode());
            tax.setRate(tax1.getRate());
            tax.setData_date(tax1.getData_date());
            tax.setId_tax(id_tax);
            taxDao.save(tax);
            return "success";

        }
        return "success";
    }
}
