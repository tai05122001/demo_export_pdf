package com.example.demo_export_pdf.controller;

import com.example.demo_export_pdf.model.Bill;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.print.Doc;
import java.io.*;
import java.nio.charset.StandardCharsets;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(Model model) {

        // add attribute into form
        model.addAttribute("billForm", new Bill());

        return "bill";
    }

//    @PostMapping("/messageBill")
//    public String createPDF(@ModelAttribute Bill bill, Model model){
//        System.out.println(bill.toString());
//
////        exportPDF(bill.getIdBill(), bill.getNameBill(),bill.getDateBill(),bill.getUrlFile());
//
//        model.addAttribute("notification","Tạo ảnh thành công");
//        return "message";
//    }

    @PostMapping("/message")
    public String createPDF( @ModelAttribute Bill bill, Model model){
        System.out.println(bill.toString());

        model.addAttribute("notification", "Create PDF for bill sucessfully!");

        //function use to create pdf contains bill details
        exportPDF(bill.getIdBill(),
                bill.getNameBill(),
                bill.getDateBill(),
                bill.getNameCustomer(),
                bill.getAddressCustomer() ,
                "F:\\SparkMinds\\file_bc\\demo\\demo_export_PDF\\bill.pdf" );
        return "notification";

    }

    private void exportPDF(int idBill, String nameBill, String dateBill,String nameCustomer, String addressCustomer, String urlFile) {
        String urlHtml = "F:\\SparkMinds\\file_bc\\demo\\demo_export_PDF\\src\\main\\resources\\templates\\billForm.html";
        InputStream inputStream = null ;
        try {
            //open file html read content into file
            inputStream = new FileInputStream(urlHtml);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            String html = new String(buffer);

            // create object TemplateEngine of  Thymeleaf
            TemplateEngine templateEngine = new TemplateEngine();
            // create object to pass value parameter
            Context context = new Context();
            context.setVariable("idBill", idBill);
            context.setVariable("nameCustomer", nameCustomer);
            context.setVariable("addressCustomer", addressCustomer);
            context.setVariable("dateBill", dateBill);
            context.setVariable("nameBill", nameBill);

            ConverterProperties converterProperties = new ConverterProperties();

            // process template HTML and paste into parameter
            String processedHtmlContent = templateEngine.process(html, context );
            // Convert  HTML to PDF and save a PDF file
            HtmlConverter.convertToPdf(processedHtmlContent, new FileOutputStream(urlFile) , converterProperties);
            System.out.println("PDF file has been created.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(inputStream!=null ){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
