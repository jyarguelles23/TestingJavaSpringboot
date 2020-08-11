package com.vicsystems.testingspringboot.Repositories;




import com.vicsystems.testingspringboot.Model.Owner;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
