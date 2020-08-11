package com.vicsystems.testingspringboot.Services;



import com.vicsystems.testingspringboot.Model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
 }
