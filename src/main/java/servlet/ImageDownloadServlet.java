package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(urlPatterns = "/getImage")
public class ImageDownloadServlet extends HttpServlet {

    private final String UPLOAD_DIR = "C:\\Users\\User\\IdeaProjects\\myItems.am\\image\\";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String picUrl = req.getParameter("picture_url");
        String filePath = UPLOAD_DIR + picUrl;
        File file = new File(filePath);
        if (file.exists()) {
            try (InputStream in = new FileInputStream(file)) {
                resp.setContentType("image/jpeg");
                resp.setContentLength((int) file.length());
                OutputStream out = resp.getOutputStream();
                byte[] buffer = new byte[4096];
                int numBytesRead = -1;

                while ((numBytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, numBytesRead);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
