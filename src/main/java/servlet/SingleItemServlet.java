package servlet;

import manager.CategoryManager;
import manager.ItemManager;
import manager.PictureManager;
import model.Item;
import model.Picture;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/singleItem")
public class SingleItemServlet extends HttpServlet {
    private ItemManager itemManager = new ItemManager();
    private CategoryManager categoryManager = new CategoryManager();
    private PictureManager pictureManager = new PictureManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Item itemById = itemManager.getItemById(id);
        List<Picture> pictures = pictureManager.getAllPicturesByItemId(id);
        if (itemById == null) {
            resp.sendRedirect("/home");
        } else {
            req.setAttribute("item", itemById);
            req.setAttribute("categories", categoryManager.getAllICategories());
            req.setAttribute("pictures",pictures);
            req.getRequestDispatcher("/WEB-INF/singleItem.jsp").forward(req, resp);
        }
    }
}
