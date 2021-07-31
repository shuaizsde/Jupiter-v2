package com.laioffer.jupiter.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Game.Builder.class)//告诉jackson builder 是谁

@JsonIgnoreProperties(ignoreUnknown = true)//如果前端返回的key值增加了 忽略掉识别不了的
@JsonInclude(JsonInclude.Include.NON_NULL)//如果property == null, 返回json的时候不再生成该key
public class Game {
    //test
    //properties
    @JsonProperty("id")
    private final String id;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("box_art_url")
    private final String boxArtUrl;

    public String getId() {
        return id;
    }
    // getters
    public String getName() {
        return name;
    }

    public String getBoxArtUrl() {
        return boxArtUrl;
    }
    //builder
    //如果前端返回的key值增加了 忽略掉识别不了的
    @JsonIgnoreProperties(ignoreUnknown = true)
    //如果property == null, 返回json的时候不再生成该key
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Builder {
        @JsonProperty("id")
        private String id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("box_art_url")
        private String boxArtUrl;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder boxArtUrl(String boxArtUrl) {
            this.boxArtUrl = boxArtUrl;
            return this;
        }

        public Game build() {
            return new Game(this);
        }
    }
    //constructor
    private Game(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.boxArtUrl = builder.boxArtUrl;
    }
}
