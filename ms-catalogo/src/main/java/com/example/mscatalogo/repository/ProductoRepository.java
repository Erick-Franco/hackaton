package com.example.mscatalogo.repository;

import com.example.mscatalogo.entity.Categoria;
import com.example.mscatalogo.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
    // Búsquedas básicas
    Optional<Producto> findByCodigo(String codigo);
    boolean existsByCodigo(String codigo);
    
    // Filtros por estado
    List<Producto> findByActivoTrue();
    List<Producto> findByDestacadoTrue();
    List<Producto> findByPersonalizableTrue();
    
    // Filtros por categoría
    List<Producto> findByCategoriaId(Long categoriaId);
    List<Producto> findByCategoriaGeneroTarget(Categoria.GeneroTarget generoTarget);
    
    // Filtros por características del producto
    List<Producto> findByTipoProducto(Producto.TipoProducto tipoProducto);
    List<Producto> findByMaterial(Producto.Material material);
    
    // Búsquedas por texto
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    List<Producto> findByDescripcionContainingIgnoreCase(String descripcion);
    List<Producto> findByNombreContainingIgnoreCaseOrDescripcionContainingIgnoreCase(String nombre, String descripcion);
    
    // Filtros por precio
    List<Producto> findByPrecioVentaBetween(BigDecimal precioMin, BigDecimal precioMax);
    List<Producto> findByPrecioVentaLessThanEqual(BigDecimal precioMax);
    List<Producto> findByPrecioVentaGreaterThanEqual(BigDecimal precioMin);
    
    // Filtros por stock
    List<Producto> findByCantidadLessThan(Integer cantidad);
    List<Producto> findByCantidadGreaterThan(Integer cantidad);
    List<Producto> findByCantidadBetween(Integer min, Integer max);
    
    // Contadores
    Long countByActivoTrue();
    Long countByCategoriaId(Long categoriaId);
    Long countByTipoProducto(Producto.TipoProducto tipoProducto);
    Long countByDestacadoTrue();
    Long countByPersonalizableTrue();
    
    // Consultas personalizadas
    @Query("SELECT p FROM Producto p WHERE p.activo = true AND p.categoria.generoTarget = :generoTarget ORDER BY p.destacado DESC, p.nombre ASC")
    List<Producto> findProductosActivosPorGenero(@Param("generoTarget") Categoria.GeneroTarget generoTarget);
    
    @Query("SELECT p FROM Producto p WHERE p.activo = true AND p.categoria.id = :categoriaId ORDER BY p.destacado DESC, p.nombre ASC")
    List<Producto> findProductosActivosPorCategoria(@Param("categoriaId") Long categoriaId);
    
    @Query("SELECT p FROM Producto p WHERE p.activo = true AND p.cantidad < :stockMinimo ORDER BY p.cantidad ASC")
    List<Producto> findProductosConStockBajo(@Param("stockMinimo") Integer stockMinimo);
    
    @Query("SELECT p FROM Producto p WHERE p.activo = true AND (p.nombre LIKE %:termino% OR p.descripcion LIKE %:termino% OR p.codigo LIKE %:termino%) ORDER BY p.destacado DESC")
    List<Producto> buscarProductosActivos(@Param("termino") String termino);
}
