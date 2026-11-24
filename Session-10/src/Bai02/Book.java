package Bai02;
public class Book {
  private String title;
  private String author;
  private int publishYear;

  public Book() {
  }

  public Book(String title, String author, int publishYear) {
    this.title = title;
    this.author = author;
    this.publishYear = publishYear;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public int getPublishYear() {
    return publishYear;
  }

  public void setPublishYear(int publishYear) {
    this.publishYear = publishYear;
  }

  @Override
  public String toString() {
    return "Tên sách: " + title + " | Tác giả: " + author + " | Năm xuất bản: " + publishYear;
  }
}
