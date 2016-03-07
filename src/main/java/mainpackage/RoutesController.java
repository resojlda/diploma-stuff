/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpackage;
import com.googlecode.vkapi.Main;
import com.googlecode.vkapi.exceptions.VkException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class RoutesController {

	@RequestMapping(value = "/")
	public String index() {
            return "WEB-INF/views/index.jsp";
	}
        @RequestMapping(value = "/home")
	public String home() throws InterruptedException, VkException {
//            Main.execute();
            return "WEB-INF/views/home.jsp";
	}
        @RequestMapping(value = "/*")
        public String one(Model model) {
            model.addAttribute("ErrorCode", "404");
            return "WEB-INF/views/404.jsp";
        }
}