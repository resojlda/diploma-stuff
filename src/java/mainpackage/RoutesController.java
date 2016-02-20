/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpackage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RoutesController {

	@RequestMapping(value = "/")
	public String index() {
		return "WEB-INF/views/index.jsp";
	}
        @RequestMapping(value = "/home")
	public String home() {
		return "WEB-INF/views/home.jsp";
	}
                @RequestMapping(value = "/*")
	public String NotFound() {
		return "WEB-INF/views/404.jsp";
	}
}