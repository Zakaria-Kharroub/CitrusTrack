package com.example.citron;

import com.example.citron.domaine.Farm;
import com.example.citron.domaine.Field;
import com.example.citron.repository.FieldRepository;
import com.example.citron.service.FarmService;
import com.example.citron.service.FieldService;
import com.example.citron.service.impl.FieldServiceImpl;
import com.example.citron.web.errors.farm.FarmFieldLimitException;
import com.example.citron.web.errors.farm.FarmNotFoundException;
import com.example.citron.web.errors.field.FieldAreaSuperieurCinquanteException;
import com.example.citron.web.errors.field.FieldNotFoundException;
import com.example.citron.web.errors.field.TotalFieldAreaExceedsFarmAreaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class FieldServiceTest {


    @Mock
    private FieldRepository fieldRepository;

    @Mock
    private FarmService farmService;

    private FieldService fieldService;
    private Farm testFarm;
    private Field testField;
    private final UUID FARM_ID = UUID.randomUUID();
    private final UUID FIELD_ID = UUID.randomUUID();

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        fieldService = new FieldServiceImpl(fieldRepository,farmService);
        // initials farm test
        testFarm = new Farm();
        testFarm.setId(FARM_ID);
        testFarm.setTotalArea(100.0);
        testFarm.setFields(new ArrayList<>());

        // init feield test
        testField = new Field();
        testField.setId(FIELD_ID);
        testField.setArea(20.0);
        testField.setFarm(testFarm);
        testField.setTrees(new ArrayList<>());

    }

    @Test
    void shouldSaveFieldSuccess() {
//        given
        when(farmService.findById(FARM_ID.toString())).thenReturn(testFarm);
        when(fieldRepository.save(any(Field.class))).thenReturn(testField);
//        when
        Field savedField = fieldService.save(testField);
//        then
        assertNotNull(savedField);
        assertEquals(testField.getArea(), savedField.getArea());
        assertEquals(testField.getFarm().getId(), savedField.getFarm().getId());
        verify(fieldRepository, times(1)).save(any(Field.class));
    }

    @Test
    void shouldThrowExceptionWhenFarmNotFound() {

        when(farmService.findById(FARM_ID.toString())).thenReturn(null);

        assertThrows(FarmNotFoundException.class,
                () -> fieldService.save(testField));
        verify(fieldRepository, never()).save(any(Field.class));
    }

    @Test
    void shouldThrowExceptionWhenFarmLimitField() {

        List<Field> existingFields = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            existingFields.add(new Field());
        }
        testFarm.setFields(existingFields);
        when(farmService.findById(FARM_ID.toString())).thenReturn(testFarm);

        assertThrows(FarmFieldLimitException.class,
                () -> fieldService.save(testField));
        verify(fieldRepository, never()).save(any(Field.class));
    }


    @Test
    @DisplayName("Should throw FieldAreaSuperieurCinquanteException when field area exceeds 50% of farm area")
    void shouldThrowExceptionWhenFieldAreaSuperieurCinquante() {
        // Given
        testField.setArea(60.0);
        when(farmService.findById(FARM_ID.toString())).thenReturn(testFarm);

        // When & Then
        assertThrows(FieldAreaSuperieurCinquanteException.class, () -> fieldService.save(testField));
        verify(fieldRepository, never()).save(any(Field.class));
    }



    @Test
    void shouldThrowExceptionWhenTotalFieldAreaExceedsFarmArea() {

        Field existingField = new Field();
        existingField.setArea(90.0);
        testFarm.getFields().add(existingField);
        when(farmService.findById(FARM_ID.toString())).thenReturn(testFarm);

        assertThrows(TotalFieldAreaExceedsFarmAreaException.class, () -> fieldService.save(testField));
        verify(fieldRepository, never()).save(any(Field.class));
    }


    @Test
    void testFindFieldById() {
        when(fieldRepository.findById(FIELD_ID)).thenReturn(Optional.of(testField));

        Field foundField = fieldService.findById(FIELD_ID.toString());

        assertNotNull(foundField);
        assertEquals(FIELD_ID, foundField.getId());
        verify(fieldRepository, times(1)).findById(FIELD_ID);
    }


    @Test
    void shouldThrowExceptionWhenFieldNotFound() {
        when(fieldRepository.findById(FIELD_ID)).thenReturn(Optional.empty());

        assertThrows(FieldNotFoundException.class, () ->
                fieldService.findById(FIELD_ID.toString())
        );
    }


    @Test
    @DisplayName("Should update field successfully")
    void testUpdateFieldSuccessfully() {
        // Given
        Field existingField = new Field();
        existingField.setId(FIELD_ID);
        existingField.setArea(30.0);
        existingField.setFarm(testFarm);

        Field updatedField = new Field();
        updatedField.setId(FIELD_ID);
        updatedField.setArea(40.0);
        updatedField.setFarm(testFarm);

        when(fieldRepository.findById(FIELD_ID)).thenReturn(Optional.of(existingField));
        when(farmService.findById(FARM_ID.toString())).thenReturn(testFarm);
        when(fieldRepository.save(any(Field.class))).thenReturn(updatedField);

        Field result = fieldService.update(FIELD_ID, updatedField);

        assertNotNull(result);
        assertEquals(40.0, result.getArea());
        verify(fieldRepository, times(1)).save(any(Field.class));
    }








}
