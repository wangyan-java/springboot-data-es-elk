package com.wy.es.elasticsearch.dao.po;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author WY
 * @Description:
 * @date 2019/7/7
 */
//    indexName：对应索引库名称
//    type：对应在索引库中的类型
//    shards：分片数量，默认5
//    replicas：副本数量，默认1
@Setter
@Getter
@Document(indexName ="#{@material}",type = "material")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Material {

    @Id  //标记一个字段作为id主键
    private Long id;
    @Field(type = FieldType.Text,analyzer = "ik")
    private String title; //标题
    @Field(type = FieldType.Keyword)
    private String category;// 分类
    @Field(type = FieldType.Keyword)
    private String brand; // 品牌
    @Field(type = FieldType.Double)
    private Double price; // 价格
    @Field(type = FieldType.Keyword,index = false)
    private String images; // 图片地址
    //创建时间
    @Field(type = FieldType.Text,pattern = "yyyy-MM-dd")
    private String createTime;

}
