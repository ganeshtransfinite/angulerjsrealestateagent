/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author user
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class TableForm  implements Serializable{

    private List items;
    private int total_count;
    private String order;
    private String sort;
    private String q;
    private int per_page;
    private int page;
private String filter;
    /**
     * @return the items
     */
    public List getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List items) {
        this.items = items;
    }

    /**
     * @return the total_count
     */
    public int getTotal_count() {
        return total_count;
    }

    /**
     * @param total_count the total_count to set
     */
    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    /**
     * @return the order
     */
    public String getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(String order) {
        this.order = order;
    }

    /**
     * @return the sort
     */
    public String getSort() {
        return sort;
    }

    /**
     * @param sort the sort to set
     */
    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * @return the q
     */
    public String getQ() {
        return q;
    }

    /**
     * @param q the q to set
     */
    public void setQ(String q) {
        this.q = q;
    }

    /**
     * @return the per_page
     */
    public int getPer_page() {
        return per_page;
    }

    /**
     * @param per_page the per_page to set
     */
    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    /**
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @return the filter
     */
    public String getFilter() {
        return filter;
    }

    /**
     * @param filter the filter to set
     */
    public void setFilter(String filter) {
        this.filter = filter;
    }
}
