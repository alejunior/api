package com.example.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api.domain.Categoria;
import com.example.api.dto.CategoriaDTO;
import com.example.api.repositories.CategoriaRepository;
import com.example.api.services.exception.DataIntegrityException;
import com.example.api.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	@Transactional(readOnly = true)
	public Optional<Categoria> buscarId(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		obj.orElseThrow(() -> new ObjectNotFoundException("Recurso não encontrado."));
		return obj;
	}

	@Transactional(readOnly = true)
	public List<Categoria> buscarNomes(String name) {
		List<Categoria> list = repository.findByNames(name);
		if (list.isEmpty())
			throw new ObjectNotFoundException("Recurso não encontrado.");
		return list;
	}

	@Transactional(readOnly = true)
	public List<Categoria> buscarTodos() {
		List<Categoria> list = repository.findAll();
		if (list.isEmpty())
			throw new ObjectNotFoundException("Recurso não encontrado.");
		return list;
	}

	@Transactional(readOnly = true)
	public Page<Categoria> buscarTodosPagina(Integer page, Integer linesPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	public CategoriaDTO salvar(CategoriaDTO categoriaDto) {
		Categoria categoria = new Categoria(categoriaDto.getId(), categoriaDto.getNome());
		categoria.setId(null);
		try {
			categoria = repository.save(categoria);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Recurso já cadastrado.");
		}
		return categoriaDto = new CategoriaDTO(categoria);
	}

	public Categoria alterar(CategoriaDTO categoriaDto) {
		buscarId(categoriaDto.getId());
		Categoria categoria = new Categoria(categoriaDto.getId(), categoriaDto.getNome());
		try {
			return repository.save(categoria);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Esta categoria já existe.");
		}
	}

	public void apagar(Integer id) {
		buscarId(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Categoria possui produtos atrelados e portanto não pode ser excluida.");
		}
	}

}
