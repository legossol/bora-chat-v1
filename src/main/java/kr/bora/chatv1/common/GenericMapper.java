package kr.bora.chatv1.common;

import java.util.List;

public interface GenericMapper<Dto,Entity> {
    Dto toDto(Entity e);
    Entity toEntity(Dto d);
    List<Dto> toDtoList(List<Entity> entities);
    List<Entity> toEntityList(List<Dto> dtos);

}
