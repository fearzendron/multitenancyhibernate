package br.com.zendron.controller;

import br.com.zendron.ejb.PeopleRepository;
import br.com.zendron.model.People;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ejb.EJB;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;


@Controller
@RequestMapping("/")
public class HelloController {

    @EJB(mappedName = "java:app/multi-hibernate/PeopleEJB")
    private PeopleRepository peopleRepository;


    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model, HttpServletRequest request) {

        String tenant = "";

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("tenantID".equals(c.getName())) {
                    model.addAttribute("tenantID", c.getValue());
                    tenant = c.getValue();
                }
            }
        }

        Collection<People> listOfPeople = peopleRepository.getAll(tenant);

        model.addAttribute("list", listOfPeople);
        return "index";
    }

}