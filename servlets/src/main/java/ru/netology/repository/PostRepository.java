package ru.netology.repository;

import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class PostRepository {
  private final AtomicLong idGenerator = new AtomicLong(0);
  private final ConcurrentHashMap<Long, Post> posts = new ConcurrentHashMap<>();

  public List<Post> all() {
    return new ArrayList<>(posts.values());
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(posts.get(id));
  }

  public Post save(Post post) {
    long newId = idGenerator.incrementAndGet();
    post.setId(newId);
    posts.put(newId, post);
    return post;
  }

  public void removeById(long id) {
    posts.remove(id);
  }
}
