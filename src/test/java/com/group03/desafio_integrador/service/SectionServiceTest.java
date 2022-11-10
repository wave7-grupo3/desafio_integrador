package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.entities.CategoryEnum;

import com.group03.desafio_integrador.entities.Section;
import com.group03.desafio_integrador.repository.SectionRepository;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class SectionServiceTest {

    @InjectMocks
    private SectionService sectionService;

    @Mock
    private SectionRepository sectionRepository;

    private Section mockSectionFresh;
    private Section mockSectionCooler;
    private Section mockSectionFrozen;
    private Section mockCreateSection;

    @BeforeEach
    void setUp() {
        mockSectionFresh = new Section(1L,"fresh",3000.00, CategoryEnum.FS);
        mockSectionCooler = new Section(2L,"cooler",2000.00, CategoryEnum.RF);
        mockSectionFrozen = new Section(3L,"frozen",1000.00, CategoryEnum.FF);
        mockCreateSection = new Section(null,"frozen",1000.00, CategoryEnum.FF);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getById_returnSuccess_whenSectionExists() {
        BDDMockito.when(sectionRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.ofNullable(mockSectionFresh));
        Section sectionByIdFresh = sectionService.getById(1L);
        assertThat(sectionByIdFresh).isNotNull();
        assertThat(sectionByIdFresh).isEqualTo(mockSectionFresh);

        BDDMockito.when(sectionRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.ofNullable(mockSectionCooler));
        Section sectionByIdCooler = sectionService.getById(2L);
        assertThat(sectionByIdCooler).isNotNull();
        assertThat(sectionByIdCooler).isEqualTo(mockSectionCooler);

        BDDMockito.when(sectionRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.ofNullable(mockSectionFrozen));
        Section sectionByIdFrozen = sectionService.getById(3L);
        assertThat(sectionByIdFrozen).isNotNull();
        assertThat(sectionByIdFrozen).isEqualTo(mockSectionFrozen);
    }

    @Test
    void findByCategory_returnSuccess_whenSectionExists() {
        BDDMockito.when(sectionRepository.findByCategory(ArgumentMatchers.any(CategoryEnum.class))).thenReturn(mockSectionFresh);
        Section sectionByCategoryFresh = sectionService.findByCategory(CategoryEnum.FS);
        assertThat(sectionByCategoryFresh).isNotNull();
        assertThat(sectionByCategoryFresh).isEqualTo(mockSectionFresh);
    }

    @Test
    void save_returnNewSection_whenValidData() {
        BDDMockito.when(sectionRepository.save(ArgumentMatchers.any(Section.class))).thenReturn(mockSectionFrozen);
        Section newSection = sectionService.save(mockCreateSection);

        assertThat(newSection).isNotNull();
        assertThat(newSection.getSectionId()).isEqualTo(mockSectionFrozen.getSectionId());
        assertThat(newSection.getCapacity()).isPositive();
        assertThat(newSection.getCategory()).isEqualTo(CategoryEnum.FF);
        assertThat(newSection.getName()).isEqualTo(mockSectionFrozen.getName());
        assertThat(newSection).isSameAs(mockSectionFrozen);
    }


}