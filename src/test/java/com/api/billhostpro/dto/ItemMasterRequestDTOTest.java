package com.api.billhostpro.dto;

import com.api.billhostpro.requestDTO.ItemMasterRequestDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ItemMasterRequestDTOTest {

    @Test
    void noArgsConstructorShouldCreateInstance() {
        ItemMasterRequestDTO dto = new ItemMasterRequestDTO();

        assertNotNull(dto);
    }

    @Test
    void gettersAndSettersShouldWork() {
        ItemMasterRequestDTO dto = createRequestDTO();

        assertEquals("Paneer Tikka", dto.getItemName());
        assertEquals("BAR-100", dto.getBarcode());
        assertEquals("HSN-100", dto.getHsnCode());
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

    private ItemMasterRequestDTO createRequestDTO() {
        ItemMasterRequestDTO dto = new ItemMasterRequestDTO();
        dto.setItemName("Paneer Tikka");
        dto.setBarcode("BAR-100");
        dto.setHsnCode("HSN-100");
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
}
