package com.brights.zwitscher.artikel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogArtikelRepository extends JpaRepository<BlogArtikel,Long> {

}
