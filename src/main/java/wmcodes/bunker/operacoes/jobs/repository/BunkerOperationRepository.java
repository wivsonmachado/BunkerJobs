package wmcodes.bunker.operacoes.jobs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import wmcodes.bunker.operacoes.jobs.model.BunkerOperation;

public interface BunkerOperationRepository extends JpaRepository<BunkerOperation, Integer>{

}
