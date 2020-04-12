package com.project.demo.controller;

import com.project.demo.domain.Message;
import com.project.demo.exeptions.NotFoundExceptions;
import io.swagger.annotations.Api;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

@RestController
@RequestMapping("testmessage")                    //Запрос
@Api(value = "/testmessage")
public class MessageController implements IDelete{
    private int counter = 4;

    private List <Map<String,String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap <String,String>() {{
                put("id", "1");
                put("Text", "Message 1"); }
        });
        add(new HashMap <String,String>() {{
            put("id", "2");
            put("Text", "Message 2"); }
        });
        add(new HashMap <String,String>() {{
            put("id", "3");
            put("Text", "Message 3"); }
        });
    }};

    @GetMapping
    public List <Map<String,String>> list() {
        return messages;
    }

    @GetMapping("{id}")
    public Map<String, String> getOne(@PathVariable String id){
        return getMessage(id);
    }

    private Map<String, String> getMessage(@PathVariable String id) {
        return messages.stream()
                .filter(messages -> messages.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundExceptions::new);
    }

    @PostMapping              //Добавление нового метода
    public Map<String,String> create(@RequestBody Map<String, String> message) {
        message.put("id",String.valueOf(counter++));
        messages.add(message);
        return message;
    }

    @PutMapping("{id}")                                                          //Добавление текущей записи
    public   Map<String,String> update(@PathVariable String id, @RequestBody Map<String, String> message) {
        Map<String, String> messageFromDb = getMessage(id);

        messageFromDb.putAll(message);
        messageFromDb.put("id",id);

        return messageFromDb;
    }


    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Map<String,String> message = getMessage(id);

        messages.remove(message);
    }
}
