package wmcodes.bunker.operacoes.jobs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import wmcodes.bunker.operacoes.jobs.model.Users;
import wmcodes.bunker.operacoes.jobs.repository.UsersRepository;

@Repository
public class LoginUserDetailsService implements UserDetailsService{

	@Autowired
	private UsersRepository usersRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = usersRepository.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
		
		return user;
	}

}
