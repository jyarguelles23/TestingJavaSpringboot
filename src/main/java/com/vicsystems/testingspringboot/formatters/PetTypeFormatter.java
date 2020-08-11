package com.vicsystems.testingspringboot.formatters;

import com.vicsystems.testingspringboot.Model.PetType;
import com.vicsystems.testingspringboot.Services.PetTypeService;
import com.vicsystems.testingspringboot.fauxspring.Formatter;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;


public class PetTypeFormatter implements Formatter {

    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }


    @Override
    public String print(PetType petType, Locale locale) {
        return null;
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        Collection<PetType> findPetTypes = petTypeService.findAll();

        for (PetType type : findPetTypes) {
            if (type.getName().equals(text)) {
                return type;
            }
        }

        throw new ParseException("type not found: " + text, 0);
    }
}
