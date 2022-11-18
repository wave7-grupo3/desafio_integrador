package com.group03.desafio_integrador.entities.mapper;


import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.List;
import java.util.stream.Collectors;

public class DozerMapper {

    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public static <O, D> D parseObject(O origin, Class<D> destination) {
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseObject(List<O> origin, Class<D> destination) {
        return origin.stream()
                .map(obj -> mapper.map(obj, destination))
                .collect(Collectors.toList());
    }

}
