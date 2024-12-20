package com.ohgiraffers.hellocat.user.dto;

import com.ohgiraffers.hellocat.character.entity.Character;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserFindResponseDto {

    private Long id;
    private Character character;

    @Builder
    public UserFindResponseDto(Long id, Character character) {
        this.id = id;
        this.character = character;
    }
}
