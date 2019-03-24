
package ro.utcn.spet.example.a1.service;

        import org.junit.Assert;
        import org.junit.Test;
        import ro.utcn.spet.example.a1.entity.QTag;

        import ro.utcn.spet.example.a1.exception.QtagException;
        import ro.utcn.spet.example.a1.repository.RepositoryFactory;
        import ro.utcn.spet.example.a1.repository.memory.InMemoryRepositoryFactory;

public class QtagManagementServiceTest {

    private static RepositoryFactory createMockedFactory() {
        RepositoryFactory factory = new InMemoryRepositoryFactory();
        factory.createQtagRepository().save(new QTag(1, 2, 3));
        factory.createQtagRepository().save(new QTag(2, 4, 5));
        return factory;
    }

    @Test
    public void testRemoveWorksWithExistingId() {
        // arrange - create a mocked factory and a service backed up by this factory
        RepositoryFactory factory = createMockedFactory();
        QtagManagementService service = new QtagManagementService(factory);

        // act - remove a student with a well-known ID
        service.qTagList();

        // assert - expect that the student was removed from the repository and the other student is still there
        Assert.assertEquals(0, factory.createUserssRepository().findAll().size());

    }


}
