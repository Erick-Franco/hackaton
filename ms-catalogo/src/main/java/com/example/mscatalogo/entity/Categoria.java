package com.example.mscatalogo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categorias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la categoría no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    @Column(unique = true, nullable = false)
    private String nombre;

    @Size(max = 255, message = "La descripción no puede exceder 255 caracteres")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero_target")
    private GeneroTarget generoTarget;

    @Column(name = "activo")
    private Boolean activo = true;

    @Column(name = "orden_visualizacion")
    private Integer ordenVisualizacion;

    public enum GeneroTarget {
        PARA_ELLA("🧥 PARA ELLA"),
        PARA_EL("👕 PARA ÉL"),
        PARA_NINOS("👶 PARA NIÑOS"),
        MAS_LINEAS("🧶 MÁS LÍNEAS"),
        ACCESORIOS("👜 ACCESORIOS"),
        HOGAR("🏠 HOGAR"),
        SERVICIOS("🛠️ SERVICIOS");

        private final String displayName;

        GeneroTarget(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }
}
