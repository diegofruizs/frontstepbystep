package co.edu.uniandes.csw.bookstore.dtos;

import co.edu.uniandes.csw.bookstore.entities.AuthorEntity;
import co.edu.uniandes.csw.bookstore.entities.AwardEntity;
import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuthorDetailDTO extends AuthorDTO {

    private List<BookDTO> books;
    private List<AwardDTO> awards;

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
            awards = new ArrayList<>();
            for (AwardEntity entityAwards : entity.getAwards()) {
                awards.add(new AwardDTO(entityAwards));
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

        if (awards != null) {
            List<AwardEntity> awardsEntity = new ArrayList<>();
            for (AwardDTO dtoAward : awards) {
                awardsEntity.add(dtoAward.toEntity());
            }
            authorE.setAwards(awardsEntity);
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

    /**
     * @return the awards
     */
    public List<AwardDTO> getAwards() {
        return awards;
    }

    /**
     * @param awards the awards to set
     */
    public void setAwards(List<AwardDTO> awards) {
        this.awards = awards;
    }

}
