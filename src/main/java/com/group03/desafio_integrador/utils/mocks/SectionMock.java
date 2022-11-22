package com.group03.desafio_integrador.utils.mocks;

import com.group03.desafio_integrador.entities.Section;
import com.group03.desafio_integrador.entities.entities_enum.CategoryEnum;

import java.util.List;

public class SectionMock {

    public static List<Section> sectionFromDatabase() {
       return List.of(new Section(null, "Fresh", Double
                        .valueOf("3000.0"), CategoryEnum.FS),
                new Section(null, "Cooler", Double.valueOf("2000.0"), CategoryEnum.RF),
                new Section(null, "Frozen", Double.valueOf("1000.0"), CategoryEnum.FF));
    }
}
