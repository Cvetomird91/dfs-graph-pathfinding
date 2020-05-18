package org.pu.ai.model;

public class Link {

    private String relatedName;
    private int length;

    public Link(String rn, int l) {
        this.relatedName = rn;
        this.length = l;
    }

    public String getRelatedName() {
        return relatedName;
    }

    public int getLength() {
        return length;
    }

}
