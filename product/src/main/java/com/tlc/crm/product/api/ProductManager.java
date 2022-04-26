package com.tlc.crm.product.api;

import com.tlc.commons.code.ErrorCode;

import com.tlc.crm.common.config.AuditEntry;
import com.tlc.crm.common.config.ConfigManager;
import com.tlc.crm.product.model.Product;
import com.tlc.crm.product.sql.resource.PRODUCTDETAILS;

import com.tlc.crm.product.status.ProductErrorCode;
import com.tlc.sql.SQLAccess;
import com.tlc.sql.api.DataContainer;
import com.tlc.sql.api.Row;
import com.tlc.sql.api.dml.Table;
import com.tlc.sql.api.ds.OrgDataStore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Implements CRUD operation.
 *
 * @author AswiniN
 */
public class ProductManager implements ConfigManager<Product> {

    private static final Table TABLE = Table.get(PRODUCTDETAILS.TABLE);
    private static final OrgDataStore ORG_DATA_STORE = getOrgDataStore();

    /**
     * Creates instance.
     */
    private static class Instance {
        private static final ProductManager INSTANCE = new ProductManager();
    }

    private ProductManager() {

    }

    /**
     * Gets instance.
     */
    public static ProductManager getInstance() {
        return Instance.INSTANCE;
    }

    /**
     * Get Org_id to use OrgDataStore.
     */
    public static OrgDataStore getOrgDataStore() {
        return SQLAccess.get().getOrgDataStore(Long.valueOf(1));
    }

    /**
     * Inserts the product.
     *
     * @param product
     */
    @Override
    public void create(final Product product) {
    }

    /**
     * Inserts collection of data.
     *
     * @param models
     */
    @Override
    public void create(final Collection<Product> models) {

        for (final Product product : models) {
            final Row row = new Row(TABLE);
            setRow(row, product);
            ORG_DATA_STORE.addRow(row);
        }
    }

    /**
     * Sets data into the row.
     *
     * @param row
     * @param product
     */
    private void setRow(final Row row, final Product product) {
        row.set(PRODUCTDETAILS.PRODUCTNAME, product.getProductName());
        row.set(PRODUCTDETAILS.PRICE, product.getPrice());
        row.set(PRODUCTDETAILS.QUANTITY, product.getQuantity());
        row.set(PRODUCTDETAILS.SIZE, product.getSize());
    }

    /**
     * Updates the product.
     *
     * @param product
     */
    @Override
    public void update(final Product product) {
        final DataContainer dataContainer = DataContainer.create();
        final Row row = new Row(TABLE, product.id());

        if (exists(product)) {
            setRow(row, product);
            dataContainer.updateRow(row);
            ORG_DATA_STORE.commitChanges(dataContainer);
        } else {
            throw ErrorCode.get(ProductErrorCode.ID_NOT_FOUND);
        }
    }

    /**
     * Deletes the product.
     *
     * @param product
     */
    @Override
    public void delete(final Product product) {

        if (exists(product)) {
            delete(List.of(product));
        } else {
            throw ErrorCode.get(ProductErrorCode.ID_NOT_FOUND);
        }
    }

    /**
     * Deletes the product.
     *
     * @param product
     */
    @Override
    public void delete(final Collection<Product> product) {
        final Collection<Long> id = new ArrayList<>();

        product.forEach(products -> id.add(products.id()));
        ORG_DATA_STORE.delete(TABLE, id);
    }

    /**
     * Checks existence of product.
     *
     * @param product
     */
    @Override
    public boolean exists(final Product product) {
        return ORG_DATA_STORE.get(TABLE, product.id()) != null ? true : false;
    }

    /**
     * Gets the product.
     *
     * @param id
     */
    @Override
    public Product partialGet(final Long id) {
        final Product product = new Product();

        product.setId(id);
        return product;
    }

    /**
     * Gets the product.
     *
     * @param id
     */
    @Override
    public Product get(final Long id) {
        final Product product = new Product();
        final Row row = ORG_DATA_STORE.get(TABLE, id);

        if (row != null) {
            product.setId(row.get("ID"));
            product.setProductName(row.get("PRODUCTNAME"));
            product.setPrice(row.get("PRICE"));
            product.setQuantity(row.get("QUANTITY").toString());
            product.setSize(row.get("SIZE"));

            return product;
        } else {
            throw ErrorCode.get(ProductErrorCode.ID_NOT_FOUND);
        }
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Collection<Product> get(final Collection<Long> id) {
        return null;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public AuditEntry auditEntry(final Product product) {
        return null;
    }
}


