package org.sid;

import org.sid.dao.ClientRepository;
import org.sid.dao.CompteRepository;
import org.sid.dao.OperationRepository;
import org.sid.entities.*;
import org.sid.metier.IbanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class VotrebanqueApplication implements CommandLineRunner {
     @Autowired
	 private ClientRepository clientRepository;
	@Autowired
	private CompteRepository compteRepository ;
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private IbanqueMetier ibanqueMetier ;

	public static void   main(String[] args) {
		SpringApplication.run(VotrebanqueApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
/*
		Client c1= clientRepository.save(new Client("sidizein","sidi@gmail.com"));
		Client c2= clientRepository.save(new Client("ahmedMed","ahmedMed@gmail.com"));

		Compte CP1=compteRepository.save(new CompteCourant("cp1",new Date(),7000, c1,6000));
		Compte CP2=compteRepository.save(new CompteEpargne("cp2",new Date(),5500, c2,6.4));

		operationRepository.save(new Versement(new Date(),8000,CP1));
		operationRepository.save(new Versement(new Date(),5000,CP1));
		operationRepository.save(new Retrait(new Date(),4000,CP1));

		operationRepository.save(new Versement(new Date(),12000,CP2));
		operationRepository.save(new Versement(new Date(),9000,CP2));
		operationRepository.save(new Retrait(new Date(),6000,CP2));

		ibanqueMetier.verser("cp1",666);
		ibanqueMetier.retirer("cp2",250);

 */

	}
}
