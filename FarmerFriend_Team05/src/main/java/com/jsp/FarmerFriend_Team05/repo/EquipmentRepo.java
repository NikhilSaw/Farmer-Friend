package com.jsp.FarmerFriend_Team05.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.FarmerFriend_Team05.entity.Equipment;
import com.jsp.FarmerFriend_Team05.entity.User;

public interface EquipmentRepo extends JpaRepository<Equipment, String> {

	@Query("Select a from Equipment a where a.name=?1")
	public Equipment fetchByName(String name);

	@Query("Select a from Equipment a where a.user=?1")
	public List<Equipment> fetchEquipmentByUserId(User user);
}