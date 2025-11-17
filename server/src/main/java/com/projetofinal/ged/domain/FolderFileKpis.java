package com.projetofinal.ged.domain;

public class FolderFileKpis {
    public String type;
    public long quantity;

    public FolderFileKpis(String type, long quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
