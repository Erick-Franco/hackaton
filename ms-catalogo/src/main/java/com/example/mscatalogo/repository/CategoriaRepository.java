package com.example.mscatalogo.repository;

import com.example.mscatalogo.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
    Optional<Categoria> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
    
    List<Categoria> findByActivoTrue();
    List<Categoria> findByActivoTrueOrderByOrdenVisualizacionAsc();
    List<Categoria> findAllByOrderByOrdenVisualizacionAsc();
    
    List<Categoria> findByGeneroTarget(Categoria.GeneroTarget generoTarget);
    List<Categoria> findByGeneroTargetOrderByOrdenVisualizacionAsc(Categoria.GeneroTarget generoTarget);
    List<Categoria> findByGeneroTargetAndActivoTrueOrderByOrdenVisualizacionAsc(Categoria.GeneroTarget generoTarget);
    
    Long countByActivoTrue();
    Long countByGeneroTarget(Categoria.GeneroTarget generoTarget);
}
