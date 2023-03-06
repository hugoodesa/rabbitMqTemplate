package br.stapassoli.msorder.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public abstract class BasicService<DTO,Repository> {

    protected final Repository repository;

    public BasicService(Repository repository) {
        this.repository = repository;
    }

    public abstract ResponseEntity<DTO> post(DTO dto);
    public abstract ResponseEntity delete(Long id);
    public abstract ResponseEntity<DTO> select(Long id);
    public abstract ResponseEntity<DTO> put(Long id,DTO dto);
    public abstract Page<DTO> selectAll(Pageable pageable);

}
