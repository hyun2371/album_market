package com.prgrms.album_market.album.entity;


import com.prgrms.album_market.common.exception.CustomException;

import java.util.Arrays;

import static com.prgrms.album_market.common.exception.ErrorCode.INVALID_FORMAT_CATEGORY;

public enum Category {
    POP,
    DANCE,
    BALLAD,
    ELEC,
    FOLK, 
    ROCK;

    public static Category matchCategory(String categoryStr) {
        Category category = Arrays.stream(Category.values())
                .filter(categoryType -> categoryType.name().equals(categoryStr))
                .findFirst()
                .orElse(null);
        if (category==null)
            throw new CustomException(INVALID_FORMAT_CATEGORY);
        return category;
    }
}
