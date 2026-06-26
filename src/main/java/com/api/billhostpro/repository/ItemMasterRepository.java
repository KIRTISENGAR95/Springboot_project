

package com.api.billhostpro.repository;

import com.api.billhostpro.entity.ItemMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for ItemMaster.
 *
 * Provides CRUD database operations.
 */
@Repository
public interface ItemMasterRepository extends JpaRepository<ItemMaster, UUID> {

}
