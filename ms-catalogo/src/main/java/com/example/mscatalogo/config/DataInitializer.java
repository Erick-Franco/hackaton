package com.example.mscatalogo.config;

import com.example.mscatalogo.entity.Categoria;
import com.example.mscatalogo.entity.Producto;
import com.example.mscatalogo.repository.CategoriaRepository;
import com.example.mscatalogo.repository.ProductoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public void run(String... args) throws Exception {
        if (categoriaRepository.count() == 0) {
            log.info("Inicializando datos del catálogo textil...");
            inicializarCategorias();
            inicializarProductos();
            log.info("Datos inicializados correctamente!");
        } else {
            log.info("Los datos ya existen, omitiendo inicialización.");
        }
    }

    private void inicializarCategorias() {
        log.info("Creando categorías...");
        
        List<Categoria> categorias = Arrays.asList(
            // 🧥 PARA ELLA
            new Categoria(null, "Ponchos", "Ponchos para mujer en diversos estilos", Categoria.GeneroTarget.PARA_ELLA, true, 1),
            new Categoria(null, "Chalecos", "Chalecos elegantes para mujer", Categoria.GeneroTarget.PARA_ELLA, true, 2),
            new Categoria(null, "Cardigans", "Cardigans cómodos y modernos", Categoria.GeneroTarget.PARA_ELLA, true, 3),
            new Categoria(null, "Capas", "Capas sofisticadas para ocasiones especiales", Categoria.GeneroTarget.PARA_ELLA, true, 4),
            new Categoria(null, "Polivestidos", "Polivestidos versátiles y elegantes", Categoria.GeneroTarget.PARA_ELLA, true, 5),
            new Categoria(null, "Sweaters", "Sweaters cálidos para mujer", Categoria.GeneroTarget.PARA_ELLA, true, 6),
            new Categoria(null, "Otros", "Otros productos para mujer (Ruanas, Chales)", Categoria.GeneroTarget.PARA_ELLA, true, 7),

            // 👕 PARA ÉL
            new Categoria(null, "Ponchos Hombre", "Ponchos masculinos de calidad", Categoria.GeneroTarget.PARA_EL, true, 8),
            new Categoria(null, "Chalecos Hombre", "Chalecos para caballeros", Categoria.GeneroTarget.PARA_EL, true, 9),
            new Categoria(null, "Sweaters Hombre", "Sweaters cómodos para hombre", Categoria.GeneroTarget.PARA_EL, true, 10),

            // 👶 PARA NIÑOS
            new Categoria(null, "Chalecos Niños", "Chalecos para los más pequeños", Categoria.GeneroTarget.PARA_NINOS, true, 11),
            new Categoria(null, "Casacas Niños", "Casacas infantiles", Categoria.GeneroTarget.PARA_NINOS, true, 12),
            new Categoria(null, "Camisetas Niños", "Camisetas divertidas para niños", Categoria.GeneroTarget.PARA_NINOS, true, 13),
            new Categoria(null, "Ponchos Niños", "Ponchos infantiles adorables", Categoria.GeneroTarget.PARA_NINOS, true, 14),

            // 🧶 MÁS LÍNEAS
            new Categoria(null, "Camisetas Personalizables", "Camisetas con opción de personalización", Categoria.GeneroTarget.MAS_LINEAS, true, 15),
            new Categoria(null, "Polos Personalizables", "Polos con/sin personalización", Categoria.GeneroTarget.MAS_LINEAS, true, 16),
            new Categoria(null, "Sweaters Unisex", "Sweaters unisex con/sin personalización", Categoria.GeneroTarget.MAS_LINEAS, true, 17),

            // 👜 ACCESORIOS
            new Categoria(null, "Mochilas", "Mochilas funcionales y elegantes", Categoria.GeneroTarget.ACCESORIOS, true, 18),
            new Categoria(null, "Neceseres", "Neceseres prácticos y bonitos", Categoria.GeneroTarget.ACCESORIOS, true, 19),
            new Categoria(null, "Otros Accesorios", "Monederos, fajas, bolsas y más", Categoria.GeneroTarget.ACCESORIOS, true, 20),

            // 🏠 HOGAR
            new Categoria(null, "Hogar", "Productos textiles para el hogar", Categoria.GeneroTarget.HOGAR, true, 21),

            // 🛠️ SERVICIOS
            new Categoria(null, "Servicios", "Servicios de personalización textil", Categoria.GeneroTarget.SERVICIOS, true, 22)
        );

        categoriaRepository.saveAll(categorias);
        log.info("Categorías creadas: {}", categorias.size());
    }

    private void inicializarProductos() {
        log.info("Creando productos...");
        
        // Obtener categorías
        Categoria ponchosMujer = categoriaRepository.findByNombre("Ponchos").orElse(null);
        Categoria chalecosMujer = categoriaRepository.findByNombre("Chalecos").orElse(null);
        Categoria cardigans = categoriaRepository.findByNombre("Cardigans").orElse(null);
        Categoria capas = categoriaRepository.findByNombre("Capas").orElse(null);
        Categoria servicios = categoriaRepository.findByNombre("Servicios").orElse(null);

        List<Producto> productos = Arrays.asList(
            // 🧥 PARA ELLA - Ponchos
            crearProducto("PON001", "Poncho Tortuga", "Poncho estilo tortuga clásico para mujer", 
                         BigDecimal.valueOf(45.00), BigDecimal.valueOf(75.00), ponchosMujer,
                         Producto.TipoProducto.PONCHO, Producto.Material.ALPACA, false, true),
            
            crearProducto("PON002", "Poncho Sada", "Poncho modelo Sada con diseño único", 
                         BigDecimal.valueOf(40.00), BigDecimal.valueOf(70.00), ponchosMujer,
                         Producto.TipoProducto.PONCHO, Producto.Material.ALPACRIL, false, false),
            
            crearProducto("PON003", "Ponchos Greca", "Poncho con diseño de greca andina tradicional", 
                         BigDecimal.valueOf(50.00), BigDecimal.valueOf(85.00), ponchosMujer,
                         Producto.TipoProducto.PONCHO, Producto.Material.ALPACA, false, true),
            
            crearProducto("PON004", "Ponchos Andinos", "Poncho con motivos andinos auténticos", 
                         BigDecimal.valueOf(48.00), BigDecimal.valueOf(80.00), ponchosMujer,
                         Producto.TipoProducto.PONCHO, Producto.Material.ALPACA, false, false),

            // 🧥 PARA ELLA - Chalecos
            crearProducto("CHA001", "Chaleco Cielo", "Chaleco ligero modelo Cielo", 
                         BigDecimal.valueOf(35.00), BigDecimal.valueOf(60.00), chalecosMujer,
                         Producto.TipoProducto.CHALECO, Producto.Material.ALPACRIL, false, false),
            
            crearProducto("CHA002", "Chaleco Estrella", "Chaleco elegante modelo Estrella", 
                         BigDecimal.valueOf(38.00), BigDecimal.valueOf(65.00), chalecosMujer,
                         Producto.TipoProducto.CHALECO, Producto.Material.ALPACA, false, true),
            
            crearProducto("CHA003", "Chaleco Bela", "Chaleco cómodo modelo Bela", 
                         BigDecimal.valueOf(36.00), BigDecimal.valueOf(62.00), chalecosMujer,
                         Producto.TipoProducto.CHALECO, Producto.Material.DRALON, false, false),
            
            crearProducto("CHA004", "Chaleco Andino", "Chaleco con diseño andino tradicional", 
                         BigDecimal.valueOf(42.00), BigDecimal.valueOf(72.00), chalecosMujer,
                         Producto.TipoProducto.CHALECO, Producto.Material.ALPACA, false, false),

            // 🧥 PARA ELLA - Cardigans
            crearProducto("CAR001", "Cardigan Camila", "Cardigan clásico modelo Camila", 
                         BigDecimal.valueOf(45.00), BigDecimal.valueOf(78.00), cardigans,
                         Producto.TipoProducto.CARDIGAN, Producto.Material.ALPACRIL, false, false),
            
            crearProducto("CAR002", "Cardigan Chino", "Cardigan estilo oriental elegante", 
                         BigDecimal.valueOf(48.00), BigDecimal.valueOf(82.00), cardigans,
                         Producto.TipoProducto.CARDIGAN, Producto.Material.ALPACA, false, true),
            
            crearProducto("CAR003", "Cardigan Andrea", "Cardigan moderno modelo Andrea", 
                         BigDecimal.valueOf(46.00), BigDecimal.valueOf(80.00), cardigans,
                         Producto.TipoProducto.CARDIGAN, Producto.Material.DRALON, false, false),

            // 🧥 PARA ELLA - Capas
            crearProducto("CAP001", "Capa Misa", "Capa elegante para ocasiones especiales", 
                         BigDecimal.valueOf(55.00), BigDecimal.valueOf(95.00), capas,
                         Producto.TipoProducto.CAPA, Producto.Material.ALPACA, false, true),
            
            crearProducto("CAP002", "Capa Amaris", "Capa sofisticada modelo Amaris", 
                         BigDecimal.valueOf(52.00), BigDecimal.valueOf(90.00), capas,
                         Producto.TipoProducto.CAPA, Producto.Material.ALPACRIL, false, false),
            
            crearProducto("CAP003", "Capa Hino", "Capa tradicional modelo Hino", 
                         BigDecimal.valueOf(58.00), BigDecimal.valueOf(98.00), capas,
                         Producto.TipoProducto.CAPA, Producto.Material.ALPACA, false, false),
            
            crearProducto("CAP004", "Capa Sumaq", "Capa hermosa modelo Sumaq", 
                         BigDecimal.valueOf(60.00), BigDecimal.valueOf(105.00), capas,
                         Producto.TipoProducto.CAPA, Producto.Material.ALPACA, false, true),

            // 🛠️ SERVICIOS
            crearProducto("SER001", "Impresión", "Servicio de impresión personalizada", 
                         BigDecimal.valueOf(5.00), BigDecimal.valueOf(15.00), servicios,
                         Producto.TipoProducto.SERVICIO_IMPRESION, null, false, false),
            
            crearProducto("SER002", "Sellado al vacío", "Servicio de sellado al vacío", 
                         BigDecimal.valueOf(2.00), BigDecimal.valueOf(8.00), servicios,
                         Producto.TipoProducto.SERVICIO_SELLADO, null, false, false),
            
            crearProducto("SER003", "Sublimado", "Servicio de sublimado de alta calidad", 
                         BigDecimal.valueOf(8.00), BigDecimal.valueOf(20.00), servicios,
                         Producto.TipoProducto.SERVICIO_SUBLIMADO, null, false, false),
            
            crearProducto("SER004", "Vinil textil", "Aplicación de vinil textil", 
                         BigDecimal.valueOf(6.00), BigDecimal.valueOf(18.00), servicios,
                         Producto.TipoProducto.SERVICIO_VINIL, null, false, false),
            
            crearProducto("SER005", "DTF", "Servicio DTF (Direct to Film)", 
                         BigDecimal.valueOf(10.00), BigDecimal.valueOf(25.00), servicios,
                         Producto.TipoProducto.SERVICIO_DTF, null, false, false)
        );

        productoRepository.saveAll(productos);
        log.info("Productos creados: {}", productos.size());
    }

    private Producto crearProducto(String codigo, String nombre, String descripcion,
                                  BigDecimal precioCompra, BigDecimal precioVenta,
                                  Categoria categoria, Producto.TipoProducto tipoProducto,
                                  Producto.Material material, boolean personalizable, boolean destacado) {
        Producto producto = new Producto();
        producto.setCodigo(codigo);
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecioCompra(precioCompra);
        producto.setPrecioVenta(precioVenta);
        producto.setCategoria(categoria);
        producto.setTipoProducto(tipoProducto);
        producto.setMaterial(material);
        producto.setPersonalizable(personalizable);
        producto.setDestacado(destacado);
        producto.setCantidad(generarStockAleatorio());
        producto.setActivo(true);
        
        // Configurar tallas según el tipo de producto
        if (esProductoConTallas(tipoProducto)) {
            producto.setTallasDisponibles(generarTallasSegunTipo(tipoProducto));
        }
        
        // Configurar colores según el material
        if (material != null) {
            producto.setColoresDisponibles(generarColoresSegunMaterial(material));
        }
        
        return producto;
    }

    private boolean esProductoConTallas(Producto.TipoProducto tipo) {
        return tipo != null && !Arrays.asList(
            Producto.TipoProducto.SERVICIO_IMPRESION,
            Producto.TipoProducto.SERVICIO_SELLADO,
            Producto.TipoProducto.SERVICIO_SUBLIMADO,
            Producto.TipoProducto.SERVICIO_VINIL,
            Producto.TipoProducto.SERVICIO_DTF,
            Producto.TipoProducto.MONEDERO,
            Producto.TipoProducto.CINTA,
            Producto.TipoProducto.ALPAQUITA
        ).contains(tipo);
    }

    private java.util.Set<Producto.Talla> generarTallasSegunTipo(Producto.TipoProducto tipo) {
        switch (tipo) {
            case CAMISETA:
            case POLO:
                return new HashSet<>(Arrays.asList(
                    Producto.Talla.TALLA_2, Producto.Talla.TALLA_4, Producto.Talla.TALLA_6,
                    Producto.Talla.TALLA_8, Producto.Talla.TALLA_10, Producto.Talla.TALLA_12
                ));
            case PONCHO:
            case CHALECO:
            case CARDIGAN:
            case SWEATER:
            case CAPA:
            case POLIVESTIDO:
            case CASACA:
                return new HashSet<>(Arrays.asList(
                    Producto.Talla.S, Producto.Talla.M, Producto.Talla.L, Producto.Talla.XL
                ));
            case MOCHILA:
            case NECESER:
            case BOLSO:
            case CHALINA:
            case RUANA:
            case CHAL:
            case FAJA:
            case BOINA_MANOPLAS:
                return new HashSet<>(Arrays.asList(Producto.Talla.UNICO));
            default:
                return new HashSet<>(Arrays.asList(
                    Producto.Talla.S, Producto.Talla.M, Producto.Talla.L
                ));
        }
    }

    private java.util.Set<String> generarColoresSegunMaterial(Producto.Material material) {
        switch (material) {
            case ALPACA:
                return new HashSet<>(Arrays.asList(
                    "Natural", "Marrón", "Gris", "Negro", "Blanco"
                ));
            case ALPACRIL:
                return new HashSet<>(Arrays.asList(
                    "Natural", "Beige", "Rosado", "Celeste", "Verde", "Rojo"
                ));
            case DRALON:
                return new HashSet<>(Arrays.asList(
                    "Azul", "Rojo", "Verde", "Amarillo", "Blanco", "Negro"
                ));
            case ALGODON:
                return new HashSet<>(Arrays.asList(
                    "Blanco", "Negro", "Gris", "Azul", "Rojo", "Verde", "Amarillo"
                ));
            case LANA:
                return new HashSet<>(Arrays.asList(
                    "Natural", "Marrón", "Gris", "Blanco"
                ));
            case MIXTO:
                return new HashSet<>(Arrays.asList(
                    "Negro", "Marrón", "Azul", "Rojo", "Verde"
                ));
            default:
                return new HashSet<>(Arrays.asList(
                    "Natural", "Marrón", "Gris"
                ));
        }
    }

    private Integer generarStockAleatorio() {
        // Generar stock entre 5 y 50 unidades
        return (int) (Math.random() * 46) + 5;
    }
}
