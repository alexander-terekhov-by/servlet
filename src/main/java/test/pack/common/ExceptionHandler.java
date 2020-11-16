package test.pack.common;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Alexander Terekhov
 */
public class ExceptionHandler {
    public void writeError(Exception e, HttpServletResponse response) {
        try {
            System.out.println(e); // pretending logging
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Pretending to be an error page");
        } catch (IOException ioException) {
            throw new RuntimeException("Everything is broken", ioException);
        }
    }
}
