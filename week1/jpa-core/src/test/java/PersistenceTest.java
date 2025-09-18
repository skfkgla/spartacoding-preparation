import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sparta.entity.Memo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class PersistenceTest {
	EntityManagerFactory emf;
	EntityManager em;

	@BeforeEach
	void setUp() {
		//META-INF에 존재하는 persistence.xml의 <persistence-unit name="memo"> 해당 이름으로 엔티티를 xml에서 읽어온다.
		emf = Persistence.createEntityManagerFactory("memo");
		em = emf.createEntityManager();
	}

	@Test
	@DisplayName("1차 캐시 : Entity 저장")
	void test1() {
		EntityTransaction et = em.getTransaction();

		et.begin();

		try {

			Memo memo = new Memo();
			memo.setId(1L);
			memo.setUsername("narahim");
			memo.setContents("1차 캐시 Entity 저장");

			em.persist(memo);

			et.commit();

		} catch (Exception ex) {
			ex.printStackTrace();
			et.rollback();
		} finally {
			em.close();
		}

		emf.close();
	}

	@Test
	@DisplayName("Entity 조회 : 캐시 저장소에 해당하는 Id가 존재하지 않은 경우")
	void test2() {
		try {

			Memo memo = em.find(Memo.class, 1);
			System.out.println("memo.getId() = " + memo.getId());
			System.out.println("memo.getUsername() = " + memo.getUsername());
			System.out.println("memo.getContents() = " + memo.getContents());


		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			em.close();
		}

		emf.close();
	}

	@Test
	@DisplayName("Entity 조회 : 캐시 저장소에 해당하는 Id가 존재하는 경우")
	void test3() {
		try {

			Memo m = em.find(Memo.class, 1);	//처음 조회
			System.out.println("memo key값이 1인 값 조회 후 캐시 저장소에 저장\n");

			Memo memo = em.find(Memo.class, 1); //두 번째 조회
			System.out.println("memo2.getId() = " + memo.getId());
			System.out.println("memo2.getUsername() = " + memo.getUsername());
			System.out.println("memo2.getContents() = " + memo.getContents());

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			em.close();
		}

		emf.close();
	}

	@Test
	@DisplayName("객체 동일성 보장")
	void test4() {
		EntityTransaction et = em.getTransaction();

		et.begin();

		try {
			Memo memo3 = new Memo();
			memo3.setId(2L);
			memo3.setUsername("Robbert");
			memo3.setContents("객체 동일성 보장");
			em.persist(memo3);

			Memo memo1 = em.find(Memo.class, 1);
			Memo memo2 = em.find(Memo.class, 1);
			Memo memo  = em.find(Memo.class, 2);

			System.out.println(memo1 == memo2);
			System.out.println(memo1 == memo);

			et.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			et.rollback();
		} finally {
			em.close();
		}

		emf.close();
	}

	@Test
	@DisplayName("Entity 삭제")
	void test5() {
		EntityTransaction et = em.getTransaction();

		et.begin();

		try {

			Memo memo = em.find(Memo.class, 2);

			em.remove(memo);

			et.commit();

		} catch (Exception ex) {
			ex.printStackTrace();
			et.rollback();
		} finally {
			em.close();
		}

		emf.close();
	}

	@Test
	@DisplayName("flush() 메서드 확인")
	void test7() {
		EntityTransaction et = em.getTransaction();

		et.begin();

		try {
			Memo memo = new Memo();
			memo.setId(4L);
			memo.setUsername("Flush");
			memo.setContents("Flush() 메서드 호출");
			em.persist(memo);

			System.out.println("flush() 전");
			em.flush(); // flush() 직접 호출
			System.out.println("flush() 후\n");


			System.out.println("트랜잭션 commit 전");
			et.commit();
			System.out.println("트랜잭션 commit 후");

		} catch (Exception ex) {
			ex.printStackTrace();
			et.rollback();
		} finally {
			em.close();
		}

		emf.close();
	}

	@Test
	@DisplayName("변경 감지 확인")
	void test8() {
		EntityTransaction et = em.getTransaction();

		et.begin();

		try {
			System.out.println("변경할 데이터를 조회합니다.");
			Memo memo = em.find(Memo.class, 4);
			System.out.println("memo.getId() = " + memo.getId());
			System.out.println("memo.getUsername() = " + memo.getUsername());
			System.out.println("memo.getContents() = " + memo.getContents());

			System.out.println("\n수정을 진행합니다.");
			memo.setUsername("Update");
			memo.setContents("변경 감지 확인");

			System.out.println("트랜잭션 commit 전");
			et.commit();
			System.out.println("트랜잭션 commit 후");

		} catch (Exception ex) {
			ex.printStackTrace();
			et.rollback();
		} finally {
			em.close();
		}

		emf.close();
	}
}


