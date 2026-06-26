

package com.api.billhostpro.service;

import com.api.billhostpro.requestDTO.ItemMasterRequestDTO;
import com.api.billhostpro.responseDTO.ItemMasterResponseDTO;

import java.util.List;
import java.util.UUID;

/**
 * Service interface for Item Master CRUD operations.
 */
public interface ItemMasterService {

    /**
     * Save Item.
     *
     * @param requestDTO Item Request
     * @return Item Response
     */
    ItemMasterResponseDTO save(ItemMasterRequestDTO requestDTO);

    /**
     * Fetch All Items.
     *
     * @return List of Items
     */
    List<ItemMasterResponseDTO> getAll();

    /**
     * Fetch Item By Id.
     *
     * @param id Item Id
     * @return Item Response
     */
    ItemMasterResponseDTO getById(UUID id);

    /**
     * Update Item.
     *
     * @param id Item Id
     * @param requestDTO Updated Item
     * @return Updated Item
     */
    ItemMasterResponseDTO update(UUID id, ItemMasterRequestDTO requestDTO);

    /**
     * Delete Item.
     *
     * @param id Item Id
     */
    void delete(UUID id);

}
