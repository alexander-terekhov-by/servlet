package test.pack.common;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Alexander Terekhov
 */
public class ExceptionHandler {
    public void writeError(Exception e, HttpServletResponse response) {
        try {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
        } catch (IOException ioException) {
            throw new RuntimeException("Everything is broken", ioException);
        }
    }
}
