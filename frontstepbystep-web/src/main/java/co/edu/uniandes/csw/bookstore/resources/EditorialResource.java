package co.edu.uniandes.csw.bookstore.resources;

import co.edu.uniandes.csw.bookstore.ejb.EditorialLogic;
import co.edu.uniandes.csw.bookstore.dtos.EditorialDetailDTO;
import co.edu.uniandes.csw.bookstore.entities.EditorialEntity;
import co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException;
import java.util.ArrayList;

import java.util.List;
import javax.inject.Inject;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso REST correspondiente a "editorials".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "editorials". Al ejecutar la aplicación, el
 * recurse será accesibe a través de la ruta "/api/editorials"
 *
 *
 */
@Path("editorials")
@Produces("application/json")
public class EditorialResource {

    @Inject
    EditorialLogic editorialLogic;

    @GET
    public List<EditorialDetailDTO> getEditorials() throws BusinessLogicException {
        return listEntity2DetailDTO(editorialLogic.getEditorials());
    }

    @GET
    @Path("{id: \\d+}")
    public EditorialDetailDTO getEditorial(@PathParam("id") Long id) throws BusinessLogicException {
        EditorialEntity entity = editorialLogic.getEditorial(id);
        if (entity == null) {
            throw new WebApplicationException("El libro no existe", 404);
        }
        return new EditorialDetailDTO(editorialLogic.getEditorial(id));
    }

    @POST
    public EditorialDetailDTO createEditorial(EditorialDetailDTO editorial) throws BusinessLogicException {
        return new EditorialDetailDTO(editorialLogic.createEditorial(editorial.toEntity()));
    }

    @PUT
    @Path("{id: \\d+}")
    public EditorialDetailDTO updateEditorial(@PathParam("id") Long id, EditorialDetailDTO editorial) throws BusinessLogicException {
        EditorialEntity entity = editorialLogic.getEditorial(id);
        if (entity == null) {
            throw new WebApplicationException("El libro no existe", 404);
        }
        return new EditorialDetailDTO(editorialLogic.updateEditorial(id, editorial.toEntity()));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteEditorial(@PathParam("id") Long id) throws BusinessLogicException {
        EditorialEntity entity = editorialLogic.getEditorial(id);
        if (entity == null) {
            throw new WebApplicationException("El libro no existe", 404);
        }
        editorialLogic.deleteEditorial(id);
    }

    private List<EditorialDetailDTO> listEntity2DetailDTO(List<EditorialEntity> entityList) {
        List<EditorialDetailDTO> list = new ArrayList<>();
        for (EditorialEntity entity : entityList) {
            list.add(new EditorialDetailDTO(entity));
        }
        return list;
    }

}
