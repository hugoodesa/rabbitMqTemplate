package br.stapassoli.msorder.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public abstract class BasicController <Service,DTO>{

    protected final Service service;

    protected BasicController(Service service) {
        this.service = service;
    }

    public abstract ResponseEntity<DTO> post(DTO dto);
    public abstract ResponseEntity<DTO> put(Long id,DTO dto);
    public abstract ResponseEntity delete(Long id);
    public abstract ResponseEntity selectById(Long id);
    public abstract Page<DTO> getAll(Pageable pageable);

}
