
package com.api.billhostpro.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Entity representing Item Master.
 *
 * Stores all item information.
 *
 * @author Kirti
 */
@Data
@Entity
@Table(name = "item_master")
public class ItemMaster extends BaseEntity {

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "hsn_code")
    private String hsnCode;

    @Column(name = "item_group_name", nullable = false)
    private String itemGroupName;

    @Column(name = "kitchen_name", nullable = false)
    private String kitchenName;

    @Column(name = "dish_head_name", nullable = false)
    private String dishHeadName;

    @Column(name = "unit_name", nullable = false)
    private String unitName;

    @Column(name = "item_description")
    private String itemDescription;

    @Column(name = "dine_in_rate")
    private BigDecimal dineInRate;

    @Column(name = "takeaway_rate")
    private BigDecimal takeawayRate;

    @Column(name = "delivery_rate")
    private BigDecimal deliveryRate;

    @Column(name = "online_rate")
    private BigDecimal onlineRate;

    @Column(name = "mrp")
    private BigDecimal mrp;

    @Column(name = "purchase_rate")
    private BigDecimal purchaseRate;

    @Column(name = "room_service_rate")
    private BigDecimal roomServiceRate;

    @Column(name = "gst")
    private BigDecimal gst;

    @Column(name = "cess")
    private BigDecimal cess;

    @Column(name = "discount")
    private BigDecimal discount;

    @Column(name = "opening_stock")
    private Integer openingStock;

    @Column(name = "is_favourite")
    private Boolean favourite;

    @Column(name = "in_package")
    private Boolean inPackage;

    @Column(name = "is_discountable")
    private Boolean discountable;

    @Column(name = "kitchen_stock")
    private Boolean kitchenStock;

    @Column(name = "is_veg")
    private Boolean veg;

}
