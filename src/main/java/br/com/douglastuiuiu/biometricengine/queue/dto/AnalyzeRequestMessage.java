package br.com.douglastuiuiu.biometricengine.queue.dto;

import java.io.Serializable;

public class AnalyzeRequestMessage implements Serializable {

    private static final long serialVersionUID = 6502898325478301267L;

    private String id;

    public AnalyzeRequestMessage() {
    }

    public AnalyzeRequestMessage(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
