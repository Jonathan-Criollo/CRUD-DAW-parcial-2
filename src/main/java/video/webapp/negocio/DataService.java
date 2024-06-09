package video.webapp.negocio;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.List;
import video.webapp.entidades.Alumno;
import video.webapp.entidades.Inscripciones;
import video.webapp.entidades.Materia;

@Stateless
public class DataService {
    
    @PersistenceContext(unitName = "pu")
    EntityManager entityManager;
    
    public List<Alumno> getAlumnos(){
        
        Query query = entityManager.createQuery("SELECT e FROM Alumno e ORDER BY e.id DESC");
        
        List<Alumno> alumnos = query.getResultList();
        
        return alumnos;
    }
    
    @Transactional
    public void saveAlumno(Alumno alumno){
        entityManager.persist(alumno);
    }
    
    @Transactional
    public void editAlumno(Alumno alumno){
        entityManager.merge(alumno);
    }
    
    @Transactional
    public void deleteAlumno(Alumno alumno){
        Alumno alumnoEliminar = entityManager.find(Alumno.class, alumno.getId());
        entityManager.remove(alumnoEliminar);
    }
    
    
    public List<Materia> getMaterias() {
        Query query = entityManager.createQuery("SELECT m FROM Materia m ORDER BY m.id DESC");
        return query.getResultList();
    }

    @Transactional
    public void saveMateria(Materia materia) {
        entityManager.persist(materia);
    }

    @Transactional
    public void editMateria(Materia materia) {
        entityManager.merge(materia);
    }

    @Transactional
    public void deleteMateria(Materia materia) {
        Materia materiaEliminar = entityManager.find(Materia.class, materia.getId());
        if (materiaEliminar != null) {
            entityManager.remove(materiaEliminar);
        }
    }

    
    public List<Inscripciones> getInscripciones() {
        Query query = entityManager.createQuery("SELECT i FROM Inscripciones i");
        return query.getResultList();
    }

    @Transactional
    public void saveInscripcion(Inscripciones inscripcion) {
        entityManager.persist(inscripcion);
    }
}
