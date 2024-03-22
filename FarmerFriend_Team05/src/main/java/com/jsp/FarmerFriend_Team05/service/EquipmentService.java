package com.jsp.FarmerFriend_Team05.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.FarmerFriend_Team05.dao.EquipmentDao;
import com.jsp.FarmerFriend_Team05.dao.UserDao;
import com.jsp.FarmerFriend_Team05.entity.Equipment;
import com.jsp.FarmerFriend_Team05.entity.User;
import com.jsp.FarmerFriend_Team05.exception.EquipmentNotFoundException;
import com.jsp.FarmerFriend_Team05.exception.UserNotFoundException;
import com.jsp.FarmerFriend_Team05.util.ResponseStructure;

@Service
public class EquipmentService {

	@Autowired
	private EquipmentDao dao;
	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<Equipment>> saveEquipment(String userId, Equipment equipment) {
		User db = userDao.fetchUserById(userId);
		ResponseStructure<Equipment> m = new ResponseStructure<Equipment>();
		if (db != null) {
			equipment.setUser(db);
			dao.saveEquipment(equipment);
			m.setData(equipment);
			m.setMessage("Equipment Added Successfully");
			m.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Equipment>>(m, HttpStatus.CREATED);
		} else {
			throw new UserNotFoundException("User not Found with Id = " + userId);
		}
	}

	public ResponseEntity<ResponseStructure<Equipment>> fetchEquipmentById(String equipmentId) {
		Equipment db = dao.fetchEquipmentById(equipmentId);
		if (db != null) {
			ResponseStructure<Equipment> m = new ResponseStructure<Equipment>();
			m.setData(db);
			m.setMessage("Equipment Fetched Successfully");
			m.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Equipment>>(m, HttpStatus.FOUND);
		} else {
			throw new EquipmentNotFoundException("Equipment not Found with Id = " + equipmentId);
		}
	}

	public ResponseEntity<ResponseStructure<Equipment>> fetchAllEquipment() {
		List<Equipment> equipments = dao.fetchAllEquipment();
		ResponseStructure<Equipment> m = new ResponseStructure<Equipment>();
		if (!equipments.isEmpty()) {
			m.setListData(equipments);
			m.setMessage("All Equipment Fetched Successfully");
			m.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Equipment>>(m, HttpStatus.FOUND);
		} else {
			throw new EquipmentNotFoundException("Equipment List is Empty");
		}
	}

	public ResponseEntity<ResponseStructure<Equipment>> fetchEquipmentByName(String name) {
		Equipment db = dao.fetchByName(name);
		ResponseStructure<Equipment> m = new ResponseStructure<>();
		if (db != null) {
			m.setData(db);
			m.setMessage("Fetch Equiment by name successfully");
			m.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Equipment>>(m, HttpStatus.FOUND);
		} else {
			throw new EquipmentNotFoundException("Equipment not Found by Name = " + name);
		}
	}

	public ResponseEntity<ResponseStructure<List<Equipment>>> fetchEquipmentByUserId(String userId) {
		User db = userDao.fetchUserById(userId);
		ResponseStructure<List<Equipment>> m = new ResponseStructure<List<Equipment>>();
		if (db != null) {
			List<Equipment> equipments = dao.fetchEquipmentByUser(db);
			if (equipments.isEmpty()) {
				m.setData(null);
				m.setMessage("No Equipments Listed to User -> " + db.getFirstName() + " " + db.getLastName());
			} else {
				m.setData(equipments);
				m.setMessage("Fetch Equipment by User_id Successfully");
			}
			m.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Equipment>>>(m, HttpStatus.FOUND);
		} else {
			throw new UserNotFoundException("User not Found with Id = " + userId);
		}
	}

	public ResponseEntity<ResponseStructure<Equipment>> updateEquipment(Equipment equipment) {
		ResponseStructure<Equipment> m = new ResponseStructure<Equipment>();
		Equipment db = dao.fetchEquipmentById(equipment.getId());
		if (db != null) {
			m.setData(dao.updateEquipment(equipment));
			m.setMessage("Equipment Updated Successfully");
			m.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Equipment>>(m, HttpStatus.OK);
		} else {
			throw new EquipmentNotFoundException("Equipment not Found with Id = " + equipment.getId());
		}
	}

	public ResponseEntity<ResponseStructure<Equipment>> deleteEquipment(String equipmentId) {
		ResponseStructure<Equipment> m = new ResponseStructure<Equipment>();
		Equipment db = dao.fetchEquipmentById(equipmentId);
		if (db != null) {
			m.setData(dao.deleteEquipmentById(equipmentId));
			m.setMessage("Delete Equipment Successfully");
			m.setStatus(HttpStatus.GONE.value());
			return new ResponseEntity<ResponseStructure<Equipment>>(m, HttpStatus.GONE);
		} else {
			throw new EquipmentNotFoundException("Equipment Not found with Id = " + equipmentId);
		}
	}

}
