package ro.utcn.spet.example.a1.service;

import org.junit.Assert;
import org.junit.Test;
import ro.utcn.spet.example.a1.entity.Userss;
import ro.utcn.spet.example.a1.exception.UserssNotFoundException;
import ro.utcn.spet.example.a1.repository.RepositoryFactory;
import ro.utcn.spet.example.a1.repository.memory.InMemoryRepositoryFactory;

public class UserssManagementServiceUnitTests {

	private static RepositoryFactory createMockedFactory() {
		RepositoryFactory factory = new InMemoryRepositoryFactory();
		factory.createUserssRepository().save(new Userss(1, "A.FN", "A.LN"));
		factory.createUserssRepository().save(new Userss(2, "B.FN", "B.LN"));
		return factory;
	}

	@Test
	public void testRemoveWorksWithExistingId() {
		// arrange - create a mocked factory and a service backed up by this factory
		RepositoryFactory factory = createMockedFactory();
		UserssManagementService service = new UserssManagementService(factory);

		// act - remove a student with a well-known ID
		service.removeUserss(1);

		// assert - expect that the student was removed from the repository and the other student is still there
		Assert.assertEquals(1, factory.createUserssRepository().findAll().size());
		Assert.assertTrue(factory.createUserssRepository().findById(2).isPresent());
	}

	@Test(expected = UserssNotFoundException.class)
	public void testRemoveThrowsWithNotExistingId() {
		// arrange - create a mocked factory and a service backed up by this factory
		RepositoryFactory factory = createMockedFactory();
		UserssManagementService service = new UserssManagementService(factory);

		// act - remove a student with a non-existent ID
		service.removeUserss(999);

		// no assert, we expect an exception (see the @Test annotation)
	}

}
