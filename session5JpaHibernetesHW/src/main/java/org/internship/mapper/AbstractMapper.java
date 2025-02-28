package org.internship.mapper;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractMapper<E, D> {

    // Convert a single entity to a DTO
    public abstract D toDTO(E entity);

    // Convert a single DTO to an entity
    public abstract E toEntity(D dto);

    // Convert a list of entities to a list of DTOs
    public List<D> toDTOList(List<E> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }

    // Convert a list of DTOs to a list of entities
    public List<E> toEntityList(List<D> dtos) {
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
