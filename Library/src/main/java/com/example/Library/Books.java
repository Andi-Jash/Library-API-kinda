package com.example.Library;

public class Books {
    private String name;
    private String author;
    private String categories;

    public Books() {}


        public Books(String name, String author, String categories){
            this.name = name;
            this.author = author;
            this.categories = categories;
        }

        public String getName() {
            return name;
        }

        public String getAuthor() {
            return author;
        }

        public String getCategories() {
            return categories;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setCategories(String categories) {
            this.categories = categories;
        }
}
