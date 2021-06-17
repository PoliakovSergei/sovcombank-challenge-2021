package ru.sergo.challenge.sovcombank.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Код ответа
 * 0 - успешно
 * 1 - таймаут системы
 * 2 - ошибка получения данных от системы
 */
@AllArgsConstructor
public enum ResponseCode {

    SUCCESS(0),
    TIMEOUT(1),
    ERROR(2);

    @Getter
    private final Integer code;
}
