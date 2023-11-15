package com.brights.zwitscher.kommentar;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KommentarRepository extends CrudRepository<Kommentar, Long> {
}
