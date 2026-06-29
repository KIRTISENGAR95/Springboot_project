package com.api.billhostpro.serviceimpl;

import com.api.billhostpro.entity.ItemMaster;
import com.api.billhostpro.exception.ResourceNotFoundException;
import com.api.billhostpro.repository.ItemMasterRepository;
import com.api.billhostpro.requestDTO.ItemMasterRequestDTO;
import com.api.billhostpro.responseDTO.ItemMasterResponseDTO;
import com.api.billhostpro.utility.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemMasterServiceImplTest {

    @Mock
    private ItemMasterRepository repository;

    @InjectMocks
    private ItemMasterServiceImpl service;

    @Test
    void saveShouldPersistMappedEntityAndReturnMappedResponse() {
        ItemMasterRequestDTO requestDTO = createRequestDTO("Paneer Tikka");
        UUID id = UUID.randomUUID();

        when(repository.save(any(ItemMaster.class))).thenAnswer(invocation -> {
            ItemMaster item = invocation.getArgument(0);
            item.setId(id);
            return item;
        });

        ItemMasterResponseDTO responseDTO = service.save(requestDTO);

        ArgumentCaptor<ItemMaster> captor = ArgumentCaptor.forClass(ItemMaster.class);
        verify(repository).save(captor.capture());
        assertItemMatchesRequest(captor.getValue(), requestDTO);
        assertResponseMatchesRequest(responseDTO, requestDTO, id);
    }

    @Test
    void getAllShouldReturnMappedResponses() {
        UUID firstId = UUID.randomUUID();
        UUID secondId = UUID.randomUUID();
        ItemMaster firstItem = createItem(firstId, "Paneer Tikka");
        ItemMaster secondItem = createItem(secondId, "Veg Biryani");

        when(repository.findAll()).thenReturn(List.of(firstItem, secondItem));

        List<ItemMasterResponseDTO> responseDTOList = service.getAll();

        assertEquals(2, responseDTOList.size());
        assertResponseMatchesItem(responseDTOList.get(0), firstItem);
        assertResponseMatchesItem(responseDTOList.get(1), secondItem);
        verify(repository).findAll();
    }

    @Test
    void getByIdShouldReturnMappedResponseWhenItemExists() {
        UUID id = UUID.randomUUID();
        ItemMaster item = createItem(id, "Paneer Tikka");

        when(repository.findById(id)).thenReturn(Optional.of(item));

        ItemMasterResponseDTO responseDTO = service.getById(id);

        assertResponseMatchesItem(responseDTO, item);
        verify(repository).findById(id);
    }

    @Test
    void getByIdShouldThrowResourceNotFoundExceptionWhenItemDoesNotExist() {
        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> service.getById(id)
        );

        assertEquals(Constants.ITEM_NOT_FOUND, exception.getMessage());
        verify(repository).findById(id);
    }

    @Test
    void updateShouldUpdateEntityAndReturnMappedResponseWhenItemExists() {
        UUID id = UUID.randomUUID();
        ItemMaster existingItem = createItem(id, "Old Item");
        ItemMasterRequestDTO requestDTO = createRequestDTO("Updated Item");

        when(repository.findById(id)).thenReturn(Optional.of(existingItem));
        when(repository.save(existingItem)).thenReturn(existingItem);

        ItemMasterResponseDTO responseDTO = service.update(id, requestDTO);

        verify(repository).findById(id);
        verify(repository).save(existingItem);
        assertItemMatchesRequest(existingItem, requestDTO);
        assertResponseMatchesRequest(responseDTO, requestDTO, id);
    }

    @Test
    void updateShouldThrowResourceNotFoundExceptionWhenItemDoesNotExist() {
        UUID id = UUID.randomUUID();
        ItemMasterRequestDTO requestDTO = createRequestDTO("Updated Item");

        when(repository.findById(id)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> service.update(id, requestDTO)
        );

        assertEquals(Constants.ITEM_NOT_FOUND, exception.getMessage());
        verify(repository).findById(id);
        verify(repository, never()).save(any(ItemMaster.class));
    }

    @Test
    void deleteShouldDeleteItemWhenItemExists() {
        UUID id = UUID.randomUUID();
        ItemMaster item = createItem(id, "Paneer Tikka");

        when(repository.findById(id)).thenReturn(Optional.of(item));

        service.delete(id);

        verify(repository).findById(id);
        verify(repository).delete(item);
    }

    @Test
    void deleteShouldThrowResourceNotFoundExceptionWhenItemDoesNotExist() {
        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> service.delete(id)
        );

        assertEquals(Constants.ITEM_NOT_FOUND, exception.getMessage());
        verify(repository).findById(id);
        verify(repository, never()).delete(any(ItemMaster.class));
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

    private ItemMaster createItem(UUID id, String itemName) {
        ItemMasterRequestDTO requestDTO = createRequestDTO(itemName);
        ItemMaster item = new ItemMaster();
        item.setId(id);
        item.setItemName(requestDTO.getItemName());
        item.setBarcode(requestDTO.getBarcode());
        item.setHsnCode(requestDTO.getHsnCode());
        item.setItemGroupName(requestDTO.getItemGroupName());
        item.setKitchenName(requestDTO.getKitchenName());
        item.setDishHeadName(requestDTO.getDishHeadName());
        item.setUnitName(requestDTO.getUnitName());
        item.setItemDescription(requestDTO.getItemDescription());
        item.setDineInRate(requestDTO.getDineInRate());
        item.setTakeawayRate(requestDTO.getTakeawayRate());
        item.setDeliveryRate(requestDTO.getDeliveryRate());
        item.setOnlineRate(requestDTO.getOnlineRate());
        item.setMrp(requestDTO.getMrp());
        item.setPurchaseRate(requestDTO.getPurchaseRate());
        item.setRoomServiceRate(requestDTO.getRoomServiceRate());
        item.setGst(requestDTO.getGst());
        item.setCess(requestDTO.getCess());
        item.setDiscount(requestDTO.getDiscount());
        item.setOpeningStock(requestDTO.getOpeningStock());
        item.setFavourite(requestDTO.getFavourite());
        item.setInPackage(requestDTO.getInPackage());
        item.setDiscountable(requestDTO.getDiscountable());
        item.setKitchenStock(requestDTO.getKitchenStock());
        item.setVeg(requestDTO.getVeg());
        return item;
    }

    private void assertItemMatchesRequest(ItemMaster item, ItemMasterRequestDTO requestDTO) {
        assertAll(
                () -> assertEquals(requestDTO.getItemName(), item.getItemName()),
                () -> assertEquals(requestDTO.getBarcode(), item.getBarcode()),
                () -> assertEquals(requestDTO.getHsnCode(), item.getHsnCode()),
                () -> assertEquals(requestDTO.getItemGroupName(), item.getItemGroupName()),
                () -> assertEquals(requestDTO.getKitchenName(), item.getKitchenName()),
                () -> assertEquals(requestDTO.getDishHeadName(), item.getDishHeadName()),
                () -> assertEquals(requestDTO.getUnitName(), item.getUnitName()),
                () -> assertEquals(requestDTO.getItemDescription(), item.getItemDescription()),
                () -> assertEquals(requestDTO.getDineInRate(), item.getDineInRate()),
                () -> assertEquals(requestDTO.getTakeawayRate(), item.getTakeawayRate()),
                () -> assertEquals(requestDTO.getDeliveryRate(), item.getDeliveryRate()),
                () -> assertEquals(requestDTO.getOnlineRate(), item.getOnlineRate()),
                () -> assertEquals(requestDTO.getMrp(), item.getMrp()),
                () -> assertEquals(requestDTO.getPurchaseRate(), item.getPurchaseRate()),
                () -> assertEquals(requestDTO.getRoomServiceRate(), item.getRoomServiceRate()),
                () -> assertEquals(requestDTO.getGst(), item.getGst()),
                () -> assertEquals(requestDTO.getCess(), item.getCess()),
                () -> assertEquals(requestDTO.getDiscount(), item.getDiscount()),
                () -> assertEquals(requestDTO.getOpeningStock(), item.getOpeningStock()),
                () -> assertEquals(requestDTO.getFavourite(), item.getFavourite()),
                () -> assertEquals(requestDTO.getInPackage(), item.getInPackage()),
                () -> assertEquals(requestDTO.getDiscountable(), item.getDiscountable()),
                () -> assertEquals(requestDTO.getKitchenStock(), item.getKitchenStock()),
                () -> assertEquals(requestDTO.getVeg(), item.getVeg())
        );
    }

    private void assertResponseMatchesRequest(ItemMasterResponseDTO responseDTO, ItemMasterRequestDTO requestDTO, UUID id) {
        assertAll(
                () -> assertEquals(id, responseDTO.getId()),
                () -> assertEquals(requestDTO.getItemName(), responseDTO.getItemName()),
                () -> assertEquals(requestDTO.getBarcode(), responseDTO.getBarcode()),
                () -> assertEquals(requestDTO.getHsnCode(), responseDTO.getHsnCode()),
                () -> assertEquals(requestDTO.getItemGroupName(), responseDTO.getItemGroupName()),
                () -> assertEquals(requestDTO.getKitchenName(), responseDTO.getKitchenName()),
                () -> assertEquals(requestDTO.getDishHeadName(), responseDTO.getDishHeadName()),
                () -> assertEquals(requestDTO.getUnitName(), responseDTO.getUnitName()),
                () -> assertEquals(requestDTO.getItemDescription(), responseDTO.getItemDescription()),
                () -> assertEquals(requestDTO.getDineInRate(), responseDTO.getDineInRate()),
                () -> assertEquals(requestDTO.getTakeawayRate(), responseDTO.getTakeawayRate()),
                () -> assertEquals(requestDTO.getDeliveryRate(), responseDTO.getDeliveryRate()),
                () -> assertEquals(requestDTO.getOnlineRate(), responseDTO.getOnlineRate()),
                () -> assertEquals(requestDTO.getMrp(), responseDTO.getMrp()),
                () -> assertEquals(requestDTO.getPurchaseRate(), responseDTO.getPurchaseRate()),
                () -> assertEquals(requestDTO.getRoomServiceRate(), responseDTO.getRoomServiceRate()),
                () -> assertEquals(requestDTO.getGst(), responseDTO.getGst()),
                () -> assertEquals(requestDTO.getCess(), responseDTO.getCess()),
                () -> assertEquals(requestDTO.getDiscount(), responseDTO.getDiscount()),
                () -> assertEquals(requestDTO.getOpeningStock(), responseDTO.getOpeningStock()),
                () -> assertEquals(requestDTO.getFavourite(), responseDTO.getFavourite()),
                () -> assertEquals(requestDTO.getInPackage(), responseDTO.getInPackage()),
                () -> assertEquals(requestDTO.getDiscountable(), responseDTO.getDiscountable()),
                () -> assertEquals(requestDTO.getKitchenStock(), responseDTO.getKitchenStock()),
                () -> assertEquals(requestDTO.getVeg(), responseDTO.getVeg())
        );
    }

    private void assertResponseMatchesItem(ItemMasterResponseDTO responseDTO, ItemMaster item) {
        assertAll(
                () -> assertEquals(item.getId(), responseDTO.getId()),
                () -> assertEquals(item.getItemName(), responseDTO.getItemName()),
                () -> assertEquals(item.getBarcode(), responseDTO.getBarcode()),
                () -> assertEquals(item.getHsnCode(), responseDTO.getHsnCode()),
                () -> assertEquals(item.getItemGroupName(), responseDTO.getItemGroupName()),
                () -> assertEquals(item.getKitchenName(), responseDTO.getKitchenName()),
                () -> assertEquals(item.getDishHeadName(), responseDTO.getDishHeadName()),
                () -> assertEquals(item.getUnitName(), responseDTO.getUnitName()),
                () -> assertEquals(item.getItemDescription(), responseDTO.getItemDescription()),
                () -> assertEquals(item.getDineInRate(), responseDTO.getDineInRate()),
                () -> assertEquals(item.getTakeawayRate(), responseDTO.getTakeawayRate()),
                () -> assertEquals(item.getDeliveryRate(), responseDTO.getDeliveryRate()),
                () -> assertEquals(item.getOnlineRate(), responseDTO.getOnlineRate()),
                () -> assertEquals(item.getMrp(), responseDTO.getMrp()),
                () -> assertEquals(item.getPurchaseRate(), responseDTO.getPurchaseRate()),
                () -> assertEquals(item.getRoomServiceRate(), responseDTO.getRoomServiceRate()),
                () -> assertEquals(item.getGst(), responseDTO.getGst()),
                () -> assertEquals(item.getCess(), responseDTO.getCess()),
                () -> assertEquals(item.getDiscount(), responseDTO.getDiscount()),
                () -> assertEquals(item.getOpeningStock(), responseDTO.getOpeningStock()),
                () -> assertEquals(item.getFavourite(), responseDTO.getFavourite()),
                () -> assertEquals(item.getInPackage(), responseDTO.getInPackage()),
                () -> assertEquals(item.getDiscountable(), responseDTO.getDiscountable()),
                () -> assertEquals(item.getKitchenStock(), responseDTO.getKitchenStock()),
                () -> assertEquals(item.getVeg(), responseDTO.getVeg())
        );
    }
}
