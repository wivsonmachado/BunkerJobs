package wmcodes.bunker.operacoes.jobs.repository;

import org.springframework.data.repository.CrudRepository;

import wmcodes.bunker.operacoes.jobs.model.Users;

public interface UsersRepository extends CrudRepository<Users, String>{
	Users findByUsername(String username);
}
