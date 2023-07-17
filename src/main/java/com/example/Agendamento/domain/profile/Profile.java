package com.example.Agendamento.domain.profile;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document("profiles")
public class Profile {

    @Id
    private String id;
    private String userId;
    private String name;
    private int age;
    private String telefone;

    public Profile(String userId, String name, int age, String telefone) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.telefone = telefone;
    }

    public void updateInformation(UpdateDataProfile data) {
        if(data.name() != null) {
            this.name = data.name();
        }
        if(data.age() != 0) {
            this.age = data.age();
        }
        if(data.telefone() != null) {
            this.telefone = data.telefone();
        }
    }

}
