package com.barberapp.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barberapp.entities.Barber;

import com.barberapp.services.barber.ServiceBarber;


@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("barber")
public class ControllerBarber {
	
	@Autowired
	private ServiceBarber BarberSevice;
	
	
	 /////////////////// BARBER REGISTRAR   http://localhost:8080/barber/save ////////////////
	@PostMapping("/save")
	public ResponseEntity<?> create (@RequestBody Barber barber){
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(BarberSevice.save(barber));
		
	}
	
	/////////////////// CONSULT BARBER   http://localhost:8080/barber/consult/ID ////////////////
	@GetMapping("/consult/{id}")
	public ResponseEntity<Optional<Barber>> read(@PathVariable(value = "id") Long barberid){
		Optional<Barber> baberaoptional=BarberSevice.findById(barberid);
		
		if(baberaoptional.isPresent()) {
			return ResponseEntity.ok(baberaoptional);
		}else {
			return ResponseEntity.notFound().build();
		}
	
		
	}
	
	/////////////////// UPDATE BARBER   http://localhost:8080/barber/update/ID ////////////////
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?>update(@RequestBody Barber barberNew,@PathVariable (value = "id")Long barberid ){
		Optional<Barber> barbero=BarberSevice.findById(barberid);
		
		if(!barbero.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		barbero.get().setAge(barberNew.getAge());
		barbero.get().setDescription(barberNew.getDescription());
		barbero.get().setGender(barberNew.getGender());
		barbero.get().setQualification(barberNew.getQualification());
		barbero.get().setId_catalogue(barberNew.getId_catalogue());
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(BarberSevice.save(barbero.get()));
	
	}
	
	/////////////////// DELETE BARBER   http://localhost:8080/barber/delete/ID ////////////////
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Barber> deleteUser(@PathVariable(value = "id")  Long id){
		
		
		if(BarberSevice.findById(id).isPresent()) {
			
			BarberSevice.deletById(id);
			return ResponseEntity.ok().build();
			
		} else {
			
			return ResponseEntity.notFound().build();
		}		
	}
	
	/////////////////// CONSULT ALL BARBER   http://localhost:8080/barber/consultall ///////////////	
	@GetMapping("/consultall")
	public  List<Barber> consultAllUsers(){
		
		
		List<Barber> barber = StreamSupport
				.stream(BarberSevice.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return  barber;
	}

}
