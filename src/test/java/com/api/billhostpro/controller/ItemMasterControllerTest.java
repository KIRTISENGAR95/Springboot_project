package com.api.billhostpro.controller;

import com.api.billhostpro.requestDTO.ItemMasterRequestDTO;
import com.api.billhostpro.responseDTO.ItemMasterResponseDTO;
import com.api.billhostpro.service.ItemMasterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemMasterController.class)
class ItemMasterControllerTest {

    private static final String BASE_URL = "/v1/api/items";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ItemMasterService itemMasterService;

    @MockBean(name = "jpaMappingContext")
    private JpaMetamodelMappingContext jpaMetamodelMappingContext;

    @Test
    void saveShouldReturnCreatedItem() throws Exception {
        UUID id = UUID.randomUUID();
        ItemMasterRequestDTO requestDTO = createRequestDTO("Paneer Tikka");
        ItemMasterResponseDTO responseDTO = createResponseDTO(id, "Paneer Tikka");

        when(itemMasterService.save(requestDTO)).thenReturn(responseDTO);

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.itemName").value("Paneer Tikka"))
                .andExpect(jsonPath("$.barcode").value("BAR100"))
                .andExpect(jsonPath("$.itemGroupName").value("Starters"))
                .andExpect(jsonPath("$.favourite").value(true));

        verify(itemMasterService).save(requestDTO);
    }

    @Test
    void getAllShouldReturnItems() throws Exception {
        UUID id = UUID.randomUUID();
        ItemMasterResponseDTO responseDTO = createResponseDTO(id, "Paneer Tikka");

        when(itemMasterService.getAll()).thenReturn(List.of(responseDTO));

        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(id.toString()))
                .andExpect(jsonPath("$[0].itemName").value("Paneer Tikka"))
                .andExpect(jsonPath("$[0].unitName").value("Plate"));

        verify(itemMasterService).getAll();
    }

    @Test
    void getByIdShouldReturnItem() throws Exception {
        UUID id = UUID.randomUUID();
        ItemMasterResponseDTO responseDTO = createResponseDTO(id, "Paneer Tikka");

        when(itemMasterService.getById(id)).thenReturn(responseDTO);

        mockMvc.perform(get(BASE_URL + "/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.itemName").value("Paneer Tikka"))
                .andExpect(jsonPath("$.veg").value(true));

        verify(itemMasterService).getById(id);
    }

    @Test
    void updateShouldReturnUpdatedItem() throws Exception {
        UUID id = UUID.randomUUID();
        ItemMasterRequestDTO requestDTO = createRequestDTO("Updated Item");
        ItemMasterResponseDTO responseDTO = createResponseDTO(id, "Updated Item");

        when(itemMasterService.update(id, requestDTO)).thenReturn(responseDTO);

        mockMvc.perform(put(BASE_URL + "/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.itemName").value("Updated Item"))
                .andExpect(jsonPath("$.kitchenStock").value(true));

        verify(itemMasterService).update(id, requestDTO);
    }

    @Test
    void deleteShouldReturnSuccessMessage() throws Exception {
        UUID id = UUID.randomUUID();

        doNothing().when(itemMasterService).delete(id);

        mockMvc.perform(delete(BASE_URL + "/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string("Item Deleted Successfully"));

        verify(itemMasterService).delete(id);
    }

    private ItemMasterRequestDTO createRequestDTO(String itemName) {
        ItemMasterRequestDTO dto = new ItemMasterRequestDTO();
        dto.setItemName(itemName);
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

    private ItemMasterResponseDTO createResponseDTO(UUID id, String itemName) {
        ItemMasterResponseDTO dto = new ItemMasterResponseDTO();
        dto.setId(id);
        dto.setItemName(itemName);
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
}
