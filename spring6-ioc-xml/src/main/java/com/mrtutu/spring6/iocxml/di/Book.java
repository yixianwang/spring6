package com.mrtutu.spring6.iocxml.di;

public class Book {
    private String bname;
    private String author;

    private String others;

    public Book() {
        System.out.println("constructor without params executed");
    }

    public Book(String bname, String author) {
        System.out.println("constructor with params executed");
        this.bname = bname;
        this.author = author;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bname='" + bname + '\'' +
                ", author='" + author + '\'' +
                ", others='" + others + '\'' +
                '}';
    }

    public static void main(String[] args) {
        // setter injection
        Book book = new Book();
        book.setBname("Talent");
        book.setAuthor("Yixian");

        // constructor injection
        Book book1 = new Book("Awesome", "Yixian");

    }
}
