package ru.netology.model;

import java.util.Objects;

public class Post {
  private long id;
  private String title;
  private String content;


  public Post() {}

  public Post(long id, String title, String content) {
    this.id = id;
    this.title = title;
    this.content = content;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Post)) return false;
    Post post = (Post) o;
    return id == post.id &&
            Objects.equals(title, post.title) &&
            Objects.equals(content, post.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, content);
  }

  @Override
  public String toString() {
    return "Post{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            '}';
  }
}
