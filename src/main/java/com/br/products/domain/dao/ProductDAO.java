package com.br.products.domain.dao;

import com.br.products.configurations.DatabaseCollectionProperties;
import com.br.products.domain.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAO {

	private final MongoOperations mongoOperations;
	private final DatabaseCollectionProperties databaseCollectionProperties;

	@Autowired
	public ProductDAO(final MongoOperations mongoOperations, final DatabaseCollectionProperties databaseCollectionProperties) {

		this.mongoOperations = mongoOperations;
		this.databaseCollectionProperties = databaseCollectionProperties;
	}

	public Product saveProduct(Product product) {
		return mongoOperations.save(product,databaseCollectionProperties.getCollectionByMap("default"));
	}

	public Product findByName(String name) {
		return mongoOperations.findOne(Query.query(Criteria.where("name").regex("^"+name+"$","i")), Product.class,databaseCollectionProperties.getCollectionByMap("default"));
	}
	public Product findById(String id) {
		return mongoOperations.findById(id, Product.class,databaseCollectionProperties.getCollectionByMap("default"));
	}

	public boolean deleted(Product product) {
		mongoOperations.remove(new Query(Criteria.where("_id").is(product.getProductId())), Product.class, databaseCollectionProperties.getCollectionByMap("default"));
		return true;
	}

	public List<Product> findAllProducts() {
		return mongoOperations.findAll(Product.class, databaseCollectionProperties.getCollectionByMap("default"));
	}

}
