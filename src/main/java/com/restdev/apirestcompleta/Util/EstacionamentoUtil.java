package com.restdev.apirestcompleta.Util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EstacionamentoUtil {
    public static String gerarReceibo(){
        LocalDateTime date = LocalDateTime.now();
        String recibo = date.toString().substring(0,19);
        return  recibo.replace("-","")
                .replace(":","")
                .replace("T","-");

    }

}
