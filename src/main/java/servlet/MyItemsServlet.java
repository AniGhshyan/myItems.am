package servlet;

import manager.CategoryManager;
import manager.ItemManager;
import manager.PictureManager;
import model.Item;
import model.Picture;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/myItems")
public class MyItemsServlet extends HttpServlet {
    private ItemManager itemManager = new ItemManager();
    private CategoryManager categoryManager = new CategoryManager();
    private PictureManager pictureManager = new PictureManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Item> items = itemManager.getAllUserItems(user.getId());

        req.setAttribute("items", items);
        for (Item item : items) {
            List<Picture> pictures = pictureManager.getAllPicturesByItemId(item.getId());
            req.setAttribute("pictures", pictures);
        }
        req.setAttribute("categories", categoryManager.getAllICategories());
        req.getRequestDispatcher("WEB-INF/main.jsp").forward(req, resp);
    }
}
