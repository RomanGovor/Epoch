package com.project.demo.exeptions;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@ControllerAdvice
@Controller
public class ExceptionHandlingSpring implements ErrorController{                  // Обработчик вэб запроса
    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        Date date=java.util.Calendar.getInstance().getTime();                    // Получение текущего время
        if(response.getStatus() == HttpStatus.NOT_FOUND.value()) {               // 404
            modelAndView.setViewName("error");
            modelAndView.addObject("errorCode", "Error 404");
            modelAndView.addObject("errorMessage", "Page not found");
            modelAndView.addObject("solution", "Сheck the data is correct");
            modelAndView.addObject("time", date);
        }
        else if(response.getStatus() == HttpStatus.BAD_REQUEST.value()) {         // Плохой запрос
            modelAndView.setViewName("error");
            modelAndView.addObject("errorCode", "Error 400");
            modelAndView.addObject("errorMessage", "Bad request");
            modelAndView.addObject("solution", "Check query syntax");
            modelAndView.addObject("time", date);
        }
        else if(response.getStatus() == HttpStatus.UNAUTHORIZED.value()) {         // Не найден контент
            modelAndView.setViewName("error");
            modelAndView.addObject("errorCode", "Error 401");
            modelAndView.addObject("errorMessage", "UNAUTHORIZED");
            modelAndView.addObject("solution", "Check your autorization");
            modelAndView.addObject("time", date);
        }
        else if(response.getStatus() == HttpStatus.NOT_ACCEPTABLE.value()) {         // Не авторизован
            modelAndView.setViewName("error");
            modelAndView.addObject("errorCode", "Error 406");
            modelAndView.addObject("errorMessage", "Not Acceptable");
            modelAndView.addObject("solution", "Check access to your data");
            modelAndView.addObject("time", date);
        }
        else if(response.getStatus() == HttpStatus.GONE.value()) {         // Контент удален
            modelAndView.setViewName("error");
            modelAndView.addObject("errorCode", "Error 410");
            modelAndView.addObject("errorMessage", "Gone");
            modelAndView.addObject("solution", "Сheck content integrity");
            modelAndView.addObject("time", date);
        }
        else if(response.getStatus() == HttpStatus.FORBIDDEN.value()) {         // Защищено
            modelAndView.setViewName("error");
            modelAndView.addObject("errorCode", "Error 403");
            modelAndView.addObject("errorMessage", "Forbidden");
            modelAndView.addObject("solution", "Сheck access to files if it is protected");
            modelAndView.addObject("time", date);
        }
        else if(response.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {    // Ошибка сервера
            modelAndView.setViewName("error");
            modelAndView.addObject("errorCode", "Error 500");
            modelAndView.addObject("errorMessage", "Internal server error");
            modelAndView.addObject("solution", "Restart your computer");
            modelAndView.addObject("time", date);
        }
        else {                                                                      // Прочая неизвестная ошибка
            modelAndView.setViewName("error");
            modelAndView.addObject("errorCode", "Error :-(");
            modelAndView.addObject("errorMessage", "Unknown issue caused");
            modelAndView.addObject("solution", "Restart your program...may be. I don't know :/");
            modelAndView.addObject("time", date);
        }

        return modelAndView;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
