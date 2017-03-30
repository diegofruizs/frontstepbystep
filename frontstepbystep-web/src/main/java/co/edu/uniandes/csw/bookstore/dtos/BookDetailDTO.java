
package co.edu.uniandes.csw.bookstore.dtos;

import co.edu.uniandes.csw.bookstore.entities.AuthorEntity;
import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import co.edu.uniandes.csw.bookstore.entities.ReviewEntity;
import java.util.ArrayList;
import java.util.List;


public class BookDetailDTO extends BookDTO {

    // relación  cero o muchos reviews 
    private List<ReviewDTO> reviews = new ArrayList<>();

    // relación  cero o muchos author
    private List<AuthorDTO> authors = new ArrayList<>();

    public BookDetailDTO() {
        super();
    }

    /** 
     * Constructor para transformar un Entity a un DTO
     * @param entity 
     */
    public BookDetailDTO(BookEntity entity) {
        super(entity);
        if (entity != null) {
            authors = new ArrayList<>();
            for (AuthorEntity entityAuthor : entity.getAuthors()) {
                authors.add(new AuthorDTO(entityAuthor));
            }
            reviews = new ArrayList<>();
            for (ReviewEntity entityReview : entity.getReviews()) {
                reviews.add(new ReviewDTO(entityReview));
            }

        }
    }

    @Override
    public BookEntity toEntity() {
        BookEntity bookE = super.toEntity();
        if (reviews != null) {
            List<ReviewEntity> reviewsEntity = new ArrayList<>();
            for(ReviewDTO dtoReview : reviews)
            {
                reviewsEntity.add(dtoReview.toEntity());
            }
            bookE.setReviews(reviewsEntity);
        }
        if (authors != null) {
            List<AuthorEntity> authorsEntity = new ArrayList<>();
            for(AuthorDTO dtoAuthor : authors)
            {
                authorsEntity.add(dtoAuthor.toEntity());
            }
            bookE.setAuthors(authorsEntity);
        }
        return bookE;
    }

    /**
     * @return the reviews
     */
    public List<ReviewDTO> getReviews() {
        return reviews;
    }

    /**
     * @param reviews the reviews to set
     */
    public void setReviews(List<ReviewDTO> reviews) {
        this.reviews = reviews;
    }

    /**
     * @return the authors
     */
    public List<AuthorDTO> getAuthors() {
        return authors;
    }

    /**
     * @param authors the authors to set
     */
    public void setAuthors(List<AuthorDTO> authors) {
        this.authors = authors;
    }
}
