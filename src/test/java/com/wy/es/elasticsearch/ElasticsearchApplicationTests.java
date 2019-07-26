package com.wy.es.elasticsearch;

import com.wy.es.elasticsearch.dao.po.Material;
import com.wy.es.elasticsearch.dao.repository.MaterialRepository;
import org.assertj.core.util.Lists;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ElasticsearchApplication.class})
public class ElasticsearchApplicationTests {

	@Autowired
	private MaterialRepository materialRepository;

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

//	@Autowired
//	private JestClient jestClient;

	@Test
	public void testConnect() {
	}

	@Test
	public void add() throws IOException {
		List<Material> pos = Lists.newArrayList();
		System.out.println(LocalDateTime.now());
		for (int i = 0; i < 1000; i++) {
			Material material = Material.builder().id(Long.valueOf(i))
					.createTime(LocalDate.now().toString()).images("images"+i).brand("brand"+i).category("category"+i).title("title"+i).price(Double.valueOf(i)).build();
			pos.add(material);
//			Index index = new Index.Builder(material).index("jest-test-" + DateFormatUtils.format(new Date(), "yyyy-MM-dd")).type("jest-es").build();
//			jestClient.execute(index);
		}
		materialRepository.saveAll(pos);
		System.out.println(LocalDateTime.now());
		System.out.println("================================");
	}
	@Test
	public void query() {
//		Material material = materialRepository.findByCategory("category14");
//		Iterable<Material> all = materialRepository.findAll();
//		ArrayList<Material> materials = Lists.newArrayList(all);
//		List<Material> materials = materialRepository.findByPriceBetween(2D, 22d);


		// 构建查询条件
		NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
		// 添加基本分词查询
//		queryBuilder.withQuery(QueryBuilders.matchQuery("title", "tit"));
		//模糊查询
//		queryBuilder.withQuery(QueryBuilders.wildcardQuery("title", "tit*"));
		List<String> list = Lists.newArrayList();
		String[] strings = (String[]) list.toArray();
		String[] strs = {"1"};
		queryBuilder.withQuery(QueryBuilders.multiMatchQuery("title", strs));



		// 分页：
		int page = 0;
		int size = 2;
		queryBuilder.withPageable(PageRequest.of(page,size));

		// 搜索，获取结果
		Page<Material> items = materialRepository.search(queryBuilder.build());

		// 总条数
		long total = items.getTotalElements();
		List<Material> materials = items.getContent();
		System.out.println("total = " + total);
		for (Material s : materials) {
			System.out.println(s);
		}
//		material.setImages("TEST-IMAGE");
//		materialRepository.save(material);
//		System.out.println(material);
		System.out.println("================================");
	}
	@Test
	public void update() {
		Material material = Material.builder().id(1L).brand("0").category("a").build();
		materialRepository.save(material);
		System.out.println("================================");
	}
	@Test
	public void dele() {
		Material material = Material.builder().id(1L).brand("0").category("a").build();
		materialRepository.save(material);
		System.out.println("================================");
	}

	@Test
	public void createIndex() {
		elasticsearchTemplate.createIndex(Material.class);
		System.out.println("================================");
	}

	@Test
	public void deleteIndex() {
		elasticsearchTemplate.deleteIndex(Material.class);
		System.out.println("================================");
	}

}
