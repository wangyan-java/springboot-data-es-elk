package com.wy.es.elasticsearch.dao.repository;

import com.wy.es.elasticsearch.dao.po.Material;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface MaterialRepository extends ElasticsearchRepository<Material,Long> {

    Material findByCategory(String category);

    List<Material> findByPriceBetween(Double minPrice,Double maxPrice);

}
