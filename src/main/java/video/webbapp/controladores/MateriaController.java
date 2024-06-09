package video.webbapp.controladores;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.List;
import video.webapp.entidades.Materia;
import video.webapp.negocio.DataService;

@Named
@RequestScoped
public class MateriaController {

    private List<Materia> materiasList = new ArrayList<>();
    private Materia materia = new Materia();

    @EJB
    DataService servicio;

    @PostConstruct
    public void cargarMaterias() {
        materiasList = servicio.getMaterias();
    }

    public void guardarMateria() {
        if (materia.getId() != null) {
            servicio.editMateria(materia);
        } else {
            servicio.saveMateria(materia);
        }
        materia = new Materia();
        cargarMaterias();
    }

    public void llenarFormEditar(Materia materia) {
        this.materia.setId(materia.getId());
        this.materia.setNombre(materia.getNombre());
        this.materia.setDescripcion(materia.getDescripcion());
        this.materia.setCodigoMateria(materia.getCodigoMateria());
    }

    public void eliminarMateria(Materia materia) {
        servicio.deleteMateria(materia);
        cargarMaterias();
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
