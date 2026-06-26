

package com.api.billhostpro.serviceimpl;

import com.api.billhostpro.entity.ItemMaster;
import com.api.billhostpro.repository.ItemMasterRepository;
import com.api.billhostpro.requestDTO.ItemMasterRequestDTO;
import com.api.billhostpro.responseDTO.ItemMasterResponseDTO;
import com.api.billhostpro.service.ItemMasterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ItemMasterServiceImpl implements ItemMasterService {

    private static final Logger logger =
            LoggerFactory.getLogger(ItemMasterServiceImpl.class);

    private final ItemMasterRepository repository;

    public ItemMasterServiceImpl(ItemMasterRepository repository) {
        this.repository = repository;
    }

    @Override
    public ItemMasterResponseDTO save(ItemMasterRequestDTO requestDTO) {

        logger.info("Saving Item : {}", requestDTO.getItemName());

        ItemMaster item = mapToEntity(requestDTO);

        ItemMaster savedItem = repository.save(item);

        logger.info("Item Saved Successfully : {}", savedItem.getId());

        return mapToResponse(savedItem);
    }

    @Override
    public List<ItemMasterResponseDTO> getAll() {

        logger.info("Fetching All Items");

        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ItemMasterResponseDTO getById(UUID id) {

        logger.info("Fetching Item : {}", id);

        ItemMaster item = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item Not Found"));

        return mapToResponse(item);
    }

    @Override
    public ItemMasterResponseDTO update(UUID id, ItemMasterRequestDTO requestDTO) {

        logger.info("Updating Item : {}", id);

        ItemMaster item = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item Not Found"));

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

        ItemMaster updatedItem = repository.save(item);

        logger.info("Item Updated Successfully : {}", updatedItem.getId());

        return mapToResponse(updatedItem);
    }

    @Override
    public void delete(UUID id) {

        logger.info("Deleting Item : {}", id);

        ItemMaster item = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item Not Found"));

        repository.delete(item);

        logger.info("Item Deleted Successfully : {}", id);
    }

    private ItemMaster mapToEntity(ItemMasterRequestDTO dto) {

        ItemMaster item = new ItemMaster();

        item.setItemName(dto.getItemName());
        item.setBarcode(dto.getBarcode());
        item.setHsnCode(dto.getHsnCode());
        item.setItemGroupName(dto.getItemGroupName());
        item.setKitchenName(dto.getKitchenName());
        item.setDishHeadName(dto.getDishHeadName());
        item.setUnitName(dto.getUnitName());
        item.setItemDescription(dto.getItemDescription());
        item.setDineInRate(dto.getDineInRate());
        item.setTakeawayRate(dto.getTakeawayRate());
        item.setDeliveryRate(dto.getDeliveryRate());
        item.setOnlineRate(dto.getOnlineRate());
        item.setMrp(dto.getMrp());
        item.setPurchaseRate(dto.getPurchaseRate());
        item.setRoomServiceRate(dto.getRoomServiceRate());
        item.setGst(dto.getGst());
        item.setCess(dto.getCess());
        item.setDiscount(dto.getDiscount());
        item.setOpeningStock(dto.getOpeningStock());
        item.setFavourite(dto.getFavourite());
        item.setInPackage(dto.getInPackage());
        item.setDiscountable(dto.getDiscountable());
        item.setKitchenStock(dto.getKitchenStock());
        item.setVeg(dto.getVeg());

        return item;
    }

    private ItemMasterResponseDTO mapToResponse(ItemMaster item) {

        ItemMasterResponseDTO dto = new ItemMasterResponseDTO();

        dto.setId(item.getId());
        dto.setItemName(item.getItemName());
        dto.setBarcode(item.getBarcode());
        dto.setHsnCode(item.getHsnCode());
        dto.setItemGroupName(item.getItemGroupName());
        dto.setKitchenName(item.getKitchenName());
        dto.setDishHeadName(item.getDishHeadName());
        dto.setUnitName(item.getUnitName());
        dto.setItemDescription(item.getItemDescription());
        dto.setDineInRate(item.getDineInRate());
        dto.setTakeawayRate(item.getTakeawayRate());
        dto.setDeliveryRate(item.getDeliveryRate());
        dto.setOnlineRate(item.getOnlineRate());
        dto.setMrp(item.getMrp());
        dto.setPurchaseRate(item.getPurchaseRate());
        dto.setRoomServiceRate(item.getRoomServiceRate());
        dto.setGst(item.getGst());
        dto.setCess(item.getCess());
        dto.setDiscount(item.getDiscount());
        dto.setOpeningStock(item.getOpeningStock());
        dto.setFavourite(item.getFavourite());
        dto.setInPackage(item.getInPackage());
        dto.setDiscountable(item.getDiscountable());
        dto.setKitchenStock(item.getKitchenStock());
        dto.setVeg(item.getVeg());

        return dto;
    }
}
