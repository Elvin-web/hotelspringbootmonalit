package az.elvin.hotel.controller;

import az.elvin.hotel.dao.ExpensesDao;
import az.elvin.hotel.dao.ExpensesTypeDao;
import az.elvin.hotel.model.Expenses;
import az.elvin.hotel.model.ExpensesType;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ExpensesController {
    @Autowired
    private ExpensesDao expensesDao;
    @Autowired
    private ExpensesTypeDao expensesTypeDao;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private static final String UPLOAD_DIRECTORY = "upload";
    private static final int THRESHOLD_SIZE = 1024 * 1024 * 3;
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 10;
    private static final int REQUEST_SIZE = 1024 * 1024 * 50;

    @GetMapping(value = "/getExpensesList")
    public ModelAndView getExpensesList() {
        ModelAndView model = new ModelAndView("expenses/expensesList");
        List<Expenses> expensesList = (List<Expenses>) expensesDao.findAll();
        model.addObject("expensesList", expensesList);
        return model;
    }

    @GetMapping(value = "/newExpenses")
    public ModelAndView newExpenses() {
        ModelAndView model = new ModelAndView("expenses/newExpenses");
        List<ExpensesType> expensesTypeList = (List<ExpensesType>) expensesTypeDao.findAll();
        model.addObject("expensesTypeList", expensesTypeList);
        return model;
    }

    @PostMapping(value = "/addExpenses")
    public @ResponseBody
    ModelAndView addExpenses(@RequestParam("name") String name,
                       @RequestParam("dataInsert") String dataInsert,
                       @RequestParam("expensesType") Long expensesType,
                       @RequestParam("amount") Double amount,
                       @RequestParam("fileName1") MultipartFile fileName1,
                       HttpServletRequest request, HttpServletResponse response) {


        ModelAndView model = new ModelAndView("expenses/expensesList");
        List<Expenses> expensesList = (List<Expenses>) expensesDao.findAll();
        model.addObject("expensesList", expensesList);
        Expenses expenses = new Expenses();
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
            expenses.setDocument(filePath);
            expenses.setDate_insert(df.parse(dataInsert));
            System.out.println("dataInsert=" + dataInsert);
            expenses.setName(name);
            System.out.println("name=" + name);
            ExpensesType expensesType1 = new ExpensesType();
            expensesType1.setId_expenses_type(expensesType);
            System.out.println("expensesType=" + expensesType);
            expenses.setExpensesType(expensesType1);
            expenses.setAmount(amount);
            System.out.println("amount=" + amount);
            expensesDao.save(expenses);
            response.setContentType("text/html");
            //return "success";
           // return new ModelAndView("expenses/expensesList");
              return model;
        } catch (Exception ex) {
            request.setAttribute("message", "There was an error:" + ex.getMessage());
            ex.printStackTrace();
        }
        //return "success";
         return model;
       // return new ModelAndView("expenses/expensesList");
    }

    @GetMapping("/editExpenses/{id}")
    public ModelAndView editExpenses(
            @PathVariable(value = "id") Long id_expenses) {
        ModelAndView model = new ModelAndView("expenses/editExpenses");
        Optional<Expenses> expenses = expensesDao.findById(id_expenses);
        if (expenses.isPresent()) {
            Expenses expenses1 = expenses.get();
            model.addObject("expenses1", expenses1);
            String filePath = expenses1.getDocument().substring(expenses1.getDocument().lastIndexOf("\\"));
            String filePath2 = "upload" + File.separator + filePath;
            model.addObject("filePath2", filePath2);
        }
        List<ExpensesType> expensesTypeList = (List<ExpensesType>) expensesTypeDao.findAll();
        model.addObject("expensesTypeList", expensesTypeList);
        return model;
    }


    @PostMapping(value = "*/updateExpenses")
    public @ResponseBody
    ModelAndView updateExpenses(@RequestParam("id") Long id_expenses,
                          @RequestParam("name") String name,
                          @RequestParam("dataInsert") String dataInsert,
                          @RequestParam("expensesType") Long id_expenses_type,
                          @RequestParam("amount") Double amount,
                          @RequestParam("fileName1") MultipartFile fileName1,
                          HttpServletRequest request, HttpServletResponse response) {

        ModelAndView model = new ModelAndView("expenses/expensesList");
        List<ExpensesType> expensesTypeList = (List<ExpensesType>) expensesTypeDao.findAll();
        model.addObject("expensesTypeList", expensesTypeList);
        String filePath = "";
        String fileName = "";
        String newFileName = "";
        if (fileName1.isEmpty()) {
            //return "Please select a file and try again";
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
            Optional<Expenses> expenses2 = expensesDao.findById(id_expenses);
            Optional<ExpensesType> expensesType2 = expensesTypeDao.findById(id_expenses_type);
            if (expensesType2.isPresent()) {
                if (expenses2.isPresent()) {
                    Expenses expenses1 = expenses2.get();
                    ExpensesType expensesType1 = expensesType2.get();
                    Expenses expenses = new Expenses();
                    ExpensesType expensesType = new ExpensesType();
                    UUID uuid = UUID.randomUUID();
                    byte[] bytes = fileName1.getBytes();
                    String fileName2 = fileName1.getOriginalFilename();
                    newFileName = fileName2.replace(fileName2.substring(0, fileName2.lastIndexOf(".")), uuid.toString());
                    filePath = uploadPath + File.separator + newFileName;
                    Path path = Paths.get(filePath);
                    Files.write(path, bytes);
                    expenses.setDocument(filePath);
                    expenses.setDate_insert(df.parse(dataInsert));
                    expenses.setName(name);
                    expenses.setAmount(amount);
                    expenses.setId_expenses(id_expenses);
                    expenses.setData_date(expenses1.getData_date());
                    expenses.setActive(expenses1.getActive());
                    expensesType.setId_expenses_type(id_expenses_type);
                    expenses.setExpensesType(expensesType);
                    expensesType.setExpenses_type(expensesType1.getExpenses_type());
                    expensesType.setData_date(expensesType1.getData_date());
                    expensesType.setActive(expensesType1.getActive());
                    expensesDao.save(expenses);
                    response.setContentType("text/html");
                    //return "success";

                     return model;
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message", "There was an error:" + ex.getMessage());
            ex.printStackTrace();
        }
        // return "success";
         return model;
    }

    @PostMapping(value = "/deleteExpenses")
    public @ResponseBody
    String deleteExpenses(@RequestParam("expensesId") Long id_expenses) {

        ModelAndView model = new ModelAndView("expenses/expensesList");
        List<ExpensesType> expensesTypeList = (List<ExpensesType>) expensesTypeDao.findAll();
        model.addObject("expensesTypeList", expensesTypeList);

        Optional<Expenses> expenses2 = expensesDao.findById(id_expenses);
        if (expenses2.isPresent()) {
            Expenses expenses1 = expenses2.get();
            Expenses expenses = new Expenses();
            ExpensesType expensesType = new ExpensesType();
            expenses.setDocument(expenses1.getDocument());
            expenses.setDate_insert(expenses1.getDate_insert());
            expenses.setName(expenses1.getName());
            expenses.setAmount(expenses1.getAmount());
            expenses.setActive(0);
            expenses.setId_expenses(id_expenses);
            expenses.setData_date(expenses1.getData_date());
            expensesType.setId_expenses_type(expenses1.getExpensesType().getId_expenses_type());
            expensesType.setExpenses_type(expenses1.getExpensesType().getExpenses_type());
            expensesType.setData_date(expenses1.getExpensesType().getData_date());
            expensesType.setActive(expenses1.getExpensesType().getActive());
            expenses.setExpensesType(expensesType);
            expensesDao.save(expenses);
            return "success";

            // return model;
        }

        return"success";
    // return model;
}
}
