package co.edu.uniandes.csw.bookstore.dtos;

import co.edu.uniandes.csw.bookstore.entities.AuthorEntity;
import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuthorDetailDTO extends AuthorDTO {

    private List<BookDTO> books;

    /**
     * Constructor por defecto
     */
    public AuthorDetailDTO() {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public AuthorDetailDTO(AuthorEntity entity) {
        super(entity);
        if (entity != null) {
            books = new ArrayList<>();
            for (BookEntity entityBooks : entity.getBooks()) {
                books.add(new BookDTO(entityBooks));
            }

        }
    }
    /**
     * Transformar un DTO a un Entity
     *
     */
    public AuthorEntity toEntity() {
        AuthorEntity authorE = super.toEntity();
        if (books != null) {
            List<BookEntity> booksEntity = new ArrayList<>();
            for (BookDTO dtoBook : books) {
                booksEntity.add(dtoBook.toEntity());
            }
            authorE.setBooks(booksEntity);
        }
        return authorE;
    }

    /**
     * @return the books
     */
    public List<BookDTO> getBooks() {
        return books;
    }

    /**
     * @param books the books to set
     */
    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }

}
