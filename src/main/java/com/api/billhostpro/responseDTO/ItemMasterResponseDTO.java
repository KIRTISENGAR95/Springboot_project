

package com.api.billhostpro.responseDTO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Response DTO for ItemMaster.
 *
 * Used to send data to client.
 */
@Data
public class ItemMasterResponseDTO {

    private UUID id;

    private String itemName;

    private String barcode;

    private String hsnCode;

    private String itemGroupName;

    private String kitchenName;

    private String dishHeadName;

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

    private Boolean favourite;

    private Boolean inPackage;

    private Boolean discountable;

    private Boolean kitchenStock;

    private Boolean veg;

}
