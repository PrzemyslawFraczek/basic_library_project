package pl.sda.model;

public enum BooksType {
    SCIENCE_FICTION("Science Fiction"),
    HORROR("Horror"),
    ROMANCE("Romance"),
    TRAVEL("Travel"),
    SCIENCE("Science"),
    HISTORY("History"),
    FANTASY("Fantasy");

    private String text;

    BooksType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
