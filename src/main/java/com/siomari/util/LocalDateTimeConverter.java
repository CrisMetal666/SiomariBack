package com.siomari.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Se usara para usar fechas con local date time en los json correctamente
 * @author crismetal
 *
 */
@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<java.time.LocalDateTime, java.sql.Timestamp> {

    @Override
    public java.sql.Timestamp convertToDatabaseColumn(java.time.LocalDateTime attribute) {
        return attribute == null ? null : java.sql.Timestamp.valueOf(attribute);
    }

    @Override
    public java.time.LocalDateTime convertToEntityAttribute(java.sql.Timestamp dbData) {
        return dbData == null ? null : dbData.toLocalDateTime();
    }
}