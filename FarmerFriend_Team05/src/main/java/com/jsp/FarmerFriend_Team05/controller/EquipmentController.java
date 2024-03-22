package com.jsp.FarmerFriend_Team05.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.FarmerFriend_Team05.entity.Equipment;
import com.jsp.FarmerFriend_Team05.service.EquipmentService;
import com.jsp.FarmerFriend_Team05.util.ResponseStructure;

@RestController
public class EquipmentController {

	@Autowired
	private EquipmentService service;
	
	@PostMapping("/saveEquipment")
	public ResponseEntity<ResponseStructure<Equipment>> saveEquipment(@RequestParam String userId, @RequestBody Equipment equipment) {
		return service.saveEquipment(userId, equipment);
	}
	
	@GetMapping("/fetchEquipmentById")
	public ResponseEntity<ResponseStructure<Equipment>> fetchEquipmentById(@RequestParam String equipmentId) {
		return service.fetchEquipmentById(equipmentId);
	}
	
	@GetMapping("/fetchAllEquipment")
	public ResponseEntity<ResponseStructure<Equipment>> fetchEquipmentAll() {
		return service.fetchAllEquipment();
	}
	
	@GetMapping("/fetchEquipmentByName")
	public ResponseEntity<ResponseStructure<Equipment>> fetchEquipmentByName(@RequestParam String name) {
		return service.fetchEquipmentByName(name);
	}
	
	@GetMapping("/fetchByuserId")
	public ResponseEntity<ResponseStructure<List<Equipment>>> fetchEquipmentByUserId(@RequestParam String userId){
		return service.fetchEquipmentByUserId(userId);
	}
	
	@PutMapping("/updateEquipment")
	public ResponseEntity<ResponseStructure<Equipment>> updateEquipment(@RequestBody Equipment equipment){
		return service.updateEquipment(equipment);
	}
	
	@DeleteMapping("/deleteEquipment")
	public ResponseEntity<ResponseStructure<Equipment>> deleteEquipment(@RequestParam String equipmentId){
		return service.deleteEquipment(equipmentId);
	}
}

