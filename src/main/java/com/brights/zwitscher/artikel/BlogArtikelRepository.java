package com.brights.zwitscher.artikel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogArtikelRepository extends CrudRepository<BlogArtikel, Long> {

}
