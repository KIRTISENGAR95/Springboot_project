

package com.api.billhostpro.requestDTO;

import com.api.billhostpro.validator.ValidBarcode;
import com.api.billhostpro.validator.ValidHsnCode;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
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
    @Size(min = 3, max = 100,
            message = "Item Name must be between 3 and 100 characters")
    private String itemName;

    @ValidBarcode
    private String barcode;

    @ValidHsnCode
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

    @DecimalMin(value = "0.00", message = "Dine In Rate cannot be negative")
    @DecimalMax(value = "999999.99",
            message = "Dine In Rate cannot be greater than 999999.99")
    @Digits(integer = 6, fraction = 2,
            message = "Dine In Rate can have up to 6 digits and 2 decimals")
    private BigDecimal dineInRate;

    @DecimalMin(value = "0.00", message = "Takeaway Rate cannot be negative")
    @DecimalMax(value = "999999.99",
            message = "Takeaway Rate cannot be greater than 999999.99")
    @Digits(integer = 6, fraction = 2,
            message = "Takeaway Rate can have up to 6 digits and 2 decimals")
    private BigDecimal takeawayRate;

    @DecimalMin(value = "0.00", message = "Delivery Rate cannot be negative")
    @DecimalMax(value = "999999.99",
            message = "Delivery Rate cannot be greater than 999999.99")
    @Digits(integer = 6, fraction = 2,
            message = "Delivery Rate can have up to 6 digits and 2 decimals")
    private BigDecimal deliveryRate;

    @DecimalMin(value = "0.00", message = "Online Rate cannot be negative")
    @DecimalMax(value = "999999.99",
            message = "Online Rate cannot be greater than 999999.99")
    @Digits(integer = 6, fraction = 2,
            message = "Online Rate can have up to 6 digits and 2 decimals")
    private BigDecimal onlineRate;

    @DecimalMin(value = "0.00", message = "MRP cannot be negative")
    @DecimalMax(value = "999999.99",
            message = "MRP cannot be greater than 999999.99")
    @Digits(integer = 6, fraction = 2,
            message = "MRP can have up to 6 digits and 2 decimals")
    private BigDecimal mrp;

    @DecimalMin(value = "0.00", message = "Purchase Rate cannot be negative")
    @DecimalMax(value = "999999.99",
            message = "Purchase Rate cannot be greater than 999999.99")
    @Digits(integer = 6, fraction = 2,
            message = "Purchase Rate can have up to 6 digits and 2 decimals")
    private BigDecimal purchaseRate;

    @DecimalMin(value = "0.00", message = "Room Service Rate cannot be negative")
    @DecimalMax(value = "999999.99",
            message = "Room Service Rate cannot be greater than 999999.99")
    @Digits(integer = 6, fraction = 2,
            message = "Room Service Rate can have up to 6 digits and 2 decimals")
    private BigDecimal roomServiceRate;

    @DecimalMin(value = "0.00", message = "GST must be between 0 and 100")
    @DecimalMax(value = "100.00", message = "GST must be between 0 and 100")
    private BigDecimal gst;

    @DecimalMin(value = "0.00", message = "CESS must be between 0 and 100")
    @DecimalMax(value = "100.00", message = "CESS must be between 0 and 100")
    private BigDecimal cess;

    @DecimalMin(value = "0.00", message = "Discount must be between 0 and 100")
    @DecimalMax(value = "100.00",
            message = "Discount must be between 0 and 100")
    private BigDecimal discount;

    @PositiveOrZero(message = "Opening Stock cannot be negative")
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
