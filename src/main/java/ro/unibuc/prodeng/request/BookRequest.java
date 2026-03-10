package ro.unibuc.prodeng.request;

public class BookRequest {
    private String title;
    private String author;
    private String isbn;
    private int totalCopies;
    
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public int getTotalCopies() { return totalCopies; }
}