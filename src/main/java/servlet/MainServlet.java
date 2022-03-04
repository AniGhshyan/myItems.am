package servlet;

import manager.CategoryManager;
import manager.ItemManager;
import manager.PictureManager;
import model.Category;
import model.Item;
import model.Picture;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/home")
public class MainServlet extends HttpServlet {

    private CategoryManager categoryManager = new CategoryManager();
    private ItemManager itemManager = new ItemManager();
    private PictureManager pictureManager = new PictureManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String catIdStr = req.getParameter("category_id");
        List<Item> items;
        if (catIdStr == null || catIdStr.equals("")) {
            items = itemManager.getLast20Items();
        } else {
            int categoryId = Integer.parseInt(catIdStr);
            items = itemManager.getLast20ItemsByCategory(categoryId);
        }
        req.setAttribute("items", items);
        for (Item item : items) {
            List<Picture> pictures = pictureManager.getAllPicturesByItemId(item.getId());
            req.setAttribute("pictures", pictures);
        }

        req.setAttribute("categories", categoryManager.getAllICategories());
        req.getRequestDispatcher("/WEB-INF/main.jsp").forward(req, resp);
    }
}
