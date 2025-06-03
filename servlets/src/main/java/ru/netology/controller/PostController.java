package ru.netology.controller;

import ru.netology.model.Post;
import ru.netology.service.PostService;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class PostController {
  private final PostService service;

  public PostController(PostService service) {
    this.service = service;
  }

  public void all(HttpServletResponse resp) throws IOException {
    List<Post> posts = service.all();
    resp.setContentType("application/json");
    resp.getWriter().write(posts.toString());
  }

  public void getById(long id, HttpServletResponse resp) throws IOException {
    Post post = service.getById(id);
    resp.setContentType("application/json");
    resp.getWriter().write(post.toString());
  }

  public void save(BufferedReader reader, HttpServletResponse resp) throws IOException {
    StringBuilder body = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      body.append(line);
    }

    Post post = parsePostFromJson(body.toString());
    service.save(post);

    resp.setStatus(HttpServletResponse.SC_CREATED);
    resp.getWriter().write("Post created or updated successfully");
  }

  public void removeById(long id, HttpServletResponse resp) throws IOException {
    service.removeById(id);
    resp.setStatus(HttpServletResponse.SC_NO_CONTENT); // Успешное удаление
  }

  private Post parsePostFromJson(String json) {
    return null;
  }
}
