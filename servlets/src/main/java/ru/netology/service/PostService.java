package ru.netology.service;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.repository.PostRepository;

import java.util.List;

public class PostService {
  private final PostRepository repository;

  public PostService(PostRepository repository) {
    this.repository = repository;
  }

  public List<Post> all() {
    return repository.all();
  }

  public Post getById(long id) {

    return repository.getById(id).orElseThrow(() -> new NotFoundException("Post with ID " + id + " not found"));
  }

  public Post save(Post post) {

    return repository.save(post);
  }

  public void removeById(long id) {

    if (repository.getById(id).isEmpty()) {
      throw new NotFoundException("Post with ID " + id + " not found");
    }
    repository.removeById(id);
  }
}
