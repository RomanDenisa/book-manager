package domain;

/**
 * Book entity - describes a book
 */
public class Book extends Entity<Integer> {
    /**
     * the name of the book
     */
    private String title;
    /**
     * the author of the book
     */
    private String author;
    /**
     * the date at which the reading was completed
     */
    private String finishDate;
    /**
     * the rating of the book - shows how enjoyable the book is
     */
    private int rating;

    public Book(String title, String author, String finishDate, int rating) {
        this.title = title;
        this.author = author;
        this.finishDate = finishDate;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Title='" + title + ' ' +
                ", Author='" + author + ' ' +
                ", FinishDate='" + finishDate + ' ' +
                ", Rating=" + rating;
    }
}
