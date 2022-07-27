package com.mysite.mm;

import com.mysite.mm.store.Store;
import com.mysite.mm.store.StoreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MmApplicationTests {

	@Autowired
	private StoreRepository storeRepository;

	@Test
	void testStore() {
		Store s1 = new Store();
		s1.setLat(33.450701);
		s1.setLng(126.570667);
		this.storeRepository.save(s1);
	}

}
