package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;
import com.group03.desafio_integrador.dto.ManagerDTO;
import com.group03.desafio_integrador.entities.Manager;
import com.group03.desafio_integrador.repository.ManagerRepository;
import com.group03.desafio_integrador.utils.mocks.TestsMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ManagerServiceTest {

    @InjectMocks
    private ManagerService managerService;

    @Mock
    private ManagerRepository managerRepository;

    @Test
    void loadUserByUsername_returnSuccess_whenUsernameIsValid() {
        var response = new org.springframework.security.core.userdetails
                .User(TestsMocks.mockManager().getUsername(), TestsMocks.mockManager().getPassword(), new ArrayList<>());

        BDDMockito.doReturn(TestsMocks.mockManager()).when(managerRepository)
                .findByUsername(ArgumentMatchers.anyString());

        UserDetails manager = managerService.loadUserByUsername(ArgumentMatchers.anyString());

        assertThat(manager).isNotNull();
        assertThat(manager).isEqualTo(response);
    }

    @Test
    void loadUserByUsername_doThrowUsernameNotFoundException_whenUsernameIsInvalid() {
        BDDMockito.doReturn(null).when(managerRepository)
                .findByUsername(ArgumentMatchers.anyString());

        UsernameNotFoundException usernameNotFound = assertThrows(UsernameNotFoundException.class,
                () -> managerService.loadUserByUsername(ArgumentMatchers.anyString()));

        assertThat(usernameNotFound.getMessage()).isEqualTo("Manager not found in database!");
    }

    @Test
    void updateManager_returnSuccess_whenIdIsValid() {
        Manager responseSave = Manager.builder()
                .managerId(TestsMocks.mockManager().getManagerId())
                .name("John Travolta")
                .username("john")
                .password(TestsMocks.mockManager().getPassword()).build();

        BDDMockito.when(managerRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(TestsMocks.mockManager()));

        BDDMockito.when(managerRepository.save(ArgumentMatchers.any(Manager.class)))
                .thenReturn(responseSave);

        ManagerDTO manager = managerService.updateManager(responseSave);

        Manager managerById = managerService.getManagerById(TestsMocks.mockManager().getManagerId());

        assertThat(manager).isNotNull();
        assertThat(manager.getName()).isNotEqualTo(managerById.getName());
        assertThat(manager.getUsername()).isNotEqualTo(managerById.getUsername());
    }

    @Test
    void updateUsernameManager_returnSuccess_whenIdIsValid() {
        String username = "john";

        Manager responseSave = Manager.builder()
                .managerId(TestsMocks.mockManager().getManagerId())
                .name(TestsMocks.mockManager().getName())
                .username(username)
                .password(TestsMocks.mockManager().getPassword()).build();

        BDDMockito.when(managerRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(TestsMocks.mockManager()));

        BDDMockito.when(managerRepository.save(ArgumentMatchers.any(Manager.class)))
                .thenReturn(responseSave);

        ManagerDTO manager = managerService
                .updateUsernameManager("carlos", String.valueOf(ArgumentMatchers.anyLong()));

        Manager managerById = managerService.getManagerById(TestsMocks.mockManager().getManagerId());

        assertThat(manager).isNotNull();
        assertThat(manager.getUsername()).isNotEqualTo(managerById.getUsername());
    }

    @Test
    void updateNameManager_returnSuccess_whenIdIsValid() {
        String name = "John";

        Manager responseSave = Manager.builder()
                .managerId(TestsMocks.mockManager().getManagerId())
                .name(name)
                .username(TestsMocks.mockManager().getUsername())
                .password(TestsMocks.mockManager().getPassword()).build();

        BDDMockito.when(managerRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(TestsMocks.mockManager()));

        BDDMockito.when(managerRepository.save(ArgumentMatchers.any(Manager.class)))
                .thenReturn(responseSave);

        ManagerDTO manager = managerService.updateNameManager("Carlos", String.valueOf(ArgumentMatchers.anyLong()));

        Manager managerById = managerService.getManagerById(TestsMocks.mockManager().getManagerId());

        assertThat(manager).isNotNull();
        assertThat(manager.getName()).isNotEqualTo(managerById.getName());
    }

    @Test
    void getAll_returnListManagers_whenListIsNotEmpty() {

        BDDMockito.doReturn(List.of(TestsMocks.mockManager())).when(managerRepository).findAll();

        List<Manager> managers = managerService.getAll();

        assertThat(managers).isNotNull();
        assertThat(managers).isNotEmpty();
        assertThat(managers.get(0).getName()).isEqualTo(TestsMocks.mockManager().getName());
        assertThat(managers.get(0).getUsername()).isEqualTo(TestsMocks.mockManager().getUsername());
    }

    @Test
    void getAll_doThrowNotFound_whenListIsEmpty() {

        BDDMockito.doReturn(List.of()).when(managerRepository).findAll();

        NotFoundException notFoundException = assertThrows(NotFoundException.class, () -> managerService.getAll());
        assertThat(notFoundException.getMessage()).isEqualTo("This search did not found any result!");
    }

    @Test
    void getManagerById_returnManager_whenIdIsValid() {
        BDDMockito.when(managerRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(TestsMocks.mockManager()));

        Manager manager = managerService.getManagerById(TestsMocks.mockManager().getManagerId());

        assertThat(manager).isNotNull();
        assertThat(manager.getManagerId()).isEqualTo(TestsMocks.mockManager().getManagerId());
        assertThat(manager.getName()).isEqualTo(TestsMocks.mockManager().getName());
        assertThat(manager.getUsername()).isEqualTo(TestsMocks.mockManager().getUsername());
    }

    @Test
    void getManagerById_doThrowNotFound_whenIdIsNotValid() {

        Long id = TestsMocks.mockManager().getManagerId();

        BDDMockito.doThrow(new NotFoundException("Manager with id " + id + " not found!"))
                .when(managerRepository)
                .findById(ArgumentMatchers.anyLong());

        NotFoundException notFoundException = assertThrows(NotFoundException
                .class, () -> managerService.getManagerById(id));
        assertThat(notFoundException.getMessage()).isEqualTo("Manager with id " + id + " not found!");
    }

    @Test
    void deleteManagerById_doThrowNotFound_whenIdIsNotValid() {
        BDDMockito.when(managerRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(TestsMocks.mockManager()));

        BDDMockito.doNothing().when(managerRepository).deleteById(ArgumentMatchers.anyLong());

        managerService.deleteManagerById(ArgumentMatchers.anyLong());
        BDDMockito.verify(managerRepository, BDDMockito.times(1))
                .deleteById(ArgumentMatchers.anyLong());
    }

    @Test
    void deleteManagerById_withSuccess_whenIdIsValid() {
        Long id = TestsMocks.mockManager().getManagerId();
        BDDMockito.doThrow(new NotFoundException("Manager with id " + id + " not found!"))
                .when(managerRepository)
                .findById(ArgumentMatchers.anyLong());

        NotFoundException notFoundException = assertThrows(NotFoundException
                .class, () -> managerService.getManagerById(id));
        assertThat(notFoundException.getMessage()).isEqualTo("Manager with id " + id + " not found!");

    }

}