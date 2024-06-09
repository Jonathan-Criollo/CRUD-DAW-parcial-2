package video.webbapp.controladores;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import java.util.List;
import video.webapp.entidades.Materia;
import video.webapp.negocio.DataService;

@Named
@RequestScoped
public class MateriaController {

    private List<Materia> materiasList;
    private Materia materia;

    @EJB
    DataService servicio;

    @PostConstruct
    public void init() {
        materiasList = servicio.getMaterias();
        materia = new Materia();  // Inicializa una nueva materia para el formulario
    }

    public void guardarMateria() {
        try {
            if (materia.getId() != null && materia.getId() > 0) {
                servicio.editMateria(materia);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Materia actualizada correctamente."));
            } else {
                servicio.saveMateria(materia);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Materia creada correctamente."));
            }
            materia = new Materia();  // Reinicia la entidad para evitar reuso del objeto
            materiasList = servicio.getMaterias();  // Recargar la lista para reflejar los cambios
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al guardar la materia: " + e.getMessage()));
        }
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("materiaForm:materiaMessages");
    }

    public void llenarFormEditar(Materia materia) {
        this.materia = materia; // Directamente asignar la materia seleccionada al formulario
    }

    public void eliminarMateria(Materia materia) {
        servicio.deleteMateria(materia);
        materiasList.remove(materia); // Optimiza la actualización de la lista sin necesidad de recargar desde el servicio
        PrimeFaces.current().ajax().update("materiaTable"); // Asegúrate de que el ID de la tabla en la vista sea 'materiaTable'
    }

    // Getters y Setters
    public List<Materia> getMateriasList() {
        return materiasList;
    }

    public void setMateriasList(List<Materia> materiasList) {
        this.materiasList = materiasList;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
}
