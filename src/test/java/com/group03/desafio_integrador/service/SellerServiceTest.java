package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;
import com.group03.desafio_integrador.dto.ProductSellerDTO;
import com.group03.desafio_integrador.entities.Batch;
import com.group03.desafio_integrador.entities.ProductAdvertising;
import com.group03.desafio_integrador.entities.Seller;
import com.group03.desafio_integrador.repository.SellerRepository;
import com.group03.desafio_integrador.utils.mocks.SellerTestsMocks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SellerServiceTest {

    @InjectMocks
    private SellerService sellerService;

    @Mock
    private SellerRepository sellerRepository;

    @Mock
    private ProductAdvertisingService productService;

    @Mock
    private BatchService batchService;

    private Seller mockSeller;
    private List<Seller> mockSellerList;
    private Seller mockCreateSeller;
    private Batch mockBatch;
    private List<ProductAdvertising> mockProductList;

    private final List<Batch> mockBatchList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        mockSeller = SellerTestsMocks.mockSeller();
        mockSellerList = SellerTestsMocks.mockSellerList();
        mockCreateSeller = SellerTestsMocks.mockcreateSeller();
        mockProductList = SellerTestsMocks.mockProductList();
        mockBatch = SellerTestsMocks.mockBatch();
        mockBatchList.add(mockBatch);
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void getAll_returnAllSellers_whenDataIsValid() {
        BDDMockito.when(sellerRepository.findAll())
                .thenReturn(mockSellerList);

        List<Seller> sellerList = sellerService.getAll();

        assertThat(sellerList).isNotNull();
        assertThat(sellerList).asList();
    }

    @Test
    void getAll_returnRunTimeException_whenNoSellerFound() {
        BDDMockito.when(sellerRepository.findAll())
                .thenReturn(List.of());

        NotFoundException notFoundException = assertThrows(NotFoundException.class, () -> sellerService.getAll());

        assertThat(notFoundException.getMessage()).isEqualTo("No registered seller found!");
    }

    @Test
    void getById_returnSellerById_whenFindSeller() {
        BDDMockito.when(sellerRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(mockSeller));

        Seller seller = sellerService.getById(mockSeller.getSellerId());

        assertThat(seller).isNotNull();
        assertThat(seller.getSellerId()).isEqualTo(mockSeller.getSellerId());
        assertThat(seller.getSellerRating()).isNotNegative();
        assertThat(seller).isEqualTo(mockSeller);
    }

    @Test
    void getById_returnRunTimeException_whenSellerIdNotFound() {
        BDDMockito.when(sellerRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        NotFoundException notFoundException = assertThrows(NotFoundException.class, () -> sellerService.getById(1L));

        assertThat(notFoundException.getMessage()).isEqualTo("Seller not found!");
    }

    @Test
    void save_returnNewSeller_whenValidData() {
        BDDMockito.when(sellerRepository.save(ArgumentMatchers.any(Seller.class)))
                .thenReturn(mockSeller);

        Seller newSeller = sellerService.save(mockCreateSeller);

        assertThat(newSeller).isNotNull();
        assertThat(newSeller.getSellerId()).isEqualTo(mockSeller.getSellerId());
        assertThat(newSeller).isEqualTo(mockSeller);
    }

    @Test
    void update_returnUpdatedSeller_whenValidData() {

        BDDMockito.when(sellerRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(mockSeller));

        BDDMockito.when(sellerRepository.save(ArgumentMatchers.any(Seller.class)))
                .thenReturn(mockSeller);

        Seller updatedSeller = sellerService.update(mockSeller);

        assertThat(updatedSeller).isNotNull();
        assertThat(updatedSeller).isInstanceOf(Seller.class);
        assertThat(updatedSeller.getSellerName()).isEqualTo(mockSeller.getSellerName());
    }

    @Test
    void deleteById_returnSucess_whenDeleteSellerById() {

        BDDMockito.when(sellerRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(mockSeller));

        sellerService.deleteById(1L);

        verify(sellerRepository, times(1)).deleteById(1L);
    }

    @Test
    void getSellerDTO_returnProductSellerDTO() {
        BDDMockito.when(sellerRepository.findAll())
                .thenReturn(mockSellerList);

        BDDMockito.when(productService.getById(ArgumentMatchers.anyLong()))
                .thenReturn(mockProductList.get(0));

        BDDMockito.when(batchService.findBatchByProductId(ArgumentMatchers.any(ProductAdvertising.class)))
                .thenReturn(mockBatchList);

        List<ProductSellerDTO> productSellerDTOList = sellerService.getSellerDTO(1L);

        assertThat(productSellerDTOList).isNotNull();
        assertThat(productSellerDTOList).asList();
    }
}
