package test.pack.timezone.servlet;

import test.pack.timezone.entity.ZipCode;
import test.pack.timezone.service.TimezoneService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TimezoneServlet extends HttpServlet {


    private static TimezoneService timezoneService;


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String zip = new BufferedReader(
                new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        if (zip.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "No zip was specified");
        }
        final ZipCode zipCode = new ZipCode(); // from request
        final TimeZone timeZone = timezoneService.getTimezone(zipCode);
        if (timeZone == null) {
            resp.getWriter().print("There is no timezone for " + zip);
        } else {
            resp.getWriter().print("Timezone is " + timeZone);
        }
    }
}
