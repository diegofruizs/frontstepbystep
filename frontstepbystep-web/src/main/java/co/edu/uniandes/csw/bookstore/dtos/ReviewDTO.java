package co.edu.uniandes.csw.bookstore.dtos;

import co.edu.uniandes.csw.bookstore.entities.ReviewEntity;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReviewDTO {

    private Long id;

    private String name;

    private String source;

    private String description;

    private BookDTO book;

    /**
     * Constructor por defecto
     */
    public ReviewDTO() {
    }

    public ReviewDTO(ReviewEntity entity) {

        this.id = entity.getId();
        this.name = entity.getName();
        this.source = entity.getSource();
        this.description = entity.getDescription();
    }

    public ReviewEntity toEntity() {
        ReviewEntity entity = new ReviewEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setSource(this.source);
        entity.setDescription(this.description);
        return entity;
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
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
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

}
