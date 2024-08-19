package com.example.Library;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@RestController
@RequestMapping("/books")
public class BooksController {
    private List<Books> books = new ArrayList<>();


    @PostMapping
    public ResponseEntity<String> addBooks(@RequestBody List<Books> books) {
        if (books == null || books.isEmpty()) {
            return new ResponseEntity<>("No books provided", HttpStatus.BAD_REQUEST);
        }
        this.books.addAll(books);
        return new ResponseEntity<>("Books added successfully", HttpStatus.CREATED );
    }

    @GetMapping
    public ResponseEntity<String> getAllBooks() {
        StringBuilder response = new StringBuilder("<html><body>");

        for (Books book : books) {
            response.append("<p>Name: ")
                    .append(book.getName())
                    .append("<p>Author: ")
                    .append(book.getAuthor())
                    .append("<p>Categories: ")
                    .append(book.getCategories())
                    .append("</p>");
        }

        response.append("</body></html>");
        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }

    @PutMapping

    public ResponseEntity<String> updateBook(@RequestBody Books updatedBook) {
        for (Books book : books) {
            if(book.getName().equals(updatedBook.getName())) {
                book.setAuthor((updatedBook.getAuthor()));
                book.setCategories((updatedBook.getCategories()));
                return new ResponseEntity<>("Book updated successfully", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteBook(@PathVariable String name) {
        Iterator<Books> iterator = books.iterator();
        
        while(iterator.hasNext()) {
            Books book = iterator.next();
            if (book.getName().equals(name)) {
                iterator.remove();
                return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
    }
}
