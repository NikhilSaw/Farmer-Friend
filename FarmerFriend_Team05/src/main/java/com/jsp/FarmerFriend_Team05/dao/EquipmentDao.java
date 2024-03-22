package com.jsp.FarmerFriend_Team05.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.FarmerFriend_Team05.entity.Equipment;
import com.jsp.FarmerFriend_Team05.entity.User;
import com.jsp.FarmerFriend_Team05.repo.EquipmentRepo;

@Repository
public class EquipmentDao {

	@Autowired
	private EquipmentRepo repo;

	public Equipment saveEquipment(Equipment equipment) {
		return repo.save(equipment);
	}

	public Equipment fetchEquipmentById(String equipmentId) {
		return repo.findById(equipmentId).orElse(null);
	}

	public Equipment fetchByName(String name) {
		return repo.fetchByName(name);
	}

	public List<Equipment> fetchEquipmentByUser(User user) {
		return repo.fetchEquipmentByUserId(user);
	}

	public Equipment updateEquipment(Equipment equipment) {
		Optional<Equipment> db = repo.findById(equipment.getId());
		if (db.isPresent()) {
			Equipment e = db.get();
			if (equipment.getName() != null)
				e.setName(equipment.getName());
			if (equipment.getCostPerHour() != 0)
				e.setCostPerHour(equipment.getCostPerHour());
			if (equipment.getNumberOfItems() != 0)
				e.setNumberOfItems(equipment.getNumberOfItems());
			if (equipment.getDiscription() != null)
				e.setDiscription(equipment.getDiscription());
			return repo.save(e);
		} else {
			return null;
		}
	}

	public Equipment deleteEquipmentById(String equipmentId) {
		Optional<Equipment> db = repo.findById(equipmentId);
		if (db.isPresent()) {
			Equipment equipment = db.get();
			equipment.setUser(null);
			updateEquipment(equipment);
			repo.deleteById(equipmentId);
			return db.get();
		} else {
			return null;
		}
	}

	public List<Equipment> fetchAllEquipment() {
		return repo.findAll();
	}

}
