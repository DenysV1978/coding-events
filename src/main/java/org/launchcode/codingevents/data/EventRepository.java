package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository //it says this class should be managed for us by Springboot
public interface EventRepository extends CrudRepository<Event, Integer> {

}

//Event - type of class stored
//type of primary keys - Integer
