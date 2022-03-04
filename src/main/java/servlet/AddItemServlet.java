package servlet;

import manager.CategoryManager;
import manager.ItemManager;
import manager.PictureManager;
import model.Category;
import model.Item;
import model.Picture;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/addItem")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100)
public class AddItemServlet extends HttpServlet {
    private final String UPLOAD_DIR = "C:\\Users\\User\\IdeaProjects\\myItems.am\\image\\";
    private ItemManager itemManager = new ItemManager();
    private CategoryManager categoryManager = new CategoryManager();
    private PictureManager pictureManager=new PictureManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", categoryManager.getAllICategories());
        req.getRequestDispatcher("/WEB-INF/addItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        String title = req.getParameter("title");
        String description=req.getParameter("description");
        double price = Double.parseDouble(req.getParameter("price"));
        int categoryId = Integer.parseInt(req.getParameter("category_id"));
        Category category=categoryManager.getCategoryById(categoryId);
        Item item = Item.builder()
                .title(title)
                .price(price)
                .description(description)
                .category(category)
                .user(user)
                .build();
        itemManager.add(item);

        Part filterPart = req.getPart("picture");
        String fileName = filterPart.getSubmittedFileName();
        String picUrl = System.nanoTime() + "_" + fileName;
        filterPart.write(UPLOAD_DIR + picUrl);

        Picture picture = Picture.builder()
                .pictureUrl(picUrl)
                .build();
        pictureManager.add(picture,item.getId());
        resp.sendRedirect("/home");
    }
}
