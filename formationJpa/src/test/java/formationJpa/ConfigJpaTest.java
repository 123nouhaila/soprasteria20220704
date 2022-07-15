package formationJpa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import formationJpa.dao.DaoFormation;
import formationJpa.dao.DaoFormationFactory;
import formationJpa.dao.DaoModule;
import formationJpa.dao.DaoModuleFactory;
import formationJpa.dao.DaoOrdinateur;
import formationJpa.dao.DaoOrdinateurFactory;
import formationJpa.dao.DaoPersonne;
import formationJpa.dao.DaoPersonneFactory;
import formationJpa.entities.Adresse;
import formationJpa.entities.Civilte;
import formationJpa.entities.Formateur;
import formationJpa.entities.Formation;
import formationJpa.entities.Module;
import formationJpa.entities.Ordinateur;
import formationJpa.entities.Personne;
import formationJpa.entities.Ram;
import formationJpa.entities.Stagiaire;
import formationJpa.util.Context;

public class ConfigJpaTest {
	public static void main(String[] args) {

		DaoPersonne daoPersonne = DaoPersonneFactory.getInstance();
		DaoFormation daoFormation = DaoFormationFactory.getInstance();
		DaoModule daoModule = DaoModuleFactory.getInstance();
		Formation formation = new Formation("java", LocalDate.of(2022, 7, 12));
		daoFormation.insert(formation);

		Formateur olivier = new Formateur();
		olivier.setPrenom("olivier");
		olivier.setCivilite(Civilte.MLLE);
		olivier.setAdresse(new Adresse("1", "rue", "3333", "aaaa"));
		daoPersonne.insert(olivier);
		// traitements.....
		// Formateur referent = new Formateur();
		// referent.setId(100L);

		Module java = new Module("java");
		Module jpa = new Module("jpa");
		daoModule.insert(jpa);
		daoModule.insert(java);

		Set<Module> modules = new HashSet<Module>();
		modules.add(jpa);
		modules.add(java);
		olivier.setModules(modules);
		daoPersonne.update(olivier);
		modules.remove(jpa);
		daoPersonne.update(olivier);

		Context.destroy();
	}
}