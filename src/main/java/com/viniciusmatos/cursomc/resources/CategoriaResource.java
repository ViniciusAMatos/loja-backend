package com.viniciusmatos.cursomc.resources;


import com.viniciusmatos.cursomc.domain.Categoria;
import com.viniciusmatos.cursomc.dto.CategoriaDto;
import com.viniciusmatos.cursomc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Categoria> find(@PathVariable Integer id) {
        Categoria obj = service.find(id);

        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDto objDTO) {
        Categoria obj = service.fromDto(objDTO);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();

        return ResponseEntity.created(uri).build();

    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDto objDTO, @PathVariable Integer id){
        Categoria obj = service.fromDto(objDTO);
        obj.setId(id);
        obj = service.update(obj);

        return ResponseEntity.noContent().build();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaDto>> findAll() {
        List<Categoria> listObj = service.findAll();
        List<CategoriaDto> listObjDto = listObj.stream()
                .map(obj -> new CategoriaDto(obj))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(listObjDto);
    }

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDto>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Categoria> listObj = service.findPage(page,linesPerPage,orderBy,direction);
        Page<CategoriaDto> listObjDto = listObj.map(obj -> new CategoriaDto(obj));

        return ResponseEntity.ok().body(listObjDto);
    }






}
