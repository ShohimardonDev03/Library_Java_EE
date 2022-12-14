package uz.jl.library.servlet;


import uz.jl.library.doa.BookDao;
import uz.jl.library.domains.Book;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String spageid = request.getParameter("page");
        int pageid = Integer.parseInt(spageid);
        int total = 5;
        if (pageid == 1) {
        } else {
            pageid = pageid - 1;
            total = pageid * total + 1;
        }
        BookDao bookdao = BookDao.getInstance();
        List<Book> list = bookdao.getRecords(pageid, total);

        out.print("<h1>Page No: " + list.size() + "</h1>");
        out.print("<table border='1' cellpadding='4' width='60%'>");
        out.print("<tr><th>Id</th><th>Name</th><th>Salary</th>");
        for (Book e : list) {
            out.print("<tr><td>" + e.getId() + "</td><td>" + e.getName() + "</td><td>" + e.getLanguage() + "</td></tr>");
        }
        out.print("</table>");

        out.print("<a href='ViewServlet?page=1'>1</a> ");
        out.print("<a href='ViewServlet?page=2'>2</a> ");
        out.print("<a href='ViewServlet?page=3'>3</a> ");

        out.close();
    }


}
