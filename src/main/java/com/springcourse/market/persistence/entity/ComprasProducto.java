package com.springcourse.market.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "compras_productos")
public class ComprasProducto {

    @EmbeddedId
    private ComprasProductoPK id;

    private Integer cantidad;

    private BigDecimal total;

    private Boolean estado;

    @ManyToOne()
    @MapsId("idCompra")
    @JoinColumn(name = "id_compra", updatable = false, insertable = false)
    private Compra compra;

    @ManyToOne()
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private Producto producto;
}
