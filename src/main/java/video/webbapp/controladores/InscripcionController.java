package video.webbapp.controladores;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.List;
import video.webapp.entidades.Alumno;
import video.webapp.entidades.Inscripciones;
import video.webapp.entidades.Materia;
import video.webapp.negocio.DataService;

@Named
@RequestScoped
public class InscripcionController {

    private List<Inscripciones> inscripcionesList = new ArrayList<>();
    private List<Alumno> alumnosList = new ArrayList<>();
    private List<Materia> materiasList = new ArrayList<>();
    private Inscripciones inscripcion = new Inscripciones();

    @EJB
    DataService servicio;

    @PostConstruct
    public void cargarDatos() {
        inscripcionesList = servicio.getInscripciones();
        alumnosList = servicio.getAlumnos();
        materiasList = servicio.getMaterias();
        // Inicializa la inscripción con objetos internos
        inscripcion = new Inscripciones();
        inscripcion.setAlumno(new Alumno()); // Asigna un objeto Alumno nuevo
        inscripcion.setMateria(new Materia()); // Asigna un objeto Materia nuevo
    }

    public void guardarInscripcion() {
        servicio.saveInscripcion(inscripcion);
        // Resetear inscripción después de guardar
        inscripcion = new Inscripciones();
        inscripcion.setAlumno(new Alumno()); // Resetear Alumno
        inscripcion.setMateria(new Materia()); // Resetear Materia
        cargarDatos(); // Recargar datos desde el servicio
    }

    // Getters y Setters
    public List<Inscripciones> getInscripcionesList() {
        return inscripcionesList;
    }

    public void setInscripcionesList(List<Inscripciones> inscripcionesList) {
        this.inscripcionesList = inscripcionesList;
    }

    public List<Alumno> getAlumnosList() {
        return alumnosList;
    }

    public void setAlumnosList(List<Alumno> alumnosList) {
        this.alumnosList = alumnosList;
    }

    public List<Materia> getMateriasList() {
        return materiasList;
    }

    public void setMateriasList(List<Materia> materiasList) {
        this.materiasList = materiasList;
    }

    public Inscripciones getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(Inscripciones inscripcion) {
        this.inscripcion = inscripcion;
    }
}
