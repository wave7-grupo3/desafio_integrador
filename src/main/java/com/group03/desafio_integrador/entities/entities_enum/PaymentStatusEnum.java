package com.group03.desafio_integrador.entities.entities_enum;

import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;

public enum PaymentStatusEnum {
    PAID, // 0
    OPEN, // 1
    CANCELED;  // 2

    /**
     * Método responsável por converter uma String em Enum.
     * @author Gabriel Morais
     * @return PaymentStatusEnum - Retorna um enum do tipo PaymentStatusEnum.
     * @throws NotFoundException
     */
    public static PaymentStatusEnum toEnum(String string) {
        PaymentStatusEnum enumCategory;

        try {
            enumCategory = PaymentStatusEnum.valueOf(string);
        } catch (Exception ex){
            throw new NotFoundException("This status is not valid");
        }

        return enumCategory;
    }

}
