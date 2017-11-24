package com.pro.entity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name="user")
public class User {
  @Column(name = "id")
  private String id;
  private String name;
  private String age;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  /**
   * 返回JSON格式的String字符串方法
   */
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("{");
    sb.append("\"id\":\"")
            .append(id).append('\"');
    sb.append(",\"name\":\"")
            .append(name).append('\"');
    sb.append(",\"age\":\"")
            .append(age).append('\"');
    sb.append('}');
    return sb.toString();
  }
}
