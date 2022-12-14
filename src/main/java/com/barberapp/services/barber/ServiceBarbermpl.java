package com.barberapp.services.barber;


import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barberapp.entities.Barber;
import com.barberapp.repositories.RepositoryBarber;



@Service
public class ServiceBarbermpl implements ServiceBarber{
	
	@Autowired(required=true)
	private RepositoryBarber barber;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Barber> findAll() {
		
		return barber.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Barber> findAll(Pageable pageable) {
		return barber.findAll(pageable);
	}
	

	@Transactional(readOnly = true)
	public Optional<Barber> findById(Long id) {

		return barber.findById(id);
	}


	@Transactional
	public Barber save(Barber barberVo) {
		
		return barber.save(barberVo);
	}

	@Override
	public void deletById(Long id) {
		barber.deleteById(id);
		
	}

}
