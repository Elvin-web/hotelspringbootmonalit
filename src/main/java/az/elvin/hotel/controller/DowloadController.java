package az.elvin.hotel.controller;

import az.elvin.hotel.dao.ExpensesDao;
import az.elvin.hotel.model.Expenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Optional;

@RestController
public class DowloadController {
    @Autowired
    private ExpensesDao expensesDao;

 /*   @GetMapping("/dowloadExpenses/{id}")
    public ModelAndView dowloadExpenses(
            @PathVariable(value = "id") Long id_expenses, HttpServletRequest request, HttpServletResponse response) {

        ModelAndView model = new ModelAndView("expenses/expensesList");
        Optional<Expenses> expenses = expensesDao.findById(id_expenses);
        try {
            if (expenses.isPresent()) {
                Expenses expenses1 = expenses.get();
                String filePath2 = expenses1.getDocument();
                System.out.println("filePath2" + filePath2);
                File dowloadFile = new File(filePath2);
                FileInputStream inStream = new FileInputStream(dowloadFile);
                ServletContext context = getServletContext();
                String mimeType = context.getMimeType(filePath2);
                if (mimeType == null) {
                    mimeType = "application/octet-stream";
                }
                response.setContentType(mimeType);
                response.setContentLength((int) dowloadFile.length());
                String headerKey = "Content-Disposition";
                String headerValue = String.format("attachment; filename=\"%s\"", dowloadFile.getName());
                response.setHeader(headerKey, headerValue);
                OutputStream outStream = response.getOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                while ((bytesRead = inStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }
                inStream.close();
                outStream.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }*/
}
