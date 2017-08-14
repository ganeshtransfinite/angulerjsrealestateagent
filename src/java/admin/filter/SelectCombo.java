/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.filter;

/**
 *
 * @author user
 */
public class SelectCombo {

    private int id;
    private String text;
    private int count;
 
    private int pid1;

    private int pid2;

    public SelectCombo() {
    }

    public SelectCombo(int id, String text, int count) {
        this.id = id;
        this.text = text;
        this.count = count;
    }

    public SelectCombo(int id, String text) {
        this.id = id;
        this.text = text;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @return the pid1
     */
    public int getPid1() {
        return pid1;
    }

    /**
     * @param pid1 the pid1 to set
     */
    public void setPid1(int pid1) {
        this.pid1 = pid1;
    }

    /**
     * @return the pid2
     */
    public int getPid2() {
        return pid2;
    }

    /**
     * @param pid2 the pid2 to set
     */
    public void setPid2(int pid2) {
        this.pid2 = pid2;
    }

     
}
