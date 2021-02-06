package ir.maktab.online_exam.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RedirectController {

    @GetMapping({"/", "login"})
    public String sendLoginPage(){
        return "login_page";
    }

    @GetMapping("/home-page")
    public String sendHomePage(){
        return "home_page";
    }

    @GetMapping("/sign-up")
    public String sendSignUpPage(){
        return "signup_page";
    }

    @GetMapping("/course-page/{courseId}")
    public String sendCoursePage(@PathVariable Long courseId, HttpServletResponse response){
        Cookie courseIdCookie = new Cookie("courseId", courseId.toString());
        response.addCookie(courseIdCookie);
        return "course_page";
    }

    @GetMapping("/exam-page/{examId}")
    public String sendExamPage(@PathVariable Long examId, HttpServletResponse response){
        Cookie courseIdCookie = new Cookie("examId", examId.toString());
        response.addCookie(courseIdCookie);
        return "exam_page";
    }

    @GetMapping("/question-page")
    public String sendQuestionPage(){
        return "question_page";
    }

}
