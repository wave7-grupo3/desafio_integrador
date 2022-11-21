package com.group03.desafio_integrador.entities.entities_enum;

import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;

public enum PaymentTypeEnum {
    CREDIT_CARD, // 0
    BANK_SLIP, // 1
    PIX;  // 2

    /**
     * Método responsável por converter uma String em Enum.
     * @author Gabriel Morais
     * @return PaymentStatusEnum - Retorna um enum do tipo PaymentStatusEnum.
     * @throws NotFoundException
     */
    public static PaymentTypeEnum toEnum(String string) {
        PaymentTypeEnum enumCategory;

        try {
            enumCategory = PaymentTypeEnum.valueOf(string);
        } catch (Exception ex){
            throw new NotFoundException("This status is not valid");
        }

        return enumCategory;
    }

}
