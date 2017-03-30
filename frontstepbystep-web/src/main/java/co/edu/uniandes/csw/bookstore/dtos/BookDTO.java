
package co.edu.uniandes.csw.bookstore.dtos;

import co.edu.uniandes.csw.bookstore.entities.BookEntity;

import java.util.Date;


public class BookDTO {

    private Long id;
    private String name;
    private String isbn;
    private String image;
    private String description;
    private Date publishingdate;

    // Relación a una editorial
    private EditorialDTO editorial;

    /**
     * Constructor por defecto
     */
    public BookDTO() {
    }

    public BookDTO(BookEntity bookE) {

        this.id = bookE.getId();
        this.name = bookE.getName();
        this.isbn = bookE.getIsbn();
        this.image = bookE.getImage();
        this.description = bookE.getDescription();
        this.publishingdate = bookE.getPublishDate();
        this.editorial = new EditorialDTO(bookE.getEditorial());
    }

    public BookEntity toEntity() {

        BookEntity bookE = new BookEntity();
        bookE.setId(this.id);
        bookE.setName(this.name);
        bookE.setIsbn(this.isbn);
        bookE.setImage(this.image);
        bookE.setDescription(this.description);
        bookE.setPublishDate(this.publishingdate);
        if (this.editorial != null) {
            bookE.setEditorial(this.editorial.toEntity());               
        } else {
            bookE.setEditorial(null);
        }
        return bookE;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the publishingdate
     */
    public Date getPublishingdate() {
        return publishingdate;
    }

    /**
     * @param publishingdate the publishingdate to set
     */
    public void setPublishingdate(Date publishingdate) {
        this.publishingdate = publishingdate;
    }

    /**
     * @return the editorial
     */
    public EditorialDTO getEditorial() {
        return editorial;
    }

    /**
     * @param editorial the editorial to set
     */
    public void setEditorial(EditorialDTO editorial) {
        this.editorial = editorial;
    }

}
