package com.sparta.memo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.memo.entity.Memo;
import com.sparta.memo.repository.MemoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@SpringBootTest
public class TransactionTest {

	@PersistenceContext
	EntityManager em;

	@Autowired
	MemoRepository memoRepository;



	@Test
	@Transactional
	@Rollback(value = false) // 테스트 코드에서 @Transactional 를 사용하면 테스트가 완료된 후 롤백하기 때문에 false 옵션 추가
	@DisplayName("메모 생성 성공")
	void test1() {
		Memo memo = new Memo();
		memo.setUsername("Robbert");
		memo.setContents("@Transactional 테스트 중!");

		em.persist(memo);  // 영속성 컨텍스트에 메모 Entity 객체를 저장합니다.
	}

	@Test
	@Disabled
	@DisplayName("메모 생성 실패 - 트랜젝션을 걸지 않아 실패합니다.")
	void test2() {
		Memo memo = new Memo();
		memo.setUsername("Robbie");
		memo.setContents("@Transactional 테스트 중!");

		em.persist(memo);  // 영속성 컨텍스트에 메모 Entity 객체를 저장합니다.
	}

	@Test
	@Transactional
	@Disabled
	@Rollback(value = false)
	@DisplayName("트랜잭션 전파 테스트 - 부모 트렌잭션으로 덮어 씌워지는지 테스트")
	void test3() {
		// memoRepository.createMemo(em);
		System.out.println("테스트 test3 메서드 종료");
	}

	@Test
	@Disabled
	@DisplayName("트랜잭션 전파 테스트 - 자식 트랜잭션이 존재하기 때문에 오류는 발생하지 않고 createMemo 가 먼저 발생한다.")
	void test4() {
		// memoRepository.createMemo(em);
		System.out.println("테스트 test3 메서드 종료");
	}
}


