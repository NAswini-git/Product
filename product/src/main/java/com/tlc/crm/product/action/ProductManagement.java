package com.tlc.crm.product.action;

import com.tlc.commons.json.Json;
import com.tlc.commons.json.JsonArray;
import com.tlc.commons.json.JsonObject;

import com.tlc.crm.common.action.secure.CrmConfigAction;
import com.tlc.crm.common.config.ConfigManager;
import com.tlc.crm.product.api.ProductManager;
import com.tlc.crm.product.model.Product;
import com.tlc.crm.product.validator.InputValidator;

import com.tlc.validator.type.Group.Create;
import com.tlc.validator.type.Group.Update;
import com.tlc.web.WebAction;

import java.util.ArrayList;
import java.util.Collection;

/**
 * API implementation for the given data.
 *
 * @author AswiniN
 */
@WebAction(path = "/product/mgmt")
public class ProductManagement extends CrmConfigAction<Product> {

    /**
     * Gets instance.
     */
    @Override
    public ConfigManager<Product> getConfigManager() {
        return ProductManager.getInstance();
    }

    /**
     * Constructs data in a Product format.
     *
     * @param jsonObject
     */
    @Override
    public Product construct(final JsonObject jsonObject) {

        final Long id = jsonObject.optLong("id", 0);
        final String productName = jsonObject.optString("productName", null);
        final Integer price = jsonObject.optInt("price", 0);
        final String size = jsonObject.optString("size", null);
        final String quantity = jsonObject.optString("quantity", null);
        final String type = jsonObject.getString("type");

        final Product product = new Product(id, productName, price, size, quantity);

        if (type.equals("create") || type.equals("update")) {
            InputValidator.validateInput(product, getGroups(type));
        }
        return product;
    }

    /**
     * Selected class is returned based on the given type.
     *
     * @param type
     */
    private Class getGroups(final String type) {

        if (type.equals("create")) {
            return Create.class;
        } else {
            return Update.class;
        }
    }

    /**
     * Constructs Json data for products provided.
     *
     * @param model
     */
    @Override
    public JsonObject construct(final Product model) {
        final JsonObject jsonObject = Json.object();

        jsonObject.put("productName", model.getProductName());
        jsonObject.put("price", model.getPrice());
        jsonObject.put("size", model.getSize());
        jsonObject.put("quantity", model.getQuantity());
        return jsonObject;
    }

    /**
     * Iterates the array of data.
     *
     * @param data
     */
    @Override
    public Collection<Product> constructFromArray(final JsonObject data) {
        final Collection<Product> productList = new ArrayList<>();
        final JsonArray dataArray = data.getJsonArray("product");
        final String type = data.getString("type");

        for (int index = 0; index < dataArray.size(); index++) {
            final JsonObject dataObject = dataArray.getJsonObject(index);
            final Long id = dataObject.optLong("id", 0);
            final String productName = dataObject.optString("productName", null);
            final Integer price = dataObject.optInt("price", 0);
            final String size = dataObject.optString("size", null);
            final String quantity = dataObject.optString("quantity", null);

            final Product product = new Product(id, productName, price, size, quantity);

            if (type.equals("create") || type.equals("update")) {

                try {
                    InputValidator.validateInput(product, getGroups(type));
                    productList.add(product);
                } catch (Exception exception) {
                    exception.getMessage();
                }
            }
        }
        return productList;
    }
}
