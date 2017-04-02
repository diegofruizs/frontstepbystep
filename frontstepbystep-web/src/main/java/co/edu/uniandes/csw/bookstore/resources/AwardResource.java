package co.edu.uniandes.csw.bookstore.resources;

import co.edu.uniandes.csw.bookstore.ejb.AwardLogic;
import co.edu.uniandes.csw.bookstore.dtos.AwardDTO;
import co.edu.uniandes.csw.bookstore.ejb.AuthorLogic;
import co.edu.uniandes.csw.bookstore.entities.AuthorEntity;
import co.edu.uniandes.csw.bookstore.entities.AwardEntity;
import co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso REST correspondiente a "awards".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "awards". Al ejecutar la aplicación, el recurse
 * será accesible a través de la ruta "/api/authors/idAuthor/awards"
 *
 */
@Path("authors/{authorId: \\d+}/awards")
@Produces("application/json")
@Consumes("application/json")
public class AwardResource {

    private static final Logger logger = Logger.getLogger(AwardLogic.class.getName());
    @Inject
    AwardLogic awardLogic;
    @Inject
    AuthorLogic authorLogic;

    @GET
    public List<AwardDTO> getAwards(@PathParam("authorId") Long authorId) throws BusinessLogicException {

        AuthorEntity entity = authorLogic.getAuthor(authorId);
        if (entity == null) {
            throw new WebApplicationException("El autor no existe", 404);
        }
        List<AwardEntity> list = awardLogic.getAwards(authorId);
        return listEntity2DTO(list);
    }

    @GET
    @Path("{id: \\d+}")
    public AwardDTO getAward(@PathParam("authorId") Long authorId, @PathParam("id") Long id) throws BusinessLogicException {
        AuthorEntity entity = authorLogic.getAuthor(authorId);
        if (entity == null) {
            throw new WebApplicationException("El autor no existe", 404);
        }
        return new AwardDTO(awardLogic.getAward(authorId, id));
    }

    @POST
    public AwardDTO createAward(@PathParam("authorId") Long authorId, AwardDTO award) throws BusinessLogicException {
        return new AwardDTO(awardLogic.createAward(authorId, award.toEntity()));
    }

 
    @DELETE
    @Path("{id: \\d+}")
    public void deleteAward(@PathParam("authorId") Long authorId,
            @PathParam("id") Long id) throws BusinessLogicException {
        awardLogic.deleteAward(authorId, id);
    }

    private List<AwardDTO> listEntity2DTO(List<AwardEntity> entityList) {
        List<AwardDTO> list = new ArrayList<>();
        for (AwardEntity entity : entityList) {
            list.add(new AwardDTO(entity));
        }
        return list;
    }

}
