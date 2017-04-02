package co.edu.uniandes.csw.bookstore.dtos;

import co.edu.uniandes.csw.bookstore.entities.AwardEntity;
import co.edu.uniandes.csw.bookstore.entities.AwardEntity;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AwardDTO {

    private Long id;

    private String name;

   private Date awardyear;
    private String description;

    private AuthorDTO author;

    /**
     * Constructor por defecto
     */
    public AwardDTO() {
    }

    public AwardDTO(AwardEntity entity) {

        this.id = entity.getId();
        this.name = entity.getName();
        this.awardyear = entity.getAwardyear();
        this.description = entity.getDescription();
             this.author= new AuthorDTO(entity.getAuthor());
    }

    public AwardEntity toEntity() {
        AwardEntity entity = new AwardEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setAwardyear(this.awardyear);
        entity.setDescription(this.getDescription());
          if (this.author != null) {
            entity.setAuthor(this.author.toEntity());               
        } else {
            entity.setAuthor(null);
        }
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
     * @return the awardyear
     */
    public Date getAwardyear() {
        return awardyear;
    }

    /**
     * @param awardyear the awardyear to set
     */
    public void setAwardyear(Date awardyear) {
        this.awardyear = awardyear;
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
     * @return the author
     */
    public AuthorDTO getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

}
