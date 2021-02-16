package com.LinkShortening.domain;

import javax.persistence.*;

@Entity
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String text;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private User author;

  public Message(String text, User user) {
    this.author = user;
    this.text = text;
  }

  public String getAuthorName() {
    return author != null ? author.getUsername() : "<none>";
  }

  public Message() {
  }

  public User getAuthor() {
    return author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
