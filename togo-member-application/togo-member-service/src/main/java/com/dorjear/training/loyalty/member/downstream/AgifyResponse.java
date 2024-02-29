package com.dorjear.training.loyalty.member.downstream;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TypeDef;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@TypeDef(name = "json", typeClass = JsonType.class)
public class AgifyResponse {
    private String name;
    private int age;
    private int count;
}
