package com;


import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class Data2 {
  private String channel;
  private String capabilities;
  private List<String> conversation;
  private List<String> messages;
  private String callbackUrl;
  private Map<String, Object> metadata;
}
