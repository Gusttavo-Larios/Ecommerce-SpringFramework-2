package com.tads4.tads4.service;

import com.tads4.tads4.dto.UserDTO;
import com.tads4.tads4.entities.User;
import com.tads4.tads4.repositories.UserRepository;
import com.tads4.tads4.service.exceptions.DatabaseException;
import com.tads4.tads4.service.exceptions.UserNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public UserDTO findById(Long id) {
        User user = repository.findById(id).orElseThrow(
                ()-> new UserNotFoundException("Usuário não encontrado"));
        return new UserDTO(user);
    }

    @Transactional (readOnly = true)
    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> result = repository.findAll(pageable);
        return result.map(x-> new UserDTO(x));
    }

    @Transactional
    public UserDTO insert(UserDTO dto) {
        User entity = new User();
        copyDtoToEntity(dto, entity);
        entity =repository.save(entity);
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO update (Long id, UserDTO dto) {

        try{
            User entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            return new UserDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new UserNotFoundException("Recurso não encontrado");
        }

    }

    @Transactional (propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        try{
            repository.deleteById(id);
        } catch(EmptyResultDataAccessException e){
            throw new UserNotFoundException("Recurso não encontrado");
        } catch(DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integrigadade referencial");
        }

    }

    private void copyDtoToEntity(UserDTO dto, User entity) {
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setBirthDate(dto.getBirthDate());
        entity.setPassword(dto.getPassword());
        entity.setPhone(dto.getPhone());
    }

}