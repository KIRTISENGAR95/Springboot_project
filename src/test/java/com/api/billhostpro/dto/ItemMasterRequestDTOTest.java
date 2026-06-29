package com.api.billhostpro.dto;

import com.api.billhostpro.requestDTO.ItemMasterRequestDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ItemMasterRequestDTOTest {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void noArgsConstructorShouldCreateInstance() {
        ItemMasterRequestDTO dto = new ItemMasterRequestDTO();

        assertNotNull(dto);
    }

    @Test
    void gettersAndSettersShouldWork() {
        ItemMasterRequestDTO dto = createRequestDTO();

        assertEquals("Paneer Tikka", dto.getItemName());
        assertEquals("BAR100", dto.getBarcode());
        assertEquals("1001", dto.getHsnCode());
        assertEquals("Starters", dto.getItemGroupName());
        assertEquals("Main Kitchen", dto.getKitchenName());
        assertEquals("Tandoor", dto.getDishHeadName());
        assertEquals("Plate", dto.getUnitName());
        assertEquals("Spiced grilled paneer", dto.getItemDescription());
        assertEquals(BigDecimal.valueOf(220), dto.getDineInRate());
        assertEquals(BigDecimal.valueOf(210), dto.getTakeawayRate());
        assertEquals(BigDecimal.valueOf(230), dto.getDeliveryRate());
        assertEquals(BigDecimal.valueOf(240), dto.getOnlineRate());
        assertEquals(BigDecimal.valueOf(250), dto.getMrp());
        assertEquals(BigDecimal.valueOf(120), dto.getPurchaseRate());
        assertEquals(BigDecimal.valueOf(260), dto.getRoomServiceRate());
        assertEquals(BigDecimal.valueOf(5), dto.getGst());
        assertEquals(BigDecimal.valueOf(1), dto.getCess());
        assertEquals(BigDecimal.valueOf(10), dto.getDiscount());
        assertEquals(25, dto.getOpeningStock());
        assertEquals(Boolean.TRUE, dto.getFavourite());
        assertEquals(Boolean.FALSE, dto.getInPackage());
        assertEquals(Boolean.TRUE, dto.getDiscountable());
        assertEquals(Boolean.TRUE, dto.getKitchenStock());
        assertEquals(Boolean.TRUE, dto.getVeg());
    }

    @Test
    void equalsAndHashCodeShouldUseFieldValues() {
        ItemMasterRequestDTO firstDTO = createRequestDTO();
        ItemMasterRequestDTO secondDTO = createRequestDTO();
        ItemMasterRequestDTO differentDTO = createRequestDTO();
        differentDTO.setItemName("Veg Biryani");

        assertEquals(firstDTO, secondDTO);
        assertEquals(firstDTO.hashCode(), secondDTO.hashCode());
        assertNotEquals(firstDTO, differentDTO);
        assertNotEquals(null, firstDTO);
        assertNotEquals("Paneer Tikka", firstDTO);
    }

    @Test
    void validRequestDTOShouldPassValidation() {
        ItemMasterRequestDTO dto = createRequestDTO();

        Set<ConstraintViolation<ItemMasterRequestDTO>> violations =
                validator.validate(dto);

        assertTrue(violations.isEmpty());
    }

    @Test
    void invalidBarcodeShouldFailValidation() {
        ItemMasterRequestDTO dto = createRequestDTO();
        dto.setBarcode("BAR-100");

        Set<ConstraintViolation<ItemMasterRequestDTO>> violations =
                validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertTrue(hasMessage(
                violations,
                "Barcode must be alphanumeric and between 5 and 30 characters"
        ));
    }

    @Test
    void invalidHsnCodeShouldFailValidation() {
        ItemMasterRequestDTO dto = createRequestDTO();
        dto.setHsnCode("HSN-100");

        Set<ConstraintViolation<ItemMasterRequestDTO>> violations =
                validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertTrue(hasMessage(
                violations,
                "HSN Code must contain only digits and be between 4 and 8 characters"
        ));
    }

    @Test
    void outOfRangeRateShouldFailValidation() {
        ItemMasterRequestDTO dto = createRequestDTO();
        dto.setDineInRate(BigDecimal.valueOf(1000000));

        Set<ConstraintViolation<ItemMasterRequestDTO>> violations =
                validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertTrue(hasMessage(
                violations,
                "Dine In Rate cannot be greater than 999999.99"
        ));
    }

    @Test
    void blankItemNameShouldFailValidation() {
        ItemMasterRequestDTO dto = createRequestDTO();
        dto.setItemName("   ");

        Set<ConstraintViolation<ItemMasterRequestDTO>> violations =
                validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertTrue(hasMessage(violations, "Item Name is required"));
    }

    private ItemMasterRequestDTO createRequestDTO() {
        ItemMasterRequestDTO dto = new ItemMasterRequestDTO();
        dto.setItemName("Paneer Tikka");
        dto.setBarcode("BAR100");
        dto.setHsnCode("1001");
        dto.setItemGroupName("Starters");
        dto.setKitchenName("Main Kitchen");
        dto.setDishHeadName("Tandoor");
        dto.setUnitName("Plate");
        dto.setItemDescription("Spiced grilled paneer");
        dto.setDineInRate(BigDecimal.valueOf(220));
        dto.setTakeawayRate(BigDecimal.valueOf(210));
        dto.setDeliveryRate(BigDecimal.valueOf(230));
        dto.setOnlineRate(BigDecimal.valueOf(240));
        dto.setMrp(BigDecimal.valueOf(250));
        dto.setPurchaseRate(BigDecimal.valueOf(120));
        dto.setRoomServiceRate(BigDecimal.valueOf(260));
        dto.setGst(BigDecimal.valueOf(5));
        dto.setCess(BigDecimal.valueOf(1));
        dto.setDiscount(BigDecimal.valueOf(10));
        dto.setOpeningStock(25);
        dto.setFavourite(Boolean.TRUE);
        dto.setInPackage(Boolean.FALSE);
        dto.setDiscountable(Boolean.TRUE);
        dto.setKitchenStock(Boolean.TRUE);
        dto.setVeg(Boolean.TRUE);
        return dto;
    }

    private boolean hasMessage(
            Set<ConstraintViolation<ItemMasterRequestDTO>> violations,
            String message) {
        return violations.stream()
                .anyMatch(violation -> message.equals(violation.getMessage()));
    }
}
