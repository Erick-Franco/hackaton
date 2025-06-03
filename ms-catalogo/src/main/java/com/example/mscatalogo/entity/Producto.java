package com.example.mscatalogo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El código del producto no puede estar vacío")
    @Size(max = 20, message = "El código no puede exceder 20 caracteres")
    @Column(unique = true, nullable = false)
    private String codigo;

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    @Size(max = 150, message = "El nombre no puede exceder 150 caracteres")
    @Column(nullable = false)
    private String nombre;

    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    private String descripcion;

    @Min(value = 0, message = "La cantidad no puede ser negativa")
    @Column(nullable = false)
    private Integer cantidad = 0;

    @DecimalMin(value = "0.0", inclusive = false, message = "El precio de compra debe ser mayor a 0")
    @Digits(integer = 8, fraction = 2, message = "Precio de compra debe tener máximo 8 dígitos enteros y 2 decimales")
    @Column(name = "precio_compra", precision = 10, scale = 2)
    private BigDecimal precioCompra;

    @DecimalMin(value = "0.0", inclusive = false, message = "El precio de venta debe ser mayor a 0")
    @Digits(integer = 8, fraction = 2, message = "Precio de venta debe tener máximo 8 dígitos enteros y 2 decimales")
    @Column(name = "precio_venta", precision = 10, scale = 2)
    private BigDecimal precioVenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    @NotNull(message = "La categoría es obligatoria")
    private Categoria categoria;

    // Campos específicos para productos textiles
    @Enumerated(EnumType.STRING)
    private TipoProducto tipoProducto;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "producto_tallas", joinColumns = @JoinColumn(name = "producto_id"))
    @Column(name = "talla")
    private java.util.Set<Talla> tallasDisponibles;

    @ElementCollection
    @CollectionTable(name = "producto_colores", joinColumns = @JoinColumn(name = "producto_id"))
    @Column(name = "color")
    private java.util.Set<String> coloresDisponibles;

    @Enumerated(EnumType.STRING)
    private Material material;

    @Column(name = "personalizable")
    private Boolean personalizable = false;

    @Column(name = "imagen_url")
    private String imagenUrl;

    @Column(name = "activo")
    private Boolean activo = true;

    @Column(name = "destacado")
    private Boolean destacado = false;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        fechaActualizacion = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }

    // Enums para mejor estructura de datos
    public enum TipoProducto {
        PONCHO, CHALECO, CARDIGAN, CAPA, POLIVESTIDO, SWEATER, RUANA, CHAL,
        CASACA, CAMISETA, POLO, MOCHILA, NECESER, MONEDERO, FAJA, BOLSA,
        BOINA_MANOPLAS, CHALINA, BOLSO, SOBRE_CAMA, COJIN, MANTA, CINTA,
        ALPAQUITA, SERVICIO_IMPRESION, SERVICIO_SELLADO, SERVICIO_SUBLIMADO,
        SERVICIO_VINIL, SERVICIO_DTF
    }

    public enum Talla {
        XS, S, M, L, XL, XXL, XXXL, 
        TALLA_2, TALLA_4, TALLA_6, TALLA_8, TALLA_10, TALLA_12, TALLA_14, TALLA_16,
        UNICO
    }

    public enum Material {
        ALPACA, DRALON, ALPACRIL, ALGODON, POLIESTER, LANA, MIXTO
    }
}
