package co.edu.uniandes.csw.bookstore.dtos;

import co.edu.uniandes.csw.bookstore.entities.EditorialEntity;
import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import java.util.ArrayList;
import java.util.List;

public class EditorialDetailDTO extends EditorialDTO {

    private List<BookDTO> books;

    /**
     * Constructor por defecto
     */
    public EditorialDetailDTO() {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public EditorialDetailDTO(EditorialEntity entity) {
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
    public EditorialEntity toEntity() {
        EditorialEntity editorialE = super.toEntity();
        if (books != null) {
            List<BookEntity> booksEntity = new ArrayList<>();
            for (BookDTO dtoBook : books) {
                booksEntity.add(dtoBook.toEntity());
            }
            editorialE.setBooks(booksEntity);
        }
        return editorialE;
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
