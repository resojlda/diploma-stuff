/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpackage;

import com.googlecode.vkapi.exceptions.VkException;
import dbpackage.Company;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.CityService;
import service.CompanyService;
import service.StudentService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.web.bind.annotation.ModelAttribute;
import service.UniversityService;
import university.CityAndUniversities;

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
    public String univ(Model model) throws IOException{
        //осторожно, говнокод!
        String json = "{\"response\":" + CityAndUniversities.getCityJson().toString() + "}";
        model.addAttribute("json", json);
        return "univ";
    }
    
    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public String result(@ModelAttribute("id")Integer id, Model model){
        model.addAttribute("id", id);
        return "result";
    }
    
    @RequestMapping(value = "/charts")
    public String charts(Model model) {
        return "charts";
    }

    @RequestMapping(value = "/chart")
    public String chart(Model model) {
        return "chart";
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
    public String insert() {
//        cityService.insert(new City("Rostov", 456));
//        cityService.insert(new City("Odessa", 74));
//        cityService.insert(new City("Rostov", 6));
//        cityService.insert(new City("Piter", 12));
//        cityService.insert(new City("Mosk", 479));
//        cityService.insert(new City("Rovno", 58679));
//
//
//        City ne = cityService.find(6);
//
//        studentService.insert(new Student(1, 132, "Alexandra", "Marysheva", 46, "https://pp.vk.me/c628825/v628825035/17e2e/qy05rKxrO8E.jpg", "somejson", "some other json"));
//        studentService.insert(new Student(1, 45621, "Kate", "Prihodko", 78, "https://pp.vk.me/c628825/v628825035/17e2e/qy05rKxrO8E.jpg", "somejson", "some other json"));
//
//        List results=new ArrayList<Student>();
//        results = studentService.findUniversityResults(1);
//
//        universityService.insert(new University(54645, "ONPU"));
//        University un = universityService.getName((long)54645);
//
//        companyService.insert(new Company(489654, "Hys"));
//        boolean please=companyService.find("Hys");
//

        //@todo teach regex clever looking for substrings (строители!=ит)
        int group_id=1234211345;

        //we get groupId of person's company (if it set by group_id) or just name
        String cName = "Looksery";
        if (companyService.find(cName)) {
            //welldone, return true
            int i = 0;
        }

        //here should be query to get info about group_id, that person has (also if it set by group_id)
        String cDescr = "Text with it or wothoutit so";
        if (!cDescr.isEmpty()) {
            boolean f = false;

            List<String> tokens = new ArrayList<String>();
            tokens.add("IT");
            tokens.add("ИТ");

            String patternString = "\\b(" + StringUtils.join(tokens, "|") + ")\\b";
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(cDescr.toUpperCase());

            while (matcher.find()) {
                companyService.insert(new Company(group_id, cName));
                //stoul do return true
            }

        //return false
        }

        return "welldone";
    }
}