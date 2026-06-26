

package com.api.billhostpro.controller;

import com.api.billhostpro.requestDTO.ItemMasterRequestDTO;
import com.api.billhostpro.responseDTO.ItemMasterResponseDTO;
import com.api.billhostpro.service.ItemMasterService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api/items")
public class ItemMasterController {

    private static final Logger logger =
            LoggerFactory.getLogger(ItemMasterController.class);

    private final ItemMasterService itemMasterService;

    public ItemMasterController(ItemMasterService itemMasterService) {
        this.itemMasterService = itemMasterService;
    }

    @PostMapping
    public ResponseEntity<ItemMasterResponseDTO> save(
            @Valid @RequestBody ItemMasterRequestDTO requestDTO) {

        logger.info("Received request to save item : {}", requestDTO.getItemName());

        return ResponseEntity.ok(itemMasterService.save(requestDTO));
    }

    @GetMapping
    public ResponseEntity<List<ItemMasterResponseDTO>> getAll() {

        logger.info("Received request to fetch all items");

        return ResponseEntity.ok(itemMasterService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemMasterResponseDTO> getById(
            @PathVariable UUID id) {

        logger.info("Received request to fetch item : {}", id);

        return ResponseEntity.ok(itemMasterService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemMasterResponseDTO> update(
            @PathVariable UUID id,
            @Valid @RequestBody ItemMasterRequestDTO requestDTO) {

        logger.info("Received request to update item : {}", id);

        return ResponseEntity.ok(itemMasterService.update(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable UUID id) {

        logger.info("Received request to delete item : {}", id);

        itemMasterService.delete(id);

        return ResponseEntity.ok("Item Deleted Successfully");
    }

}