package ru.netology.servlet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.netology.controller.PostController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MainServlet extends HttpServlet {
  private PostController controller;
  private static final String SERVLET_PATH = "/";
  private static final String POST_API_POST  = "/api/posts/";   // обычно используется для множества постов
  private static final String POST_API_POST_D = "/api/posts/\\d+";  //d+ В контексте API это обычно идентификатор (ID) поста

  @Override
  public void init() {
    ApplicationContext context = new AnnotationConfigApplicationContext("ru.netology");
    controller = context.getBean(PostController.class);
  }


  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) {
    try {
      final var path = req.getRequestURI();
      final var method = req.getMethod();

      switch (method) {
        case "GET":
          handleGetRequests(path, resp);
          break;
        case "POST":
          if (path.equals(POST_API_POST)) {
            controller.save(req.getReader(), resp);
          } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
          }
          break;
        case "DELETE":
          handleDeleteRequests(path, resp);
          break;
        default:
          resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
          break;
      }
    } catch (Exception e) {
      e.printStackTrace();
      resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

  private void handleGetRequests(String path, HttpServletResponse resp) throws Exception {
    if (path.equals(POST_API_POST)) {
      controller.all(resp);
    } else if (path.matches(POST_API_POST_D)) {
      final var id = Long.parseLong(path.substring(path.lastIndexOf(SERVLET_PATH) + 1));
      controller.getById(id, resp);
    } else {
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
  }

  private void handleDeleteRequests(String path, HttpServletResponse resp) throws Exception {
    if (path.matches(POST_API_POST_D)) {
      final var id = Long.parseLong(path.substring(path.lastIndexOf(SERVLET_PATH) + 1));
      controller.removeById(id, resp);
    } else {
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
  }
}


