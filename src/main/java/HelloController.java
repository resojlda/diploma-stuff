//import dbpackage.City;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import service.CityService;
//
///**
// * Created by Aleksandra Marysheva on 15.03.2016.
// */
//
//
//
//@Controller
//@RequestMapping("/")
//public class HelloController {
//
//
//
//    @Autowired
//    private CityService cityService;
//
//
//    @RequestMapping(method = RequestMethod.GET)
//    public String printWelcome(ModelMap model){
//        model.addAttribute("message", "HelloWorls");
//        return "hello";
//    }
//
//    @RequestMapping(value = "/insert", method = RequestMethod.GET)
//    public String insert(){
//        cityService.insert(new City("Odessa", 213));
//        return "hello";
//    }
//}
