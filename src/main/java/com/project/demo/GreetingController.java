package com.project.demo;

import com.project.demo.domain.Message;
import com.project.demo.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class GreetingController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {

        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findAll();

        model.put("messages", messages);

        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {

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
        Message message = new Message(text, tag);

       if(flag == 0)
           messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();

        model.put("messages", messages);

        return "main";
    }


    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Message> messages;

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }

        model.put("messages", messages);

        return "main";
    }

    @DeleteMapping("/delete")
       public void delete(@RequestParam String delete, Map<String, Object> model) {
        Iterable<Message> messages;

        if (delete != null && !delete.isEmpty()) {
            messages = messageRepo.findByTag(delete);
        } else {
           messages = messageRepo.findAll();
        }

       // model.remove("messages", messages);
        //messageRepo.save(messages);
       // messageRepo.saveAll(messages);
        messageRepo.deleteAll(messages);
    }

}
