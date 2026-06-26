

package com.api.billhostpro.requestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Request DTO for ItemMaster.
 *
 * Used to receive data from client.
 */
@Data
public class ItemMasterRequestDTO {

    @NotBlank(message = "Item Name is required")
    private String itemName;

    private String barcode;

    private String hsnCode;

    @NotBlank(message = "Item Group Name is required")
    private String itemGroupName;

    @NotBlank(message = "Kitchen Name is required")
    private String kitchenName;

    @NotBlank(message = "Dish Head Name is required")
    private String dishHeadName;

    @NotBlank(message = "Unit Name is required")
    private String unitName;

    private String itemDescription;

    private BigDecimal dineInRate;

    private BigDecimal takeawayRate;

    private BigDecimal deliveryRate;

    private BigDecimal onlineRate;

    private BigDecimal mrp;

    private BigDecimal purchaseRate;

    private BigDecimal roomServiceRate;

    private BigDecimal gst;

    private BigDecimal cess;

    private BigDecimal discount;

    private Integer openingStock;

    @NotNull(message = "Favourite is required")
    private Boolean favourite;

    @NotNull(message = "In Package is required")
    private Boolean inPackage;

    @NotNull(message = "Discountable is required")
    private Boolean discountable;

    @NotNull(message = "Kitchen Stock is required")
    private Boolean kitchenStock;

    @NotNull(message = "Veg is required")
    private Boolean veg;

}
