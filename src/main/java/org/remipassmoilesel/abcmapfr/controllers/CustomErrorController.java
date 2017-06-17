package org.remipassmoilesel.abcmapfr.controllers;

import org.remipassmoilesel.abcmapfr.Mappings;
import org.remipassmoilesel.abcmapfr.Templates;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping(value = Mappings.ERROR_PATH, method = RequestMethod.GET)
    public String renderErrorPage(Model model, HttpServletRequest httpRequest) {

        String errorMsg = "";
        int httpErrorCode = getErrorCode(httpRequest);

        switch (httpErrorCode) {
            case 400: {
                errorMsg = "Http Error Code: 400. Bad Request";
                break;
            }
            case 401: {
                errorMsg = "Http Error Code: 401. Unauthorized";
                break;
            }
            case 404: {
                errorMsg = "Http Error Code: 404. Resource not found";
                break;
            }
            case 500: {
                errorMsg = "Http Error Code: 500. Internal Server Error";
                break;
            }
            default: {
                errorMsg = "Error code: " + httpErrorCode;
                break;
            }
        }

        model.addAttribute("date", new Date());
        model.addAttribute("errorMsg", errorMsg);
        return Templates.ERROR;
    }

    private int getErrorCode(HttpServletRequest httpRequest) {

        if (httpRequest == null) {
            return -1;
        }

        Object code = httpRequest.getAttribute("javax.servlet.error.status_code");
        if (code == null) {
            return -1;
        }

        return (Integer) code;
    }

    @Override
    public String getErrorPath() {
        return Mappings.ERROR_PATH;
    }
}