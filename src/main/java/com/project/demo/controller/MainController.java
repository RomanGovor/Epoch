package com.project.demo.controller;

import com.project.demo.domain.Message;
import com.project.demo.domain.User;
import com.project.demo.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Component
@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @Cacheable("sums")
    public int forRealizeCache(int b){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){}
        return b;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {

        int ress = forRealizeCache(40);
        System.out.println("*************cache*********" + ress);

        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {

        int ress = forRealizeCache(30);
        System.out.println("*************cache*********" + ress);

        Iterable<Message> messages = messageRepo.findAll();

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }

        model.addAttribute("messages",messages);
        model.addAttribute("filter",filter);

        return "main";
    }



//    private byte num_of_auto_mes = 0; //
//    @Scheduled(fixedRate = 600 * 1000)
//    public void createAutomaticMessage() throws InterruptedException{
//
//         Date date=java.util.Calendar.getInstance().getTime();
//         num_of_auto_mes++;
//
//         String text_temp = "auto message creation test -" + num_of_auto_mes;
//         String tag_temp =  "d: " + date;
//
//         Message message = new Message(text_temp, tag_temp);
//         System.out.println("Message auto post - " + num_of_auto_mes);
//
//         messageRepo.save(message);
//    }
    @Transactional
    @Cacheable("sums")
    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam String text,
                      @RequestParam String tag, Map<String, Object> model,
                      @RequestParam("file") MultipartFile file
    ) throws IOException {

        int k = 0,flag = 0;

        if(text == "" || tag == ""){
            flag = 1;
        }else {

            for (int i = 0; i < text.length(); i++) {   // Проверка на пробелы
                if (text.charAt(i) == ' ')
                    k++;
            }
            if (k == text.length())
                flag = 1;
            /**/
            k = 0;
            for (int i = 0; i < tag.length(); i++) {
                if (tag.charAt(i) == ' ')
                    k++;
            }
            if (k == tag.length())
                flag = 1;
            /**/
        }
        if (flag == 0)
        {
            text.replaceAll("\\s+","");
            tag.replaceAll("\\s+","");
        }
        Message message = new Message(text, tag, user);

        if(file != null){
            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists() && !file.getOriginalFilename().isEmpty()){
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            message.setFilename(resultFilename);
        }

        if(flag == 0)
           messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();

        model.put("messages", messages);
        model.put("filter", "");

        return "main";
    }

}
