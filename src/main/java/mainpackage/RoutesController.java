/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpackage;

import com.googlecode.vkapi.exceptions.VkException;
import dbpackage.City;
import dbpackage.Company;
import dbpackage.Student;
import dbpackage.University;
import org.apache.http.ParseException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.CityService;
import service.CompanyService;
import service.StudentService;
import service.UniversityService;
import university.Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class RoutesController {



	@RequestMapping(value = "/")
	public String index() {
            return "index";
	}
        @RequestMapping(value = "/home")
	public String home() throws InterruptedException, VkException {
//            Main.execute();
            return "home";
	}
        @RequestMapping(value = "/*")
        public String one(Model model) {
            model.addAttribute("ErrorCode", "404");
            return "404";
        }

    @RequestMapping(value = "/univ")
    public String univ(Model model) throws IOException, org.json.simple.parser.ParseException {
        JSONObject json = new Servlet().getResult();
        model.addAttribute("json", json);
        return "univ";
    }

    @Autowired
    private CityService cityService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private UniversityService universityService;
    @Autowired
    private CompanyService companyService;




    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String insert(){
        cityService.insert(new City("Rostov", 456));
        cityService.insert(new City("Odessa", 74));
        cityService.insert(new City("Rostov", 6));
        cityService.insert(new City("Piter", 12));
        cityService.insert(new City("Mosk", 479));
        cityService.insert(new City("Rovno", 58679));


        City ne = cityService.find(6);

        studentService.insert(new Student(1, 132, "Alexandra", "Marysheva", 46, "https://pp.vk.me/c628825/v628825035/17e2e/qy05rKxrO8E.jpg", "somejson", "some other json"));
        studentService.insert(new Student(1, 45621, "Kate", "Prihodko", 78, "https://pp.vk.me/c628825/v628825035/17e2e/qy05rKxrO8E.jpg", "somejson", "some other json"));

        List results=new ArrayList<Student>();
        results = studentService.findUniversityResults(1);

        universityService.insert(new University(54645, "ONPU"));
        University un = universityService.getName((long)54645);

        companyService.insert(new Company(489654, "Hys"));
        boolean please=companyService.find("Hys");

        return "welldone";
    }
}